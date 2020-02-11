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

    for (int i = 1; i < lines.size(); i++) {
      String line = lines.get(i);
      System.out.println(line);
    }
  }
}