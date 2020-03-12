package main.java.ca.jrvs.practice.solutions;

import java.util.PriorityQueue;

public class StackUsingQueue<E> {
  PriorityQueue<E> queue = new PriorityQueue<E>();
  
  public E pop () {
    return queue.remove();
  }
  
  public void push (E element){
    queue.add(element);
  }
  
  public E peek() {
    return null;
  }

  public static void main (String[] args) {
    String first = "First";
    String second = "Second";
    String third = "Third";
    String fourth = "Fourth";
    
    StackUsingQueue<String> stackUsingQueue = new StackUsingQueue<>();
    
    stackUsingQueue.push(first);
    stackUsingQueue.push(second);
    stackUsingQueue.push(third);
    stackUsingQueue.push(fourth);
    
    System.out.println(stackUsingQueue.peek().toString());
    
    
  }
  
}
