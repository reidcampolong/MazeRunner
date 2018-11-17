package me.reid.Entities.Player;

import me.reid.Entities.MoveableEntity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens to key events from Moveable Entity
 */
public class Controller implements KeyListener {

    private MoveableEntity entity;

    public Controller(MoveableEntity entity) {
        this.entity = entity;
    }

    public void keyPressed(KeyEvent e){
        System.out.println("Key event" + (e.getKeyCode()));
        System.out.println(KeyEvent.VK_E);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            entity.moveRight();
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            entity.moveLeft();
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            entity.moveDown();
        else if(e.getKeyCode() == KeyEvent.VK_UP)
            entity.moveUp();
        else if(e.getKeyCode() == KeyEvent.VK_E) {
            System.out.println("Trigger E event");
            entity.customEvent();
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

}
