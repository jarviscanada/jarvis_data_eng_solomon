package main.java.ca.jrvs.practice.solutions;

import java.util.HashMap;
import java.util.Map;

public class TwoMapCompare<K, V> {
  public static <K, V> boolean compareTwoMaps(Map<K, V> m1, Map<K, V> m2) {
    if (m1.size() != m2.size()) {
      return false;
    }
    
    return m1.entrySet().stream().filter(entry -> m2.entrySet().contains(entry)).count() == m1.size();
  }

  public static void main (String[] args) {
    Map<Integer, String> m1 = new HashMap<>();
    Map<Integer, String> m2 = new HashMap<>();
    
    for (int i = 0; i < 5; i++) {
      int number = ((int)(100f * Math.random()));
      m1.put(number, number + "");
      m2.put(number, number + "");
    }
    
    System.out.println(compareTwoMaps(m1, m2));
    
    m2.put(((int)(100f * Math.random())), ((int)(100f * Math.random())) + "");
  
    System.out.println(compareTwoMaps(m1, m2));
    
  }
}
