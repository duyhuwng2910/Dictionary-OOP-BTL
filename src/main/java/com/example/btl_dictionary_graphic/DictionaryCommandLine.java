package com.example.btl_dictionary_graphic;

import java.util.Scanner;

class DictionaryCommandline {
  static Scanner scanner = new Scanner(System.in);

  /**
   * Hàm in ra danh sách dữ liệu từ điển trên màn hình.
   */
  public static void showAllWords() {
    System.out.format("%-5s %-30s %-30s\n", "No", "| English", "| Vietnamese");
    for (int i = 0; i < Dictionary.getSize(); i++) {
      System.out.format("%-5s %-30s %-30s\n",
          i+1,
          "| " + Dictionary.getDictionary().get(i).getWord_target(),
          "| " + Dictionary.getDictionary().get(i).getWord_explain() );
    }
  }

  /**
   * Hàm có chức năng gọi 2 hàm dưới.
   */
  public static void dictionaryBasic() {
    DictionaryManagement.insertFromCommandline();
    DictionaryCommandline.showAllWords();
  }

  /**
   * Hàm có chức năng gọi 3 hàm dưới.
   */
  public static void dictionaryAdvanced() {
    DictionaryManagement.insertFromFile();
    DictionaryCommandline.showAllWords();
    DictionaryManagement.dictionaryLookup();
  }

  /**
   * Hàm có chức năng tìm kiếm nhiều từ.
   */
  public static void dictionarySearcher() {
    System.out.print("Hãy nhập vào từ bạn muốn tra nghĩa: ");
    String w = scanner.nextLine();
    int w_length = w.length();
    boolean check_w = false;
    System.out.println("Đây là kết quả trả về cho từ bạn vừa nhập:");
    System.out.format("%-30s %-30s\n", "| Tiếng Anh", "| Tiếng Việt");
    for (int i = 0; i < Dictionary.getSize(); i++) {
      if (w.equalsIgnoreCase(Dictionary.getDictionary().get(i).getWord_target().substring(0,w_length))) {
        check_w = true;
        System.out.format("%-30s %-30s\n",
            "| " + Dictionary.getDictionary().get(i).getWord_target(),
            "| " + Dictionary.getDictionary().get(i).getWord_explain());
      }
    }
    if (!check_w) {
      System.out.println("Từ bạn nhập vào không hợp lệ "
          + "hoặc không có trong từ điển, vui lòng thử lại sau.");
    }
  }
}


