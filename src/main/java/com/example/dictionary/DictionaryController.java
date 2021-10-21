package com.example.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DictionaryController {
  @FXML
  public TextField textFieldSearcher;

  public TextField textFieldAddEnglish;
  public TextField textFieldAddVietnamese;

  public TextField textFieldWordNeedsChanging;
  public TextField textFieldChangeMeaningOfWord;

  public TextField textFieldRemoveWord;

  public TextField textFieldAddNewWordToYourList;

  public Button buttonAddNewWord;
  public Button buttonSubmitAddNewWord;

  public Button buttonFixWord;
  public Button buttonSubmitFixWord;

  public Button buttonRemoveWord;
  public Button buttonSubmitRemoveWord;

  public Button buttonShowAllWords;

  public Button buttonShowHistory;

  public Button buttonYourList;
  public Button buttonAddNewWordToYourList;
  public Button buttonSubmitAddNewWordToYourList;
  public Button buttonShowYourList;

  public ListView listViewSearcher;
  public ListView listViewShowWord;

  public VBox vBoxAddNewWord;
  public VBox vBoxFixWord;
  public VBox vBoxRemoveWord;
  public VBox vBoxYourList;

  private String wordTarget;
  private String wordExplain;

  /**
   * hàm gõ từ cần tra.
   */
  public void onChangeTextFieldSearcher() {
    wordTarget = textFieldSearcher.getText();
    ArrayList<String> list = DictionaryManagement.dictionarySearcher(wordTarget);
    listViewSearcher.getItems().clear();
    listViewSearcher.getItems().addAll(list);
  }

  /**
   * hàm đầy các từ gọi ý vào listview.
   */
  public void onPushItemOfListViewSearcher(){
    Object wordChoosen = listViewSearcher.getSelectionModel().getSelectedItem();
    textFieldSearcher.setText((String) wordChoosen);
  }

  /**
   *
   * @throws Exception
   */
  public void onClickFindIcon() throws Exception {
    wordTarget = textFieldSearcher.getText();
    Speak.mp3(wordTarget);
    String temp = wordTarget;
    if (!DictionaryManagement.wordIsExisted(wordTarget)) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error...");
      alert.setHeaderText("This word is not existed");
      alert.show();
      refreshToStart();
    } else {
      refreshToStart();
      wordTarget = temp.toLowerCase(Locale.ROOT);
      wordExplain = DictionaryManagement.getWordResultFromDictionary(wordTarget);
      listViewShowWord.getItems().add(wordTarget + ": " + wordExplain);
      DictionaryManagement.getHistoryList().push(wordTarget);
      DictionaryManagement.exportToHistoryFile();
    }
  }

  public void onClickListenIcon() throws Exception {
    Speak.speak();
  }

  public void onClickRemoveIcon(){
    textFieldSearcher.setText("");
    listViewSearcher.getItems().clear();
    listViewShowWord.getItems().clear();
  }

  public void onClickButtonAddNewWord(){
    refreshToStart();
    vBoxAddNewWord.setVisible(!vBoxAddNewWord.isVisible());
  }

  public void onClickButtonSubmitAddNewWord() {
    String eng = textFieldAddEnglish.getText();
    String vie = textFieldAddVietnamese.getText();
    if (eng == "" || vie == "") {
      // throw message error
      return;
    } else {
      Word newWord = new Word(eng, vie);
      addNewWordToDictionary(newWord);
      DictionaryManagement.exportToDictionaryFile();
      textFieldAddEnglish.setText("");
      textFieldAddVietnamese.setText("");
    }
  }

  public void onClickButtonFixWord(){
    refreshToStart();
    vBoxFixWord.setVisible(!vBoxFixWord.isVisible());
  }

  public void onClickButtonSubmitFixWord(){
    wordTarget = textFieldWordNeedsChanging.getText();
    Word newWord = new Word(wordTarget, textFieldChangeMeaningOfWord.getText());
    if (!DictionaryManagement.sameMeaning(newWord)) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText("This word is fixed");
      alert.setContentText("You have changed the meaning of this word successfully!");
      alert.show();
      DictionaryManagement.fixWord(newWord);
      DictionaryManagement.exportToDictionaryFile();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Note");
      alert.setHeaderText("This meaning is similar to the previous one");
      alert.setContentText("You need to try another meaning if you want to fix this word!");
      alert.show();
    }
    refreshToStart();
    textFieldWordNeedsChanging.setText("");
    textFieldChangeMeaningOfWord.setText("");
  }

  public void onClickButtonRemoveWord(){
    refreshToStart();
    vBoxRemoveWord.setVisible(!vBoxRemoveWord.isVisible());
  }

  public void onClickButtonSubmitRemoveWord() {
    wordTarget = textFieldRemoveWord.getText();
    if (DictionaryManagement.wordIsExisted(wordTarget)) {
      DictionaryManagement.removeWord(wordTarget);
      DictionaryManagement.exportToDictionaryFile();
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText("This word was removed");
      alert.setContentText("You have removed this word successfully!");
      alert.show();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error...");
      alert.setHeaderText("This word does not exist");
      alert.setContentText("You can not remove this word becase it is does not exist in Dictionary!");
      alert.show();
    }
    refreshToStart();
    textFieldRemoveWord.setText("");
  }

  public void onClickButtonShowAllWords(){
    refreshToStart();
    listViewShowWord.getItems().addAll(DictionaryManagement.showAllWords());
  }

  public void onClickButtonShowHistory() {
    refreshToStart();
    listViewSearcher.getItems().addAll(DictionaryManagement.historySearcher());
    DictionaryManagement.insertFromHistoryFile();
  }

  public void onClickButtonYourList() {
    refreshToStart();
    vBoxYourList.setVisible(!vBoxYourList.isVisible());
  }

  public void onClickButtonAddNewWordToYourList() throws IOException {
    Stage stage = new Stage();
    FXMLLoader loader =
        new FXMLLoader(
            new File(
                    "D:\\Code\\OOP\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\YourList.fxml")
                .toURI()
                .toURL());
    Parent root = loader.load();
    stage.setTitle("Add new word to your list");
    Scene scene = new Scene(root, 250, 200);
    stage.setScene(scene);
    stage.show();
  }

  public void onClickButtonSubmitAddNewWordToYourList() {
    wordTarget = textFieldAddNewWordToYourList.getText();
    if (DictionaryManagement.wordIsExisted(wordTarget)) {
      DictionaryManagement.getFavoriteList().add(wordTarget);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText("This word was added");
      alert.setContentText("You have added this word to your favorite list successfully!");
      alert.show();
      DictionaryManagement.exportToFavoriteFile();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error...");
      alert.setHeaderText("This word does not exist");
      alert.setContentText("You can not add this word becase it is does not exist in Dictionary!");
      alert.show();
    }
    textFieldAddNewWordToYourList.setText("");
  }

  public void onClickButtonShowYourList() {
    refreshToStart();
    listViewSearcher.getItems().addAll(DictionaryManagement.getFavoriteList());
    System.out.println(DictionaryManagement.getFavoriteList());
  }

  private void addNewWordToDictionary(Word word) {
    if (!DictionaryManagement.wordIsExisted(word)) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error...");
      alert.setHeaderText("This word is existed");
      alert.setContentText("You can't add this word to your dictionary!");
      alert.show();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText("This word is added");
      alert.setContentText("You have added this word successfully!");
      alert.show();
      Dictionary.addNewWord(word);
    }
  }

  private void refreshToStart(){
    textFieldSearcher.setText("");
    listViewShowWord.getItems().clear();
    listViewSearcher.getItems().clear();
    wordTarget = "";
  }
}