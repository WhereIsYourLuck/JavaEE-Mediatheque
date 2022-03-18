package persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jdk.internal.misc.FileSystemOption;
import mediatek2022.*;


// classe mono-instance  dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
	Connection conn = null;
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
		try {
			//Connexion � la bdd MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur connexion bdd mysql");
		}
	}

	// renvoie la liste de tous les documents disponibles de la m�diath�que
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

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String sqlUser = "SELECT idUser, nomUser, mdp, typeUser FROM utilisateurs WHERE nomUser =" + "'" + login + "'"+ " AND mdp =" + "'" + password + "'";
		String listDoc = "SELECT * from documents d, utilisateurs u WHERE nomUser =" + "'" + login + "' AND d.empruntUser = u.idUser";
		//Expliqu� avec Brette
		//Avec notre m�thode de bdd n�cessaire de cr�er les doc � chaque getUsER pour les remettre dans object[] data
		//Dans la variable session on devra ranger login et mdp pour pouvoir faire appel � la m�thode ou les mettre dans la fonction data
		//
		try {
			//Creation de l'objet utilisateur
			Statement stmtUser = conn.createStatement();
			ResultSet resUser = stmtUser.executeQuery(sqlUser);
			
			//Creation de sa liste de documents emprunt�s
			Statement stmtDoc = conn.createStatement();
			ResultSet resDoc = stmtDoc.executeQuery(listDoc);
			if(resUser == null)
				return null;
			while(resUser.next()) {
				Utilisateur user = new PersisUtilisateur(resUser.getInt("idUser"),resUser.getString("nomUser"), resUser.getString("mdp"), resUser.getString("typeUser"));
				while(resDoc.next()) {
					//Ajouter dans data jsp comment faire
					new PersisDocument(resDoc.getInt("d.id"), resDoc.getString("d.auteur"),resDoc.getString("d.nomDoc"),resDoc.getString("d.typeDoc"),resDoc.getInt("d.empruntUser"));
					System.out.println(user.data());
				}
				return user;
			}	
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION GETUSER()");
			e.printStackTrace();
		}
		return null;
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String sqlDoc = "SELECT * FROM documents WHERE id='" + numDocument + "'";
		try {
			Statement stmtDoc = conn.createStatement();
			ResultSet resDoc = stmtDoc.executeQuery(sqlDoc);
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
		// args est un tableau � taille variable on peut mettre ce qu'on veut
	}

}
