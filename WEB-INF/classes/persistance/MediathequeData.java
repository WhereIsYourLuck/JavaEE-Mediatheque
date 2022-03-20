package persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mediatek2022.*;


// classe mono-instance  dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
		
	}

	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		Connection conn = sqlConnection.getConnection();
		List<Document> tousDocsDispo = new ArrayList<Document>();
		String sqlDocsLibres = "SELECT id, auteur, nomDoc, typeDoc FROM documents WHERE empruntUser IS NULL";
		try {
			PreparedStatement stmtDocsLibres = conn.prepareStatement(sqlDocsLibres);
			ResultSet resDocLibres = stmtDocsLibres.executeQuery();
			while(resDocLibres.next()) {
				int idDoc = resDocLibres.getInt("id");
				String auteurDoc = resDocLibres.getString("auteur");
				String typeDoc = resDocLibres.getString("typeDoc");
				String nomDoc = resDocLibres.getString("nomDoc");
				tousDocsDispo.add(new PersisDocument(idDoc, auteurDoc, nomDoc, typeDoc, 0));
			}
			conn.close();
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION GetDocument()");
			e.printStackTrace();
		}
		return tousDocsDispo;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		Connection conn = sqlConnection.getConnection();
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
			conn.close();
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
		Connection conn = sqlConnection.getConnection();
		String sqlDoc = "SELECT * FROM documents WHERE id= ?";
		try {
			PreparedStatement stmtDoc = conn.prepareStatement(sqlDoc);
			stmtDoc.setInt(1, numDocument);
			ResultSet resDoc = stmtDoc.executeQuery();
			if(resDoc == null) {
				conn.close();
				return null;
			}	
			while(resDoc.next()) {
				Document doc = new PersisDocument(resDoc.getInt("id"), resDoc.getString("auteur"),resDoc.getString("nomDoc"),resDoc.getString("typeDoc"),resDoc.getInt("empruntUser"));
				conn.close();
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
		//PersisDocument(int idDoc, String auteur, String nomDoc, String typeDoc, int empruntUser)
		Connection conn = sqlConnection.getConnection();
		int typeDoc = type;
		String typeDocString = "";
		String nomDoc = (String) args[0];
		String auteurDoc = (String) args[1];
		String sqlDoc = "INSERT INTO documents(auteur,nomDoc, typeDoc, empruntUser) VALUES(?, ?, ?, NULL)";
		switch (typeDoc) {
        case 0:
        	typeDocString = "CD";
            break;
        case 1:
        	typeDocString = "DVD";
            break;
        default:
        	typeDocString = "Livre";
    }
	try {
		PreparedStatement stmtDocAjout = conn.prepareStatement(sqlDoc);
		stmtDocAjout.setString(1, auteurDoc);
		stmtDocAjout.setString(2, nomDoc);
		stmtDocAjout.setString(3, typeDocString);
		stmtDocAjout.execute(); //Pas besoin de executeQuery car resulset pas nécessaire
		conn.close();
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION ajoutDocument()");
			e.printStackTrace();
		}		
	}

}
