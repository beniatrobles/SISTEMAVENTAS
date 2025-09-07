package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controlador.Ctrl_Usuario;
import modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FrmLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPanel panel_Imagen;
	private JLabel lblTitulo;
	private JLabel lblRealizado;
	private JLabel lblNewLabel;
	private JPanel panel_form;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnIniciarSesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/img/ventas.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Login - Sistema de Ventas");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout()); 
		setContentPane(contentPane);

		panel_Imagen = new JPanel();
		panel_Imagen.setBackground(new Color(51, 153, 255));
		panel_Imagen.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_Imagen, BorderLayout.WEST); // Panel a la izquierda
		panel_Imagen.setPreferredSize(new Dimension(325, 0));
		panel_Imagen.setLayout(null);

		lblTitulo = new JLabel("Sistema de Ventas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		lblTitulo.setBounds(10, 60, 295, 40);
		panel_Imagen.add(lblTitulo);

		lblRealizado = new JLabel("Beñat Robles");
		lblRealizado.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealizado.setFont(new Font("Bodoni MT Black", Font.PLAIN, 20));
		lblRealizado.setBounds(77, 339, 151, 24);
		panel_Imagen.add(lblRealizado);

		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/carrito1.png")));
		lblNewLabel.setBounds(77, 148, 151, 149);
		panel_Imagen.add(lblNewLabel);

		panel_form = new JPanel();
		panel_form.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_form, BorderLayout.CENTER); // Panel a la derecha
		panel_form.setLayout(null);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(107, 22, 128, 128);
		lblNewLabel_1.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/user1.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_form.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(50, 204, 64, 14);
		panel_form.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Contraseña");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(50, 273, 90, 14);
		panel_form.add(lblNewLabel_3);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(149, 203, 137, 20);
		panel_form.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(149, 272, 137, 20);
		panel_form.add(txtPassword);

		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIniciarSesion.setBounds(107, 351, 128, 31);
		btnIniciarSesion.setBackground(new Color(51, 153, 255));
		panel_form.add(btnIniciarSesion);
	}

	private void login() {
		if (!txtUsuario.getText().isEmpty() && !txtPassword.getText().isEmpty()) {

			Ctrl_Usuario controlUsuario = new Ctrl_Usuario();

			Usuario usuario = new Usuario();
			usuario.setUsuario(txtUsuario.getText().trim());
			usuario.setPassword(txtPassword.getText().trim());

			if (controlUsuario.loginUser(usuario)) {
				JOptionPane.showMessageDialog(null, "Login correcto");
				FrmMenu menu = new FrmMenu();
				menu.setVisible(true);
				this.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Usuario o password incorrectos");
				txtUsuario.setText("");
				txtPassword.setText("");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Ingrese sus credenciales.");
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnIniciarSesion) {
			login();
		}

	}
}
