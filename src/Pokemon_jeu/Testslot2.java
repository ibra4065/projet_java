package Pokemon_jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Testslot2 extends JPanel implements ActionListener, MouseListener {

    public Image imglogo = Toolkit.getDefaultToolkit().getImage("cartes/logo.png");

    Dimension d = new Dimension(600, 800);
    ArrayList<JButton> buttons;
    ArrayList<JButton> buttons2;
    ImageIcon image;
    JButton boutonSelectionne = null;
    ImageIcon img2;
    JButton slotHaut;
    JButton slotBas;

    JPanel panelBoutons;
    JPanel panelBoutons2;

    JPanel panelActionsNord;
    JPanel panelActionsSud;

    JButton attaquerNord;
    JButton pouvoirNord;
    JButton changerNord;

    JButton attaquerSud;
    JButton pouvoirSud;
    JButton changerSud;

    public Testslot2(JFrame f) {

        setPreferredSize(d);
        setLayout(null);

        buttons = new ArrayList<JButton>();
        buttons2 = new ArrayList<JButton>();

        panelBoutons = new JPanel();
        panelBoutons2 = new JPanel();
        panelActionsNord = new JPanel();
        panelActionsSud = new JPanel();

        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBoutons.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));

        panelBoutons2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBoutons2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));

        panelActionsNord.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelActionsSud.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        panelBoutons.setBounds(0, 0, 600, 150);
        panelActionsNord.setBounds(0, 145, 600, 50);

        panelActionsSud.setBounds(0, 565, 600, 50);
        panelBoutons2.setBounds(0, 620, 600, 150);

        for (int i = 0; i < 5; i++) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(100, 130));
            b.setMargin(new Insets(0, 0, 0, 0));
            b.setFocusPainted(false);
            b.addActionListener(this);

            buttons.add(b);
            panelBoutons.add(b);
        }

        for (int i = 0; i < 5; i++) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(100, 130));
            b.setMargin(new Insets(0, 0, 0, 0));
            b.setFocusPainted(false);
            b.addActionListener(this);

            buttons2.add(b);
            panelBoutons2.add(b);
        }

        slotHaut = new JButton();
        slotHaut.setBounds((d.width - 112) / 2, (d.height - 130) / 2 - 125, 100, 130);
        slotHaut.setMargin(new Insets(0, 0, 0, 0));
        slotHaut.setFocusPainted(false);
        slotHaut.addActionListener(this);

        slotBas = new JButton();
        slotBas.setMargin(new Insets(0, 0, 0, 0));
        slotBas.setFocusPainted(false);
        slotBas.addActionListener(this);
        slotBas.setBounds((d.width - 112) / 2, (d.height - 130) / 2 + 85, 100, 130);

        attaquerNord = new JButton("Attaquer");
        pouvoirNord = new JButton("Utiliser pouvoir");
        changerNord = new JButton("Changer");

        attaquerSud = new JButton("Attaquer");
        pouvoirSud = new JButton("Utiliser pouvoir");
        changerSud = new JButton("Changer");

        attaquerNord.addActionListener(this);
        pouvoirNord.addActionListener(this);
        changerNord.addActionListener(this);

        attaquerSud.addActionListener(this);
        pouvoirSud.addActionListener(this);
        changerSud.addActionListener(this);

        panelActionsNord.add(attaquerNord);
        panelActionsNord.add(pouvoirNord);
        panelActionsNord.add(changerNord);

        panelActionsSud.add(attaquerSud);
        panelActionsSud.add(pouvoirSud);
        panelActionsSud.add(changerSud);

        image = new ImageIcon("cartes/dragon.png");
        img2 = new ImageIcon("cartes/pikachu.png");
        Image img3= img2.getImage();
        Image img = image.getImage();
        Image imgRedim = img.getScaledInstance(110, 130, Image.SCALE_SMOOTH);
        Image imgRedim2 = img3.getScaledInstance(170, 130, Image.SCALE_SMOOTH);
        image = new ImageIcon(imgRedim);
        img2=new ImageIcon(imgRedim2);

        buttons.get(0).setIcon(image);
        buttons2.get(1).setIcon(img2);
        add(panelBoutons);
        add(panelActionsNord);
        add(panelActionsSud);
        add(panelBoutons2);
        add(slotHaut);
        add(slotBas);

        addMouseListener(this);
    }

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

        g.drawImage(imglogo,
                (width - 50) / 2,
                (height - 50) / 2,
                50,
                50,
                this
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton boutonClique = (JButton) e.getSource();

        if (boutonClique == attaquerNord) {
            System.out.println("J2 Nord attaque");
        }
        else if (boutonClique == pouvoirNord) {
            System.out.println("J2 Nord utilise son pouvoir");
        }
        else if (boutonClique == changerNord) {
            System.out.println("J2 Nord change de pokemon");
        }
        else if (boutonClique == attaquerSud) {
            System.out.println("J1 South attaque");
        }
        else if (boutonClique == pouvoirSud) {
            System.out.println("J1 South utilise son pouvoir");
        }
        else if (boutonClique == changerSud) {
            System.out.println("J1 South change de pokemon");
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
    }

    public static void main(String[] args) {

        JFrame f = new JFrame();
        Testslot2 t = new Testslot2(f);

        f.setLayout(new BorderLayout());
        f.add(t, BorderLayout.CENTER);

        f.setSize(600, 800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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