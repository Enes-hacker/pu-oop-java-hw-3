package Piece;

import java.awt.*;

public class Guard {
    private int row;
    private int col;
    private String color;
    private int point;

    public Guard(int row, int col, Color green) {
        this.row = row;
        this.col = col;
    }

    public void render (Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(0,0,10,10);
    }
}
