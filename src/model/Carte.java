package model;

import java.util.Random;

import model.Pouvoir.TypePouvoir;
/* reussi a faire fonctionner les atack et les different pouvors*/
public class Carte {
	
    public static enum typeCarte {
        ACIER, COMBAT, DRAGON, EAU, ELECTRIK, FEE, FEU, GLACE,
        INSECTE, NORMAL, PLANTE, POISON, PSY, ROCHE, SOL,
        SPECTRE, TENEBRES, VOL
    }

    // MATRICE DES TYPES
    private static final double[][] TYPE_CHART = {
        //DEF →
        //          ACIER  COMBAT DRAGON  EAU  ELEC  FEE   FEU  GLACE INSECT NORMAL PLANTE POISON PSY ROCHE  SOL SPECT TENEB  VOL
        /*ACIER*/   {0.5,   2.0,   0.5,   1.0, 1.0,  0.5,  2.0,  0.5,  0.5,   0.5,  0.5,   0.0,   0.5, 0.5,  2.0, 1.0,  1.0,  0.5},
        /*COMBAT*/  {1.0,   1.0,   1.0,   1.0, 1.0,  2.0,  1.0,  1.0,  0.5,   1.0,  1.0,   1.0,   2.0, 0.5,  1.0, 1.0,  0.5,  2.0},
        /*DRAGON*/  {1.0,   1.0,   2.0,   0.5, 0.5,  2.0,  0.5,  2.0,  1.0,   1.0,  0.5,   1.0,   1.0, 1.0,  1.0, 1.0,  1.0,  1.0},
        /*EAU*/     {0.5,   1.0,   1.0,   0.5, 2.0,  1.0,  0.5,  0.5,  1.0,   1.0,  2.0,   1.0,   1.0, 1.0,  1.0, 1.0,  1.0,  1.0},
        /*ELECTR*/  {0.5,   1.0,   1.0,   2.0, 0.5,  1.0,  1.0,  1.0,  1.0,   1.0,  0.5,   1.0,   1.0, 1.0,  2.0, 1.0,  1.0,  0.5},
        /*FEE*/     {2.0,   0.5,   0.0,   1.0, 1.0,  1.0,  1.0,  1.0,  0.5,   1.0,  0.5,   2.0,   1.0, 1.0,  1.0, 1.0,  0.5,  1.0},
        /*FEU*/     {0.5,   1.0,   1.0,   2.0, 1.0,  0.5,  0.5,  0.5,  0.5,   1.0,  0.5,   1.0,   1.0, 2.0,  2.0, 1.0,  1.0,  1.0},
        /*GLACE*/   {2.0,   2.0,   1.0,   1.0, 1.0,  1.0,  1.0,  0.5,  1.0,   1.0,  1.0,   1.0,   1.0, 2.0,  1.0, 1.0,  1.0,  1.0},
        /*INSECT*/  {1.0,   0.5,   1.0,   1.0, 1.0,  2.0,  2.0,  1.0,  1.0,   1.0,  0.5,   1.0,   2.0, 1.0,  0.5, 1.0,  1.0,  2.0},
        /*NORMAL*/  {1.0,   2.0,   1.0,   1.0, 1.0,  1.0,  1.0,  1.0,  1.0,   1.0,  1.0,   1.0,   1.0, 1.0,  1.0, 0.0,  1.0,  1.0},
        /*PLANTE*/  {1.0,   1.0,   1.0,   0.5, 2.0,  1.0,  2.0,  2.0,  2.0,   1.0,  0.5,   2.0,   1.0, 0.5,  0.5, 1.0,  1.0,  2.0},
        /*POISON*/  {1.0,   0.5,   1.0,   1.0, 1.0,  0.5,  1.0,  1.0,  0.5,   1.0,  0.5,   0.5,   2.0, 1.0,  2.0, 1.0,  1.0,  1.0},
        /*PSY*/     {1.0,   0.5,   1.0,   1.0, 1.0,  2.0,  1.0,  1.0,  2.0,   1.0,  1.0,   1.0,   0.5, 1.0,  1.0, 2.0,  2.0,  1.0},
        /*ROCHE*/   {0.5,   2.0,   1.0,   2.0, 1.0,  1.0,  0.5,  1.0,  0.5,   0.5,  2.0,   0.5,   1.0, 1.0,  2.0, 1.0,  1.0,  0.5},
        /*SOL*/     {0.5,   1.0,   1.0,   2.0, 0.0,  1.0,  1.0,  2.0,  1.0,   1.0,  2.0,   0.5,   1.0, 0.5,  1.0, 1.0,  1.0,  1.0},
        /*SPECT*/   {1.0,   0.0,   1.0,   1.0, 1.0,  1.0,  1.0,  1.0,  0.5,   0.0,  1.0,   0.5,   1.0, 1.0,  1.0, 2.0,  2.0,  1.0},
        /*TENEB*/   {1.0,   2.0,   1.0,   1.0, 1.0,  2.0,  1.0,  1.0,  2.0,   1.0,  1.0,   1.0,   0.5, 1.0,  1.0, 0.5,  0.5,  1.0},
        /*VOL*/     {1.0,   0.5,   1.0,   1.0, 2.0,  1.0,  1.0,  2.0,  0.5,   1.0,  0.5,   1.0,   1.0, 2.0,  0.0, 1.0,  1.0,  1.0}
    };
    

