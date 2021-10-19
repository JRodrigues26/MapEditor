package org.academiadecodigo.altcatras;

import org.academiadecodigo.altcatras.classes.Cursor;
import org.academiadecodigo.altcatras.classes.FileCreator;
import org.academiadecodigo.altcatras.classes.MapCreator;

import java.io.IOException;

public class Game {

    private MapCreator map;
    private Cursor cursor;

    public Game(int cols, int rows) throws IOException {
        map = new MapCreator(cols, rows);
        cursor = new Cursor(map);
    }

    public void start() {
        map.draw();
        cursor.draw();
    }


}
