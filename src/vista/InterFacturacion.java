package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

public class InterFacturacion extends JInternalFrame {

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
		
		JComboBox comboBox_cliente = new JComboBox();
		comboBox_cliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_cliente.setModel(new DefaultComboBoxModel(new String[] {"Selecciona cliente:"}));
		comboBox_cliente.setBounds(106, 70, 165, 22);
		getContentPane().add(comboBox_cliente);
		
		JComboBox comboBox_producto = new JComboBox();
		comboBox_producto.setModel(new DefaultComboBoxModel(new String[] {"Selecciona producto:"}));
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
		
		JButton btnBuscar_cliente = new JButton("Buscar");
		btnBuscar_cliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscar_cliente.setBounds(405, 70, 90, 23);
		getContentPane().add(btnBuscar_cliente);
		
		JButton btn_anadir = new JButton("Añadir");
		btn_anadir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_anadir.setBounds(442, 121, 90, 23);
		getContentPane().add(btn_anadir);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 154, 764, 205);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		jTable_Productos = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Productos);
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
		txt_subtotal.setEnabled(false);
		txt_subtotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_subtotal.setBounds(119, 9, 138, 20);
		panel_1.add(txt_subtotal);
		txt_subtotal.setColumns(10);
		
		txt_descuento = new JTextField();
		txt_descuento.setEnabled(false);
		txt_descuento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_descuento.setColumns(10);
		txt_descuento.setBounds(119, 34, 138, 20);
		panel_1.add(txt_descuento);
		
		txt_iva = new JTextField();
		txt_iva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_iva.setEnabled(false);
		txt_iva.setColumns(10);
		txt_iva.setBounds(119, 59, 138, 20);
		panel_1.add(txt_iva);
		
		txt_totalapagar = new JTextField();
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
		
		JButton btn = new JButton("Calcular Cambio");
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn.setBounds(267, 124, 132, 23);
		panel_1.add(btn);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(119, 427, 119, 64);
		getContentPane().add(btnNewButton_1);
		
		
		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setBounds(0, 0, 784, 570);
		getContentPane().add(jLabel_wallpaper);
		jLabel_wallpaper.setIcon(icono);
		this.repaint();


	}
}
