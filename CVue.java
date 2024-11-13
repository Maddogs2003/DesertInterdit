import javax.swing.*;
import java.awt.*;

class CVue {

    private JFrame frame;
    private JFrame framemenu;
    private VueGrille grille;
    private VueCommandes commandes;
    private VueJoueurs joueurs;

    public CVue(Desert plateau, JFrame menuframe) {
        framemenu = menuframe;
        frame = new JFrame();
        frame.setTitle("Jeu du Desert Interdit");

        frame.setLayout(new FlowLayout());

        grille = new VueGrille(plateau);
        frame.add(grille);
        commandes = new VueCommandes(plateau, frame, framemenu);
        frame.add(commandes);
        joueurs = new VueJoueurs(plateau);
        frame.add(joueurs);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}