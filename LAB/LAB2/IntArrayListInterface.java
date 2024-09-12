
/**
 * Interface defining methods to be used in l
 *
 * @author Sean McLoughlin
 * @version 9/3/2024
 */
public interface IntArrayListInterface
{
    /**
     * appends given value to the end of the list, resizes if necessary
     * @param e value to be added
     */
    public void add(int e);
    /**
     * insterts given value at specified index, shifts values down if necessary and resizes array if necessary
     * @param index soecified index for placement
     * @param e value to be added at specified index
     */
    public void add(int index, int e);
    /**
     * returns value at given index
     * @param index specified index of value to be found
     * @return element found at given index
     */
    public int get(int index);
    /**
     * fully resets list, creates new array and resets counters
     */
    public void clear();
    /**
     * returns true if there are no value in list
     * @return value indicating whether the list is holding values
     */
    public boolean isEmpty();
    /**
     * removes array value at specified index and returns said value
     * @param index specified index of value to be removed
     * @return value which was removed
     */
    public int remove(int index);
    /**
     * returns number of values currently being stored in the list
     * @return size of array list
     */
    public int size();
    /**
     * returns current number of spaces available to the list without resizing
     * @return size of current array being used to hold the list
     */
    public int arraySize();
    /**
     * returns number of available (not holding values) spaces currently in the array
     * @return number of available spaces before resize
     */
    public int emptyCount();
    /**
     * prints a readable String representing all values in the list
     */
    public String toString();
    /**
     * resets all values in the array by moving the index to the front (makes no change to the actual values in the array, just moves counter to disregard current elements)
     */
    public void reset();
    /**
     * returns value specified by next counter, which starts at 0 and increments one each time this method is called
     * @return value at index specified by nextCounter
     */
    public int next();
}
