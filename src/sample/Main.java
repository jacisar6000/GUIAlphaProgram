package sample;

//Joseph Cisar, 9/28/2019, This file allows the application to be ran and allows the application scene to be edited.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    //This is a class
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 780, 550)); // height and width of the the GUI
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
