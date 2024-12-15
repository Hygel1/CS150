/**
 * Binary tree containing nodes placed in order and inserted according to binary tree insert rules (less than moves left, more than moves right)
 *
 * @author Sean McLoughlin
 * @version 11/19/24
 */
//import java.util.Comparable;
public class BinarySearchTree<E extends Comparable> extends BinaryTree<E>{
    /**
     * builds new BinarySearchTree object with initial value as root node
     */
    public BinarySearchTree(BinaryNode e){
        super(e);
    }
    /**
     * inserts value into tree by building a node to represent it and moving through tree to find a valid empty child spot
     * values can only be added to tree is they are not already represented in the tree
     * @param e E type object representing value to be added to tree
     * @return boolean type object representing whether or not the insertion was successful
     */
    public boolean insert(E e){
        if(root==null){
            root=new BinaryNode(e);
            return true;
        }
        if(contains(e)) return false; //only false case      
        insertHelper(root,new BinaryNode(e));
        return true;
    }
    /**
     * determines whether the passed value is already present in the BinarySearchTree, taking advantage of the tree's contstantly sorted state to more efficiently search the tree
     * @param e E type object to be searched for in the tree
     * @return boolean value representing whether the value already exists in the tree
     */
    public boolean contains(E e){
        return containsHelper(e, root);
    }
    /**
     * private helper method to help determine whether a value exists in the tree or not, taking advantake of the constantly sorted state of the BinarySearchTree
     * @param val E type object representing value ot be searched for
     * @pararm n BinaryNode type ojbect representing current node being searched
     * @return boolean value representing whether the value exists in the tree
     */
    public boolean containsHelper(E val, BinaryNode n){
        if(val.compareTo(n.getValue())==0) return true;
        else if(val.compareTo(n.getValue())>0){
            if(n.getRight()==null) return false;
            return containsHelper(val, n.getRight());
        }
        else{
            if(n.getLeft()==null) return false;
            return containsHelper(val, n.getLeft());
        }
    }
    /**
     * private helper method to aid insertion into the binary search tree, moves through tree until an empty child in a valid position is found
     * @param n BinaryNode object representing current node to be checked
     * @param insert BinaryNode object representing node to be inserted
     */
    private void insertHelper(BinaryNode n, BinaryNode insert){
        if(insert.getValue().compareTo(n.getValue())<0){ //compare must be used, for some reason comparable class cannot be added
            if(n.getLeft()==null) n.setLeft(insert);
            else insertHelper(n.getLeft(),insert);
        }
        else{ //initial contains call in caller method means that it is impossible for n and insert to be equal to each other, else implies that n is less than insert
            if(n.getRight()==null) n.setRight(insert);
            else insertHelper(n.getRight(),insert);
        }
    }
}
