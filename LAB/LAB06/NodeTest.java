

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
/**
 * tests Node class for errors
 *
 * @author  Sean McLoughlin
 * @version 10/21/24
 */
public class NodeTest
{
    /**
     * tests first constructor by building Node object and comparing values to expected values
     */
    @Test
    @DisplayName("TM: constructor 1")
    public void testConsstructor1(){
        Node<Integer> node1 = new Node<Integer>(1);
        assertTrue(node1.getVal()==1);
        Node<String> node2 = new Node<String>("word");
        assertTrue(node2.getVal().equals("word"));
        assertTrue(node1.getNext()==null);
        assertTrue(node2.getNext()==null);
    }
    /**
     * tests second constructor by building Node object from arraylist and comparing values to expected values
     */
    @Test
    @DisplayName("TM: constructor 2")
    public void testConsstructor2(){
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i=0;i<3;i++) list1.add(i);
        Node<Integer> node1 = new Node(list1);
        assertTrue(node1.getVal()==0);
        node1=node1.getNext();
        assertTrue(node1.getVal()==1);
        node1=node1.getNext();
        assertTrue(node1.getVal()==2);
        assertTrue(node1.getNext()==null);
    }
    /**
     * tests addToEnd by calling method several times and comparing values against expected values
     */
    @Test
    @DisplayName("TM: addToEnd")
    public void testAddToEnd(){
        Node<String> node1 = new Node<String>("a");
        node1.addToEnd("b");
        node1.addToEnd("c");
        assertTrue(node1.getVal().equals("a"));node1=node1.getNext();
        assertTrue(node1.getVal().equals("b"));node1=node1.getNext();
        assertTrue(node1.getVal().equals("c"));node1=node1.getNext();
        assertTrue(node1==null);
    }
    /**
     * tests setNext method by calling method then testing results against expected values
     */
    @Test
    @DisplayName("TM: setNext")
    public void testSetNext(){
        Node<String> node1 = new Node<>("a");
        node1.setNext(new Node("c"));
        assertTrue(node1.getNext().getVal().equals("c"));
        node1.setNext(new Node("b"));
        assertTrue(node1.getNext().getVal().equals("b"));
    }
    /**
     * tests rotate method by calling method then testing results against expected values
     */
    @Test
    @DisplayName("TM: rotate")
    public void testRotate(){
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i=0;i<10;i++) list1.add(i);
        Node<Integer> node1 = new Node<>(list1);
        node1.rotate(2);
        assertEquals(node1.toString(), "{8, 9, 0, 1, 2, 3, 4, 5, 6, 7}");
    }
    /**
     * tests toString method by calling method then testing results against expected values
     */
    @Test
    @DisplayName("TM: toString")
    public void testToString(){
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i=0;i<10;i++) list1.add(i);
        Node<Integer> node1 = new Node<>(list1);
        assertEquals(node1.toString(),"{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}");
    }
}

