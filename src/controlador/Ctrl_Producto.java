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
	
	
	// Actualizar producto
    public boolean actualizar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement pst = cn.prepareStatement(
                "UPDATE tb_producto SET nombre=?, cantidad=?, precio=?, descripcion=?, porcentajeIva=?, idCategoria=? " +
                "WHERE idProducto=?"
            );

            pst.setString(1, objeto.getNombre());
            pst.setInt(2, objeto.getCantidad());
            pst.setDouble(3, objeto.getPrecio());
            pst.setString(4, objeto.getDescripcion());
            pst.setInt(5, objeto.getPorcentajeIva());
            pst.setInt(6, objeto.getIdCategoria());
            pst.setInt(7, objeto.getIdProducto());

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }

        return respuesta;
    }
    
    
    // Eliminar producto
    public boolean eliminar(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement pst = cn.prepareStatement(
                "DELETE FROM tb_producto WHERE idProducto=?"
            );
            pst.setInt(1, idProducto);

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
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
		
		
		//metodo para actualizar el stock del producto
		
		public boolean actualizarStock(Producto producto, int idProducto) {
	        boolean respuesta = false;
	        Connection cn = Conexion.conectar();

	        try {
	            PreparedStatement pst = cn.prepareStatement(
	                "update tb_producto set cantidad=? where idProducto = '"+idProducto+"'"
	            );
	            pst.setInt(1, producto.getCantidad());

	            if (pst.executeUpdate() > 0) {
	                respuesta = true;
	            }

	            cn.close();
	        } catch (SQLException e) {
	            System.out.println("Error al actualizar el stock del producto: " + e);
	        }

	        return respuesta;
	    }
		

}
