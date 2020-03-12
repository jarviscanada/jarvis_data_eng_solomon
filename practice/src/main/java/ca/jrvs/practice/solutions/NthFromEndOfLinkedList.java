package main.java.ca.jrvs.practice.solutions;

public class NthFromEndOfLinkedList<E> {
  static ListNode listHead;
  static int size = 0;
  
  public static class ListNode<E> {
    E contents = null;
    ListNode<E> next;
    public ListNode(E value){
      contents = value;
      next = null;
    }
  
    public ListNode<E> getNext () {
      return next;
    }
  
    public void setNext (ListNode<E> next) {
      this.next = next;
    }
  }

  /**
   * The node in the nth position when counting from the end
   * can be found by using two pointers, one leading and one trailing
   * behind by n positions. When the leading pointer points to the end,
   * the trailing pointer will point to the desired node.
   *
   * Assumptions: n is always valid (there are at minimum n + 1 nodes in list),
   * nth node is to be removed from list (keep track of node prior to desired node,
   * and make its next equal to the desired nodes next and setting desired node next
   * to null)
   */
  public static <E> ListNode<E> returnNthFromLast (ListNode<E> head, int n) {
    ListNode<E> trailing = new ListNode<E>(null);
    trailing.next = head;
    ListNode<E> leading = head;
    
    for(int i = n; i > 0; i--) {
      if (leading.next != null) {
        leading = leading.next;
      }
    }
    
    while (leading.next != null) {
      if(trailing == null) {
        trailing = head;
      } else {
        trailing = trailing.next;
      }
      leading = leading.next;
    }
    
    ListNode<E> desiredNode = new ListNode<>(trailing.next.contents);
    desiredNode.next = trailing.next.next;
    
    if (trailing.next.next != null) {
      trailing.next = trailing.next.next;
    }
    
    return desiredNode;
  }

  public static void main (String[] args) { //   head -> second -> third -> fourth -> fifth -> null
    listHead = new ListNode<>("head");
    ListNode<String> second = new ListNode<>("second");
    ListNode<String> third = new ListNode<>("third");
    ListNode<String> fourth = new ListNode<>("fourth");
    ListNode<String> fifth = new ListNode<>("fifth");
  
    listHead.setNext(second);
    second.setNext(third);
    third.setNext(fourth);
    fourth.setNext(fifth);
    
    int n = 5;
    
    System.out.println(returnNthFromLast(listHead, n).contents);
  }
}