    private final String nom;
    private int pv;
    private final int pvmax;
    private typeCarte type;
    private final Pouvoir pouvoir;

    // Stats de base
    private final int attackBase;
    private final int defanceBase;

    // Modificateurs temporaires
    private int bonusAttack;
    private int malusAttack;
    private int bonusDefance;
    private int malusDefance;

    // Durées des modificateurs
    private int toursBonusAttack;
    private int toursMalusAttack;
    private int toursBonusDefance;
    private int toursMalusDefance;

    // Statuts
    private boolean ko;
    private int toursParalysie;
    private int toursSommeil;
    private int toursGel;

    public Carte(String nom, int pv, int attack, int defance, Pouvoir pouvoir, typeCarte type) {
        this.nom = nom;
        this.pv = pv;
        this.pvmax = pv;
        this.attackBase = attack;
        this.defanceBase = defance;
        this.pouvoir = pouvoir;
        this.type = type;

        this.bonusAttack = 0;
        this.malusAttack = 0;
        this.bonusDefance = 0;
        this.malusDefance = 0;

        this.toursBonusAttack = 0;
        this.toursMalusAttack = 0;
        this.toursBonusDefance = 0;
        this.toursMalusDefance = 0;

        this.ko = false;
        this.toursParalysie = 0;
        this.toursSommeil = 0;
        this.toursGel = 0;
    }

 

    public void attaquer(Carte cible) {
        if (cible == null || !this.isUtilisable()) {
            return;
        }

        int base = this.getAttack() - cible.getDefance();
        if (base < 0) {
            base = 0;
        }

        double multi = TYPE_CHART[this.type.ordinal()][cible.type.ordinal()];
        int degat = (int) Math.round(base * multi);

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
        if (this.pouvoir == null || !this.pouvoir.isEtat() || !this.isUtilisable()) {
            return;
        }

        TypePouvoir t = this.pouvoir.getType();

        switch (t) {
            case SOIN:
                soin();
                break;

            case BRULURE:
                if (cible != null) brulure(cible);
                break;

            case CRITIQUE:
                if (cible != null) critique(cible);
                break;

            case BUFF_ATTAQUE:
                buffAtt(cible);
                break;

            case DEBUFF_ATTAQUE:
                if (cible != null) debuffAtt(cible);
                break;

            case BUFF_DEFENSE:
                buffDef();
                break;

            case DEBUFF_DEFENSE:
                if (cible != null) debuffDef(cible);
                break;

            case POISON:
                if (cible != null) poison(cible);
                break;

            case PARALYSIE:
                if (cible != null) paralysie(cible);
                break;

            case SOMMEIL:
                if (cible != null) sommeil(cible);
                break;

            case GEL:
                if (cible != null) gel(cible);
                break;

            default:
                break;
        }
        
        this.pouvoir.setUtilisé(true);

        if (cible != null) {
            cible.verifierKO();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // même objet
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false; 
        }

        Carte other = (Carte) obj;

        return this.nom != null && this.nom.equals(other.nom);
    }



	private void consommerPouvoir() {
        int d = this.pouvoir.getDuree();

        if (d > 1) {
            this.pouvoir.setDuree(d - 1);
        } else {
            this.pouvoir.setDuree(0);
            this.pouvoir.setEtat(false);
        }
    }

    private void soin() {
        this.pv += this.pouvoir.getValeur();

        if (this.pv > this.pvmax) {
            this.pv = this.pvmax;
        }

        consommerPouvoir();
    }

    private void brulure(Carte cible) {
        cible.pv -= this.pouvoir.getValeur();

        if (cible.pv < 0) {
            cible.pv = 0;
        }

        consommerPouvoir();
    }

    private void critique(Carte cible) {
        int degat = this.getAttack() - cible.getDefance();

        if (degat < 0) {
            degat = 0;
        }

        cible.pv -= degat * this.pouvoir.getValeur();
        
        if (cible.pv < 0) {
            cible.pv = 0;
        }

        consommerPouvoir();
    }

