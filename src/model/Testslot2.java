package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



public class Testslot2 extends JPanel implements ActionListener, MouseListener {

    ArrayList<JButton> buttons;
    ArrayList<JButton> buttons2;

    JButton boutonSelectionne = null;

    JButton slotHaut;
    JButton slotBas;

    JButton attaquerNord;
    JButton pouvoirNord;
    JButton changerNord;

    JButton attaquerSud;
    JButton pouvoirSud;
    JButton changerSud;

    JProgressBar barreVieHaut;
    JProgressBar barreVieBas;

    ImageIcon image;
    ImageIcon img2;

    JPanel panelNordGlobal;
    JPanel panelCentreGlobal;
    JPanel panelSudGlobal;

    JPanel panelCartesNord;
    JPanel panelActionsNord;

    JPanel panelCombatCentre;
    JPanel panelSlotHaut;
    JPanel panelMilieu;
    JPanel panelSlotBas;

    JPanel panelActionsSud;
    JPanel panelCartesSud;

    JLabel logoLabel;

    public Testslot2(JFrame f) {

        setPreferredSize(new Dimension(1000, 800));
        setLayout(new BorderLayout());

        buttons = new ArrayList<JButton>();
        buttons2 = new ArrayList<JButton>();

        creerImages();
        creerZoneNord();
        creerZoneCentre();
        creerZoneSud();

        add(panelNordGlobal, BorderLayout.NORTH);
        add(panelCentreGlobal, BorderLayout.CENTER);
        add(panelSudGlobal, BorderLayout.SOUTH);

        addMouseListener(this);
    }

    public void creerImages() {
        image = new ImageIcon("cartes/Dracaufeu.png");
        Image img = image.getImage();
        Image imgRedim = img.getScaledInstance(105, 135, Image.SCALE_SMOOTH);
        image = new ImageIcon(imgRedim);

        img2 = new ImageIcon("cartes/pikachu.png");
        Image imgBas = img2.getImage();
        Image imgRedim2 = imgBas.getScaledInstance(170, 130, Image.SCALE_SMOOTH);
        img2 = new ImageIcon(imgRedim2);
       
    }

