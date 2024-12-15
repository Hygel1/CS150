

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * Tests AVLTree class by comparing returned values to expected values
 *
 * @author  Sean McLoughlin
 * @version 11/19/24
 */
public class AVLTreeTest
{
    /**
     * performs unit test for insert by testing balance factor of built trees
     * this unit test is more so a test of the balance function rather than the insert function
     * since the insertion element of the method is efectively the same as what is implicly tested in
     * the BinarySearchTreeTest class
     */
    @Test
    @DisplayName("TM: insert")
    public static void insertTest(){
        AVLTree<Integer> a = new AVLTree(new AVLNode(5));
        a.insert(1);a.insert(3);a.insert(-1);a.insert(9);a.insert(3);
        assertTrue(Math.abs(a.getBalanceFactor(a.getRoot()))<2);
        a.insert(3); //should not change tree at all, value already exists
        assertTrue(Math.abs(a.getBalanceFactor(a.getRoot()))<2);
        a.insert(-10);
        assertTrue(Math.abs(a.getBalanceFactor(a.getRoot()))<2);
    }
}
