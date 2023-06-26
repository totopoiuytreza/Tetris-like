package com.example.tetrislike;
import com.example.tetrislike.logic.*;
import com.example.tetrislike.usercontroller.ScorePage;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class MainController {
    private boolean isPaused = false;


    private AnimationTimer timer;
    @FXML
    private Label scoreLabel;

    @FXML
    private Button testButton;

    @FXML
    private Button pauseButton;

    @FXML
    private AnchorPane gamePane;
    @FXML
    private Button startButton;

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

        // Open ScorePage
        ScorePage scorePage = new ScorePage();
        try {
            scorePage.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                rectangle.setFill(javafx.scene.paint.Color.TRANSPARENT);
                //Color of the border of the rectangle #dcdcdc
                rectangle.setStroke(javafx.scene.paint.Color.web("#dcdcdc"));
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
        setGameArea(gameArea);
        // Affichage de la matrice sur le gamePane
        for (int i = 0; i < gameArea.height; i++) {
            for (int j = 0; j < gameArea.width; j++) {
                if (gameArea.getArea()[i][j] != "0") {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setHeight(37);
                    rectangle.setWidth(37);
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
                    rectangle.setHeight(37);
                    rectangle.setWidth(37);
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

    public void initialize(GameArea gameArea) {
        // Créer les boutons "Start" et "Score"
        startButton = new Button("Start");
        startButton.setLayoutX(200.0);
        startButton.setLayoutY(350.0);
        startButton.setPrefWidth(200.0);
        startButton.setPrefHeight(100.0);
        startButton.setOnAction(this::onStartButtonClick);



        // Ajouter les boutons à gamePane
        gamePane.getChildren().addAll(startButton);


        // Activer le bouton Start lorsque le jeu est prêt à être lancé
        //gameArea.setOnGameReady(() -> startButton.setDisable(false));
    }

    public void onStartButtonClick(ActionEvent event) {
        // Action à exécuter lors du clic sur le bouton Start
        // Par exemple, lancer le jeu en activant le timer
        // Désactiver le bouton Start au démarrage
        startButton.setDisable(true);
        timer.start(); // Démarrer le timer ici
    }


    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

}