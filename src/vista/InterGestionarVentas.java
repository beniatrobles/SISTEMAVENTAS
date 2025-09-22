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

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import controlador.Ctrl_RegistrarVenta;
import modelo.CabeceraVenta;
import modelo.Cliente;

public class InterGestionarVentas extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int idCliente = 0;
	private int idVenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterGestionarVentas frame = new InterGestionarVentas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTable jTable_Ventas;
	private JButton btnActualizar;
	private JTextField txt_totalapagar;
	private JTextField txt_fechaVenta;
	private DefaultTableModel model;
	private JComboBox<String> comboBox_cliente;
	private JComboBox<String> comboBox_estado;

	/**
	 * Create the frame.
	 */
	public InterGestionarVentas() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Administrar Ventas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(315, 11, 350, 22);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 61, 730, 270);
		getContentPane().add(panel);
		panel.setLayout(null);

		jTable_Ventas = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Ventas);
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

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 330, 730, 100);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Total pagar:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 100, 19);
		panel_2.add(lblNewLabel_1);

		txt_totalapagar = new JTextField();
		txt_totalapagar.setEditable(false);
		txt_totalapagar.setBounds(103, 10, 170, 20);
		panel_2.add(txt_totalapagar);
		txt_totalapagar.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 41, 100, 14);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Estado:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(351, 41, 100, 14);
		panel_2.add(lblNewLabel_1_2);

		txt_fechaVenta = new JTextField();
		txt_fechaVenta.setEnabled(false);
		txt_fechaVenta.setColumns(10);
		txt_fechaVenta.setBounds(103, 40, 170, 20);
		panel_2.add(txt_fechaVenta);

		JLabel lblNewLabel_1_2_2 = new JLabel("Cliente:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_2.setBounds(351, 14, 100, 14);
		panel_2.add(lblNewLabel_1_2_2);

		comboBox_cliente = new JComboBox<String>();
		comboBox_cliente.setBounds(418, 10, 165, 22);
		panel_2.add(comboBox_cliente);
		comboBox_cliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_cliente.setModel(new DefaultComboBoxModel(new String[] { "Selecciona cliente:" }));

		comboBox_estado = new JComboBox<String>();
		comboBox_estado.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		comboBox_estado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_estado.setBounds(418, 39, 165, 22);
		panel_2.add(comboBox_estado);

		this.setSize(new Dimension(900, 500));

		String titulo = "Gestionar Clientes";
		this.setTitle("Gestionar Ventas");

		// insertar omagen en el jlabel

		ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setBounds(0, 0, 884, 470);
		getContentPane().add(jLabel_wallpaper);
		jLabel_wallpaper.setIcon(icono);
		this.repaint();

		CargarTablaVentas();
		cargarComboCliente();

	}

	private void cargarComboCliente() {
		// TODO Auto-generated method stub
		
		try {
			Connection cn = Conexion.conectar();
			String sql = "select apellido from tb_cliente";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				comboBox_cliente.addItem(rs.getString("apellido"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al cargar clientes" + e);
		}
		
	}

	private void Limpiar() {
		this.txt_totalapagar.setText("");
		this.txt_fechaVenta.setText("");
		this.comboBox_cliente.setSelectedItem("Seleccione cliente:");
		this.comboBox_estado.setSelectedItem("Activo");
		idCliente = 0;

	}

	private void CargarTablaVentas() {
		try {

			Connection con = Conexion.conectar();

			model = new DefaultTableModel();
			model.addColumn("Nº");
			model.addColumn("Cliente");
			model.addColumn("Total Pagar");
			model.addColumn("Fecha Venta");
			model.addColumn("Estado");

			String sql = "select cv.idCabeceraVenta as id,c.apellido as cliente, cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado from tb_cabecera_venta as cv, tb_cliente as c where cv.idCliente = c.idCliente";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Object fila[] = new Object[5];
				for (int i = 0; i < 5; i++) {

					if (i == 4) {
						String estado = String.valueOf(rs.getObject(i + 1));
						if (estado.equalsIgnoreCase("1")) {
							fila[i] = "Activo";
						} else {
							fila[i] = "Inactivo";
						}
					} else {
						fila[i] = rs.getObject(i + 1);
					}

				}

				model.addRow(fila);
			}
			jTable_Ventas.setModel(model);

			// Cerrar conexión
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al llenar la tabla ventas: " + e);
		}

		jTable_Ventas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = jTable_Ventas.rowAtPoint(e.getPoint());
				int columna_point = 0;

				if (fila_point >= -1) {
					idVenta = (int) model.getValueAt(fila_point, columna_point);
					EnviarDatosClienteSeleccionado(idVenta);
				}
			}

		});

	}

	private void EnviarDatosClienteSeleccionado(int idVenta) {
		// TODO Auto-generated method stub

		try {

			Connection con = Conexion.conectar();
			PreparedStatement pst = con
					.prepareStatement("select cv.idCabeceraVenta ,c.idCliente ,c.apellido as cliente, cv.valorPagar , cv.fechaVenta , cv.estado from tb_cabecera_venta as cv, tb_cliente as c where cv.idCabeceraVenta = '"+idVenta+"' and  cv.idCliente = c.idCliente");
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				txt_totalapagar.setText(rs.getString("valorPagar"));
				txt_fechaVenta.setText(rs.getString("fechaVenta"));
				comboBox_cliente.setSelectedItem(rs.getString("cliente"));
				int estado = rs.getInt("estado");
				
				if(estado == 1) {
					comboBox_estado.setSelectedItem("Activo");
				}else {
					comboBox_estado.setSelectedItem("Inactivo");
				}
				

			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al seleccionar el venta: " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnActualizar) {
			CabeceraVenta cabecera = new CabeceraVenta();
			Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();
			String cliente ,estado;
			cliente = comboBox_cliente.getSelectedItem().toString().trim();
			estado = comboBox_estado.getSelectedItem().toString().trim();
			
			//obtener id cliente
			
			try {
				Connection cn = Conexion.conectar();
				PreparedStatement pst = cn.prepareStatement("select idCliente, apellido as cliente from tb_cliente where apellido = '"+cliente+"' ");
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					idCliente = rs.getInt("idCliente");
				}
				cn.close();
				
			} catch (SQLException e2) {
				// TODO: handle exception
				System.out.println("Error al obtener el id del cliente" + e2);
			}
			
			
			//Actualizar datos 
			
			if(!cliente.equalsIgnoreCase("Seleccione cliente:")) {
				cabecera.setIdCliente(idCliente);
				if(estado.equalsIgnoreCase("Activo")) {
					cabecera.setEstado(1);
				}else {
					cabecera.setEstado(0);
				}
				if(controlVenta.actualizar(cabecera, idVenta)) {
					JOptionPane.showMessageDialog(null,"Registro actualizado.");
					this.CargarTablaVentas();
					this.Limpiar();
				}else {
					JOptionPane.showMessageDialog(null,"Error al actuaizar.");
				}
				
				
			}else {
				JOptionPane.showMessageDialog(null,"Seleccione un cliente.");
			}
		}
	}
}
