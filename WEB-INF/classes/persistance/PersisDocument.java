package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class PersisDocument implements Document{
	private int idDoc;
	private String auteur;
	private String nomDoc;
	private String typeDoc;
	private int empruntUser;
	//EmpruntUser = null dans la bdd
	//Devient un int qui est converti automatiquement à 0 quand il est null grâce à la fonction
	// getInt() dans la fonction MediathèqueData.getDocument()
	public PersisDocument(int idDoc, String auteur, String nomDoc, String typeDoc, int empruntUser) {
		this.idDoc = idDoc;
		this.auteur = auteur;
		this.nomDoc = nomDoc;
		this.typeDoc = typeDoc;
		this.empruntUser = empruntUser;
	}

	@Override
	public boolean disponible() {
		Connection conn = sqlConnection.getConnection();
		String sql = "SELECT * FROM documents WHERE empruntUser = ? AND id = ?";
		try {
			PreparedStatement stmtDoc = conn.prepareStatement(sql);
			stmtDoc.setInt(1, this.empruntUser);
			stmtDoc.setInt(2, this.idDoc);
			ResultSet resDoc = stmtDoc.executeQuery();
			while(resDoc.next()) {
				empruntUser = resDoc.getInt("empruntUser");
			}
			conn.close();
		} catch (SQLException e) {
				System.err.println("ERREUR SQL FONCTION Document.disponible()");
				e.printStackTrace();
		}
		
		if(empruntUser != 0) 
			return false;
		else
			return true;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
		Connection conn = sqlConnection.getConnection();
		synchronized(conn) { //On synchronise la connexion car c'est une ressource critique
			
			String sqlEmpruntDoc = "UPDATE documents SET empruntUser = ? WHERE id = ?";
			
			if(!this.disponible()) { //On verifie que le document n'est pas déjà emprunté
				throw new Exception("Ce document est déjà emprunté.");
			}
	        PreparedStatement stmt = conn.prepareStatement(sqlEmpruntDoc);
	        stmt.setInt(1, (int) u.data()[4]); //Sinon on récupère le String idUser
	        stmt.setInt(2, this.idDoc);
	        stmt.execute();
	   }
	}

	@Override
	public void retour() {
		Connection conn = sqlConnection.getConnection();

        String sqlRetour = "UPDATE documents SET empruntUser = NULL WHERE id= ?";
        try {
            PreparedStatement stmtDoc = conn.prepareStatement(sqlRetour);
            stmtDoc.setInt(1, idDoc);
            stmtDoc.execute();
        } catch (SQLException e) {
        	System.err.println("ERREUR SQL FONCTION Document.retour()");
            e.printStackTrace();
        }
	}
	
	public String toString() {
		String s = this.idDoc + " " + this.typeDoc + " | " + this.nomDoc + " par " + this.auteur; 
		return s;
		
	}

}
