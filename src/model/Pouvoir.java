package model;

public class Pouvoir {
    
    public static enum TypePouvoir {
        SOIN,
        BRULURE,
        BUFF_ATTAQUE,
        CRITIQUE,
        DEBUFF_ATTAQUE,
        PARALYSIE,
        POISON,
        SOMMEIL,
        GEL,
        BUFF_DEFENSE,
        DEBUFF_DEFENSE
    }
    
    private TypePouvoir type;
    private int valeur;
    private int duree;
    boolean etat;

    public Pouvoir(TypePouvoir type) {
        this.type = type;
        this.etat = true;

        // 🔥 INITIALISATION AUTOMATIQUE
        switch(type) {

            case SOIN:
                valeur = 20;
                duree = 1;
                break;

            case CRITIQUE:
                valeur = 2; // x2 dégâts
                duree = 1;
                break;

            case BRULURE:
                valeur = 5;
                duree = 3;
                break;

            case POISON:
                valeur = 5;
                duree = 3;
                break;

            case BUFF_ATTAQUE:
                valeur = 10;
                duree = 3;
                break;

            case DEBUFF_ATTAQUE:
                valeur = 10;
                duree = 3;
                break;

            case BUFF_DEFENSE:
                valeur = 10;
                duree = 3;
                break;

            case DEBUFF_DEFENSE:
                valeur = 10;
                duree = 3;
                break;

            case PARALYSIE:
                valeur = 1;
                duree = 2;
                break;

            case SOMMEIL:
                valeur = 1;
                duree = 2;
                break;

            case GEL:
                valeur = 1;
                duree = 2;
                break;
        }
    }

    public int getValeur() {
        return valeur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public TypePouvoir getType() {
        return type;
    }

    public void setType(TypePouvoir type) {
        this.type = type;
    }
}