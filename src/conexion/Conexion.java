package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	// conexion local
	public static Connection conectar() {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_ventas","root","");
			return cn;
		}
		catch(SQLException e) {
			System.out.println("Error de conexion. " + e);
		}
		return null;
		
	}
}
