package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexion.Conexion;
import vista.InterFacturacion;

public class VentaPDF {

	private String nombreCliente;
	private String cedulaCliente;
	private String telefonoCliente;
	private String direccionCliente;

	private String fechaActual = "";
	private String nombreArchivoPDFVenta = "";

	// metodo para obtener datos de los clientes

	public void DatosCliente(int idCliente) {

		Connection cn = Conexion.conectar();
		String sql = "select * from tb_cliente where idCliente = '" + idCliente + "'";
		Statement st;
		try {

			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				nombreCliente = rs.getString("apellido");
				cedulaCliente = rs.getString("cedula");
				telefonoCliente = rs.getString("nombre");
				direccionCliente = rs.getString("direccion");

			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al obtener datos cliente: " + e);
		}
	}

	// metodo para generar factura

	public void generarFacturaPDF() {
		try {

			// Cargar la fecha actual
			Date date = new Date();
			fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

			// cambiar el formato de la fecha de / a _ para el nombre del archivo

			String fechaNueva = "";

			for (int i = 0; i < fechaActual.length(); i++) {

				if (fechaActual.charAt(i) == '/') {
					fechaNueva = fechaActual.replace("/", "_");
				}

			}

			nombreArchivoPDFVenta = "Venta_ " + nombreCliente + "_" + fechaNueva + ".pdf";

			FileOutputStream archivo;
			File file = new File("src/pdf/" + nombreArchivoPDFVenta);

			archivo = new FileOutputStream(file);

			Document doc = new Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();

			Image img = Image.getInstance("src/img/ventas.png");
			Paragraph fecha = new Paragraph();
			Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
			fecha.add(Chunk.NEWLINE); // agregar una nueva linea
			fecha.add("Factura: 001 " + "\nFecha: " + fechaActual + "\n\n");

			PdfPTable Encababezado = new PdfPTable(4);
			Encababezado.setWidthPercentage(100);
			Encababezado.getDefaultCell().setBorder(0); // quitar el borde de la tabla
			// tamaño de la celda
			float[] ColumnaEncabezado = new float[] { 20f, 30f, 70f, 40f };
			Encababezado.setWidths(ColumnaEncabezado);
			Encababezado.setHorizontalAlignment(Element.ALIGN_LEFT);
			// agregar celdas
			Encababezado.addCell(img);

			String ruc = "0987654321";
			String nombre = "Beñat Robles";
			String telefono = "66789456";
			String direccion = "C / Jesucristo";

			Encababezado.addCell(""); // celda vacia
			Encababezado.addCell("CIF: " + ruc + "\n" + "Nombre: " + nombre + "\n" + "Telefono: " + telefono + "\n"
					+ "Direccion: " + direccion);
			Encababezado.addCell(fecha);

			doc.add(Encababezado);

			// CUERPO

			Paragraph cliente = new Paragraph();
			cliente.add(Chunk.NEWLINE);
			cliente.add("Datos del Cliente: " + "\n\n");
			doc.add(cliente);

			// Datos del cliente

			PdfPTable tablaCliente = new PdfPTable(4);
			tablaCliente.setWidthPercentage(100);
			tablaCliente.getDefaultCell().setBorder(0);
			// tamaño de las celdas
			float[] ColumnaCliente = new float[] { 25f, 45f, 30f, 40f };
			tablaCliente.setWidths(ColumnaCliente);
			tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cliente1 = new PdfPCell(new Phrase("Cedula: ", negrita));
			PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre: ", negrita));
			PdfPCell cliente3 = new PdfPCell(new Phrase("Telefono: ", negrita));
			PdfPCell cliente4 = new PdfPCell(new Phrase("Direccion: ", negrita));

			cliente1.setBorder(0);
			cliente2.setBorder(0);
			cliente3.setBorder(0);
			cliente4.setBorder(0);

			// agregar celda a la tabla
			tablaCliente.addCell(cliente1);
			tablaCliente.addCell(cliente2);
			tablaCliente.addCell(cliente3);
			tablaCliente.addCell(cliente4);
			tablaCliente.addCell(cedulaCliente);
			tablaCliente.addCell(nombreCliente);
			tablaCliente.addCell(telefonoCliente);
			tablaCliente.addCell(direccionCliente);

			doc.add(tablaCliente);

			// ESPACION EN BLANCO

			Paragraph espacio = new Paragraph();
			espacio.add(Chunk.NEWLINE);
			espacio.add("");
			espacio.setAlignment(Element.ALIGN_CENTER);
			doc.add(espacio);

			/// Informacion de los producto de la venta

			PdfPTable tablaProducto = new PdfPTable(4);
			tablaProducto.setWidthPercentage(100);
			tablaProducto.getDefaultCell().setBorder(0);
			float[] ColumnaProducto = new float[] { 15f, 50f, 15f, 20f };
			tablaProducto.setWidths(ColumnaProducto);
			tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
			PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: ", negrita));
			PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: ", negrita));
			PdfPCell producto4 = new PdfPCell(new Phrase("Precio Total: ", negrita));

			producto1.setBorder(0);
			producto2.setBorder(0);
			producto3.setBorder(0);
			producto4.setBorder(0);

			// agregar color al encabezado

			producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);

			// agregar celda a la tabla
			tablaProducto.addCell(producto1);
			tablaProducto.addCell(producto2);
			tablaProducto.addCell(producto3);
			tablaProducto.addCell(producto4);

			for (int i = 0; i < InterFacturacion.jTable_Productos.getRowCount(); i++) {

				String producto = InterFacturacion.jTable_Productos.getValueAt(i, 1).toString();
				String cantidad = InterFacturacion.jTable_Productos.getValueAt(i, 2).toString();
				String precio = InterFacturacion.jTable_Productos.getValueAt(i, 3).toString();
				String total = InterFacturacion.jTable_Productos.getValueAt(i, 7).toString();

				tablaProducto.addCell(cantidad);
				tablaProducto.addCell(producto);
				tablaProducto.addCell(precio);
				tablaProducto.addCell(total);

			}

			doc.add(tablaProducto);

			// total a pagar
			Paragraph info = new Paragraph();
			info.add(Chunk.NEWLINE);
			info.add("Total a pagar: " + InterFacturacion.txt_totalapagar);
			info.setAlignment(Element.ALIGN_RIGHT);
			
			

			//ESPACION EN BLANCO
			Paragraph espacio1 = new Paragraph();
			espacio1.add(Chunk.NEWLINE);
			espacio1.add("");
			espacio1.setAlignment(Element.ALIGN_CENTER);
			doc.add(espacio1);
			
			//Firma
			
			Paragraph firma = new Paragraph();
			firma.add(Chunk.NEWLINE);
			firma.add("_________________________");
			firma.add(Chunk.NEWLINE);
			firma.add("¡Gracias por su compra!");
			firma.setAlignment(Element.ALIGN_CENTER);
			doc.add(firma);
			
			// cerrar documento
			doc.close();
			archivo.close();
			
			//abrir el documento al ser generado
			Desktop.getDesktop().open(file);

		} catch (DocumentException | IOException e) {
			// TODO: handle exception
			System.out.println("Error:" + e);
		}

	}

}
