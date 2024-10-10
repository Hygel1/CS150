

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Comparator;
/**
 * The test class FreqComparatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FreqComparatorTest
{
    /**
     * performs unit test for compare by comparing to expected values
     */
    @Test
    @DisplayName("TM: compare")
    public void testCompare(){
        Comparator comp = new FreqComparator();
        Word w1 = new Word("this");w1.incr();
        Word w2 = new Word("that");
        Word w3 = new Word("this1");
        assertTrue(comp.compare(w1,w2)>0);
        assertTrue(comp.compare(w3,w2)==0);
    }
}
