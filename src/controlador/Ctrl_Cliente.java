package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import modelo.Cliente;
import modelo.Producto;

public class Ctrl_Cliente {
	
	public boolean guardar(Cliente objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values(?,?,?,?,?,?,?)");
			consulta.setInt(1, 0);
			consulta.setString(2, objeto.getNombre());
			consulta.setString(3, objeto.getApellido());
			consulta.setString(4, objeto.getCedula());
			consulta.setString(5, objeto.getTelefono());
			consulta.setString(6, objeto.getDireccion());
			consulta.setInt(7, objeto.getEstado());

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al guardar cliente: " + e);
		}

		return respuesta;

	}
	
	// Actualizar cliente
    public boolean actualizar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement pst = cn.prepareStatement(
                "UPDATE tb_cliente SET nombre=?, apellido=?, cedula=?, telefono=?, direccion=?" +
                "WHERE idCliente=?"
            );
            
            
            pst.setString(1, objeto.getNombre());
            pst.setString(2, objeto.getApellido());
            pst.setString(3, objeto.getCedula());
            pst.setString(4, objeto.getTelefono());
            pst.setString(5, objeto.getDireccion());
            pst.setInt(6, objeto.getIdCliente());
            

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }

        return respuesta;
    }
    
	
	
	
	// Eliminar cliente
    public boolean eliminar(int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement pst = cn.prepareStatement(
                "DELETE FROM tb_cliente WHERE idCliente=?"
            );
            pst.setInt(1, idCliente);

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }

        return respuesta;
    }
	
	
	
	// metodo para ver si existe ese cliente
			public boolean existeCliente(String cliente) {
				boolean respuesta = false;
				String sql = "select nombre from tb_cliente where nombre = '" + cliente + "'";
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
					System.out.println("Error al consultar cliente: " + e);
				}

				return respuesta;

			}
	
	

}
