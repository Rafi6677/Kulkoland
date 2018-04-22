import javax.swing.*;
import java.awt.*;

public class Rysownik extends JFrame
{
    public static void main(String[] args) {

        JFrame frame = new JFrame("Kulkoland");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Kula());

        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);

    }
}
