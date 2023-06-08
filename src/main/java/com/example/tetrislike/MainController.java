package com.example.tetrislike;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    private boolean isPaused = false;
    @FXML
    private Label scoreLabel;

    @FXML
    private Button testButton;

    @FXML
    private Button pauseButton;

    @FXML
    protected void onTestButtonClick() {
        testButton.setText("Test");
        System.out.println("Test button clicked");
    }
    @FXML
    protected void onPauseButtonClick() {
        if (isPaused) {
            isPaused = false;
            pauseButton.setText("Pause");
        } else {
            isPaused = true;
            pauseButton.setText("Resume");
        }
        System.out.println("Pause button clicked");
    }

    public void setScoreLabel(String score) {
        scoreLabel.setText(score);
    }


}