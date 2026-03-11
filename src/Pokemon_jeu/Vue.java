package Pokemon_jeu;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Vue extends Canvas implements Observer{
	

	
	Modele model;
	Controleur cntrl;
	private final int largeur=100; // largeur de la carte 
	private final int longeur = 130; // longeur de la carte 
	private Slot S1;
	private Slot S2;
	private ArrayList<Slot> main_J1;
	private ArrayList<Slot> main_J2;
	private Rectangle barre_pvJ1;
	private Rectangle barre_pvJ2;
	private Rectangle carte_selectionée; 
	
	public Vue(Modele model, Controleur cntrl) {
		this.model = model;
		this.cntrl = cntrl;
		main_J1=new ArrayList<>();
		main_J2=new ArrayList<>();
		dim = new Dimension(600, 850);
		for (int i=0;i<this.model.getJ1().getMain().size();i++) {
			Slot s1 = new Slot();
			Slot s2 = new Slot();
			s1.remplirSlot(model.getJ1().getMain().get(i));
			s2.remplirSlot(model.getJ2().getMain().get(i));
			main_J1.add(s1);
			main_J2.add(s2);
			
		}
		
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
		repaint();
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}


}
