package com.example.dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;

/**
 * this class has method with typing function.
 */
public class DictionaryManagement {
  private static int quantity = 0;
  private static Stack<String> historyList = new Stack<>();
  private static ArrayList<String> favoriteList = new ArrayList<>();
  /**
   * hàm nhập dữ liệu từ điển từ file txt.
   */
  public static void insertFromDictionaryFile() {
    try {
      FileReader fr = new FileReader("dictionary.txt");
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      while(true) {
        line = br.readLine();
        if (line == null) {
          break;
        }
        String[] txt = line.split(": ");
        String w_tar = txt[0];
        String w_exp = txt[1];
        Dictionary.addNewWord(new Word(w_tar, w_exp));
      }
      br.close();
      fr.close();
    } catch (Exception ignored){
      System.out.println("Error!");
    }
  }

  /**
   * hàm nhập dữ liệu từ file lịch sử.
   */
  public static void insertFromHistoryFile() {
      try {
        FileReader hfr = new FileReader("history.txt");
        BufferedReader br = new BufferedReader(hfr);
        String hline = "";
        while(true) {
          hline = br.readLine();
          if (hline == null) {
            break;
          }
          DictionaryManagement.historyList.push(hline);
        }
        br.close();
        hfr.close();
    } catch (Exception ignored){
      System.out.println("Error!");
    }
  }

  /**
   * hàm nhập dữ liệu từ yêu thích.
   */
  public static void insertFromFavoriteFile() {
    try {
      FileReader ffr = new FileReader("favorite.txt");
      BufferedReader br = new BufferedReader(ffr);
      String fline = "";
      while(true) {
        fline = br.readLine();
        if (fline == null) {
          break;
        }
        DictionaryManagement.favoriteList.add(fline);
      }
      br.close();
      ffr.close();
    } catch (Exception ignored){
      System.out.println("Error!");
    }
  }

  /**
   * hàm có chức năng tra cứu từ điển bằng dòng lệnh.
   */
  public static String getWordResultFromDictionary(String w) {
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (w.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
            return Dictionary.getDictionary().get(i).getWord_explain();
      }
    }
    return "";
  }

  /**
   * Hàm in ra danh sách dữ liệu từ điển trên màn hình.
   */
  public static ArrayList<String> showAllWords() {
    ArrayList<String> wordsList = new ArrayList<>();
    String temp = "";
    for (int i = 0; i < Dictionary.getSize(); i++) {
      temp = Dictionary.getDictionary().get(i).getWord_target()
          + ": " + Dictionary.getDictionary().get(i).getWord_explain();
      wordsList.add(temp);
    }
    return wordsList;
  }

  /**
   * Hàm có chức năng tìm kiếm nhiều từ.
   */
  public static ArrayList<String> dictionarySearcher(String w) {
    ArrayList<String> result = new ArrayList<>();
    int w_length = w.length();
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (w_length <= Dictionary.getDictionary().get(i).getWord_target().length()) {
        if (w.equalsIgnoreCase(
            Dictionary.getDictionary().get(i).getWord_target().substring(0, w_length))) {
          result.add(Dictionary.getDictionary().get(i).getWord_target());
        }
      }
    }
    return result;
  }

  /**
   * hàm kiểm tra từ nhập vào trong chức năng sửa/thêm từ đã tồn tại hay chưa.
   */
  public static boolean wordIsExisted(Word word) {
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (word.getWord_target().equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        return false;
      }
    }
    return true;
  }

  /**
   * hàm kiểm tra từ nhập vào trong chức năng tìm kiếm từ đã tồn tại hay chưa.
   */
  public static boolean wordIsExisted(String w) {
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (w.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        return true;
      }
    }
    return false;
  }

  /**
   * hàm kiểm tra xem nghĩa của từ có thay đổi không.
   */
  public static boolean sameMeaning(Word word) {
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (word.getWord_target().equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        if (word.getWord_explain().equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_explain())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * hàm cho phép sửa dữ liệu.
   */
  public static void fixWord(Word word) {
    for (Word i : Dictionary.getDictionary()) {
      if (word.getWord_target().equalsIgnoreCase(i.getWord_target())) {
        i.setWord_explain(word.getWord_explain());
        break;
      }
    }
  }

  /**
   * hàm cho phép xóa dữ liệu.
   */
  public static void removeWord(String rW) {
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (rW.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        Dictionary.getDictionary().remove(i);
        break;
      }
    }
  }

  /**
   * hàm cho phép ghi đè lại vào file txt.
   */
  public static void exportToDictionaryFile() {
    try {
      FileWriter fw = new FileWriter("dictionary.txt");
      BufferedWriter bw = new BufferedWriter(fw);
      for (Word i : Dictionary.getDictionary()) {
        bw.write(i.getInfo());
        bw.newLine();
      }
      bw.close();
      fw.close();
    } catch (Exception ignored) {
    }
  }

  public static void exportToHistoryFile() {
    try {
      FileWriter fw = new FileWriter("history.txt");
      BufferedWriter bw = new BufferedWriter(fw);
      for (String W : DictionaryManagement.historyList) {
        bw.write(W);
        bw.newLine();
      }
      bw.close();
      fw.close();
    } catch (Exception ignored) {
    }
  }

  public static void exportToFavoriteFile() {
    try {
      FileWriter fw = new FileWriter("favorite.txt");
      BufferedWriter bw = new BufferedWriter(fw);
      for (String w : DictionaryManagement.favoriteList) {
        bw.write(w);
        bw.newLine();
      }
      bw.close();
      fw.close();
    } catch (Exception ignored) {
    }
  }

  /**
   * Hàm in ra lịch sử tìm kiếm.
   */
  public static ArrayList<String> historySearcher() {
    ArrayList<String> temp = new ArrayList<>();
    while (!historyList.isEmpty()) {
      temp.add(historyList.pop());
    }
    return temp;
  }

  /**
   * hàm getter của mảng từ quá khứ.
   */
  public static Stack<String> getHistoryList() {
    quantity++;
    return historyList;
  }

  /**
   * hàm getter của màng từ yêu thích.
   */
  public static ArrayList<String> getFavoriteList() {
    return favoriteList;
  }

}

