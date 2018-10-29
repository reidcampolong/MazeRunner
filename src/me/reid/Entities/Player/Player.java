package me.reid.Entities.Player;

import me.reid.Game;
import me.reid.Grid.Drawable;
import me.reid.Grid.Map;
import me.reid.Entities.Node;

import java.awt.*;

public class Player implements Drawable {

    private Node currentNode;

    public Player(Node startingNode) {
        this.currentNode =  startingNode;
    }

    public Node getNode() {
        return currentNode;
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(currentNode.getX() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)),
                currentNode.getY() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)), Map.playerPixelSize, Map.playerPixelSize);
        //g.fillRect(, currentNode.getY() + 5, Map.playerPixelSize, Map.nodePixelSize - 10);
    }

}
