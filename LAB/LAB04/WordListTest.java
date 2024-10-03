

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
/**
 * tests WordList class for errors
 *
 * @author  Sean McLoughlin
 * @version 9/24/24
 */
public class WordListTest
{
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        ArrayList<String> killWords = new ArrayList<String>();
        WordList wl1 = new WordList(s1,killWords);
        WordList wl2 = new WordList(s2,killWords);
        ArrayList<Word> w1 = wl1.getWordFrequency();
        ArrayList<Word> w2 = wl2.getWordFrequency();
    public WordListTest(){
        s1.add("A");s1.add("Q");s1.add("Q");s1.add("E");s1.add("A");
        s2.add("Q");s2.add("A");s2.add("Q");s2.add("E");s2.add("A");
    }
    /**
     * performs unit test for getWordFrequency by comparing the output fo two identical lists built in different orders
     */
    @Test
    @DisplayName("TM: getWordFrequency")
    public void testGetWordFrequency(){
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        s1.add("A");s1.add("Q");s1.add("Q");s1.add("E");s1.add("A");
        s2.add("Q");s2.add("A");s2.add("Q");s2.add("E");s2.add("A");
        ArrayList<String> killWords = new ArrayList<String>();
        WordList wl1 = new WordList(killWords,s1);
        WordList wl2 = new WordList(killWords,s2);
        ArrayList<Word> w1 = wl1.getWordFrequency();
        ArrayList<Word> w2 = wl2.getWordFrequency();        
        boolean failed = false;
        
        for(int i=0;i<w1.size();i++) if(w1.get(i).compareTo(w2.get(i))!=0) failed=true;
        assertFalse(failed);
    }
    /**
     * performs unit test for search by finding the index of a given word in a wordList
     */
    @Test
    @DisplayName("TM: search")
    public void testSearch(){
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        s1.add("A");s1.add("Q");s1.add("Q");s1.add("E");s1.add("A");
        s2.add("Q");s2.add("A");s2.add("Q");s2.add("E");s2.add("A");
        ArrayList<String> killWords = new ArrayList<String>();
        WordList wl1 = new WordList(killWords,s1);
        WordList wl2 = new WordList(killWords,s2);
        
        ArrayList<Word> w1 = wl1.getWordFrequency();
        ArrayList<Word> w2 = wl2.getWordFrequency();
        
        
        assertTrue(wl1.search("A")==0);
        assertTrue(wl1.search("M")==-1);
    }
    /**
     * performs unit test for getMostFrequent by comparing returned value against known values for specific lists
     */
    @Test
    @DisplayName("TM: getMostFrequent")
    public void testGetMostFrequent(){
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        s1.add("A");s1.add("Q");s1.add("Q");s1.add("E");s1.add("A");
        s2.add("Q");s2.add("A");s2.add("Q");s2.add("E");s2.add("A");
        ArrayList<String> killWords = new ArrayList<String>();
        WordList wl1 = new WordList(killWords,s1);
        WordList wl2 = new WordList(killWords,s2);
        ArrayList<Word> w1 = wl1.getWordFrequency();
        ArrayList<Word> w2 = wl2.getWordFrequency();
        s1.add("A");
        
        WordList list = new WordList(killWords,s1);
        assertTrue(list.getMostFrequent().getWord().equals("A"));
        assertTrue(wl1.getMostFrequent().getWord().equals("A"));
    }
    /**
     * performs unit test for topKMostFrequent by comparing returned array against known top K most frequent values
     */
    @Test
    @DisplayName("TM: topKMostFrequent")
    public void testTopK(){
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        ArrayList<String> killWords = new ArrayList<String>();
        s1.add("A");s1.add("Q");s1.add("Q");s1.add("E");s1.add("A");
        s2.add("Q");s2.add("A");s2.add("Q");s2.add("E");s2.add("A");
        WordList wl1 = new WordList(killWords,s1);
        WordList wl2 = new WordList(killWords,s2);
        ArrayList<Word> w1 = wl1.getWordFrequency();
        ArrayList<Word> w2 = wl2.getWordFrequency();
        Word[] rtn1 = wl1.topKMostFrequent(2);
        assertTrue(rtn1[0].getWord().equals("A")&&rtn1[1].getWord().equals("Q"));
        Word[] rtn2 = wl2.topKMostFrequent(2);
        assertTrue(rtn2[0].getWord().equals("Q")&&rtn2[1].getWord().equals("A"));
    }
    
}