    private void buffAtt(Carte cible) {
        this.bonusAttack = this.pouvoir.getValeur();
        this.toursBonusAttack = this.pouvoir.getDuree();
        this.pouvoir.setEtat(false);
    }

    private void debuffAtt(Carte cible) {
        cible.malusAttack = this.pouvoir.getValeur();
        cible.toursMalusAttack = this.pouvoir.getDuree();
        this.pouvoir.setEtat(false);
    }

    private void buffDef() {
        this.bonusDefance = this.pouvoir.getValeur();
        this.toursBonusDefance = this.pouvoir.getDuree();
        this.pouvoir.setEtat(false);
    }
    // le buff/debuff est un pouvoir que on poura l'utiliser q'une seul fois pendant un nombre de tour consecutif  
    private void debuffDef(Carte cible) {
        cible.malusDefance = this.pouvoir.getValeur();
        cible.toursMalusDefance = this.pouvoir.getDuree();
        this.pouvoir.setEtat(false);
    }

    private void poison(Carte cible) {
        cible.pv -= this.pouvoir.getValeur();

        if (cible.pv < 0) {
            cible.pv = 0;
        }

        consommerPouvoir();
    }

    private void paralysie(Carte cible) {
    	/*Random rand = new Random();
    	boolean chance = rand.nextBoolean(); // 1 chance sur 2 que la carte adverse soit paralysé
    	if (chance) {
    		
    	}*/
    	cible.toursParalysie += this.pouvoir.getValeur();
        consommerPouvoir();
    }

    private void sommeil(Carte cible) {
        cible.toursSommeil = this.pouvoir.getValeur();
        consommerPouvoir();
    }

    private void gel(Carte cible) {
        cible.toursGel = this.pouvoir.getValeur();
        consommerPouvoir();
    }

    private void verifierKO() {
        if (this.pv <= 0) {
            this.pv = 0;
            this.ko = true;
        }
    }

    public void diminuerStatuts() {
        if (this.toursParalysie > 0) {
            this.toursParalysie--;
        }

        if (this.toursSommeil > 0) {
            this.toursSommeil--;
        }

        if (this.toursGel > 0) {
            this.toursGel--;
        }

        if (this.toursBonusAttack > 0) {
            this.toursBonusAttack--;
            if (this.toursBonusAttack == 0) {
                this.bonusAttack = 0;
            }
        }

        if (this.toursMalusAttack > 0) {
            this.toursMalusAttack--;
            if (this.toursMalusAttack == 0) {
                this.malusAttack = 0;
            }
        }

        if (this.toursBonusDefance > 0) {
            this.toursBonusDefance--;
            if (this.toursBonusDefance == 0) {
                this.bonusDefance = 0;
            }
        }

        if (this.toursMalusDefance > 0) {
            this.toursMalusDefance--;
            if (this.toursMalusDefance == 0) {
                this.malusDefance = 0;
            }
        }
    }

    public boolean isUtilisable() {
        return !ko && toursParalysie == 0 && toursSommeil == 0 && toursGel == 0;
    }

    public boolean isKO() {
        return ko;
    }

    public boolean isParalyse() {
        return toursParalysie > 0;
    }

    public boolean isEndormi() {
        return toursSommeil > 0;
    }

    public boolean isGele() {
        return toursGel > 0;
    }

    public int getAttack() {
        int att = attackBase + bonusAttack - malusAttack;
        return Math.max(att, 0);
    }

    public int getDefance() {
        int def = defanceBase + bonusDefance - malusDefance;
        return Math.max(def, 0);
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
        verifierKO();
    }

    public Pouvoir getPouvoir() {
        return pouvoir;
    }

    public int getPvmax() {
        return pvmax;
    }

    public typeCarte getType() {
        return type;
    }

    public int getAttackBase() {
        return attackBase;
    }

    public int getDefanceBase() {
        return defanceBase;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public int getMalusAttack() {
        return malusAttack;
    }

    public int getBonusDefance() {
        return bonusDefance;
    }

    public int getMalusDefance() {
        return malusDefance;
    }

    public int getToursBonusAttack() {
        return toursBonusAttack;
    }

    public int getToursMalusAttack() {
        return toursMalusAttack;
    }

    public int getToursBonusDefance() {
        return toursBonusDefance;
    }

    public int getToursMalusDefance() {
        return toursMalusDefance;
    }

    public int getToursParalysie() {
        return toursParalysie;
    }

    public int getToursSommeil() {
        return toursSommeil;
    }

    public int getToursGel() {
        return toursGel;
    }
}