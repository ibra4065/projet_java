package model;

import java.util.Random;

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
    private boolean etat;
    private boolean Consommetour;
    private boolean utilisé;

    public Pouvoir(TypePouvoir type) {
        this.type = type;
        etat = true;
        utilisé=false;

        // 🔥 INITIALISATION AUTOMATIQUE
        switch(type) {

            case SOIN:
                valeur = 20;
                duree = 1;
                Consommetour=false;
                break;

            case CRITIQUE:
                valeur = 2; // x2 dégâts
                duree = 1;
                Consommetour=true;
                break;

            case BRULURE:
                valeur = 15;
                duree = 3;
                Consommetour=true;
                break;

            case POISON:
                valeur = 18;
                duree = 3;
                Consommetour=true;
                break;

            case BUFF_ATTAQUE:
                valeur = 10;
                duree = 3;
                Consommetour=false;
                break;

            case DEBUFF_ATTAQUE:
                valeur = 10;
                duree = 3;
                Consommetour=false;
                break;

            case BUFF_DEFENSE:
                valeur = 10;
                duree = 3;
                Consommetour=false;
                break;

            case DEBUFF_DEFENSE:
                valeur = 10;
                duree = 3;
                Consommetour=false;
                break;

            case PARALYSIE:
            	valeur = 2;
                duree = 3;
                Consommetour=false;
                break;

            case SOMMEIL:
                valeur = 2;
                duree = 1;
                Consommetour=false;
                break;

            case GEL:
                valeur = 3;
                duree = 1;
                Consommetour=false;
                break;
        }
    }

    /**
	 * @return the utilisé
	 */
	public boolean isUtilisé() {
		return utilisé;
	}

	/**
	 * @param utilisé the utilisé to set
	 */
	public void setUtilisé(boolean utilisé) {
		this.utilisé = utilisé;
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

	public boolean isConsommetour() {
		return Consommetour;
	}
    
    
}