package com.example.tetrislike;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label scoreLabel;


    public void setScoreLabel(String score) {
        scoreLabel.setText(score);
    }


}