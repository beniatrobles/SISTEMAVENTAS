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
import controlador.Ctrl_Usuario;
import modelo.Usuario;

public class InterGestionarUsuarios extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int idUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterGestionarUsuarios frame = new InterGestionarUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTable jTable_Usuarios;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTextField txt_nombreUsuario;
	private JTextField txt_apellidoUsuario;
	private JTextField txt_usuarioUsuario;
	private JTextField txt_passwordUsuario;
	private JTextField txt_telefonoUsuario;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public InterGestionarUsuarios() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrar Usuarios");
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
		
		jTable_Usuarios = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Usuarios);
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

		txt_nombreUsuario = new JTextField();
		txt_nombreUsuario.setBounds(77, 10, 170, 20);
		panel_2.add(txt_nombreUsuario);
		txt_nombreUsuario.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 41, 100, 14);
		panel_2.add(lblNewLabel_1_1);
		
		txt_apellidoUsuario = new JTextField();
		txt_apellidoUsuario.setColumns(10);
		txt_apellidoUsuario.setBounds(77, 40, 170, 20);
		panel_2.add(txt_apellidoUsuario);
		
		JLabel lblNewLabel_1_2 = new JLabel("Usuario:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 75, 100, 14);
		panel_2.add(lblNewLabel_1_2);
		
		txt_usuarioUsuario = new JTextField();
		txt_usuarioUsuario.setColumns(10);
		txt_usuarioUsuario.setBounds(77, 74, 170, 20);
		panel_2.add(txt_usuarioUsuario);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Password:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(351, 14, 100, 14);
		panel_2.add(lblNewLabel_1_2_1);
		
		txt_passwordUsuario = new JTextField();
		txt_passwordUsuario.setColumns(10);
		txt_passwordUsuario.setBounds(445, 11, 170, 20);
		panel_2.add(txt_passwordUsuario);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Telefono:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(351, 44, 100, 14);
		panel_2.add(lblNewLabel_1_2_1_1);
		
		txt_telefonoUsuario = new JTextField();
		txt_telefonoUsuario.setColumns(10);
		txt_telefonoUsuario.setBounds(445, 40, 170, 22);
		panel_2.add(txt_telefonoUsuario);
		
		
		
		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setBounds(0, 0, 884, 470);
		getContentPane().add(jLabel_wallpaper);

		this.setSize(new Dimension(900, 500));

		String titulo = "Gestionar Usuarios";
		this.setTitle(titulo);

		// insertar omagen en el jlabel

		ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
		jLabel_wallpaper.setIcon(icono);
		this.repaint();
		
		CargarTablaUsuarios();

	}
	
	
	private void CargarTablaUsuarios() {
		try {
			
			Connection cn = Conexion.conectar();
			
			model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Nombre");
			model.addColumn("Apellido");
			model.addColumn("Usuario");
			model.addColumn("Password");
			model.addColumn("Telefono");
			model.addColumn("Estado");
			
			String sql = "select * from tb_usuario";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String usuario = rs.getString("usuario");
				String password = rs.getString("password");
				String telefono = rs.getString("telefono");
				int estado = rs.getInt("estado");
				
				model.addRow(new Object[] {id,nombre,apellido,usuario,password,telefono,estado} );
				
			}
			
			jTable_Usuarios.setModel(model);
			
			rs.close();
			st.close();
			cn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al llenar la tabla usuarios: " + e);
		}
		jTable_Usuarios.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = jTable_Usuarios.rowAtPoint(e.getPoint());
				int columna_point = 0;

				if (fila_point >= -1) {
					idUsuario = (int) model.getValueAt(fila_point, columna_point);
					EnviarDatosUsuarioSeleccionado(idUsuario);
				}
			}

		});
	}
	
	private void EnviarDatosUsuarioSeleccionado(int idUsuario) {
		
		try {
			
			Connection con = Conexion.conectar();
			PreparedStatement pst = con.prepareStatement("select * from tb_usuario where idUsuario = ?");
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				txt_nombreUsuario.setText(rs.getString("nombre"));
				txt_apellidoUsuario.setText(rs.getString("apellido"));
				txt_usuarioUsuario.setText(rs.getString("usuario"));
				txt_passwordUsuario.setText(rs.getString("password"));
				txt_telefonoUsuario.setText(rs.getString("telefono"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al seleccionar el usuario: " + e);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Ctrl_Usuario controlUsuario = new Ctrl_Usuario();
		
		if(e.getSource() == btnActualizar) {
			if(idUsuario == 0) {
				JOptionPane.showMessageDialog(null, "Selecciona un usuario.");
			}else {
				try {
					Usuario usuario = new Usuario();
					usuario.setIdUsuario(idUsuario);
					usuario.setNombre(txt_nombreUsuario.getText().trim());
					usuario.setApellido(txt_apellidoUsuario.getText().trim());
					usuario.setUsuario(txt_usuarioUsuario.getText().trim());
					usuario.setPassword(txt_passwordUsuario.getText().trim());
					usuario.setTelefono(txt_telefonoUsuario.getText().trim());
					
					if(controlUsuario.actualizar(usuario)) {
						JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
						CargarTablaUsuarios();
					}else {
						
						JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e2.getMessage());
				}
			}
			
		}
		
		
		if(e.getSource()==btnEliminar) {
			if(idUsuario == 0) {
				JOptionPane.showMessageDialog(null, "Selecciona un usuario primero");
			}else {
				try {
					if(controlUsuario.eliminar(idUsuario)) {
						JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
						CargarTablaUsuarios();
					}else {
						JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e2.getMessage());
				}
			}
		}
		
	}

}
