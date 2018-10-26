package me.reid.Grid;

import java.awt.Graphics;

import me.reid.MazeClient;

public class Map {

	private Node[][] nodes;
	public static final int mapSize = 10;
	public static final int nodePixelSize = 30;

	public Map() {
		nodes = new Node[mapSize][mapSize];
		populateNodes();
	}

	// Call once at start of game
	private void populateNodes() {
		for (int nodeX = 0; nodeX < mapSize; nodeX++) {
			for (int nodeY = 0; nodeY < mapSize; nodeY++) {

				int pixelX = nodeX * nodePixelSize;
				int pixelY = nodeY * nodePixelSize;
				if (pixelX > (MazeClient.width + nodePixelSize))
					pixelX = 0;
				if(pixelY > (MazeClient.height + nodePixelSize)) {
					System.out.println("Out of bounds!");
				}
				boolean isWall = false;
				if(pixelY % 60 == 0)
					isWall = true;
				nodes[nodeX][nodeY] = new Node(pixelX, pixelY, isWall);
			}
		}
	}

	public void drawNodes(Graphics g) {
		for (int nodeX = 0; nodeX < mapSize; nodeX++) {
			for (int nodeY = 0; nodeY < mapSize; nodeY++) {
				nodes[nodeX][nodeY].render(g);
			}
		}
	}

}
