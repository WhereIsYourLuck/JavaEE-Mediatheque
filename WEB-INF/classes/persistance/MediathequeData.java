package persistance;

import java.sql.*;

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
		//Doc pas emprunté 
		return null;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String sqlUser = "SELECT idUser, nomUser, mdp, typeUser FROM utilisateurs WHERE nomUser =" + "'" + login + "'"+ " AND mdp =" + "'" + password + "'";
		//Expliqué avec Brette
		//Avec notre méthode de bdd nécessaire de créer les doc à chaque getUsER pour les remettre dans object[] data
		//Dans la variable session on devra ranger login et mdp pour pouvoir faire appel à la méthode ou les mettre dans la fonction data
		//
		try {
			Statement stmtUser = conn.createStatement();
			ResultSet resUser = stmtUser.executeQuery(sqlUser);
			if(resUser == null)
				return null;
			while(resUser.next()) {
				Utilisateur user = new PersisUtilisateur(resUser.getString("nomUser"), resUser.getString("mdp"), resUser.getString("typeUser"));
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
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		// args est un tableau à taille variable on peut mettre ce qu'on veut pas
	}

}
