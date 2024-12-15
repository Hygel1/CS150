
/**
 * Individual nodes to be contained as values in SnobbyLinkedList
 *
 * @author Sean McLoughlin
 * @version 10/24/2024
 */
public class Node<E>
{
    private E val;
    private Node next;
    public Node(E val){
        this.val=val;
    }
    /**
     * sets Next value
     * @param node Node type object representing Node to be set to Next value
     */
    public void setNext(Node node){
        next=node;
    }
    /**
     * returns true if there exists a Next node to current Node
     * @return boolean value representing whether there exists a next Node for the current Node
     */
    public boolean hasNext(){
        return next!=null;
    }
    /**
     * returns value contained in current node
     * @return E type object representing value contained in current Node
     */
    public E getVal(){
        return val;
    }
    /**
     * adds node to back of list and returns value contained in the added node. If the value contained in the passed node already exists in the list, returns null and doesn't add to list
     * @param n Node value to be appended to list
     * @return E type object contained in appended Node (returns null if value is already contained in list)
     */
    public E addToBack(Node n){
        if(val==n.getVal()) return null;
        if(next==null){
            next=n;
            return (E)n.getVal();
        }
        return (E)next.addToBack(n);
    }
    /**
     * assists in recursively creating String representation of full list
     * starting from the initial call, this will output a comma separated version of the String representation of every value
     * @return String representation of current Node's part in the full output of the list's String representation
     */
    public String toString(){
        if(next==null) return val+"";
        return val+", "+next.toString();
    }
}
