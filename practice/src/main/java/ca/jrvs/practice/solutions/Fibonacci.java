package main.java.ca.jrvs.practice.solutions;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
  static Map<Integer, Integer> storedFibs = new HashMap<>();
  
  public static int nthFibonacci (int n) {
    if (n < 2) return n;
    return nthFibonacci(n - 1) + nthFibonacci(n - 2);
  }

  public static int nthFibonacciDP  (int n) {
    if(storedFibs.get(n) != null) {
      return storedFibs.get(n);
    }

    if(n < 2) {
      storedFibs.putIfAbsent(n, n);
      return n;
    }
    
    for (int i = 2; i < n; i++) {
      storedFibs.putIfAbsent(i, nthFibonacci(i));
    }
  
    return storedFibs.get(n - 2) + storedFibs.get(n - 1);
  }

  public static void main (String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.print(nthFibonacci(i) + " ");
    }
    System.out.println("\n");
    for (int i = 0; i < 12; i++) {
      System.out.print(nthFibonacciDP(i) + " ");
    }
  }
}
