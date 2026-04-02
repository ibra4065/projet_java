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
			this.put("PV", pv);
			this.put("Attack", attack);
			this.put("Def", defence);
		}
		
	}
	
	public final static int tailledeck = 5;
	//private ArrayList<String> CatalogueCartes;
	private Map<String, Caracteristique> CatalogueCartes;
	private ArrayList<Carte> cartes;
	Pouvoir[] pouv;
	
	
	public BibliothequeCartes() {
		
	    CatalogueCartes = new HashMap<String, BibliothequeCartes.Caracteristique>();
	    CatalogueCartes.put("Dracaufeu", new Caracteristique(170, 55 , 80));
	    CatalogueCartes.put("Pikachu",new Caracteristique(70 , 15, 10));
	    CatalogueCartes.put("Feuillajou",new Caracteristique(70, 15, 10));
	    CatalogueCartes.put("Magicarpe",new Caracteristique(30, 5, 5));
	    CatalogueCartes.put("Qulbutoké",new Caracteristique(120, 30, 50));
	    CatalogueCartes.put("Salamèche", new Caracteristique(70, 15, 10));
	    CatalogueCartes.put("Goupix", new Caracteristique(60, 10, 10));
	    CatalogueCartes.put("Diancie", new Caracteristique(90, 20, 35));
	    CatalogueCartes.put("Miaouss", new Caracteristique(60, 10, 10));
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
