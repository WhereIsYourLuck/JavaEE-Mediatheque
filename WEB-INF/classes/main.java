import java.sql.*;

public class main {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "root");
			System.out.println("Connecté");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
