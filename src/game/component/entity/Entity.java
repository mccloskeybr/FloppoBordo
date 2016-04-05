package game.component.entity;

import java.awt.*;

/**
 * Created by mccloskeybr on 4/1/16.
 */
public abstract class Entity {

    private double x;
    private double y;
    private int width;
    private int height;

    public Entity(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public abstract void update(double delta);

    public abstract void render(Graphics g, double deltax, double deltay);

}
