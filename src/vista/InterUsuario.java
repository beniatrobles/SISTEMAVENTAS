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

import controlador.Ctrl_Usuario;
import modelo.Usuario;

public class InterUsuario extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterUsuario frame = new InterUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTextField txt_nombreUsuario;
	private JTextField txt_apellidoUsuario;
	private JTextField txt_usuarioUsuario;
	private JTextField txt_passwordUsuario;
	private JTextField txt_telefonoUsuario;
	private JButton btnGuardar;

	/**
	 * Create the frame.
	 */
	public InterUsuario() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nuevo Usuario");
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

		JLabel lblNewLabel_1_1_1 = new JLabel("Usuario:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(27, 130, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Password:");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(27, 162, 90, 25);
		getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Telefono:");
		lblNewLabel_1_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1.setBounds(27, 198, 90, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);

		txt_nombreUsuario = new JTextField();
		txt_nombreUsuario.setBounds(140, 61, 211, 20);
		getContentPane().add(txt_nombreUsuario);
		txt_nombreUsuario.setColumns(10);

		txt_apellidoUsuario = new JTextField();
		txt_apellidoUsuario.setColumns(10);		
		txt_apellidoUsuario.setBounds(140, 92, 211, 20);
		getContentPane().add(txt_apellidoUsuario);

		txt_usuarioUsuario = new JTextField();
		txt_usuarioUsuario.setColumns(10);
		txt_usuarioUsuario.setBounds(140, 129, 211, 20);
		getContentPane().add(txt_usuarioUsuario);

		txt_passwordUsuario = new JTextField();
		txt_passwordUsuario.setColumns(10);
		txt_passwordUsuario.setBounds(140, 162, 211, 20);
		getContentPane().add(txt_passwordUsuario);
		
		txt_telefonoUsuario = new JTextField();
		txt_telefonoUsuario.setColumns(10);
		txt_telefonoUsuario.setBounds(140, 197, 211, 20);
		getContentPane().add(txt_telefonoUsuario);

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
		String titulo = "Nuevo Usuario";
		this.setTitle(titulo);
		


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnGuardar) {
			Usuario usuario = new Usuario();
			Ctrl_Usuario controlUsuario = new Ctrl_Usuario();
			
			
			if(txt_nombreUsuario.getText().equals("") || txt_apellidoUsuario.getText().equals("") || txt_usuarioUsuario.getText().equals("") || txt_passwordUsuario.getText().equals("") || txt_telefonoUsuario.getText().equals("")) {
				
				JOptionPane.showMessageDialog(null, "Complete todos los campos");
				txt_nombreUsuario.setBackground(Color.red);
				txt_apellidoUsuario.setBackground(Color.red);
				txt_usuarioUsuario.setBackground(Color.red);
				txt_passwordUsuario.setBackground(Color.red);
				txt_telefonoUsuario.setBackground(Color.red);
				
				
				
			}else {
				
				if(!controlUsuario.existeUsuario(txt_usuarioUsuario.getText().trim())) {
					
					usuario.setNombre(txt_nombreUsuario.getText().trim());
					usuario.setApellido(txt_apellidoUsuario.getText().trim());
					usuario.setUsuario(txt_usuarioUsuario.getText().trim());
					usuario.setPassword(txt_passwordUsuario.getText().trim());
					usuario.setTelefono(txt_telefonoUsuario.getText().trim());
					usuario.setEstado(1);
					
					if(controlUsuario.guardar(usuario)) {
						JOptionPane.showMessageDialog(null, "Registro guardado");
					}else {
						JOptionPane.showMessageDialog(null, "Error al guardar");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "El usuario " + txt_usuarioUsuario.getText().trim() + " ya existe.");
				}
				
				
				
			}
			
			txt_nombreUsuario.setText("");
			txt_apellidoUsuario.setText("");
			txt_usuarioUsuario.setText("");
			txt_passwordUsuario.setText("");
			txt_telefonoUsuario.setText("");
			
		}
		
	}

}
