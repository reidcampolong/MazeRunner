package me.reid.Entities.Environment;

import me.reid.Grid.Drawable;
import me.reid.Grid.Map;

import java.awt.*;

/**
 * Stored in a grid space, holds state of grid space.
 */
public class Node implements Drawable {

    // Pathfinding resources
    private int HValue;
    private int GValue;
    private Node pathfindingParent;

    private Color color;
    private boolean isWall;
    private int x, y;

    public Node(int x, int y, boolean isWall) {
        this.x = x;
        this.y = y;
        this.isWall = isWall;

        this.color = Color.white;
    }

    public int getCordX() {
        return this.x / Map.nodePixelSize;
    }

    public int getCordY() {
        return this.y / Map.nodePixelSize;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isWall() {
        return this.isWall;
    }

    public void setWall(boolean wall) {
        if (wall) {
            this.setColor(Color.BLACK);
            this.isWall = true;
        } else {
            this.setColor(Color.white);
            this.isWall = false;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, Map.nodePixelSize, Map.nodePixelSize);
        g.setColor(color);
        g.fillRect(x + 1, y + 2, Map.nodePixelSize - 2, Map.nodePixelSize - 2);
    }

    // Pathfinding parts
    public void setGValue(int value) {
        this.GValue = value;
    }
    public int getGValue() {
        return GValue;
    }

    public void setHValue(int value) {
        this.HValue = value;
    }
    public int getHValue() {
        return HValue;
    }

    public int getFCost() {
        return getGValue() + getHValue();
    }

    public Node getPathfindingParent() {
        return pathfindingParent;
    }

    public void setPathfindingParent(Node node){
        this.pathfindingParent = node;
    }


}
