package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.Carte.typeCarte;
import model.Pouvoir.TypePouvoir;

public class BibliothequeCartes {

    public class Caracteristique {

        int pv;
        int attack;
        int defence;
        typeCarte type;
        TypePouvoir pouvoir;

        public Caracteristique(int pv, int attack, int defence, typeCarte type, TypePouvoir pouvoir) {
            this.pv = pv;
            this.attack = attack;
            this.defence = defence;
            this.type = type;
            this.pouvoir = pouvoir;
        }
        
    }

    private Map<String, Caracteristique> CatalogueCartes;

    public BibliothequeCartes() {

        CatalogueCartes = new HashMap<>();

        CatalogueCartes.put("Dracaufeu",new Caracteristique(170, 55, 80, typeCarte.FEU, TypePouvoir.BRULURE));
        CatalogueCartes.put("Pikachu", new Caracteristique(70, 15, 10, typeCarte.ELECTRIK, TypePouvoir.PARALYSIE));
        CatalogueCartes.put("Feuillajou",new Caracteristique(70, 15, 10, typeCarte.PLANTE, TypePouvoir.BUFF_ATTAQUE));
        CatalogueCartes.put("Magicarpe",new Caracteristique(30, 5, 5, typeCarte.EAU, TypePouvoir.SOIN));
        CatalogueCartes.put("Qulbutoké",new Caracteristique(120, 30, 50, typeCarte.PSY, TypePouvoir.DEBUFF_ATTAQUE));
        CatalogueCartes.put("Salamèche",new Caracteristique(70, 15, 10, typeCarte.FEU, TypePouvoir.BRULURE));
        CatalogueCartes.put("Goupix",new Caracteristique(60, 10, 10, typeCarte.FEU, TypePouvoir.BRULURE));
        CatalogueCartes.put("Diancie",new Caracteristique(90, 20, 35, typeCarte.ROCHE, TypePouvoir.BUFF_DEFENSE));
        CatalogueCartes.put("Miaouss",new Caracteristique(60, 10, 10, typeCarte.NORMAL, TypePouvoir.CRITIQUE));
    }

    public Carte creerCarte(String nom) {

        Caracteristique c = CatalogueCartes.get(nom);

        return new Carte(
            nom,
            c.pv,
            c.attack,
            c.defence,
            new Pouvoir(c.pouvoir),
            c.type
        );
    }

    public Stack<Carte> creerDeckAleatoire(int tailleDeck) {

        Stack<Carte> deck = new Stack<>();
        ArrayList<String> personnages = new ArrayList<>(CatalogueCartes.keySet());
        Collections.shuffle(personnages);

        for (int i = 0; i < tailleDeck; i++) {
            String nom = personnages.get(i % personnages.size());
            deck.push(creerCarte(nom));
        }

        return deck;
    }
}