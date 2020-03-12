package main.java.ca.jrvs.practice.solutions;

public class SwapNumber {

  public static void swapNumber (int[] array) {
    array[0] += array[1];
    array[1] = array[0] - array[1];
    array[0] = array[0] - array[1];
  }
  
  public static void main (String[] args) {
    int[] array = {2, 3};
    System.out.println(new StringBuilder().append("[").append(array[0]).append(", ")
                           .append(array[1]).append("]").toString());
  
    swapNumber(array);
    System.out.println(new StringBuilder().append("[").append(array[0]).append(", ")
                           .append(array[1]).append("]").toString());
  }
}
