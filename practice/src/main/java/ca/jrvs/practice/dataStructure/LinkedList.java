package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.dataStructure.list.*;

public class LinkedList<E> implements JList<E> {
  private ListNode listHead;

  public static class ListNode {
    private E nodeValue;
    private ListNode nextNode = null;

    ListNode() {
    }

    ListNode(E value) {
        this.nodeValue = value;
    }

    void setNextNode(ListNode nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "\nNode value: " + this.nodeValue;
    }
  }

  LinkedList() {
  }

  void addFirstNode(ListNode node) {
    if (this.listHead == null) {                     //If head node is empty, fill with new one
        this.listHead = node;
    } else {                                        //Else replace head and link to second node
        ListNode second = this.listHead;
        this.listHead = node;
        this.listHead.setNextNode(second);
    }
    System.out.println("Node added as the head of the linked list");
  }

  void addLastNode(ListNode node) {
    if (this.listHead == null) {                        //If head node is empty, fill with new one
        this.listHead = node;
    } else {                                                      //Else find last node
        ListNode currentNode = this.listHead;
        while (currentNode.nextNode != null) {
            currentNode = currentNode.nextNode;
        }
        currentNode.setNextNode(node);                                  //add new node to end
    }
    System.out.println("Node added to end of linked list");
  }

  ListNode findNodeByKey(int searchKey) {
    System.out.print("\nfindNodeByKey >> ");

    if (searchKey < 0) {
        System.out.println("Key not valid");
        return null;
    }
    ListNode currentNode = this.listHead;

    while (currentNode.nodeValue != searchKey) {                     //Traverse list comparing keys
        currentNode = currentNode.nextNode;
    }
    if (currentNode == null) {                                            //Node not found
        System.out.println("\nNode with key " + searchKey + " not in list");
        return null;
    } else {
        System.out.println("\nNode with key " + searchKey + " has been found");
        return currentNode;                                             //Return queried node
    }
  }

  ListNode findNodeByPos(int position) {
    System.out.print("\nfindNodeByPos >> ");

    if (position < 0) {
        System.out.println("Position not valid");
        return null;
    }

    ListNode currentNode = this.listHead;
    int currentPos = 0;

    while (currentPos != position) {                            //Traverse list comparing keys
        currentNode = currentNode.nextNode;
        currentPos++;
    }
    if (currentNode == null) {                                            //Node not found
        System.out.println("\nNode in position " + position + " not in list");
        return null;
    } else {
        System.out.println("\nNode in position " + position + " has been found");
        return currentNode;                                             //Return queried node
    }
  }

  ListNode deleteNodeByKey(int key) {
    System.out.print("\ndeleteNodeByKey >> ");

    if (key < 0) {
        System.out.println("Key not valid");
        return null;
    }
    ListNode toBeDeleted = findNodeByKey(key);                             //Search for node

    if (toBeDeleted == null) {
        System.out.println("Node with key " + key + " not in list");
        return null;                                         //return if not in list
    }
    
    ListNode currentNode = this.listHead;
    
    while (currentNode.nextNode != toBeDeleted) {            //find node prior to node in question
        currentNode = currentNode.nextNode;
    }

    currentNode.setNextNode(toBeDeleted.nextNode);                     //remove from list links
    System.out.println("Node with key " + key + " has been deleted");
    return toBeDeleted;
  }

  ListNode deleteNodeByPos(int position) {
    System.out.print("\ndeleteNodeByPos >> ");
    if (position < 0) {
        System.out.println("Position not valid");
        return null;
    }
    ListNode toBeDeleted = findNodeByPos(position);                   //Search for node

    if (toBeDeleted == null) {                                       //return if not in list
        System.out.println("Node in position " + position + " not in list");
        return null;
    }

    ListNode currentNode = this.listHead;
    
    if (currentNode == toBeDeleted) {                            //If the head is to be deleted
      this.listHead = currentNode.nextNode;
    } else {
      while (currentNode.nextNode != toBeDeleted) {          //find node prior to node in question
          currentNode = currentNode.nextNode;
      }
      currentNode.setNextNode(toBeDeleted.nextNode);          //remove from list links
    }

    System.out.println("Node in position " + position + " has been deleted");
    return toBeDeleted;
  }

  void printList() {
    StringBuilder printedList = new StringBuilder();
    ListNode currentNode = this.listHead;

    while (currentNode != null) {                                  //Traverse list
      printedList.append(currentNode.toString());            //appending toString() for each node
      currentNode = currentNode.nextNode;
    }
    System.out.println("Linked List contents: ");
    System.out.println(printedList.toString());                       //print list in stirng form
  }
  
  
}
