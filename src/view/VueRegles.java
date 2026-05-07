package view;

import javax.swing.*;

import model.BibliothequeCartes;
import model.BibliothequeCartes.Caracteristique;

import java.awt.*;

public class VueRegles extends JFrame {

    public VueRegles() {
        super("Règles du jeu");

       
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(15, 20, 40));

     
        JLabel titre = new JLabel("Règles du jeu Pokémon", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 32));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

       
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(80, 80, 120));
        separator.setBackground(new Color(80, 80, 120));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titre, BorderLayout.CENTER);
        header.add(separator, BorderLayout.SOUTH);

      
        JTextArea texteRegles = new JTextArea();
        texteRegles.setEditable(false);
        texteRegles.setLineWrap(true);
        texteRegles.setWrapStyleWord(true);
        texteRegles.setFont(new Font("SansSerif", Font.PLAIN, 15));
        texteRegles.setForeground(Color.WHITE);
        texteRegles.setBackground(new Color(30, 35, 70));
        texteRegles.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        texteRegles.setText(
                "But du jeu :\n" +
                "Chaque joueur possède un deck de cartes Pokémon.\n" +
                "Le but est de battre le Pokémon actif de l'adversaire.\n" +
                "Chaque carte a des points forts contre certains types de cartes et des points faibles face à d'autres types.\n" +
                "Pour mieux comprendre les types de cartes et leurs caractéristiques, visitez :\n" +
                "https://www.pokepedia.fr/Table_des_types.\n\n" +

                "Début de partie :\n" +
                "- Chaque joueur pioche 3 cartes.\n" +
                "- Chaque joueur choisit un Pokémon pour commencer la partie.\n\n" +

                "Pendant un tour :\n" +
                "- Le joueur peut attaquer.\n" +
                "- Il peut utiliser le pouvoir de son Pokémon si c'est possible.\n" +
                "- Il peut changer sa carte active (3 fois maximum pendant la partie).\n" +
                "- Il peut piocher une carte de son deck si celui-ci n'est pas vide.\n" +
                "- Il peut passer son tour ou changer sa carte si elle est temporairement inutilisable.\n\n" +

                "Caractéristiques d'une carte :\n" +
                "- PV : points de vie.\n" +
                "- Attaque : dégâts infligés.\n" +
                "- Défense : réduction des dégâts reçus.\n" +
                "- Type : élément de la carte.\n" +
                "- Pouvoir : effet spécial de la carte.\n"
        );

        JPanel panelRegles = new JPanel(new BorderLayout());
        panelRegles.setOpaque(false);
        panelRegles.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        panelRegles.add(texteRegles, BorderLayout.CENTER);

      
        JPanel panelCartes = new JPanel();
        panelCartes.setLayout(new GridLayout(0, 3, 15, 15));
        panelCartes.setBackground(new Color(15, 20, 40));
        panelCartes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        BibliothequeCartes biblio = new BibliothequeCartes();
        for (String nom : biblio.getCatalogueCartes().keySet()) {
        	Caracteristique c = biblio.getCatalogueCartes().get(nom);
        	ajouterCarte(panelCartes, nom, "cartes/"+nom+".jpg",c.getPv() , c.getAttack(), c.getDefence(), c.getType().name(), c.getPouvoir().name());
        }
        

     
        JPanel contenu = new JPanel(new BorderLayout());
        contenu.setOpaque(false);
        contenu.add(panelRegles, BorderLayout.NORTH);
        contenu.add(panelCartes, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(contenu);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(15, 20, 40));

        panelPrincipal.add(header, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private void ajouterCarte(JPanel panel, String nom, String cheminImage,
                              int pv, int attaque, int defense, String type, String pouvoir) {

        JPanel panelCarte = new JPanel();
        panelCarte.setLayout(new BorderLayout());
        panelCarte.setBackground(new Color(30, 35, 70));
        panelCarte.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(180, 180, 220), 2, true),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                )
        );

       
        ImageIcon icon = new ImageIcon(cheminImage);
        Image img = icon.getImage().getScaledInstance(110, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

       
        String infosHtml =
                "<html><div style='font-family:SansSerif; font-size:12px; color:white;'>" +
                        "<b>" + nom + "</b><br/>" +
                        "PV : " + pv + "<br/>" +
                        "Attaque : " + attaque + "<br/>" +
                        "Défense : " + defense + "<br/>" +
                        "Type : " + type + "<br/>" +
                        "Pouvoir : " + pouvoir +
                "</div></html>";

        JLabel infos = new JLabel(infosHtml);
        infos.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelCarte.add(imageLabel, BorderLayout.CENTER);
        panelCarte.add(infos, BorderLayout.SOUTH);

        panel.add(panelCarte);
    }
}