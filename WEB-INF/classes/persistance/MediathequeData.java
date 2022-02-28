package persistance;

import java.sql.*;
import java.util.List;

import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
	List<Utilisateur> utilisateurs;
// Jean-Fran�ois Brette 01/01/2018
	Connection conn = null;
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		
	}

	private MediathequeData() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "root");
			this.getInformationsBD();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur connexion bdd mysql");
		}
	}
	public void getInformationsBD() {
		String reqDoc = "SELECT id, auteur, nomDoc, typeDoc, empruntUser FROM documents";
		String reqUser = "SELECT idUser, nomUser, mdp, typeUser FROM utilisateurs";
		try {
			Statement stmtDoc = conn.createStatement();
			ResultSet resDoc = stmtDoc.executeQuery(reqDoc);
			while(resDoc.next()) {
				new Document(resDoc.getInt("id"), resDoc.getString("auteur"), resDoc.getString("nomDoc"), resDoc.getString("typeDoc"), resDoc.getInt("empruntUser"));
			}
			
			Statement stmtUser = conn.createStatement();
			ResultSet resUser = stmtUser.executeQuery(reqUser);
			while(resUser.next()) {
				new Utilisateur(resUser.getInt("idUser"), resUser.getString("nomUser"), resUser.getString("typeUser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		String req = "SELECT "
		return 
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		return null;
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
	}

}
