package boundary;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PannelloSfondo extends JPanel {

    private Image sfondo;
    public PannelloSfondo(String percorsoImmagine) {

        URL url = getClass().getResource(percorsoImmagine);

        if (url != null) {

            sfondo = new ImageIcon(url).getImage();

        } else {

            System.out.println("Immagine non trovata: " + percorsoImmagine);

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (sfondo != null) {

            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);

        }

    }

}