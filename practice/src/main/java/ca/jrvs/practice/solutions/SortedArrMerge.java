package main.java.ca.jrvs.practice.solutions;

public class SortedArrMerge<E>{
  public static void mergedSortedArray(Integer[] first, Integer[] second) {
    int endOfFirstValues = first.length - second.length - 1; //2 -> first.length - 1 = 2 -> 5
    int current = 0; //0 -> second.length - 1
    
    while(endOfFirstValues < first.length) {
//      if (first[] > second[]) {
//        //push stuff all over + 1
//        //replace first i with second current
//        //done
//      } else { //second is greater or equal
//        //push stuff over + 1
//        //replace first i + 1 with second current
//      }
//      endOfFirstValues++;
//      current++;
    }
  }
  
  //1, 2, 3] 0, 0, 0 <- 2

  //1<, >2, 2->, 3->] 0, 0

  //1, >2, 2, 3] 0, 0 <- 5

  //1<, >2, 2, 3] 0, 0
  //1, >2<, 2, 3] 0, 0
  //1, >2, 2<, 3] 0, 0
  //1, >2, 2, 3<] 0, 0
  //1, >2, 2, 3, >5], 0

  //1, >2, 2, 3, >5], 0 <- 6

  //1<, >2, 2, 3 >5], 0
  //1, >2<, 2, 3 >5], 0
  //1, >2, 2<, 3 >5], 0
  //1, >2, 2, 3< >5], 0
  //1, >2, 2, 3, >5<], 0

  //1, >2, 2, 3, >5, >6]

  
  public static void main (String[] args) {
    Integer[] nums1 = new Integer[]{1, 2, 3, 0, 0, 0};
    Integer[] nums2 = new Integer[]{2, 5, 6};
    
    mergedSortedArray(nums1, nums2);
    
    System.out.println(nums1);
  }
}
