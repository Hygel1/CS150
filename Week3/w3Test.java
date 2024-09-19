package Week3;
import java.util.Scanner;
public class w3Test<T> {
public w3Test(){
    
}

    public static void main(String[] args){
        //runPal();
        w3Test<Integer> th = new w3Test<Integer>();
        System.out.println(new w3TObj().getClass());
    }





    private static void runPal(){
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
