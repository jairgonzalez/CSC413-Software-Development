import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TankControl implements KeyListener {

    private Tank t1;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl(Tank t1, int up, int down, int left, int right, int shoot) {
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.t1.ShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased == up) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased == left) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased == right) {
            this.t1.unToggleRightPressed();
        }
        if (keyReleased == shoot) {
            this.t1.Shoot_UN_Pressed();
        }


    }
}
