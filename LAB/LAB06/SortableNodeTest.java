

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
/**
 * The test class SortableNodeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SortableNodeTest
{
    /**
     * tests first constructor by building Node object and comparing values to expected values
     */
    @Test
    @DisplayName("TM: constructor 1")
    public void testConsstructor1(){
        SortableNode<Integer> node1 = new SortableNode<>(1);
        assertTrue(node1.getVal()==1);
        assertTrue(node1.getNext()==null);
    }
    /**
     * tests second constructor by building Node object and comparing values to expected values
     */
    @Test
    @DisplayName("TM: constructor 1")
    public void testConsstructor2(){
        SortableNode<Integer> node1 = new SortableNode<>(new Node(1));
        assertTrue(node1.getVal()==1);
        assertTrue(node1.getNext()==null);
    }
    /**
     * tests third constructor by building Node objects and comparing values to expected values
     */
    @Test
    @DisplayName("TM: constructor 3")
    public void testConsstructor3(){
        ArrayList<String> list = new ArrayList<>();
        list.add("a");list.add("b");list.add("c");
        SortableNode<String> node = new SortableNode<>(list);
        assertEquals(node.toString(), "{a, b, c}");
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i=0;i<10;i++) list1.add(i);
        Node<Integer> node1 = new Node<>(list1);
    }
    /**
     * tests partition method by calling method and comparing against expected values
     */
    @Test
    @DisplayName("TM: partition")
    public void testPartition(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i);
            list.add(5-i);
        }
        SortableNode<Integer> node = new SortableNode<>(list);
        assertEquals(node.toString(), "{0, 5, 1, 4, 2, 3, 3, 2, 4, 1}");
        node.partition(3);
        assertEquals(node.toString(), "{1, 2, 3, 3, 2, 1, 4, 5, 5}");
    }
    /**
     * tests sort method by calling method and comparing against expected values
     *
    @Test
    @DisplayName("TM: Sort")
    public void testSort(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i);list.add(5-i);
        }
        SortableNode<Integer> node = new SortableNode<>(list);
        node.sort();
        assertEquals(node.toString(), "{0, 1, 1, 2, 2, 3, 3, 4, 4, 5}");
    }
    /**
     * tests unique sort method by calling method and comparing against expected values
     *
    @Test
    @DisplayName("TM: Unique Sort")
    public void testUSort(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i);list.add(5-i);
        }
        SortableNode<Integer> node = new SortableNode<>(list);
        node.uniqueSort();
        assertEquals(node.toString(), "{0, 1, 2, 3, 4, 5}");
    }
    */
}
