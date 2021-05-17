package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CConexion {
	
	Connection con;
	
	public void iniciarConexion(String db , String usuario , String password) {

		String url = "jdbc:postgresql://" + db;
	

		try {

			Class.forName("org.postgresql.Driver");
			//System.out.println(url+" - "+"usuario"+"-"+password);
			con = DriverManager.getConnection(url, usuario, password);

			

		} catch (ClassNotFoundException e) {
			System.out.println("ERROR AL CARGAR EL DRIVER");
		
		} catch (SQLException ex) {
			System.out.println("ERROR AL CONECTARSE A LA BASE DE DATOS");
		}

	}

	public Connection getConnection() {
		
		return con;
	}
	
	public void cerrarConexion() {

		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("ERROR AL CERRAR LA CONEXION");
		}

	}

}
