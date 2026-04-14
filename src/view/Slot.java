package view;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Carte;
import model.Joueur;

public class Slot extends JButton {

    private boolean libre;
    private ImageIcon imgcarte;
    private Carte carte;
    private boolean select;
    private final boolean slot_plt;
    private final Joueur proprietaire;

    private final Dimension dimbtn = new Dimension(100, 130);

    // slot du plateau
    public Slot(Joueur proprietaire) {
        this.proprietaire = proprietaire;
        this.libre = true;
        this.select = false;
        this.slot_plt = true;

        setText("poser ici");
        setPreferredSize(dimbtn);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    // slot de la main
    public Slot(Carte c, Joueur proprietaire) {
        this.slot_plt = false;
        this.proprietaire = proprietaire;
        this.libre = true;
        this.select = false;

        setFocusPainted(false);
        setPreferredSize(dimbtn);
        setBorderPainted(false);
        setContentAreaFilled(false);

        setCarte(c);
    }

    public void remplirSlot(Carte c) {
        setCarte(c);
        select = false;
    }

    public void deplacerCarte(Slot s) {
        if (this.libre) return;
        if (!s.libre) return;

        s.setCarte(this.carte);
        this.viderSlot();
    }

    private void viderSlot() {
        libre = true;
        select = false;
        carte = null;
        imgcarte = null;

        setIcon(null);

        if (slot_plt) {
            setText("poser ici");
        } else {
            setText(null);
        }

        repaint();
    }

    public void setCarte(Carte c) {
        if (carte == c || (carte != null && carte.equals(c))) return;

        carte = c;

        if (c == null) {
            imgcarte = null;
            setIcon(null);
            libre = true;

            if (slot_plt) {
                setText("poser ici");
            } else {
                setText(null);
            }

            repaint();
            return;
        }

        ImageIcon icon = new ImageIcon("cartes/" + c.getNom() + ".jpg");
        Image imgRedim = icon.getImage().getScaledInstance(100, 130, Image.SCALE_SMOOTH);

        imgcarte = new ImageIcon(imgRedim);
        setIcon(imgcarte);
        setText(null);

        libre = false;
        repaint();
    }

    public boolean isLibre() {
        return libre;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Carte getCarte() {
        return carte;
    }

    public boolean isSlot_plt() {
        return slot_plt;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }
}