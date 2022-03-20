package persistance;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class PersisUtilisateur implements Utilisateur{
	private int idUser;
	private String name;
	private String password;
	private String isBibliothecaire;
	private List<Document> documentsEmpruntes = new ArrayList<Document>();
	
	public PersisUtilisateur(int id, String name, String password, String isBibliothecaire) {
		this.idUser = id;
		this.name = name;
		this.password = password;
		this.isBibliothecaire = isBibliothecaire;
	}

	public List<Document> getDocumentsEmpruntes() {
		Connection conn = sqlConnection.getConnection();
		String sqlDocsEmpruntes = "SELECT * FROM documents WHERE empruntUser = ?";
		try {
			PreparedStatement stmtDocsEmpruntes = conn.prepareStatement(sqlDocsEmpruntes);
			stmtDocsEmpruntes.setInt(1, this.idUser);
			ResultSet resDocLibres = stmtDocsEmpruntes.executeQuery();
			while(resDocLibres.next()) {
				int idDoc = resDocLibres.getInt("id");
				String auteurDoc = resDocLibres.getString("auteur");
				String typeDoc = resDocLibres.getString("typeDoc");
				String nomDoc = resDocLibres.getString("nomDoc");
				documentsEmpruntes.add(new PersisDocument(idDoc, auteurDoc, nomDoc, typeDoc, this.idUser));
			}
			conn.close();
		} catch (SQLException e) {
			System.err.println("ERREUR SQL FONCTION getDocumentsEmpruntes()");
			e.printStackTrace();
		}
		return documentsEmpruntes;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public boolean isBibliothecaire() {
		//Pas nécessaire de faire une requête vers la BDD, le status d'un utilisateur ne change jamais
		if(Integer.parseInt(isBibliothecaire) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object[] data() {
		//Passage de tous les autres attributs utiles ici
		//MDP, tableau de livre emprunté
		//Très utile car les instances sont volatiles : On recrée le même utilisateur à chaque modification

		Object[] data = new Object[5];
		data[0] = this.name();
		data[1] = this.password;
		data[2] = this.isBibliothecaire;
		data[3] = this.getDocumentsEmpruntes();
		data[4] = this.idUser;
		
		return data;
	}

}
