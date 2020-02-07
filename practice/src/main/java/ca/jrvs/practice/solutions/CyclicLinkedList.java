package main.java.ca.jrvs.practice.solutions;

public class CyclicLinkedList {
  private static class ListNode<E> {
    E contents = null;
    ListNode<E> next = null;
  
    public ListNode (E contents, ListNode<E> next) {
      this.contents = contents;
      this.next = next;
    }
  }
  
  public static <E> boolean isCyclicList(ListNode<E> start) {
    ListNode<E> current = start;
    while(current.next != null) {
      current = current.next;
      if (current == start) {
        return true;
      }
    }
    return false;
  }
  
  public static void main (String[] args) {
    ListNode<Integer> tail = new ListNode<Integer>(0, null);
    ListNode<Integer> fourth = new ListNode<Integer>(0, tail);
    ListNode<Integer> third = new ListNode<Integer>(0, fourth);
    ListNode<Integer> second = new ListNode<Integer>(0, third);
    ListNode<Integer> start = new ListNode<Integer>(0, second);
    tail.next = start;
    
    System.out.println("The given linked list "
                           + (isCyclicList(start) ? "is" : "is not")
                           + " cyclic.");
  
    tail.next = null;
  
    System.out.println("The given linked list "
                           + (isCyclicList(start) ? "is" : "is not")
                           + " cyclic.");
  }
}
