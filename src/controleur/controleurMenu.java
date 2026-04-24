package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Pokemon;

public class controleurMenu implements ActionListener {

    private Pokemon fenetre;

    public controleurMenu(Pokemon fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("JOUER".equals(cmd)) {
            fenetre.demarrerPartie();
        }
    }
}