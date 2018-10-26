package me.reid;

import java.awt.Canvas;

import javax.swing.JFrame;

public class MazeClient {

	private JFrame frame;
	public Canvas canvas;
	
	// 330 is a test value, not sure why it doesnt work
	public static int width = 300, height = 320;

	public static void main(String[] args) {
		MazeClient client = new MazeClient();
		client.create();
	}

	public void create() {
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		canvas = new Canvas();
		canvas.setSize(600, 600);
		frame.add(canvas);

		new Game(this);
	}

}
