package game.component.entity;

import game.*;
import game.Window;

import java.awt.*;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public class Bird extends Entity {

    private double deltaY;
    private double deltaX;

    public Bird() {
        super(0, Window.WINDOW_HEIGHT / 2, 20, 20);

        deltaX = 7;
    }

    public void jump(){
        deltaY = -25;
    }

    @Override
    public void update(double delta) {

        setX(getX() + delta * deltaX);
        setY(getY() + delta * deltaY);

        deltaY += delta * 3;

    }

    @Override
    public void render(Graphics g, double deltax, double deltay) {

        g.setColor(Color.BLUE);
        g.fillRect((int) getX() - (int) deltax, (int) getY() - (int) deltay, getWidth(), getHeight());

    }
}
