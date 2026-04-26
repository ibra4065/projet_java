package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Modele;
import view.Slot;

public class Controleur implements ActionListener {

    private Modele model;
    private Slot carteSelectionnee;

    public Controleur(Modele model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof Slot) {
            Slot slotClique = (Slot) source;

            if (model.modeSelection()) {

                if (!slotClique.isSlot_plt() && slotClique.getCarte() != null) {
                    carteSelectionnee = slotClique;
                    System.out.println("Carte sélectionnée : " + slotClique.getCarte().getNom());
                    return;
                }

                if (slotClique.isSlot_plt()) {
                    if (carteSelectionnee == null) {
                        System.out.println("Aucune carte sélectionnée");
                        return;
                    }

                    if (carteSelectionnee.getProprietaire() == slotClique.getProprietaire()) {
                        model.poserCarteDebut(carteSelectionnee.getProprietaire(), carteSelectionnee.getCarte());
                        carteSelectionnee = null;
                    } else {
                        System.out.println("Tu dois poser la carte sur ton propre plateau");
                    }
                    return;
                }
            }

            if (model.getCourant() != null && model.getCourant().isModechange()) {
                if (!slotClique.isSlot_plt()
                        && slotClique.getProprietaire() == model.getCourant()
                        && slotClique.getCarte() != null) {
                    model.changementCarte(model.getCourant(), slotClique.getCarte());
                }
                return;
            }

            return;
        }

        String cmd = e.getActionCommand();
        if (cmd == null) return;

        switch (cmd) {
            case "ATTAQUER_J1":
                model.JoueurAttack(model.getJ1());
                break;

            case "POUVOIR_J1":
                model.UtilisePouvoir(model.getJ1());
                break;

            case "CHANGER_J1":
                model.activerModeChangement(model.getJ1());
                break;

            case "ATTAQUER_J2":
                model.JoueurAttack(model.getJ2());
                break;

            case "POUVOIR_J2":
                model.UtilisePouvoir(model.getJ2());
                break;

            case "CHANGER_J2":
                model.activerModeChangement(model.getJ2());
                break;
                
            case "PIOCHER_INIT_J1":
                model.piocherinit(model.getJ1());;
                break;
            case "PIOCHER_INIT_J2":
                model.piocherinit(model.getJ2());;
                System.out.println("pioche faite!");
                break;
                
            case "piocherJ1":
            	model.piocher(model.getJ1());
            	break;
            case "piocherJ2":
            	model.piocher(model.getJ2());
            	break;
            case "passerJ1":
            	model.passertour();
            	break;
            case "passerJ2":
            	model.passertour();
            	break;
            case "rejouer":
            	model.reset();
            	break;
        }
    }
}