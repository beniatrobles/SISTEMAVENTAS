package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;

public class FrmMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
	private JMenu menuUsuario;
	private JMenu menuProducto;
	private JMenu menuCliente;
	private JMenu menuCategoria;
	private JMenu menuFacturar;
	private JMenu menuReportes;
	private JMenu menuHistorial;
	private JMenu menuCerrarSesion;
	private JMenuItem mntmNuevoUsuario;
	private JMenuItem mntmGestionarUsuarios;
	private JMenuItem mntmNuevoProductos;
	private JMenuItem mntmGestionarProductos;
	private JMenuItem mntmActualizarStock;
	private JMenuItem mntmNuevoCliente;
	private JMenuItem mntmGestionarClientes;
	private JMenuItem mntmNuevaCategoria;
	private JMenuItem mntmGestionarCategorias;
	private JMenuItem mntmNuevaVenta;
	private JMenuItem mntmGestionarVentas;
	private JMenuItem mntmReportesClientes;
	private JMenuItem mntmReportesCategorias;
	private JMenuItem mntmReportesProductos;
	private JMenuItem mntmReportesVentas;
	private JMenuItem mntmVerHistorial;
	private JMenuItem mntmCerrarSesion;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmMenu frame = new FrmMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 750);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(150, 50));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setTitle("Sistema de Ventas");
        
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);  
        
        
        // Menu Usuario ///
        menuUsuario = new JMenu("Usuario");
        menuUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuUsuario.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/usuario.png")));
        menuUsuario.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuUsuario);  
        
        mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
        mntmNuevoUsuario.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo-cliente.png")));
        menuUsuario.add(mntmNuevoUsuario);
        
        mntmGestionarUsuarios = new JMenuItem("Gestionar Usuarios");
        mntmGestionarUsuarios.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuUsuario.add(mntmGestionarUsuarios);
        
        
        // Menu Producto ///
        menuProducto = new JMenu("Producto");
        menuProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuProducto.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/producto.png")));
        menuProducto.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuProducto); 
        
        mntmNuevoProductos = new JMenuItem("Nuevo Producto");
        mntmNuevoProductos.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo-producto.png")));
        menuProducto.add(mntmNuevoProductos);
        
        mntmGestionarProductos = new JMenuItem("Gestionar Productos");
        mntmGestionarProductos.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuProducto.add(mntmGestionarProductos);
        
        mntmActualizarStock = new JMenuItem("Actualizar Stock");
        mntmActualizarStock.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo.png")));
        menuProducto.add(mntmActualizarStock);
        
        
        // Menu Cliente ///
        menuCliente = new JMenu("Cliente");
        menuCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuCliente.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cliente.png")));
        menuCliente.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuCliente); 
        
        mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
        mntmNuevoCliente.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo-cliente.png")));
        menuCliente.add(mntmNuevoCliente);
        
        mntmGestionarClientes = new JMenuItem("Gestionar Clientes");
        mntmGestionarClientes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuCliente.add(mntmGestionarClientes);
        
        
        // Menu CAtegoria ///
        
        menuCategoria = new JMenu("Categoria");
        menuCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/categorias.png")));
        menuCategoria.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuCategoria); 
        
        mntmNuevaCategoria = new JMenuItem("Nueva Categoria");
        mntmNuevaCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo.png")));
        menuCategoria.add(mntmNuevaCategoria);
        
        mntmGestionarCategorias = new JMenuItem("Gestionar Categorias");
        mntmGestionarCategorias.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuCategoria.add(mntmGestionarCategorias);
        
        
        // Menu Facturar ///
        
        menuFacturar = new JMenu("Facturar");
        menuFacturar.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuFacturar.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/carrito.png")));
        menuFacturar.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuFacturar); 
        
        mntmNuevaVenta = new JMenuItem("Nueva Venta");
        mntmNuevaVenta.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/anadir.png")));
        menuFacturar.add(mntmNuevaVenta);
        
        mntmGestionarVentas = new JMenuItem("Gestionar Ventas");
        mntmGestionarVentas.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuFacturar.add(mntmGestionarVentas);
        
        
        // Menu Reportes  ///
        
        menuReportes = new JMenu("Reportes");
        menuReportes.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuReportes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/reporte1.png")));
        menuReportes.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuReportes); 
        
        mntmReportesClientes = new JMenuItem("Reportes Clientes");
        mntmReportesClientes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cliente.png")));
        menuReportes.add(mntmReportesClientes);
        
        mntmReportesCategorias = new JMenuItem("Reportes Categorias");
        mntmReportesCategorias.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/categorias.png")));
        menuReportes.add(mntmReportesCategorias);
        
        mntmReportesProductos = new JMenuItem("Reportes Productos");
        mntmReportesProductos.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo-producto.png")));
        menuReportes.add(mntmReportesProductos);
        
        mntmReportesVentas = new JMenuItem("Reportes Ventas");
        mntmReportesVentas.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/carrito.png")));
        menuReportes.add(mntmReportesVentas);
        
        
        // Menu Historial ///
        
        menuHistorial = new JMenu("Historial");
        menuHistorial.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuHistorial.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/historial1.png")));
        menuHistorial.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuHistorial); 
        
        mntmVerHistorial = new JMenuItem("Ver Historial");
        mntmVerHistorial.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/anadir.png")));
        menuHistorial.add(mntmVerHistorial);
        
        
        ////////////////// CERRAR SESION ////////////
        menuCerrarSesion = new JMenu("Cerrar Sesion");
        menuCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuCerrarSesion.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cerrar-sesion.png")));
        menuCerrarSesion.setPreferredSize(new Dimension(150, 50));
        menuBar.add(menuCerrarSesion); 
        
        mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
        mntmCerrarSesion.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cerrar-sesion.png")));
        menuCerrarSesion.add(mntmCerrarSesion);

        

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
}
