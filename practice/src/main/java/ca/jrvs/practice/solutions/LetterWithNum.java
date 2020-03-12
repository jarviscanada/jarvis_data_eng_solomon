package main.java.ca.jrvs.practice.solutions;

import java.util.HashMap;
import java.util.Map;

public class LetterWithNum {

  public static String letterWithNum (String letters) {
    StringBuilder lettersWtihNum = new StringBuilder();
    Map<Integer, Integer> letterNumMap = new HashMap<>();
  
    for (int i = 'a'; i <= 'z'; i++) {
      letterNumMap.putIfAbsent(i, i - 'a' + 1);
    }
  
    for (int i = 'A'; i <= 'Z'; i++) {
      letterNumMap.putIfAbsent(i, i - 'A' + 27);
    }
    
    for (char letter : letters.toCharArray()) {
      lettersWtihNum.append(letter).append(letterNumMap.get((int) letter));
    }
    return lettersWtihNum.toString();
  }
  
  public static void main (String[] args) {
    String test = "abcee";
    System.out.println(letterWithNum(test));
  
    test = "rAcEcAr";
    System.out.println(letterWithNum(test));
  }
}
