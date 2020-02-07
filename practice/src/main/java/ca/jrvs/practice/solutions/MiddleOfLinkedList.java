package main.java.ca.jrvs.practice.solutions;

public class MiddleOfLinkedList<E> {
  ListNode<E> listHead;
  int size = 1;
  
  private static class ListNode<E> {
    E contents = null;
    ListNode<E> next;
    public ListNode(E value){
      contents = value;
      next = null;
    }
  }
  
  public static <E> ListNode<E> findMiddleOfLinkedList(ListNode<E> listHead) {
    if (listHead == null) return null;
    if (listHead.next == null) return listHead;
    
    ListNode<E> trailing = listHead;
    ListNode<E> leading = listHead;
    
    while(leading.next != null) {
      if(leading.next.next == null) break;
      leading = leading.next.next;
      trailing = trailing.next;
    }

    return trailing;
  }

  public static void main (String[] args) {
    ListNode<String> listHead = new ListNode<>("listHead");
    ListNode<String> second = new ListNode<>("second");
    ListNode<String> third = new ListNode<>("third");
    ListNode<String> fourth = new ListNode<>("fourth");
    ListNode<String> fifth = new ListNode<>("fifth");
  
    listHead.next = second;
    second.next = third;
    third.next = fourth;
    fourth.next = fifth;
    
    System.out.println(MiddleOfLinkedList.findMiddleOfLinkedList(listHead).contents);
  
    listHead.next = second;
    second.next = third;
    third.next = fourth;
    fourth.next = null;
    
    System.out.println(MiddleOfLinkedList.findMiddleOfLinkedList(listHead).contents);
  }
}
