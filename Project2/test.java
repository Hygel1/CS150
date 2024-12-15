
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    public static void main(String[] args){
        String[] vals={"a","b","c","d","e"};
        DoublingList<String> newList = new DoublingList(vals);
        System.out.println(newList.toString()); //test toString (constructor)
        
        newList.add(2,"r");
        System.out.println(newList.toString());
        
        newList.remove(3);
        System.out.println(newList.toString());
        
        newList.add(4, "s");
        System.out.println(newList.toString());
        for(int i=0;i<10;i++){
            newList.add(4,"A");
        }
        System.out.println(newList.toString());
    }
}
