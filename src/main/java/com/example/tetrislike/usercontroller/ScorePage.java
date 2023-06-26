package com.example.tetrislike.usercontroller;
import javafx.application.Application;
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
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setTitle("Score Page");
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);

        final Label label = new Label("Score Page");
        label.setFont(new javafx.scene.text.Font("Arial", 20));

        table.setEditable(true);


        // Mettre sur l'écran les informations de score stockées dans le fichier Score.json

        // Create a score table with 3 columns: Rank, Name, Score
        TableColumn rankCol = new TableColumn("Rank");
        TableColumn nameCol = new TableColumn("Name");
        TableColumn scoreCol = new TableColumn("Score");

        table.getColumns().addAll(rankCol, nameCol, scoreCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((StackPane) scene.getRoot()).getChildren().addAll(vbox);




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
