package com.example.tetrislike.usercontroller;
import com.example.tetrislike.MainApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class ScorePage extends Application {
    private TableView table = new TableView();
    private String[] scoreList = new String[10];
    private String[] nameList = new String[10];
    private String[] rankList = new String[10];
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Score.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        primaryStage.setTitle("Score Page");
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/logo.png"));




        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getAllScores() {
        String path = "src/main/java/com/example/tetrislike/usercontroller/Score.json";




    }
    public static void main(String[] args) {
        launch(args);
    }
}
