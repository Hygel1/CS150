
/**
 * Stores objects in order of internal comparator in tree-style format
 *
 * @author Sean McLoughlin
 * @version 12/5/24
 */
import java.util.ArrayList;
public class MyPriorityQueue<T extends Comparable>{
    private ArrayList<T> list;
    /**
     * builds new empty PriorityQueue object with no values
     */
    public MyPriorityQueue(){
        list=new ArrayList();
    }
    /**
     * swaps two values in item list
     * @param i1 index of first item to be swapped
     * @param i2 index of second item to be swapped
     */
    public void swap(int i1, int i2){
        T hold=list.get(i1);
        list.set(i1,list.get(i2));
        list.set(i2,hold);
    }
    /**
     * adds value to queue, then percolates accordingly to place value in the correct spot
     * if value already exists in queue, the value is not added and null is returned
     * @param t T type value to be added to queue
     * @return boolean value representing whether the addition was successful
     */
    public boolean add(T t){
        for(int i=0;i<list.size();i++) //check for duplicates
            if(list.get(i).compareTo(t)==0)
                return false;
        int place=0;
        boolean cont=true;
        list.add(t); //add to next spot
        int parentInd=(list.size()-2)/2;
        int addedInd=list.size()-1;
        if(list.size()==1) return true; //if this is the first addition, end
        while(parentInd>=0){ //continue until there exists no parent (case of final position is handled by break
            if(list.get(parentInd).compareTo(t)<0){ //if aded element is greater than its parent, swap up
                swap(parentInd,addedInd);
                addedInd=parentInd; //added value is now in the place of its parent
                parentInd=(addedInd-1)/2; //find index of new parent
            }
            else break; //if no swap necessaryvalue is in correct position and loop can be terminated
        }
        return true;
    }
    /**
     * returns and removes item at top of queue, then adjusts list accordingly to maintain integrity of ordering
     * @return T type value representing top of queue previous to call
     */
    public T poll(){
        T hold=list.get(0);
        list.set(0,list.remove(list.size()-1));
        int spotInd=0;
        int leftChild=1;
        while(leftChild<list.size()){ //while there exists a child to the node being examined
            if(list.get(leftChild).compareTo(list.get(spotInd))>0){ //if child has greater value than node of interest
                swap(spotInd,leftChild);
                spotInd=leftChild;
                leftChild=leftChild*2+1;
            }
            else if(leftChild+1<list.size()&&list.get(leftChild+1).compareTo(list.get(spotInd))>0){ //if there exists a right child and child has greater value than node of interest
                swap(spotInd,leftChild+1);
                spotInd=leftChild+1;
                leftChild=leftChild*2+1;
            }
            else break; //if both children are lesser, node is placed correctly and loop can be terminated
        }
        return hold;
    }
    /**
     * returns value at top of queue without removing it
     * @return T type value representing value currently at top of queue
     */
    public T peek(){
        return list.get(0);
    }
    /**
     * accessor method for item list, returns value at specified index
     * @param i int type value representing index to be searched
     */
    public T get(int i){
        return list.get(i);
    }
    /**
     * accessor method for item list, sets value at specified index to specified value
     * @param i int type value representing index to be set
     * @param val T type value representign value to be moved in
     */
    public void set(int i, T val){
        list.set(i, val);
    }
    /**
     * accessor method for item list, returns integer representation of list size
     * @return in type value representing list size
     */
    public int size(){
        return list.size();
    }
    /**
     * parses list into readable String, helpful for testing
     * @return readable String version of list object
     */
    public String toString(){
        String rtn="";
        for(int i=0;i<list.size();i++){
            rtn+=list.get(i)+", ";
        }
        return rtn.substring(0,rtn.length()-2);
    }
    /**
     * accessor method for item list, adds object to end of list
     * @param val T type object representing value to be added
     */
    public void listAdd(T val){
        list.add(val);
    }
    /**
     * accessor method for item list, removes object and returns removed value
     * @param i int type value representing index of value to be removed
     * @return removed value
     */
    public T remove(int i){
        return remove(i);
    }
}