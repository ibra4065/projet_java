package view;

import javax.swing.*;

import controleur.ControleurRegles;
import controleur.controleurMenu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;

public class VueMenu extends JPanel {

    private JButton btnJouer;
    private JButton btnRegles;
    private JButton btnQuitter;
    private Image imageFond;


    private JButton btnSon;
    private JSlider sliderVolume;

    private Clip clip;
    private FloatControl volumeControl;
    private boolean isMuted = false;
    private int previousSliderValue = 80; 

    public VueMenu(controleurMenu controleurMenu) {
        this.setPreferredSize(new Dimension(1300, 800)); 
        this.setLayout(new BorderLayout());

        imageFond = new ImageIcon("images/fond.gif").getImage();

        initSon(); 

        String path = "images/PokemonLogo.png";
        ImageIcon iconOriginal = new ImageIcon(path);

        Image img = iconOriginal.getImage();
        Image imgRedimensionnee = img.getScaledInstance(400, -1, Image.SCALE_SMOOTH);
        ImageIcon logoFinal = new ImageIcon(imgRedimensionnee);

        JLabel logoLabel = new JLabel(logoFinal, SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        btnSon = new JButton("🔊");
        btnSon.setFocusPainted(false);
        btnSon.setContentAreaFilled(false);
        btnSon.setBorderPainted(false);
        btnSon.setForeground(Color.WHITE);
        btnSon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        sliderVolume = new JSlider(0, 100, 80);
        sliderVolume.setPreferredSize(new Dimension(120, 20));
        sliderVolume.setOpaque(false);

     
        sliderVolume.addChangeListener(e -> {
            if (volumeControl != null) {
                setVolumeFromSlider(sliderVolume.getValue());
            }

            if (sliderVolume.getValue() == 0) {
                isMuted = true;
                btnSon.setText("🔇");
            } else {
                isMuted = false;
                btnSon.setText("🔊");
            }
        });

        // Ibrahim ne touche pas a ça car ya un délai
        btnSon.addActionListener(e -> toggleMute());

       
        JPanel panelSonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelSonWrapper.setOpaque(false);
        panelSonWrapper.add(sliderVolume);
        panelSonWrapper.add(btnSon);

     
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.setOpaque(false);
        panelLogo.add(logoLabel);

       
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(panelSonWrapper, BorderLayout.NORTH);
        header.add(panelLogo, BorderLayout.CENTER);

       
        this.add(header, BorderLayout.NORTH);

        

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

   
    private void initSon() {
        try {
            String musicPath = "sons/menu.wav"; 
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(musicPath));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolumeFromSlider(80); 
            }

        } catch (Exception e) {
            System.out.println("Erreur chargement son menu : " + e.getMessage());
        }
    }


    private void setVolumeFromSlider(int sliderValue) {
        if (volumeControl == null) return;

        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();

        float db = min + (max - min) * (sliderValue / 100.0f);
        volumeControl.setValue(db);
    }


    private void toggleMute() {
        if (clip == null || volumeControl == null) return;

        if (!isMuted) {
            previousSliderValue = sliderVolume.getValue();
            if (previousSliderValue == 0) {
                previousSliderValue = 50; 
            }
            sliderVolume.setValue(0);
        } else {
            sliderVolume.setValue(previousSliderValue);
        }
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

       
        bouton.setForeground(Color.WHITE);

      
        bouton.setContentAreaFilled(false);
        bouton.setOpaque(false);

  
        Dimension tailleBouton = new Dimension(250, 60);
        bouton.setPreferredSize(tailleBouton);
        bouton.setMaximumSize(tailleBouton);

        
        bouton.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
            )
        );

        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));

  
        bouton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bouton.setContentAreaFilled(true);
                bouton.setOpaque(true);
                bouton.setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bouton.setContentAreaFilled(false);
                bouton.setOpaque(false);
                bouton.setBackground(new Color(0, 0, 0, 0));
            }
        });

        return bouton;
    }

    public JButton getBtnJouer() {
        return btnJouer;
    }

    public JButton getBtnOption() {
        return btnRegles;
    }

    public JButton getBtnQuitter() {
        return btnQuitter;
    }
}