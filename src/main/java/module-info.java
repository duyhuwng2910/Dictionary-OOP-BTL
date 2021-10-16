module com.example.btl_dictionary_graphic {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.example.btl_dictionary_graphic to javafx.fxml;
  exports com.example.btl_dictionary_graphic;
}