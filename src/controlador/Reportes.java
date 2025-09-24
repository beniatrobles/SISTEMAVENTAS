package controlador;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexion.Conexion;

public class Reportes {
	
	//metodo para crear reportes de los clientes registrados en el sistema
	
	public void ReportesClientes(){
		
		Document documento = new Document();
		try {
			String ruta = System.getProperty("user.home");
			PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Cliente.pdf"));
			documento.open();
			Image header = Image.getInstance("src/img/header1.jpg");
			header.scaleToFit(650,1000);
			header.setAlignment(Chunk.ALIGN_CENTER);
			documento.add(header);
			
			//Formato al texto
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafo.setFont(FontFactory.getFont("tahoma" ,18,Font.BOLD,BaseColor.GRAY));
			parrafo.add("Reporte de Clientes \n\n");
			
			
			documento.add(parrafo);
			
			PdfPTable tabla = new PdfPTable(5);
			tabla.addCell("Codigo");
			tabla.addCell("Nombre");
			tabla.addCell("Cedula");
			tabla.addCell("Telefono");
			tabla.addCell("Direccion");
			
			try {
				
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement("select idCliente, apellido ,cedula,telefono,direccion from tb_cliente");
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					do {
						tabla.addCell(rs.getString(1));
						tabla.addCell(rs.getString(2));
						tabla.addCell(rs.getString(3));
						tabla.addCell(rs.getString(4));
						tabla.addCell(rs.getString(5));
					}while(rs.next());
					documento.add(tabla);
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Error en; " +e);
			}
			
			
			
			
			
		} catch (DocumentException | IOException e) {
			// TODO: handle exception
			
			System.out.println("Error en: " + e);
		}
		
		documento.close();
		JOptionPane.showMessageDialog(null, "Reporte creado");
		
	}
	
	
	public void ReportesCategorias() {
	    Document documento = new Document();
	    try {
	        String ruta = System.getProperty("user.home");
	        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Categorias.pdf"));

	        documento.open();

	        // Imagen de encabezado
	        Image header = Image.getInstance("src/img/header1.jpg");
	        header.scaleToFit(650, 1000);
	        header.setAlignment(Chunk.ALIGN_CENTER);
	        documento.add(header);

	        // Título
	        Paragraph parrafo = new Paragraph();
	        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
	        parrafo.setFont(FontFactory.getFont("tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
	        parrafo.add("Reporte de Categorías \n\n");
	        documento.add(parrafo);

	        // Tabla con columnas
	        PdfPTable tabla = new PdfPTable(3); // ajusta según tu tabla
	        tabla.addCell("Código");
	        tabla.addCell("Descripción");
	        tabla.addCell("Estado");

	        try (Connection cn = Conexion.conectar();
	             PreparedStatement pst = cn.prepareStatement("SELECT idCategoria, descripcion, estado FROM tb_categoria");
	             ResultSet rs = pst.executeQuery()) {

	            while (rs.next()) {
	                tabla.addCell(rs.getString("idCategoria"));
	                tabla.addCell(rs.getString("descripcion"));

	                // Mostrar "Activo/Inactivo" en lugar de 1/0
	                String estado = rs.getString("estado");
	                if ("1".equals(estado)) {
	                    tabla.addCell("Activo");
	                } else {
	                    tabla.addCell("Inactivo");
	                }
	            }

	            documento.add(tabla);

	        } catch (SQLException e) {
	            System.out.println("Error al generar reporte de categorías: " + e);
	        }

	        documento.close();
	        JOptionPane.showMessageDialog(null, "Reporte de categorías creado en el escritorio");

	    } catch (DocumentException | IOException e) {
	        System.out.println("Error en: " + e);
	    }
	}
	
	
	public void ReportesProductos() {
	    Document documento = new Document();
	    try {
	        String ruta = System.getProperty("user.home");
	        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Productos.pdf"));

	        documento.open();

	        // Imagen de encabezado
	        Image header = Image.getInstance("src/img/header1.jpg");
	        header.scaleToFit(650, 1000);
	        header.setAlignment(Chunk.ALIGN_CENTER);
	        documento.add(header);

	        // Título
	        Paragraph parrafo = new Paragraph();
	        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
	        parrafo.setFont(FontFactory.getFont("tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
	        parrafo.add("Reporte de Productos \n\n");
	        documento.add(parrafo);

	        // Tabla con columnas
	        PdfPTable tabla = new PdfPTable(6); // ajusta según los campos que quieras mostrar
	        tabla.addCell("Código");
	        tabla.addCell("Nombre");
	        tabla.addCell("Cantidad");
	        tabla.addCell("Precio");
	        tabla.addCell("Descripción");
	        tabla.addCell("Estado");

	        try (Connection cn = Conexion.conectar();
	             PreparedStatement pst = cn.prepareStatement(
	                 "SELECT idProducto, nombre, cantidad, precio, descripcion, estado FROM tb_producto");
	             ResultSet rs = pst.executeQuery()) {

	            while (rs.next()) {
	                tabla.addCell(rs.getString("idProducto"));
	                tabla.addCell(rs.getString("nombre"));
	                tabla.addCell(rs.getString("cantidad"));
	                tabla.addCell(rs.getString("precio"));
	                tabla.addCell(rs.getString("descripcion"));

	                // Estado: mostrar Activo/Inactivo en vez de 1/0
	                String estado = rs.getString("estado");
	                if ("1".equals(estado)) {
	                    tabla.addCell("Activo");
	                } else {
	                    tabla.addCell("Inactivo");
	                }
	            }

	            documento.add(tabla);

	        } catch (SQLException e) {
	            System.out.println("Error al generar reporte de productos: " + e);
	        }

	        documento.close();
	        JOptionPane.showMessageDialog(null, "Reporte de productos creado en el escritorio");

	    } catch (DocumentException | IOException e) {
	        System.out.println("Error en: " + e);
	    }
	}
	
	
	public void ReportesVentas() {
	    Document documento = new Document();
	    try {
	        String ruta = System.getProperty("user.home");
	        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Ventas.pdf"));

	        documento.open();

	        // Imagen de encabezado
	        Image header = Image.getInstance("src/img/header1.jpg");
	        header.scaleToFit(650, 1000);
	        header.setAlignment(Chunk.ALIGN_CENTER);
	        documento.add(header);

	        // Título
	        Paragraph parrafo = new Paragraph();
	        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
	        parrafo.setFont(FontFactory.getFont("tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
	        parrafo.add("Reporte de Ventas \n\n");
	        documento.add(parrafo);

	        // Tabla con columnas
	        PdfPTable tabla = new PdfPTable(5); // 5 columnas: id, cliente, total, fecha, estado
	        tabla.addCell("Código Venta");
	        tabla.addCell("Cliente");
	        tabla.addCell("Total Pagar");
	        tabla.addCell("Fecha");
	        tabla.addCell("Estado");

	        try (Connection cn = Conexion.conectar();
	             PreparedStatement pst = cn.prepareStatement(
	                 "SELECT cv.idCabeceraVenta, c.apellido, cv.valorPagar, cv.fechaVenta, cv.estado " +
	                 "FROM tb_cabecera_venta cv " +
	                 "INNER JOIN tb_cliente c ON cv.idCliente = c.idCliente")) {
	            
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                tabla.addCell(rs.getString("idCabeceraVenta"));
	                tabla.addCell(rs.getString("apellido"));
	                tabla.addCell(rs.getString("valorPagar"));
	                tabla.addCell(rs.getString("fechaVenta"));

	                // Estado 1 = Activo, 0 = Inactivo
	                String estado = rs.getString("estado");
	                if ("1".equals(estado)) {
	                    tabla.addCell("Activo");
	                } else {
	                    tabla.addCell("Inactivo");
	                }
	            }

	            documento.add(tabla);

	        } catch (SQLException e) {
	            System.out.println("Error al generar reporte de ventas: " + e);
	        }

	        documento.close();
	        JOptionPane.showMessageDialog(null, "Reporte de ventas creado en el escritorio");

	    } catch (DocumentException | IOException e) {
	        System.out.println("Error en: " + e);
	    }
	}


}
