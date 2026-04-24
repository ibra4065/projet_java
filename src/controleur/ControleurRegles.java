package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VueRegles;

public class ControleurRegles implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        VueRegles fenetreRegles = new VueRegles();
        fenetreRegles.setVisible(true);
    }
}