package com.example.btl_dictionary_graphic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * this class has method with typing function.
 */
public class DictionaryManagement {
  static Scanner scanner = new Scanner(System.in);

  /**
   * typing function method.
   */
  public static void insertFromCommandline() {
    int num = 0;
    boolean condition = false;

    System.out.print("Hãy nhập số lượng từ vựng bạn muốn thêm vào từ điển: ");

    do {
      if (scanner.hasNextInt()) {
        num = scanner.nextInt();
        if (num > 0) {
          condition = true;
        } else {
          System.out.print("Số liệu không hợp lệ, vui lòng nhập lại: ");
        }
      } else {
        System.out.print("Bạn đang không nhập số, vui lòng nhập lại: ");
        scanner.nextLine();
      }
    } while (!condition );

    scanner.nextLine();
    String w_t;
    String w_e;

    for (int i = 0; i < num; i++) {
      System.out.print("English: ");
      w_t = scanner.nextLine();
      System.out.print("Vietnamese: ");
      w_e = scanner.nextLine();
      Dictionary.addNewWord(new Word(w_t, w_e));
    }
  }

  /**
   * hàm nhập dữ liệu từ điển từ file txt.
   */
  public static void insertFromFile() {
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
   * hàm có chức năng tra cứu từ điển bằng dòng lệnh.
   */
  public static void dictionaryLookup() {
    System.out.print("Hãy nhập vào từ bạn muốn tra nghĩa: ");
    String w = scanner.nextLine();
    boolean check = false;
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (w.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        System.out.format("%-30s %-30s\n", "| Tiếng Anh", "| Tiếng Việt");
        System.out.format("%-30s %-30s\n",
            "| " + Dictionary.getDictionary().get(i).getWord_target(),
            "| " + Dictionary.getDictionary().get(i).getWord_explain());
        check = true;
        break;
      }
    }
    if (!check) {
      System.out.println("Từ bạn nhập vào không hợp lệ "
          + "hoặc không có trong từ điển, vui lòng thử lại sau.");
    }
  }

  /**
   * hàm cho phép sửa dữ liệu.
   */
  public static void fixWord() {
    System.out.print("Hãy nhập vào từ bạn muốn sửa nghĩa trong từ điển: ");
    String fw = scanner.nextLine();
    boolean check_fw = false;
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (fw.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        System.out.print("Nghĩa mới của từ: ");
        String fw_explain = scanner.nextLine();
        Dictionary.getDictionary().get(i).setWord_explain(fw_explain);
        System.out.println("Bạn đã sửa nghĩa thành công. Nghĩa mới của "
            + Dictionary.getDictionary().get(i).getWord_target() + " la "
            + Dictionary.getDictionary().get(i).getWord_explain() + ".");
        check_fw = true;
        break;
      }
    }
    if (!check_fw) {
      System.out.println("Từ bạn nhập vào không hợp lệ "
          + "hoặc không có trong từ điển, vui lòng thử lại sau.");
    }
  }

  /**
   * hàm cho phép xóa dữ liệu.
   */
  public static void removeWord() {
    System.out.print("Hãy nhập từ bạn muốn xóa khỏi từ điển: ");
    String rW = scanner.nextLine();
    boolean check_rw = false;
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (rW.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target())) {
        System.out.println("Bạn đã xóa thành công từ "
            + Dictionary.getDictionary().get(i).getWord_target() + ".");
        Dictionary.getDictionary().remove(i);
        check_rw = true;
        break;
      }
    }
    if (!check_rw) {
      System.out.println("Từ bạn nhập vào không hợp lệ "
          + "hoặc không có trong từ điển, vui lòng thử lại sau.");
    }
  }
}

