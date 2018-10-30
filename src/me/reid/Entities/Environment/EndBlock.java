package me.reid.Entities.Environment;

import java.awt.*;

public class EndBlock extends Node {

    public EndBlock(int x, int y, boolean isWall) {
        super(x, y, isWall);
        setColor(Color.RED);
    }
}
