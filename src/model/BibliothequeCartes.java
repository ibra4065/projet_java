package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.Pouvoir.TypePouvoir;

/* ne pas changer la facon de gener les cartes 
 * cree un dictionnaire qui est presenter par le nom du perso comme clé 
 * et pour valeur un dictionnaire de caracteristique 
 * */

public class BibliothequeCartes {
	
	public class Caracteristique extends HashMap<String, Integer>{
		
		public Caracteristique(int pv, int attack,int defence) {
			super();
			this.put("pv", pv);
			this.put("attack", attack);
			this.put("defence", defence);
		}
		
	}
	
	public final static int tailledeck = 5;
	//private ArrayList<String> CatalogueCartes;
	private Map<String, Caracteristique> CatalogueCartes;
	private ArrayList<Carte> cartes;
	Pouvoir[] pouv;
	
	
	public BibliothequeCartes() {
		
	    CatalogueCartes = new HashMap<String, BibliothequeCartes.Caracteristique>();
	    CatalogueCartes.put("Dragon", new Caracteristique(75, 30, 20));
	    CatalogueCartes.put("Pretre",new Caracteristique(60 , 15, 15));
	    CatalogueCartes.put("Assassin",new Caracteristique(50, 25, 10));
	    CatalogueCartes.put("Guerrier",new Caracteristique(70, 20, 20));
	    CatalogueCartes.put("Archer",new Caracteristique(55, 18, 12));
	}
	
	
	public Carte creerCarte(String nom , Pouvoir p) {
		Carte c = new Carte(nom, CatalogueCartes.get(nom).get("pv"), CatalogueCartes.get(nom).get("attack"), CatalogueCartes.get(nom).get("defence"), p);

		
	    return c;
	}
	
	/*public Stack<Carte> creerDeckAleatoire(int tailleDeck) {

	    Stack<Carte> deck = new Stack<>();

	    Collections.shuffle(CatalogueCartes);

	    for (int i = 0; i < tailleDeck; i++) {
	        String nomCarte = CatalogueCartes.get(i);
	        deck.push(creerCarte(nomCarte)); 
	    }

	    return deck;
	}*/
}
