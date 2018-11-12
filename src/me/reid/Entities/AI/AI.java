package me.reid.Entities.AI;

import me.reid.Entities.Environment.Node;
import me.reid.Entities.MoveableEntity;
import me.reid.Entities.Player.Controller;
import me.reid.Game;
import me.reid.Grid.Map;

import java.awt.*;

/**
 * Pathfinder entity
 */
public class AI extends MoveableEntity {

    public AI(Game game, Node startingNode) {
        super(game, startingNode);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(getNode().getX() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)),
                getNode().getY() + ((Map.nodePixelSize / 2) - (Map.playerPixelSize/2)), Map.playerPixelSize, Map.playerPixelSize);
    }
}
