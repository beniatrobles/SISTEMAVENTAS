package vista;

import javax.swing.*;
import java.awt.*;

public class DesktopConFondo extends JDesktopPane {

    private Image imagen;

    public DesktopConFondo(String rutaImagen) {
        // Cargar imagen desde el package "img"
        this.imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            // Dibuja la imagen escalada al tamaño actual
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
