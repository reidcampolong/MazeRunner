package me.reid.Grid;

import java.awt.Graphics;

import me.reid.Entities.Environment.EndBlock;
import me.reid.Entities.Environment.Node;
import me.reid.MazeClient;

public class Map {

	private Node[][] nodes;
	public static final int mapSize = 20;
	public static final int nodePixelSize = 30;
	public static final int playerPixelSize = 20;

	/** The finish line block **/
	private EndBlock endBlock;

	public Map() {
		nodes = new Node[mapSize][mapSize];
		populateNodes();
	}

    /**
     * Populate nodes at start of game
     */
	private void populateNodes() {
		for (int nodeX = 0; nodeX < mapSize; nodeX++) {
			for (int nodeY = 0; nodeY < mapSize; nodeY++) {

				int pixelX = nodeX * nodePixelSize;
				int pixelY = nodeY * nodePixelSize;
				if (pixelX > (MazeClient.width + nodePixelSize))
					pixelX = 0;
				if(pixelY > (MazeClient.height + nodePixelSize))
					System.out.println("Out of bounds!");
				nodes[nodeX][nodeY] = new Node(pixelX, pixelY, false);
			}
		}
		createEndBlock();
	}

	private void createEndBlock() {
	    int row = mapSize - 1;
	    int col = (mapSize/2) - 1;
        nodes[row][col] = new EndBlock(row * nodePixelSize, col * nodePixelSize, true);
    }

    /**
     * Render all nodes
     * @param g
     */
	public void drawNodes(Graphics g) {
		for (int nodeX = 0; nodeX < mapSize; nodeX++) {
			for (int nodeY = 0; nodeY < mapSize; nodeY++) {
				nodes[nodeX][nodeY].render(g);
			}
		}
	}

    /**
     * Get a node from coordinates
     * @param x
     * @param y
     * @return Node
     */
	public Node getNode(int x, int y) {
		return nodes[x][y];
	}

}
