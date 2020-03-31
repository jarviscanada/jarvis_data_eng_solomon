package main.java.ca.jrvs.practice.solutions;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

  static boolean containsDuplicate(int[] nums) {
    Set<Integer> numSet = new HashSet<>();
    for (int num : nums){
      if (numSet.add(num) == false) {
        return true;
      }
    }
    return false;
  }
  
  public static void main (String[] args) {
    System.out.println(containsDuplicate(new int[]{1, 2, 3, 1}));
    System.out.println(containsDuplicate(new int[]{1, 2, 3, 4}));
    System.out.println(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
  }
}
