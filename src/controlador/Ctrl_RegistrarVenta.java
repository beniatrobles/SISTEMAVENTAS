package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;



public class Ctrl_RegistrarVenta {
	
	//ultimo id de la cabecera
	public static int idCabeceraRegistrada;
	java.math.BigDecimal iDColVar;
	
	//metodo para guardar la cabecera de venta
	
	public boolean guardar(CabeceraVenta objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement("insert into tb_cabecera_venta values(?,?,?,?,?)" , 
					Statement.RETURN_GENERATED_KEYS);
			consulta.setInt(1, 0);
			consulta.setInt(2, objeto.getIdCliente());
			consulta.setDouble(3, objeto.getValorPagar());
			consulta.setString(4, objeto.getFechaVenta());
			consulta.setInt(5, objeto.getEstado());
			

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}
			
			ResultSet rs = consulta.getGeneratedKeys();
			while(rs.next()) {
				iDColVar = rs.getBigDecimal(1);
				idCabeceraRegistrada = iDColVar.intValue();
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al guardar cabecera: " + e);
		}

		return respuesta;

	}
	
	
	//metodo para guardar el detalle de venta
	public boolean guardarDetalle(DetalleVenta objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {

			PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values(?,?,?,?,?,?,?,?,?,?)");
			consulta.setInt(1, 0);
			consulta.setInt(2, idCabeceraRegistrada);
			consulta.setInt(3, objeto.getIdProducto());
			consulta.setInt(4, objeto.getCantidad());
			consulta.setDouble(5, objeto.getPrecioUnitario());
			consulta.setDouble(6, objeto.getSubtotal());
			consulta.setDouble(7, objeto.getDescuento());
			consulta.setDouble(8, objeto.getIva());
			consulta.setDouble(9, objeto.getTotalPagar());
			consulta.setInt(10, objeto.getEstado());

			if (consulta.executeUpdate() > 0) {
				respuesta = true;
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al guardar detalle venta: " + e);
		}

		return respuesta;

	}

}
