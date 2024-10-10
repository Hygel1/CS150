
/**
 * Parses file contents into individual words and prints to file
 *
 * @author Sean McLoughlin
 * @version 9/24/24
 */
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Comparator;
public class Intake
{
    private static WordList words1;
    private static WordList words2;
    private static WordList words3;
    private static WordList words4;
    /**
    *   intakes data from file, parses into WordList format, separating each individual word and excluding words designated in stop word file
    *   initializes words in separate test WordList, then sorts using bubble sort, insertion sort, and selection sort, counting the number of required swaps for each
    *       each sorting algorithm intakes a comparator to be used while comparing
    *       selects the top k values from each list
    *   prints each of these test lists as readable Strings
    */
    public static void main(String args[]) {
        setUp();
        listInit();
        int k=15;
        Word[] w1, w2, w3, w4;
        w1=topK(words1, 0, k);
        w2=topK(words2, 1, k);
        w3=topK(words3, 2, k);
        w4=topK(words4, 3, k);
        
        System.out.println(printArr(w1));
        System.out.println(printArr(w2));
        System.out.println(printArr(w3));
        System.out.println(printArr(w4));
    }
    /**
     * sorts words given a specified method, then returns an array of a given size with the top elements of the sorted words
     * @param words WordList object with words to be sorted
     * @param sortType indicates type of sorting to be used
     * @param k integer representation of the number of elements to be included in the final returned array
     * @return an array of size k containing the top k Word objects contained in WordList according to sortType
     */
    public static Word[] topK(WordList words, int sortType, int k){
        Word[] rtn = new Word[k];
        if(sortType==0) return words.topKMostFrequent(k);
        else if(sortType==1) bubbleSort(words.getWordFrequency(), new FreqComparator());//freq
        else if(sortType==2) selectionSort(words.getWordFrequency(), new LengthComparator());
        else if(sortType==3) insertionSort(words.getWordFrequency(), new WordComparator());
        for(int i=0;i<k;i++) rtn[i]=words.getWordFrequency().get(i);
        return rtn;
    }
    /**
     * takes in 2 files of text, parses contents into individual words, builds a WordList from words, then prints words to file
     */
    public static void setUp(){
        try{
        File f1 = new File("Pride_and_Prejudice.txt");
        File f2 = new File("stopwords.txt");
        Scanner s1 = new Scanner(f1);
        Scanner s2 = new Scanner(f2);
        ArrayList<String> pride = new ArrayList<String>();
        ArrayList<String> kill = new ArrayList<String>();
        while(s1.hasNextLine()){
            pride.add(s1.nextLine());
        }
        String prideStr = "";
        for(int i=0;i<pride.size();i++) prideStr+=" "+pride.get(i);
        prideStr=prideStr.toLowerCase();
        String[] prArr = prideStr.split("[\\W]+");
        pride.clear();
        for(int i=0;i<prArr.length;i++) pride.add(prArr[i]);
        while(s2.hasNextLine()){
            kill.add(s2.nextLine());
        }
        String killStr = "";
        for(int i=0;i<kill.size();i++) killStr+=" "+kill.get(i);
        killStr=killStr.toLowerCase();
        String[] killArr = killStr.split("[\\W]+");
        kill.clear();
        for(int i=0;i<killArr.length;i++) kill.add(killArr[i]);
        WordList wL = new WordList(kill,pride);
        FileWriter fw = new FileWriter("Lab04.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        ArrayList<Word> words = wL.getWordFrequency();
        for(int i=0;i<words.size();i++) bw.write(words.get(i).toString()+"\n");
        s1.close();s2.close();fw.close();bw.close();
        }catch(Exception e){}
    }
    /**
     * defines 4 WordList objects to be accessed by other methods
     */
    public static void listInit(){
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("this");tokens.add("a");tokens.add("that");tokens.add("another");
        tokens.add("this");tokens.add("this");tokens.add("that");tokens.add("one");
        tokens.add("more");tokens.add("word");tokens.add("b");tokens.add("A");tokens.add("q");
        tokens.add("few");tokens.add("more");tokens.add("words");tokens.add("k");tokens.add("is");
        tokens.add("fifteen");
        ArrayList<String> deadWords = new ArrayList<String>();
        
        words1 = new WordList(deadWords, tokens);
        words2 = new WordList(deadWords, tokens);
        words3 = new WordList(deadWords, tokens);
        words4 = new WordList(deadWords, tokens);
    }
    /**
     * helper method to swap values at specified indices in a passed ArrayList
     * @param list ArrayList object containing values ot be swapped
     * @param i int value representing the index of a value to be swapped
     * @param k int value representing the index of a value to be swapped
     */
    private static <T> void swap(ArrayList<T> list, int i, int k){
        T hold = list.get(i);
        list.set(i, list.get(k));
        list.set(k,hold);
    }
    /**
     * sorts arraylist according to the passed comparator using the selection sort algorithm
     * @param words Arraylist object containing objects to be sorted of generic type T
     * @param comp Copmarator object to be used when comparing objects in list words
     * @return number of swaps required to sort list using selection sort algorithm
     */
    public static <T> int selectionSort(ArrayList<T> words, Comparator<T> comp){
        int swaps=0;
        for(int i=0;i<words.size()-1;i++){ //goes from bottom index to one before last (there is no possible swap at the end)
            int currMin=i;
            for(int k=i+1;k<words.size();k++){ //search for value to be swapped
                if(comp.compare(words.get(currMin),words.get(k))<0) currMin=k;
            }
            if(currMin!=i){
                swap(words,currMin,i);
                swaps++;
            }
        }
        return swaps;
    }
    /**
     * sorts arraylist according to the passed comparator using the bubble sort algorithm
     * @param words Arraylist object containing objects to be sorted of generic type T
     * @param comp Copmarator object to be used when comparing objects in list words
     * @return number of swaps required to sort list using bubble sort algorithm
     */
    public static <T> int bubbleSort(ArrayList<T> words, Comparator<T> comp){
        int swaps=0;
        for(int i=words.size()-1;i>0;i--){
            for(int k=i-1;k>-1;k--){
                if(comp.compare(words.get(i),words.get(k))>0){
                    swap(words,i,k);
                    swaps++;
                }
            }
        }
        return swaps;
    }
    /**
     * sorts arraylist according to the passed comparator using the insertion sort algorithm
     * @param words Arraylist object containing objects to be sorted of generic type T
     * @param comp Copmarator object to be used when comparing objects in list words
     * @return number of swaps required to sort list using insertion sort algorithm
     */
    public static <T> int insertionSort(ArrayList<T> words, Comparator<T> comp){
        int swaps=0;
        for(int i=0;i<words.size();i++){
            for(int k=i;k>0;k--){
                if(comp.compare(words.get(k-1),words.get(k))<0){
                    swap(words,k-1,k);
                    swaps++;
                }
            }
        }
        return swaps;
    }
    /**
     * returns a String object representing a readable version of the passed array
     * @param arr array to be converted
     * @return String representing a readable version of the passed array
     */
    public static<T> String printArr(T[] arr){
        String rtn="{";
        for(int i=0;i<arr.length;i++){
            rtn+=arr[i]+", ";
        }
        return rtn.substring(0,rtn.length()-2)+"}";
    }
    /**
     * returns a String object representing a readable version of the passed ArrayList
     * @param arr Word ArrayList to be converted
     * @return String representing a readable version of the passed array
     */
    public static String printArr(ArrayList<Word> arr){
        Word[] rtn = new Word[arr.size()];
        for(int i=0;i<rtn.length;i++){
            rtn[i]=arr.get(i);
        }
        return printArr(rtn);
    }
}
