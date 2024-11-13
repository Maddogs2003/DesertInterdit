import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyControleur implements KeyListener {

    Desert plateau;
    boolean enable;
    private final Mode mode;

    public KeyControleur(Desert plateau, Mode m) {
        this.plateau = plateau;
        this.mode = m;
        enable = false;
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) { // Action du joueur avec les fleches du clavier
        if (enable) {
            Joueur j = plateau.getJoueur()[plateau.getJoueurActuel()];
            if (mode == Mode.DEPLACER) { // On verifie l'action a faire
                if (j.getActions() > 0) {
                    int k = e.getKeyCode();
                    if (k == 37) {
                        j.deplacer(j.getX() - 1, j.getY()); // GAUCHE
                    }
                    if (k == 38) {
                        j.deplacer(j.getX(), j.getY() - 1); // HAUT
                    }
                    if (k == 39) {
                        j.deplacer(j.getX() + 1, j.getY()); // DROITE
                    }
                    if (k == 40) {
                        j.deplacer(j.getX(), j.getY() + 1); // BAS
                    }
                    if (k == 17) { //
                        if (plateau.getCase(j.getX(), j.getY()).getType() == TypeCase.TUNNEL) {
                            j.passageTunnel(); // PASSAGE TUNNEL
                        }
                    }
                }
                this.setEnabled(!(this.isEnabled()));
            } else if (mode == Mode.CREUSER) { // On verifie l'action a faire
                if (j.getActions() > 0) {
                    int k = e.getKeyCode();
                    if (k == 37) {
                        j.creuser(j.getX() - 1, j.getY()); // GAUCHE
                    }
                    if (k == 38) {
                        j.creuser(j.getX(), j.getY() - 1); // HAUT
                    }
                    if (k == 39) {
                        j.creuser(j.getX() + 1, j.getY()); // DROITE
                    }
                    if (k == 40) {
                        j.creuser(j.getX(), j.getY() + 1); // BAS
                    }
                    if (k == 17) {
                        j.creuser(j.getX(), j.getY()); // CONTROLE ou CTRL pour creuser la case actuelle
                    }
                }
                this.setEnabled(!(this.isEnabled()));
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public boolean isEnabled() {
        return enable;
    }

    public void setEnabled(boolean b) {
        this.enable = b;
    }
}