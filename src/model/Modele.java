package model;

import java.util.Observable;

import model.Joueur.EtatJoueur;

public class Modele extends Observable{
	
	/*
	 * une boiblioteque qui genere un deck de cartes pour chaque joueur
	 * 2 joueur qui s'affront dans un plateau de carte de taille 2 
	 * */
	

	
	public enum Etat_partie{
		
		DEBUT,
		EN_COURS,
		FIN
		
	};
	
	private int tour;
	private final int taillPlateau=2;
	private Etat_partie etat;
	private BibliothequeCartes biblio;
	private Joueur J1;
	private Joueur J2;
	private Joueur courant;
	private Carte[] plateau;
	private boolean piocheFaite;
	
	public Modele() {
		this.biblio=new BibliothequeCartes();
		/*J1 = new Joueur("J1", biblio.creerDeckAleatoire(BibliothequeCartes.tailledeck),0);
		J2 = new Joueur("J2", biblio.creerDeckAleatoire(BibliothequeCartes.tailledeck),1);*/
		plateau = new Carte[taillPlateau];
		tour=0;
		this.etat=Etat_partie.DEBUT;
		J1.piocherMainInitiale();
		J2.piocherMainInitiale();
		J1.initJoueurAdverse(J2);
		J2.initJoueurAdverse(J1);
		piocheFaite = false;
	}
	
	
	private void majPlateau() {
	    plateau[0] = J1.getPersonnageActif();
	    plateau[1] = J2.getPersonnageActif();
	}
	

	private boolean modeSelection() {
	    return etat == Etat_partie.DEBUT;
	    // ou 
	    // return (J1.getPersonnageActif() == null || J2.getPersonnageActif() == null);
	}
	
	
	private void prochainJoueur() {
		if (etat != Etat_partie.EN_COURS) return;
	    if (courant == J1) {
	        J1.setMontour(false);
	        J2.setMontour(true);
	        courant = J2;
	    } else {
	        J2.setMontour(false);
	        J1.setMontour(true);
	        courant = J1;
	    }
	    tour++;
	    piocheFaite=false;
	}
	
	private boolean verifierPassageEnCours() {
	    if (J1.getPersonnageActif() != null && J2.getPersonnageActif() != null) {
	        etat = Etat_partie.EN_COURS;
	        courant = J1;      // ou random
	        J1.setMontour(true);
	        J2.setMontour(false);
	        tour = 1;
	        piocheFaite = false;
	        return true;
	    }
	    return false;
	}
	
	private void verifierFin(Joueur j) {
		if (j == null || j.getAdverse() == null) return;
		if (j.getAdverse().joueurAPerdu()) {
			j.setEtat(EtatJoueur.GAGNE);
			j.getAdverse().setEtat(EtatJoueur.PERDU);
			etat=Etat_partie.FIN;

		}
	}
	
	
	
	public void poserCarteDebut(Joueur j, Carte c) {
	    if (!modeSelection()) return;

	    j.poserCarte(c);
	    majPlateau();                 

	    verifierPassageEnCours();     

	    setChanged();                 
	    notifyObservers();
	}
	
	/*une methode qui permet a un joueur de changer une carte*/
	
	public void changementCarte(Joueur j, Carte c) {
	    if (etat != Etat_partie.EN_COURS) return;
	    if (j != courant) return;

	    j.changerPersonnage(c);
	    majPlateau();

	    setChanged();
	    notifyObservers();
	}
	
	public void JoueurAttack(Joueur j) {
	    if (etat != Etat_partie.EN_COURS) return;
	    if (j != courant) return;
	    if (j.getPersonnageActif() == null) return;
	    if (j.getAdverse().getPersonnageActif()== null) return;
	    if (!j.getPersonnageActif().isUtilisable()) return;
	    if (!j.getAdverse().getPersonnageActif().isUtilisable()) return;
	    courant.getPersonnageActif().attaquer(courant.getAdverse().getPersonnageActif());
	    majPlateau();
	    verifierFin(j);
	    if (etat == Etat_partie.EN_COURS) prochainJoueur();

	    setChanged();
	    notifyObservers();
	}

	public void UtilisePouvoir(Joueur j) {
	    if (etat != Etat_partie.EN_COURS) return;
	    if (j != courant) return;
	    if (j.getPersonnageActif() == null) return;
	    if (j.getAdverse().getPersonnageActif() == null) return;
	    if (!j.getPersonnageActif().isUtilisable()) return;
	    if (j.getPersonnageActif().getPouvoir() == null) return;
	    if (!j.getPersonnageActif().getPouvoir().isEtat()) return;

	    j.getPersonnageActif().utiliserPouvoir(j.getAdverse().getPersonnageActif());
	    majPlateau();
	    verifierFin(j); 
	    if (etat == Etat_partie.EN_COURS) prochainJoueur();
	   
	    

	    setChanged();
	    notifyObservers();
	}
	
	public void piocher() {
		if (etat != Etat_partie.EN_COURS) return;
		if (courant == null) return;
		if (piocheFaite) return;
		courant.pioche();
		piocheFaite=true;
		
		setChanged();
	    notifyObservers();
	}
	

	
	public int getTour() {return tour;}
	public Joueur getJ1() {return J1;}
	public Joueur getJ2() {return J2;}
	public Joueur getCourant() {return courant;}
	public Carte[] getPlateau() {return plateau;}
	public Etat_partie getEtat() {return etat;}
	public void setEtat(Etat_partie etat) {this.etat = etat;}
	
	

}
