
/**
 * Write a description of class mainRun here.
 *
 * @author Sean McLoughlin
 * @version 11/19/2024
 */
public class mainRun
{
    public static void main(String[] args){
        //start bst test.............................................
        /*BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(3));
        t.insert(2);
        t.insert(4);
        t.insert(9);
        t.insert(4);
        t.insert(1);
        t.insert(-10);
        System.out.println(t.isEmpty());
        t.empty();
        System.out.println(t.isEmpty());
        t.insert(9);
        t.insert(10);
        t.insert(2);
        */
        //start avl test............................................
        
        AVLTree<Integer> a = new AVLTree(new AVLNode(5));
        a.insert(1);
        a.insert(3);
        a.insert(-1);
        a.insert(9);
        a.insert(3);
        System.out.println(a.getBalanceFactor(a.getRoot()));
        a.insert(3);
        System.out.println(Math.abs(a.getBalanceFactor(a.getRoot()))<2);
        a.insert(-10);
        System.out.println(Math.abs(a.getBalanceFactor(a.getRoot()))<2);
       /*
       BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(5));
        t.insert(2);t.insert(4);t.insert(9);t.insert(4);t.insert(1);t.insert(-10);
        System.out.println(t.isEmpty());
        t.empty();
        System.out.println(t.isEmpty());
        t.insert(7);
        System.out.println(t.isEmpty());
        */
    }
}
