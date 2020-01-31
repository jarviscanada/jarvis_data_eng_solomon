package main.java.ca.jrvs.practice.solutions;

import java.util.HashMap;
import java.util.Map;

public class TwoMapCompare<K, V> {
  public static <K, V> boolean compareTwoMaps(Map<K, V> m1, Map<K, V> m2) {
    if (m1.size() != m2.size()) {
      return false;
    }
    
    return m1.values() == m2.values() && m1.keySet() == m2.keySet();
//    return m1.keySet().stream().allMatch((K key) -> m1.get(key) == m2.get(key));
    
//    for(K key : m1.keySet()) {
//      if (m1.get(key) != m2.get(key)) return false;
//    }
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
    
  }
}
