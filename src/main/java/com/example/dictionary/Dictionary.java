package com.example.dictionary;

import java.util.ArrayList;

public class Dictionary {
  // TODO: Các thuộc tính của lớp Dictionary
  private static ArrayList<Word> dictionary = new ArrayList<>();
  private static int quantity = 0;

  /**
   * constructor không tham số.
   */
  Dictionary() {}

  /**
   * constructor có tham số.
   */
  Dictionary(ArrayList<Word> _words) {
    dictionary = _words;
  }

  /**
   * phương thức addNewWord dùng để thêm từ mới vào mảng.
   */
  public static void addNewWord(Word newWord) {
    dictionary.add(newWord);
    quantity++;
  }

  /**
   * phương thức trả về độ dài mảng.
   */
  public static int getSize() {
    return dictionary.size();
  }

  /**
   * phương thức trả về mảng.
   */
  public static ArrayList<Word> getDictionary() {
    return dictionary;
  }
}
