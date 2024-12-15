
/**
 * Holds a value and next node to represent a single index in a linked list
 *
 * @author Sean McLoughlin
 * @version 10/22/24
 */
import java.util.ArrayList;
public class Node<E>{
    public E val;
    public Node<E> next;
    /**
     * builds new Node object with empty next value and specified value
     * @param d E type object containing the value to be stored by built node
     */
    public Node(E d){
        next = null;
        val = d;
    }
    /**
     * builds a String of nodes containing the values found in arr parameter. Uses addToEnd method to append each value
     * @param arr ArrayList object containing values to be added to the list
     */
    public Node(ArrayList<E> arr){
        if(arr.size()>0) val = arr.get(0);
        for(int i=1;i<arr.size();i++) addToEnd(arr.get(i));
    }
    /**
     * Recursive addition structure. If current node is last (next==null), add as next, otherwise pass to next node down the line
     * @param d E type object containing the value to be appended to the beginning of the list
     */
    public void addToEnd(E d){
        if(next==null) next = new Node(d);
        else next.addToEnd(d);
    }
    /**
     * helper method:
     * sets the next value of a node to be the passed node value (useful setter method)
     * @param node Node object to be set as next value
     */
    public void setNext(Node<E> node){
        next = node;
    }
    /**
     * 'rotates' list by pushing values down one index assuming circular list
     * @param k int value representing number of times to 'rotate' list
     */
    public void rotate(int k){
        if(k<0) throw new IllegalArgumentException("Rotation number must be non-negative");
        for(int i=0;i<k;i++){
            E hold = null; //holder value, null because null is a universal value
            val=rotateHelper(null); //this will initially set val to be null in rotateHelper, but this is avoided by setting it equal to the returned final value
        }
    }
    /**
     * recursively pushes through list, pushing values down the list one time
     * this method sets the first value in list to be whatever the initial call parameter is, but sicne it is called from this object, this value can be given any value as long as the returned value is handled correctly
     * @param val E object representing value to be set in current node
     * @return value at the end of the list
     */
    private E rotateHelper(E passVal){
        E holdVal=val;
        val=passVal;
        if(next!=null) return next.rotateHelper(holdVal);
        else return holdVal;
    }
    /**
     * getter method to return value carried by node
     * @return value carried by current node
     */
    public E getVal(){
        return val;
    }    
    public Node getNext(){
        return next;
    }
    /**
     * recursively returns readable String representation of full list
     * @return String representation of list
     */
    public String toString(){
        return "{"+recStringHelper();
    }
    /**
     * recursively builds readable String to represent list, necessary to prevent opening brace from being repeatde given that there is no inherent check whether a node begins a list
     * @return representation of current node follwed by "}" if last node or ", " if there exists a following node
     */
    public String recStringHelper(){
        if(next==null) return val+"}";
        return val+", "+next.recStringHelper();
    }
    
    
}
