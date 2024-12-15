
/**
 * contains several methods to convert between infix-notation and postfix-notation and solve for the results of equations of both types
 *
 * @author Sean McLoughlin
 * @version 11/7/2024
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
public class Lab07_Main
{
    /**
     * takes user input to read file with infix-notation equations on each line, prints evaluation of equations on corresponding lines in file at path designated by user
     */
   public static void main(String[] args) throws Exception{
       Scanner s = new Scanner(System.in);
       System.out.print("Input Path: ");
       String inPath = s.nextLine();
       System.out.print("Output path: ");
       String outPath = s.nextLine();
       File inFile = new File(inPath);
       int line=1;
       s.close();
       try{
       Scanner input = new Scanner(inFile); //created file at intake.txt
       //File outFile = new File(outPath);
       FileWriter writer = new FileWriter(outPath);
       while(input.hasNextLine()){
           String l=input.nextLine();
           int val = evaluateInfix(l);
           writer.write(val+"\n");
           System.out.println(val);
           line++;
       }
       input.close();
       writer.close();
    }catch(Exception e){
        if(e instanceof FileNotFoundException) throw new FileNotFoundException(e.getMessage());
        else throw new Exception("Problem Encountered at line "+line);
    }
   }
   /**
    * push values in array back one index, implicitly removes numTimes amount of values beginning at start index
    * array is returned via the array param being a reference
    * @param vals initial String array to be modified
    * @param start int type value representing index to begin at
    * @param end int type value representing final used index in array
    * @param numTimes number of times to perform operation
    */
   public static void pushBack (String[] vals, int start, int end, int numTimes){
       for(int i=0;i<numTimes;i++){
           for(int k=start;k<end-1;k++){
               vals[k]=vals[k+1];
           }
       }
   }
   /**
    * takes postfix-notation equation in string form (space separated) and returns the of the evaluated equation
    * @param str String type object representing equation to be solved
    * @return int value representing fully evaluated value
    */
   public static int evaluatePostfix(String str) throws Exception{
       String[] split=str.split(" ");
       int end=split.length;
       try{
           for(int i=0;i<end;i++){
               //find operators, perform operations and push back
               if(split[i].equals("^")||split[i].equals("+")||split[i].equals("-")||split[i].equals("/")||split[i].equals("*")){
                   if(split[i].equals("+")) split[i-2]=Integer.parseInt(split[i-2])+Integer.parseInt(split[i-1])+"";
                   else if(split[i].equals("*")) split[i-2]=Integer.parseInt(split[i-2])*Integer.parseInt(split[i-1])+"";
                   else if(split[i].equals("/")){ 
                       if(split[i-1].equals("0")) throw new ArithmeticException();
                       split[i-2]=Integer.parseInt(split[i-2])/Integer.parseInt(split[i-1])+"";
                   }
                   else if(split[i].equals("-")) split[i-2]=Integer.parseInt(split[i-2])-Integer.parseInt(split[i-1])+"";
                   else if(split[i].equals("^")) split[i-2]=""+(int)Math.pow(Integer.parseInt(split[i-2]),Integer.parseInt(split[i-1]));
                   pushBack(split, i-1, end, 2); //assign number to position of first value in operation and push back values ahead
                   end-=2;
                   i-=2;
                }
           }
           return Integer.parseInt(split[0]); //when operation is fully complete, there should exist an integer number at index 0 (in String form)
       } catch(Exception e){
           throw new Exception("ExpressionFormatException");
           //thrown in instance of index out of bounds exception, this will only happen when equation is formatted incorrectly as array should only be accessed when an operation is being performed on the two previous elements
           //also can be called in case of parsing error
        }
   }
   /**
    * takes String value representing infix-notation equation and converts to postfix-notation equation
    * @param val String type object designating infix-notation equation to be converted
    * @return String type value representing inputted equation in postfix form
    */
   public static String simpleInfixToPostfix(String val){
       String[] eq = val.split(" ");
       int fin=eq.length;
       for(int i=0;i<fin;i++){
           if(eq[i].equals("*")||eq[i].equals("/")){
               eq[i-1]= eq[i-1]+" "+eq[i+1]+" "+eq[i];
               pushBack(eq, i,fin,2);
               fin-=2;
               i--;
           }
       }
       for(int i=0;i<fin;i++){ //repeat for second-class operators
           if(eq[i].equals("+")||eq[i].equals("-")){
               eq[i-1]= eq[i-1]+" "+eq[i+1]+" "+eq[i];
               pushBack(eq, i,fin,2);
               fin-=2;
               i--;
            }
       }
   return eq[0];
   }
   /**
    * takes in infix-notation equation and moves through to format as postfix
    * @param val String type value representing initial infix-type equation
    * @return String type value representing postfix representation of inputted equation
    */
   public static String infixToPostfix(String val){
       String[] split = val.split(" ");
       int fin=split.length;
       for(int i=0;i<fin;i++){ //find all parenthetical statements
           if(split[i].equals("(")){
               int count=0;
               int back=-1;
               for(int n=i+1;n<fin;n++){ //find end index of parenthetical statement
                   if(split[n].equals("(")) count++; //finds nested parentheses
                   if(count==0&&split[n].equals(")")){ //this should be able to find an ending parentheses, if not then inputted equation was formatted incorrectly
                       back=n;
                       break;
                   }
               }
               String hold="";
               for(int n=i+1;n<back;n++) hold+=split[n]+" "; //make string out of parenthetical statement, i+1/fin-1 to eliminate parentheses
               split[i]=infixToPostfix(hold); //format values inside of parentheses and push to a single index
               pushBack(split, i+1, fin, back-i); //push back everything that is in parenthetical statement
               fin-=back-i;
               //i value is the same now as it was when the initial parntheses open was found
           }
       }
       for(int i=0;i<fin;i++){ //find all exponent elements
           if(split[i].equals("^")){
               split[i-1]=split[i-1]+" "+split[i+1]+" ^"; //if this throws index out of bounds, there is an issue with the formatting of the inputted equation
               pushBack(split, i, fin, 2);
               fin-=2;
               i--;
               //3*2^3
           }
       }
       for(int i=0;i<fin;i++){ //find all mult/div elements
           if(split[i].equals("*")||split[i].equals("/")){
               split[i-1]=split[i-1]+" "+split[i+1]+" "+split[i];
               pushBack(split, i,fin, 2);
               fin-=2;
               i--;
           }
       }
       for(int i=0;i<fin;i++){ //find all add/sub elements
           if(split[i].equals("-")||split[i].equals("+")){
               split[i-1]=split[i-1]+" "+split[i+1]+" "+split[i];
               pushBack(split, i,fin, 2);
               fin-=2;
               i--;
           }
       }
       //now that all parentheses in input have been found, the equation is effectively a simple infix equation (parenthetical statements will be treated as number values
       return split[0]; //entire equation should now be formatted into first element of array
   }
   /**
    * intakes infix-notation equation and returns integer representation of evaluation
    * @param str String type value representing equation to be evaluated
    * @return String type value representing evaluation of inputted equation
    */
   public static int evaluateInfix(String str) throws Exception{
    return evaluatePostfix(infixToPostfix(str));
}
}
