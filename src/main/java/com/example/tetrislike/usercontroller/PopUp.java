package com.example.tetrislike.usercontroller;

import com.example.tetrislike.MainController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUp extends Application {
    private int score;
    Text scoreText = new Text("Score: " + score);
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button = new Button("Sign In");

        // Ajout du texte "Game Over"
        StackPane layout = new StackPane();
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Halo", FontWeight.BOLD, 36));
        gameOverText.setFill(Color.DARKBLUE);
        //layout.getChildren().add(gameOverText);
        //StackPane.setAlignment(gameOverText, Pos.CENTER);

        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        scoreText.setFill(Color.BLACK);
        layout.getChildren().add(scoreText);
        StackPane.setAlignment(scoreText, Pos.TOP_CENTER);

        Text EnterName = new Text("Enter your name :");
        EnterName.setFont(Font.font("Halo", FontWeight.BOLD, 16));
        EnterName.setFill(Color.DARKGRAY);


        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Enter your name");
        //layout.getChildren().add(nameTextField);
        //StackPane.setAlignment(nameTextField, Pos.BOTTOM_CENTER);
        VBox vbox = new VBox(gameOverText,EnterName, nameTextField, button);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
        layout.getChildren().add(vbox);

        button.setOnAction(e -> {
            String jsonData = "{\"name\":\"" + nameTextField.getText() + "\",\"score\":" + score + "}";
            MainController mainController = new MainController();
            mainController.writeScore(jsonData);
            primaryStage.close();
        });


        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void setScore(int score){
        scoreText.setText("Score: " + score);
        this.score = score;
    }
}