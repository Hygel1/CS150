

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * tests Word class for errors
 *
 * @author  Sean McLoughlin
 * @version 9/24/24
 */
public class WordTest
{
    /**
     * performs unit test for getWord method by testing against known values
     */
    @Test
    @DisplayName("TM: getWord")
    public void testGetWord(){
        Word thisWord= new Word("this");
        Word thatWord = new Word("that");
        assertTrue(thisWord.getWord().equals("this"));
        assertTrue(thatWord.getWord().equals("that"));
    }
    /**
     * performs unit test for getFrequency method by testing returned value against known frequency values
     */
    @Test
    @DisplayName("TM: getFrequency")
    public void testFrequency(){
    Word thisWord = new Word("this");
    assertTrue(thisWord.getFrequency()==1);
    for(int i=0;i<20;i++) thisWord.incr();
    assertTrue(thisWord.getFrequency()==21);
    }
    /**
     * performs unit test for incr by testing that incrmentation is taking place when method is called using getFrequency method
     */
    @Test
    @DisplayName("TM: Incr")
    public void testIncr(){
        Word thisWord = new Word("this");
        for(int i=0;i<10;i++) thisWord.incr();
        assertTrue(thisWord.getFrequency()==11);
        for(int i=0;i<100;i++) thisWord.incr();
        assertTrue(thisWord.getFrequency()==111);
    }
    /**
     * performs unit test for toString method by comparing returned value against predicted values
     */
    @Test
    @DisplayName("TM: toString")
    public void testToString(){
        Word thisWord = new Word("this");
        for(int i=0;i<9;i++) thisWord.incr();
        assertTrue(thisWord.toString().equals("this appears 10 times"));
        Word thatWord = new Word("that");
        for(int i=0;i<99;i++) thatWord.incr();
        assertTrue(thatWord.toString().equals("that appears 100 times"));
    }
    /**
     * performs unit test for compareTo by comparing several known values of different words
     */
    @Test
    @DisplayName("TM: compareTo")
    public void testCompare(){
        Word thisWord = new Word("this");
        Word thatWord = new Word("that");
        assertTrue(thisWord.compareTo(thatWord)==0);
        for(int i=0;i<100;i++) thisWord.incr();
        assertTrue(thisWord.compareTo(thatWord)==100);
    }
}
