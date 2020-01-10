import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {
    private int health = 100;
    private static BufferedImage breakable_wall_img;
    private boolean dead = false;

    BreakableWall(int x, int y) {
        this.x = x;
        this.y = y;
        this.my_rectangle = new Rectangle(x, y, breakable_wall_img.getWidth(), breakable_wall_img.getHeight());
    }

    private void addHealth(int val) {
        if (health + val > 100) {
            health = 100;
        } else {
            health += val;
        }

    }

     private void removeHealth(int val) {
        if (health - val < 0) {
            health = 0;
            dead = true;
        } else {
            health -= val;
        }
    }

    int getHealth() {
        return this.health;
    }

    boolean isDead() {
        return dead;
    }

    void setDead(boolean dead) {
        this.dead = dead;
    }

    static void set_breakable_wall_img(BufferedImage image) {
        BreakableWall.breakable_wall_img = image;
    }

    public void update() {

    }

    public void collision() {
        this.removeHealth(50);

    }

    public void drawImage(Graphics2D g2d) {

        if (!dead) {
            g2d.drawImage(breakable_wall_img, x, y, null);
        }

    }


}
