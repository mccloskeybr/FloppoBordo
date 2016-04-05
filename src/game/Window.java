package game;

import javax.swing.*;
import java.awt.event.KeyListener;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public class Window {

    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 500;

    private JFrame f;

    public Window(JPanel panel) {

        f = new JFrame();
        f.setTitle("Floppo Bordo");
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.add(panel);
        f.setVisible(true);

    }

    public void addKeyListener(KeyListener keyListener){
        f.addKeyListener(keyListener);
    }

}
