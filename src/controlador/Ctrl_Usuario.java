package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Categoria;
import modelo.Usuario;

public class Ctrl_Usuario {

	// metodo para iniciar sesion
	public boolean loginUser(Usuario objeto) {

		boolean respuesta = false;

		Connection cn = Conexion.conectar();
		String sql = "SELECT usuario, password from tb_usuario WHERE usuario = '" + objeto.getUsuario()
				+ "' AND password = '" + objeto.getPassword() + "' ";
		Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				respuesta = true;
			}

		} catch (SQLException e) {
			System.out.println("Error al iniciar sesion");
			JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
		}

		return respuesta;
	}

	public boolean guardar(Usuario objeto) {

		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement("insert into tb_usuario values(?,?,?,?,?,?,?)");
			consulta.setInt(1, 0);
			consulta.setString(2, objeto.getNombre());
			consulta.setString(3, objeto.getApellido());
			consulta.setString(4, objeto.getUsuario());
			consulta.setString(5, objeto.getPassword());
			consulta.setString(6, objeto.getTelefono());
			consulta.setInt(7, objeto.getEstado());

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al guardar usuario: " + e);
		}

		return respuesta;
	}

	public boolean existeUsuario(String usuario) {
		boolean respuesta = false;
		String sql = "select usuario from tb_usuario where usuario = '" + usuario + "' ";
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

			System.out.println("Error al consultar usuario: " + e);
		}

		return respuesta;
	}

	// metodo para actualizar un usuario

	public boolean actualizar(Usuario objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement(
					"update tb_usuario set nombre=?, apellido=?, usuario=?, password=?, telefono=? where idUsuario = ?");

			consulta.setString(1, objeto.getNombre());
			consulta.setString(2, objeto.getApellido());
			consulta.setString(3, objeto.getUsuario());
			consulta.setString(4, objeto.getPassword());
			consulta.setString(5, objeto.getTelefono());
			consulta.setInt(6, objeto.getIdUsuario());
			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al actualizar usuario: " + e);
		}

		return respuesta;

	}

	// metodo para eliminar un usuario

	public boolean eliminar(int idUsuario) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn
					.prepareStatement("delete from tb_usuario where idUsuario = '" + idUsuario + "'");

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al eliminar usuario: " + e);
		}

		return respuesta;

	}

}
