package me.reid;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WallCreator implements MouseListener {

   private Game game;
    public WallCreator(Game game) {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.getNodeByPixels(e.getX(), e.getY()).setColor(Color.ORANGE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
