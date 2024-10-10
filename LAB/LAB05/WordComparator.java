
/**
 * Comparator class used to compare 2 Word objects based on length, frequency, and lexicographic value
 *
 * @author Sean McLoughlin
 * @version 10/3/24
 */
import java.util.Comparator;
/**
 * compares 2 words based on length (in descending order), then frequency (in descending order) if equal length,
 * then lexicographic value (in ascending order) if equal frequency
 * @param w1 Word object to be compared
 * @param w2 Word object to be compared against w1
 * @return int value representing final determined compare value of the 2 Word objects
 */
public class WordComparator implements Comparator<Word>
{
    public int compare(Word w1, Word w2){
        int dL = w1.getWord().length()-w2.getWord().length();
        if(dL!=0) return dL*-1;
        int dF = w1.compareTo(w2);
        if(dF!=0) return dF*-1;
        return w1.getWord().compareTo(w2.getWord());
    }
}
