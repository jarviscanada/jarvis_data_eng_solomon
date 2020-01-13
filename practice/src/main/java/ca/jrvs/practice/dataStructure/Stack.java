package ca.jrvs.practice.dataStructure;

public class Stack {
    LinkedList StackBody ;
    int topOfStackPosition;

    public Stack() {
        StackBody = new LinkedList();
        topOfStackPosition = -1;
    }

    public void push(LinkedList.ListNode toBePushed){
        StackBody.addLastNode(toBePushed);
        topOfStackPosition++;
        System.out.println("Pushed: " + toBePushed.toString());
    }

    public LinkedList.ListNode pop() {
        if (topOfStackPosition > 0) {
            topOfStackPosition--;
        } else {
            System.out.println("This stack is empty");
            return null;
        }
        System.out.println("Popped: " + StackBody.deleteNodeByPos(topOfStackPosition).toString());
        return StackBody.deleteNodeByPos(topOfStackPosition);
    }

    public LinkedList.ListNode peek(){
        System.out.println("Peeked: " + StackBody.findNodeByPos(topOfStackPosition).toString());
        return StackBody.findNodeByPos(topOfStackPosition);
    }

    public boolean isEmpty(){
        if(StackBody.findNodeByPos(0) == null) {
            System.out.println("The stack is empty\n");
            return true; //Sufficient to check if stack is empty
        }
        System.out.println("The stack is not empty\n");
        return false;
    }

    public void printStack(){
        System.out.println("\nStack:");
        StackBody.printList();
    }

    public int size(){
        return topOfStackPosition + 1;
    }

}
