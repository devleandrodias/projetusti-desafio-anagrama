package br.com.projetusti.anagrama.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ReaderFileText
 */
public class ReaderFileText {

  public static List<String> readFileText() throws IOException {
    Path path = Paths.get("files//teste.txt");

    FileReader file = new FileReader(path.toString());

    BufferedReader reader = new BufferedReader(file);

    String line;

    List<String> arrayList = new ArrayList<>();

    while ((line = reader.readLine()) != null) {
      arrayList.add(line);
    }

    reader.close();

    return arrayList;
  }
}