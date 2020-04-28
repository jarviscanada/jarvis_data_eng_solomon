package main.java.ca.jrvs.practice.solutions;

import java.util.Deque;
import java.util.LinkedList;

public class StackUsingQueue<E> {
  private final Deque<E> queue;
  private int stackSize;
  
  StackUsingQueue(){
    queue = new LinkedList<>();
    stackSize = 0;
  }
  
  public boolean empty(){
    return stackSize == 0;
  }
  
  public E pop () {
    for (int i = 0; i < stackSize - 1; i++) {
      queue.push(queue.poll());
    }
    stackSize--;
    return queue.poll();
  }
  
  public void push (E element){
    queue.push(element);
    stackSize++;
  }
  
  public E peek() {
    for (int i = 0; i < stackSize - 1; i++) {
      queue.push(queue.poll());
    }
    E peeked = queue.peek();
    queue.push(queue.poll());
    return peeked;
  }

  public static void main (String[] args) {
    String first = "First";
    String second = "Second";
    String third = "Third";
    String fourth = "Fourth";
    
    StackUsingQueue<String> stackUsingQueue = new StackUsingQueue<>();
    
    stackUsingQueue.push(first);
    System.out.println(stackUsingQueue.peek());
    stackUsingQueue.push(second);
    System.out.println(stackUsingQueue.peek());
    stackUsingQueue.push(third);
    System.out.println(stackUsingQueue.peek());
    stackUsingQueue.push(fourth);
    System.out.println(stackUsingQueue.peek());
    
    System.out.println(stackUsingQueue.pop());
    System.out.println(stackUsingQueue.peek());
  }
}
