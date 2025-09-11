package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Ctrl_Cliente;
import modelo.Cliente;

public class InterCliente extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterCliente frame = new InterCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JButton btnGuardar;
	private JTextField txt_nombreProducto;
	private JTextField txt_telefonoCliente;
	private JTextField txt_nombreCliente;
	private JTextField txt_cantidadProducto;
	private JTextField txt_direccionCliente;
	private JTextField txt_apellidoCliente;
	private JTextField txt_precioProducto;
	private JTextField txt_cedulaCliente;

	/**
	 * Create the frame.
	 */
	public InterCliente() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nuevo Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(115, 23, 172, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(27, 62, 71, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(27, 97, 71, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Cedula:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(27, 130, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Telefono:");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(27, 162, 90, 25);
		getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Direccion:");
		lblNewLabel_1_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1.setBounds(27, 198, 90, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);

		txt_nombreCliente = new JTextField();
		txt_nombreCliente.setBounds(140, 166, 211, 20);
		getContentPane().add(txt_nombreCliente);
		txt_nombreCliente.setColumns(10);

		txt_apellidoCliente = new JTextField();
		txt_apellidoCliente.setColumns(10);
		txt_apellidoCliente.setBounds(140, 96, 211, 20);
		getContentPane().add(txt_apellidoCliente);

		txt_telefonoCliente = new JTextField();
		txt_telefonoCliente.setBounds(140, 61, 211, 20);
		getContentPane().add(txt_telefonoCliente);
		txt_telefonoCliente.setColumns(10);

		txt_direccionCliente = new JTextField();
		txt_direccionCliente.setColumns(10);
		txt_direccionCliente.setBounds(140, 197, 211, 20);
		getContentPane().add(txt_direccionCliente);

		txt_cedulaCliente = new JTextField();
		txt_cedulaCliente.setColumns(10);
		txt_cedulaCliente.setBounds(140, 129, 211, 20);
		getContentPane().add(txt_cedulaCliente);

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
		String titulo = "Cliente";
		this.setTitle(titulo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGuardar) {
			Cliente cliente = new Cliente();
			Ctrl_Cliente controlCliente = new Ctrl_Cliente();

			if (txt_nombreCliente.getText().equals("") || txt_apellidoCliente.getText().equals("")
					|| txt_cedulaCliente.getText().equals("") || txt_telefonoCliente.getText().equals("")
					|| txt_direccionCliente.getText().equals("")) {

				JOptionPane.showMessageDialog(null, "Complete todos los campos");
				txt_nombreCliente.setBackground(Color.red);
				txt_apellidoCliente.setBackground(Color.red);
				txt_cedulaCliente.setBackground(Color.red);
				txt_telefonoCliente.setBackground(Color.red);
				txt_direccionCliente.setBackground(Color.red);
			} else {
				if (!controlCliente.existeCliente(txt_nombreCliente.getText().trim())) {

					cliente.setNombre(txt_nombreCliente.getText().trim());
					cliente.setApellido(txt_apellidoCliente.getText().trim());
					cliente.setCedula(txt_cedulaCliente.getText().trim());
					cliente.setTelefono(txt_telefonoCliente.getText().trim());
					cliente.setDireccion(txt_direccionCliente.getText().trim());
					cliente.setEstado(1);
					
					
					
					 if (controlCliente.guardar(cliente)) {
		                    JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");

							txt_nombreCliente.setText("");;
							txt_apellidoCliente.setText("");
							txt_cedulaCliente.setText("");
							txt_telefonoCliente.setText("");
							txt_direccionCliente.setText("");
							
							txt_nombreCliente.setBackground(Color.green);
							txt_apellidoCliente.setBackground(Color.green);
							txt_cedulaCliente.setBackground(Color.green);
							txt_telefonoCliente.setBackground(Color.green);
							txt_direccionCliente.setBackground(Color.green);
							
							
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error al registrar cliente");
		                }

				}else {
					JOptionPane.showMessageDialog(null, "El cliente ya está registrado");
				}
			}

		}
	}

}
