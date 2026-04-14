package model;

import javax.swing.JFrame;

import controleur.Controleur;
import view.VuePartie;

public class Pokemon extends JFrame {

    private Modele model;
    private Controleur cntrl;
    private VuePartie vue;

    public Pokemon() {
        super("Jeu de cartes Pokemon");

        // MVC
        model = new Modele();
        cntrl = new Controleur(model);
        vue = new VuePartie(model, cntrl);

        // liaison Observer
        model.addObserver(vue);

        // fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(vue);
        pack();
        setLocationRelativeTo(null); // centre la fenêtre
        setVisible(true);
    }

    public static void main(String[] args) {
        new Pokemon();
    }
}