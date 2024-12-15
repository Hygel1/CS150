
/**
 * intakes a text file containing space-separated (and line-separated) numbers, and outputs a file containing the same values multiplied by 10
 *
 * @author Sean McLoughlin
 * @version 10/24/2024
 */
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
public class QuestionOne
{   
    /**
     * intakes a text file containing space-separated (and line-separated) numbers, and outputs a file containing the same values multiplied by 10
     */
    public static void main(String[] args) throws IOException, FileNotFoundException{
        File in = new File("input.txt");
        File out = new File("output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        Scanner scan = new Scanner(in);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] nums = line.split(" ");
            line="";
            int[] nums1 = new int[nums.length];
            for(int i=0;i<nums.length;i++) nums1[i]=Integer.parseInt(nums[i])*10;
            for(int i=0;i<nums.length;i++) line+=nums1[i]+" ";
            writer.write(line.substring(0,line.length()-1)+"\n");
        }
        scan.close();writer.close();
    }
}
