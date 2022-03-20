package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
	public static Connection conn;
	/** 
	 * Fonction qui va nous permettre de se connecter � la bdd lors d'une requ�te puis fermer
	 * cette connection juste apr�s la requ�te
	 * @return Connection � la base de donn�es
	 */
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur connexion bdd mysql");
		}
		return conn;
	}

}
