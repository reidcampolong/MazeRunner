package me.reid.Entities.AI;

import me.reid.Entities.Environment.Node;
import me.reid.Game;
import me.reid.Grid.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Brain for the pathfinding algorithm
 */
public class Pathfinding {

    public final static int LAT_MOVE_COST = 10;

    private List<Node> path;
    private List<Node> openSet;
    private List<Node> closedSet;

    private Map map;

    public Pathfinding(Map map) {
        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
        this.path = new ArrayList<>();
        this.map = map;
    }

    public static void start(Game game, Node aiPosition) {
        Pathfinding p = new Pathfinding(game.getMap());
        p.A_Star(aiPosition, game.getMap().getEndBlock());
    }

    public int heuristic_cost_estimate(Node start, Node goal) {
        int x1 = start.getCordX(), y1 = start.getCordY();
        int x2 = goal.getCordX(), y2 = goal.getCordY();
        return LAT_MOVE_COST * (Math.abs(x2 - x1) + Math.abs(y2 - y1));
    }

    public void tracePath(Node start, Node goal) {
        for(Node[] n : map.getNodes()) {
            for(Node n2 : n){
                if(n2.getColor() == Color.YELLOW || n2.getColor() == Color.CYAN)
                    n2.setColor(Color.white);
            }
        }
        Node current = goal;
        while(!current.equals(start)) {
            path.add(current);
            current = current.getPathfindingParent();
        }

        for(Node n : path)
            n.setColor(Color.cyan);
    }

    /**
     * G Score - start -> current node
     * H Score - current node -> end
     * F Score - G + H (cost)
     *
     * @param start
     * @param goal
     */
    public void A_Star(Node start, Node goal) {
        // TODO set this to AI's position
        System.out.println("Start: " + nodeCord(start) + " End: " + nodeCord(goal));
        // Set of nodes already evaluated
        closedSet.clear();

        // Discovered nodes that are not evaluated yet
        // Open node starts with the beginning node
        openSet.add(start);
        start.setGValue(0);
        start.setHValue(heuristic_cost_estimate(start, goal));

        while (openSet.size() > 0) {
            Node current = openSet.get(0);

            // Get the node with the LOWEST f score
            for (int i = 1; i < openSet.size(); i++) {
                Node nextNode = openSet.get(i);
                if(!closedSet.contains(nextNode))
                    calculateCosts(start, goal, nextNode);
                if (nextNode.getFCost() < current.getFCost() || (nextNode.getFCost() == current.getFCost() && (nextNode.getHValue() < current.getHValue()))) {
                    current = nextNode;
                }
            }

            openSet.remove(current);
            closedSet.add(current);

            if (current.equals(goal)) {
                // Retrace our steps of the path!
                tracePath(start, goal);
                return;
            }


            // Through neighbors
            for (Node n : getNeighbors(current)) {

                // Already evaluated
                if(n.isWall() || closedSet.contains(n)) {
                    //System.out.println(nodeCord(n) + " already evaluated");
                    continue;
                }

                n.setColor(Color.YELLOW);

                int tentative_gScore = current.getGValue() + heuristic_cost_estimate(current, n);
                //System.out.println("Tentative: " + tentative_gScore + " Compared to " + n.getGValue());
                if(tentative_gScore < n.getGValue() || !openSet.contains(n)) {
                    n.setGValue(tentative_gScore);
                    n.setHValue(heuristic_cost_estimate(n, goal));
                    n.setPathfindingParent(current);

                    if(!openSet.contains(n))
                        openSet.add(n);
                }
            }

        }

    }

    public List<Node> getNeighbors(Node n) {
        List<Node> neighbors = new ArrayList<>();
        int x = n.getCordX(), y = n.getCordY();

        if (!map.isCoordinatesOutofBounds(x + 1, y))
            neighbors.add(map.getNode(x + 1, y));
        if (!map.isCoordinatesOutofBounds(x - 1, y))
            neighbors.add(map.getNode(x - 1, y));
        if (!map.isCoordinatesOutofBounds(x, y + 1))
            neighbors.add(map.getNode(x, y + 1));
        if (!map.isCoordinatesOutofBounds(x, y - 1))
            neighbors.add(map.getNode(x, y - 1));

        return neighbors;
    }

    private void calculateCosts(Node start, Node goal, Node node) {
        node.setGValue(heuristic_cost_estimate(start, node));
        node.setHValue(heuristic_cost_estimate(node, goal));
    }

    private String nodeCord(Node n) {
        return n.getCordX() + ";"+n.getCordY();
    }
}
