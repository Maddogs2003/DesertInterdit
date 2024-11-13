
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VueCommandes extends JPanel implements Observer {

    private JFrame framejeu;
    private JFrame framemenu;
    private Desert plateau;
    private JButton Findetour;
    private JButton Bouger;
    private KeyControleur key;
    private KeyControleur key2;
    private JButton Creuser;
    private JButton Exploration;
    private JButton Ramasser;
    private JButton Enfuir;
    private JButton Quitter;

    public VueCommandes(Desert plateau, JFrame framejeu, JFrame framemenu) {
        this.framejeu = framejeu;
        this.framemenu = framemenu;
        this.plateau = plateau;
        plateau.addObserver(this);
        Dimension dim = new Dimension(200, 900); // 1366,768
        this.setPreferredSize(dim);
        paintComponent();
    }

    public void paintComponent() {
        setLayout(null);
        Findetour = new JButton("Fin de tour"); // Bouton fin de tour
        Findetour.setBounds(0, 300, 100, 25);
        add(Findetour);
        ControleurFinDeTour FinTour = new ControleurFinDeTour(plateau);
        Findetour.addActionListener(FinTour);
        Bouger = new JButton("Bouger"); // Bouton Exploration (une fois presse permet d'entrer une action au clavier
                                        // (haut,bas,droite,gauche) avec les flèches)= deplacement.
        Bouger.setBounds(0, 330, 100, 25);
        add(Bouger);
        key = new KeyControleur(plateau, Mode.DEPLACER);
        Bouger.addKeyListener(key);
        Bouger.addActionListener(new BougerControleur(this, key));
        Creuser = new JButton("Creuser"); // bouton action de creuser
        Creuser.setBounds(0, 360, 100, 25);
        add(Creuser);
        key2 = new KeyControleur(plateau, Mode.CREUSER);
        Creuser.addKeyListener(key2); // A partir de ce bouton, j'utilise des Actions Listener définie de manière
                                      // interne pour ne pas avoir à créer un fichier et une classe par bouton
        Creuser.addActionListener(new ActionListener() { // Déclaration interne de la reaction à la pression du bouton
            public void actionPerformed(ActionEvent e) {
                key2.setEnabled(!(key2.isEnabled()));
            }
        });
        Exploration = new JButton("Exploration"); // bouton action d'explorer la case actuelle
        Exploration.setBounds(0, 390, 100, 25);
        add(Exploration);
        Exploration.addActionListener(new ActionListener() { // Déclaration interne de la reaction à la pression du
                                                             // bouton
            public void actionPerformed(ActionEvent e) {
                plateau.getJoueur()[plateau.getJoueurActuel()].explorer();
            }
        });

        Ramasser = new JButton("Ramasser");
        Ramasser.setBounds(0, 420, 100, 25);
        add(Ramasser);
        Ramasser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plateau.getJoueur()[plateau.getJoueurActuel()].ramasserPiece();
            }
        });
        Enfuir = new JButton("S'Enfuir");
        Enfuir.setBounds(0, 450, 100, 25);
        add(Enfuir);
        Enfuir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (plateau.partieGagnee()) {
                    Findetour.setEnabled(false);
                    Creuser.setEnabled(false);
                    Bouger.setEnabled(false);
                    Enfuir.setEnabled(false);
                    Exploration.setEnabled(false);
                    Ramasser.setEnabled(false);
                    JLabel end = new JLabel(); // Texte en cas de Victoire
                    end.setBounds(0, 425, 300, 150);
                    JOptionPane.showMessageDialog(framejeu, "Vous avez Gagnes !");
                    end.setText("Good Job c:");
                    add(end);
                    repaint();
                }
            }
        });
        Quitter = new JButton("Quitter");
        Quitter.setBounds(0, 800, 100, 25);
        add(Quitter);
        Quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                framejeu.dispose();
                framemenu.setVisible(true);
            }
        });
    }

    public void update() {
        int sable_tot = 0;
        boolean dead = false;
        for (int i = 0; i < plateau.getTaille(); i++) {
            for (int j = 0; j < plateau.getTaille(); j++) {
                sable_tot = sable_tot + (plateau.getCase(i, j).getSable());
            }
        }
        for (Joueur j : plateau.getJoueur()) {
            if (j.getNiveauEau() <= 0) {
                dead = true;
            }
        }
        if (sable_tot >= 43 || plateau.getTempete() >= 7 || dead) {
            JLabel end = new JLabel(); // Texte en cas de defaite
            JLabel how = new JLabel(); // Texte en cas de defaite
            end.setBounds(0, 425, 300, 150);
            end.setText("Vous avez perdu,");
            add(end);
            if (sable_tot >= 43) {
                Findetour.setEnabled(false);
                Creuser.setEnabled(false);
                Bouger.setEnabled(false);
                Enfuir.setEnabled(false);
                Exploration.setEnabled(false);
                Ramasser.setEnabled(false);
                how.setText("par ensablement !");
                how.setBounds(0, 450, 300, 150);
            } else if (plateau.getTempete() >= 7) {
                Findetour.setEnabled(false);
                Creuser.setEnabled(false);
                Bouger.setEnabled(false);
                Enfuir.setEnabled(false);
                Exploration.setEnabled(false);
                Ramasser.setEnabled(false);
                how.setText("a cause de la tempete!");
                how.setBounds(0, 450, 300, 150);
            } else if (dead) {
                Findetour.setEnabled(false);
                Creuser.setEnabled(false);
                Bouger.setEnabled(false);
                Enfuir.setEnabled(false);
                Exploration.setEnabled(false);
                Ramasser.setEnabled(false);
                how.setText("l'un d'entre vous est mort !");
                how.setBounds(0, 450, 300, 150);
            }
            add(how);
            repaint();
        }
    }
}