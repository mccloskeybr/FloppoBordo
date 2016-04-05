package game;

import game.component.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public class Main extends JPanel {

    private long oldTime;
    private boolean cap;

    public Main(){

        cap = true;

        Window window = new Window(this);

        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_Z)
                    cap = !cap;

                GameState.getInstance().keyPressed(e.getKeyCode());

            }

        });

    }

    public void update(){

        double delta = System.currentTimeMillis() - oldTime;

        if (cap && delta > 0.02)
            delta = 0.02;

        oldTime = System.currentTimeMillis();

        GameState.getInstance().update(delta);

        repaint();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        GameState.getInstance().render(g);

    }

}
