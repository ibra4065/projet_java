package model;

import model.Pouvoir.TypePouvoir;

public class Carte {
    
    public enum typeCarte {
        ACIER, COMBAT, DRAGON, EAU, ELECTRIK, FEE, FEU, GLACE,
        INSECTE, NORMAL, PLANTE, POISON, PSY, ROCHE, SOL,
        SPECTRE, TENEBRES, VOL
    }

    // 🔥 MATRICE DES TYPES
    private static final double[][] TYPE_CHART = {

    //DEF →
    // ACIER COMBAT DRAGON EAU ELEC FEE FEU GLACE INSECT NORMAL PLANTE POISON PSY ROCHE SOL SPECT TENEB VOL


    /*ACIER*/   {0.5,2.0,0.5,1.0,1.0,0.5,2.0,0.5,0.5,0.5,0.5,0.0,0.5,0.5,2.0,1.0,1.0,0.5},
    /*COMBAT*/  {1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,2.0},
    /*DRAGON*/  {1.0,1.0,2.0,1.0,1.0,2.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0},
    /*EAU*/     {1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0},
    /*ELECTRIK*/{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0},
    /*FEE*/     {2,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    /*FEU*/     {1,1,1,2,1,1,1,1,1,1,1,1,1,2,2,1,1,1},
    /*GLACE*/   {2,2,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1},
    /*INSECTE*/ {1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,2},
    /*NORMAL*/  {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
    /*PLANTE*/  {1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,2},
    /*POISON*/  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    /*PSY*/     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    /*ROCHE*/   {2,2,1,2,1,1,1,1,1,1,2,1,1,1,2,1,1,1},
    /*SOL*/     {1,1,1,2,0,1,2,1,1,1,2,1,1,1,1,1,1,1},
    /*SPECTRE*/ {1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,2,1},
    /*TENEBRES*/{1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    /*VOL*/     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}
    };

    private final String nom;
    private int pv;
    private typeCarte type;
    private int attack;
    private final int defance;
    private final int pvmax;
    private Pouvoir pouvoir;
    private boolean utilisable;

    public Carte(String nom, int pv, int atack, int defance, Pouvoir pouvoir, typeCarte type) {
        this.nom = nom;
        this.pv = pv;
        this.pvmax = pv;
        this.attack = atack;
        this.defance = defance;
        this.pouvoir = pouvoir;
        this.utilisable = true;
        this.type = type;
    }

    public Carte(String nom, int pv, int atack, int defance, typeCarte type) {
        this.nom = nom;
        this.pv = pv;
        this.pvmax = pv;
        this.attack = atack;
        this.defance = defance;
        this.pouvoir = null;
        this.utilisable = true;
        this.type = type;
    }

    /*a changer apres avoir fini le type et potentielment pouvoir*/
    public void attaquer(Carte cible) {

        int base = this.attack - cible.defance;
        if (base < 0) base = 0;

        double multi = TYPE_CHART[this.type.ordinal()][cible.type.ordinal()];

        //int degat = (int)(base * multi); // conversion en int à la fin
        int degat = (int)Math.round(base * multi);

        if (multi > 1) {
            System.out.println("Super efficace !");
        } else if (multi > 0 && multi < 1) {
            System.out.println("Pas très efficace...");
        } else if (multi == 0) {
            System.out.println("Aucun effet...");
        }

        cible.pv -= degat;
        cible.verifierKO();
    }

    public void utiliserPouvoir(Carte cible) {
        if (this.pouvoir == null || !this.pouvoir.isEtat()) return;

        TypePouvoir t = this.pouvoir.getType();

        if (t == TypePouvoir.SOIN) {
            this.pv += this.pouvoir.getValeur();
            if (this.pv > this.pvmax) this.pv = this.pvmax;
            this.pouvoir.setEtat(false);
        }
        else if (t == TypePouvoir.BRULURE) {
            brulure(cible);
        }
        else if (t == TypePouvoir.CRITIQUE) {
            critique(cible);
        }
        else if (t == TypePouvoir.BUFF_ATTAQUE) {
            buffAtt(cible);
        }
    }

    private void brulure(Carte cible) {
        cible.pv -= this.pouvoir.getValeur();
        if (cible.pv < 0) cible.pv = 0;

        int d = this.pouvoir.getDuree();
        if (d > 0) this.pouvoir.setDuree(d - 1);
        if (this.pouvoir.getDuree() == 0) this.pouvoir.setEtat(false);

        cible.verifierKO();
    }

    private void critique(Carte cible) {
        int degat = this.attack - cible.defance;
        if (degat < 0) degat = 0;

        cible.pv -= degat * this.pouvoir.getValeur();
        if (cible.pv < 0) cible.pv = 0;

        this.pouvoir.setEtat(false);
        cible.verifierKO();
    }

    private void buffAtt(Carte cible) {
        int attackTemp = this.attack + this.pouvoir.getValeur();
        int degat = attackTemp - cible.defance;
        if (degat < 0) degat = 0;

        cible.pv -= degat;
        if (cible.pv < 0) cible.pv = 0;

        int d = this.pouvoir.getDuree();
        if (d > 0) this.pouvoir.setDuree(d - 1);
        if (this.pouvoir.getDuree() == 0) this.pouvoir.setEtat(false);

        cible.verifierKO();
    }

    private void verifierKO() {
        if (this.pv <= 0) {
            this.pv = 0;
            this.utilisable = false;
        }
    }

    
    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public void setPv(int pv) { this.pv = pv; }
    public boolean isUtilisable() { return utilisable; }
    public int getAttack() { return attack; }
    public int getDefance() { return defance; }
    public Pouvoir getPouvoir() { return pouvoir; }
    public int getPvmax() { return pvmax; }
}