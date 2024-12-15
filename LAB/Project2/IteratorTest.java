

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ListIterator;
/**
 * Tests methods found in Iterator class, omits testing for methods which only pass to a method of another class
 *
 * @author  Sean McLoughlin
 * @version 10/29/2024
 */
public class IteratorTest
{
    /**
     * performs unit test for setPointer by settign pointer value and calling previous index (this is an exploitation of both methods, but breaks no technical rules
     */
    @Test
    @DisplayName("TM: setPointer")
    public void testSetPointer(){
        DoublingList<Integer> list = new DoublingList(1);
        ListIterator it = list.listIterator(0);
        Iterator<Integer> iterator = (Iterator)it;
        iterator.setPointer(5);
        assertTrue(iterator.previousIndex()==4);
    }
    /**
     * performs unit test for next and previous by traversing through list using operators
     */
    @Test
    @DisplayName("TM: next/previous")
    public void testNextPrevious(){
        DoublingList<Integer> list = new DoublingList(new Integer[]{1,2,3,4,5,6,7});
        Iterator<Integer> it = (Iterator)list.listIterator();
        assertTrue(it.next()==2);
        assertTrue(it.next()==3);
        assertTrue(it.next()==4);
        assertTrue(it.previous()==3);
        assertTrue(it.previous()==2);
    }
    /**
     * performs unit test for has next previous by traversing list and testing known values
     */
    @Test
    @DisplayName("TM: hasNext hasPrevious")
    public void testHasNextPrevious(){
        DoublingList<Integer> list = new DoublingList(new Integer[]{1,2,3,4,5,6,7});
        Iterator<Integer> it = (Iterator)list.listIterator();
        assertFalse(it.hasPrevious());
        assertTrue(it.hasNext());
        for(int i=0;i<6;i++){
            it.next();
        }
        assertFalse(it.hasNext());
        assertTrue(it.hasPrevious());
    }
    /**
     * performs unit test for next/previous index by traversing list and testing known values
     */
    @Test
    @DisplayName("TM: nextIndex previousIndex")
    public void testIndices(){
        DoublingList<Integer> list = new DoublingList(new Integer[]{1,2,3,4,5,6,7});
        Iterator<Integer> it = (Iterator)list.listIterator();
        assertTrue(it.nextIndex()==1);
        assertTrue(it.previousIndex()==-1);
        it.next();
        assertTrue(it.nextIndex()==2);
        assertTrue(it.previousIndex()==0);
    }
    
}
