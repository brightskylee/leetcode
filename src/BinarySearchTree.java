import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haotian on 2/4/16.
 */
public class BinarySearchTree {

    public static class Node {
        private int value;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node(){}

        public Node(int v, Node p, Node l, Node r){
            value = v;
            parent = p;
            leftChild = l;
            rightChild = r;
        }
    }

    private Node root = new Node();


    public BinarySearchTree(int[] initialValues){

        int pivot = initialValues[initialValues.length/2];


        root.value = pivot;
        root.parent = null;
        root.leftChild = null;
        root.rightChild = null;

        for( int i = 0; i<initialValues.length; i++){
            if(initialValues[i] != pivot){
                add(root, initialValues[i]);
            }
        }
    }


    /*
    public BinarySearchTree(int rootVal){
        root = new Node(rootVal, null, null, null);
    }
    */

    public static void add(Node rootNode, int value){

        if(rootNode == null){
            return;
        }

        if(value > rootNode.value){

            if(rootNode.rightChild == null){
                rootNode.rightChild = new Node(value, rootNode, null, null);
                return;
            }
            add(rootNode.rightChild, value);
        }
        else{
            if(rootNode.leftChild == null){
                rootNode.leftChild = new Node(value, rootNode, null, null);
                return;
            }
            add(rootNode.leftChild, value);
        }

    }

    public static List<Integer> traverse(Node root){

        List<Integer> ret = new ArrayList<>();
        if(root.leftChild == null && root.rightChild != null){
            ret.add(root.value);
            ret.addAll(traverse(root.rightChild));
        }
        else if(root.rightChild == null && root.leftChild != null){
            ret.addAll(traverse(root.leftChild));
            ret.add(root.value);
        }
        else if(root.leftChild != null && root.rightChild != null){
            ret.addAll(traverse(root.leftChild));
            ret.add(root.value);
            ret.addAll(traverse(root.rightChild));
        }
        else if(root.leftChild == null && root.rightChild == null){
            ret.add(root.value);
        }

        return ret;

    }


    public static boolean search(Node root, int value){

        boolean hasValue = false;

        if(root == null){
            return false;
        }

        if(root.value == value){
            return true;
        }

        if(root.value < value){
            hasValue = search(root.rightChild, value);
        }

        if(root.value > value){
            hasValue = search(root.leftChild, value);
        }

        return hasValue;
    }

    public static void main(String[] args){

        int[] initVal = {23, 42, 66, 101, 27};

        BinarySearchTree bt = new BinarySearchTree(initVal);
        bt.add(bt.root, 5);
        bt.add(bt.root, 3);
        bt.add(bt.root, 10);
        bt.add(bt.root, 6);

        System.out.println(Arrays.toString(traverse(bt.root).toArray()));

        System.out.print(search(bt.root, 101));
        System.out.print(search(bt.root, 102));
    }


}
