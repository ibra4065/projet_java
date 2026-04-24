package view;

import javax.swing.*;

import controleur.ControleurRegles;
import controleur.controleurMenu;

import java.awt.*;



public class VueMenu extends JPanel {

    private JButton btnJouer;
    private JButton btnRegles;
    private JButton btnQuitter;
    private Image imageFond;

    public VueMenu(controleurMenu controleurMenu) {
    	this.setPreferredSize(new Dimension(1000, 800)); // ✅ AJOUT IMPORTANT
        this.setLayout(new BorderLayout());
    	

        imageFond = new ImageIcon("images/fond.gif").getImage();

        String path = "images/PokemonLogo.png";
        ImageIcon iconOriginal = new ImageIcon(path);

        Image img = iconOriginal.getImage();
        Image imgRedimensionnee = img.getScaledInstance(400, -1, Image.SCALE_SMOOTH);
        ImageIcon logoFinal = new ImageIcon(imgRedimensionnee);

        JLabel logoLabel = new JLabel(logoFinal, SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        this.add(logoLabel, BorderLayout.NORTH);

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setOpaque(false);

        btnJouer = creerBoutonMenu("Jouer");
        btnRegles = creerBoutonMenu("Regles");
        btnQuitter = creerBoutonMenu("Quitter");

        btnJouer.setActionCommand("JOUER");
        btnJouer.addActionListener(controleurMenu);
        
        btnRegles.addActionListener(new ControleurRegles());

        btnQuitter.addActionListener(e -> System.exit(0));

        btnJouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegles.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuitter.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBoutons.add(btnJouer);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 30)));
        panelBoutons.add(btnRegles);
        panelBoutons.add(Box.createRigidArea(new Dimension(0, 30)));
        panelBoutons.add(btnQuitter);

        this.add(panelBoutons, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageFond != null) {
            g.drawImage(imageFond, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private JButton creerBoutonMenu(String texte) {
        JButton bouton = new JButton(texte);
        bouton.setFont(new Font("SansSerif", Font.PLAIN, 24));
        bouton.setFocusPainted(false);
        bouton.setContentAreaFilled(false);
        bouton.setBorderPainted(false);
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return bouton;
    }

    public JButton getBtnJouer() { return btnJouer; }
    public JButton getBtnOption() { return btnRegles; }
    public JButton getBtnQuitter() { return btnQuitter; }
}