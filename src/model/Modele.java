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
	
	public enum Mode{Normal,IA};
	
	private int tour;
	private Etat_partie etat;
	private BibliothequeCartes biblio;
	private Joueur J1;
	private Joueur J2;
	private Joueur courant;
	private boolean piocheFaite;
	
	public Modele() {
		this.biblio=new BibliothequeCartes();
		J1 = new Joueur("J1", biblio.creerDeckAleatoire(3));
		J2 = new Joueur("J2", biblio.creerDeckAleatoire(5));
		tour=0;
		this.etat=Etat_partie.DEBUT;
		J1.initJoueurAdverse(J2);
		J2.initJoueurAdverse(J1);
		piocheFaite = false;
	}
	
	


	public boolean modeSelection() {
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
	    for (Carte c :courant.getMain()) {
        	c.diminuerStatuts();
        }
	    courant.getPersonnageActif().diminuerStatuts();
	    
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
			setChanged();
		    notifyObservers(this.etat);

		}
	}
	
	
	
	public void poserCarteDebut(Joueur j, Carte c) {
	    if (!modeSelection()) return;
	    if (j == null || c == null) return;
	    if (j.getPersonnageActif() != null) return;
	    if (!j.getMain().contains(c)) return;

	    j.poserCarte(c);
	    verifierPassageEnCours();

	    setChanged();
	    notifyObservers();
	}
	
	/*une methode qui permet a un joueur de changer une carte*/
	
	public void changementCarte(Joueur j, Carte c) {
	    if (etat != Etat_partie.EN_COURS) return;
	    if (j == null || c == null) return;
	    if (j != courant) return;
	    if (!j.isModechange()) return;
	    if (!j.getMain().contains(c)) return;
	    j.ChangerCarte(c);
	    if (j.getPersonnageActif().isUtilisable()) {
	    	j.setChanger_encours(1);
	    	j.setModechange(false);
	    }
	    else {
	    	j.setModechange(false);
	    	
	    }
	    
	    verifierFin(j);
	    if (etat == Etat_partie.EN_COURS) {
	        prochainJoueur();
	    }

	    setChanged();
	    notifyObservers();
	}
	
	public void JoueurAttack(Joueur j) {
	    if (etat != Etat_partie.EN_COURS) return;
	    if (j != courant) return;
	    if (j.getPersonnageActif() == null) return;
	    if (j.getAdverse().getPersonnageActif() == null) return;
	    if (!j.getPersonnageActif().isUtilisable()) return;

	    j.getPersonnageActif().attaquer(j.getAdverse().getPersonnageActif());

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
	    verifierFin(j);
	    
	    if (etat == Etat_partie.EN_COURS && j.getPersonnageActif().getPouvoir().isConsommetour()) prochainJoueur();
	    
	    
	    setChanged();
	    notifyObservers();	
	    
	    
	    
	}
	
	public void piocher(Joueur j) {
		if (etat != Etat_partie.EN_COURS) return;
		if (courant == null) return;
		if (piocheFaite) return;
		j.pioche();
		piocheFaite=true;
		
		setChanged();
	    notifyObservers();
	    
	}
	
	public void reset() {
	    this.biblio = new BibliothequeCartes();
	    J1 = new Joueur("J1", biblio.creerDeckAleatoire(5));
	    J2 = new Joueur("J2", biblio.creerDeckAleatoire(5));
	    J1.initJoueurAdverse(J2);
	    J2.initJoueurAdverse(J1);
	    tour = 0;
	    etat = Etat_partie.DEBUT;
	    courant = null;
	    piocheFaite = false;

	    setChanged();
	    notifyObservers();
	}
	
	public void activerModeChangement(Joueur j) {
	    if (etat != Etat_partie.EN_COURS) return;
	    if (j == null) return;
	    if (j != courant) return;
	    if (j.getPersonnageActif() == null) return;
	    if (j.getPersonnageActif().isUtilisable() && j.getChanger_encours()<=0) return;
	    

	    j.setModechange(true);

	    setChanged();
	    notifyObservers();
	}
	
	public void passertour() {
		prochainJoueur();
		setChanged();
	    notifyObservers();
	}
	
	public boolean peutChangerCarte(Joueur j) {
	    if (j == null) return false;
	    if (etat != Etat_partie.EN_COURS) return false;
	    if (!j.isMonTour()) return false;
	    if (j.getPersonnageActif() == null) return false;

	    return !j.getPersonnageActif().isUtilisable() || j.getChanger_encours()>0;
	}
	
	public boolean peutUtiliserPouvoir(Joueur j) {
	    if (j == null) return false;
	    if (etat != Etat_partie.EN_COURS) return false;
	    if (!j.isMonTour()) return false;
	    if (j.getPersonnageActif() == null) return false;
	    if (j.getAdverse() == null || j.getAdverse().getPersonnageActif() == null) return false;
	    if (!j.getPersonnageActif().isUtilisable()) return false;
	    if (j.getPersonnageActif().getPouvoir() == null) return false;

	    return j.getPersonnageActif().getPouvoir().isEtat();
	}
	
	public boolean peutAttaquer(Joueur j) {
	    if (j == null) return false;
	    if (etat != Etat_partie.EN_COURS) return false;
	    if (!j.isMonTour()) return false;
	    if (j.getPersonnageActif() == null) return false;
	    if (j.getAdverse() == null || j.getAdverse().getPersonnageActif() == null) return false;

	    return j.getPersonnageActif().isUtilisable();
	}
	
	public void piocherinit(Joueur j) {
		j.piocherMainInitiale();
		setChanged();
	    notifyObservers();
	}
	
	public boolean peutpasser(Joueur j) {
		if (j.getPersonnageActif()==null) return false;
		if (etat != Etat_partie.EN_COURS) return false;
		if (!j.isMonTour()) return false;
		return !j.getPersonnageActif().isUtilisable() && !j.getPersonnageActif().isKO();
	}
	
	public int getTour() {return tour;}
	public Joueur getJ1() {return J1;}
	public Joueur getJ2() {return J2;}
	public Joueur getCourant() {return courant;}
	public Etat_partie getEtat() {return etat;}
	public void setEtat(Etat_partie etat) {this.etat = etat;}
	
	
	

}
