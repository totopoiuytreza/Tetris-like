package com.example.tetrislike;

import com.example.tetrislike.logic.Block;
import com.example.tetrislike.logic.GameArea;
import com.example.tetrislike.usercontroller.Score;
import com.example.tetrislike.usercontroller.ScorePage;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;


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
    private TableView<Score> tableView;
    @FXML
    private TableColumn<Score, String> rankCol;
    @FXML
    private TableColumn<Score, String> nameCol;
    @FXML
    private TableColumn<Score, String> scoreCol;


    public void getTable(String[] scoreList, String[] nameList, String[] rankList) {
        tableView.setEditable(true);
        // Height of cells
        tableView.setFixedCellSize(50);

        // Size of texts and center them
        tableView.setStyle("-fx-font-size: 22px;");
        rankCol.setStyle("-fx-alignment: CENTER;");
        nameCol.setStyle("-fx-alignment: CENTER;");
        scoreCol.setStyle("-fx-alignment: CENTER;");

        // Horizontal Borders of the columns
        rankCol.setStyle("-fx-border-width: 0.5px; -fx-border-color: gray;");
        nameCol.setStyle("-fx-border-width: 0.5px; -fx-border-color: gray;");
        scoreCol.setStyle("-fx-border-width: 0.5px; -fx-border-color: gray;");

        // Add the data to the table
        for(int i = 0; i < 10; i++){
            rankList[i] = Integer.toString(i + 1);
            System.out.println(rankList[i] + " " + nameList[i] + " " + scoreList[i]);
            Score score = new Score(rankList[i], nameList[i], scoreList[i]);
            tableView.getItems().add(score);
        }
    }

    public void writeScore(String score) {
        String path = "src/main/java/com/example/tetrislike/usercontroller/Score.json";
        String tempPath = "src/main/java/com/example/tetrislike/usercontroller/TempScore.json";
        File file = new File(path);
        File tempFile = new File(tempPath);
        // Write in Score.json
        // Read Score.json
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
             BufferedReader reader = new BufferedReader(new FileReader(file))){

            String currentLine;

            // Read each line from the original file
            while ((currentLine = reader.readLine()) != null) {
                // If the line does not match the line to be deleted, write it to the temporary file
                if (!currentLine.equals("]")) {
                    bw.write(currentLine);
                    bw.newLine();
                }
                else{
                    bw.write(",");
                    bw.newLine();
                    bw.write(score);
                    bw.newLine();
                    bw.write("]");
                }
            }
            reader.close();
            bw.close();
            // Replace the original file with the temporary file
            file.delete();
            tempFile.renameTo(file);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    protected void onTestButtonClick() {
        testButton.setText("Scores");
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
        startButton.setPrefWidth(200.0);
        startButton.setPrefHeight(100.0);
        startButton.setOnAction(this::onStartButtonClick);

        double gamePaneWidth = 444.0;
        double buttonWidth = startButton.getPrefWidth();
        double buttonX = (gamePaneWidth - buttonWidth) / 2.0;
        startButton.setLayoutX(buttonX);
        startButton.setLayoutY(350.0);

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