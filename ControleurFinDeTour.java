
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurFinDeTour implements ActionListener {

    private final Desert plateau;

    public ControleurFinDeTour(Desert plateau) {
        this.plateau = plateau;
    }

    public void actionPerformed(ActionEvent e) {
        if (plateau.getJoueurActuel() == (plateau.getJoueur().length - 1)) {
            plateau.actionDesert(plateau.getJoueur());
        }
        plateau.getJoueur()[plateau.getJoueurActuel()].resetActions();
        plateau.next();
        plateau.notifyObservers();
    }
}
