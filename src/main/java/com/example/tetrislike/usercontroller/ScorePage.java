package com.example.tetrislike.usercontroller;

import com.example.tetrislike.MainApplication;
import com.example.tetrislike.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ScorePage extends Application {
    private MainController mainController;
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

        final Label label = new Label("Score Page");
        label.setFont(new javafx.scene.text.Font("Arial", 20));

        getAllScores();

        // Use the function getTable to create a table from MainController
        mainController = fxmlLoader.getController();
        System.out.println("ScorePage: " + mainController);
        mainController.getTable(scoreList, nameList, rankList);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getAllScores() {
        String path = "src/main/java/com/example/tetrislike/usercontroller/Score.json";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> jsonData = new ArrayList<String>();
            String line;
            List<String> scoreList = new ArrayList<String>();
            List<String> nameList = new ArrayList<String>();

            while ((line = reader.readLine()) != null) {
                jsonData.add(line);
            }
            try{
                for (String s : jsonData) {
                    // If the line different than "[" and "]"
                    if(!s.equals("[") && !s.equals("]")) {
                        // Split the line by ","
                        String[] parts = s.split(",");
                        for(String p : parts){
                            String part = p.split(":")[1];
                            // If the line contains "
                            if(part.contains("\"")){
                                // Remove the "
                                part = part.replace("\"", "");
                                nameList.add(part);
                            }
                            // If the line contains }
                            if(part.contains("}")){
                                // Remove the }
                                part = part.replace("}", "");
                                part = part.replace(" ", "");
                                scoreList.add(part);
                            }
                        }
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }


            // Sort the list
            for(int i = 0; i < scoreList.size(); i++){
                for(int j = i + 1; j < scoreList.size(); j++){
                    if(Integer.parseInt(scoreList.get(i)) < Integer.parseInt(scoreList.get(j))){
                        String temp = scoreList.get(i);
                        scoreList.set(i, scoreList.get(j));
                        scoreList.set(j, temp);

                        temp = nameList.get(i);
                        nameList.set(i, nameList.get(j));
                        nameList.set(j, temp);
                    }
                }
            }
            // Print the list
            for(int i = 0; i < scoreList.size(); i++){
                this.nameList[i] = nameList.get(i);
                this.scoreList[i] = scoreList.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}



