package org.academiadecodigo.altcatras;

import org.academiadecodigo.altcatras.classes.Cursor;
import org.academiadecodigo.altcatras.classes.Map;

import java.io.IOException;

public class MapEditor {

    private Map map;
    private Cursor cursor;

    public MapEditor(int cols, int rows) throws IOException {
        map = new Map(cols, rows);
        cursor = new Cursor(map);
    }

    public void start() {
        map.draw();
        cursor.draw();
    }


}
