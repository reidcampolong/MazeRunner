package me.reid.Entities.AI;

import me.reid.Entities.Environment.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Brain for the pathfinding algorithm
 */
public class Pathfinding {

    public final static int LAT_MOVE_COST = 10;
    public final static int DIAG_MOVE_COST = 14;

    private List<Node> openSet;
    private List<Node> closedSet;


    public Pathfinding() {
        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
    }

    public void A_Star(Node start, Node goal) {

    }
}
