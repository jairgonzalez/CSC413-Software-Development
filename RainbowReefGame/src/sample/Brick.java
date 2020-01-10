package sample;

import java.awt.*;

public class Brick extends Collidable{
    private int points;

    public Brick(Image img, int x, int y, int score) {
        super(img, x, y);
        this.points = score;
//        width = img.getWidth(null);
       // height = img.getHeight(null);
    }

    public int getPoints()
    {
        return points;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
}