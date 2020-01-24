package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.dataStructure.deque.JDeque;
import ca.jrvs.practice.dataStructure.list.LinkedJList;

public class LinkedJListJDeque extends LinkedJList implements JDeque {
/**
 * This is equivalent dequeue operation in Queue ADT
 * <p>
 * Retrieves and removes the head of the queue represented by this deque
 * (in other words, the first element of this deque).
 *
 * @return the head of the queue represented by this deque
 * @throws NoSuchElementException if this deque is empty
 */
@Override
public Object remove () {
  return null;
}

/**
 * Pops an element from the stack represented by this deque. In other
 * words, removes and returns the first element of this deque.
 *
 * @return the element at the front of this deque (which is the top
 * of the stack represented by this deque)
 * @throws NoSuchElementException if this deque is empty
 */
@Override
public Object pop () {
  return null;
}

/**
 * Pushes an element onto the stack represented by this deque (in other
 * words, at the head of this deque) if it is possible to do so
 * immediately without violating capacity restrictions
 *
 * @param o the element to push
 * @throws NullPointerException if the specified element is null and this
 *                              deque does not permit null elements
 */
@Override
public void push (Object o) {

}

/**
 * Retrieves, but does not remove, the head of the queue represented by
 * this deque (in other words, the first element of this deque), or
 * returns {@code null} if this deque is empty.
 *
 * @return the head of the queue represented by this deque, or
 * {@code null} if this deque is empty
 */
@Override
public Object peek () {
  return null;
}
}
