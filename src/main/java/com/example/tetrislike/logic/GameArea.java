package com.example.tetrislike.logic;

import javafx.animation.TranslateTransition;

public class GameArea {
    int height = 20;
    int width = 10;
    int[][] area = new int[height][width];

    public GameArea(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                area[i][j] = 0;
            }
        }
    }

    public void addBlock(Block block){
        TranslateTransition transition = new TranslateTransition();
        }
    }