    public void creerZoneNord() {
        panelNordGlobal = new JPanel();
        panelNordGlobal.setLayout(new BoxLayout(panelNordGlobal, BoxLayout.Y_AXIS));

        panelCartesNord = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelActionsNord = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));

        for (int i = 0; i < 5; i++) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(100, 130));
            b.setMargin(new Insets(0, 0, 0, 0));
            b.setFocusPainted(false);
            b.addActionListener(this);
            buttons.add(b);
            panelCartesNord.add(b);
        }

        buttons.get(0).setIcon(image);

        attaquerNord = new JButton("Attaquer");
        pouvoirNord = new JButton("Utiliser pouvoir");
        changerNord = new JButton("Changer");
        

        attaquerNord.addActionListener(this);
        pouvoirNord.addActionListener(this);
        changerNord.addActionListener(this);

        panelActionsNord.add(attaquerNord);
        panelActionsNord.add(pouvoirNord);
        panelActionsNord.add(changerNord);

        panelNordGlobal.add(panelCartesNord);
        panelNordGlobal.add(panelActionsNord);
    }

    private void creerZoneCentre() {
        panelCentreGlobal = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        //panelCentreGlobal.setPreferredSize(new Dimension(700, 330));

        panelCombatCentre = new JPanel();
        panelCombatCentre.setLayout(new BoxLayout(panelCombatCentre, BoxLayout.Y_AXIS));
        panelCombatCentre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        slotHaut = new JButton();
        slotHaut.setPreferredSize(new Dimension(100, 130));
        slotHaut.setMargin(new Insets(0, 0, 0, 0));
        slotHaut.setFocusPainted(false);
        slotHaut.addActionListener(this);

        slotBas = new JButton();
        slotBas.setPreferredSize(new Dimension(100, 130));
        slotBas.setMargin(new Insets(0, 0, 0, 0));
        slotBas.setFocusPainted(false);
        slotBas.addActionListener(this);

        barreVieHaut = new JProgressBar(0, 100);
        barreVieHaut.setValue(100);
        barreVieHaut.setStringPainted(true);
        barreVieHaut.setForeground(Color.green);
        barreVieHaut.setPreferredSize(new Dimension(180, 20));
        barreVieHaut.setMaximumSize(new Dimension(180, 20));

        barreVieBas = new JProgressBar(0, 100);
        barreVieBas.setValue(100);
        barreVieBas.setStringPainted(true);
        barreVieBas.setForeground(Color.green);
        barreVieBas.setPreferredSize(new Dimension(180, 20));
        barreVieBas.setMaximumSize(new Dimension(180, 20));

        ImageIcon icon = new ImageIcon("cartes/logo.png");
        Image img4 = icon.getImage();
        Image imgRedim4 = img4.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(imgRedim4));

        panelSlotHaut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSlotHaut.add(slotHaut);

        panelMilieu = new JPanel();
        panelMilieu.setLayout(new BoxLayout(panelMilieu, BoxLayout.Y_AXIS));

        JPanel panelBarreHaut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBarreHaut.add(barreVieHaut);

        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.add(logoLabel);

        JPanel panelBarreBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBarreBas.add(barreVieBas);

        panelMilieu.add(panelBarreHaut);
        panelMilieu.add(Box.createVerticalStrut(5));
        panelMilieu.add(panelLogo);
        panelMilieu.add(Box.createVerticalStrut(5));
        panelMilieu.add(panelBarreBas);

        panelSlotBas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSlotBas.add(slotBas);

        panelCombatCentre.add(panelSlotHaut);
        panelCombatCentre.add(Box.createVerticalStrut(5));
        panelCombatCentre.add(panelMilieu);
        panelCombatCentre.add(Box.createVerticalStrut(5));
        panelCombatCentre.add(panelSlotBas);

        panelCentreGlobal.add(panelCombatCentre);
        
    }

    public void creerZoneSud() {
        panelSudGlobal = new JPanel();
        panelSudGlobal.setLayout(new BoxLayout(panelSudGlobal, BoxLayout.Y_AXIS));

        panelActionsSud = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelCartesSud = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        attaquerSud = new JButton("Attaquer");
        pouvoirSud = new JButton("Utiliser pouvoir");
        changerSud = new JButton("Changer");

        attaquerSud.addActionListener(this);
        pouvoirSud.addActionListener(this);
        changerSud.addActionListener(this);

        panelActionsSud.add(attaquerSud);
        panelActionsSud.add(pouvoirSud);
        panelActionsSud.add(changerSud);

        for (int i = 0; i < 5; i++) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(100, 130));
            b.setMargin(new Insets(0, 0, 0, 0));
            b.setFocusPainted(false);
            b.addActionListener(this);
            buttons2.add(b);
            panelCartesSud.add(b);
        }

        buttons2.get(1).setIcon(img2);

        panelSudGlobal.add(panelActionsSud);
        panelSudGlobal.add(panelCartesSud);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton boutonClique = (JButton) e.getSource();

        if (boutonClique == attaquerNord) {
            System.out.println("J2 Nord attaque");
            barreVieBas.setValue(barreVieBas.getValue() - 10);
        }
        else if (boutonClique == pouvoirNord) {
            System.out.println("J2 Nord utilise son pouvoir");
        }
        else if (boutonClique == changerNord) {
            System.out.println("J2 Nord change de pokemon");
        }
        else if (boutonClique == attaquerSud) {
            System.out.println("J1 Sud attaque");
            barreVieHaut.setValue(barreVieHaut.getValue() - 10);
        }
        else if (boutonClique == pouvoirSud) {
            System.out.println("J1 Sud utilise son pouvoir");
        }
        else if (boutonClique == changerSud) {
            System.out.println("J1 Sud change de pokemon");
        }
        else if (boutonClique.getIcon() != null) {
            boutonSelectionne = boutonClique;
            System.out.println("image selectionnee");
        }
        else if (boutonSelectionne != null && boutonClique.getIcon() == null) {
            boutonClique.setIcon(boutonSelectionne.getIcon());
            boutonSelectionne.setIcon(null);
            boutonSelectionne = null;
            System.out.println("image deplacee");
        }

        if (barreVieHaut.getValue() < 0) {
            barreVieHaut.setValue(0);
        }

        if (barreVieBas.getValue() < 0) {
            barreVieBas.setValue(0);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();

        Testslot2 t = new Testslot2(f);

        f.setLayout(new BorderLayout());
        f.add(t);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && boutonSelectionne != null) {
            System.out.println("selection annulee");
            boutonSelectionne = null;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}