package me.reid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.reid.Grid.Map;

public class Game implements Runnable {

	private MazeClient client;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private Map map;

	public Game(MazeClient client) {
		this.client = client;
		this.map = new Map();
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
		// Stop
		bs.show();
		g.setColor(Color.BLUE);
		g.drawRect(10, 10, 10, 20);
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
