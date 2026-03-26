package Pokemon_jeu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/* class qui la vue des main de chaque joueur */
public class Vue extends JPanel implements Observer{
	

	Dimension dim = new Dimension(600,800);
	Modele model;
	Controleur cntrl;
	
	private final Image plt;
	private Slot slot_J1;
	private Slot Slot_J2;
	private ArrayList<Slot> CartesJ1;
	private ArrayList<Slot> CartesJ2;
	JPanel ActionJ1;
	JPanel ActionJ2;
	JPanel mainJ1;
	JPanel mainJ2;
	JPanel btnsJ1;
	JPanel btnJ2;
	
	public Vue(Modele model, Controleur cntrl) {
		super();
		setPreferredSize(dim);
        setLayout(null);
        
		this.model = model;
		this.cntrl = cntrl;
		
		CartesJ1=new ArrayList<>();
		CartesJ1=new ArrayList<>();
		
		mainJ1=new JPanel();
		mainJ2=new JPanel();
		btnsJ1=new JPanel(); // Slot J1 pour poser sa carte 
		btnJ2 = new JPanel();// Slot J2 pour poser sa carte 
		ActionJ1=new JPanel();
		ActionJ2 = new JPanel();
		
		mainJ1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, model.getJ1().getMain().size()));
        mainJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));
		mainJ2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, model.getJ2().getMain().size()));
        mainJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        plt=Toolkit.getDefaultToolkit().getImage("cartes/logo.png");
        
		for (int i=0;i<this.model.getJ1().getMain().size();i++) {
			Slot s1 = new Slot(model.getJ1().getMain().get(i));
			Slot s2 = new Slot(model.getJ2().getMain().get(i));
			CartesJ1.add(s1);
			mainJ1.add(s1);
			CartesJ2.add(s2);
			mainJ2.add(s2);
			s1.addActionListener(this.cntrl);
			s2.addActionListener(this.cntrl);	
		}
		for (int i=0;i<this.model.getJ2().getMain().size();i++) {
			Slot s2 = new Slot(model.getJ2().getMain().get(i));
			CartesJ2.add(s2);
			mainJ2.add(s2);
			s2.addActionListener(this.cntrl);	
		}
		
		slot_J1 = new Slot();
		slot_J1.setBounds((dim.width-112)/2, (dim.height-130)/2 + 85, 100, 130);
		slot_J1.setMargin(new Insets(0, 0, 0, 0));
        slot_J1.setFocusPainted(false);
        slot_J1.addActionListener(this.cntrl);
        
		Slot_J2 = new Slot();
		slot_J1.setBounds((dim.width-112)/2, (dim.height-130)/2 - 125, 100, 130);
		Slot_J2.setMargin(new Insets(0, 0, 0, 0));
        Slot_J2.setFocusPainted(false);
        Slot_J2.addActionListener(this.cntrl);
        
		add(mainJ1,BorderLayout.SOUTH);
		add(mainJ2,BorderLayout.NORTH);
		add(slot_J1);
		add(Slot_J2);
		
	}

	
	/* a modifier les rectangle du pv au fonction du pv du la carte actif*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        int centreY = height / 2;

        int barreWidth = 120;
        int barreHeight = 12;

        int x = (width - barreWidth) / 2;

        int y1 = centreY - 35;
        int y2 = centreY + 20;

        g.setColor(Color.green);
        g.fillRect(x, y1, barreWidth, barreHeight);
        g.fillRect(x, y2, barreWidth, barreHeight);

        g.setColor(Color.BLACK);
        g.drawRect(x, y1, barreWidth, barreHeight);
        g.drawRect(x, y2, barreWidth, barreHeight);

        g.drawImage(plt,
                (width - 50) / 2,
                (height - 50) / 2,
                50,
                50,
                this
        );
    }


















	@Override
	public void update(Observable o, Object arg) {
		repaint();
		
	}




}
