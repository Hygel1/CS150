
/**
 * Class containing a list of Word objects, built by inserting a list of String values and stop words
 *
 * @author Sean McLoughlin
 * @version 9/24/2024
 */
import java.util.ArrayList;
public class WordList
{
    ArrayList<Word> list = new ArrayList<Word>();
    /**
     * builds new WordList object and builds initial list value, representing all words aside from stop words and handling duplicates
     */
    public WordList(ArrayList<String> stopWords, ArrayList<String> tokens){
        for(int i=0;i<tokens.size();i++){
            boolean found=false;
            for(int k=0;k<list.size();k++){
                if(tokens.get(i).equals(list.get(k).getWord())){ //find duplicate
                    list.get(k).incr();
                    found=true;
                    break;
                }
            }
            if(!found&&!isStopWord(tokens.get(i), stopWords)) list.add(new Word(tokens.get(i))); //if no duplicate found, create new word object
        }
    }
    /**
     * returns copy of list representing words
     * @return copy of word list
     */
    public ArrayList<Word> getWordFrequency(){
        return list;
    }
    /**
     * searches list to find instance of word representing passed String, returns -1 if none found
     * @param w String value representing value to be searched for in list
     * @return index value of found Object representing the passed String, returns -1 for unfound instance
     */
    public int search(String w){
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getWord().equals(w)) return i;
        }
        return -1;
    }
    /**
     * finds and returns more frequently occured value
     * @return most frequently occured value
     */
    public Word getMostFrequent(){
        if(list.size()==0) return null;
        int highInd=0;
        for(int i=1;i<list.size();i++){
            if(list.get(highInd).getFrequency()<list.get(i).getFrequency()) highInd=i;
        }
        return list.get(highInd);
    }
    /**
     * finds and returns the top k most occured values
     * @param k int value representing the number of values to find
     * @return Word array representing the top k most occured values
     */
    public Word[] topKMostFrequent(int k){
        int[] found = new int[k]; //represents found indices
        int foundInd=0; //helps traverse through found index array
        for(int i=0;i<k;i++){ //find k top values
            int highInd=0; //index of most frequent value
            for(int m=0;m<i;m++){ //i = max nuber of times a previous result could appear as a defult high
                for(int r=0;r<i;r++){ //go through list and see if highInd is a value that's already been found as a high
                    if(found[m]==highInd) highInd++;
                }
            }
            for(int q=0;q<list.size();q++){ //find next highest value
                if(list.get(highInd).compareTo
                (list.get(q))<0){ //if passed value is greater than calling value, new high has been established, update accordingly
                    boolean foundAlready=false;
                    for(int w=0;w<k;w++) if(found[w]==q) foundAlready=true; //set true if the found high value has already been found
                    if(!foundAlready) highInd=q;
                }
            }
            found[i]=highInd;
        }
        Word[] rtn = new Word[k]; //turn index list into word list
        for(int i=0;i<k;i++) rtn[i]=list.get(found[i]);
        return rtn;
    }
    /**
     * searches passed stopWords list to determine whether passed word is a stopWord
     * @param word String value representing word to consider
     * @param stopWords list representing the stop word list to be scanned
     * @return boolean value representing whether the word was found to be a stop word
     */
    private Boolean isStopWord(String word, ArrayList<String> stopWords){
        return stopWords.indexOf(word)>-1;
    }
}
