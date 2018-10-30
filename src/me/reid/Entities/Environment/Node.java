package me.reid.Entities.Environment;

import me.reid.Grid.Drawable;
import me.reid.Grid.Map;

import java.awt.*;

public class Node implements Drawable {

	private Color color;
	private boolean isWall;
	private int x, y;

	public Node(int x, int y, boolean isWall) {
		this.x = x;
		this.y = y;
		this.isWall = isWall;

		this.color = Color.white;
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

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x,y,Map.nodePixelSize,Map.nodePixelSize);
		g.setColor(color);
		g.fillRect(x+1, y+2, Map.nodePixelSize-2, Map.nodePixelSize-2);
	}

}
