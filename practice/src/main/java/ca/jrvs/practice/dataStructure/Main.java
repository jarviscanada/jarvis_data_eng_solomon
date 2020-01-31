//package ca.jrvs.practice.dataStructure;
//
//import static java.lang.Math.floor;
//
//public class Main {
//
//    public static void heapSort(int[] arrayToHeapify, boolean minOrMaxHeap){
////        if(minOrMaxHeap){   //min Heap
////            Heap.minHeap heapToSort = new Heap.minHeap(arrayToHeapify.length);
////            heapToSort.heapArray = arrayToHeapify;
////            heapToSort.buildHeap(arrayToHeapify);
////
////            for(int i = heapToSort.currentHeapSize; i >= 0; i--){
////                heapToSort.minHeapify(i);
////            }
////            heapToSort.minHeapify(0);
////
////        }else{              //max Heap
////            Heap.maxHeap heapToSort = new Heap.maxHeap(arrayToHeapify.length);
////            heapToSort.heapArray = arrayToHeapify;
////            heapToSort.buildHeap(arrayToHeapify);
////
////            heapToSort.maxHeapify(0);
//
//        }
//
//    }
//
//    public static int partition(int[] totalArray, int boundOne, int boundTwo){
////        int pivotElement = totalArray[boundTwo];
////        int indexOne = boundOne - 1;
////
////        for(int indexTwo = boundOne; indexTwo < boundTwo; indexTwo++){
////            if(totalArray[indexTwo] <= pivotElement){
////                indexOne++;
////                int tempElement = totalArray[indexTwo];
////                totalArray[indexTwo] = totalArray[indexOne];
////                totalArray[indexOne] = tempElement;
////            }
////            int tempElementTwo = totalArray[boundTwo];
////            totalArray[boundTwo] = totalArray[indexOne + 1];
////            totalArray[indexOne + 1] = tempElementTwo;
////        }
////        return indexOne + 1;
//        return 0;
//    }
//
//    public static void quickSort(int[] arrayToBeSorted, int indexOne, int indexTwo){
//        if(indexOne < indexTwo){
//            int boundaryIndex = partition(arrayToBeSorted, indexOne, indexTwo);
//            quickSort(arrayToBeSorted, indexOne, boundaryIndex - 1);
//            quickSort(arrayToBeSorted, boundaryIndex + 1, indexTwo);
//        }
//    }
//
//    public static void merge(int[] arrayToBeSorted, int indexOne, int indexTwo, int indexThree){
//        int limitOne = indexOne - indexTwo + 1;
//        int limitTwo = indexThree - indexTwo;
//
//        int[] subArrayOne = new int[limitOne], subArrayTwo = new int[limitTwo];
//
//        for(int i = 0; i < limitOne; i++) subArrayOne[i] = arrayToBeSorted[indexOne + i];       //fill subarrays with
//        for(int j = 0; j < limitTwo; j++) subArrayTwo[j] = arrayToBeSorted[indexTwo + j];     //original array elements
//
//        subArrayOne[limitOne - 1] = 1337;
//        subArrayTwo[limitTwo - 1] = 1337;
//
//        int i = 0, j = 0;
//
//        for(int k = indexOne; k < indexThree; k++){         //comparing elements and placing them back in
//            if(subArrayOne[i] <= subArrayTwo[j]){           //original array
//                arrayToBeSorted[k] = subArrayOne[i];
//                i++;
//            }else{
//                arrayToBeSorted[k] = subArrayTwo[j];
//                j++;
//            }
//        }
//    }
//
//    public static void mergeSort(int[] arrayToBeSorted, int indexOne, int indexThree){ //[...indexOne...indexTwo...indexThree...]
//        if(indexOne < indexThree){
//            int indexTwo = (int) floor((indexOne + indexThree) / 2);
//            mergeSort(arrayToBeSorted, indexOne, indexTwo);
//            mergeSort(arrayToBeSorted, indexTwo + 1, indexThree);
//            merge(arrayToBeSorted, indexOne, indexTwo, indexThree);
//        }
//    }
//
//    public static void main(String[] args) {
////    System.out.println("\n/////////////////////////////Arrays///////////////////////////////////////////////////");
////        int[] testArray = new int[5];
////
////        testArray[0] = 2;
////        testArray[1] = 5;
////        testArray[2] = 6;
////        testArray[3] = 1;
////        testArray[4] = 0;
////
////        int[] copiedArray = testArray.clone();
////
////        System.out.print("testArray: ");
////        for(int i = 0; i < testArray.length; i++) System.out.print(testArray[i] + " ");
////
////        System.out.print("\ncopiedArray: ");
////        for(int i = 0; i < copiedArray.length; i++) System.out.print(copiedArray[i] + " ");
////
//////        System.out.println("\n/////////////////////////////Linked List///////////////////////////////////////////////");
//////        LinkedList testList = new LinkedList();
//////
//////        testList.addFirstNode(new ListNode(5));
//////        testList.addLastNode(new ListNode(7));
//////        testList.printList();
//////
//////        testList.addFirstNode(new ListNode(6));
//////        testList.printList();
//////
//////        testList.deleteNodeByPos(1);
//////        testList.printList();
//////
//////        testList.deleteNodeByKey(7);
//////        testList.printList();
//////
//////        System.out.println("\n///////////////////////////////// Stack & Queue/////////////////////////////////////////");
//////        Stack testStack = new Stack();
//////
//////        testStack.push(new ListNode(5));
//////        testStack.push(new ListNode(16));
//////        testStack.push(new ListNode(90));
//////        testStack.push(new ListNode(30));
//////
//////        testStack.printStack();
//////
//////        System.out.println("\nStack size: " + testStack.size());
//////
//////        testStack.peek();
//////        testStack.pop();
//////        testStack.printStack();
//////
//////        System.out.println("\n///////////////////////////////");
//////        Queue testQueue = new Queue();
//////
//////        testQueue.enqueue(new ListNode(5));
//////        testQueue.enqueue(new ListNode(16));
//////        testQueue.enqueue(new ListNode(90));
//////        testQueue.enqueue(new ListNode(30));
//////        testQueue.printQueue();
//////
//////        System.out.println("\nQueue size: " + testQueue.size());
//////
//////        testQueue.dequeue();
//////        testQueue.dequeue();
//////
//////        testQueue.printQueue();
//////
//////        System.out.println("\n/////////////////////////Binary Search Tree////////////////////////////////////////////");
//////        BinarySearchTree testBST = new BinarySearchTree();
//////
//////        testBST.insertNode(new TreeNode(4, null, null));
//////        testBST.insertNode(new TreeNode(5, null, null));
//////        testBST.insertNode(new TreeNode(2, null, null));
//////        testBST.insertNode(new TreeNode(3, null, null));
//////        testBST.insertNode(new TreeNode(1, null, null));
//////
//////        testBST.printInOrder();
//////        testBST.printPostOrder();
//////        testBST.printPreOrder();
////
////        System.out.println("\n/////////////////////////////Sorts & Heaps//////////////////////////////////////////////");
////        int[] toBeHeaped = {3, 6, 8, 2, 4, 1, 10, 7, 5, 13};
////        int[] arrayOne = toBeHeaped, arrayTwo = toBeHeaped, arrayThree = toBeHeaped;
////
////        System.out.println("toBeHeaped: ");
////        for(int i = 0; i < toBeHeaped.length; i++) System.out.print(toBeHeaped[i] + " ");
////
////        mergeSort(arrayOne, 0, arrayOne.length-1);
////        System.out.print("\nmergeSort Result: ");
////        for(int i = 0; i < arrayOne.length; i++) System.out.print(arrayOne[i] + " ");
////
////        quickSort(arrayTwo, 0, arrayTwo.length - 1);
////        System.out.print("\nquickSort Result: ");
////        for(int i = 0; i < arrayTwo.length; i++) System.out.print(arrayTwo[i] + " ");
////
////        heapSort(arrayThree, true); //min
////        System.out.print("\nmin heapSort Result: ");
////        for(int i = 0; i < arrayThree.length; i++) System.out.print(arrayThree[i] + " ");
////
////        heapSort(arrayThree, false); //max
////        System.out.print("\nmax heapSort Result: ");
////        for(int i = 0; i < arrayThree.length; i++) System.out.print(arrayThree[i] + " ");
////    }
//}
