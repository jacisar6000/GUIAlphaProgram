package sample;

//Joseph Cisar, 11/8/2019, This file allows the application to be ran and allows the application scene to be edited.

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
        Scene scene = new Scene(root);
        primaryStage.setTitle("Product Information");
        primaryStage.setScene(scene); // height and width of the the GUI
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

