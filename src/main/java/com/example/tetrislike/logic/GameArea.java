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

    public void addBlock(Block block){
        // Add Block to Matrix area
        int[][] blockMatrix = block.getMatrix();
        String color_name = block.getColor_name();

        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    area[i][j] = color_name;
                }
            }
        }
    }

    public void movementBlock(Block block, String move){
        switch (move) {
            case "down" -> {
                if (!block.getTouchedBottom()) {
                    moveDownBlock(block, 1);
                }
            }
            case "right" -> moveRightBlock(block);
            case "left" -> moveLeftBlock(block);
            case "rotateR" -> rotateRBlock(block);
            case "rotateL" -> rotateLBlock(block);
        }
    }

    public void checkAll(Block block){
        checkTouchedBottom(block);
        checkTouchedBlock(block);
        checkCanRotateL(block);
        checkCanRotateR(block);
    }

    public void checkTouchedBottom(Block block){

        int positionYonGameArea = getBottomOfBlock(block);
        // Check if bottom of Block is touching bottom of Matrix area
        if(positionYonGameArea == height){
            block.setTouchedBottom(true);
        }
    }

    public void checkTouchedBlock(Block block){

        int positionYonGameArea = getBottomOfBlock(block);
        try{


            // Check if bottom of Block is something else than 0
            for(int j = 0; j < block.getMatrix()[0].length; j++){
                if(area[positionYonGameArea - 1][block.getPrevious_y() + j].equals(block.getColor_name())){
                    if(!area[positionYonGameArea][block.getPrevious_y() + j].equals("0")){
                        block.setTouchedBottom(true);
                    }
                }
                else{
                    // Check all uppers of Block if they are touching something else than 0

                    for(int i = 0; i < block.getMatrix().length; i++){
                        if(area[block.getPrevious_x() + i][block.getPrevious_y() + j].equals(block.getColor_name())){
                            if(!area[block.getPrevious_x() + i - 1][block.getPrevious_y() + j].equals("0")){
                                block.setTouchedBottom(true);
                            }
                        }
                    }


                }
            }


            // Check if left of Block is something else than 0
            for(int i = 0; i < block.getMatrix().length; i++){
                if(area[block.getPrevious_x() + i][block.getPrevious_y()].equals(block.getColor_name())){
                    block.setCanLeft(area[block.getPrevious_x() + i][block.getPrevious_y()].equals("0"));
                }
            }
            // Check if right of Block is something else than 0
            for(int i = 0; i < block.getMatrix().length; i++){
                if(area[block.getPrevious_x() + i][block.getPrevious_y() + block.getMatrix()[0].length].equals(block.getColor_name())){
                    block.setCanRight(area[block.getPrevious_x() + i][block.getPrevious_y() + block.getMatrix()[0].length - 1].equals("0"));
                }
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void checkCanRotateR(Block block) {
        int[][] blockMatrix = block.getMatrix();
        block.rotateR();
        block.setCanRotateR(true);

        // Check if Block can rotate
        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1 && block.getPrevious_x() + i >= 0 && block.getPrevious_y() + j >= 0 && block.getPrevious_x() + i < height && block.getPrevious_y() + j < width){
                    if(!area[block.getPrevious_x() + i][block.getPrevious_y() + j].equals("0")){
                        block.setCanRotateR(false);
                    }
                }
            }
        }



    }
    public void checkCanRotateL(Block block) {
        int[][] blockMatrix = block.getMatrix();
        block.rotateL();
        block.setCanRotateL(true);

        // Check if Block can rotate
        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1 && block.getPrevious_x() + i >= 0 && block.getPrevious_y() + j >= 0 && block.getPrevious_x() + i < height && block.getPrevious_y() + j < width){
                    if(!area[block.getPrevious_x() + i][block.getPrevious_y() + j].equals("0")){
                        block.setCanRotateL(false);
                    }
                }
            }
        }


    }

    public void moveDownBlock(Block block, int x){
        try{
            // Move Block to Matrix area
            int[][] blockMatrix = block.getMatrix();
            int previous_x = block.getPrevious_x();
            int previous_y = block.getPrevious_y();
            String color_name = block.getColor_name();

            // Clear Block from Matrix area
            clearBlock(block);

            // Move Block to Matrix area
            for(int i = 0; i < blockMatrix.length; i++){
                for(int j = 0; j < blockMatrix[i].length; j++){
                    if(blockMatrix[i][j] == 1){
                        area[previous_x + i + x][previous_y + j] = color_name;
                    }
                }
            }
            block.setPreviousX(block.getPrevious_x() + 1);
        } catch (Exception e){
            System.out.println("Movement not possible");
            clearBlock(block);
            placeBlock(block);

        }

    }


    public void moveRightBlock(Block block){

        // Clear Block from Matrix area
        clearBlock(block);

        // Move Block to Right
        block.setPreviousY(block.getPrevious_y() + 1);

        // Move Block to Matrix area
        placeBlock(block);
    }

    public void moveLeftBlock(Block block){

        // Clear Block from Matrix area
        clearBlock(block);

        // Move Block to Left
        block.setPreviousY(block.getPrevious_y() - 1);

        // Move Block to Matrix area
        placeBlock(block);

    }

    public void rotateRBlock(Block block){

        // Clear Block from Matrix area
        clearBlock(block);

        System.out.println(area);

        // Rotate Block Right to Matrix area
        block.rotateR();

        // Move Block to Matrix area
        placeBlock(block);
    }
    public void rotateLBlock(Block block){

        // Clear Block from Matrix area
        clearBlock(block);

        // Rotate Block Left to Matrix area
        block.rotateL();

        // Move Block to Matrix area
        placeBlock(block);

    }

    public void placeBlock(Block block){
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();
        String color_name = block.getColor_name();

        // Move Block to Matrix area
        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    area[previous_x + i][previous_y + j] = color_name;
                }
            }
        }
    }

    public void clearBlock(Block block){
        // Clear Block from Matrix area
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();

        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    area[previous_x + i][previous_y + j] = "0";
                }
            }
        }
    }

    public int getBottomOfBlock(Block block){
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int temp = 0;

        // temp is the height of the block
        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    temp = i;
                }
            }
        }
        return previous_x + 1 + temp;
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

