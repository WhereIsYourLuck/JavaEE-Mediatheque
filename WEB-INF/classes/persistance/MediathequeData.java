package persistance;

import java.sql.*;
import java.util.List;

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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "root");
			this.getInformationsBD();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur connexion bdd mysql");
		}
	}
	public void getInformationsBD() {
		String req = "SELECT id, auteur, nomDoc, typeDoc, empruntUser FROM documents";
		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(req);
			while(res.next()) {
				new Document(res.getInt("id"), res.getString("auteur"), res.getString("auteur"), res.getInt("empruntUser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
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
	}

}
