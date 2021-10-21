module com.example.dictionary {
  requires javafx.controls;
  requires javafx.fxml;
  requires voicerss.tts;
  requires java.desktop;

  opens com.example.dictionary to javafx.fxml;
  exports com.example.dictionary;
}