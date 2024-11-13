
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Controleur implements ActionListener {

    Desert plateau;
    Joueur joueur;

    public Controleur(Desert plateau, Joueur p) {
        this.plateau = plateau;
        this.joueur = p;
    }

    public void actionPerformed(ActionEvent e) {
        Joueur actu = plateau.getJoueur()[plateau.getJoueurActuel()];
        actu.donnerEau(joueur, 1);
    }
}