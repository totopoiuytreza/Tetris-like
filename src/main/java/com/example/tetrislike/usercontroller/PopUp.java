package com.example.tetrislike.usercontroller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUp extends Application {
    private int score =0;
    Text scoreText = new Text("Score: " + score);
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Ajout du texte "Game Over"
        StackPane layout = new StackPane();
        //Text gameOverText = new Text("Game Over");
        //gameOverText.setFont(Font.font("Halo", FontWeight.BOLD, 36));
        //gameOverText.setFill(Color.DARKBLUE);
        //layout.getChildren().add(gameOverText);
        //StackPane.setAlignment(gameOverText, Pos.CENTER);

        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        scoreText.setFill(Color.BLACK);
        layout.getChildren().add(scoreText);
        StackPane.setAlignment(scoreText, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static void setScore(int score){
        Text scoreText = new Text("Score: " + score);
    }
}