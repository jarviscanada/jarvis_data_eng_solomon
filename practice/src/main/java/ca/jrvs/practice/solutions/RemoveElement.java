package main.java.ca.jrvs.practice.solutions;

public class RemoveElement {
  static int removeElement(int[] nums, int val) {
    int length = nums.length;
    int i = 0;
    
    while (i < nums.length) {
      if (nums[i] == val) {
        for (int j = i; j < nums.length - 1; j++) {
          nums[j] = nums[j + 1];
        }
        length--;
        i--;
      }
       i++;
    }
    
    return length;
  }

  public static void main (String[] args) {
    int[] nums = {3, 2, 2, 3};
    int val = 3;
    
    System.out.println(removeElement(nums, val));
    
    nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
    val = 2;
  
    System.out.println(removeElement(nums, val));
    
  }
}
