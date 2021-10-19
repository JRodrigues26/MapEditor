package org.academiadecodigo.altcatras.classes;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


import java.io.IOException;
import java.util.LinkedList;


public class Cursor implements KeyboardHandler {

    private Rectangle cursor;
    private Rectangle cursorBorder;
    private Keyboard keyboard;
    private Position position;
    private MapCreator map;
    private LinkedList<Rectangle> allCells;
    private Color[] colors;
    private int currentColor = 0;
    private FileCreator fileCreator;
    private boolean paint = false;


    public Cursor(MapCreator map) throws IOException {
        this.position = new Position(0, 0);
        this.map = map;
        this.fileCreator = new FileCreator();
        this.colors = new Color[]{Color.BLUE, Color.GREEN, Color.BLACK, Color.RED, Color.YELLOW, Color.ORANGE, Color.GRAY, Color.PINK, Color.CYAN, Color.MAGENTA};
        this.allCells = map.getCells();
        this.cursor = new Rectangle(MapCreator.getPADDING(), MapCreator.getPADDING(), MapCreator.getCELLSIZE(), MapCreator.getCELLSIZE());
        this.cursor.setColor(Color.BLACK);
        this.cursorBorder = new Rectangle(MapCreator.getPADDING() - 2, MapCreator.getPADDING() - 2, MapCreator.getCELLSIZE() + 4, MapCreator.getCELLSIZE() + 4);
        this.cursorBorder.setColor(colors[currentColor]);
        this.keyboard = new Keyboard(this);
    }

    public void draw() {
        //draw the cursor
        this.cursorBorder.fill();
        this.cursor.fill();

        //create events

        //movement
        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        moveUp.setKey(KeyboardEvent.KEY_UP);

        //interact
        KeyboardEvent draw = new KeyboardEvent();
        draw.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        draw.setKey(KeyboardEvent.KEY_SPACE);

        KeyboardEvent stopDraw = new KeyboardEvent();
        stopDraw.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        stopDraw.setKey(KeyboardEvent.KEY_SPACE);

        KeyboardEvent erase = new KeyboardEvent();
        erase.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        erase.setKey(KeyboardEvent.KEY_C);
        KeyboardEvent deleteAll = new KeyboardEvent();
        deleteAll.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        deleteAll.setKey(KeyboardEvent.KEY_D);

        //set color
        KeyboardEvent changeColor = new KeyboardEvent();
        changeColor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        changeColor.setKey(KeyboardEvent.KEY_V);

        //copy and paste
        KeyboardEvent save = new KeyboardEvent();
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        save.setKey(KeyboardEvent.KEY_0);
        KeyboardEvent paste = new KeyboardEvent();
        paste.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        paste.setKey(KeyboardEvent.KEY_1);


        keyboard.addEventListener(moveLeft);
        keyboard.addEventListener(moveRight);
        keyboard.addEventListener(moveDown);
        keyboard.addEventListener(moveUp);

        keyboard.addEventListener(draw);
        keyboard.addEventListener(stopDraw);

        keyboard.addEventListener(erase);
        keyboard.addEventListener(deleteAll);
        keyboard.addEventListener(changeColor);
        keyboard.addEventListener(save);
        keyboard.addEventListener(paste);
    }

    //needs update
    public void changeColor() {
        if (currentColor == 9) {
            currentColor = -1;
        }
        currentColor++;
        this.cursorBorder.setColor(colors[currentColor]);
    }

    public void copy() throws IOException {
        String text = "";
        String filled = new String();
        int col = 1;
        for (Rectangle cells : allCells) {
            if (!cells.isFilled()) {
                filled = "0";
                if (col % map.getCOLS() == 0) {
                    filled += "\n";
                }
            } else if (cells.isFilled()) {
                if (cells.getColor() == colors[0]) {
                    filled = "B";
                } else if (cells.getColor() == colors[1]) {
                    filled = "G";
                } else if (cells.getColor() == colors[2]) {
                    filled = "F";
                } else if (cells.getColor() == colors[3]) {
                    filled = "R";
                } else if (cells.getColor() == colors[4]) {
                    filled = "Y";
                } else if (cells.getColor() == colors[5]) {
                    filled = "O";
                } else if (cells.getColor() == colors[6]) {
                    filled = "g";
                } else if (cells.getColor() == colors[7]) {
                    filled = "P";
                } else if (cells.getColor() == colors[8]) {
                    filled = "C";
                } else {
                    filled = "M";
                }
                if (col % map.getCOLS() == 0) {
                    filled += "\n";
                }
            }
            text += filled + " ";
            System.out.println(text);
            col++;
        }
        this.fileCreator.save("resources/work", text);
    }

