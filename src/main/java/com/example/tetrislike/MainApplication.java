package com.example.tetrislike;

import com.example.tetrislike.logic.Block;
import com.example.tetrislike.logic.GameArea;
import com.example.tetrislike.logic.GameLogic;
import com.example.tetrislike.usercontroller.PopUp;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TEST.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/logo.png"));
        stage.setTitle("Tetris Like Game");
        stage.setResizable(false);
        stage.setFullScreen(false);
        //fermer le programme quand on ferme la fenetre
        stage.setOnCloseRequest(e -> System.exit(0));
        GameArea gameArea = new GameArea();

        //affiche gameArea dans la zone anchorPane GameScreen
        MainController mainController = fxmlLoader.getController();
        mainController.setGameArea(gameArea);


        //executer la fonction afficher_matrice() à chaque fois que la matrice est modifiée

        GameLogic gameLogic = new GameLogic();
        gameLogic.setGameArea(gameArea);
        Block block = new Block();
        gameLogic.setBlock(block);
        gameLogic.addBlockToArea();
        /*
        for(int i = 0; i < 18; i++){
            gameLogic.getGameArea().movementBlock(block, "down");
            System.out.println(gameLogic.getGameArea());
        }
        gameLogic.getGameArea().movementBlock(block, "down");
        System.out.println(gameLogic.getGameArea());
        */



        AnimationTimer timer = new Timer(gameArea, gameLogic ,mainController);
        mainController.setTimer(timer);
        mainController.initialize(gameArea);
        //timer.start();

        stage.setScene(scene);
        stage.show();

    }
    private void updateBlockMatrix(ObservableList<String[]> blockMatrix,String[][] newMatrix) {
        System.out.println("updateBlockMatrix");
        blockMatrix.clear(); // Effacer l'ancienne matrice


        // Convertir la matrice en une liste de tableaux de chaînes de caractères
        List<String[]> matrixList = Arrays.asList(newMatrix);

        // Ajouter la liste à l'ObservableList
        blockMatrix.addAll(matrixList);
    }

    private class Timer extends AnimationTimer {
        private GameArea gameArea = new GameArea();
        private GameLogic gameLogic = new GameLogic();
        private MainController mainController = new MainController();

        private EventHandler eventHandler;

        private long lastUpdate = 0;

        public Timer(GameArea area, GameLogic logic, MainController mainController) {
            super();
            this.gameArea = area;
            this.gameLogic = logic;
            this.mainController = mainController;
        }

        @Override
        public void handle(long now) {
            // Pause the game if the pause button is pressed
            if (mainController.getIsPaused()) {
                return;
            }
            // Inputs keyboards using the function deplacement and creat the functionality of the KeyEvents
            mainController.getScene().setOnKeyPressed(e -> gameLogic.deplacement(e));
            mainController.affichage_matrice(gameArea);

            if (now - lastUpdate >= 1000000000) {
                // Mettre à jour le jeu ici
                lastUpdate = now;
                System.out.println("Update");
                //Afficher la matrice et mise à jour de la matrice
                if(gameLogic.getGameOver()){
                    //TODO : afficher game over et afficher une fenetre pour demander si on veut rejouer avec le score affiché
                    System.out.println("Game Over");
                    PopUp scorePage = new PopUp();
                    try {
                        scorePage.start(new Stage());
                        scorePage.setScore(gameLogic.getScore());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stop();
                }
                gameLogic.fall();
                mainController.affichage_matrice(gameArea);
                mainController.affichage_nextblock(gameLogic.getNextBlock());
                mainController.setScoreLabel(gameLogic.getScore());

            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}