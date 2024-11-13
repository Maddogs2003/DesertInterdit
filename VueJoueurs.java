import javax.swing.*;
import java.awt.*;

class VueJoueurs extends JPanel implements Observer {

    private Desert plateau;
    private int width_card = 200;
    private int height_card = 150;
    private int gap = 3; // pixels
    private int startx = 0;
    private int starty = 200;
    private JButton Give;

    public VueJoueurs(Desert plateau) {
        this.plateau = plateau;
        plateau.addObserver(this);
        Dimension dim = new Dimension(600, 900); // 1366,768
        this.setPreferredSize(dim);
        paintComponent();
    }

    public void update() {
        removeAll();
        paintComponent();
        repaint();
    }

    public void paintComponent() { // Creation des cartes de chaques joueurs
        int compt = 0;
        int compt_lignes = 0;
        for (Joueur h : plateau.getJoueur()) {
            if (compt * (width_card + gap) + startx + width_card >= 600) {
                compt_lignes += 1;
                compt = 0;
            }

            int posX = compt * (width_card + gap);
            int posY = compt_lignes * (height_card + gap);
            JLabel name = new JLabel(h.getNom());
            name.setBounds(((startx + posX + gap) + width_card / 3), (starty + posY + gap) + gap, 150, 20);
            add(name);
            JLabel water = new JLabel("Niveau de Gourde : " + h.getNiveauEau());
            water.setBounds(((startx + posX + gap) + width_card / 5), (starty + posY + gap) + height_card / 6, 150, 10);
            add(water);
            JLabel pieces = new JLabel("Pieces : " + h.getStringPieces());
            pieces.setBounds(((startx + posX + gap) + width_card / 5), (starty + posY + gap) + height_card / 6 + 15,
                    150, 10);
            add(pieces);
            Give = new JButton("Donner Eau");
            Give.setBounds(((startx + posX + gap) + width_card / 5), (starty + posY + gap) + height_card / 6 + 30, 100,
                    25);
            add(Give);
            Controleur con = new Controleur(plateau, h);
            Give.addActionListener(con);
            JLabel inside = new JLabel("");
            inside.setBounds(startx + posX + gap, starty + posY + gap, width_card - (gap * 2), height_card - (gap * 2));
            inside.setBackground(Color.WHITE);
            inside.setOpaque(true);
            add(inside);
            setLayout(null);
            JLabel outline = new JLabel("");
            outline.setBounds(startx + posX, starty + posY, width_card, height_card);
            outline.setBackground(h.getColor());
            outline.setOpaque(true);
            add(outline);
            compt += 1;
        }
    }
}

/**
 * class CarteJoueurs extends JPanel implements Observer {
 * 
 * private Desert plateau;
 * private Joueur joueur;
 * private int card_height=150;
 * private int card_width=200;
 * 
 * 
 * public CarteJoueurs(Desert plateau,Joueur j) {
 * this.plateau = plateau;
 * plateau.addObserver(this);
 * Dimension dim = new Dimension(card_width, card_height); // 1366,768
 * setBackground(j.getColor());
 * this.setPreferredSize(dim);
 * paintComponent();
 * }
 * public void paintComponent(){
 * 
 * }
 * public int getHeight(){
 * return card_height;
 * }
 * public int getWidth(){
 * return card_width;
 * }
 * 
 * public void update(){
 * repaint();
 * }
 * }
 **/
