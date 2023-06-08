package com.example.tetrislike.logic;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;

final class Block extends Group implements Cloneable {

    private static final Random RANDOM = new Random();

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



    private static class BlockDefinition {
        private final int[][] matrix;
        private final Color color;

        private BlockDefinition(int[][] matrix, Color color) {
            this.matrix = matrix;
            this.color = color;
        }
    }
}
