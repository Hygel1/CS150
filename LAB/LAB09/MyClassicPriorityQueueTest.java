

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * The test class MyClassicPriorityQueueTest.
 *
 * @author  Sean Mcloughlin
 * @version 12/5/24
 */
public class MyClassicPriorityQueueTest
{
    /**
     * tests add method by building list and comparing values at selected indices to expected values
     */
    @Test
    @DisplayName("TM: add")
    public static void addTest(){
        MyClassicPriorityQueue<WeightedElement<Integer,Integer>> q = new MyClassicPriorityQueue();
        WeightedElement e1 = new WeightedElement(3,3); q.add(e1);
        WeightedElement e2 = new WeightedElement(5,5); q.add(e2);
        WeightedElement e3 = new WeightedElement(8,8); q.add(e3);
        WeightedElement e4 = new WeightedElement(1,1); q.add(e4);
        WeightedElement e5 = new WeightedElement(7,7); q.add(e5);
        assertTrue(q.getMap(e3)==0);
        assertTrue(q.getMap(e5)==1);
        assertTrue(q.getMap(e1)==4);
        
        MyClassicPriorityQueue<WeightedElement<String,String>> b = new MyClassicPriorityQueue();
        WeightedElement q1 = new WeightedElement("c","c"); q.add(q1);
        WeightedElement q2 = new WeightedElement("e","e"); q.add(q2);
        WeightedElement q3 = new WeightedElement("h","h"); q.add(q3);
        WeightedElement q4 = new WeightedElement("a","a"); q.add(q4);
        WeightedElement q5 = new WeightedElement("g","g"); q.add(q5);
        assertTrue(b.getMap(q3)==0);
        assertTrue(b.getMap(q5)==1);
        assertTrue(b.getMap(q1)==4);
        
    }
    /**
     * tests decreaseValue method by calling method on values and comparing list state to expected state
     */
    @Test
    @DisplayName("TM: decreaseKey")
    public static void decreaseTest(){
        MyClassicPriorityQueue<Integer> q = new MyClassicPriorityQueue();
        q.add(3);
        q.add(5);
        q.add(8);
        q.add(1);
        q.add(7);
        assertTrue(q.decreaseKey(8,2)==8);
        assertTrue(q.getMap(2)==4);
        assertTrue(q.decreaseKey(2,8)==null);
        assertTrue(q.decreaseKey(5,-1)==5);
        assertTrue(q.getMap(-1)==2);
    }  
    /**
     * tests poll method by building queue and comparing returned values to expected values
     */
    @Test
    @DisplayName("TM: poll")
    public static void pollTest(){
        MyClassicPriorityQueue<Integer> q = new MyClassicPriorityQueue();
        q.add(3);
        q.add(5);
        q.add(8);
        q.add(1);
        q.add(7);
        assertTrue(q.poll()==8);
        assertTrue(q.get(3)==1);
        assertTrue(q.getMap(1)==3);
        assertTrue(q.get(2)==3);
        assertTrue(q.getMap(3)==2);
        assertTrue(q.poll()==7);
    }
}
