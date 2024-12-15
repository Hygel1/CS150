
/**
 * Stores values in FILO order, allowing peek and pop to access last added value
 * this class is implicly tested by the functionality of simulation classes as all methods are employed
 *
 * @author Sean McLoughlin
 * @version 12/14/24
 */
public class Stack<E>
{   
    ArrayList<E> vals;
    public Stack(){
        vals=new ArrayList<E>();

    }

    /**
     * returns last added value to Stack
     * @return value last added to stack
     */
    public E peek(){
        return vals.get(vals.size()-1);
    }
    /**
     * removes and returns last value added to Stack
     * @return last value that was added to the stack
     */
    public E pop(){
        return vals.remove(vals.size()-1);
    }
    /**
     * retruns number of values held by stack
     * @return int value representing number of values held by current stack
     */
    public int size(){
        return vals.size();
    }
    /**
     * returns values of Stack in readbale FILO format
     * @reutrn readably formatted String representing Stack
     */
    public String toString(){
        String rtn="[";
        for(int i=vals.size()-1;i>-1;i--){
            rtn+=vals.get(i)+", ";
        }
        if(rtn.length()>1) rtn=rtn.substring(0,rtn.length()-2)+"]";
        return rtn;
    }
    /**
     * appends value to the end of list, applies FILO in pop/poll methods
     * @param val value to be added
     */
    public void add(E val){
        vals.add(val);
    }
    /**
     * returns boolean value representing whether there are any values currently being held by Stack
     * @return boolean representing whether Stack size==0
     */
    public boolean isEmpty(){
        return vals.size()==0;
    }
}
