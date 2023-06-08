package com.example.tetrislike;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/logo.png"));
        stage.setTitle("Tetris Like Game");
        stage.setResizable(false);
        stage.setFullScreen(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}