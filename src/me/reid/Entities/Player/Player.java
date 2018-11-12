package me.reid.Entities.Player;

import me.reid.Entities.MoveableEntity;
import me.reid.Game;
import me.reid.Grid.Map;
import me.reid.Entities.Environment.Node;

import java.awt.*;

/**
 * Player controlled by keyboard events
 */
public class Player extends MoveableEntity {

    private Controller controller;

    public Player(Game game, Node startingNode) {
        super(game, startingNode);
        this.controller = new Controller(this);
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(getNode().getX() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)),
                getNode().getY() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)), Map.playerPixelSize, Map.playerPixelSize);
    }

    public Controller getController() {
        return controller;
    }

}