    public void paste() throws IOException {
        String[] rectangles;
        rectangles = fileCreator.paste().split(" ");
        for (int i = 0; i < rectangles.length; i++) {
            if (rectangles[i].equals("B")) {
                allCells.get(i).setColor(colors[0]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("G")) {
                allCells.get(i).setColor(colors[1]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("F")) {
                allCells.get(i).setColor(colors[2]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("R")) {
                allCells.get(i).setColor(colors[3]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("Y")) {
                allCells.get(i).setColor(colors[4]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("O")) {
                allCells.get(i).setColor(colors[5]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("g")) {
                allCells.get(i).setColor(colors[6]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("P")) {
                allCells.get(i).setColor(colors[7]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("C")) {
                allCells.get(i).setColor(colors[8]);
                allCells.get(i).fill();
            } else if (rectangles[i].equals("M")) {
                allCells.get(i).setColor(colors[8]);
                allCells.get(i).fill();
            }
        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                if (this.position.getCols() + 1 < this.map.getCOLS()) {
                    this.position.setCols(1);
                    this.cursor.translate(MapCreator.getCELLSIZE(), 0);
                    this.cursorBorder.translate(MapCreator.getCELLSIZE(), 0);
                }
                if (paint) {
                    for (Rectangle cell : allCells) {
                        if (cell.getX() == this.cursor.getX() && cell.getY() == this.cursor.getY()) {
                            cell.setColor(this.cursorBorder.getColor());
                            cell.fill();
                        }
                    }
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (this.position.getCols() - 1 >= 0) {
                    this.position.setCols(-1);
                    this.cursor.translate(-MapCreator.getCELLSIZE(), 0);
                    this.cursorBorder.translate(-MapCreator.getCELLSIZE(), 0);

                }
                if (paint) {
                    for (Rectangle cell : allCells) {
                        if (cell.getX() == this.cursor.getX() && cell.getY() == this.cursor.getY()) {
                            cell.setColor(this.cursorBorder.getColor());
                            cell.fill();
                        }
                    }
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (this.position.getRows() + 1 < this.map.getROWS()) {
                    this.position.setRows(+1);
                    this.cursor.translate(0, MapCreator.getCELLSIZE());
                    this.cursorBorder.translate(0, MapCreator.getCELLSIZE());

                }
                if (paint) {
                    for (Rectangle cell : allCells) {
                        if (cell.getX() == this.cursor.getX() && cell.getY() == this.cursor.getY()) {
                            cell.setColor(this.cursorBorder.getColor());
                            cell.fill();
                        }
                    }
                }
                break;
            case KeyboardEvent.KEY_UP:
                if (this.position.getRows() - 1 >= 0) {
                    this.position.setRows(-1);
                    this.cursor.translate(0, -MapCreator.getCELLSIZE());
                    this.cursorBorder.translate(0, -MapCreator.getCELLSIZE());
                }
                if (paint) {
                    for (Rectangle cell : allCells) {
                        if (cell.getX() == this.cursor.getX() && cell.getY() == this.cursor.getY()) {
                            cell.setColor(this.cursorBorder.getColor());
                            cell.fill();
                        }
                    }
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                paint = true;
                for (Rectangle cell : allCells) {
                    if (cell.getX() == this.cursor.getX() && cell.getY() == this.cursor.getY()) {
                        cell.setColor(this.cursorBorder.getColor());
                        cell.fill();
                    }
                }
                break;
            case KeyboardEvent.KEY_C:
                for (Rectangle cell : allCells) {
                    if (cell.getX() == this.cursor.getX() && cell.getY() == this.cursor.getY()) {
                        cell.delete();
                        cell.setColor(Color.BLACK);
                        cell.draw();
                    }
                }
                break;
            case KeyboardEvent.KEY_D:
                for (Rectangle cell : allCells) {
                    cell.delete();
                    cell.setColor(Color.BLACK);
                    cell.draw();
                }
                break;
            case KeyboardEvent.KEY_V:
                changeColor();
                break;
            case KeyboardEvent.KEY_0:
                System.out.println("saving");
                try {
                    copy();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case KeyboardEvent.KEY_1:
                try {
                    paste();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            paint = false;
            this.cursor.delete();
            this.cursor.fill();
        }
    }
}
