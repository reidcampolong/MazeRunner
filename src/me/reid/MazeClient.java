package me.reid;

import me.reid.Grid.Map;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MazeClient {

	private JFrame frame;
	public Canvas canvas;

	public static int width = 300, height = 320;

	public static void main(String[] args) {
		width = (Map.nodePixelSize * Map.mapSize);
		height = width;
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
		canvas.addMouseListener(new WallCreator());
        frame.add(canvas);

        frame.getContentPane().setBackground(Color.black);


		new Game(this);
	}

	public void addKeyListener(KeyListener listener) {
	    frame.addKeyListener(listener);
    }
    public void addClickListener(MouseListener listener) {frame.addMouseListener(listener);}

    public void focus() {
	    frame.requestFocusInWindow();
    }

}
