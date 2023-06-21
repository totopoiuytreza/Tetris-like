package com.example.tetrislike.logic;

import javafx.animation.TranslateTransition;

public class GameArea {
    public int height = 20;
    public int width = 12;
    String[][] area = new String[height][width];

    public GameArea() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                area[i][j] = "0";
            }
        }
    }

    public void addBlock(Block block) {
        // Add Block to Matrix area
        int[][] blockMatrix = block.getMatrix();

        for (int i = 0; i < blockMatrix.length; i++) {
            for (int j = 0; j < blockMatrix[i].length; j++) {
                if (blockMatrix[i][j] == 1) {
                    area[i][j] = "1";
                }
            }
        }
    }

    public void movementBlock(Block block, String move) {
        try {
            switch (move) {
                case "down":
                    moveDownBlock(block, 1);
                    break;
                case "right":
                    moveRightBlock(block);
                    break;
                case "left":
                    moveLeftBlock(block);
                    break;
                case "rotateR":
                    rotateRBlock(block);
                    break;
                case "rotateL":
                    rotateLBlock(block);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Movement not possible");
        }
    }

    public void moveDownBlock(Block block, int x) {
        // Move Block to Matrix area
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();

        // Clear Block from Matrix area
        clearBlock(block);

        // Move Block to Matrix area
        for (int i = 0; i < blockMatrix.length; i++) {
            for (int j = 0; j < blockMatrix[i].length; j++) {
                if (blockMatrix[i][j] == 1) {
                    area[previous_x + i + x][j] = "1";
                }
            }
        }
        block.setPreviousX(block.getPrevious_x() + 1);
    }


    public void moveRightBlock(Block block) {

        // Clear Block from Matrix area
        clearBlock(block);

        // Move Block to Right
        block.setPreviousY(block.getPrevious_y() + 1);

        // Move Block to Matrix area
        placeBlock(block);
    }

    public void moveLeftBlock(Block block) {

        // Clear Block from Matrix area
        clearBlock(block);

        // Move Block to Left
        block.setPreviousY(block.getPrevious_y() - 1);

        // Move Block to Matrix area
        placeBlock(block);

    }

    public void rotateRBlock(Block block) {

        // Clear Block from Matrix area
        clearBlock(block);

        // Rotate Block Right to Matrix area
        block.rotateR();

        // Move Block to Matrix area
        placeBlock(block);
    }

    public void rotateLBlock(Block block) {

        // Clear Block from Matrix area
        clearBlock(block);

        // Rotate Block Left to Matrix area
        block.rotateL();

        // Move Block to Matrix area
        placeBlock(block);

    }

    public void placeBlock(Block block) {
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();

        // Move Block to Matrix area
        for (int i = 0; i < blockMatrix.length; i++) {
            for (int j = 0; j < blockMatrix[i].length; j++) {
                if (blockMatrix[i][j] == 1) {
                    area[previous_x + i][previous_y + j] = "1";
                }
            }
        }
    }

    public void clearBlock(Block block) {
        // Clear Block from Matrix area
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();

        for (int i = 0; i < blockMatrix.length; i++) {
            for (int j = 0; j < blockMatrix[i].length; j++) {
                if (blockMatrix[i][j] == 1) {
                    area[previous_x + i][previous_y + j] = "0";
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBuilder.append(area[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String[][] getArea() {
        return area;
    }
}

