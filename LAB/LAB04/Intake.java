
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
public class Intake
{
    /**
     * takes in 2 files of text, parses contents into individual words, builds a WordList from words, then prints words to file
     */
    public static void main(String args[]) {
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
}
