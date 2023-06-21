package com.example.tetrislike;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.tetrislike.logic.*;

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
        timer.start();

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

        private long lastUpdate = 0;

        public Timer(GameArea area, GameLogic logic, MainController mainController) {
            super();
            this.gameArea = area;
            this.gameLogic = logic;
            this.mainController = mainController;
        }

        @Override
        public void handle(long now) {
            if (now - lastUpdate >= 1000000000) {
                // Mettre à jour le jeu ici
                lastUpdate = now;
                System.out.println("Update");
                //Afficher la matrice et mise à jour de la matrice
                //gameLogic.addBlockToArea();
                gameLogic.fall();
                mainController.affichage_matrice(gameArea);

            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}