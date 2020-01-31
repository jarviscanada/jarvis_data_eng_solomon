//package ca.jrvs.practice.dataStructure;
//
//public class BinarySearchTree {
//    TreeNode treeRoot;
//
//    static class TreeNode {
//        int Key;
//        TreeNode Left, Right = null;
//
//        public TreeNode(int key, TreeNode left, TreeNode right){
//            this.setKey(key);
//            this.setRight(right);
//            this.setLeft(left);
//        }
//
//        public TreeNode getLeft() {
//            return Left;
//        }
//
//        public void setLeft(TreeNode left) {
//            Left = left;
//        }
//
//        public TreeNode getRight() {
//            return Right;
//        }
//
//        public void setRight(TreeNode right) {
//            Right = right;
//        }
//
//        public int getKey() {
//            return Key;
//        }
//
//        public void setKey(int key) {
//            Key = key;
//        }
//
//        @Override public String toString(){
//            StringBuilder leftKey = new StringBuilder();
//            StringBuilder rightKey = new StringBuilder();
//            if(this.getRight() == null){
//                 rightKey.append("null");
//            }else{
//                rightKey.append(this.getRight().getKey());
//            }
//            if(this.getLeft() == null){
//                leftKey.append("null");
//            }else{
//                leftKey.append(this.getLeft().getKey());
//            }
//            return "TreeNode: " + leftKey.toString() + "--( " + this.getKey() + " )--" + rightKey.toString();
//        }
//
//        public int compareTo(TreeNode node){
//            int result = this.getKey() - node.getKey();
//            if(result == 0){                            //Nodes have same key
//                return 0;
//            }
//            if(result < 0){
//                return -1;                              //node.getKey() is larger than this node's key
//            }else{
//                return 1;                               //this node's key is larger than node.getKey()
//            }
//        }
//    }
//
//    BinarySearchTree(){}
//
//    BinarySearchTree(int key){
//        treeRoot.setKey(key);
//    }
//
//    TreeNode searchTree(TreeNode startNode, int key){               //Search tree from particular staring node for
//        if(startNode == null || key < 0){                           //TreeNode with Key key
//            System.out.println("Invalid search target");
//            return null;
//        }
//
//        if(startNode.getKey() < key) searchTree(startNode.getLeft(), key);      //Recursively search leaf nodes and
//        if(startNode.getKey() > key) searchTree(startNode.getRight(), key);     //their subtrees
//
//        System.out.println("TreeNode " + key + " found.");
//        return startNode;
//    }
//
//    int size(TreeNode startNode){
//        if(startNode == null) return 0;
//        return size(startNode.getLeft()) + 1 + size(startNode.getRight());
//    }
//
//    int size(){
//        return size(treeRoot);
//    }
//
//    TreeNode findTreeMax(TreeNode startNode){
//        TreeNode maximumNode = startNode;
//        while(maximumNode.getRight() != null){
//            maximumNode = maximumNode.getRight();
//        }
//        return maximumNode;
//    }
//
//    TreeNode findTreeMin(TreeNode startNode){
//        TreeNode minimumNode = startNode;
//        while(minimumNode.getLeft() != null){
//            minimumNode = minimumNode.getLeft();
//        }
//        return minimumNode;
//    }
//
//    void insertNode(TreeNode toBeInserted){
//        TreeNode parentNode = null;
//        TreeNode currentNode = treeRoot;
//
//        if(toBeInserted == null){
//            System.out.println("Invalid insertion node");
//            return;
//        }
//
//        while(currentNode != null){
//            parentNode = currentNode;                //To keep track of node prior to inserted node's final position
//            if(currentNode.compareTo(toBeInserted) == -1){          //toBeInserted.getKey() > current.getKey()
//                currentNode = currentNode.getRight();
//            }else{
//                currentNode = currentNode.getLeft();
//            }
//        }
//
//        if(parentNode == null){
//            System.out.println("Test");
//            treeRoot = toBeInserted;                                //Fill empty tree node
//        }else if(parentNode.compareTo(toBeInserted) == -1){         //Otherwise, place in left or right leaf position
//            parentNode.setRight(toBeInserted);
//            parentNode.toString();
//        }else{
//            parentNode.setLeft(toBeInserted);
//            parentNode.toString();
//        }
//
//        System.out.println("Inserted Tree Node " + toBeInserted.getKey());
//    }
//
//    void deleteNode(TreeNode toBeDeleted){
//        if(toBeDeleted == null){
//            return;
//        }
//
//
//        if(toBeDeleted.getLeft() != null && toBeDeleted.getRight() != null){
//            toBeDeleted = findTreeMin(toBeDeleted.getRight());
//        }
//
//        if(toBeDeleted.getLeft() == null && toBeDeleted.getRight() == null) toBeDeleted = null;
//        if(toBeDeleted.getLeft() != null && toBeDeleted.getRight() == null) toBeDeleted = toBeDeleted.getLeft();
//        if(toBeDeleted.getLeft() == null && toBeDeleted.getRight() != null) toBeDeleted = toBeDeleted.getRight();
//    }
//
//    void inOrderTraversal(TreeNode rootOfSubTree){            //Left, Root, Right
//        if(rootOfSubTree == null){
//            //System.out.println("Tree not valid");
//            return;
//        }
//        inOrderTraversal(rootOfSubTree.getLeft());
//        System.out.println(rootOfSubTree.toString());
//        inOrderTraversal(rootOfSubTree.getRight());
//    }
//
//    void preOrderTraversal(TreeNode rootOfSubTree){           //Root, Left, Right
//        if(rootOfSubTree == null){
//            //System.out.println("Tree not valid");
//            return;
//        }
//        System.out.println(rootOfSubTree.toString());
//        preOrderTraversal(rootOfSubTree.getLeft());
//        preOrderTraversal(rootOfSubTree.getRight());
//    }
//
//    void postOrderTraversal(TreeNode rootOfSubTree){          //Left, Right, Root
//        if(rootOfSubTree == null){
//            //System.out.println("Tree not valid");
//            return;
//        }
//        postOrderTraversal(rootOfSubTree.getLeft());
//        postOrderTraversal(rootOfSubTree.getRight());
//        System.out.println(rootOfSubTree.toString());
//    }
//
//    void printInOrder(){
//        System.out.println("printInOrder: ");
//        inOrderTraversal(treeRoot);
//    }
//    void printPreOrder(){
//        System.out.println("printPreOrder: ");
//        preOrderTraversal(treeRoot);
//    }
//    void printPostOrder(){
//        System.out.println("printPostOrder: ");
//        postOrderTraversal(treeRoot);
//    }
//
//}
