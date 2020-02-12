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
    // Lê o arquivo de texto linha a linha
    List<String> lines = ReaderFileText.readFileText();

    // Cria o array de palavras sem acentuações espaços e caracteres especiais
    List<String> palavrasTratadasDiferentesTamanhosUnicas = new ArrayList<>();

    // Faz a limpeza das palavras e os adiciona em um array, sem repetir palavras
    for (int i = 0; i < lines.size(); i++) {
      for (String word : lines.get(i).split(" ")) {
        String palavraTratada = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        if (!palavrasTratadasDiferentesTamanhosUnicas.contains(palavraTratada))
          palavrasTratadasDiferentesTamanhosUnicas.add(palavraTratada);
      }
    }

    // Cria uma lista de anagramas que serão escritos no arquivo de texto
    List<List<String>> listaAnagramas = new ArrayList<>();

    // Método que compara as palavras e verifica caso a caso se são anagramas
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

    // Separa as anagramas são repetidos
    List<String> anagramasNaoRepetidos = new ArrayList<>();

    // Método que separa os anagramas repetidos
    for (List<String> l : listaAnagramas) {
      for (String m : l) {
        if (!anagramasNaoRepetidos.contains(m))
          anagramasNaoRepetidos.add(m);
      }
    }

    File file = new File("files\\anagrama.txt");

    try (FileWriter fileWriter = new FileWriter(file, true); FileReader fileReader = new FileReader(file);) {

      fileWriter.write("3" + "\n");

      for (String string : anagramasNaoRepetidos) {
        fileWriter.write(string + "\n");
      }

      fileWriter.flush();

      char[] in = new char[500];

      for (char c : in) {
        System.out.print(c);
      }
    }

    System.out.println(anagramasNaoRepetidos);
  }

  // Método que verifica se duas strings são anagramas
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