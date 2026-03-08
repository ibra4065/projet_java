package Pokemon_jeu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestSlots extends Canvas {

    private Carte dragon;
    private Image img;
    private Image imglogo;
    Dimension d = new Dimension(600, 850);
    private Rectangle slotA = new Rectangle(10, 10, 100, 130); 
    private Rectangle slotB = new Rectangle(125, 10, 100, 130);
    private Rectangle slotC = new Rectangle(240, 10, 100, 130);
    private Rectangle slotD = new Rectangle(355, 10, 100, 130);
    private Rectangle slotE = new Rectangle(470, 10, 100, 130);
    private Rectangle slotA2 = new Rectangle(150, 660, 100, 130); 
    private Rectangle slotB2 = new Rectangle(300, 660, 100, 130);
    private Rectangle slotC2 = new Rectangle(450, 660, 100, 130);
    private Rectangle barre = new Rectangle((d.width/2)-64, (d.height/2)-65, 120, 12);
    private Rectangle barre3 = new Rectangle((d.width/2)-64, (d.height/2)-65, 120, 12);
    private Rectangle barre2 = new Rectangle((d.width/2)-64, (d.height/2)-10, 120, 12);
    private Rectangle carteRect;   // position actuelle de la carte
    private boolean selectionnee;

    public TestSlots() {

        BibliothequeCartes b = new BibliothequeCartes();
        dragon = b.creerCarte("dragon");

        // si ton image est dans le projet : Pokemon/cartes/dragon.png
        img = Toolkit.getDefaultToolkit().getImage("cartes/dragon.png");
        imglogo = Toolkit.getDefaultToolkit().getImage("cartes/logo.png");
        // au début, la carte est dans slotA
        carteRect = new Rectangle(slotA.x, slotA.y, slotA.width, slotA.height);
        selectionnee = false;

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int mx = e.getX();
                int my = e.getY();

                // 1) clic sur la carte => sélection
                if (carteRect.contains(mx, my)) {
                    selectionnee = true;
                    System.out.println("selection : " + dragon.getNom());
                    repaint();
                    return;
                }

                // 2) si carte sélectionnée, clic sur slot => déplacer
                if (selectionnee) {

                    if (slotA.contains(mx, my)) {
                        deplacerCarteDans(slotA);
                        selectionnee = false;
                        repaint();
                        return;
                    }

                    if (slotB.contains(mx, my)) {
                        deplacerCarteDans(slotB);
                        selectionnee = false;
                        repaint();
                        return;
                    }
                    
                    if (slotC.contains(mx, my)) {
                        deplacerCarteDans(slotC);
                        selectionnee = false;
                        repaint();
                        return;
                    }
                }
            }
        });
    }

    private void deplacerCarteDans(Rectangle slot) {
        carteRect.x = slot.x;
        carteRect.y = slot.y;
        carteRect.width=slot.width;
        carteRect.height=slot.height;
        System.out.println("deplacement dans le slot");
    }

    public void paint(Graphics g) {
    	g.setColor(Color.red);
        g.fillRect(barre.x+1, barre.y, barre.width,barre.height);
        g.fillRect(barre2.x+1, barre2.y+1, barre2.width-110,barre2.height);
        // slots
        g.setColor(Color.BLACK);
        g.drawRect(slotA.x, slotA.y, slotA.width, slotA.height);
        g.drawRect(slotB.x, slotB.y, slotB.width, slotB.height);
        g.drawRect(slotC.x, slotC.y, slotC.width, slotC.height);
        g.drawRect(slotD.x, slotC.y, slotC.width, slotC.height);
        g.drawRect(slotE.x, slotC.y, slotC.width, slotC.height);
        g.drawRect(slotA2.x, slotA2.y, slotA2.width, slotA2.height);
        g.drawRect(slotB2.x, slotB2.y, slotB2.width, slotB2.height);
        g.drawRect(slotC2.x, slotC2.y, slotC2.width, slotC2.height);
        g.drawRect(barre.x, barre.y, barre.width,barre.height);
        g.drawRect(barre2.x, barre2.y, barre2.width,barre2.height);
        
        

        // carte (image)
        g.drawImage(img, carteRect.x, carteRect.y, carteRect.width, carteRect.height, this);
        g.drawImage(imglogo, (d.width/2)-30, (d.height/2)-55, 50, 50, this);
        // contour si sélectionnée
        if (selectionnee) {
            g.drawRect(carteRect.x - 2, carteRect.y - 2, carteRect.width + 4, carteRect.height + 4);
        }
    }

    public static void main(String[] args) {

        Frame f = new Frame("AWT Slots - Dragon");
        TestSlots canvas = new TestSlots();

        f.add(canvas);
        f.setSize(canvas.d.width, canvas.d.height);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.dispose();
                System.exit(0);
            }
        });
    }
}