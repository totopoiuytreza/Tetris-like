package com.example.tetrislike.logic;

import javafx.animation.TranslateTransition;

public class GameArea {
    public int height = 20;
    public int width = 12;
    String[][] area = new String[height][width];

    public GameArea(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                area[i][j] = "0";
            }
        }
    }

    public void setArea(String[][] area) {
        this.area = area;
    }

    public String[][] getArea() {
        return area;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                stringBuilder.append(area[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

