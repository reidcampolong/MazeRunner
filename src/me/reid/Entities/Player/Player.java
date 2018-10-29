package me.reid.Entities.Player;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import me.reid.Game;
import me.reid.Grid.Drawable;
import me.reid.Grid.Map;
import me.reid.Entities.Node;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements Drawable {

    private Game game;
    private Controller controller;
    private Node currentNode;

    public Player(Game game, Node startingNode) {
        this.game = game;
        this.currentNode =  startingNode;
        this.controller = new Controller(this);
    }

    public Node getNode() {
        return currentNode;
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(currentNode.getX() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)),
                currentNode.getY() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)), Map.playerPixelSize, Map.playerPixelSize);
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

    public void changeNodeByCoords(int xModification, int yModification) {
        Node nextNode = game.getNode(currentNode, xModification, yModification);
        this.currentNode = (nextNode != null) ? nextNode : currentNode;
    }


    public Controller getController() {
        return controller;
    }

}
