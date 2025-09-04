package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 700, 500);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setTitle("Login - Sistema de Ventas");

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout()); // 🔥 Aquí el cambio
	    setContentPane(contentPane);

	    JPanel panel_Imagen = new JPanel();
	    panel_Imagen.setBackground(new Color(51, 153, 255));
	    panel_Imagen.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    contentPane.add(panel_Imagen, BorderLayout.WEST); // Panel a la izquierda
	    panel_Imagen.setPreferredSize(new Dimension(325, 0));
	    panel_Imagen.setLayout(null);
	    
	    JLabel lblTitulo = new JLabel("Sistema de Ventas");
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
	    lblTitulo.setBounds(10, 60, 295, 40);
	    panel_Imagen.add(lblTitulo);
	    
	    JLabel lblRealizado = new JLabel("Beñat Robles");
	    lblRealizado.setHorizontalAlignment(SwingConstants.CENTER);
	    lblRealizado.setFont(new Font("Bodoni MT Black", Font.PLAIN, 20));
	    lblRealizado.setBounds(77, 339, 151, 24);
	    panel_Imagen.add(lblRealizado);
	    
	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/carrito1.png")));
	    lblNewLabel.setBounds(77, 148, 151, 149);
	    panel_Imagen.add(lblNewLabel);

	    JPanel panel_form = new JPanel();
	    panel_form.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    contentPane.add(panel_form, BorderLayout.CENTER); // Panel a la derecha
	    panel_form.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setBounds(107, 22, 128, 128);
	    lblNewLabel_1.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/user1.png")));
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    panel_form.add(lblNewLabel_1);
	}
}
