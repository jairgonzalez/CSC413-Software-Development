import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class Tank extends GameObject {

    private int vx;
    private int vy;
    private int angle;

    private final int R = 2;
    private final int ROTATIONSPEED = 3;
    private int health = 100;


    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private long LastFired = 0;
    private long speedBoost = 0;
    private boolean speed_isboosted;
    private String tag;


    private boolean dont_move = false;
    private GameWorld gw;

    void setGW(GameWorld gw_to_set) { //this is needed for spawning bullets
        this.gw = gw_to_set;
    }

    void setHealth(int health_to_set) {
        this.health = health_to_set;
    }


    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.height = 50;
        this.width = 50;
        this.my_rectangle = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    void setTag(String tag_to_set) {
        this.tag = tag_to_set;
    }

    String getTag() {
        return tag;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void ShootPressed() {
        this.ShootPressed = true;
    }

    void Shoot_UN_Pressed() {
        this.ShootPressed = false;
    }


    public void update() {
        this.my_rectangle.setLocation(x, y);

        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }


        if (this.ShootPressed && (System.currentTimeMillis() - LastFired > 1000)) {

            this.SpawnBullet( x , y , vx, vy, angle, gw);
            LastFired = System.currentTimeMillis();

        }
        this.dont_move = false;

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle))) * -1;
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle))) * -1;
        if (System.currentTimeMillis() - speedBoost < 5000 && this.speed_isboosted) {
            vx = (int) Math.round(4 * R * Math.cos(Math.toRadians(angle))) * -1;
            vy = (int) Math.round(4 * R * Math.sin(Math.toRadians(angle))) * -1;

        } else if (this.isSpeed_boosted() && (System.currentTimeMillis() - speedBoost < 5000)) {
            speedBoost = 0;
            this.speed_isboosted = false;
        }
        if (!dont_move) {
            x += vx;
            y += vy;
        }

        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        if (System.currentTimeMillis() - speedBoost < 5000 && speed_isboosted) { // 1 second
            vx = (int) Math.round(4 * R * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(4 * R * Math.sin(Math.toRadians(angle)));
        } else if (this.isSpeed_boosted() && (System.currentTimeMillis() - speedBoost < 5000)) {
            speedBoost = 0;
            this.speed_isboosted = false;
        }
        if (!dont_move) {
            x += vx;
            y += vy;
        }

        checkBorder();
    }

    @Override
    public void setAngle(int angle) {
        this.angle = angle;
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameWorld.WORLD_WIDTH - 88) {
            x = GameWorld.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameWorld.WORLD_HEIGHT - 80) {
            y = GameWorld.WORLD_HEIGHT - 80;
        }
    }


    private void SpawnBullet(int x, int y, int vx, int vy, int angle, GameWorld gw) {
        Bullet blt = new Bullet(x, y, angle);
        blt.setOwner(tag);
        gw.addGame_object(blt);

    }

    public void collision() {
        this.removeHealth(10);

    }

    void addHealth(int val) {
        if (health + val > 100) {
            health = 100;
        } else {
            health += val;
        }

    }

    private void removeHealth(int val) {
        if (health - val < 0) {
            health = 0; //tank died
        } else {
            health -= val;
        }
    }

    int getHealth() {
        return this.health;
    }


    public void drawImage(Graphics2D g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        if (this.health != 0) {
            g.drawImage(this.img, rotation, null);
        }


    }

    Rectangle getOffsetBounds() {  //this is used as a check ahead mechanism to prevent collisions between the Tank and breakable walls before they happen
        return new Rectangle(x + vx, y + vy, 50, 50);
    }


    long getSpeedBoost() {
        return speedBoost;
    }

    void setSpeedBoost(long speedBoost) {
        this.speedBoost = speedBoost;
    }

    private boolean isSpeed_boosted() { //private access because only the tank should be able to check if its speed is boosted(since it controls how large Vx and Vy are)
        return speed_isboosted;
    }

    void setSpeed_boosted(boolean speed_isboosted) {
        this.speed_isboosted = speed_isboosted;
    }
    void setdont_move(boolean dont_move){
        this.dont_move = dont_move;
    }
    boolean getdont_move(){
        return this.dont_move;
    }
}
