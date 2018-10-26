package me.reid.Grid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Node {

	private Color color;
	private boolean isWall;
	private int x, y;

	public Node(int x, int y, boolean isWall) {
		this.x = x;
		this.y = y;
		this.isWall = isWall;
		
		this.color = isWall ? Color.black : Color.white;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, Map.nodePixelSize, Map.nodePixelSize);
	}

}
