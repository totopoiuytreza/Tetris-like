package com.example.tetrislike.logic;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;

public final class Block extends Group implements Cloneable {

    private static final Random RANDOM = new Random();
    private int previous_x;
    private int previous_y;

    private static final BlockDefinition I = new BlockDefinition(new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }, Color.CYAN);

    private static final BlockDefinition J = new BlockDefinition(new int[][]{
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
    }, Color.BLUE);

    private static final BlockDefinition L = new BlockDefinition(new int[][]{
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
    }, Color.ORANGE);

    private static final BlockDefinition O = new BlockDefinition(new int[][]{
            {1, 1},
            {1, 1}
    }, Color.YELLOW);

    private static final BlockDefinition S = new BlockDefinition(new int[][]{
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
    }, Color.GREENYELLOW);

    private static final BlockDefinition T = new BlockDefinition(new int[][]{
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
    }, Color.PURPLE);

    private static final BlockDefinition Z = new BlockDefinition(new int[][]{
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
    }, Color.ORANGERED);

    private static final BlockDefinition[] BLOCKS = {I, J, L, O, S, T, Z};
    private int[][] matrix;
    private BlockDefinition blockDefinition;
    private Color color;


    public Block() {
        setRandomBlock();
        previous_x = 0;
        previous_y = 0;
    }

    public Block(BlockDefinition blockDefinition) {
        setBlock(blockDefinition);
    }

    public void setBlock(BlockDefinition blockDefinition) {
        this.matrix = blockDefinition.matrix;
        this.color = blockDefinition.color;
    }

    public void setRandomBlock() {
        // Random choice in BlockDefinition array
        BlockDefinition blockDefinition = BLOCKS[RANDOM.nextInt(BLOCKS.length)];
        this.matrix = blockDefinition.matrix;
        this.color = blockDefinition.color;
    }

    public int[][] getMatrix(){
        return matrix;
    }

    public Color getColor(){
        return color;
    }

    public void setPreviousX(int x){
        previous_x = x;
    }

    public int getPrevious_x(){
        return previous_x;
    }

    public void setPreviousY(int y){
        previous_y = y;
    }

    public int getPrevious_y(){
        return previous_y;
    }

    public void rotateR(){
        // Rotate Block Right to Matrix area
        int[][] rotatedBlockMatrix = new int[matrix[0].length][matrix.length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                rotatedBlockMatrix[j][matrix.length - 1 - i] = matrix[i][j];
            }
        }

        matrix = rotatedBlockMatrix;
    }

    public void rotateL(){
        // Rotate Block Left to Matrix area
        int[][] rotatedBlockMatrix = new int[matrix[0].length][matrix.length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                rotatedBlockMatrix[matrix.length - 1 - j][i] = matrix[i][j];
            }
        }

        matrix = rotatedBlockMatrix;
    }


    private static class BlockDefinition {
        private final int[][] matrix;
        private final Color color;

        private BlockDefinition(int[][] matrix, Color color) {
            this.matrix = matrix;
            this.color = color;
        }
    }
}
