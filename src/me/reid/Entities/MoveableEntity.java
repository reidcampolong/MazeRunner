package me.reid.Entities;

import me.reid.Entities.Environment.Node;
import me.reid.Entities.Player.Controller;
import me.reid.Game;
import me.reid.Grid.Drawable;

import java.awt.*;

/**
 * An entity that can be drawn and can be moved programatically.
 */
public abstract class MoveableEntity implements Drawable {

    private Game game;
    private Node currentNode;

    public MoveableEntity(Game game, Node startingNode) {
        this.game = game;
        this.currentNode =  startingNode;
    }

    public void moveRight() {
        changeNodeByCoords(1,0);
    }

    public void moveLeft() {
        changeNodeByCoords(-1,0);
    }

    public void moveUp() {
        changeNodeByCoords(0,-1);
    }

    public void moveDown() {
        changeNodeByCoords(0,1);
    }

    /**
     * Set the current node to new coordinates
     * @param x Offset
     * @param y Offset
     */
    public void changeNodeByCoords(int xModification, int yModification) {
        this.currentNode = game.getNodeByCoordinates(currentNode, xModification, yModification);
    }

    /**
     * Every entity can have their own special action
     */
    public abstract void customEvent();

    /**
     * Draw an entity on screen
     * @param g
     */
    public abstract void render(Graphics g);

    public Game getGame() {
        return game;
    }

    public Node getNode() {
        return currentNode;
    }
}
