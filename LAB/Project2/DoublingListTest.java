

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ListIterator;

/**
 * Tests methods found in DoublingList class
 *
 * @author  Sean McLoughlin
 * @version 10/29/24
 */
public class DoublingListTest
{
    /**
     * performs unit test for add by adding values to a list and testing samples against predicted values
     */
    @Test
    @DisplayName("TM: Add")
    public void testAdd(){
        DoublingList<String> list = new DoublingList(new String[]{"a","b","c","d","e","f","g"});
        list.add(3,"w");
        assertEquals("[a, b, w, c, d, e, f, g]",list.toString());
        
    }
    
    /**
     * performs unit test for remove by removing values to a list and testing samples against predicted values
     */
    @Test
    @DisplayName("TM: Remove")
    public void testRemove(){
        DoublingList<String> list = new DoublingList(new String[]{"a","b","c","d","e","f","g"});
        list.remove(3);
        assertEquals("[a, b, c, e, f, g]",list.toString());
        list.remove(0);
        assertEquals("[b, c, e, f, g]",list.toString());
        for(int i=0;i<4;i++){
            list.remove(0);
        }
        assertEquals("[g]",list.toString());
        
    }
}
