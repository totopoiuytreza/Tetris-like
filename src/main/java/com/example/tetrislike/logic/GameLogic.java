package com.example.tetrislike.logic;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;

public class GameLogic {
    private GameArea gameArea;
    private Block block;
    private Block nextBlock;

    private boolean isPaused = false;

    private int score = 0;

    private boolean isGameOver = false;

    public GameLogic(){
        gameArea = new GameArea();
        block = new Block("I");
        nextBlock = new Block("I");
    }

    public void addBlockToArea(){
        addBlock();
    }

    public GameArea getGameArea() {
        return gameArea;
    }

    public void setGameArea(GameArea gameArea) {this.gameArea = gameArea;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getScore() {
        return score;
    }


    public void clearLine(){
        //Check if line is full
        //If line is full, clear line
        //If line is cleared, move all blocks above down by 1
        //Repeat until no more lines are cleared
        int scoreMultiplier = 0;
        String[][] gameAreaMatrix = gameArea.getArea();

        //For each line, check if line is full
        for(int i = 0; i < gameAreaMatrix.length; i++){
            boolean isLineFull = true;
            for(int j = 0; j < gameAreaMatrix[i].length; j++){
                if (gameAreaMatrix[i][j].equals("0")) {
                    isLineFull = false;
                    break;
                }
            }
            //If line is full, clear line
            if(isLineFull){
                Arrays.fill(gameAreaMatrix[i], "0");
                //Move all blocks above down by 1
                for(int k = i; k > 0; k--){
                    System.arraycopy(gameAreaMatrix[k - 1], 0, gameAreaMatrix[k], 0, gameAreaMatrix[k].length);
                }
                if(scoreMultiplier == 0)
                    scoreMultiplier++;
                else
                    scoreMultiplier *= 3;
            }

        }
        //Add score
        score += 100 * scoreMultiplier;
    }


    public void addBlock(){
        int [][] blockMatrix = block.getMatrix();
        String [][] gameAreaMatrix = gameArea.getArea();
        String color_name = block.getColor_name();

        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    gameAreaMatrix[i][j] = color_name;
                }
            }
        }
    }

    public int getBottomOfBlock(){
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

    public void checkFalling(){
        checkTouchedBottom();
    }
    public void checkTouchedBottom(){

        int positionYonGameArea = getBottomOfBlock();
        // Check if bottom of Block is touching bottom of Matrix area
        if(positionYonGameArea == gameArea.height){
            block.setTouchedBottom(true);
        }
    }

    public boolean checkCanRight(){
        try{
            int[][] blockMatrix = block.getMatrix();
            int previous_x = block.getPrevious_x();
            int previous_y = block.getPrevious_y();
            String color_name = block.getColor_name();

            String [][] gameAreaMatrix = gameArea.getArea();

            // Check if right of Block is something else than 0
            for(int i = 0; i < blockMatrix.length; i++){
                if(!gameAreaMatrix[previous_x + i][previous_y + blockMatrix[i].length].equals("0")){
                    return false;
                }
            }
            return true;
        } catch (Exception e){
            if(e instanceof ArrayIndexOutOfBoundsException){
                boolean isRightWall = false;
                // Check if block is touching right wall of it's matrix
                int[][] blockMatrix = block.getMatrix();
                for(int i = 0; i < blockMatrix.length; i++){
                    if(blockMatrix[i][blockMatrix[i].length - 1] == 1){
                        isRightWall = true;
                    }
                }
                // If block is touching right wall, return false
                if(isRightWall){
                    return false;
                } else {
                    for(int i = 0; i < blockMatrix.length; i++){
                        if(blockMatrix[i][blockMatrix[i].length - 1] == 1){
                            blockMatrix[i][blockMatrix[i].length - 1] = 0;
                            blockMatrix[i][blockMatrix[i].length - 2] = 1;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

    }

    public boolean checkCanLeft(){
        try{
            int[][] blockMatrix = block.getMatrix();
            int previous_x = block.getPrevious_x();
            int previous_y = block.getPrevious_y();
            String color_name = block.getColor_name();

            String [][] gameAreaMatrix = gameArea.getArea();

            // Check if left of Block is something else than 0
            for(int i = 0; i < blockMatrix.length; i++){
                if(!gameAreaMatrix[previous_x + i][previous_y - 1].equals("0")){
                    return false;
                }
            }
            return true;
        } catch (Exception e){
            if(e instanceof ArrayIndexOutOfBoundsException){
                boolean isLeftWall = false;
                // Check if block is touching left wall of it's matrix
                int[][] blockMatrix = block.getMatrix();
                for(int i = 0; i < blockMatrix.length; i++){
                    if(blockMatrix[i][0] == 1){
                        isLeftWall = true;
                    }
                }
                // If block is touching left wall, return false
                if(isLeftWall){
                    return false;
                } else {
                    for(int i = 0; i < blockMatrix.length; i++){
                        if(blockMatrix[i][0] == 1){
                            blockMatrix[i][0] = 0;
                            blockMatrix[i][1] = 1;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }


    public boolean checkCanRotateR(){
        //Create rotated Block
        block.rotateR();
        int[][] rotatedBlockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();

        String [][] gameAreaMatrix = gameArea.getArea();

        // Check if rotated Block is touching something else than 0
        for(int i = 0; i < rotatedBlockMatrix.length; i++){
            for(int j = 0; j < rotatedBlockMatrix[i].length; j++){
                if(rotatedBlockMatrix[i][j] == 1){
                    if(!gameAreaMatrix[previous_x + i][previous_y + j].equals("0")){
                        block.rotateL();
                        return false;
                    }
                }
            }
        }
        block.rotateL();
        return true;
    }

    public boolean checkCanRotateL(){
        //Create rotated Block
        block.rotateL();
        int[][] rotatedBlockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();

        String [][] gameAreaMatrix = gameArea.getArea();

        // Check if rotated Block is touching something else than 0
        for(int i = 0; i < rotatedBlockMatrix.length; i++){
            for(int j = 0; j < rotatedBlockMatrix[i].length; j++){
                if(rotatedBlockMatrix[i][j] == 1){
                    if(!gameAreaMatrix[previous_x + i][previous_y + j].equals("0")){
                        block.rotateR();
                        return false;
                    }
                }
            }
        }
        block.rotateR();
        return true;
    }

    public void instantFall(){
        while(!block.getTouchedBottom()){
            moveDownBlock(1);
        }
    }


    public void moveDownBlock(int distance){
        try{
            // Move Block to Matrix area
            int[][] blockMatrix = block.getMatrix();
            int previous_x = block.getPrevious_x();
            int previous_y = block.getPrevious_y();
            String color_name = block.getColor_name();

            String [][] gameAreaMatrix = gameArea.getArea();

            // Clear Block from Matrix area
            clearBlock();

            // Check if Block is touching something else than 0
            for(int i = 0; i < blockMatrix.length; i++){
                for(int j = 0; j < blockMatrix[i].length; j++){
                    if(blockMatrix[i][j] == 1){
                        if(!gameAreaMatrix[previous_x + i + distance][previous_y + j].equals("0")){
                            block.setTouchedBottom(true);
                        }
                    }
                }
            }
            if(block.getTouchedBottom()){
                placeBlock();
                return;
            }
            // Move Block to Matrix area
            for(int i = 0; i < blockMatrix.length; i++){
                for(int j = 0; j < blockMatrix[i].length; j++){
                    if(blockMatrix[i][j] == 1){
                        gameAreaMatrix[previous_x + i + distance][previous_y + j] = color_name;
                    }
                }
            }
            block.setPreviousX(block.getPrevious_x() + 1);
        } catch (Exception e){
            System.out.println("Movement not possible");
            clearBlock();
            placeBlock();
            block.setTouchedBottom(true);

        }
    }

    public void moveRightBlock(){
        if(checkCanRight()){
            try{
                // Clear Block from Matrix area
                clearBlock();

                // Move Block to Right
                block.setPreviousY(block.getPrevious_y() + 1);

                // Move Block to Matrix area
                placeBlock();
            } catch (Exception e){
                System.out.println("Movement not possible");
                block.setPreviousY(block.getPrevious_y() - 1);
                clearBlock();
                placeBlock();
            }
        }
    }

    public void moveLeftBlock(){
        if(checkCanLeft()){
            try{
                // Clear Block from Matrix area
                clearBlock();

                // Move Block to Left
                block.setPreviousY(block.getPrevious_y() -1);

                // Move Block to Matrix area
                placeBlock();
            } catch (Exception e){
                System.out.println("Movement not possible");
                block.setPreviousY(block.getPrevious_y() + 1);
                clearBlock();
                placeBlock();
            }
        }
    }

    public void rotateRBlock(){
        try{
            // Clear Block from Matrix area
            clearBlock();

            if(checkCanRotateR()){
                // Rotate Block Right to Matrix area
                block.rotateR();
            } else {
                block.rotateL();
            }

            // Move Block to Matrix area
            placeBlock();
        } catch (Exception e){
            System.out.println("Movement not possible");
            block.rotateL();
            clearBlock();
            placeBlock();
        }
    }
    public void rotateLBlock(){
        try{
            // Clear Block from Matrix area
            clearBlock();

            if(checkCanRotateL()){
                // Rotate Block Left to Matrix area
                block.rotateL();
            } else {
                block.rotateR();
            }

            // Move Block to Matrix area
            placeBlock();
        } catch (Exception e){
            System.out.println("Movement not possible");
            block.rotateR();
            clearBlock();
            placeBlock();
        }

    }

    public void clearBlock(){
        // Clear Block from Matrix area
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();

        String [][] gameAreaMatrix = gameArea.getArea();

        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    gameAreaMatrix[previous_x + i][previous_y + j] = "0";
                }
            }
        }
    }

    public void placeBlock(){
        int[][] blockMatrix = block.getMatrix();
        int previous_x = block.getPrevious_x();
        int previous_y = block.getPrevious_y();
        String color_name = block.getColor_name();

        String [][] gameAreaMatrix = gameArea.getArea();

        // Move Block to Matrix area
        for(int i = 0; i < blockMatrix.length; i++){
            for(int j = 0; j < blockMatrix[i].length; j++){
                if(blockMatrix[i][j] == 1){
                    gameAreaMatrix[previous_x + i][previous_y + j] = color_name;
                }
            }
        }
    }


    public void movementBlock(String direction){
        if(!block.getTouchedBottom()){
            switch(direction){
                case "down" -> moveDownBlock(1);

                case "right" -> moveRightBlock();

                case "left" -> moveLeftBlock();

                case "rotateR" -> rotateRBlock();

                case "rotateL" -> rotateLBlock();
            }
        }

    }

    public void fall(){
        checkFalling();
        if(!block.getTouchedBottom()){
            movementBlock("down");
        }
        else{
            clearLine();
            block = nextBlock;
            nextBlock = new Block("I");
            addBlockToArea();
        }
    }

    public void deplacement(KeyEvent e) {
        KeyCode touche = e.getCode();

        switch (touche) {
            case D -> movementBlock("right");
            case Q -> movementBlock("left");
            case S -> movementBlock("down");
            case E -> movementBlock("rotateR");
            case A -> movementBlock("rotateL");
            case Z -> instantFall();
        }
    }

}
