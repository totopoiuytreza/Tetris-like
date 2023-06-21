package com.example.tetrislike.logic;

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
        if(!block.getTouchedBottom()){
            gameArea.movementBlock(block, "down");
        }
    }

}
