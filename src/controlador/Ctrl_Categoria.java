package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import conexion.Conexion;
import modelo.Categoria;

public class Ctrl_Categoria {

	// metodo para agregar una categoria

	public boolean guardar(Categoria objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?,?,?)");
			consulta.setInt(1, 0);
			consulta.setString(2, objeto.getDescripcion());
			consulta.setInt(3, objeto.getEstado());

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al guardar categoria: " + e);
		}

		return respuesta;

	}

	// metodo para ver si existe esa categoria
	public boolean existeCategoria(String categoria) {
		boolean respuesta = false;
		String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "'";
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
			System.out.println("Error al consultar categoria: " + e);
		}

		return respuesta;

	}
	
	
	// metodo para actualizar una categoria


	public boolean actualizar(Categoria objeto, int idCategoria) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement(
					"update tb_categoria set descripcion=? where idCategoria = '" + idCategoria + "'");
			
			
			consulta.setString(1, objeto.getDescripcion());

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al actualizar categoria: " + e);
		}

		return respuesta;

	}
	
	
	// metodo para eliminar una categoria


		public boolean eliminar(int idCategoria) {
			boolean respuesta = false;
			Connection cn = Conexion.conectar();
			try {

				PreparedStatement consulta = cn.prepareStatement(
						"delete from tb_categoria where idCategoria = '" + idCategoria + "'");
				
				
				

				if (consulta.executeUpdate() > 0) {
					respuesta = true;
				}

				cn.close();

			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Error al eliminar categoria: " + e);
			}

			return respuesta;

		}

}
