
/**
 * Takes indefinite amount of user input, parses into single words, prints the input (granting each word its own line), then prints the amount of lines, words, and characters inputted by the user
 *
 * @author Sean McLoughlin
 * @version 1.0 8/29/2024
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class LabOne
{
    /**
     * Takes indefinite amount of user input, parses into single words (defining a word as any nonspace continuous string surrounded by either edge or spaces),
     * prints the input (granting each word its own line), then prints the amount of lines, words, and characters inputted by the user.
     * Prints exact user input followed by number of lines, words, and characters to file named "Lab01.txt"
     */
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        FileWriter writer = new FileWriter("Lab01.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        int lineCounter=0, wordCounter=0, charCounter=0;
        String lastIn=new String();
        System.err.println("entering loop");
        String fullOut="";
         do{
            lastIn=scan.nextLine();
            if(lastIn.equals("")) break;
            fullOut+=lastIn+"\n";
            lineCounter++;charCounter+=lastIn.length(); //tally counters
            System.err.println("continuing to loop >>"+lastIn+"<<");
            int holdLast=0; //position holder for word incrementation -- outside of loop to use for final word
            //use while loop to avoid second scanner, makes the code more understandable
            while(lastIn.indexOf(" ",holdLast)!=-1){ //search for and print each individual word by finding spaces -- while there exists a space in the remaining String
                int nextSpace=lastIn.indexOf(" ",holdLast); //location of next space (end of word indicator)
                System.out.println(lastIn.substring(holdLast,nextSpace)); //Prints full next word to console
                wordCounter++; //tally counter
                holdLast=nextSpace+1; //increment placeholder to one past the last used whitespace
            }
            if(lastIn.substring(holdLast).length()>0) wordCounter++; //tally counter
            System.out.println(lastIn.substring(holdLast)); //prints what remains of entered String
        }while(!lastIn.equals(""));//do while loop to bypass initial empty string value - gone!!
        System.err.println("exiting loop");
        String finData="\n"+"============================\nLine Count: "+lineCounter+"\nWord Count: "+wordCounter+"\nChar Count: "+charCounter;
        System.out.println(finData);
        fullOut+=finData;
        writer.write(fullOut);
        writer.close();
    }
}
