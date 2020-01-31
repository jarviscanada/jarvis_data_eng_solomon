package main.java.ca.jrvs.practice.solutions;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class PrimeCount {
  /**
   * Returns the list of prime numbers less than the given integer n.
   * If n < 2 there are no primes in this range thus returning an empty list.
   * Primes are defined as a number which is only divisible by 1 and itself.
   * @throws IllegalArgumentException
   * @return list of primes found
   */
  public static ArrayList<Integer> primesLessThanN (int n){
    ArrayList<Integer> foundPrimesList = new ArrayList<>();
    boolean toBeAdded = true;
    if(n < 0) throw new IllegalArgumentException("n must be greater than 0");
    if(n < 2) return foundPrimesList;
    if(n > 2) foundPrimesList.add(2);
    for (int i = 3; i < n; i++) {
      if (i % 2 != 0) {
        for (int j = 1; j < i; j++) {
          if (i % 2*j+1 == 0 && i != 2*j+1) {
              toBeAdded &= false;
          }
        }
        if(toBeAdded && foundPrimesList.contains(i) == false) {
          foundPrimesList.add(i);
          toBeAdded = true;
        }
      }
    }
    return foundPrimesList;
  }

  public static void main (String[] args) {
    System.out.println(PrimeCount.primesLessThanN(10));
  }
}
