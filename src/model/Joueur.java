package model;

import java.util.ArrayList;
import java.util.Stack;



public class Joueur {
	
	public enum EtatJoueur {GAGNE,PERDU,EN_VIE}
	private EtatJoueur etat;
	private final String nom;
	private ArrayList<Carte> main;
	private Carte personnageActif;
	private Stack<Carte> deck;
	private boolean montour;
	private final int indice_plateau;
	private Joueur adverse;
	
	 

	public Joueur(String nom,Stack<Carte> deck,int ind) {
		this.nom = nom;
		this.main = new ArrayList<Carte>();
		this.deck=deck;
		this.montour=false;
		this.indice_plateau=ind;
		etat=EtatJoueur.EN_VIE;
		
	}
	



	public void pioche() {
		if (!deck.isEmpty()) {
			Carte c = deck.pop();
			this.main.add(c);
		}
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
	        
	        if (personnageActif != null && personnageActif.isUtilisable()) main.add(personnageActif);

	        main.remove(c);
	        personnageActif = c;
	    }
	}
	
	public void initJoueurAdverse(Joueur j) {
		this.adverse=j;
	}
	
	public boolean joueurAPerdu() {
	    if (personnageActif == null) return main.isEmpty() && deck.isEmpty();
	    return main.isEmpty() && deck.isEmpty() && !personnageActif.isUtilisable();
	}
	
	
	
	


	public String getNom() { return nom; }
	public int getIndice_plateau() {return indice_plateau;}
	public ArrayList<Carte> getMain() { return main; }
	public Stack<Carte> getDeck() { return deck; }
	public boolean isMonTour() { return montour; }
	public Carte getPersonnageActif() {return personnageActif;}
	public void setMontour(boolean montour) {this.montour = montour;}
	public Joueur getAdverse() {return adverse;}
	public EtatJoueur getEtat() {return etat;}
	public void setEtat(EtatJoueur etat) {this.etat = etat;}

	
}
