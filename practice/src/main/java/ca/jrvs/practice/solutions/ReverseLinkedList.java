package main.java.ca.jrvs.practice.solutions;

import java.util.ArrayList;
import java.util.Arrays;

public class ReverseLinkedList<E> {
  static ListNode listHead;
  static int size = 1;

  private static class ListNode<E> {
    E contents;
    ListNode<E> next;
    
    public ListNode(E value){
      contents = value;
      next = null;
      size++;
    }
  
    public ListNode<E> getNext () {
      return next;
    }
  
    public void setNext (ListNode<E> next) {
      this.next = next;
    }
  
    public void add (ListNode<E> node) {
      ListNode<E> curr = listHead;
      while (curr.getNext() != null) {
        curr = curr.getNext();
      }
      curr.setNext(node);
      size++;
    }
    
    public ListNode<E> remove() {
      ListNode<E> curr = listHead;
      while (curr.getNext().getNext() != null) {
        curr = curr.next;
      }
      ListNode<E> removed = curr.getNext();
      curr.setNext(null);
      size--;
      return removed;
    }
    
  }

  public static <E> ListNode<E> reverseLinkedList (ListNode<E> listHead) {
    ListNode<E> prev = null;
    ListNode<E> curr = listHead;
    ListNode<E> tmp;
    while(curr != null) {
      tmp = curr.getNext();
      curr.setNext(prev);
      prev = curr;
      curr = tmp;
    }
    return prev;
  }
  
  public static void main (String[] args) {
    listHead = new ListNode<>("head");
    ListNode<String> second = new ListNode<>("second");
    ListNode<String> third = new ListNode<>("third");
    ListNode<String> fourth = new ListNode<>("fourth");
    ListNode<String> fifth = new ListNode<>("fifth");
  
    listHead.setNext(second);
    second.setNext(third);
    third.setNext(fourth);
    fourth.setNext(fifth);
    fifth.setNext(null);
    
    ListNode<String> curr = (ListNode<String>) reverseLinkedList(listHead);
    while(curr != null) {
      System.out.println(curr.contents);
      curr = curr.getNext();
    }
    
  }

}
