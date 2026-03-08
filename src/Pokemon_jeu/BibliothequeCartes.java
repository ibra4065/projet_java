package Pokemon_jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import Pokemon_jeu.Pouvoir.TypePouvoir;

public class BibliothequeCartes {
	
	public final static int tailledeck = 5;
	private ArrayList<String> CatalogueCartes;
	
	public BibliothequeCartes() {
	    CatalogueCartes = new ArrayList<>();

	    CatalogueCartes.add("Dragon");
	    CatalogueCartes.add("Pretre");
	    CatalogueCartes.add("Assassin");
	    CatalogueCartes.add("Guerrier");
	    CatalogueCartes.add("Archer");
	}
	
	
	public Carte creerCarte(String nom) {

	    if (nom.toLowerCase().equals("dragon")) {
	        return new Carte("Dragon", 75, 30, 20,
	                new Pouvoir(TypePouvoir.BRULURE, 5, 2));
	    } 

	    if (nom.toLowerCase().equals("pretre")) {
	        return new Carte("Pretre", 60, 15, 15,
	                new Pouvoir(TypePouvoir.SOIN, 15, 0));
	    }

	    if (nom.toLowerCase().equals("assassin")) {
	        return new Carte("Assassin", 50, 25, 10,
	                new Pouvoir(TypePouvoir.CRITIQUE, 2, 0));
	    }

	    if (nom.toLowerCase().equals("guerrier")) {
	        return new Carte("Guerrier", 70, 20, 20,
	                new Pouvoir(TypePouvoir.BUFF_ATTAQUE, 10, 2));
	    }

	    if (nom.toLowerCase().equals("archer")) {
	        return new Carte("Archer", 55, 18, 12);
	    }

	    return null; // 
	}
	
	public Stack<Carte> creerDeckAleatoire(int tailleDeck) {

	    Stack<Carte> deck = new Stack<>();

	    Collections.shuffle(CatalogueCartes);

	    for (int i = 0; i < tailleDeck; i++) {
	        String nomCarte = CatalogueCartes.get(i);
	        deck.push(creerCarte(nomCarte)); 
	    }

	    return deck;
	}
}
