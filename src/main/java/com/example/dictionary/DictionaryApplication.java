package com.example.dictionary;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.File;

public class DictionaryApplication extends Application {

  public DictionaryApplication() throws IOException { }

  @Override
  public void start(Stage primaryStage) throws Exception {
    DictionaryManagement.insertFromDictionaryFile();
    DictionaryManagement.insertFromHistoryFile();
    DictionaryManagement.insertFromFavoriteFile();
    FXMLLoader loader =
        new FXMLLoader(
            new File("D:\\Code\\OOP\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\Dictionary.fxml")
                .toURI()
                .toURL());
    Parent root = loader.load();
    primaryStage.setTitle("Dictionary");
    Scene scene = new Scene(root, 750, 450);
    primaryStage.setScene(scene);
    // Closing the engine when stage is about to close
    primaryStage.show();
  }

  public static void main(String[] args){
    launch(args);
  }

}
