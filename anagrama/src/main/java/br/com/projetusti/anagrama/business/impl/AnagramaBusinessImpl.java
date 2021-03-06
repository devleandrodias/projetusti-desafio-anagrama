package br.com.projetusti.anagrama.business.impl;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.projetusti.anagrama.util.ReaderFileText;

/**
 * AnagramaBusinessImpl
 */
public class AnagramaBusinessImpl {

  public static void main(String[] args) throws IOException {
    List<String> lines = ReaderFileText.readFileText();

    List<String> palavrasTratadasDiferentesTamanhosUnicas = new ArrayList<>();

    for (int i = 0; i < lines.size(); i++) {
      for (String word : lines.get(i).split(" ")) {
        String palavraTratada = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        if (!palavrasTratadasDiferentesTamanhosUnicas.contains(palavraTratada))
          palavrasTratadasDiferentesTamanhosUnicas.add(palavraTratada);
      }
    }

    List<List<String>> listaAnagramas = new ArrayList<>();

    for (int i = 0; i < palavrasTratadasDiferentesTamanhosUnicas.size(); i++) {
      for (int j = 0; j < palavrasTratadasDiferentesTamanhosUnicas.size(); j++) {
        if (i != j) {
          List<String> containsAnagram = isAnagrama(palavrasTratadasDiferentesTamanhosUnicas.get(i),
              palavrasTratadasDiferentesTamanhosUnicas.get(j));
          if (containsAnagram != null && !listaAnagramas.contains(containsAnagram)) {
            listaAnagramas.add(containsAnagram);
          }
        }
      }
    }

    List<String> anagramasNaoRepetidos = new ArrayList<>();

    for (List<String> l : listaAnagramas) {
      for (String m : l) {
        if (!anagramasNaoRepetidos.contains(m))
          anagramasNaoRepetidos.add(m);
      }
    }

    List<String> anagramasOrdenadosNaoRepetidos = new ArrayList<>();

    for (String string : anagramasNaoRepetidos) {

      char[] f = string.toCharArray();

      Arrays.sort(f);

      StringBuilder str = new StringBuilder();

      for (int i = 0; i < f.length; i++) {
        str.append(f[i]);
      }

      if (!anagramasOrdenadosNaoRepetidos.contains(str.toString()))
        anagramasOrdenadosNaoRepetidos.add(str.toString());
    }

    File file = new File("files\\anagrama.txt");

    try (FileWriter fileWriter = new FileWriter(file, true); FileReader fileReader = new FileReader(file);) {

      fileWriter.write(Integer.toString(anagramasOrdenadosNaoRepetidos.size()) + "\n");

      for (String string : anagramasNaoRepetidos) {
        fileWriter.write(string + "\n");
      }

      fileWriter.flush();

      char[] in = new char[500];

      for (char c : in) {
        System.out.print(c);
      }
    }
  }

  private static List<String> isAnagrama(String a, String b) {
    List<String> anagramas = new ArrayList<>();

    a = a.replace(" ", "");
    b = b.replace(" ", "");

    char[] c = a.toCharArray();
    char[] d = b.toCharArray();

    Arrays.sort(c);
    Arrays.sort(d);

    if (Arrays.equals(c, d)) {
      anagramas.add(a);
      anagramas.add(b);
      return anagramas;
    } else {
      return null;
    }
  }
}