package main.java.ca.jrvs.practice.solutions;

public class DuplicatesFromSortedArr {
  public static int duplicatesFromSortedArr(int[] nums) {
    if (nums.length == 0) return 0;
    
    int trailing = 0;
    for (int leading = 1; leading < nums.length; leading++) {
      if (nums[trailing] != nums[leading]) {
        trailing++;
        nums[trailing] = nums[leading];
      }
    }
    return trailing + 1;
  }

  public static void main (String[] args) {
    int[] sortedArray = new int[]{1, 1, 2};
    int[] sortedArray2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
  
    int length1 = duplicatesFromSortedArr(sortedArray);
    int length2 = duplicatesFromSortedArr(sortedArray2);
    
    StringBuilder first = new StringBuilder();
    StringBuilder second = new StringBuilder();
    
    first.append("[");
    for (int i = 0; i < sortedArray.length; i++) {
      first.append(sortedArray[i]);
      if(i + 1 != sortedArray.length) first.append(", ");
    }
    first.append("]");
  
    second.append("[");
    for (int i = 0; i < sortedArray2.length; i++) {
      second.append(sortedArray2[i]);
      if(i + 1 != sortedArray2.length) second.append(", ");
    }
    second.append("]");
    
    System.out.println(length1);
    System.out.println(first.toString());
  
    System.out.println(length2);
    System.out.println(second.toString());
  }
}