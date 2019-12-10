/**
 * @author Joseph Cisar
 * Joseph Cisar, 11/8/2019, This file allows the application to be ran and allows
 * the application scene to be edited.
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(root);
    root.setId("pane");
    primaryStage.setTitle("Product Information.");
    primaryStage.setScene(scene);
    primaryStage.show();
    scene.getStylesheets().add(Main.class.getResource("product.css").toExternalForm());
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}

