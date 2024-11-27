import javax.swing.*;
import java.awt.*;

public class VueMenu extends JPanel {
    public final JButton jouer;
    public final JButton quitter;
    // ImageIcon title; commented out because don't succeed to load the file

    public VueMenu(JFrame framemenu) {
        Dimension dim = new Dimension(700, 450); // 1366,768
        this.setPreferredSize(dim);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // this.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
        /*
         * try {
         * title = new ImageIcon(getClass().getResource("title.png"));
         * JLabel image = new JLabel(title);
         * this.add(image);
         * } catch (Exception e) {
         * System.out.println("title.png is missing");
         * }
         */ // commented out because don't succeed to load the file
        int space = 25;
        JLabel title = new JLabel("LE DESERT INTERDIT");
        title.setFont(new Font("Dialog", Font.BOLD, 30));
        title.setForeground(new Color(219, 112, 41));
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, space)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, space)));
        jouer = new JButton("Jouer");
        jouer.setBounds(100, 100, 100, 50);
        jouer.addActionListener(new MenuControleur(this, framemenu));
        jouer.setAlignmentX(CENTER_ALIGNMENT);
        this.add(jouer);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        quitter = new JButton("Quitter");
        quitter.setBounds(100, 300, 100, 50);
        quitter.addActionListener(new MenuControleur(this, framemenu));
        quitter.setAlignmentX(CENTER_ALIGNMENT);
        this.add(quitter);
        this.add(Box.createRigidArea(new Dimension(0, space)));

        String regles = "But du jeu\n" +
                "Votre equipe d'aventuriers a ete envoyee " +
                "en mission pour mettre a jour une cite ensevelie " +
                "dans un ancien desert et retrouver une machine " +
                "volante legendaire. La rumeur precise meme " +
                "qu'elle fonctionnerait a l'energie solaire. " +
                "Mais au moment d'arriver a destination, " +
                "une tempete de sable inattendue entraine " +
                "un atterrissage force de votre helicoptere. " +
                "A present bloques dans cette etendue immense " +
                "et exposes a une insatiable tempete, votre seul " +
                "espoir d'en rechapper est de mettre a jour la cite " +
                "rapidement pour trouver les pieces de la machine " +
                "volante et la reconstruire. Mais si un membre " +
                "de l'equipe meurt de soif ou si la tempete " +
                "devient trop forte, toute l'equipe echoue et fera " +
                "de belles reliques dans le desert interdit.";

        JTextArea reglesArea = new JTextArea(regles);
        reglesArea.setLineWrap(true);
        reglesArea.setWrapStyleWord(true);
        reglesArea.setEditable(false);
        add(reglesArea);
    }
}
