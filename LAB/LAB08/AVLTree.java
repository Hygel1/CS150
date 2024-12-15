/**
 * Write a description of class AVLTree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
//import java.util.Comparable;
public class AVLTree<E extends Comparable> extends BinarySearchTree<E>
{
    AVLNode root; //root value is still stored by parent classes so parent methods are able to work, but methods in this class might require methods defined in AVLNode class
    /**
     * AVLTree constructor defining a root node
     * @param e AVLNode type object defining the root node to be defined
     */
    public AVLTree(AVLNode e){
        super(e);
        this.root=e;
        super.root=this.root;
    }
    /**
     * inserts node into tree and performs a balancing operation to keep tree sides within 1 node of each other in height, protects against duplicates by rejecting values which are already in the tree
     * @param e E type value representing value to be added to the tree
     * @return boolean type value representing whether the value was successfully added to the tree
     */
    public boolean insert(E e){
        if(root==null){
            root=new AVLNode(e);
            return true;
        }
        if(contains(e)) return false;
        insertHelper(root, e);
        return balance(root);
    }
    /**
     * private helper method to aid insertion into the binary search tree, moves through tree until an empty child in a valid position is found
     * @param n BinaryNode object representing current node to be checked
     * @param insert E type object representing value to be inserted
     */
    private void insertHelper(BinaryNode n, E insert){
        if(insert.compareTo(n.getValue())<0){ //compare must be used, for some reason comparable class cannot be added
            if(n.getLeft()==null) n.setLeft(new AVLNode(insert, (AVLNode)n));
            else insertHelper(n.getLeft(),insert);
        }
        else{ //initial contains call in caller method means that it is impossible for n and insert to be equal to each other, else implies that n is less than insert
            if(n.getRight()==null) n.setRight(new AVLNode(insert, (AVLNode)n));
            else insertHelper(n.getRight(),insert);
        }
    }
        /**
     * recursively finds height a a given node by traversing through all nodes and tallying, then choosing the largest value
     * the minimum height for a node in a tree is 0. null nodes will return -1 as they have no valid height value
     * @param curr BinaryNode type object representing the node currently being examined
     * @return int value representing the height of a given node
     */
    public int findHeight(BinaryNode curr){
        if(curr==null) return -1;
        int maxLeft;
        int maxRight;
        if(curr.getLeft()!=null) maxLeft=findHeight(curr.getLeft());
        else maxLeft=-1;
        if(curr.getRight()!=null) maxRight=findHeight(curr.getRight());
        else maxRight=-1;
        return Math.max(maxLeft,maxRight)+1;
    }
    /**
     * recursively finds the balance factor of a given node, (height depending on right child - height depending on left child)
     * @param n AVLNode type object representing the node to be checked for balance factor
     * @return int type value representing the determined balance factor
     */
    public int getBalanceFactor(AVLNode n){
        return findHeight(n.getRight())-findHeight(n.getLeft());
    }
    /**
     * recursively balances tree from AVLNode n down using left and right rotations
     * @param n AVLNode type object representing the pivot node for a specific balance operation
     * @return boolean type value representing whether the balance was successful (should always return true)
     */
    private boolean balance(AVLNode n){
        if(n==null) return true;
        int bal = getBalanceFactor(n);
        while(true){ //this effectively just loops until balanced, the else return statement stops the loop when balanced
            if(bal<-1){ //required shift right
                if(n.getParent()!=null){ //if not pivoting on root node
                    n.getParent().setLeft((AVLNode)n.getLeft()); //replace pivot node in eyes of parent
                    ((AVLNode)(n.getLeft())).setParent(n.getParent()); //update parent node of child being pulled up
                }
                else{ //if pivoting on root node
                    ((AVLNode)(n.getLeft())).setParent(null); //update parent of new root node to null
                    root=(AVLNode)n.getLeft(); //set root node
                }
                    n.setParent(((AVLNode)n.getLeft())); //set parent of pivot node to its replacement, all parents should now be updated
                    BinaryNode hold=n.getLeft().getRight(); //store child of node being pulled up which will be replaced by pivot node
                    n.getLeft().setRight(n); //replace node stored in hold with pivot note
                    n.setLeft(hold); //pivot value will always have no left child at this point and hold value will always be lesser or null, set hold value to pivot's left child
                }
            else if(bal>1){ //required left shift
                if(n.getParent()!=null){ //if not pivoting on root node
                    n.getParent().setRight((AVLNode)n.getRight()); //replace pivot node in eyes of parent
                    ((AVLNode)(n.getRight())).setParent(n.getParent()); //update parent node of child being pulled up
                }
                else{ //if pivoting on root node
                    ((AVLNode)(n.getRight())).setParent(null); //update parent of new root node to null
                    root=(AVLNode)n.getRight(); //set root node
                }
                    n.setParent(((AVLNode)n.getRight())); //set parent of pivot node to its replacement, all parents should now be updated
                    BinaryNode hold=n.getRight().getLeft(); //store child of node being pulled up which will be replaced by pivot node
                    n.getRight().setLeft(n); //replace node stored in hold with pivot note
                    n.setRight(hold); //pivot value will always have no left child at this point and hold value will always be lesser or null, set hold value to pivot's left child
            }
            else return balance((AVLNode)(n.getLeft()))&&balance((AVLNode)(n.getRight())); //once balanced, balance children
            bal=getBalanceFactor(n);
        }
    }
    /**
     * public accessor method for AVLTree root node
     * @return AVLNode object representing root node of current tree
     */
    public AVLNode getRoot(){
        return root;
    }
}
