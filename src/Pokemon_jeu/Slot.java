package Pokemon_jeu;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Slot {

    private boolean libre;
    private Image img;
    private Carte carte;
    private Rectangle rect;
    private boolean select;

    public Slot() {
        this.libre = true;
        this.select = false;
    }

    public void remplirSlot(Carte c) {
        this.carte = c;
        this.img = Toolkit.getDefaultToolkit().getImage("cartes/" + c.getNom() + ".png");
        this.libre = false;
        this.select = false;
    }

    public void deplacerSlot(Slot s) {
        if (this.libre) return;
        if (!s.libre) return;

        s.carte = this.carte;
        s.img = this.img;
        s.libre = false;
        s.select = false;

        this.carte = null;
        this.img = null;
        this.libre = true;
        this.select = false;
    }

    public boolean isLibre() { return libre; }
    public void setLibre(boolean libre) { this.libre = libre; }

    public Image getImg() { return img; }
    public void setImg(Image img) { this.img = img; }

    public Carte getCarte() { return carte; }
    public void setCarte(Carte carte) { this.carte = carte; }

    public Rectangle getRect() { return rect; }
    public void setRect(Rectangle rect) { this.rect = rect; }

    public boolean isSelect() { return select; }
    public void setSelect(boolean select) { this.select = select; }
}