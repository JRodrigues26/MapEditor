package org.academiadecodigo.altcatras.classes;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Position {
    private int cols;
    private int rows;

    public Position(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = this.cols + cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = this.rows + rows;
    }
}
