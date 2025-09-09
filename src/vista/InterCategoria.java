package vista;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JTextField;

import controlador.Ctrl_Categoria;
import modelo.Categoria;

import javax.swing.JButton;

public class InterCategoria extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txt_descripcion;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnGuardar;
	private JLabel jLabel_wallpaper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterCategoria frame = new InterCategoria();
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
	public InterCategoria() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Nueva Categoria");
		lblNewLabel.setLocation(new Point(130, 10));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(98, 22, 175, 22);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Descripcion categoria:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 65, 166, 22);
		getContentPane().add(lblNewLabel_1);

		txt_descripcion = new JTextField();
		txt_descripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_descripcion.setBounds(186, 67, 155, 20);
		getContentPane().add(txt_descripcion);
		txt_descripcion.setColumns(10);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBackground(new Color(0, 204, 204));
		btnGuardar.setBounds(125, 111, 110, 23);
		btnGuardar.addActionListener(this);
		getContentPane().add(btnGuardar);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setIcon(new ImageIcon(InterCategoria.class.getResource("/img/fondo3.jpg")));
		jLabel_wallpaper.setBounds(0, 0, 384, 170);
		getContentPane().add(jLabel_wallpaper);

		this.setSize(new Dimension(400, 200));

		String titulo = "Nueva Categoria";
		this.setTitle(titulo);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnGuardar) {
			Categoria categoria = new Categoria();
			Ctrl_Categoria controlCategoria = new Ctrl_Categoria();

			// validacion campos vacios
			if (txt_descripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Complete el campo descripcion");
			} else {

				if (!controlCategoria.existeCategoria(txt_descripcion.getText().trim())) {
				    categoria.setDescripcion(txt_descripcion.getText().trim());
				    categoria.setEstado(1);
				    if (controlCategoria.guardar(categoria)) {
				        JOptionPane.showMessageDialog(null, "Registro guardado");
				    } else {
				        JOptionPane.showMessageDialog(null, "Error al guardar");
				    }
				} else {
				    JOptionPane.showMessageDialog(null, "La categoria " + txt_descripcion.getText().trim() + " ya existe.");
				}
			}

			txt_descripcion.setText("");

		}
	}
}
