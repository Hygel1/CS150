package Week3;
import java.util.Scanner;
public class palindromeRec {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("Is Palindrome?: ");
        String input=scn.nextLine();
        while(!input.equals("end")){
            System.out.println(isPalindrome(input)?"Yes":"No");
            System.out.print("Is Palindrome?: ");
            input=scn.nextLine();
        }

        scn.close();
    }
    private static boolean isPalindrome(String word){
        if(word.length()<2) return true;
        else if(word.charAt(0)!=word.charAt(word.length()-1)) return false;
        return isPalindrome(word.substring(1,word.length()-1));
    }
}
