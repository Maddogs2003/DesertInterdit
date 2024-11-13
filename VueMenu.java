import javax.swing.*;
import java.awt.*;

public class VueMenu extends JPanel {
    public final JButton jouer;
    public final JButton quitter;

    public VueMenu(JFrame framemenu) {
        this.setLayout(new FlowLayout());
        jouer = new JButton("Jouer");
        jouer.setBounds(100, 100, 100, 50);
        jouer.addActionListener(new MenuControleur(this, framemenu));
        this.add(jouer);
        quitter = new JButton("Quitter");
        quitter.setBounds(100, 300, 100, 50);
        quitter.addActionListener(new MenuControleur(this, framemenu));
        this.add(quitter);

        String regles = "But du jeu\n" +
                "Votre equipe d'aventuriers a ete envoyee\n" +
                "en mission pour mettre a jour une cite ensevelie\n" +
                "dans un ancien desert et retrouver une machine\n" +
                "volante legendaire. La rumeur precise meme\n" +
                "qu'elle fonctionnerait a l'energie solaire.\n" +
                "Mais au moment d'arriver a destination,\n" +
                "une tempete de sable inattendue entraine\n" +
                "un atterrissage force de votre helicoptere.\n" +
                "A present bloques dans cette etendue immense\n" +
                "et exposes a une insatiable tempete, votre seul\n" +
                "espoir d'en rechapper est de mettre a jour la cite\n" +
                "rapidement pour trouver les pieces de la machine\n" +
                "volante et la reconstruire. Mais si un membre\n" +
                "de l'equipe meurt de soif ou si la tempete\n" +
                "devient trop forte, toute l'equipe echoue et fera\n" +
                "de belles reliques dans le desert interdit.";

        JTextArea reglesArea = new JTextArea(regles);
        reglesArea.setEditable(false);
        reglesArea.setLineWrap(true);
        reglesArea.setWrapStyleWord(true);
        reglesArea.setPreferredSize(new Dimension(300, 300));
        add(reglesArea);
    }
}
