package me.reid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.reid.Grid.Map;
import me.reid.Entities.Player.Player;

public class Game implements Runnable {

	private MazeClient client;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private Map map;
	private Player player;

	public static int nodeSize = 30, playerSize = 20;

	public Game(MazeClient client) {
		this.client = client;
		this.map = new Map();
		this.player = new Player(map.getNode(0,0));
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

}
