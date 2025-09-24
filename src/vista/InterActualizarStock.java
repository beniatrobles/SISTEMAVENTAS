package vista;

import java.awt.Component;

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
import javax.swing.JButton;

public class InterActualizarStock extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterActualizarStock frame = new InterActualizarStock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTextField txt_cantidad_actual;
	private JTextField txt_cantidad_nuevo;
	private JComboBox<String> comboBox_productos;
	private JButton btnActualizarStock;

	/**
	 * Create the frame.
	 */
	public InterActualizarStock() {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Actualizar Stock");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(113, 11, 157, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(30, 55, 93, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Stock Actual:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(30, 98, 93, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Stock nuevo:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(30, 142, 93, 14);
		getContentPane().add(lblNewLabel_1_2);

		txt_cantidad_actual = new JTextField();
		txt_cantidad_actual.setColumns(10);
		txt_cantidad_actual.setBounds(133, 97, 215, 20);
		txt_cantidad_actual.setEditable(false);
		getContentPane().add(txt_cantidad_actual);

		txt_cantidad_nuevo = new JTextField();
		txt_cantidad_nuevo.setColumns(10);
		txt_cantidad_nuevo.setBounds(133, 141, 215, 20);
		getContentPane().add(txt_cantidad_nuevo);

		comboBox_productos = new JComboBox<String>();
		comboBox_productos.setBounds(133, 53, 215, 22);
		comboBox_productos.addActionListener(this);
		getContentPane().add(comboBox_productos);

		btnActualizarStock = new JButton("Actualizar");
		btnActualizarStock.setBackground(new Color(0, 255, 0));
		btnActualizarStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizarStock.setBounds(133, 210, 215, 23);
		btnActualizarStock.addActionListener(this);
		getContentPane().add(btnActualizarStock);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setIcon(new ImageIcon(InterCategoria.class.getResource("/img/fondo3.jpg")));
		jLabel_wallpaper.setBounds(0, 0, 384, 270);
		getContentPane().add(jLabel_wallpaper);

		cargarComboProductos();

	}

	// metodo para cargar los productos

	private void cargarComboProductos() {
		Connection cn = Conexion.conectar();
		String sql = "select * from tb_producto";
		Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			comboBox_productos.removeAllItems();
			comboBox_productos.addItem("Selecciona producto:");

			while (rs.next()) {

				comboBox_productos.addItem(rs.getString("nombre"));

			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cargar los productos");
		}
	}

	// metodo para mostrar el stock del producto selecionado

	int idProducto = 0;
	int cantidad = 0;

	private void mostrarStock() {
		try {

			Connection cn = Conexion.conectar();
			String sql = "select * from tb_producto where nombre = '" + this.comboBox_productos.getSelectedItem() + "'";
			Statement st;

			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				idProducto = rs.getInt("idProducto");
				cantidad = rs.getInt("cantidad");
				txt_cantidad_actual.setText(String.valueOf(cantidad));
			} else {
				txt_cantidad_actual.setText("");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al obtener el stock");
		}
	}

	// metodo valicion caraacteres no numericos

	private boolean validar(String valor) {
		
		try {
			int num;
			num = Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == comboBox_productos) {
			mostrarStock();
		}

		if (e.getSource() == btnActualizarStock) {

			if (!comboBox_productos.getSelectedItem().equals("Selecciona producto:")) {
				if (!txt_cantidad_nuevo.getText().isEmpty()) {
					
					boolean validacion = validar(txt_cantidad_nuevo.getText().trim());
					
					if (validacion == true) {
						if (Integer.parseInt(txt_cantidad_nuevo.getText()) > 0) {
							
							Producto producto = new Producto();
							Ctrl_Producto controlProducto = new Ctrl_Producto();
							int stockActual = Integer.parseInt(txt_cantidad_actual.getText().trim());
							int stockNuevo = Integer.parseInt(txt_cantidad_nuevo.getText().trim());
							
							stockNuevo = stockActual + stockNuevo;
							
							producto.setCantidad(stockNuevo);
							if(controlProducto.actualizarStock(producto, idProducto)) {
								JOptionPane.showMessageDialog(null, "Stock Actualizado");
								txt_cantidad_nuevo.setText("");
								txt_cantidad_actual.setText("");
								this.cargarComboProductos();
							}else {
								JOptionPane.showMessageDialog(null, "Error al actualizar");
							}
							
							

						} else {
							JOptionPane.showMessageDialog(null, "La cantidad tiene que ser mayor que 0");
						}
					} else {
						JOptionPane.showMessageDialog(null, "No valen valores no numericos");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Inserta un valor nuevo");
					txt_cantidad_nuevo.setBackground(Color.red);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Selecione un producto");
			}

		}

	}
}
