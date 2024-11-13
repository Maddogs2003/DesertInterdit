import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class MenuControleur implements ActionListener {

    private final VueMenu menu;
    private final JFrame framemenu;

    public MenuControleur(VueMenu menu, JFrame framemenu) {
        this.menu = menu;
        this.framemenu = framemenu;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.jouer) {
            framemenu.setVisible(false);
            Desert plateau = new Desert(5);
            plateau.addTabJoueur(); // Creation des Joueurs
            CVue jeu = new CVue(plateau, framemenu);
        } else if (e.getSource() == menu.quitter) {
            System.exit(0);
        }
    }

}
