package sample;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShellControl extends KeyAdapter {

    private GameEvents events;

    public ShellControl(GameEvents events){
        this.events = events;
    }

    @Override
    public void keyPressed(KeyEvent e){
        events.setKey(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
        events.setKey(e);
    }
}