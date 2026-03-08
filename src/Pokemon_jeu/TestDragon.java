package Pokemon_jeu;

import java.awt.*;
import java.awt.event.*;

public class TestDragon extends Panel {

    private Carte carte;
    private Image image;
    Image img;
    int x = 200;
    int y = 150;
    int w = 120;
    int h = 180;

    boolean bouger = false;

    public TestDragon() {

        BibliothequeCartes b = new BibliothequeCartes();
        carte = b.creerCarte("dragon");

        image = Toolkit.getDefaultToolkit().getImage("cartes/dragon.png");
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                int mx = e.getX();
                int my = e.getY();

                if (mx >= x && mx <= x + w &&
                    my >= y && my <= y + h) {

                    bouger = true;
                    System.out.println(carte.getNom());
                }
            }

            public void mouseReleased(MouseEvent e) {
                bouger = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {

                if (bouger) {
                    x = e.getX() - w/2;
                    y = e.getY() - h/2;

                    repaint();
                }
            }
        });
    }

    public void paint(Graphics g) {

        g.drawImage(image, x, y, w, h, this);
    }

    public static void main(String[] args) {

        Frame f = new Frame("Test Carte Dragon");

        TestDragon panel = new TestDragon();

        f.add(panel);
        f.setSize(600, 500);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}