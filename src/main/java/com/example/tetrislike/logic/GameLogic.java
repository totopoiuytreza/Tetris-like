package com.example.tetrislike.logic;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLogic {
    private GameArea gameArea;
    private Block block;

    public GameLogic(){
        gameArea = new GameArea();
        block = new Block();
    }

    public void addBlockToArea(){
        gameArea.addBlock(block);
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

    public void fall(){
        gameArea.checkAll(block);
        if(!block.getTouchedBottom()){
            gameArea.movementBlock(block, "down");
        }
        else{
            block = new Block();
            addBlockToArea();
        }
    }

    public void deplacement(KeyEvent e) {
        KeyCode touche = e.getCode();

        switch (touche) {
            case D -> gameArea.movementBlock(block, "right");
            case Q -> gameArea.movementBlock(block, "left");
            case S -> gameArea.movementBlock(block, "down");
            case E -> gameArea.movementBlock(block, "rotateR");
            case A -> gameArea.movementBlock(block, "rotateL");
        }
    }

}
