
/**
 * comparator class to compare 2 Word objects based on length and lexicographic value
 *
 * @author Sean McLoughlin
 * @version 10/3/24
 */
import java.util.Comparator;
public class LengthComparator implements Comparator<Word>
{
    /**
     * compares 2 Word objects based on length (in descending order), then lexicographic value (in ascending order) if equal length
     * @param w1 Word object to be compared
     * @param w2 Word object to be compared against w1
     * @return int value representing final determined compare value
     */
    public int compare(Word w1, Word w2){
        int dL = w1.getWord().length()-w2.getWord().length();
        if(dL!=0) return dL*-1;
        return w1.getWord().compareTo(w2.getWord());
    }
}
