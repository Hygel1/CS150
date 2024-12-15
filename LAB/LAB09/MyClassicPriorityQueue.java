
/**
 * Holds values in priority queue format, basing priority on passed generic type's internal Comparator
 * Holds values' indices in the queue in a hashmap for quicker index hunting
 *
 * @author Sean McLoughlin
 * @version 12/5/24
 */
import java.util.ArrayList;
import java.util.HashMap;
public class MyClassicPriorityQueue<T extends Comparable> extends MyPriorityQueue<T>
{
    private HashMap<T, Integer> map; //key, value
    /**
     * builds new ClassicPriorityQueue with empty Hashmap and no held values
     */
    public MyClassicPriorityQueue(){
        map=new HashMap();
    }
    /**
     * replaces target with a passed smaller node, then percolates new value down the queue until it is found to be in the right spot
     * if the replacement value is smaller than the target value, no swap occurs and null is returned
     * @param target T type value representing vaule to be replaced
     * @param smallerValue T type value representing replacement value
     * @return T type value representing replaced value, null if swap was unsuccessful
     */
    public T decreaseKey(T target, T smallerValue){
        if(target.compareTo(smallerValue)<=0) return null;
        int ind = map.get(target); //index value in list of value being replaced
        map.put(smallerValue, ind); //set value of new value to contain its index in the queue
        set(ind, smallerValue); //list now contains correct value at index
        percDown(ind);
        return target;
    }
    /**
     * returns and removes value found at top of queue, then updates array to maintain format
     * @return T type value representing removed value previously at from of queue
     */
    public T poll(){
        T hold=get(0);
        set(0,remove(size()-1));
        map.replace(get(0),0);
        int spotInd=0;
        int leftChild=1;
        while(leftChild<size()){ //while there exists a child to the node being examined
            if(get(leftChild).compareTo(get(spotInd))>0){ //if child has greater value than node of interest
                swap(spotInd,leftChild);
                swapMap(get(spotInd),get(leftChild));
                spotInd=leftChild;
                leftChild=leftChild*2+1;
            }
            else if(leftChild+1<size()&&get(leftChild).compareTo(get(spotInd))>0){ //if there exists a right child and child has greater value than node of interest
                swap(spotInd,leftChild+1);
                swapMap(get(spotInd),get(leftChild+1));
                spotInd=leftChild+1;
                leftChild=leftChild*2+1;
            }
            else break; //if both children are lesser, node is placed correctly and loop can be terminated
        }
        return hold;
    }
    /**
     * updated version of add() to reflect needs of HashMap. Adds value to bottom of queue, then percolates up until value is at correct spot
     * value adds are only successful if the value being added does not already exist in the queue
     * @param t T type value representing value to be added
     * @return boolean type value representing whether the add was successsful
     */
    public boolean add(T t){
        for(int i=0;i<size();i++) //check for duplicates
            if(get(i).compareTo(t)==0)
                return false;
        int place=0;
        boolean cont=true;
        listAdd(t); //add to next spot
        map.put(t, size()-1); //set initial map value
        int parentInd=(size()-2)/2;
        int addedInd=size()-1;
        if(size()==1) return true; //if first, end
        while(parentInd>=0){ //continue until there exists no parent (case of final position is handled by break
            if(get(parentInd).compareTo(t)<0){ //if aded element is greater than its parent, swap up
                swap(parentInd,addedInd);
                swapMap(get(parentInd),get(addedInd)); //update hashmap values to reflect correct indices
                addedInd=parentInd; //added value is now in the place of its parent
                parentInd=(addedInd-1)/2; //find index of new parent
            }
            else break; //if no swap necessary, value is in correct position and loop can be terminated
        }
        return true;
    }
    /**
     * helper method to percolate values down the queue in case of misalignment when swapping
     * @param beginningInd int type value representing index in array to be initially considered
     */
    public void percDown(int beginningInd){
        int spotInd=beginningInd;
        int leftChild=beginningInd*2+1;
        while(leftChild<size()){ //percolate down if necessary
            if(get(leftChild).compareTo(get(spotInd))>0){ //if child has greater value than node of interest
                swap(spotInd,leftChild);
                swapMap(get(spotInd), get(leftChild));
                spotInd=leftChild;
                leftChild=leftChild*2+1;
            }
            else if(leftChild+1<size()&&get(leftChild+1).compareTo(get(spotInd))>0){ //if there exists a right child and child has greater value than node of interest
                swap(spotInd,leftChild+1);
                swapMap(get(spotInd), get(leftChild+1));
                spotInd=leftChild+1;
                leftChild=leftChild*2+1;
            }
            else break; //if both children are lesser, node is placed correctly and loop can be terminated
        }
    }
    /**
     * swaps two values held index values in hash map. This is helpful for maintaining map integrity when percolating or swapping values
     * @param v1 T type value representing first value to be swapped
     * @param v2 T type value representign second value to be swapped
     */
    public void swapMap(T v1, T v2){
        int hold=map.get(v1);
        map.replace(v1,map.get(v2)); //both values now hold initial value of v2
        map.replace(v2, hold); //held indices have been swapped
    }
    /**
     * returns the hash map value of a given key
     * @return has map value at key
     */
    public int getMap(T val){
        return map.get(val);
    }
}
