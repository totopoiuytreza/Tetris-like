package com.example.tetrislike;
import com.example.tetrislike.logic.*;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private AnchorPane nextPane;

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
                rectangle.setHeight(37);
                rectangle.setWidth(37);
                rectangle.setFill(javafx.scene.paint.Color.WHITE);
                rectangle.setStroke(javafx.scene.paint.Color.BLACK);
                rectangle.setStrokeWidth(1);
                AnchorPane.setTopAnchor(rectangle, i * 37.0);
                AnchorPane.setLeftAnchor(rectangle, j * 37.0);
                gamePane.getChildren().add(rectangle);
            }
        }
    }

    @FXML
    public void affichage_matrice(GameArea gameArea) {
        gamePane.getChildren().clear();
        // Affichage de la matrice sur le gamePane
        for (int i = 0; i < gameArea.height; i++) {
            for (int j = 0; j < gameArea.width; j++) {
                if (gameArea.getArea()[i][j] != "0") {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setHeight(40);
                    rectangle.setWidth(40);
                    //affichage de la couleur
                    switch (gameArea.getArea()[i][j]) {
                        case "R" -> rectangle.setFill(javafx.scene.paint.Color.RED);
                        case "B" -> rectangle.setFill(javafx.scene.paint.Color.BLUE);
                        case "G" -> rectangle.setFill(javafx.scene.paint.Color.GREEN);
                        case "Y" -> rectangle.setFill(javafx.scene.paint.Color.YELLOW);
                        case "P" -> rectangle.setFill(javafx.scene.paint.Color.PURPLE);
                        case "O" -> rectangle.setFill(javafx.scene.paint.Color.ORANGE);
                        case "Pin" -> rectangle.setFill(javafx.scene.paint.Color.PINK);
                        case "Grey" -> rectangle.setFill(javafx.scene.paint.Color.GREY);
                        case "C" -> rectangle.setFill(javafx.scene.paint.Color.CYAN);
                        case "Brown" -> rectangle.setFill(javafx.scene.paint.Color.BROWN);
                        case "Black" -> rectangle.setFill(javafx.scene.paint.Color.BLACK);
                        default -> rectangle.setFill(javafx.scene.paint.Color.WHITE);
                    }
                    //rectangle.setFill(javafx.scene.paint.Color.BLACK);
                    rectangle.setStroke(javafx.scene.paint.Color.BLACK);
                    rectangle.setStrokeWidth(1);
                    AnchorPane.setTopAnchor(rectangle, i * 37.0);
                    AnchorPane.setLeftAnchor(rectangle, j * 37.0);
                    gamePane.getChildren().add(rectangle);
                }
            }
        }
    }

    public void affichage_nextblock(Block nextBlock) {
        nextPane.getChildren().clear();
        // Affichage de nextBlock sur le nextPane
        for (int i = 0; i < nextBlock.getMatrix()[0].length; i++) {
            for (int j = 0; j < nextBlock.getMatrix()[0].length; j++) {
                if (nextBlock.getMatrix()[i][j] != 0) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setHeight(40);
                    rectangle.setWidth(40);
                    //affichage de la couleur
                    switch (nextBlock.getColorName()) {
                        case "R" -> rectangle.setFill(javafx.scene.paint.Color.RED);
                        case "B" -> rectangle.setFill(javafx.scene.paint.Color.BLUE);
                        case "G" -> rectangle.setFill(javafx.scene.paint.Color.GREEN);
                        case "Y" -> rectangle.setFill(javafx.scene.paint.Color.YELLOW);
                        case "P" -> rectangle.setFill(javafx.scene.paint.Color.PURPLE);
                        case "O" -> rectangle.setFill(javafx.scene.paint.Color.ORANGE);
                        case "Pin" -> rectangle.setFill(javafx.scene.paint.Color.PINK);
                        case "Grey" -> rectangle.setFill(javafx.scene.paint.Color.GREY);
                        case "C" -> rectangle.setFill(javafx.scene.paint.Color.CYAN);
                        case "Brown" -> rectangle.setFill(javafx.scene.paint.Color.BROWN);
                        case "Black" -> rectangle.setFill(javafx.scene.paint.Color.BLACK);
                        default -> rectangle.setFill(javafx.scene.paint.Color.WHITE);
                    }
                    //rectangle.setFill(javafx.scene.paint.Color.BLACK);
                    rectangle.setStroke(javafx.scene.paint.Color.BLACK);
                    rectangle.setStrokeWidth(1);
                    AnchorPane.setTopAnchor(rectangle, (i) * 37.0); // Ajouter une valeur de décalage en haut
                    AnchorPane.setLeftAnchor(rectangle, (j) * 37.0); // Ajouter une valeur de décalage à gauche
                    nextPane.getChildren().add(rectangle);
                }
            }
        }


    }

    public Scene getScene() {
        return gamePane.getScene();
    }

    @FXML
    public void setScoreLabel(int score){
        scoreLabel.setText(String.valueOf(score));
    }

    // Get the state of the pause button
    public boolean getIsPaused() {
        return isPaused;
    }



}