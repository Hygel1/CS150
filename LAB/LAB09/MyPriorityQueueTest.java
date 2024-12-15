

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * The test class MyPriorityQueueTest.
 *
 * @author  Sean McLoughlin
 * @version 12/5/24
 */
public class MyPriorityQueueTest
{
    /**
     * tests add method by building list and comparing values at selected indices to expected values
     */
    @Test
    @DisplayName("TM: add")
    public static void addTest(){
        MyPriorityQueue<WeightedElement<Integer,Integer>> q = new MyPriorityQueue();
        q.add(new WeightedElement(3,3));
        q.add(new WeightedElement(5,5));
        q.add(new WeightedElement(8,8));
        q.add(new WeightedElement(1,1));
        q.add(new WeightedElement(7,7));
        assertTrue(q.get(0).compareTo(new WeightedElement(8,8))==0);
        assertTrue(q.get(2).compareTo(new WeightedElement(5,5))==0);
        assertTrue(q.get(4).compareTo(new WeightedElement(3,3))==0);
        
        MyPriorityQueue<WeightedElement<String,String>> b = new MyPriorityQueue();
        b.add(new WeightedElement("c","c"));
        b.add(new WeightedElement("e","e"));
        b.add(new WeightedElement("h","h"));
        b.add(new WeightedElement("a","a"));
        b.add(new WeightedElement("g","g"));
        assertTrue(b.get(0).toString().equals("h"));
        assertTrue(b.get(1).toString().equals("g"));
        assertTrue(b.get(3).toString().equals("a"));
    }
    /**
     * tests poll method by building queue adn comparing returned values to expected values
     */
    @Test
    @DisplayName("TM: poll")
    public static void pollTest(){
        MyPriorityQueue<Integer> q = new MyPriorityQueue();
        q.add(3);
        q.add(5);
        q.add(8);
        q.add(1);
        q.add(7);
        assertTrue(q.poll()==8);
        assertTrue(q.get(3)==1);
        assertTrue(q.get(2)==3);
        assertTrue(q.poll()==7);
    }
    /**
     * tests peek method by building queue and comparing returned values to expected values
     */
    @Test
    @DisplayName("TM: peek")
    public static void peekTest(){
        MyPriorityQueue<Integer> q = new MyPriorityQueue();
        q.add(3);
        q.add(5);
        q.add(8);
        q.add(1);
        q.add(7);
        assertTrue(q.peek()==8);
        q.remove(0);
        assertTrue(q.peek()==7);
    }
}
