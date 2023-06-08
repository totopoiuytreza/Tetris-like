package com.example.tetrislike;
import com.example.tetrislike.logic.Block;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class MainController {
    private boolean isPaused = false;
    @FXML
    private Label scoreLabel;

    @FXML
    private Button testButton;

    @FXML
    private Button pauseButton;

    @FXML
    private AnchorPane gamePane;

    @FXML
    protected void onTestButtonClick() {
        testButton.setText("Test");
        System.out.println("Test button clicked");
        Pane redPane = new Pane();
        redPane.setStyle("-fx-background-color: red;");
        redPane.prefHeight(100);
        redPane.prefWidth(100);
        // Set anchor constraints for the Pane
        AnchorPane.setTopAnchor(redPane, 10.0);
        AnchorPane.setLeftAnchor(redPane, 10.0);

        // Add the Pane to the AnchorPane
        gamePane.getChildren().add(redPane);
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