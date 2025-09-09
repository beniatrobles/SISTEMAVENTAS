package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import modelo.Producto;

public class Ctrl_Producto {
	
	public boolean guardar(Producto objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement("insert into tb_producto values(?,?,?,?,?,?,?,?)");
			consulta.setInt(1, 0);
			consulta.setString(2, objeto.getNombre());
			consulta.setInt(3, objeto.getCantidad());
			consulta.setDouble(4, objeto.getPrecio());
			consulta.setString(5, objeto.getDescripcion());
			consulta.setInt(6, objeto.getPorcentajeIva());
			consulta.setInt(7, objeto.getIdCategoria());
			consulta.setInt(8, objeto.getEstado());

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al guardar producto: " + e);
		}

		return respuesta;

	}
	
	
	
	// metodo para ver si existe ese producto
		public boolean existeProducto(String producto) {
			boolean respuesta = false;
			String sql = "select nombre from tb_producto where nombre = '" + producto + "'";
			Statement st;

			try {

				Connection cn = Conexion.conectar();
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					respuesta = true;
				}

			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Error al consultar producto: " + e);
			}

			return respuesta;

		}

}
