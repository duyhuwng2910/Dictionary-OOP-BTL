package com.example.btl_dictionary_graphic;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
    stage.setTitle("DICTIONARY");
    stage.setScene(new Scene(root, 592, 398));
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}