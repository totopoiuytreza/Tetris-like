package com.example.tetrislike.logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Block {
    private Rectangle[] rectangles;
    private Color color;

    public Block() {
        rectangles = new Rectangle[4];
        color = getRandomColor();
        for (int i = 0; i < 4; i++) {
            rectangles[i] = new Rectangle();
            rectangles[i].setWidth(30);
            rectangles[i].setHeight(30);
            rectangles[i].setFill(color);
        }
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }

    public void setRectangles(Rectangle[] rectangle) {
        this.rectangles = rectangle;
    }

    public void moveUp(){
        for(Rectangle rectangle : rectangles){
            rectangle.setY(rectangle.getY() - 30);
        }
    }

    public void moveDown(){
        for(Rectangle rectangle : rectangles){
            rectangle.setY(rectangle.getY() + 30);
        }
    }

    public void moveLeft(){
        for(Rectangle rectangle : rectangles){
            rectangle.setX(rectangle.getX() - 30);
        }
    }

    public void moveRight(){
        for(Rectangle rectangle : rectangles){
            rectangle.setX(rectangle.getX() + 30);
        }
    }

    public void rotate(){

    }

    public Color getRandomColor(){
        int random = (int) (Math.random() * 7);
        switch (random){
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.YELLOW;
                break;
            case 4:
                color = Color.PURPLE;
                break;
            case 5:
                color = Color.ORANGE;
                break;
            case 6:
                color = Color.PINK;
                break;
        }
        return color;

    }
}
