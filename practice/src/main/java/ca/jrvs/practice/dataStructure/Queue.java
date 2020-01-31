//package ca.jrvs.practice.dataStructure;
//
//public class Queue {
//    LinkedList QueueBody;
//
//    int sizeOfQueue;
//
//    public Queue(){
//        QueueBody = new LinkedList();
//        sizeOfQueue = -1;
//    }
//
//    void enqueue(LinkedList.ListNode toBeAdded){
//        System.out.println("Enqueued " + toBeAdded.toString());
//        QueueBody.addLastNode(toBeAdded);
//        sizeOfQueue++;
//    }
//
//    LinkedList.ListNode dequeue(){
//        if(sizeOfQueue > 0) {
//            LinkedList.ListNode toBePopped = QueueBody.deleteNodeByPos(0);
//            System.out.println("Dequeued " + toBePopped.toString());
//            sizeOfQueue--;
//            return toBePopped;
//        }else{
//            System.out.println("Queue empty");
//            return null;
//        }
//    }
//
//    public boolean isEmpty(){
//        if(QueueBody.findNodeByPos(0) == null) {
//            System.out.println("The queue is empty\n");
//            return true; //Sufficient to check if queue is empty
//        }
//        System.out.println("The queue is not empty\n");
//        return false;
//    }
//
//    public void printQueue(){
//        System.out.println("\nQueue:");
//        QueueBody.printList();
//    }
//
//    public int size(){
//        return sizeOfQueue + 1;
//    }
//
//}
