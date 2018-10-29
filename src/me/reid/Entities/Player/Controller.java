package me.reid.Entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private Player player;

    public Controller(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.moveRight();
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            player.moveLeft();
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            player.moveDown();
        else if(e.getKeyCode() == KeyEvent.VK_UP)
            player.moveUp();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

}
