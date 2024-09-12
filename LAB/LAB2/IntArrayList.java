
/**
 * Uses an array to maintain a list of integer numbers which automatically resizes according to need
 *
 * @author Sean McLoughlin
 * @version 9/5/2024
 */
public class IntArrayList implements IntArrayListInterface
{
    private int[] theList;
    private int ind;
    private int nextCounter;
    /**
     * builds list with default initial size 10
     */
    public IntArrayList(){
        theList = new int[10];
        ind=0;
        nextCounter=0;
    }
    /**
     * helped method to resize array when size limit has been reached
     */
    private void resize(){
        int[] hold=new int[theList.length*2];
            for(int i=0;i<theList.length;i++){
                hold[i]=theList[i];
            }
            theList=hold;
    }
    /**
     * appends given value to the end of the list, resizes if necessary
     * @param e value to be added
     */
    public void add(int e){
        theList[ind]=e;
        ind++;
        if(ind==theList.length){
            resize();
        }
    }
    /**
     * insterts given value at specified index, shifts values down if necessary and resizes array if necessary
     * @param index soecified index for placement
     * @param e value to be added at specified index
     */
    public void add(int index, int e){
        if(ind==theList.length-1) resize();
        for(int i=ind;i>index-1;i--){
            theList[i+1]=theList[i];
        }
        theList[index]=e;
    }
    /**
     * returns value at given index
     * @param index specified index of value to be found
     * @return element found at given index
     */
    public int get(int index){
        return theList[index];
    }
    /**
     * fully resets list, creates new array and resets counters
     */
    public void clear(){
        theList = new int[10];
        ind=0;
        nextCounter=0;
    }
    /**
     * returns true if there are no value in list
     * @return value indicating whether the list is holding values
     */
    public boolean isEmpty(){
        return ind==0;
    }
    /**
     * removes array value at specified index and returns said value
     * @param index specified index of value to be removed
     * @return value which was removed
     */
    public int remove(int index){
        int hold=theList[index];
        for(int i=index+1;i<ind;i++){
            theList[i-1]=theList[i];
        }
        ind--;
        return index;
    }
    /**
     * returns number of values currently being stored in the list
     * @return size of array list
     */
    public int size(){
        return ind;
    }
    /**
     * returns current number of spaces available to the list without resizing
     * @return size of current array being used to hold the list
     */
    public int arraySize(){
        return theList.length;
    }
    /**
     * returns number of available (not holding values) spaces currently in the array
     * @return number of available spaces before resize
     */
    public int emptyCount(){
        return theList.length-ind;
    }
    /**
     * prints a readable String representing all values in the list
     */
    public String toString(){
        String fullOut="{";
        for(int i=0;i<ind;i++){
            fullOut+=theList[i];
            if(i<ind-1) fullOut+=", ";
        }
        return fullOut+="}";
    }
    /**
     * resets all values in the array by moving the index to the front (makes no change to the actual values in the array, just moves counter to disregard current elements)
     */
    public void reset(){
        ind=0;
        nextCounter=0;
    }
    /**
     * returns value specified by next counter, which starts at 0 and increments one each time this method is called
     * @return value at index specified by nextCounter
     */
    public int next(){
        int rtn=theList[nextCounter];
        nextCounter++;
        return rtn;
    }
}
