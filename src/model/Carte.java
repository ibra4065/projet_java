package model;

import model.Pouvoir.TypePouvoir;

public class Carte {
	
	public enum typeCarte {
	    ACIER,
	    COMBAT,
	    DRAGON,
	    EAU,
	    ELECTRIK,
	    FEE,
	    FEU,
	    GLACE,
	    INSECTE,
	    NORMAL,
	    PLANTE,
	    POISON,
	    PSY,
	    ROCHE,
	    SOL,
	    SPECTRE,
	    TENEBRES,
	    VOL
	}
	
	private final String nom;
	private int pv;
	private typeCarte type;
	private  int attack;
	private final int defance;
	private final int pvmax;
	private Pouvoir pouvoir;
	private boolean utilisable;
	
	public Carte(String nom, int pv, int atack, int defance, Pouvoir pouvoir,typeCarte type) {
		this.nom = nom;
		this.pv = pv;
		this.pvmax=pv;
		this.attack = atack;
		this.defance = defance;
		this.pouvoir = pouvoir;
		this.utilisable=true;
		this.type=type;
	}



	public Carte(String nom, int pv, int atack, int defance) {
		super();
		this.nom = nom;
		this.pv = pv;
		this.pvmax=pv;
		this.attack = atack;
		this.pouvoir = null;
		this.defance=defance;
		this.utilisable=true;
	}
	


	public void attaquer(Carte cible) {
	    int degat = this.attack - cible.defance;
	    if (degat < 0) degat = 0;

	    cible.pv -= degat;
	    cible.verifierKO();   
	}

	    public void utiliserPouvoir(Carte cible) {
	        if (this.pouvoir == null) return;

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

	        // ✅ décrémente puis désactive si durée terminée
	        int d = this.pouvoir.getDuree();
	        if (d > 0) {
	            this.pouvoir.setDuree(d - 1);
	        }
	        if (this.pouvoir.getDuree() == 0) {
	            this.pouvoir.setEtat(false);
	        }

	        cible.verifierKO();
	    }

	    private void critique(Carte cible) {
	        int degat = this.attack - cible.defance;
	        if (degat < 0) degat = 0;

	        int multiplicateur = this.pouvoir.getValeur(); // ex: 2
	        cible.pv -= degat * multiplicateur;
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

	        // ✅ décrémente puis désactive si durée terminée
	        int d = this.pouvoir.getDuree();
	        if (d > 0) {
	            this.pouvoir.setDuree(d - 1);
	        }
	        if (this.pouvoir.getDuree() == 0) {
	            this.pouvoir.setEtat(false);
	        }

	        cible.verifierKO();
	    }
	    
	    private void verifierKO() {
	        if (this.pv <= 0) {
	            this.pv = 0;
	            this.utilisable = false;
	        }
	    }


		public String getNom() {return nom;}
		public int getPv() {return pv;}
		public void setPv(int pv) {this.pv = pv;}
		public void setUtilisable(boolean utilisable) {this.utilisable = utilisable;}
		public boolean isUtilisable() {return utilisable;}
		public int getAttack() {return attack;}
		public int getDefance() {return defance;}
		public Pouvoir getPouvoir() {return pouvoir;}
		public int getPvmax() {return pvmax;}

		
	
	
}
