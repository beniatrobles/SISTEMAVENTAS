package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMenu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // Barra de menú y menús principales
    private JMenuBar menuBar;
    private JMenu menuUsuario;
    private JMenu menuProducto;
    private JMenu menuCliente;
    private JMenu menuCategoria;
    private JMenu menuFacturar;
    private JMenu menuReportes;
    private JMenu menuHistorial;
    private JMenu menuCerrarSesion;

    // Items Usuario
    private JMenuItem mntmNuevoUsuario;
    private JMenuItem mntmGestionarUsuarios;

    // Items Producto
    private JMenuItem mntmNuevoProductos;
    private JMenuItem mntmGestionarProductos;
    private JMenuItem mntmActualizarStock;

    // Items Cliente
    private JMenuItem mntmNuevoCliente;
    private JMenuItem mntmGestionarClientes;

    // Items Categoría
    private JMenuItem mntmNuevaCategoria;
    private JMenuItem mntmGestionarCategorias;

    // Items Facturación
    private JMenuItem mntmNuevaVenta;
    private JMenuItem mntmGestionarVentas;

    // Items Reportes
    private JMenuItem mntmReportesClientes;
    private JMenuItem mntmReportesCategorias;
    private JMenuItem mntmReportesProductos;
    private JMenuItem mntmReportesVentas;

    // Items Historial
    private JMenuItem mntmVerHistorial;

    // Items Cerrar Sesión
    private JMenuItem mntmCerrarSesion;

    // Panel de escritorio (para internal frames)
    public static JDesktopPane jDesktopPane_menu;

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
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            FrmMenu.class.getResource("/img/ventas.png")
        ));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 750);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Sistema de Ventas");

        // Panel principal
        jDesktopPane_menu = new DesktopConFondo("/img/fondo2.jpg");
        setContentPane(jDesktopPane_menu);

        // Crear barra de menú
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // ================== MENU USUARIO ==================
        menuUsuario = new JMenu("Usuario");
        menuUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuUsuario.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/usuario.png")));
        menuBar.add(menuUsuario);

        mntmNuevoUsuario = new JMenuItem("Nuevo Usuario",
            new ImageIcon(FrmMenu.class.getResource("/img/nuevo-cliente.png")));
        menuUsuario.add(mntmNuevoUsuario);

        mntmGestionarUsuarios = new JMenuItem("Gestionar Usuarios",
            new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuUsuario.add(mntmGestionarUsuarios);

        // ================== MENU PRODUCTO ==================
        menuProducto = new JMenu("Producto");
        menuProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuProducto.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/producto.png")));
        menuBar.add(menuProducto);

        mntmNuevoProductos = new JMenuItem("Nuevo Producto",
            new ImageIcon(FrmMenu.class.getResource("/img/nuevo-producto.png")));
        mntmNuevoProductos.addActionListener(this);
        menuProducto.add(mntmNuevoProductos);

        mntmGestionarProductos = new JMenuItem("Gestionar Productos",
            new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        mntmGestionarProductos.addActionListener(this);
        menuProducto.add(mntmGestionarProductos);

        mntmActualizarStock = new JMenuItem("Actualizar Stock",
            new ImageIcon(FrmMenu.class.getResource("/img/nuevo.png")));
        mntmActualizarStock.addActionListener(this);
        menuProducto.add(mntmActualizarStock);

        // ================== MENU CLIENTE ==================
        menuCliente = new JMenu("Cliente");
        menuCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuCliente.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cliente.png")));
        menuBar.add(menuCliente);

        mntmNuevoCliente = new JMenuItem("Nuevo Cliente",
            new ImageIcon(FrmMenu.class.getResource("/img/nuevo-cliente.png")));
        mntmNuevoCliente.addActionListener(this);
        menuCliente.add(mntmNuevoCliente);

        mntmGestionarClientes = new JMenuItem("Gestionar Clientes",
            new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        mntmGestionarClientes.addActionListener(this);
        menuCliente.add(mntmGestionarClientes);

        // ================== MENU CATEGORIA ==================
        menuCategoria = new JMenu("Categoría");
        menuCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/categorias.png")));
        menuBar.add(menuCategoria);

        mntmNuevaCategoria = new JMenuItem("Nueva Categoría",
            new ImageIcon(FrmMenu.class.getResource("/img/nuevo.png")));
        mntmNuevaCategoria.addActionListener(this);
        menuCategoria.add(mntmNuevaCategoria);

        mntmGestionarCategorias = new JMenuItem("Gestionar Categorías",
            new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        mntmGestionarCategorias.addActionListener(this);
        menuCategoria.add(mntmGestionarCategorias);

        // ================== MENU FACTURAR ==================
        menuFacturar = new JMenu("Facturar");
        menuFacturar.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuFacturar.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/carrito.png")));
        menuBar.add(menuFacturar);

        mntmNuevaVenta = new JMenuItem("Nueva Venta",
            new ImageIcon(FrmMenu.class.getResource("/img/anadir.png")));
        menuFacturar.add(mntmNuevaVenta);

        mntmGestionarVentas = new JMenuItem("Gestionar Ventas",
            new ImageIcon(FrmMenu.class.getResource("/img/configuraciones.png")));
        menuFacturar.add(mntmGestionarVentas);

        // ================== MENU REPORTES ==================
        menuReportes = new JMenu("Reportes");
        menuReportes.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuReportes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/reporte1.png")));
        menuBar.add(menuReportes);

        mntmReportesClientes = new JMenuItem("Reportes Clientes",
            new ImageIcon(FrmMenu.class.getResource("/img/cliente.png")));
        menuReportes.add(mntmReportesClientes);

        mntmReportesCategorias = new JMenuItem("Reportes Categorías",
            new ImageIcon(FrmMenu.class.getResource("/img/categorias.png")));
        menuReportes.add(mntmReportesCategorias);

        mntmReportesProductos = new JMenuItem("Reportes Productos",
            new ImageIcon(FrmMenu.class.getResource("/img/nuevo-producto.png")));
        menuReportes.add(mntmReportesProductos);

        mntmReportesVentas = new JMenuItem("Reportes Ventas",
            new ImageIcon(FrmMenu.class.getResource("/img/carrito.png")));
        menuReportes.add(mntmReportesVentas);

        // ================== MENU HISTORIAL ==================
        menuHistorial = new JMenu("Historial");
        menuHistorial.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuHistorial.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/historial1.png")));
        menuBar.add(menuHistorial);

        mntmVerHistorial = new JMenuItem("Ver Historial",
            new ImageIcon(FrmMenu.class.getResource("/img/anadir.png")));
        menuHistorial.add(mntmVerHistorial);

        // ================== MENU CERRAR SESIÓN ==================
        menuCerrarSesion = new JMenu("Cerrar Sesión");
        menuCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
        menuCerrarSesion.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cerrar-sesion.png")));
        menuBar.add(menuCerrarSesion);

        mntmCerrarSesion = new JMenuItem("Cerrar Sesión",
            new ImageIcon(FrmMenu.class.getResource("/img/cerrar-sesion.png")));
        mntmCerrarSesion.addActionListener(this);
        menuCerrarSesion.add(mntmCerrarSesion);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		// Categorias
		if(e.getSource()== mntmNuevaCategoria) {
			InterCategoria interCategoria = new InterCategoria();
			jDesktopPane_menu.add(interCategoria);
			interCategoria.setVisible(true);
		}
		
		if(e.getSource() == mntmGestionarCategorias) {
			InterGestionarCategorias interGestionarCat = new InterGestionarCategorias();
			jDesktopPane_menu.add(interGestionarCat);
			interGestionarCat.setVisible(true);
		}
		
		
		//Productos 
		if(e.getSource() == mntmNuevoProductos) {
			InterProducto interProducto = new InterProducto();
			jDesktopPane_menu.add(interProducto);
			interProducto.setVisible(true);
		}
		
		if(e.getSource() == mntmGestionarProductos) {
			
			InterGestionarProducto interGesProducto = new InterGestionarProducto();
			jDesktopPane_menu.add(interGesProducto);
			interGesProducto.setVisible(true);
			
		}
		
		if(e.getSource() == mntmActualizarStock) {
			InterActualizarStock interStock = new InterActualizarStock();
			jDesktopPane_menu.add(interStock);
			interStock.setVisible(true);
		}
		
		//Clientes
		
		if(e.getSource() == mntmNuevoCliente) {
			InterCliente interCliente = new InterCliente();
			jDesktopPane_menu.add(interCliente);
			interCliente.setVisible(true);
			
		}
		
		if(e.getSource() == mntmGestionarClientes) {
			InterGestionarCliente interCliente = new InterGestionarCliente();
			jDesktopPane_menu.add(interCliente);
			interCliente.setVisible(true);
		}
		
		
		//cerrar sesion
		
		if(e.getSource() == mntmCerrarSesion) {
			
			FrmLogin login = new FrmLogin();
			login.setVisible(true);
			this.dispose();
			
		}
		
	}
}
