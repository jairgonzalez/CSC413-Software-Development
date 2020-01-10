package sample;

import java.awt.*;

public class Star extends Collidable{
    private double VerticalSpeed;
    private double HorizontalSpeed;

    public Star(Image img, int x, int y) {
        super(img, x, y);
        VerticalSpeed = 4;
        HorizontalSpeed = 3;

        width = img.getWidth(null);
        height = img.getHeight(null);
    }

    public void update(){

        VerticalSpeed += .1; // gravity

        x += (int)Math.round(HorizontalSpeed);
        y += (int)Math.round(VerticalSpeed);

        if(x < 0) {
            HorizontalSpeed = -HorizontalSpeed;
        }

        if(y < 0) {
            VerticalSpeed = -VerticalSpeed;
        }

        if(x > Application.ScreenWidth - 35) {
            HorizontalSpeed = -HorizontalSpeed;
        }

        if(y > Application.ScreenHeight - 35) {
            VerticalSpeed = -VerticalSpeed;
        }

    }


    public void setVerticalSpeed(double verticalSpeed) {
        VerticalSpeed = verticalSpeed;
        VerticalSpeed -= 0.3;
    }

    public double getVerticalSpeed() {
        return VerticalSpeed;
    }

    public Rectangle getRect() {

        return new Rectangle(x, y, getWidth(), getHeight());
    }
}
