package game.component;

import game.Window;
import game.component.entity.Bird;
import game.component.entity.Pipe;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public class GameState {

    private static GameState instance;

    public static GameState getInstance(){
        if (instance == null)
            newGame();

        return instance;
    }

    public static void newGame(){
        instance = new GameState();
    }

    private ArrayList<Pipe> pipes;
    private Bird player;
    private int score;
    private boolean gameOver;

    public GameState(){

        gameOver = false;

        player = new Bird();
        pipes = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            pipes.add(new Pipe(player.getX() + 250 + i * 250));

    }

    public int getScore(){
        return score;
    }

    public Bird getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes(){
        return pipes;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void update(double delta){

        player.update(delta);

        // pipes collide with player
        for (Pipe pipe: pipes)
            if (pipe.collidesWithBird(player))
                gameOver = true;

        // remove pips off screen
        if (pipes.get(0).getX() + pipes.get(0).getWidth() < player.getX() - Window.WINDOW_WIDTH / 3) {
            score++;
            pipes.remove(0);
            pipes.add(new Pipe(pipes.get(pipes.size() - 1).getX() + 250));
        }

        // hits floor or ceiling
        if (player.getY() + player.getHeight() > Window.WINDOW_HEIGHT - Window.WINDOW_HEIGHT / 10 || player.getY() <= 0)
            gameOver = true;

    }

    public void keyPressed(int k){

        if (k == KeyEvent.VK_SPACE)
            player.jump();

    }

    public void render(Graphics g){

        double deltax = player.getX() - (Window.WINDOW_WIDTH / 3 - player.getWidth() / 2);
        double deltay = 0;

        player.render(g, deltax, deltay);

        for (Pipe pipe: pipes)
            pipe.render(g, deltax, deltay);

        g.setColor(Color.GREEN);
        g.fillRect(0, Window.WINDOW_HEIGHT - Window.WINDOW_HEIGHT / 10, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT / 10);

        g.setColor(Color.BLACK);
        g.drawString("" + score, 10, 25);

    }

}
