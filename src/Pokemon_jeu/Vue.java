package Pokemon_jeu;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Vue extends Canvas implements Observer{
	
	public class slot{

		boolean libre;
		private Image img;
		private Carte carte;
		private Rectangle rect;
		
		public slot(Rectangle rect) {
			this.rect = rect;
			this.libre=true;
		}
		
		public boolean isLibre() {return libre;}
		public void setLibre(boolean libre) {this.libre = libre;}
		public Image getImg() {return img;}
		public void setImg(Image img) {this.img = img;}
		public Carte getCarte() {return carte;}
		public void setCarte(Carte carte) {this.carte = carte;}
		public Rectangle getRect() {return rect;}
		public void setRect(Rectangle rect) {this.rect = rect;}
		
		
		
	}
	
	Modele model;
	Controleur cntrl;
	private final int largeur=100; // largeur de la carte 
	private final int longeur = 130; // longeur de la carte 
	private slot S1;
	private slot S2;
	private ArrayList<slot> main_J1;
	private ArrayList<slot> main_J2;
	private Rectangle barre_pvJ1;
	private Rectangle barre_pvJ2;
	private Rectangle carte_selectionée; 
	
	public Vue(Modele model, Controleur cntrl) {
		this.model = model;
		this.cntrl = cntrl;
		main_J1=new ArrayList<Vue.slot>();
		main_J2=new ArrayList<Vue.slot>();
		dim = new Dimension(600, 850);
		
	}

	private Dimension dim;
	
	
    
	
	private void deplacerCarteDans(Rectangle slot,Rectangle carte) {
        carte.x = slot.x;
        carte.y = slot.y;
        System.out.println("deplacement dans le slot");
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
