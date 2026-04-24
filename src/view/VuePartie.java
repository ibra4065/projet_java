package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controleur.Controleur;
import model.Carte;
import model.Joueur;
import model.Modele;
import model.Modele.Etat_partie;

/* 
 * au debut de la partie chaque affichage 2 buttons piocher init pour chaque joueur 
 * gerer jusqqu'a la fin de la partie 
 * afficher le gagnant et button de rejouer
 * */
public class VuePartie extends JPanel implements Observer{
	

	private Dimension dim = new Dimension(1000,800);
	private Modele model;
	private Controleur cntrl;
	
	private Image plt;
	
	// sud
	JPanel ActionJ1; 
	JPanel mainJ1;
	JPanel btnsJ1;
	
	// nord
	JPanel ActionJ2; 
	JPanel btnsJ2;
	JPanel mainJ2;
	
	
	// centre 
	JPanel PlateauCombat; 
	JPanel panelCombatCentre;
    JPanel panelSlotJ1;
    JPanel panelMilieu;
    JPanel panelSlotJ2;
    
    JButton attaquerJ1;
    JButton utiliserpouvoirJ1;
    JButton changerCarteJ1;
    JButton piocherJ1;
    JButton passerJ1;
    
    JButton attaquerJ2;
    JButton utiliserpouvoirJ2;
    JButton changerCarteJ2;
    JButton piocherJ2;
    JButton passerJ2;

    JProgressBar barreJ1;
    JProgressBar barreJ2;
    
	Slot slot_J1;
	Slot slot_J2;
	
	JLabel logoLabel;
	
	JPanel panelFin;
	JLabel labelGagnant;
	JButton btnRejouer;
	JButton btnQuitterFin;
	
	
	
	
	public VuePartie(Modele model, Controleur cntrl) {
		super();
		setPreferredSize(dim);
        setLayout(new BorderLayout());
        
		this.model = model;
		this.cntrl = cntrl;
		
		creerZoneJ1();
		creerPanelFin();
		creerZoneCentre();
		
		creerZoneJ2();
		
        add(ActionJ1, BorderLayout.SOUTH);
        add(PlateauCombat, BorderLayout.CENTER);
        add(ActionJ2, BorderLayout.NORTH);
        
		

        refresh();

	}
	
	private void refresh() {

	    updatePlateau();
	    updateMainJ1();
	    updateMainJ2();
	    updateButtons();
	    updateFinPartie();

	    revalidate();
	    repaint();
	}
	
	
	private void creerZoneJ1() {
		
	    ActionJ1 = new JPanel();
	    ActionJ1.setLayout(new BoxLayout(ActionJ1, BoxLayout.Y_AXIS));

	    btnsJ1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
	    mainJ1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
	    
	    mainJ1.setPreferredSize(new Dimension(1000, 150));
	    mainJ1.setMinimumSize(new Dimension(1000, 150));
	    mainJ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

	    attaquerJ1 = new JButton("Attaquer");
	    attaquerJ1.setActionCommand("ATTAQUER_J1");

	    utiliserpouvoirJ1 = new JButton("Utiliser pouvoir");
	    utiliserpouvoirJ1.setActionCommand("POUVOIR_J1");

	    changerCarteJ1 = new JButton("Changer");
	    changerCarteJ1.setActionCommand("CHANGER_J1");
	    
	    piocherJ1 = new JButton("piocher");
	    piocherJ1.setActionCommand("piocherJ1");
	    
	    passerJ1 = new JButton("passer");
	    passerJ1.setActionCommand("passerJ1");
	    
	    attaquerJ1.addActionListener(cntrl);
	    utiliserpouvoirJ1.addActionListener(cntrl);
	    changerCarteJ1.addActionListener(cntrl);
	    piocherJ1.addActionListener(cntrl);
	    passerJ1.addActionListener(cntrl);


	    btnsJ1.add(attaquerJ1);
	    btnsJ1.add(utiliserpouvoirJ1);
	    btnsJ1.add(changerCarteJ1);
	    btnsJ1.add(piocherJ1);
	    btnsJ1.add(passerJ1);


	    ActionJ1.add(btnsJ1);
	    ActionJ1.add(mainJ1);
	}
	
	
	private void creerPanelFin() {
	    panelFin = new JPanel();
	    panelFin.setLayout(new BoxLayout(panelFin, BoxLayout.Y_AXIS));
	    panelFin.setOpaque(false);
	    panelFin.setVisible(false);

	    labelGagnant = new JLabel("", JLabel.CENTER);
	    labelGagnant.setFont(new Font("SansSerif", Font.BOLD, 32));
	    labelGagnant.setForeground(Color.RED);
	    labelGagnant.setAlignmentX(Component.CENTER_ALIGNMENT);

	    btnRejouer = new JButton("Rejouer");
	    btnRejouer.setFont(new Font("SansSerif", Font.PLAIN, 22));
	    btnRejouer.setAlignmentX(Component.CENTER_ALIGNMENT);

	    btnQuitterFin = new JButton("Quitter");
	    btnQuitterFin.setFont(new Font("SansSerif", Font.PLAIN, 22));
	    btnQuitterFin.setAlignmentX(Component.CENTER_ALIGNMENT);

	    btnRejouer.addActionListener(e -> model.reset());

	    btnQuitterFin.addActionListener(e -> System.exit(0));

	    panelFin.add(labelGagnant);
	    panelFin.add(Box.createRigidArea(new Dimension(0, 20)));
	    panelFin.add(btnRejouer);
	    panelFin.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelFin.add(btnQuitterFin);
	}
	private void updateFinPartie() {
	    if (model.getEtat() == Etat_partie.FIN) {

	        String gagnant = "";

	        if (model.getJ1().getEtat() == Joueur.EtatJoueur.GAGNE) {
	            gagnant = "Joueur 1 a gagné !";
	        } 
	        else if (model.getJ2().getEtat() == Joueur.EtatJoueur.GAGNE) {
	            gagnant = "Joueur 2 a gagné !";
	        } 
	        else {
	            gagnant = "Partie terminée !";
	        }

	        labelGagnant.setText(gagnant);

	        panelCombatCentre.setVisible(false);
	        panelFin.setVisible(true);

	        attaquerJ1.setEnabled(false);
	        utiliserpouvoirJ1.setEnabled(false);
	        changerCarteJ1.setEnabled(false);
	        piocherJ1.setEnabled(false);
	        passerJ1.setEnabled(false);

	        attaquerJ2.setEnabled(false);
	        utiliserpouvoirJ2.setEnabled(false);
	        changerCarteJ2.setEnabled(false);
	        piocherJ2.setEnabled(false);
	        passerJ2.setEnabled(false);

	    } else {
	        panelCombatCentre.setVisible(true);
	        panelFin.setVisible(false);
	    }
	}
	
