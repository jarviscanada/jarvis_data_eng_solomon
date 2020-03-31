package main.java.ca.jrvs.practice.solutions;

import java.util.ArrayList;
import java.util.List;

public class DuplicateCharacter {
  static List<String> duplicateCharacter(String input) {
    List<String> duplicates = new ArrayList<>();
    String[] letters = input.replaceAll(" ", "").split("");
    
    for(String letter : letters) {
      if (uniqueChar.contains(letter) == true){
        duplicates.add(letter);
      }
    }
    return duplicates;
  }

  public static void main (String[] args) {
    String example = "a black cat";
    StringBuilder builder = new StringBuilder().append("[");
  
    duplicateCharacter(example).stream().forEach(letter -> builder.append(letter).append(", "));
    builder.deleteCharAt(builder.length() - 1).append("]");
    
    System.out.println(builder.toString());
    
    
    
  }
}
