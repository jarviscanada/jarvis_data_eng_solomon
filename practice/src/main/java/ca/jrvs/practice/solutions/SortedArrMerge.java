package main.java.ca.jrvs.practice.solutions;

import java.util.Arrays;

public class SortedArrMerge<E>{
  public static void mergedSortedArray(Integer[] first, Integer[] second) {
    int endOfContentIndex = first.length - second.length - 1;
    int index1 = 0;
    int index2 = 0;
    
    while(index1 < first.length && index2 < second.length){
      index1 = 0;
      while(second[index2] >= first[index1] && index1 <= endOfContentIndex) {
        index1++;
      }
      push(first, index1, endOfContentIndex);
      first[index1] = second[index2++];
      endOfContentIndex++;
    }
  }
  
  private static void push(Integer[] array, int startIndex, int endIndex) {
    for (int i = endIndex; i >= startIndex ; i--) {
      array[i + 1] = array[i];
    }
  }

  
  public static void main (String[] args) {
    Integer[] nums1 = new Integer[]{1, 2, 3, 0, 0, 0};
    Integer[] nums2 = new Integer[]{2, 5, 6};
    
    mergedSortedArray(nums1, nums2);
  
    Arrays.stream(nums1).forEach(integer -> System.out.print(integer + " "));
    
    System.out.print("\n");
    
    nums1 = new Integer[]{3, 5, 7, 0, 0, 0, 0, 0};
    nums2 = new Integer[]{3, 9, 11, 11, 14};
  
    mergedSortedArray(nums1, nums2);
  
    Arrays.stream(nums1).forEach(integer -> System.out.print(integer + " "));
  }
}