package game;

import javax.swing.JFrame;

import controleur.Controleur;
import controleur.controleurMenu;
import model.Modele;
import view.VueMenu;
import view.VuePartie;

public class Pokemon extends JFrame {

    private Modele model;
    private Controleur cntrl;
    private controleurMenu controleurMenu;

    private VuePartie vuePartie;
    private VueMenu vueMenu;

    public Pokemon() {
        super("Jeu de cartes Pokemon");

        controleurMenu = new controleurMenu(this);
        vueMenu = new VueMenu(controleurMenu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(vueMenu);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void demarrerPartie() {
        model = new Modele();
        cntrl = new Controleur(model);
        vuePartie = new VuePartie(model, cntrl);

        model.addObserver(vuePartie);

        setContentPane(vuePartie);
        setLocationRelativeTo(null);
        pack();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new Pokemon();
    }
}