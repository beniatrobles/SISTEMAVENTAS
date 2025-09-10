package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import controlador.Ctrl_Categoria;
import controlador.Ctrl_Producto;
import modelo.Categoria;
import modelo.Producto;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class InterGestionarProducto extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int idProducto;
	int obtenerIdCategoriaCombo = 0;
	String descripcionCategoria = "";
	double precio = 0.0;
	int porcentajeIva = 0;
	double IVA = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterGestionarProducto frame = new InterGestionarProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTable jTable_Productos;
	private JTextField txt_nombre;
	private DefaultTableModel model;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTextField txt_cantidad;
	private JTextField txt_precio;
	private JTextField txt_descripcion;
	private JComboBox<String> comboBox_iva;
	private JComboBox<String> comboBox_categoria;

	/**
	 * Create the frame.
	 */
	public InterGestionarProducto() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Administrar Productos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(345, 11, 350, 22);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 61, 730, 270);
		getContentPane().add(panel);
		panel.setLayout(null);

		jTable_Productos = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Productos);
		scroll.setBounds(10, 11, panel.getWidth() - 20, panel.getHeight() - 22);
		panel.add(scroll);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(752, 61, 122, 369);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setBackground(new Color(51, 204, 0));
		btnActualizar.setBounds(10, 11, 100, 23);
		btnActualizar.addActionListener(this);
		panel_1.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setBackground(new Color(255, 51, 51));
		btnEliminar.setBounds(10, 45, 100, 23);
		btnEliminar.addActionListener(this);
		panel_1.add(btnEliminar);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 330, 730, 100);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 100, 14);
		panel_2.add(lblNewLabel_1);

		txt_nombre = new JTextField();
		txt_nombre.setBounds(77, 10, 170, 20);
		panel_2.add(txt_nombre);
		txt_nombre.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Cantidad:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 41, 100, 14);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Precio:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 75, 100, 14);
		panel_2.add(lblNewLabel_1_2);

		txt_cantidad = new JTextField();
		txt_cantidad.setColumns(10);
		txt_cantidad.setBounds(77, 40, 170, 20);
		panel_2.add(txt_cantidad);

		txt_precio = new JTextField();
		txt_precio.setColumns(10);
		txt_precio.setBounds(77, 74, 170, 20);
		panel_2.add(txt_precio);

		JLabel lblNewLabel_1_2_1 = new JLabel("Descripcion:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(351, 14, 100, 14);
		panel_2.add(lblNewLabel_1_2_1);

		txt_descripcion = new JTextField();
		txt_descripcion.setColumns(10);
		txt_descripcion.setBounds(445, 11, 170, 20);
		panel_2.add(txt_descripcion);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("IVA:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(351, 44, 100, 14);
		panel_2.add(lblNewLabel_1_2_1_1);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("Categoria:");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_2.setBounds(351, 76, 100, 14);
		panel_2.add(lblNewLabel_1_2_1_2);

		comboBox_iva = new JComboBox<String>();
		comboBox_iva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_iva.setBounds(445, 40, 170, 22);
		panel_2.add(comboBox_iva);

		comboBox_categoria = new JComboBox<String>();
		comboBox_categoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_categoria.setBounds(445, 74, 170, 22);
		panel_2.add(comboBox_categoria);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setBounds(0, 0, 884, 470);
		getContentPane().add(jLabel_wallpaper);

		this.setSize(new Dimension(900, 500));

		String titulo = "Gestionar Producto";
		this.setTitle(titulo);

		// insertar omagen en el jlabel

		ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
		jLabel_wallpaper.setIcon(icono);
		this.repaint();

		CargarTablaProductos();
		CargarComboCategorias();
		CargarComboIVA();

	}

	private void CargarComboIVA() {
		comboBox_iva.removeAllItems();
		comboBox_iva.addItem("Seleccione IVA:");
		comboBox_iva.addItem("No grava IVA");
		comboBox_iva.addItem("4%");
		comboBox_iva.addItem("10%");
		comboBox_iva.addItem("21%");
	}

	/*
	 * metodo para cargar todas las categorias
	 * 
	 */

	private void CargarComboCategorias() {

		Connection cn = Conexion.conectar();
		String sql = "select * from tb_categoria";
		Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			comboBox_categoria.removeAllItems();
			comboBox_categoria.addItem("Seleccione categoria:");

			while (rs.next()) {
				comboBox_categoria.addItem(rs.getString("descripcion"));
			}

			cn.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cargar las categorias. " + e);
		}
	}
	
	
	
	private int obtenerIdCategoriaPorDescripcion(String descripcion) {
	    int idCategoria = 0; // por defecto 0 si no encuentra
	    Connection cn = Conexion.conectar();
	    String sql = "SELECT idCategoria FROM tb_categoria WHERE descripcion = ?";
	    
	    try {
	        PreparedStatement pst = cn.prepareStatement(sql);
	        pst.setString(1, descripcion);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            idCategoria = rs.getInt("idCategoria");
	        }
	        
	        cn.close();
	    } catch (Exception e) {
	        System.out.println("Error al obtener idCategoria: " + e.getMessage());
	    }
	    
	    return idCategoria;
	}

	/*
	 * metodo para mostrar todas las categorias
	 * 
	 */

	private void CargarTablaProductos() {
		try {

			Connection con = Conexion.conectar();

			// Crear el modelo de la tabla
			model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Nombre");
			model.addColumn("Cantidad");
			model.addColumn("Precio");
			model.addColumn("Descripcion");
			model.addColumn("Porcentaje IVA");
			model.addColumn("Total con IVA");
			model.addColumn("Categoria");
			model.addColumn("Estado");

			String sql = "SELECT p.*,c.descripcion as descripcion_categoria FROM tb_producto as p , tb_categoria as c where p.idCategoria = c.idCategoria";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// Llenar el modelo con los datos
			while (rs.next()) {
				int id = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				int cantidad = rs.getInt("cantidad");
				double precio = rs.getDouble("precio");
				String descripcion = rs.getString("descripcion");
				int porcentajeIva = rs.getInt("porcentajeIva");
				String idCategoria = rs.getString("descripcion_categoria");
				int estado = rs.getInt("estado");

				double porcentaje = precio * (porcentajeIva / 100.0);
//				System.out.println(porcentaje);
				double total = precio + porcentaje;

				model.addRow(new Object[] { id, nombre, cantidad, precio, descripcion,
						porcentajeIva + "% - " + porcentaje, total, idCategoria, estado });
			}

			// Asignar el modelo a la tabla
			jTable_Productos.setModel(model);

			// Cerrar conexión
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al llenar la tabla categorias: " + e);
		}

		jTable_Productos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = jTable_Productos.rowAtPoint(e.getPoint());
				int columna_point = 0;

				if (fila_point >= -1) {
					idProducto = (int) model.getValueAt(fila_point, columna_point);
					EnviarDatosProductoSeleccionada(idProducto);
				}
			}

		});
	}

	private void EnviarDatosProductoSeleccionada(int idProducto) {
		// TODO Auto-generated method stub
		try {

			Connection con = Conexion.conectar();
			PreparedStatement pst = con.prepareStatement(
				    "SELECT p.*, c.descripcion AS descripcion_categoria " +
				    "FROM tb_producto p " +
				    "INNER JOIN tb_categoria c ON p.idCategoria = c.idCategoria " +
				    "WHERE p.idProducto = ?"
				);
				pst.setInt(1, idProducto);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				txt_nombre.setText(rs.getString("nombre"));
				txt_cantidad.setText(String.valueOf(rs.getInt("cantidad")));
				txt_precio.setText(String.valueOf(rs.getDouble("precio")));
				txt_descripcion.setText(rs.getString("descripcion"));
				int porcentajeIva = rs.getInt("porcentajeIva");
				if (porcentajeIva == 0) {
					comboBox_iva.setSelectedItem("No grava IVA");
				} else {
					comboBox_iva.setSelectedItem(porcentajeIva + "%");
				}

				String categoriaDescripcion = rs.getString("descripcion_categoria");
				System.out.println(categoriaDescripcion);
				comboBox_categoria.setSelectedItem(categoriaDescripcion);
			}
			con.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al seleccionar la categoria: " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		Ctrl_Producto ctrlProducto = new Ctrl_Producto();

		 if (e.getSource() == btnActualizar) {

		        if (idProducto == 0) {
		            JOptionPane.showMessageDialog(null, "Selecciona un producto primero");
		        } else {
		            try {
		                Producto producto = new Producto();
		                producto.setIdProducto(idProducto);
		                producto.setNombre(txt_nombre.getText().trim());
		                producto.setCantidad(Integer.parseInt(txt_cantidad.getText().trim()));
		                producto.setPrecio(Double.parseDouble(txt_precio.getText().trim()));
		                producto.setDescripcion(txt_descripcion.getText().trim());

		                // IVA
		                String ivaSeleccionado = comboBox_iva.getSelectedItem().toString();
		                int porcentajeIva = 0;
		                if (!ivaSeleccionado.equals("No grava IVA") && !ivaSeleccionado.equals("Seleccione IVA:")) {
		                    porcentajeIva = Integer.parseInt(ivaSeleccionado.replace("%", ""));
		                }
		                producto.setPorcentajeIva(porcentajeIva);

		                // Categoría
		                int idCategoria = obtenerIdCategoriaPorDescripcion(comboBox_categoria.getSelectedItem().toString());
		                producto.setIdCategoria(idCategoria);

		                // Actualizar
		                if (ctrlProducto.actualizar(producto)) {
		                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
		                    CargarTablaProductos();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error al actualizar producto");
		                }

		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + ex.getMessage());
		            }
		        }
		    }

		 if (e.getSource() == btnEliminar) {

		        if (idProducto == 0) {
		            JOptionPane.showMessageDialog(null, "Selecciona un producto primero");
		        } else {
		            try {
		                // Eliminar
		                if (ctrlProducto.eliminar(idProducto)) {
		                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
		                    CargarTablaProductos();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error al eliminar producto");
		                }
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + ex.getMessage());
		            }
		        }
		    }
	}
}
