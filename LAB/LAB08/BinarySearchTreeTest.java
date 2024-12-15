

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * Tests BinarySearchTree class by comparing returned values to expected values
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BinarySearchTreeTest
{
    /**
     * performs unit test for contains by testing method return values against known values
     */
    @Test
    @DisplayName("TM: contains")
    public static void containsTest(){
        BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(3));
        t.insert(2);t.insert(4);t.insert(9);t.insert(4);t.insert(1);t.insert(-10);
        assertTrue(t.contains(4));
        assertTrue(t.contains(-10));
        assertFalse(t.contains(-1));
    }
    /**
     * performs unit test for preOrderString by testing method return values against expected String value
     */
    @Test
    @DisplayName("TM: preOrder")
    public static void preOrderTest(){
        BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(5));
        t.insert(2);t.insert(4);t.insert(9);t.insert(4);t.insert(1);t.insert(-10);
        assertEquals(t.preOrderString(), "5, 2, 1, -10, 4, 9");
        t.insert(7);
        assertEquals(t.preOrderString(), "5, 2, 1, -10, 4, 9, 7");
    }
    /**
     * performs unit test for postOrderString by testing method return values against expected String values
     */
    @Test
    @DisplayName("TM: preOrder")
    public static void postOrderTest(){
        BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(5));
        t.insert(2);t.insert(4);t.insert(9);t.insert(4);t.insert(1);t.insert(-10);
        assertEquals(t.postOrderString(), "-10, 1, 4, 2, 9");
        t.insert(7);
        assertEquals(t.postOrderString(), "-10, 1, 4, 2, 7, 9");
    }
    /**
     * performs unit test for inOrderString by testing method return values against expected String values
     */
    @Test
    @DisplayName("TM: preOrder")
    public static void inOrderTest(){
        BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(5));
        t.insert(2);t.insert(4);t.insert(9);t.insert(4);t.insert(1);t.insert(-10);
        assertEquals(t.inOrderString(), "-10, 1, 2, 4, 5, 9");
        t.insert(7);
        assertEquals(t.inOrderString(), "-10, 1, 2, 4, 5, 7, 9");
    }
    /**
     * performs unit test for isEmpty by testing method return values against known values
     * performs unit test for empty by emptying tree and testing whether the tree was actually emptied
     */
    @Test
    @DisplayName("TM: empty/isEmpty")
    public static void emptyIsEmptyTest(){
       BinarySearchTree<Integer> t = new BinarySearchTree(new BinaryNode(5));
       t.insert(2);t.insert(4);t.insert(9);t.insert(4);t.insert(1);t.insert(-10);
       assertFalse(t.isEmpty());
       t.empty();
       assertTrue(t.isEmpty());
       t.insert(1);
       assertFalse(t.isEmpty());
    }
    /**
     * insert function is tested implicly by the functionality of other tests
     * a standalone insert test would require recursion and an aggressive test method, which seems largely unnecessary
     * I was able to confirm that values were being correctly inserted using a standalone calling class via breakpoints and variable inspection as well as the String tests
     */
}
