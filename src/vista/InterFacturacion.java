package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import controlador.Ctrl_RegistrarVenta;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InterFacturacion extends JInternalFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterFacturacion frame = new InterFacturacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTextField txt_cantidadFacturacion;
	private JTextField txt_cliente_buscar;
	private JTable jTable_Productos;
	private JTextField txt_subtotal;
	private JTextField txt_descuento;
	private JTextField txt_iva;
	private JTextField txt_totalapagar;
	private JTextField txt_efectivo;
	private JTextField txt_cambio;
	private JComboBox<String> comboBox_cliente;
	private JComboBox<String> comboBox_producto;
	private JButton btnBuscar_cliente;
	private JButton btn_calcularCambio;
	private JButton btn_registrarVenta;
	private JButton btn_anadir;

	private DefaultTableModel modeloDatosProductos;

	// lista para detallesVenta de los productos
	ArrayList<DetalleVenta> listaProductos = new ArrayList<DetalleVenta>();
	private DetalleVenta producto;

	private int idCliente = 0;

	private int idProducto = 0;
	private String nombre = "";
	private int cantidadProductoBBDD = 0;
	private double precioUnitario = 0.0;
	private int porcentajeIVA = 0;

	private int cantidad = 0; // cantidad de productos a comprar
	private double subTotal = 0.0; // cantidad por precio
	private double descuento = 0.0;
	private double iva = 0.0;
	private double totalPagar = 0.0;

	// variables para calculos globales
	private double subtotalGeneral = 0.0;
	private double descuentoGeneral = 0.0;
	private double IvaGeneral = 0.0;
	private double totalPagarGeneral = 0.0;

	// fin de variables gloables

	private int auxIdDetalle = 1; // id ddel detalle de venta
	private int idArrayList;

	/**
	 * Create the frame.
	 */
	public InterFacturacion() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		this.setTitle("Facturacion");
		setResizable(true);
		setIconifiable(true);
		setClosable(true);

		JLabel lblNewLabel = new JLabel("Facturacion");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(335, 21, 350, 22);
		getContentPane().add(lblNewLabel);

		ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 700, WIDTH));

		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 72, 90, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Producto:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(26, 121, 90, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(275, 121, 90, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		comboBox_cliente = new JComboBox<String>();
		comboBox_cliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_cliente.setModel(new DefaultComboBoxModel(new String[] { "Selecciona cliente:" }));
		comboBox_cliente.setBounds(106, 70, 165, 22);
		getContentPane().add(comboBox_cliente);

		comboBox_producto = new JComboBox<String>();
		comboBox_producto.setModel(new DefaultComboBoxModel(new String[] { "Selecciona producto:" }));
		comboBox_producto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_producto.setBounds(106, 121, 165, 22);
		getContentPane().add(comboBox_producto);

		txt_cantidadFacturacion = new JTextField();
		txt_cantidadFacturacion.setBounds(352, 121, 80, 20);
		getContentPane().add(txt_cantidadFacturacion);
		txt_cantidadFacturacion.setColumns(10);

		txt_cliente_buscar = new JTextField();
		txt_cliente_buscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_cliente_buscar.setBounds(275, 71, 130, 20);
		getContentPane().add(txt_cliente_buscar);
		txt_cliente_buscar.setColumns(10);

		btnBuscar_cliente = new JButton("Buscar");
		btnBuscar_cliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar_cliente.addActionListener(this);
		btnBuscar_cliente.setBounds(405, 70, 90, 23);
		getContentPane().add(btnBuscar_cliente);

		btn_anadir = new JButton("Añadir");
		btn_anadir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_anadir.addActionListener(this);
		btn_anadir.setBounds(442, 121, 90, 23);
		getContentPane().add(btn_anadir);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 154, 764, 205);
		getContentPane().add(panel);
		panel.setLayout(null);

		jTable_Productos = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Productos);
		jTable_Productos.addMouseListener(this);
		scroll.addMouseListener(this);
		scroll.setBounds(10, 11, 744, 183);
		panel.add(scroll);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(352, 370, 422, 189);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("SubTotal:");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(27, 11, 82, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Descuento:");
		lblNewLabel_2_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(27, 36, 82, 14);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("IVA:");
		lblNewLabel_2_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(27, 61, 70, 14);
		panel_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Total a pagar:");
		lblNewLabel_2_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(27, 86, 97, 14);
		panel_1.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_3_1 = new JLabel("Efectivo:");
		lblNewLabel_2_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3_1.setBounds(27, 128, 97, 14);
		panel_1.add(lblNewLabel_2_3_1);

		JLabel lblNewLabel_2_3_2 = new JLabel("Cambio:");
		lblNewLabel_2_3_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3_2.setBounds(27, 153, 97, 14);
		panel_1.add(lblNewLabel_2_3_2);

		txt_subtotal = new JTextField();
		txt_subtotal.setForeground(new Color(0, 0, 0));
		txt_subtotal.setEnabled(false);
		txt_subtotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_subtotal.setBounds(119, 9, 138, 20);
		panel_1.add(txt_subtotal);
		txt_subtotal.setColumns(10);

		txt_descuento = new JTextField();
		txt_descuento.setForeground(new Color(0, 0, 0));
		txt_descuento.setEnabled(false);
		txt_descuento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_descuento.setColumns(10);
		txt_descuento.setBounds(119, 34, 138, 20);
		panel_1.add(txt_descuento);

		txt_iva = new JTextField();
		txt_iva.setForeground(new Color(0, 0, 0));
		txt_iva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_iva.setEnabled(false);
		txt_iva.setColumns(10);
		txt_iva.setBounds(119, 59, 138, 20);
		panel_1.add(txt_iva);

		txt_totalapagar = new JTextField();
		txt_totalapagar.setForeground(new Color(0, 0, 0));
		txt_totalapagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_totalapagar.setEnabled(false);
		txt_totalapagar.setColumns(10);
		txt_totalapagar.setBounds(119, 84, 138, 20);
		panel_1.add(txt_totalapagar);

		txt_efectivo = new JTextField();
		txt_efectivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_efectivo.setColumns(10);
		txt_efectivo.setBounds(119, 126, 138, 20);
		panel_1.add(txt_efectivo);

		txt_cambio = new JTextField();
		txt_cambio.setEnabled(false);
		txt_cambio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_cambio.setColumns(10);
		txt_cambio.setBounds(119, 153, 138, 20);
		panel_1.add(txt_cambio);

		btn_calcularCambio = new JButton("Calcular Cambio");
		btn_calcularCambio.setBackground(new Color(0, 255, 255));
		btn_calcularCambio.addActionListener(this);
		btn_calcularCambio.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_calcularCambio.setBounds(267, 124, 132, 49);
		panel_1.add(btn_calcularCambio);

		btn_registrarVenta = new JButton("Registrar Venta");
		btn_registrarVenta.setIcon(new ImageIcon(InterFacturacion.class.getResource("/img/anadir.png")));
		btn_registrarVenta.addActionListener(this);
		btn_registrarVenta.setBackground(new Color(0, 255, 255));
		btn_registrarVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_registrarVenta.setBounds(53, 417, 233, 94);
		getContentPane().add(btn_registrarVenta);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setBounds(0, 0, 784, 570);
		getContentPane().add(jLabel_wallpaper);
		jLabel_wallpaper.setIcon(icono);
		this.repaint();

		txt_subtotal.setText("0");
		txt_descuento.setText("0");
		txt_iva.setText("0");
		txt_totalapagar.setText("0");

		txt_efectivo.setEnabled(false);
		btn_calcularCambio.setEnabled(false);

		cargarComboClientes();
		cargarComboProductos();
		inicializarTablaProducto();
		listaTablaProductos();

	}

	// metodo para inicializar la tabla de los productos

	private void inicializarTablaProducto() {

		modeloDatosProductos = new DefaultTableModel();
		modeloDatosProductos.addColumn("Nº");
		modeloDatosProductos.addColumn("Nombre");
		modeloDatosProductos.addColumn("Cantidad");
		modeloDatosProductos.addColumn("P Unitario");
		modeloDatosProductos.addColumn("SubTotal");
		modeloDatosProductos.addColumn("Descuento");
		modeloDatosProductos.addColumn("IVA");
		modeloDatosProductos.addColumn("Total a Pagar");
		modeloDatosProductos.addColumn("Accion");

		this.jTable_Productos.setModel(modeloDatosProductos);

	}

	// metodo para cargar clientes en el combobox

	private void cargarComboClientes() {

		Connection cn = Conexion.conectar();
		String sql = "select * from tb_cliente";
		Statement st;
		try {

			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			comboBox_cliente.removeAllItems();
			comboBox_cliente.addItem("Seleccione cliente:");
			while (rs.next()) {
				comboBox_cliente.addItem((rs.getString("apellido")));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cargar clientes " + e);
		}

	}

	// metodo para cargar productos en el combobox

	private void cargarComboProductos() {

		Connection cn = Conexion.conectar();
		String sql = "select * from tb_producto";
		Statement st;
		try {

			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			comboBox_producto.removeAllItems();
			comboBox_producto.addItem("Seleccione producto:");
			while (rs.next()) {
				comboBox_producto.addItem(rs.getString("nombre"));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cargar productos " + e);
		}

	}

	// metodo para validar que el usuario no ingrese caraacteres no numericos
	private boolean validar(String valor) {
		// TODO Auto-generated method stub
		try {
			int num = Integer.parseInt(valor);
			return true;

		} catch (NumberFormatException e) {
			// TODO: handle exception

			System.out.println("error al validar" + e);
			return false;
		}

	}

	// metodo para validar que el usuario no ingrese caraacteres no numericos
	private boolean validarDouble(String valor) {
		// TODO Auto-generated method stub
		try {
			Double num = Double.parseDouble(valor);
			return true;

		} catch (NumberFormatException e) {
			// TODO: handle exception

			System.out.println("error al validar" + e);
			return false;
		}

	}

	// metodo para calcular iva
	private double calcularIva(double precio, int porcentajeIva) {
		int p_iva = porcentajeIva;

		switch (p_iva) {
		case 0:
			iva = 0.0;
			break;

		case 4:
			iva = (precio * cantidad) * 0.04;
			break;

		case 10:
			iva = (precio * cantidad) * 0.10;
			break;

		case 21:
			iva = (precio * cantidad) * 0.21;
			break;

		default:
			break;

		}
		return iva;
	}

	// metodo para mostrar los datos del producto
	private void DatosDelProducto() {

		try {

			String sql = "select * from tb_producto  where nombre = '" + this.comboBox_producto.getSelectedItem() + "'";
			Connection cn = Conexion.conectar();
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				idProducto = rs.getInt("idProducto");
				nombre = rs.getString("nombre");
				cantidadProductoBBDD = rs.getInt("cantidad");
				precioUnitario = rs.getDouble("precio");
				porcentajeIVA = rs.getInt("porcentajeIva");

				this.calcularIva(precioUnitario, porcentajeIVA);

			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error al obtener los datos del producto;" + e);
		}

	}

	// metodo para presentar informacion de la tabla
	private void listaTablaProductos() {
		this.modeloDatosProductos.setRowCount(listaProductos.size());
		for (int i = 0; i < listaProductos.size(); i++) {
			this.modeloDatosProductos.setValueAt(i + 1, i, 0);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubtotal(), i, 4);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getIva(), i, 6);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
			this.modeloDatosProductos.setValueAt("Eliminar", i, 8);
		}

		jTable_Productos.setModel(modeloDatosProductos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnBuscar_cliente) {

			String clienteBuscar = txt_cliente_buscar.getText().trim();
			Connection cn = Conexion.conectar();
			String sql = "select apellido from tb_cliente where cedula = '" + clienteBuscar + "'";
			Statement st;

			try {

				st = cn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					comboBox_cliente.setSelectedItem(rs.getString("apellido"));
					txt_cliente_buscar.setText("");
				} else {
					comboBox_cliente.setSelectedItem("Seleccione cliente:");
					JOptionPane.showMessageDialog(null, "No se encontro ese cliente");
					txt_cliente_buscar.setText("");
				}
				cn.close();

			} catch (SQLException e2) {
				// TODO: handle exception
				System.out.println("Error al buscar cliente " + e2);
			}

		}

		if (e.getSource() == btn_anadir) {
			String combo = this.comboBox_producto.getSelectedItem().toString();
			if (combo.equalsIgnoreCase("Seleccione producto:")) {
				JOptionPane.showMessageDialog(null, "Seleccione un producto");
			} else {

				// validar que ingresa una cantidad
				if (!txt_cantidadFacturacion.getText().isEmpty()) {
					// validar que no ingrese caracteres no numericos
					boolean validacion = validar(txt_cantidadFacturacion.getText());
					if (validacion == true) {
						// validar que la cantidad sea mayor que 0
						if (Integer.parseInt(txt_cantidadFacturacion.getText()) > 0) {
							cantidad = Integer.parseInt(txt_cantidadFacturacion.getText());
							// ejecutar metodo para obtener datos del producto
							this.DatosDelProducto();
							// validar que la cantidad de productos seleccionado no sea mayor al stock de la
							// bbdd
							if (cantidad <= cantidadProductoBBDD) {

								subTotal = precioUnitario * cantidad;
								totalPagar = subTotal + iva + descuento;

								// redondear decimales
								subTotal = (double) Math.round(subTotal * 100) / 100;
								iva = (double) Math.round(iva * 100) / 100;
								descuento = (double) Math.round(descuento * 100) / 100;
								totalPagar = (double) Math.round(totalPagar * 100) / 100;

								// crear un nuevo producto
								producto = new DetalleVenta(auxIdDetalle, 1, idProducto, nombre,
										Integer.parseInt(txt_cantidadFacturacion.getText()), precioUnitario, subTotal,
										descuento, iva, totalPagar, 1);

								listaProductos.add(producto);
								JOptionPane.showMessageDialog(null, "Producto agregado");
								auxIdDetalle++;
								txt_cantidadFacturacion.setText("");
								this.cargarComboProductos();

								this.CalcularTotalPagar();

								txt_efectivo.setEnabled(true);
								btn_calcularCambio.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(null, "La cantidad supera el stock");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Ingresa una cantidad mayor que 0");
						}
					} else {
						JOptionPane.showMessageDialog(null, "No se admiten caracteres no numericos");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
				}

			}

			this.listaTablaProductos();
		}

		if (e.getSource() == btn_calcularCambio) {

			if (!txt_efectivo.getText().isEmpty()) {
				// validacion qeu el usuario no ingrese otro caracteres no numericos
				boolean validacion = validarDouble(txt_efectivo.getText());
				if (validacion == true) {
					// validar que el efectivo sea mayor al total a pagar
					double efectivo = Double.parseDouble(txt_efectivo.getText().trim());
					double top = Double.parseDouble(txt_totalapagar.getText().trim());
					if (efectivo < top) {
						JOptionPane.showMessageDialog(null, "El dinero efectivo no es suficiente");
					} else {
						double cambio = (efectivo - top);
						double cmb = (double) Math.round(cambio * 100) / 100;
						String camb = String.valueOf(cmb);
						txt_cambio.setText(camb);
					}

				} else {
					JOptionPane.showMessageDialog(null, "No se admiten caracteres no numericos");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio");
			}

		}

		if (e.getSource() == btn_registrarVenta) {

			CabeceraVenta cabeceraVenta = new CabeceraVenta();
			DetalleVenta detalleVenta = new DetalleVenta();
			Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();

			String fechaActual = "";
			Date date = new Date();
			fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

			if (!comboBox_cliente.getSelectedItem().equals("Seleccione cliente:")) {

				if (listaProductos.size() > 0) {

					// metodo para obtener el id del cliente
					this.ObtenerIdCliente();

					// registrar cabecera de venta
					cabeceraVenta.setIdCabeceraVenta(0);
					cabeceraVenta.setIdCliente(idCliente);
					cabeceraVenta.setValorPagar(Double.parseDouble(txt_totalapagar.getText()));
					cabeceraVenta.setFechaVenta(fechaActual);
					cabeceraVenta.setEstado(1);

					if (controlVenta.guardar(cabeceraVenta)) {
						JOptionPane.showMessageDialog(null, "Venta registrada");

						// guardar detalles de venta
						for (DetalleVenta elemento : listaProductos) {
							detalleVenta.setIdDetalleVenta(0);
							detalleVenta.setIdCabeceraVenta(0);
							detalleVenta.setIdProducto(elemento.getIdProducto());
							detalleVenta.setCantidad(elemento.getCantidad());
							detalleVenta.setPrecioUnitario(elemento.getPrecioUnitario());
							detalleVenta.setSubtotal(elemento.getSubtotal());
							detalleVenta.setDescuento(elemento.getDescuento());
							detalleVenta.setIva(elemento.getIva());
							detalleVenta.setTotalPagar(elemento.getTotalPagar());
							detalleVenta.setEstado(1);

							if (controlVenta.guardarDetalle(detalleVenta)) {
								System.out.println("Venta registrada correctamente");
								
								txt_subtotal.setText("0.0");
								txt_descuento.setText("0.0");
								txt_iva.setText("0.0");
								txt_totalapagar.setText("0.0");
								txt_efectivo.setText("0.0");
								txt_cambio.setText("0.0");
								auxIdDetalle = 1;
								
								this.cargarComboClientes();
								this.cargarComboProductos();
								
								this.restarStockProductos(elemento.getIdProducto(), elemento.getCantidad());
								
								
								

							} else {
								JOptionPane.showMessageDialog(null, "Error al guardar detalle de venta.");
							}

						}
						
						//vaciar lista productos
						listaProductos.clear();
						listaTablaProductos();

					} else {
						JOptionPane.showMessageDialog(null, "Error al guardar cabecera de venta.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un producto");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un cliente");
			}

		}

	}
	
	
	// metodo para restar el stock de los productos vendidos
	private void restarStockProductos(int idProducto , int cantidad) {
		// TODO Auto-generated method stub
		int cantidadProductosBBDD = 0;
		try {
			Connection cn = Conexion.conectar();
			String sql = "select idProducto,cantidad from tb_producto where idProducto = '"+idProducto+"'";
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				cantidadProductosBBDD = rs.getInt("cantidad");
				
			}
			
			cn.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al restar cantidad" + e);
		}
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement consulta = cn.prepareStatement("update tb_producto set cantidad=? where idProducto = '"+idProducto+"'");
			int cantidadNueva = cantidadProductosBBDD - cantidad;
			
			consulta.setInt(1, cantidadNueva);
			
			if(consulta.executeUpdate() > 0) {
				System.out.println("se ha actualizado el stock");
			}
			cn.close();
			
		} catch (SQLException e2) {
			// TODO: handle exception
			System.out.println("Error al restar cantidad 22" + e2);
		}
		
		
	}

	// obtener id cliente
	private void ObtenerIdCliente() {
		// TODO Auto-generated method stub
		try {

			String sql = "select * from tb_cliente where apellido = '" + this.comboBox_cliente.getSelectedItem() + "'";
			Connection cn = Conexion.conectar();
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				idCliente = rs.getInt("idCliente");

			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al obtener la idCliente: " + e);
		}

	}

	// metodo para calcular total a pagar de todos los productos agregados
	private void CalcularTotalPagar() {
		// TODO Auto-generated method stub

		// resetear variables
		subtotalGeneral = 0;
		descuentoGeneral = 0;
		IvaGeneral = 0;
		totalPagarGeneral = 0;

		for (DetalleVenta elemento : listaProductos) {
			subtotalGeneral += elemento.getSubtotal();
			descuentoGeneral += elemento.getDescuento();
			IvaGeneral += elemento.getIva();
			totalPagarGeneral += elemento.getTotalPagar();
		}

		// redondear decimales
		subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
		descuentoGeneral = (double) Math.round(descuentoGeneral * 100) / 100;
		IvaGeneral = (double) Math.round(IvaGeneral * 100) / 100;
		totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;

		// enviar datos a la vista

		txt_subtotal.setText(String.valueOf(subtotalGeneral));
		txt_iva.setText(String.valueOf(IvaGeneral));
		txt_descuento.setText(String.valueOf(descuentoGeneral));
		txt_totalapagar.setText(String.valueOf(totalPagarGeneral));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int fila = jTable_Productos.rowAtPoint(e.getPoint());
		int columna = jTable_Productos.columnAtPoint(e.getPoint());

		// Solo si hizo clic en la columna "Accion"
		if (fila >= 0 && columna == 8) {
			int opcion = JOptionPane.showConfirmDialog(null, "¿Eliminar producto?", "Confirmar eliminación",
					JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
				// Eliminar usando la posición de la fila
				listaProductos.remove(fila);

				// Actualizar tabla y totales
				this.CalcularTotalPagar();
				this.listaTablaProductos();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
