
/**
 * Node to contain values for AVLTree class
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AVLNode<E extends Comparable> extends BinaryNode
{
    AVLNode parent;
    int height;
    /**
     * basic constructor designed to define a root node for AVLTree. Parent value is null and height is initially set to 0
     * @param val E type object to be designated as node value
     */
    public AVLNode(E val){
        super(val);
        height=0;
        super.left=left;
        super.right=right;
    }
    /**
     * builds new AVLNode with specified value and parent Node value
     * @param val E type object to be designated as node value
     * @param parent AVLNode type object to be designated as this node's parent node
     */
    public AVLNode(E val, AVLNode parent){
        super(val);
        this.parent=parent;
    }
    /**
     * returns the current value of the parent node to this node
     * @return AVLNode type object representing the parent node to the current node
     */
    public AVLNode getParent(){
        return parent;
    }
    /**
     * sets parent node to a passed object
     * @param AVLNode type object representing value ot be set as parent to current node
     */
    public void setParent(AVLNode n){
        parent=n;
    }
    /**
     * recursively finds height a a given node by traversing through all nodes and tallying, then choosing the largest value
     * the minimum height for a node in a tree is 0. null nodes will return -1 as they have no valid height value
     * @return int value representing the height of a given node
     *
    public int findHeight(){
        int maxLeft;
        int maxRight;
        if(left!=null) maxLeft=left.findHeight();
        else maxLeft=-1;
        if(right!=null) maxRight=right.findHeight();
        else maxRight=-1;
        return Math.max(maxLeft,maxRight)+1;
    }
    */
}
