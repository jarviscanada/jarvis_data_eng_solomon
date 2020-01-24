package ca.jrvs.practice.dataStructure.list;

import com.sun.org.apache.xml.internal.utils.res.XResources_zh_CN;

import java.util.Arrays;
import java.util.Objects;

public class ArrayJList<E> implements JList<E> {
  
  /**
   * Default initial capacity.
   */
  private static final int DEFAULT_CAPACITY = 10;
  
  /**
   * The array buffer into which the elements of the ArrayList are stored.
   * The capacity of the ArrayList is the length of this array buffer.
   */
  transient Object[] elementData; // non-private to simplify nested class access
  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  private int size;
  
  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param  initialCapacity  the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity
   *         is negative
   */
  public ArrayJList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
                                             initialCapacity);
    }
  }
  
  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public ArrayJList() {
    this(DEFAULT_CAPACITY);
  }
  
  /**
   * Appends the specified element to the end of this list (optional
   * operation).
   *
   * Double elementData size if elementData is full.
   */
  @Override
  public boolean add(E e) {
    if (size == elementData.length) {
      Object[] oldElementData = elementData;
      elementData = new Object[2 * size];
      System.arraycopy(oldElementData, 0, elementData, 0, size);
    }
    elementData[size] = e;
    size++;
    return (elementData[size - 1] == e);
  }
  
  @Override
  public Object[] toArray() {
    Object[] toArray = new Object[elementData.length];
    System.arraycopy(elementData, 0, toArray, 0, size);
    return toArray;
  }
  
  @Override
  public int size() {
    return size;
  }
  
  @Override
  public boolean isEmpty() {
    for (Object elementDatum : elementData) {
      if (elementDatum != null) {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < size; i++){
      if(elementData[i] == o){
        return i;
      }
    }
    return -1;
  }
  
  @Override
  public boolean contains(Object o) {
    for (Object elementDatum : elementData) {
      if (o.equals(elementDatum)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public E get(int index) {
    for (int i = 0; i < size; i++){
      if (i == index) {
        return (E) elementData[i];
      }
    }
    return null;
  }
  
  /**
   * Removes the element at the specified position in this list.
   * Shifts any subsequent elements to the left (subtracts one from their
   * indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index > size - 1) throw new IllegalArgumentException("Index must be greater "
                                                                              + "than 0 and less "
                                                                              + "than size()");
    Object[] resizedArrayList = new Object[size - 1];
    Object[] oldElementArray = new Object[size];
    System.arraycopy(elementData, 0, oldElementArray, 0, size);
    for (int i = 0; i < size; i++) {
      if (i < index) {
        resizedArrayList[i] = oldElementArray[i];
      }
      if (i > index) {
        resizedArrayList[i - 1] = oldElementArray[i];
      }
    }
    elementData = resizedArrayList;
    size--;
    return (E) oldElementArray[index];
  }
  
  @Override
  public void clear() {
    elementData = new Object[DEFAULT_CAPACITY];
    size = 0;
  }
}