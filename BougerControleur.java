import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BougerControleur implements ActionListener {
    private final KeyControleur key;

    public BougerControleur(VueCommandes commandes, KeyControleur key) {
        this.key = key;
    }

    public void actionPerformed(ActionEvent e) {
        key.setEnabled(!(key.isEnabled()));
    }
}