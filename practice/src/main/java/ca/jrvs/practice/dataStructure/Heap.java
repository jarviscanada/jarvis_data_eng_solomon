//package ca.jrvs.practice.dataStructure;
//
//import static java.lang.Math.floor;
//
//public class Heap {
//    int[] heapArray;
//    int heapSize = 0;
//    int currentHeapSize = 0;
//
//    Heap(){}
//
//    Heap(int maximumSize){
//        if(maximumSize <= 0) {
//            System.out.println("Invalid heap length");
//            return;
//        }
//        heapArray = new int[maximumSize];
//        heapSize = maximumSize;
//    }
//
//    public int findElementParent(int elementPosition){
//        return (int) floor(elementPosition / 2);
//    }
//
//    public int findElementLeftChild(int elementPosition){
//        return (2 * elementPosition);
//    }
//
//    public int findElementRightChild(int elementPosition){
//        return (elementPosition * 2) + 1;
//    }
//
//    public void swapElements(int valueOne, int valueTwo){
//        int tempValue = heapArray[valueOne];
//        heapArray[valueOne] = heapArray[valueTwo];
//        heapArray[valueTwo] = tempValue;
//    }
//
//    public void insert(int toBeInserted){
//        if(toBeInserted <= 0 || currentHeapSize == heapSize) return;
//        int i = 0;
//        while(heapArray[i] != 0)    i++;
//        heapArray[i] = toBeInserted;
//        currentHeapSize++;
//    }
//
//    public static class minHeap extends Heap{
//        minHeap(int heapSize){
//            super(heapSize);
//        }
//
//        public void minHeapify(int startNode){
//            int smallestElement;
//            int leftNode = findElementLeftChild(startNode);
//            int rightNode = findElementRightChild(startNode);
//
//            if(leftNode <= heapSize && heapArray[leftNode] < heapArray[startNode]){   //Compares startNode element with
//                smallestElement = leftNode;                                           //left and right nodes, finding
//            }else{                                                                    //the smallest
//                smallestElement = startNode;
//            }
//            if(rightNode <= heapSize && heapArray[rightNode] < heapArray[startNode]){
//                smallestElement = rightNode;
//            }else{
//                smallestElement = startNode;
//            }
//
//            if(smallestElement != startNode){                                            //if the startNode element
//                swapElements(startNode, smallestElement);                                //wasn't the smallest,
//                minHeapify(smallestElement);                                             //swap with the smallest element
//            }                                                                            //repeat until complete
//        }
//
//        public void insert(int toBeInserted){
//            super.insert(toBeInserted);
//            minHeapify(0);
//        }
//
//        public void buildHeap(int[] toBeHeaped){
//            currentHeapSize = toBeHeaped.length;
//            for(int i = (int) floor(currentHeapSize / 2); i >= 0; i--){
//                minHeapify(i);
//            }
//        }
//
//    }
//
//    public static class maxHeap extends Heap{
//        maxHeap(int heapSize){
//            super(heapSize);
//        }
//
//        public void maxHeapify(int startNode){
//            int largestElement;
//            int leftNode = findElementLeftChild(startNode);
//            int rightNode = findElementRightChild(startNode);
//
//            if(leftNode <= heapSize && heapArray[leftNode] > heapArray[startNode]){   //Compares startNode element with
//                largestElement = leftNode;                                            //left and right nodes, finding
//            }else{                                                                    //the largest
//                largestElement = startNode;
//            }
//            if(rightNode <= heapSize && heapArray[rightNode] > heapArray[startNode]){
//                largestElement = rightNode;
//            }else{
//                largestElement = startNode;
//            }
//
//            if(largestElement != startNode){                                            //if the startNode element
//                swapElements(startNode, largestElement);                                //wasn't the largest,
//                maxHeapify(largestElement);                                             //swap with the largest element
//            }                                                                           //and repeat until complete
//        }
//
//        public void insert(int toBeInserted){
//            super.insert(toBeInserted);
//            maxHeapify(0);
//        }
//
//        public void buildHeap(int[] toBeHeaped){
//            currentHeapSize = toBeHeaped.length;
//            for(int i = (int) floor(currentHeapSize / 2); i >= 0; i--){
//                maxHeapify(i);
//            }
//        }
//    }
//
//}
