package me.reid.Grid;

import me.reid.Entities.Environment.Node;
import me.reid.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WallCreator implements MouseListener {

    private Game game;

    public WallCreator(Game game) {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node node = game.getNodeByPixels(e.getX(), e.getY());
        node.setWall(!node.isWall());
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
