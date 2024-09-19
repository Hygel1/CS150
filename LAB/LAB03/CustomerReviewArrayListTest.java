

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class IntArrayListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerReviewArrayListTest
{
    private CustomerReviewArrayList arr = new CustomerReviewArrayList();
    private CustomerReview[] testList = new CustomerReview[54];
    public CustomerReviewArrayListTest()
    {
    
    }
    /**
     * creates a test list with collection of different strings for every parameter value, used to rebuild list during testing
     */
    public void buildList(){
        for(int i=0;i<54;i++){
            String name, rev;
            //name value increments abc, bcd, cde... (reaches special characters and capitals)
            name = Character.toString((char)(66+i))+Character.toString((char)(67+i))+Character.toString((char)(68+i));
            //rev value increments ZYX, YXW, XWV... (reached special characters and lower case)
            rev = Character.toString((char)(122-i))+Character.toString((char)(122-i))+Character.toString((char)(121-i));
            testList[i]=new CustomerReview(name,rev);
        }
    }
    /*
     * tests add method by checking that values are added and array is automatically resized
     */
    @Test
    @DisplayName("TM: add")
    public void testAdd(){
        arr.add(new CustomerReview("john", "good"));
        assertTrue(arr.get(0).getCustomerName().equals("john")&&arr.get(0).getLatestReview().equals("good"));
        for(int i=0;i<10;i++) arr.add(new CustomerReview("y","y"));
        assertTrue(arr.arraySize()==20); //check that resizing works
        arr.reset();
    }
    /**
     * tests add at index method by testing that values can be properly added in specified indices and values are properly shifted
     */
    @Test
    @DisplayName("TM: addIndex")
    public void testAddIndex(){
        arr.clear();for(int i=0;i<10;i++)arr.add(new CustomerReview("q","q"));
        arr.add(9,new CustomerReview("x","x"));
        assertTrue(arr.get(9).equals(new CustomerReview("x","x")));
        arr.add(9,new CustomerReview("a","a"));
        assertTrue(arr.get(9).equals(new CustomerReview("a","a")));
        assertTrue(arr.get(10).equals(new CustomerReview("x","x")));
    }
    /**
     * tests get method by assigning values to specific indices and testing that the proper value is returned 
     */
    @Test
    @DisplayName("TM: get")
    public void testGet(){
        arr.reset();
        arr.add(new CustomerReview("a","a"));arr.add(new CustomerReview("b","b"));arr.add(new CustomerReview("c","c"));
        assertTrue(arr.get(0).equals(new CustomerReview("a","a")));
        assertTrue(arr.get(1).equals(new CustomerReview("b","b")));
        assertTrue(arr.get(2).equals(new CustomerReview("c","c")));
    }
    /**
     * tests clear method by testing that clearing an array properly resets counters and array size
     */
    @Test
    @DisplayName("TM: clear")
    public void testClear(){
        arr.clear();
        assertTrue(arr.arraySize()==10);
        assertTrue(arr.size()==0);
    }
    /**
     * tests isEmpty method by testing an empty list, testing a list with a value added, then clearing and resetting and testing the list
     */
    @Test
    @DisplayName("TM: isEmpty")
    public void testEmpty(){
        arr.clear();
        assertTrue(arr.isEmpty());
        arr.add(new CustomerReview("a","a"));
        assertFalse(arr.isEmpty());
        arr.reset();
        assertTrue(arr.isEmpty());
    }
    /**
     * tests remove method by testing that the proper value is returned, that removing the value changes the value returned by the size function, and that values following the removed value are properly shifted
     */
    @Test
    @DisplayName("TM: remove")
    public void testRemove(){
        for(int i=0;i<5;i++) arr.add(new CustomerReview("a","a"));
        assertTrue(arr.remove(3).equals(new CustomerReview("a","a")));
        assertTrue(arr.size()==4);
        assertTrue(arr.get(3).equals(new CustomerReview("a","a")));
    }
    /**
     * tests size method by adding a specific number of values to the list and comparing the number of added values to the value returned by the size method
     */
    @Test
    @DisplayName("TM: size")
    public void testSize(){
        arr.clear();for(int i=0;i<4;i++) arr.add(new CustomerReview("a","a"));
        assertTrue(arr.size()==4);
        arr.reset();
        assertTrue(arr.size()==0);
        for(int i=0;i<100;i++) arr.add(new CustomerReview("b","b"));
        assertTrue(arr.size()==100);
    }
    /**
     * teste arraySize method by adding enough values to cross resizing thresholds, then comparing to returned arraySize value
     */
    @Test
    @DisplayName("TM: arraySize")
    public void testArraySize(){
        arr.clear();for(int i=0;i<81;i++) arr.add(new CustomerReview("a","a"));
        assertTrue(arr.arraySize()==160);
        arr.clear();
        assertTrue(arr.arraySize()==10);
        for(int i=0;i<12;i++) arr.add(new CustomerReview("a","a"));
        assertTrue(arr.arraySize()==20);
    }
    /**
     * tests emptyCount method by adding specific amounts of values to list, then comparing the difference between the number of added values and the next resizing threshold to the returned emptyCount value
     */
    @Test
    @DisplayName("TM: emptyCount")
    public void testEmptyCount(){
        arr.clear();arr.add(new CustomerReview("a","A"));arr.add(new CustomerReview("a","a"));
        assertTrue(arr.emptyCount()==8);
        arr.clear();
        assertTrue(arr.emptyCount()==10);
        arr.add(new CustomerReview("w","w"));
        assertTrue(arr.emptyCount()==9);
    }
    /**
     * tests toString method by adding 1, multiple, and 0 values to array, testing single value printing, conditional comma printing, and the ability to skip toString's for loop when necessary
     */
    @Test
    @DisplayName("TM: toString")
    public void testToString(){
        arr.clear();arr.add(new CustomerReview("a","a"));
        assertTrue(arr.toString().equals("{Name: a Review: a}"));
        arr.add(new CustomerReview("b","b"));
        assertTrue(arr.toString().equals("{Name: a Review: a, Name: b Review: b}"));
        arr.clear();
        assertTrue(arr.toString().equals("{}"));
    }
    /**
     * tests reset method by testing that both size and arraySize are reset, indicating that the list is effectively untouched
     */
    @Test
    @DisplayName("TM: reset")
    public void testReset(){
        for(int i=0;i<100;i++) arr.add(new CustomerReview("a","a")); 
        arr.reset();
        assertTrue(arr.size()==0);
        assertFalse(arr.arraySize()==10);
    }
    /**
     * tests next function by checking that the method increments to the next value upon being called, and that the counter resets when the list is reset
     */
    @Test
    @DisplayName("TM: next")
    public void testNext(){
        arr.clear();
        arr.add(new CustomerReview("a","a"));
        arr.add(new CustomerReview("b","b"));
        assertTrue(arr.next().equals(new CustomerReview("a","a")));
        assertTrue(arr.next().equals(new CustomerReview("b","b")));
        arr.reset();
        arr.add(new CustomerReview("v","v"));
        assertTrue(arr.next().equals(new CustomerReview("v","v")));
    }
}