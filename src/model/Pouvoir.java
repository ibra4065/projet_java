package model;


public class Pouvoir {
	
	public enum TypePouvoir {
	    SOIN,
	    BRULURE,
	    BUFF_ATTAQUE,
	    CRITIQUE,
	    DEBUFF_ATTAQUE
	    
	}
	
	private TypePouvoir type;
	private int valeur;
	private int duree;
	boolean etat;
	
	/**
	 * @return the valeur
	 */
	public int getValeur() {
		return valeur;
	}


	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}


	/**
	 * @return the durée
	 */
	public int getDuree() {
		return duree;
	}


	/**
	 * @param durée the durée to set
	 */
	public void setDuree(int durée) {
		this.duree = durée;
	}


	
	
	
	public Pouvoir(TypePouvoir type, int valeur, int durée) {
		this.type = type;
		this.valeur = valeur;
		this.duree = durée;
		this.etat=true;
		
	}


	/**
	 * @return the etat
	 */
	public boolean isEtat() {
		return etat;
	}


	/**
	 * @param etat the etat to set
	 */
	public void setEtat(boolean etat) {
		this.etat = etat;
	}


	/**
	 * @return the type
	 */
	public TypePouvoir getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(TypePouvoir type) {
		this.type = type;
	}
	
	
	
	
	
	
}
