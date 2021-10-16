package com.example.btl_dictionary_graphic;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryController {
  private Stage stage;
  private Scene scene;
  private Parent root;

  public void switchToMenu(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToDictionaryLookUp(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DictionaryLookUp.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToTranslate(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Translate.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToInsertWord(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsertWord.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToFixWord(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FixWord.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToRemoveWord(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RemoveWord.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToShowAllWords(ActionEvent event) throws IOException {
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShowAllWords.fxml")));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
  public void mouseKick(ActionEvent e) {
    System.out.println("kicked");
  }

}