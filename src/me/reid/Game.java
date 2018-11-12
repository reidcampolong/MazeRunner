package me.reid;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.reid.Entities.AI.AI;
import me.reid.Entities.Environment.Node;
import me.reid.Grid.Map;
import me.reid.Entities.Player.Player;
import me.reid.Grid.WallCreator;

/**
 * This is the game instance. Contains running loop, rendering,
 * logic etc.
 */
public class Game implements Runnable {

	private MazeClient client;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private Map map;
	private Player player;
	private AI ai;

	public Game(MazeClient client) {
		this.client = client;
		this.map = new Map();
		this.player = new Player(this, map.getNode(0,0));
		this.ai = new AI(this, map.getNode(5,0));

		client.addKeyListener(player.getController());
		client.addMouseListener(new WallCreator(this));
		init();
	}

    /**
     * Start running the game
     */
	public void init() {
		this.running = true;
		this.run();
	}

    /**
     * Game run loop
     */
	public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
		stop();
	}

	public void tick() {

	}

    /**
     * Draw objects to screen
     */
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

    /**
     * Starts the game thread
     */
	public synchronized void start() {
		if (running)
			return;
		running = true;
		this.thread = new Thread(this);
		thread.start();
	}

    /**
     * Stops the game
     */
	public synchronized void stop() {
		if (!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    /**
     * Returns a new node in relation to current Node.
     * @param node
     * @param xModification
     * @param yModification
     * @return New node offset from @node's position by (x,y)
     */
    public Node getNodeByCoordinates(Node node, int xModification, int yModification) {
        int xPosition = node.getX() / Map.nodePixelSize, yPosition = node.getY() / Map.nodePixelSize;
        xPosition = xPosition + xModification;
        yPosition = yPosition + yModification;

		// Check if node is out of bounds, if so return current node
        if(map.isCoordinatesOutofBounds(xPosition, yPosition))
            return node;

        Node newNode = map.getNode(xPosition,yPosition);

        if(newNode.isWall())
        	return node;

        // Valid new node, return new node
        return map.getNode(xPosition, yPosition);
    }

    /**
     * Returns a new node in relation to screen coordinates
     * @param pixelX
     * @param pixelY
     * @return New node at pixel x, y
     */
    public Node getNodeByPixels(int pixelX, int pixelY) {
        int xPosition = (int) Math.floor(pixelX / 30);
        int yPosition = (int) Math.floor(pixelY / 30);
        if(!map.isCoordinatesOutofBounds(xPosition, yPosition)) {
            return map.getNode(xPosition, yPosition);
        }
        return null;
    }

}
