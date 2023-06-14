package com.example.tetrislike;
import com.example.tetrislike.logic.Block;
import com.example.tetrislike.logic.GameArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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

    @FXML
    public void setGameArea(GameArea gameArea) {

        for (int i = 0; i < gameArea.height; i++) {
            for (int j = 0; j < gameArea.width; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(40);
                rectangle.setWidth(40);
                rectangle.setFill(javafx.scene.paint.Color.WHITE);
                rectangle.setStroke(javafx.scene.paint.Color.BLACK);
                rectangle.setStrokeWidth(1);
                AnchorPane.setTopAnchor(rectangle, i * 37.0);
                AnchorPane.setLeftAnchor(rectangle, j * 37.0);
                gamePane.getChildren().add(rectangle);
            }
        }
    }


}