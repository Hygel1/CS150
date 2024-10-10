

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
/**
 * The test class IntakeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class IntakeTest
{
    private ArrayList<Word> initList(){
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("this");tokens.add("a");tokens.add("that");tokens.add("another");
        tokens.add("this");tokens.add("this");tokens.add("that");tokens.add("one");
        tokens.add("more");tokens.add("word");tokens.add("b");tokens.add("A");tokens.add("q");
        tokens.add("few");tokens.add("more");tokens.add("words");tokens.add("k");tokens.add("is");
        tokens.add("fifteen");
        ArrayList<String> dead = new ArrayList<String>();
        WordList wl = new WordList(dead, tokens);
        return wl.getWordFrequency();
    }
    /**
     * performs unit test for bubbleSort by comparing to true sorted list elements
     */
    @Test
    @DisplayName("TM: bubbleSort")
    public void testBubbleSort(){
         ArrayList<Word> list = initList();
         int test = Intake.bubbleSort(list, new FreqComparator());
         assertTrue(list.get(0).getFrequency()==3);
         assertTrue(list.get(1).getFrequency()==2);
         assertTrue(list.get(2).getFrequency()==2);
         assertEquals(test,3);
    }
    /**
     * performs unit test for selectionSort by comparing to true sorted list elements
     */
    @Test
    @DisplayName("TM: selectionSort")
    public void testSelectionSort(){
         ArrayList<Word> list = initList();
         int test = Intake.selectionSort(list, new FreqComparator());
         assertTrue(list.get(0).getFrequency()==3);
         assertTrue(list.get(1).getFrequency()==2);
         assertTrue(list.get(2).getFrequency()==2);
         assertEquals(test,2);
    }
    /**
     * performs unit test for insertionSort by comparing to true sorted list elements
     */
    @Test
    @DisplayName("TM: insertionSort")
    public void testInsertionSort(){
         ArrayList<Word> list = initList();
         int test = Intake.insertionSort(list, new FreqComparator());
         assertTrue(list.get(0).getFrequency()==3);
         assertTrue(list.get(1).getFrequency()==2);
         assertTrue(list.get(2).getFrequency()==2);
         assertEquals(test,4);
    }
    
}
