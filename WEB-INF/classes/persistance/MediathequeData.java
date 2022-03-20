package persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jdk.internal.misc.FileSystemOption;
import mediatek2022.*;


// classe mono-instance  dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	Connection conn = null;
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
		try {
			//Connexion à la bdd MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur connexion bdd mysql");
		}
	}

	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		List<Document> tousLesDocumentsDisponibles = new ArrayList<Document>();
		String sqlDocsLibres = "SELECT * FROM documents WHERE empruntUser IS NULL";
		try {
			Statement stmtDocsLibres = conn.createStatement();
			ResultSet resDocLibres = stmtDocsLibres.executeQuery(sqlDocsLibres);
//			if(resDoc == null)
//				return tousLesDocumentsDisponibles;
			while(resDocLibres.next()) {
				tousLesDocumentsDisponibles.add(new PersisDocument(resDocLibres.getInt("id"), resDocLibres.getString("auteur"), resDocLibres.getString("nomDoc"), resDocLibres.getString("typeDoc"), resDocLibres.getInt("empruntUser")));
				System.out.println(resDocLibres.getInt(6));
				return tousLesDocumentsDisponibles;
			}	
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION GetDocument()");
			e.printStackTrace();
		}
		return tousLesDocumentsDisponibles;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		//Expliqué avec Brette
		//Avec notre méthode de bdd nécessaire de créer les doc à chaque getUsER pour les remettre dans object[] data
		//Dans la variable session on devra ranger login et mdp pour pouvoir faire appel à la méthode ou les mettre dans la fonction data
		//
		String sqlUser = "SELECT idUser, nomUser, mdp, typeUser FROM utilisateurs WHERE nomUser = ? AND mdp = ?";
		
		try {
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser);
            stmtUser.setString(1, login);
            stmtUser.setString(2, password);
            ResultSet resUser = stmtUser.executeQuery();
			if(resUser == null)
				return null;
			while(resUser.next()) {
				Utilisateur user = new PersisUtilisateur(resUser.getInt("idUser"),resUser.getString("nomUser"), resUser.getString("mdp"), resUser.getString("typeUser"));
				return user;
			}	
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION GETUSER()");
			e.printStackTrace();
		}
		return null;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String sqlDoc = "SELECT * FROM documents WHERE id= ?";
		try {
			PreparedStatement stmtDoc = conn.prepareStatement(sqlDoc);
			stmtDoc.setInt(1, numDocument);
			ResultSet resDoc = stmtDoc.executeQuery();
			System.out.println(resDoc);
			if(resDoc == null)
				return null;
			while(resDoc.next()) {
				Document doc = new PersisDocument(resDoc.getInt("id"), resDoc.getString("auteur"),resDoc.getString("nomDoc"),resDoc.getString("typeDoc"),resDoc.getInt("empruntUser"));
				
				return doc;
			}	
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION GetDocument()");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		// args est un tableau à taille variable on peut mettre ce qu'on veut
	}

}
