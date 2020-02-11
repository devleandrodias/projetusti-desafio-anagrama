package br.com.projetusti.anagrama.business.impl;

import java.io.IOException;
import java.util.List;

import br.com.projetusti.anagrama.util.ReaderFileText;

/**
 * AnagramaBusinessImpl
 */
public class AnagramaBusinessImpl {

  public static void main(String[] args) throws IOException {
    List<String> lines = ReaderFileText.readFileText();

    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      String[] words = line.split(" ");
      for (String word : words) {
        System.out.println(word);
      }
    }
  }
}