package me.reid;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.reid.Entities.AI.AI;
import me.reid.Entities.Environment.Node;
import me.reid.Grid.Map;
import me.reid.Entities.Player.Player;

import static me.reid.Grid.Map.mapSize;

public class Game implements Runnable {

	private MazeClient client;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private Map map;
	private Player player;
	private AI ai;

	public static int nodeSize = 30, playerSize = 20;

	public Game(MazeClient client) {
		this.client = client;
		this.map = new Map();
		this.player = new Player(this, map.getNode(0,0));
		this.ai = new AI(this, map.getNode(5,0));
		System.out.println("X");
		client.addKeyListener(player.getController());
		init();
	}

	public void init() {
		this.running = true;
		this.run();
	}

	public void run() {
		while (running) {
			tick();
			render();
			client.focus();
		}
		stop();
	}

	public void tick() {

	}

	public void render() {
		bs = client.canvas.getBufferStrategy();
		if (bs == null) {
			client.canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// Draw Objects
		map.drawNodes(g);

		// Draw Player
		player.render(g);

		// Draw AI
        ai.render(g);

		// Stop
		bs.show();
		g.dispose();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		this.thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public Node getNode(Node node, int xModification, int yModification) {
        int xPosition = node.getX() / nodeSize, yPosition = node.getY() / nodeSize;
        xPosition = xPosition + xModification;
        yPosition = yPosition + yModification;

		// Check if node is out of bounds
        if((xPosition < 0 || xPosition >= mapSize) || (yPosition < 0 || yPosition >= mapSize))
            return node;
        Node newNode = map.getNode(xPosition,yPosition);

        if(newNode.isWall())
        	return node;

        // Valid new node, return new node
        return map.getNode(xPosition, yPosition);
    }

}
