
/**
 * BinaryTree abstract class representing basic functionality of a BinaryTree without implementing methods (insert) which are specific to a type of BinaryTree
 *
 * @author Sean McLoughlin
 * @version 11/12/24
 */
public abstract class BinaryTree<E> implements Tree<E>{
    BinaryNode root;
    /**
     * constructor to build binary tree with specified root node;
     */
    public BinaryTree(BinaryNode n){
        root=n;
    }
    /**
     * determines whether a value exists in the tree already
     * @param val E type object to be searched for
     * @return boolean value representing whether the value exists in the tree
     */
    public boolean contains(E val){
        return containsHelper(root,val);
    }
    /**
     * private helper method to help determine whether a given value is in the tree
     * @param n BinaryNode object representing current position in the tree
     * @param val E type object representign object to look for
     * @return true if similar object is found, false otherwise
     */
    private boolean containsHelper(BinaryNode n, E val){
        return n.getValue()!=null&&(n.getValue().equals(val)||containsHelper(n.getLeft(),val)||containsHelper(n.getRight(), val));
    }
    /**
     * returns String representation of tree in preorder format
     * @return preorder format String representation of tree
     */
    public String preOrderString(){
        String s = preHelper(root);
        return s.substring(0,s.length()-2);
    }
    /**
     * private helper method to help develop preorder String representation of tree
     * @param n BianryNode object representing object at current place in tree
     * @return chunk of tree's String representation
     */
    private String preHelper(BinaryNode n){
        String rtn=n.getValue().toString()+", ";
        if(n.getLeft()!=null) rtn+=preHelper(n.getLeft());
        if(n.getRight()!=null) rtn+=preHelper(n.getRight());
        return rtn;
    }
    /**
     * returns String representation of tree in postrder format
     * @return postorder format String representation of tree
     */
    public String postOrderString(){
        String s = postHelper(root);
        return s.substring(0,s.length()-2);
    }
    /**
     * private helper method to help develop postorder String representation of tree
     * @param n BianryNode object representing object at current place in tree
     * @return chunk of tree's String representation
     */
    private String postHelper(BinaryNode n){
        String rtn="";
        if(n.getLeft()!=null) rtn+=postHelper(n.getLeft())+", ";
        if(n.getRight()!=null) rtn+=postHelper(n.getRight())+", ";
        rtn+=n.getValue().toString();
        return rtn;
    }
    /**
     * returns String representation of tree in inorder format
     * @return inorder format String representation of tree
     */
    public String inOrderString(){
        String s = inHelper(root);
        return s.substring(0,s.length()-2);
    }
    /**
     * private helper method to help develop inorder String representation of tree
     * @param n BianryNode object representing object at current place in tree
     * @return chunk of tree's String representation
     */
    private String inHelper(BinaryNode n){
        String rtn="";
        if(n.getLeft()!=null) rtn+=inHelper(n.getLeft());
        rtn+=n.getValue().toString()+", ";
        if(n.getRight()!=null) rtn+=inHelper(n.getRight());
        return rtn;
    }
    /**
     * empties tree by getting rid of root node reference
     */
    public void empty(){
        root=null;
    }
    /**
     * determines whether the tree is empty by checking the root node
     * @return true if the tree is empty, false if it contains values
     */
    public boolean isEmpty(){
        return root==null;
    }
    
}
