
/**
 * handles processing of a single word, holds String representation of Word and number of times the word has been encountered
 *
 * @author Sean McLoughlin  
 * @version 9/24/24
 */
public class Word implements Comparable<Word>
{
    private String wordVal;
    private int freq;
    /**
     * builds new word object with passed word value and initial frequency 1
     * @param String value representing the word represented by the class
     */
    public Word(String val){
        wordVal=val;
        freq=1;
    }
    /**
     * compares the frequencies of two words in format calling-passed
     * @param w represents the Word value to be passed and compared
     * @return int value representing neg. if the calling object is less frequent than the passed, pos. if greater, and 0 if equal
     */
    public int compareTo(Word w){
        return freq-w.getFrequency();
    }
    /**
     * returns value stored as word to be represented
     * @return word value represented by object
     */
    public String getWord(){
        return wordVal;
    }
    /**
     * returns the number of times a word object has been found via a counter
     * @return number of times a word has been found in the document
     */
    public int getFrequency(){
        return freq;
    }
    /**
     * increments frequency counter, called on instance of found word
     */
    public void incr(){
        freq++;
    }
    /**
     * represents word and frequency as readable String value
     * @return String value representing word value and frequency value
     */
    public String toString(){
        return wordVal+" appears "+freq+" times";
    }
    
}
