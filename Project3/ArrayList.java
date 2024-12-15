
/**
 * Provides easy access for values in 'list' form by managing an array to appear as a list
 * this class is implicly tested by the functionality of simulation classes as all methods are employed
 * 
 * @author Sean McLoughlin
 * @version 12/14/24
 */
public class ArrayList<E>
{
    Object[] arr; //array holding list values
    int size; //pointer to next value, real size = size+1
    /**
     * empty constructor for ArrayList, builds new array of defult size 10 and sets size to 0
     */
    public ArrayList(){
        arr=new Object[10];
        size=0;
    }
    /**
     * adds value to list, doubles list size if array is full after last add
     * @param val
     */
    public void add(E val){
        arr[size]=val;
        size++;
        if(size>=arr.length){ //if last addition filled array
            Object[] hold=new Object[arr.length];
            for(int i=0;i<arr.length;i++) hold[i]=arr[i]; //copy values into temp array
            arr=new Object[arr.length*2]; //double capacity
            for(int i=0;i<hold.length;i++) arr[i]=hold[i]; //copy values into new main array
        }
    }
    /**
     * remove value from specified index and return removed value
     * @param i index of value to be removed
     * @return E type value at index of removal
     */
    public E remove(int i){
        E val=(E)arr[i]; //cast is checked because only E type values can be added
        for(int n=i;n<size;n++){
            arr[n]=arr[n+1]; //move vaules down to implicitly remove designated value
        }
        size--; //remove last index from considereable section of array
        return val; //return value that used to be at specified index
    }
    /**
     * returns int value representing number of values held by list
     * @return number of elements held by list
     */
    public int size(){
        return size;
    }
    /**
     * returns value at specified location in list, throws ArrayIndexOutOfBoundsException if specified index is out of range
     * @param n index to be checked
     * @return value at specified index
     */
    public E get(int n){
        if(n<=size) return (E)arr[n]; //implicitly checked by add method
        else throw new ArrayIndexOutOfBoundsException();
    }
    /**
     * checks internal array for passed value
     * @param val value to be searched for
     * @return boolean representing whether the specified value exists in the list
     */
    public boolean contains(E val){
        for(int i=0;i<size;i++){
            if(arr[i].equals(val)) return true;
        }
        return false;
    }
    /**
     * returns arraylist values in readable format as coherent list
     * @return readably formatted ArrayList String
     */
    public String toString(){
        String rtn="[";
        for(int i=0;i<size+1;i++){
            rtn+=arr[i]+", ";
        }
        rtn=rtn.substring(0,rtn.length()-2)+"]";
        return rtn;
    }
    /**
     * sets value at specified index 
     * @param ind
     * @param val
     */
    public void set(int ind, E val){
        if(ind>size) throw new ArrayIndexOutOfBoundsException();
        arr[ind]=val;
    }
}
