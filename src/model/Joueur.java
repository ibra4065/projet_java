package model;

import java.util.ArrayList;
import java.util.Stack;



public class Joueur {
	
	public enum EtatJoueur {GAGNE,PERDU,EN_VIE}
	private EtatJoueur etat;
	private final String nom;
	private ArrayList<Carte> main;
	private Carte personnageActif;
	/**
	 * @return the modechange
	 */
	public boolean isModechange() {
		return modechange;
	}


	/**
	 * @param modechange the modechange to set
	 */
	public void setModechange(boolean modechange) {
		this.modechange = modechange;
	}
	private Stack<Carte> deck;
	private boolean montour;
	private boolean modechange;
	private Joueur adverse;
	private int changer_encours;
	
	 

	public Joueur(String nom,Stack<Carte> deck) {
		this.nom = nom;
		this.main = new ArrayList<Carte>();
		this.deck=deck;
		this.montour=false;
		etat=EtatJoueur.EN_VIE;
		modechange=false;
		changer_encours=3;
	}
	

	/**
	 * @return the changer_encours
	 */
	public int getChanger_encours() {
		return changer_encours;
	}


	/**
	 * @param changer_encours the changer_encours to set
	 */
	public void setChanger_encours(int changer_encours) {
		if (this.changer_encours==1) {
			this.changer_encours=0;
		}
		else this.changer_encours -= changer_encours;
		
	}


	public void pioche() {
		if (deck.isEmpty())return;
		main.add(deck.pop());
	}
	
	public void piocherMainInitiale() {
	    for (int i = 0; i < 3; i++) {
	        pioche();
	    }
	}
	
	public void poserCarte(Carte c) {
	    if (personnageActif == null && main.contains(c)) {
	        main.remove(c);
	        personnageActif = c;
	    }
	}
	
	public void ChangerCarte(Carte c) {
	    if (main.contains(c)) {
	        
	        if (personnageActif != null && !personnageActif.isKO()) main.add(personnageActif);

	        main.remove(c);
	        personnageActif = c;
	    }
	}
	
	public void initJoueurAdverse(Joueur j) {
		this.adverse=j;
	}
	
	public boolean joueurAPerdu() {
	    if (personnageActif == null) return main.isEmpty() && deck.isEmpty();
	    return main.isEmpty() && deck.isEmpty() && personnageActif.isKO();
	}
	
	
	


	public String getNom() { return nom; }
	public ArrayList<Carte> getMain() { return main; }
	public Stack<Carte> getDeck() { return deck; }
	public boolean isMonTour() { return montour; }
	public Carte getPersonnageActif() {return personnageActif;}
	public void setMontour(boolean montour) {this.montour = montour;}
	public Joueur getAdverse() {return adverse;}
	public EtatJoueur getEtat() {return etat;}
	public void setEtat(EtatJoueur etat) {this.etat = etat;}

	
}
