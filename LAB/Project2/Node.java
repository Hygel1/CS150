
/**
 * Holds a subset of a full list described by DoublingList class
 *  node does not contain a test class as all methods are implicitly tested by other test classes depending on their functionality
 *
 * @author Sean McLoughlin
 * @version 10/28/24
 */
public class Node<E>{
    private int size;
    private Object[] values;//this is made as an Object array, but objects can always be successfully casted as every addition will be an instance of E
    private Node next, previous;
    private boolean dummy=false;
    /**
     * node constructor for dummy nodes, contains no value, only contains link to preceding or following node
     * @param type boolean representing whether the node is the header or footer to the list (true=footer)
     * 
     */
    public Node(boolean type, Node link){
        dummy=true;
        if(type) previous=link;
        else next=link;
    }
    /**
     * builds a new, empty node with a given capacity
     * @param capacity int value representing desired capacity of the Node object
     */
    public Node(int capacity){
        values = new Object[capacity];
        size=0;
    }
    /**
     * biulds a new node object with a specified capacity and single starting value
     * @param capacity int value representing desired node capacity
     * @param value E type object representing value to be initially stored in built node
     */
    public Node(int capacity, E value){
        values = new Object[capacity]; 
        values[0]=value;
        size=1;
        next=null;
    }
    /**
     * builds a new node object with specified capcaity and list of values, if specified capacity is not enough to hold values, will build and assign a next node to hold the rest of the values
     * @param capacity int value representing desired capcity of initial list
     * @param values E array representing list of values to be stored in list
     */
    public Node(int capacity, E[] values){
        this.values = new Object[capacity]; //capacity is accurate to this current object
        if(capacity<values.length){ //if there are more values passed than can be held by current node
            Object[] hold = new Object[values.length-capacity]; //holds overflow values
            for(int i=0;i<capacity;i++){
                if(values[i]==null) throw new NullPointerException();
                this.values[i]=values[i]; //fill values array with values
            }
            for(int i=capacity;i<values.length;i++){
                if(values[i]==null) throw new NullPointerException();
                hold[i-capacity]=values[i]; //fill array with overflow values
            }
            next = new Node(capacity*2, hold); //take overflow values and create new list
            next.setPrevious(this);
            size=capacity;
        }
        else{ //if all values can be held by current node
            next=null;
            for(int i=0;i<values.length;i++){
                if(values[i]==null) throw new NullPointerException();
                this.values[i]=values[i]; //fill array with passed values
            }
            size = values.length;
            next=null;
        }
    }
    /**
     * sets previous pointer to passed Node object
     * @param node Node type object to be set to previous pointer
     */
    public void setPrevious(Node node){
        previous=node;
    }
    /**
     * returns value stored in previous pointer
     * @return value stored in previous pointer
     */
    public Node previous(){
        return previous;
    }
    /**
     * returns size of list by recursively moving through all nodes and adding sizes
     * meant to be used as a recursive helper method for a call made in DoublingList class
     * @return total number of elements in the array
     */
    public int size(){
        if(next!=null) return size+next.size();
        return size;
    }
    /**
     * returns the individual capacity of this individual Node
     * @return total capcity of called node
     */
    public int nodeCap(){
        return values.length;
    }
    /**
     * returns true if there exists an open slot somewhere in the list
     * meant to be a recursive helper method called by DoublingList class
     * @return true if there exists space for an incoming value in the entire DoublingList
     */
    public boolean hasSpace(){
        if(next==null){
            if(values.length>size) return true;
            return false;
        }
        if(values.length>size) return true;
        else return next.hasSpace();
    }
    /**
     * recursive helper method to help find total capacity of total list
     * @return int value representing total capcity of current and all following nodes
     */
    public int findCap(){
        if(next==null) return values.length;
        else return values.length+next.findCap();
    }
    /**
     * increments size value stored inside of node
     * @param i int value representing how much to increment size value
     */
    public void incrSize(int i){
        size+=i;
    }
    /**
     * returns value stored in next pointer
     * @return Node type object representing value currently stored in next pointer
     */
    public Node next(){
        return next;
    }
    /**
     * returns current size of node (number of stored values)
     * @return number of values stored in current node
     */
    public int sizeCurr(){
        return size;
    }
    /**
     * recursive helper method to assist in determining number of values stored in entire list
     * @return int value representing number of values stored in this and all followign nodes
     */
    public int sizeFull(){
        if(next==null) return size();
        return size+next.sizeFull();
    }
    /**
     * returns value stored at specified index in current node
     * @param val index to search for value
     * @return E type value representing value stored at passed index
     */
    public E getCurr(int val){
        return (E)values[val];
    }
    /**
     * takes in desired size value and sets size field
     * @param size int value representing desired size value
     */
    public void setSize(int size){
        this.size=size;
    }
    /**
     * sets next pointer to passed value
     * @param e Node type value to be set to next pointer
     */
    public void setNext(Node e){
        next = e;
    }
    /**
     * sets passed value to passed index in current node
     * @param ind int value representing index to store passed value
     * @param e E type object to be stored in passed index value
     */
    public void setVal(int ind, E e){
        values[ind]=e;
    }
}
