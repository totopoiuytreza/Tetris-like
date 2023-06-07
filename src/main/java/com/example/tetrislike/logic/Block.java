package com.example.tetrislike.logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Block {
    private Rectangle a;
    private Rectangle b;
    private Rectangle c;
    private Rectangle d;
    private Color color = Color.SLATEGRAY;
    private int position = 1;

    public Block(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


}
