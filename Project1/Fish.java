 
/**
 * represents an instance of a Fish animal to be used in the River class
 */
public class Fish extends Animal{
    
    /**
     * initializes fish object, sets age to 0 and randomly assigns gender
     */
    public Fish(){
        age=0;
        if(Math.random()>.5) gender=Gender.MALE;
        else gender=Gender.FEMALE;
    }
    /**
     * used for initial river setup, gives fish a random age value within possibility
     */
    public void randAge(){
        age = (int)Math.random()*6;
    }
    /**
     * advances age by one year
     */
    public void ageOneYear(){
        age++;
    }
    /**
     * returns 1 to represent fish class
     * @return value 1 represents status Fish
     */
    public int checkType(){return 1;}

    /**
     * returns a string representing a single fish object
     * states type (Fish), gender (represented as M/F), and age (followed by 'CO' for cycles old)
     * @return String representing Fish object
     */
    public String toString(){
        return "F"+(gender==Gender.FEMALE?"F":"M")+age;
    }
}