	private void updateMainJ1() {

	    mainJ1.removeAll();

	    if (model.getEtat() == Etat_partie.DEBUT && model.getJ1().getMain().isEmpty())  {
	        JButton piocherinit = new JButton("piocher");
	        piocherinit.setActionCommand("PIOCHER_INIT_J1");
	        piocherinit.addActionListener(cntrl);
	        mainJ1.add(piocherinit);
	    }
	    else {
	        for (Carte c : model.getJ1().getMain()) {
	            Slot s = new Slot(c,model.getJ1());
	            s.addActionListener(cntrl);
	            mainJ1.add(s);
	        }
	    }

	    mainJ1.revalidate();
	    mainJ1.repaint();
	}
	
	private void creerZoneJ2() {
	    ActionJ2 = new JPanel();
	    ActionJ2.setLayout(new BoxLayout(ActionJ2, BoxLayout.Y_AXIS));

	    btnsJ2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
	    mainJ2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
	    
	    mainJ2.setPreferredSize(new Dimension(1000, 150));
	    mainJ2.setMinimumSize(new Dimension(1000, 150));
	    mainJ2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
	    
	    attaquerJ2 = new JButton("Attaquer");
	    attaquerJ2.setActionCommand("ATTAQUER_J2");

	    utiliserpouvoirJ2 = new JButton("Utiliser pouvoir");
	    utiliserpouvoirJ2.setActionCommand("POUVOIR_J2");

	    changerCarteJ2 = new JButton("Changer");
	    changerCarteJ2.setActionCommand("CHANGER_J2");
	    
	    piocherJ2 = new JButton("piocher");
	    piocherJ2.setActionCommand("piocherJ2");
	    
	    passerJ2 = new JButton("passer");
	    passerJ2.setActionCommand("passerJ2");

	    attaquerJ2.addActionListener(cntrl);
	    utiliserpouvoirJ2.addActionListener(cntrl);
	    changerCarteJ2.addActionListener(cntrl);
	    piocherJ2.addActionListener(cntrl);
	    passerJ2.addActionListener(cntrl);

	    btnsJ2.add(attaquerJ2);
	    btnsJ2.add(utiliserpouvoirJ2);
	    btnsJ2.add(changerCarteJ2);
	    btnsJ2.add(piocherJ2);
	    btnsJ2.add(passerJ2);
	    
	    ActionJ2.add(mainJ2);
	    ActionJ2.add(btnsJ2);
	    
	}
	
