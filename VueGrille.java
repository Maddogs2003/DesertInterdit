
import javax.swing.*;
import java.awt.*;

class VueGrille extends JPanel implements Observer {
    private Desert plateau;
    private final static int TAILLE = 100;

    public VueGrille(Desert plateau) {
        this.plateau = plateau;
        plateau.addObserver(this);
        Dimension dim = new Dimension(800, 900); // 1366,768
        this.setPreferredSize(dim);
    }

    public void update() {
        int sable_tot = 0;
        for (int i = 0; i < plateau.getTaille(); i++) {
            for (int j = 0; j < plateau.getTaille(); j++) {
                sable_tot = sable_tot + (plateau.getCase(i, j).getSable());
            }
        }
        if (!(sable_tot >= 43 || plateau.getTempete() >= 7)) {
            removeAll();
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        removeAll();
        setLayout(null);
        int sable_tot = 0;
        for (int i = 0; i < plateau.getTaille(); i++) { // Affichage Du Desert + Sable
            for (int j = 0; j < plateau.getTaille(); j++) {
                color(g, plateau.getCase(i, j),
                        ((getWidth() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2)) + i * (TAILLE + 5),
                        ((getHeight() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2)) + j * (TAILLE + 5));
                // color(g, plateau.getCase(i, j),((TAILLE+5)*plateau.getTaille())*2+i *
                // (TAILLE+5),((TAILLE+5)*plateau.getTaille())/2+j * (TAILLE+5));
                if (plateau.getCase(i, j).getType() != TypeCase.OEIL_TEMPETE) {
                    JLabel sand = new JLabel();
                    sand.setText("Sable : " + plateau.getCase(i, j).getSable());
                    sable_tot = sable_tot + plateau.getCase(i, j).getSable();
                    sand.setBounds(
                            ((getWidth() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2) + TAILLE / 4)
                                    + i * (TAILLE + 5),
                            ((getHeight() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2) + TAILLE / 8)
                                    + j * (TAILLE + 5) - 20,
                            100, 50);
                    add(sand);
                    if (plateau.getCase(i, j).estExploree()) { // Modifier ici pour afficher le contenu des cases
                        JLabel type = new JLabel();
                        type.setText(plateau.getCase(i, j).getType().toString());
                        sable_tot = sable_tot + plateau.getCase(i, j).getSable();
                        type.setBounds(
                                ((getWidth() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2) + 4)
                                        + i * (TAILLE + 5),
                                ((getHeight() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2) + TAILLE / 6)
                                        + j * (TAILLE + 5) - 20 + 10,
                                100, 50);
                        add(type);

                        if (plateau.getCase(i, j).getPiece() != null && plateau.getCase(i, j).isRevealed()) {
                            JLabel pieceLabel = new JLabel();
                            pieceLabel.setText(plateau.getCase(i, j).getPiece().toString());
                            pieceLabel.setBounds(
                                    ((getWidth() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2) + 4)
                                            + i * (TAILLE + 5),
                                    ((getHeight() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2) + TAILLE / 6)
                                            + j * (TAILLE + 5) - 20 + 10 + 20,
                                    100, 50);
                            add(pieceLabel);
                        }
                    }

                }
            }
        }
        JLabel sand = new JLabel();
        sand.setText("Sable total : " + sable_tot);
        sand.setBounds(150, 150, 100, 50);
        add(sand);
        // AFFICHAGE des Joueurs
        int taille_joueur = 20;
        int matricule = 0;
        for (Joueur j : plateau.getJoueur()) {
            g.setColor(j.getColor()); //// Couleur symbolisant le joueur actuelle (jouant)
            int o = 10;
            if (plateau.getJoueur().length >= 5) {
                o = 0;
            }
            g.fillOval(
                    ((getWidth() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2)) + j.getX() * (TAILLE + 5) + o
                            + ((taille_joueur) * matricule),
                    ((getHeight() / 2) - (plateau.getTaille() * (TAILLE + 5) / 2)) + j.getY() * (TAILLE + 5)
                            + ((TAILLE - taille_joueur) / 2) + 20,
                    taille_joueur, taille_joueur);
            matricule += 1;
        }
        JLabel actionrest = new JLabel();
        actionrest.setText("Actions restantes : " + plateau.getJoueur()[plateau.getJoueurActuel()].getActions());
        actionrest.setBounds(150, 130, 150, 50);
        add(actionrest);
        JLabel jactuel = new JLabel();
        jactuel.setText("Joueur actuel : " + plateau.getJoueur()[plateau.getJoueurActuel()].getNom());
        jactuel.setForeground(plateau.getJoueur()[plateau.getJoueurActuel()].getColor());
        jactuel.setFont(new Font("Dialog", Font.BOLD, 30));
        jactuel.setBounds(150, 100, 500, 50);
        add(jactuel);

    }

    private void color(Graphics g, Case c, int x, int y) {
        if (c.getSable() >= 1) {
            Color col = new Color(246, 145, 30);
            g.setColor(col);
        } else {
            g.setColor(Color.ORANGE);
        }
        if (c.estBloquee()) {
            g.setColor(Color.RED);
        }
        if (c.getType() == TypeCase.OASIS || (c.getType() == TypeCase.MIRAGE && (!c.estExploree()))) {
            g.setColor(new Color(82, 195, 218));
        }
        if (c.estExploree()) {
            if (c.getType() == TypeCase.HELICOPTERE_CRASH) {
                g.setColor(Color.GRAY);
            }
        }
        if (c.getType() == TypeCase.OEIL_TEMPETE) {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x, y, TAILLE, TAILLE);
    }
}