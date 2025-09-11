package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

import conexion.Conexion;
import controlador.Ctrl_Producto;
import modelo.Producto;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class InterProducto extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterProducto frame = new InterProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	int obtenerIdCategoriaCombo = 0;
	private JTextField txt_nombreProducto;
	private JTextField txt_cantidadProducto;
	private JTextField txt_precioProducto;
	private JTextField txt_descripcionProducto;
	private JButton btnGuardar;
	private JComboBox<String> comboBox_categoria;
	private JComboBox<String> comboBox_iva;

	/**
	 * Create the frame.
	 */
	public InterProducto() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nuevo Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(115, 23, 172, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(27, 62, 71, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Cantidad:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(27, 97, 71, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Precio:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(27, 130, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Descripcion:");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(27, 162, 90, 25);
		getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("IVA:");
		lblNewLabel_1_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1.setBounds(27, 198, 90, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_1_1_2_2 = new JLabel("Categorias:");
		lblNewLabel_1_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_2.setBounds(27, 223, 90, 28);
		getContentPane().add(lblNewLabel_1_1_2_2);

		txt_nombreProducto = new JTextField();
		txt_nombreProducto.setBounds(140, 61, 211, 20);
		getContentPane().add(txt_nombreProducto);
		txt_nombreProducto.setColumns(10);

		txt_cantidadProducto = new JTextField();
		txt_cantidadProducto.setColumns(10);		txt_cantidadProducto.setBounds(140, 92, 211, 20);
		getContentPane().add(txt_cantidadProducto);

		txt_precioProducto = new JTextField();
		txt_precioProducto.setColumns(10);
		txt_precioProducto.setBounds(140, 129, 211, 20);
		getContentPane().add(txt_precioProducto);

		txt_descripcionProducto = new JTextField();
		txt_descripcionProducto.setColumns(10);
		txt_descripcionProducto.setBounds(140, 166, 211, 20);
		getContentPane().add(txt_descripcionProducto);

		comboBox_iva = new JComboBox<String>();
		comboBox_iva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_iva.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Seleccione iva:", "No grava iva", "4%", "10%", "21%" }));
		comboBox_iva.setBounds(140, 196, 211, 22);
		getContentPane().add(comboBox_iva);

		comboBox_categoria = new JComboBox<String>();
		comboBox_categoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_categoria.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione categoria:" }));
		comboBox_categoria.setBounds(140, 228, 211, 22);
		getContentPane().add(comboBox_categoria);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBackground(new Color(0, 204, 204));
		btnGuardar.setBounds(140, 273, 104, 23);
		btnGuardar.addActionListener(this);
		getContentPane().add(btnGuardar);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setIcon(new ImageIcon(InterCategoria.class.getResource("/img/fondo3.jpg")));
		jLabel_wallpaper.setBounds(0, 0, 400, 350);
		getContentPane().add(jLabel_wallpaper);

		this.setSize(new Dimension(400, 350));
		String titulo = "Nuevo Producto";
		this.setTitle(titulo);

		this.CargarComboCategorias();

	}

	// metodo para cargar categorias en el combobox
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

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cargar categorias " + e);
		}
	}

	// metodo para obtener el id de la categoria

	private int IdCategoria() {
		String sql = "select * from tb_categoria where descripcion = '" + this.comboBox_categoria.getSelectedItem()
				+ "'";

		Statement st;

		try {

			Connection cn = Conexion.conectar();
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				obtenerIdCategoriaCombo = rs.getInt("idCategoria");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al obtener id cartegoria.");
		}

		return obtenerIdCategoriaCombo;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnGuardar) {
			Producto producto = new Producto();
			Ctrl_Producto controlProducto = new Ctrl_Producto();

			String iva = "";
			String categoria = "";
			iva = comboBox_iva.getSelectedItem().toString().trim();
			categoria = comboBox_categoria.getSelectedItem().toString().trim();

			// validar campos
			if (txt_nombreProducto.getText().equals("") || txt_cantidadProducto.getText().equals("")
					|| txt_precioProducto.getText().equals("")) {

				JOptionPane.showMessageDialog(null, "Complete todos los campos");
				txt_nombreProducto.setBackground(Color.red);
				txt_cantidadProducto.setBackground(Color.red);
				txt_precioProducto.setBackground(Color.red);
				txt_descripcionProducto.setBackground(Color.red);

			} else {

				if (!controlProducto.existeProducto(txt_nombreProducto.getText().trim())) {
					if (iva.equalsIgnoreCase("Seleccione iva:")) {

						JOptionPane.showMessageDialog(null, "Seleccione iva.");

					} else {

						if (categoria.equalsIgnoreCase("Seleccione categoria:")) {

							JOptionPane.showMessageDialog(null, "Seleccione una categoria.");

						} else {

							try {

								producto.setNombre(txt_nombreProducto.getText().trim());
								producto.setCantidad(Integer.parseInt(txt_cantidadProducto.getText().trim()));
								String precioTXT = "";
								double Precio = 0.0;

								precioTXT = txt_precioProducto.getText().trim();

								boolean aux = false;

								// si el usuario ingresa, (coma) como punto decimal lo transformamos a punto
								// ////

								for (int i = 0; i < precioTXT.length(); i++) {
									if (precioTXT.charAt(i) == ',') {
										String precioNuevo = precioTXT.replace(",", ".");
										Precio = Double.parseDouble(precioNuevo);
										aux = true;
									}
								}

								// evaluar la condicion

								if (aux == true) {
									producto.setPrecio(Precio);
								} else {
									Precio = Double.parseDouble(precioTXT);
									producto.setPrecio(Precio);
								}

								producto.setDescripcion(txt_descripcionProducto.getText().trim());

								// porcentaje IVA

								if (iva.equalsIgnoreCase("No grava iva")) {
									producto.setPorcentajeIva(0);

								} else if (iva.equalsIgnoreCase("4%")) {
									producto.setPorcentajeIva(4);
								} else if (iva.equalsIgnoreCase("10%")) {
									producto.setPorcentajeIva(10);
								} else if (iva.equalsIgnoreCase("21%")) {
									producto.setPorcentajeIva(21);
								}

								// pasar idCategoria - cargar metodo que obtiene el id de la categoria

								this.IdCategoria();
								producto.setIdCategoria(obtenerIdCategoriaCombo);

								producto.setEstado(1);

								if (controlProducto.guardar(producto)) {
									JOptionPane.showMessageDialog(null, "Producto guardado.");
									txt_nombreProducto.setBackground(Color.green);
									txt_cantidadProducto.setBackground(Color.green);
									txt_precioProducto.setBackground(Color.green);
									txt_descripcionProducto.setBackground(Color.green);

									this.comboBox_iva.setSelectedItem("Seleccione iva:");
									this.comboBox_categoria.setSelectedItem("Seleccione categoria:");

									txt_nombreProducto.setText("");
									txt_cantidadProducto.setText("");
									txt_precioProducto.setText("");
									txt_descripcionProducto.setText("");

								} else {
									JOptionPane.showMessageDialog(null, "Error al guardar el producto.");
								}

							} catch (Exception e2) {
								// TODO: handle exception
								System.out.println("Error en: " + e2);
							}

						}

					}

				} else {
					JOptionPane.showMessageDialog(null, "Ya existe ese producto en la BBDD.");
				}

			}

		}

	}
}
