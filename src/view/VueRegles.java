package view;

import javax.swing.*;
import java.awt.*;

public class VueRegles extends JFrame {

    public VueRegles() {
        super("Règles du jeu");

        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        JLabel titre = new JLabel("Règles du jeu Pokémon", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 28));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JTextArea texteRegles = new JTextArea();
        texteRegles.setEditable(false);
        texteRegles.setLineWrap(true);
        texteRegles.setWrapStyleWord(true);
        texteRegles.setFont(new Font("SansSerif", Font.PLAIN, 16));
        texteRegles.setText(
            "But du jeu :\n" +
            "Chaque joueur possède un deck de cartes Pokémon.\n" +
            "Le but est de battre le Pokémon actif de l'adversaire.\n" +
            "chaque carte a des point fort contre certaine type de carte et point faible face a certain type\n" +
            "pour mieux comprendre les type de carte et ses caracteristique je vous invite de visiter la page .\n" +
            "https://www.pokepedia.fr/Table_des_types.\n\n" +

            "Début de partie :\n" +
            "- Chaque joueur pioche 3 cartes.\n" +
            "- Chaque joueur choisit un Pokémon pour commencer la partie.\n\n" +

            "Pendant un tour :\n" +
            "- Le joueur peut attaquer.\n" +
            "- Il peut utiliser le pouvoir de son Pokémon si cest possible .\n" +
            "- Il peut changer sa carte active quand il veut (il a 3 fois de changer pendant la partie) une posibiliter de changer aussi si les 2 carte sur le plateau sont pareile et ils ont aucun effet  .\n" +
            "- il peut piocher une carte de son deck si il n'est pas vide.\n" +
            "- il peut passer le tour ou changer sa carte si celle ci est non utilisable temporairement .\n\n" +

            "Caractéristiques :\n" +
            "- PV : points de vie de la carte.\n" +
            "- Attaque : dégâts infligés.\n" +
            "- Défense : réduction des dégâts reçus.\n" +
            "- Type : élément de la carte.\n" +
            "- Pouvoir : effet spécial de la carte.\n"
            
        );

        texteRegles.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel panelCartes = new JPanel();
        panelCartes.setLayout(new GridLayout(0, 1, 10, 10));
        panelCartes.setBackground(Color.WHITE);
        panelCartes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ajouterCarte(panelCartes, "Dracaufeu", "cartes/Dracaufeu.jpg", 170, 55, 80, "FEU", "BRULURE");
        ajouterCarte(panelCartes, "Pikachu", "cartes/pikachu.jpg", 70, 15, 10, "ELECTRIK", "PARALYSIE");
        ajouterCarte(panelCartes, "Feuillajou", "cartes/Feuillajou.jpg", 70, 15, 10, "PLANTE", "BUFF_ATTAQUE");
        ajouterCarte(panelCartes, "Magicarpe", "cartes/Magicarpe.jpg", 30, 5, 5, "EAU", "SOIN");
        ajouterCarte(panelCartes, "Qulbutoké", "cartes/Qulbutoké.jpg", 120, 30, 50, "PSY", "DEBUFF_ATTAQUE");
        ajouterCarte(panelCartes, "Salamèche", "cartes/Salamèche.jpg", 70, 15, 10, "FEU", "BRULURE");
        ajouterCarte(panelCartes, "Goupix", "cartes/Goupix.jpg", 60, 10, 10, "FEU", "BRULURE");
        ajouterCarte(panelCartes, "Diancie", "cartes/Diancie.jpg", 90, 20, 35, "ROCHE", "BUFF_DEFENSE");
        ajouterCarte(panelCartes, "Miaouss", "cartes/Miaouss.jpg", 60, 10, 10, "NORMAL", "CRITIQUE");

        JPanel contenu = new JPanel();
        contenu.setLayout(new BorderLayout());
        contenu.add(texteRegles, BorderLayout.NORTH);
        contenu.add(panelCartes, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(contenu);

        panelPrincipal.add(titre, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void ajouterCarte(JPanel panel, String nom, String cheminImage, int pv, int attaque, int defense, String type, String pouvoir) {

        JPanel panelCarte = new JPanel();
        panelCarte.setLayout(new BorderLayout());
        panelCarte.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panelCarte.setBackground(new Color(245, 245, 245));

        ImageIcon icon = new ImageIcon(cheminImage);
        Image img = icon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel infos = new JLabel(
            "<html>" +
            "<h2>" + nom + "</h2>" +
            "PV : " + pv + "<br>" +
            "Attaque : " + attaque + "<br>" +
            "Défense : " + defense + "<br>" +
            "Type : " + type + "<br>" +
            "Pouvoir : " + pouvoir +
            "</html>"
        );

        infos.setFont(new Font("SansSerif", Font.PLAIN, 16));
        infos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        panelCarte.add(imageLabel, BorderLayout.WEST);
        panelCarte.add(infos, BorderLayout.CENTER);

        panel.add(panelCarte);
    }
}