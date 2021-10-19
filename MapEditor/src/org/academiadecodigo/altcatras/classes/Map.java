package org.academiadecodigo.altcatras.classes;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;

public class Map {

    private static final int PADDING = 10;
    private static final int CELLSIZE = 25;

    private int cols;
    private int rows;
    private Rectangle grid;
    private int width;
    private int height;
    private LinkedList<Rectangle> cells;

    public Map(int cols, int rows) {
        cells = new LinkedList<>();
        this.cols = cols;
        this.rows = rows;
        this.width = this.cols * CELLSIZE;
        this.height = this.rows * CELLSIZE;
        this.grid = new Rectangle(PADDING, PADDING, this.width, this.height);
    }

    public void draw() {
        this.grid.draw();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Rectangle rectangle = new Rectangle(PADDING + CELLSIZE * j, PADDING + CELLSIZE * i, CELLSIZE, CELLSIZE);
                rectangle.draw();
                cells.add(rectangle);
            }
        }
    }

    public LinkedList<Rectangle> getCells() {
        return cells;
    }

    public static int getPADDING() {
        return PADDING;
    }

    public static int getCELLSIZE() {
        return CELLSIZE;
    }

    public int getCOLS() {
        return this.cols;
    }

    public int getROWS() {
        return this.rows;
    }

    public Rectangle getGrid() {
        return grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int colsToX(int cols) {
        int x = this.cols * CELLSIZE;
        return x + PADDING;
    }

    public int rowsToY(int rows) {
        int y = this.rows * CELLSIZE;
        return y + PADDING;
    }

}
