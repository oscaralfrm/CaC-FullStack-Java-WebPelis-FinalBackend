package proyectoFinalCaC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public String driver = "com.mysql.cj.jdbc.Driver";
	
	public Connection getConexion() throws ClassNotFoundException {
		Connection conexion = null;
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrowebpelis","root","root");
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}	
		return conexion;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		Conexion con = new Conexion();
		conexion = con.getConexion();
		
		if (conexion != null) {
			System.out.println("Conexión establecida con éxito.");
		} else {
			System.out.println("Error al conectar a la base de datos.");
		}
	}
}

