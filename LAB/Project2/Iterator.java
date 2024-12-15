
/**
 * Write a description of class ListIterator here.
 *
 * @author (your name)
 * @version 10/28/24
 */
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class Iterator<E> implements ListIterator<E>
{
    DoublingList curr; //contains object value of list at subject of Iterator object
    int pointer; //current index in curr list
    Node currNode; //current node being examined in DoublingList curr
    /**
     * builds enw listIterator object to act on passed DoublingList
     * @param begin DoublingList object to be traversed by iterator
     */
    public Iterator(DoublingList begin){
        curr=begin;
        pointer=-1;
        currNode=begin.firstNode();
    }
    /**
     * sets pointer value to passed desired value
     * @param index int value for pointer to be set to
     */
    public void setPointer(int index){
        pointer=index;
    }
    /**
     * adds an object at the end of the current list (index pointer+1)
     * @param e E type object representing object to be added to the end of the list
     */
    public void add(E e){
        curr.add(pointer+1,e);
    }
    /**
     * returns true if there exist a value in the spot immediately after the current list pointer
     * @return boolean object representing whether there exist a value immediately following the current list pointer
     */
    public boolean hasNext(){
        return pointer<curr.size()-1;
    }
    /**
     * returns true if there exist a value in the spot immediately previous to the current list pointer
     * @return boolean object representing whether there exist a value immediately previous to the current pointed to object
     */
    public boolean hasPrevious(){
        return pointer>0;
    }
    /**
     * returns next value in list
     * @return E type object representing the value stored in the next spot in the list
     */
    public E next(){
        if(!(pointer<curr.size()-1)) throw new NoSuchElementException(); //if pointer is pointing to last item in list before increment
        else{
            pointer++;
            return (E)curr.get(pointer); //if pointer doesn't correspond to last in a list, increment pointer and return last pointed to in list
        }
    }
    /**
     * returns index of value that would be returned by next() call
     * @return int value representing index of object that would be returned by the next next() method call, will return list size if currently on last element of list
     */
    public int nextIndex(){
        return pointer+1;
    }
    /**
     * returns object with index immediately previous to what is currently beign pointed to
     * @return E type object representing object immediately before that represented by current pointer
     */
    public E previous(){
        if(pointer==0) throw new NoSuchElementException();
        //else
        pointer--;
        return (E)curr.get(pointer);       
    }
    /**
     * returns the index of the object immediately previous to the one being currently pointed to
     * @return int value representing index of object immediately previous to current pointer
     */
    public int previousIndex(){
        return pointer-1;
    }
    /**
     * removes value last returned by Iterator, this would be the value stored in index pointer
     */
    public void remove(){
        curr.remove(pointer);
    }
    /**
     * sets last called value to passed value using pointer
     * @param e E type value representing object to replace last returned item
     */
    public void set(E e){
        curr.set(pointer, e);
    }
}
