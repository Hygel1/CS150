
/**
 * Node value to be used in BinaryTree, conatins a value and two potential children
 * tested implicitly through tree functions as all tree methods rely on the working state of node methods
 *
 * @author Sean McLoughlin
 * @version 11/12/24
 */
public class BinaryNode<E extends Comparable>
{
    E value;
    BinaryNode left, right;
    /**
     * constructor for node with child/children, if only one child node is to be defined, param can accept null value in place of empty child slot
     * @param value E type value representing value to be stored by current node
     * @param left BinaryNode type object to be assigned as left child
     * @param right BianryNode type object to be assigned as right child
     */
    public BinaryNode(E value, BinaryNode left, BinaryNode right){
        this.value=value;
        this.left=left;
        this.right=right;
    }
    /**
     * constructor for childless BinaryNode
     * @param value E type objetc representing value to be stored in built node
     */
    public BinaryNode(E value){
        this.value=value;
    }
    /**
     * returns node stored in left-node slot
     * @return BinaryNode value representing left child of current node
     */
    public BinaryNode getLeft(){
         return left;   
    }
    /**
     * returns Node stored in right-node slot
     * @reutrn BinaryNode value representing right child of curent node
     */
    public BinaryNode getRight(){
        return right;
    }
    /**
     * setter method for left child node
     * @param n BinaryNode type object to be set to left child node
     */
    public void setLeft(BinaryNode n){
        left=n;
    }
    /**
     * setter method for right child node
     * @param n BinaryNode type object to be set to right child node
     */
    public void setRight(BinaryNode n){
        right=n;
    }
    /**
     * returns value held by current node
     * @reurn E type value contained by current node
     */
    public E getValue(){
        return value;
    }
    /**
     * recursive helper method to search through list to find a given value
     * @param val E type object representing object to be searched for
     */
    public boolean find(E val){
        if(val.equals(value)) return true;
        if(left!=null) if(left.find(val)) return true;
        if(right!=null) if(right.find(val)) return true;
        return false;
    }    
}
