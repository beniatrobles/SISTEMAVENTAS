package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

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
	
	public void crearUsuarioPorDefecto() {
	    try (Connection cn = Conexion.conectar();
	         PreparedStatement pst = cn.prepareStatement("SELECT COUNT(*) FROM tb_usuario");
	         ResultSet rs = pst.executeQuery()) {

	        if (rs.next() && rs.getInt(1) == 0) {
	            String sql = "INSERT INTO tb_usuario (idUsuario, nombre, apellido, usuario, password, telefono, estado) " +
	                         "VALUES (1, 'Admin', 'Principal', 'admin', 'admin123', '000000000', 1)";
	            PreparedStatement insert = cn.prepareStatement(sql);
	            insert.executeUpdate();
	            insert.close();
	            System.out.println("Usuario por defecto creado: admin / admin123");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al crear usuario por defecto: " + e);
	    }
	}
}
