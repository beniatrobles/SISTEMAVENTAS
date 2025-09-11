package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
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


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import controlador.Ctrl_Cliente;
import modelo.Cliente;

public class InterGestionarCliente extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int idCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterGestionarCliente frame = new InterGestionarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTable jTable_Clientes;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTextField txt_nombre_Cliente;
	private JTextField txt_apellidoCliente;
	private JTextField txt_cedulaCliente;
	private JTextField txt_telefonoCliente;
	private JTextField txt_direccionCliente;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public InterGestionarCliente() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Administrar Clientes");
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

		jTable_Clientes = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Clientes);
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

		txt_nombre_Cliente = new JTextField();
		txt_nombre_Cliente.setBounds(77, 10, 170, 20);
		panel_2.add(txt_nombre_Cliente);
		txt_nombre_Cliente.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 41, 100, 14);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Cedula:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 75, 100, 14);
		panel_2.add(lblNewLabel_1_2);

		txt_apellidoCliente = new JTextField();
		txt_apellidoCliente.setColumns(10);
		txt_apellidoCliente.setBounds(77, 40, 170, 20);
		panel_2.add(txt_apellidoCliente);

		txt_cedulaCliente = new JTextField();
		txt_cedulaCliente.setColumns(10);
		txt_cedulaCliente.setBounds(77, 74, 170, 20);
		panel_2.add(txt_cedulaCliente);

		JLabel lblNewLabel_1_2_1 = new JLabel("Telefono:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(351, 41, 100, 14);
		panel_2.add(lblNewLabel_1_2_1);

		txt_telefonoCliente = new JTextField();
		txt_telefonoCliente.setColumns(10);
		txt_telefonoCliente.setBounds(445, 40, 170, 20);
		panel_2.add(txt_telefonoCliente);

		JLabel lblNewLabel_1_2_2 = new JLabel("Direccion:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2.setBounds(351, 14, 100, 14);
		panel_2.add(lblNewLabel_1_2_2);

		txt_direccionCliente = new JTextField();
		txt_direccionCliente.setColumns(10);
		txt_direccionCliente.setBounds(445, 11, 170, 20);
		panel_2.add(txt_direccionCliente);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setBounds(0, 0, 884, 470);
		getContentPane().add(jLabel_wallpaper);

		this.setSize(new Dimension(900, 500));

		String titulo = "Gestionar Clientes";
		this.setTitle(titulo);

		// insertar omagen en el jlabel

		ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
		jLabel_wallpaper.setIcon(icono);
		this.repaint();

		CargarTablaClientes();

	}

	private void CargarTablaClientes() {
		try {

			Connection con = Conexion.conectar();

			model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Nombre");
			model.addColumn("Apellido");
			model.addColumn("Cedula");
			model.addColumn("Telefono");
			model.addColumn("Direccion");
			model.addColumn("Estado");

			String sql = "SELECT idCliente, nombre, apellido, cedula, telefono, direccion, estado FROM tb_cliente";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
			    int id = rs.getInt("idCliente");
			    String nombre = rs.getString("nombre");
			    String apellido = rs.getString("apellido");
			    String cedula = rs.getString("cedula");
			    String telefono = rs.getString("telefono");
			    String direccion = rs.getString("direccion");
			    int estado = rs.getInt("estado");

			    model.addRow(new Object[] { id, nombre, apellido, cedula, telefono, direccion, estado });
			}
			jTable_Clientes.setModel(model);

			// Cerrar conexión
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al llenar la tabla categorias: " + e);
		}

		jTable_Clientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = jTable_Clientes.rowAtPoint(e.getPoint());
				int columna_point = 0;

				if (fila_point >= -1) {
					idCliente = (int) model.getValueAt(fila_point, columna_point);
					EnviarDatosClienteSeleccionado(idCliente);
				}
			}

		});

	}

	private void EnviarDatosClienteSeleccionado(int idCliente) {
		// TODO Auto-generated method stub

		try {

			Connection con = Conexion.conectar();
			PreparedStatement pst = con
					.prepareStatement("select * from tb_cliente where idCliente = '" + idCliente + "'");
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				txt_nombre_Cliente.setText(rs.getString("nombre"));
				txt_apellidoCliente.setText(rs.getString("apellido"));
				txt_cedulaCliente.setText(rs.getString("cedula"));
				txt_telefonoCliente.setText(rs.getString("telefono"));
				txt_direccionCliente.setText(rs.getString("direccion"));

			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al seleccionar el cliente: " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	    if(e.getSource() == btnEliminar) {
	        if(!txt_nombre_Cliente.getText().isEmpty()) {
	            Cliente cliente = new Cliente();
	            Ctrl_Cliente controlCliente = new Ctrl_Cliente();

	            if(controlCliente.eliminar(idCliente)) {
	                JOptionPane.showMessageDialog(null, "Cliente eliminado");
	                txt_nombre_Cliente.setText("");
	                txt_apellidoCliente.setText("");
	                txt_cedulaCliente.setText("");
	                txt_telefonoCliente.setText("");
	                txt_direccionCliente.setText("");
	                this.CargarTablaClientes();
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al eliminar");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Selecciona un cliente");
	        }
	    }

	    if(e.getSource() == btnActualizar) {
	        if(!txt_nombre_Cliente.getText().isEmpty()) {
	            Cliente cliente = new Cliente();
	            Ctrl_Cliente controlCliente = new Ctrl_Cliente();

	            cliente.setIdCliente(idCliente);
	            cliente.setNombre(txt_nombre_Cliente.getText().trim());
	            cliente.setApellido(txt_apellidoCliente.getText().trim());
	            cliente.setCedula(txt_cedulaCliente.getText().trim());
	            cliente.setTelefono(txt_telefonoCliente.getText().trim());
	            cliente.setDireccion(txt_direccionCliente.getText().trim());

	            if (controlCliente.actualizar(cliente)) {
	                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente");
	                this.CargarTablaClientes();
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al actualizar cliente");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Selecciona un cliente");
	        }
	    }
	}

}
