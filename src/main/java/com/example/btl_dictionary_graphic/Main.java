package com.example.btl_dictionary_graphic;

public class Main {
  public static void main(String[] args) {
    DictionaryManagement.insertFromFile();
    DictionaryCommandline.dictionaryBasic();
    DictionaryManagement.dictionaryLookup();
    DictionaryManagement.fixWord();
    DictionaryManagement.removeWord();
    DictionaryCommandline.showAllWords();
    DictionaryCommandline.dictionarySearcher();
  }
}
