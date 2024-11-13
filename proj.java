import java.awt.*;
import javax.swing.JFrame;

class proj {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            JFrame framemenu = new JFrame();
            framemenu.setTitle("Menu Jeu du Desert Interdit");
            framemenu.setLayout(new FlowLayout());
            VueMenu menu = new VueMenu(framemenu);
            framemenu.add(menu);
            framemenu.pack();
            framemenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framemenu.setLocationRelativeTo(null);
            framemenu.setVisible(true);

        });
    }
}
