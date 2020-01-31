package main.java.ca.jrvs.practice.solutions;

public class MiddleOfLinkedList {
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
  public static <E> ListNode<E> findMiddleOfLinkedList(ListNode<E> listHead) {
    ListNode<E> trailing = listHead;
    ListNode<E> leading = listHead;
    
    while(leading.next.next != null) {
      trailing = trailing.next;
      if(leading.next.next == null) break;
      leading = leading.next.next;
    }      if(leading.next.next == null) break;

    
    return trailing;
  }

  public static void main (String[] args) {
    ListNode<String> head = new ListNode<>("head");
    ListNode<String> second = new ListNode<>("second");
    ListNode<String> third = new ListNode<>("third");
    ListNode<String> fourth = new ListNode<>("fourth");
    ListNode<String> fifth = new ListNode<>("fifth");
  
    head.setNext(second);
    second.setNext(third);
    third.setNext(fourth);
    fourth.setNext(fifth);
    
    System.out.println(MiddleOfLinkedList.findMiddleOfLinkedList(head).contents);
    
    head.setNext(second);
    second.setNext(third);
    third.setNext(fourth);
    fourth.setNext(null);
    
    System.out.println(MiddleOfLinkedList.findMiddleOfLinkedList(head).contents);
  
  }
}
