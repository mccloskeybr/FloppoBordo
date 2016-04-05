package game.component.entity;

import game.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public class Pipe extends Entity {

    private ArrayList<Rectangle> components;
    private int height;

    public Pipe(double x) {
        super(x, 0, 100, -1);

        components = new ArrayList<>();

        int r = new Random().nextInt(3);
        if (r == 0)
            height = 50;
        else if (r == 1)
            height = 150;
        else
            height = 200;

        components.add(new Rectangle((int) x, 0, 100, height));
        components.add(new Rectangle((int) x, height + 150, 100, Window.WINDOW_HEIGHT - (height) + 150));

    }

    public int getHeight(){
        return height;
    }

    public ArrayList<Rectangle> getComponents(){
        return components;
    }

    public boolean collidesWithBird(Bird player){

        for (Rectangle rect: components)
            if (rect.contains(player.getX() + 10, player.getY() + 10))
                return true;

        return false;

    }

    @Override
    public void update(double delta) {}

    @Override
    public void render(Graphics g, double deltax, double deltay) {

        g.setColor(Color.BLACK);

        for (Rectangle rectangle: components)
            g.fillRect(rectangle.x - (int) deltax, rectangle.y - (int) deltay, rectangle.width, rectangle.height);

    }
}
