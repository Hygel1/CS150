
/**
 * comparator class to compare 2 Word objects based on frequency
 *
 * @author Sean McLoughlin
 * @version 10/3/24
 */
import java.util.Comparator;
public class FreqComparator implements Comparator<Word>
{
    /**
     * compares 2 Word objects based solely on frequency using Word object compareTo method
     * @param w1 Word object to be compared
     * @param w2 Word object to be compared against w1
     * @return return value determined by comparing ferquency values
     */
    public int compare (Word w1, Word w2){
        return w1.compareTo(w2);
    }
}
