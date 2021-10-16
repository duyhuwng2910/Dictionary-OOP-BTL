package com.example.btl_dictionary_graphic;

public class Word {
  private String word_target;
  private String word_explain;

  /**
   * constructor không tham số.
   */
  Word() {
    word_target = "Hello";
    word_explain = "Xin chao";
  }

  /**
   * constructor có tham số.
   */
  Word(String wt, String we) {
    this.word_target = wt;
    this.word_explain = we;
  }

  public String getWord_target() {
    return word_target;
  }

  public void setWord_target(String word_target) {
    this.word_target = word_target;
  }

  public String getWord_explain() {
    return word_explain;
  }

  public void setWord_explain(String word_explain) {
    this.word_explain = word_explain;
  }

  public String getInfo() {
    return this.getWord_target() + " : " + this.getWord_explain();
  }
}

