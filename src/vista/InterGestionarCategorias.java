package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import conexion.Conexion;
import controlador.Ctrl_Categoria;
import modelo.Categoria;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

public class InterGestionarCategorias extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int idCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterGestionarCategorias frame = new InterGestionarCategorias();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel jLabel_wallpaper;
	private JTable jTable_Categorias;
	private JTextField txt_descripcion;
	private DefaultTableModel model;
	private JButton btnActualizar;
	private JButton btnEliminar;

	/**
	 * Create the frame.
	 */
	public InterGestionarCategorias() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Administrar Categorias");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(192, 11, 350, 22);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 61, 350, 250);
		getContentPane().add(panel);
		panel.setLayout(null);

		jTable_Categorias = new JTable();
		JScrollPane scroll = new JScrollPane(jTable_Categorias);
		scroll.setBounds(10, 11, panel.getWidth() - 20, panel.getHeight() - 22);
		panel.add(scroll);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(404, 61, 122, 80);
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
		panel_2.setBounds(380, 160, 190, 80);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Descripcion:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 100, 14);
		panel_2.add(lblNewLabel_1);

		txt_descripcion = new JTextField();
		txt_descripcion.setBounds(10, 36, 170, 20);
		panel_2.add(txt_descripcion);
		txt_descripcion.setColumns(10);

		jLabel_wallpaper = new JLabel("");
		jLabel_wallpaper.setAlignmentX(Component.CENTER_ALIGNMENT);
		jLabel_wallpaper.setIcon(new ImageIcon(InterCategoria.class.getResource("/img/fondo3.jpg")));
		jLabel_wallpaper.setBounds(0, 0, 584, 370);
		getContentPane().add(jLabel_wallpaper);

		this.setSize(new Dimension(600, 400));

		String titulo = "Gestionar Categorias";
		this.setTitle(titulo);

		CargarTablaCategorias();

	}

	/*
	 * metodo para mostrar todas las categorias
	 * 
	 */

	private void CargarTablaCategorias() {
		try {

			Connection con = Conexion.conectar();

			// Crear el modelo de la tabla
			model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Descripción");
			model.addColumn("Estado");

			String sql = "SELECT * FROM tb_categoria";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// Llenar el modelo con los datos
			while (rs.next()) {
				int id = rs.getInt("idcategoria");
				String descripcion = rs.getString("descripcion");
				int estado = rs.getInt("estado");
				model.addRow(new Object[] { id, descripcion, estado });
			}

			// Asignar el modelo a la tabla
			jTable_Categorias.setModel(model);

			// Cerrar conexión
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al llenar la tabla categorias: " + e);
		}

		jTable_Categorias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = jTable_Categorias.rowAtPoint(e.getPoint());
				int columna_point = 0;

				if (fila_point >= -1) {
					idCategoria = (int) model.getValueAt(fila_point, columna_point);
					EnviarDatosCategoriaSeleccionada(idCategoria);
				}
			}

		});
	}

	private void EnviarDatosCategoriaSeleccionada(int idCategoria) {
		// TODO Auto-generated method stub
		try {

			Connection con = Conexion.conectar();
			PreparedStatement pst = con
					.prepareStatement("select * from tb_categoria where idCategoria = '" + idCategoria + "'");
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				txt_descripcion.setText(rs.getString("descripcion"));
			}
			con.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al seleccionar la categoria: " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnActualizar) {
			
			if(!txt_descripcion.getText().isEmpty()){
				Categoria categoria = new Categoria();
				Ctrl_Categoria controlCategoria = new Ctrl_Categoria();
				
				categoria.setDescripcion(txt_descripcion.getText().trim());
				
				if (controlCategoria.actualizar(categoria, idCategoria)) {
					JOptionPane.showMessageDialog(null, "Categoria actualizada");
					txt_descripcion.setText("");
					this.CargarTablaCategorias();
				}else {
					JOptionPane.showMessageDialog(null, "Error al actualizar");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Selecciona una categoria");
			}

		}

		if (e.getSource() == btnEliminar) {
			if(!txt_descripcion.getText().isEmpty()){
				Categoria categoria = new Categoria();
				Ctrl_Categoria controlCategoria = new Ctrl_Categoria();
				
				categoria.setDescripcion(txt_descripcion.getText().trim());
				
				if (controlCategoria.eliminar(idCategoria)) {
					JOptionPane.showMessageDialog(null, "Categoria eliminada");
					txt_descripcion.setText("");
					this.CargarTablaCategorias();
				}else {
					JOptionPane.showMessageDialog(null, "Error al eliminar");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Selecciona una categoria");
			}

		}

	}
}
