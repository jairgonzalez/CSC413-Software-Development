package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class Shell extends Collidable implements Observer {
    //keys for movement
    private final int left, right;
    private boolean LeftPressed, RightPressed;
    private final int LeftBounds;
        private final int RightBounds;

    public Shell(Image img, int x, int y, int leftKey, int rightKey) {
        super(img, x, y);

        this.left = leftKey;
        this.right = rightKey;
        this.LeftPressed =  false;
        this.RightPressed = false;
        this.LeftBounds =   LeftBounds;
        this.RightBounds = RightBounds;
    }

    public void move() {
        if(this.LeftPressed) {
            this.x -= 9;
            checkBorder();
        }
        if(this.RightPressed) {
            this.x += 9;
            checkBorder();
        }

      //  if(this.x < this.LeftBounds)
        //    this.x = this.LeftBounds;
       //if(this.x + this.width > this.RightBounds)
         //   this.x = this.RightBounds -this.width;
    }

    public void checkBorder(){
        if(x<0){
            x=0;
        }
        if(x>=560){
            x=560;
        }

    }

    @Override
    public void update(Observable obj, Object arg){
        GameEvents ge = (GameEvents) arg;
        KeyEvent e = (KeyEvent) ge.event;
        //Left
        if(e.getKeyCode() == left){
            if(e.getID() == KeyEvent.KEY_RELEASED){
                this.LeftPressed  = false;
            } else if (e.getID() == KeyEvent.KEY_PRESSED){
                this.LeftPressed = true;
            }
        }

        //Right
        if(e.getKeyCode() == right){
            if(e.getID() == KeyEvent.KEY_RELEASED){
                this.RightPressed = false;
            }else if (e.getID() == KeyEvent.KEY_PRESSED){
                this.RightPressed = true;
            }
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 80, 30);
    }
}