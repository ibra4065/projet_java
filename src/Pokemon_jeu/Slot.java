package Pokemon_jeu;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Slot extends JButton {

    private boolean libre;
    private Image img;
    private ImageIcon imgcarte;
    private Carte carte;
    private boolean select;
	private final Dimension dimbtn=new Dimension(100,130);

    public Slot() {
        this.libre = true;
        this.select = false;
    }
    
    public Slot(Carte c) {
    	this.setPreferredSize(dimbtn);
    	this.setFocusPainted(false);
    	this.carte = c;
    	this.img = Toolkit.getDefaultToolkit().getImage("cartes/" + c.getNom() + ".png");
    	Image imgRedim = img.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
    	imgcarte=new ImageIcon(imgRedim);
        this.libre = false;
        this.select = false;
    	this.setIcon(imgcarte);
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
    
    public void viderSlot() {
    	libre=true;
    	select=false;
    	img=null;
    	carte=null;
    }

    public boolean isLibre() { return libre; }
    public void setLibre(boolean libre) { this.libre = libre; }

    public Image getImg() { return img; }
    public void setImg(Image img) { this.img = img; }

    public Carte getCarte() { return carte; }
    public void setCarte(Carte carte) { this.carte = carte; }


    public boolean isSelect() { return select; }
    public void setSelect(boolean select) { this.select = select; }
}