	private void updateMainJ2() {
		
	    mainJ2.removeAll();

	    if (model.getEtat() == Etat_partie.DEBUT && model.getJ2().getMain().isEmpty()) {
	        JButton piocherinit = new JButton("piocher");
	        piocherinit.setActionCommand("PIOCHER_INIT_J2");
	        piocherinit.addActionListener(cntrl);
	        mainJ2.add(piocherinit);
	    }
	    else {
	        for (Carte c : model.getJ2().getMain()) {
	            Slot s = new Slot(c,model.getJ2());
	            s.addActionListener(cntrl);
	            mainJ2.add(s);
	        }
	    }

	    mainJ2.revalidate();
	    mainJ2.repaint();
	    
	}
	
	private void creerZoneCentre() {
        PlateauCombat = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));

        panelCombatCentre = new JPanel();
        panelCombatCentre.setLayout(new BoxLayout(panelCombatCentre, BoxLayout.Y_AXIS));
        panelCombatCentre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        slot_J1 = new Slot(model.getJ1());
        slot_J1.addActionListener(cntrl);

        slot_J2 = new Slot(model.getJ2());
        slot_J2.addActionListener(cntrl);

        barreJ1 = new JProgressBar(0, 100);
        barreJ1.setStringPainted(true);
        barreJ1.setPreferredSize(new Dimension(180, 20));
        barreJ1.setMaximumSize(new Dimension(180, 20));

        barreJ2 = new JProgressBar(0, 100);
        barreJ2.setStringPainted(true);
        barreJ2.setPreferredSize(new Dimension(180, 20));
        barreJ2.setMaximumSize(new Dimension(180, 20));

        ImageIcon icon = new ImageIcon("images/logo.png");
        plt = icon.getImage();
        Image imgRedim4 = plt.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(imgRedim4));

        panelSlotJ2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSlotJ2.add(slot_J2);

        panelMilieu = new JPanel();
        panelMilieu.setLayout(new BoxLayout(panelMilieu, BoxLayout.Y_AXIS));

        JPanel panelBarreHaut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBarreHaut.add(barreJ2);

        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.add(logoLabel);

        JPanel panelBarreBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBarreBas.add(barreJ1);

        panelMilieu.add(panelBarreHaut);
        panelMilieu.add(Box.createVerticalStrut(5));
        panelMilieu.add(panelLogo);
        panelMilieu.add(Box.createVerticalStrut(5));
        panelMilieu.add(panelBarreBas);

        panelSlotJ1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSlotJ1.add(slot_J1);

        panelCombatCentre.add(panelSlotJ2);
        panelCombatCentre.add(Box.createVerticalStrut(5));
        panelCombatCentre.add(panelMilieu);
        panelCombatCentre.add(Box.createVerticalStrut(5));
        panelCombatCentre.add(panelSlotJ1);

        PlateauCombat.add(panelCombatCentre);
        PlateauCombat.add(panelFin);
        
    }
	
	private void updatePV(JProgressBar barre, Joueur j) {
	    if (j.getPersonnageActif() == null) {
	        barre.setMaximum(100);
	        barre.setValue(0);
	        barre.setString("0 / 0");
	        return;
	    }

	    Carte c = j.getPersonnageActif();

	    barre.setMaximum(c.getPvmax());
	    barre.setValue(c.getPv());
	    barre.setString(c.getPv() + " / " + c.getPvmax());

	    if (c.getPv() < c.getPvmax() * 0.25) barre.setForeground(Color.RED);
	    else if (c.getPv() < c.getPvmax() * 0.50) barre.setForeground(Color.YELLOW);
	    else barre.setForeground(Color.GREEN);
	}
	
	private void updateButtons() {
		attaquerJ1.setEnabled(model.peutAttaquer(model.getJ1()));
		attaquerJ2.setEnabled(model.peutAttaquer(model.getJ2()));

		utiliserpouvoirJ1.setEnabled(model.peutUtiliserPouvoir(model.getJ1()));
		utiliserpouvoirJ2.setEnabled(model.peutUtiliserPouvoir(model.getJ2()));

		changerCarteJ1.setEnabled(model.peutChangerCarte(model.getJ1()));
		changerCarteJ2.setEnabled(model.peutChangerCarte(model.getJ2()));
		
		passerJ1.setEnabled(model.peutpasser(model.getJ1()));
		passerJ2.setEnabled(model.peutpasser(model.getJ2()));
	}
	
	private void updatePlateau() {
		updatePV(barreJ1, model.getJ1());
		updatePV(barreJ2, model.getJ2());
		slot_J1.setCarte(model.getJ1().getPersonnageActif());
		slot_J2.setCarte(model.getJ2().getPersonnageActif());
		
	}
	@Override
	public void update(Observable o, Object arg) {
		refresh();
	}




}
