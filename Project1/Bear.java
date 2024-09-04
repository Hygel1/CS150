package Project1;
/**
 * represents an instance of a Bear animal used by the River class
 */
public class Bear extends Animal{
    int strength;
    /**
     * initializes Bear object, sets age to 0, strength to 1, and randomly assigns gender
     */
    public Bear(){
        strength=1;
        age=0;
        if(Math.random()>.5) gender=Gender.MALE;
        else gender=Gender.FEMALE;
    }
    /**
     * advances age by one year and sets strength level to correspond with incoming age
     */
    public void ageOneYear(){
        age++;
        if(age<5) strength++;
        else strength--;
    }
    /**
     * return 0 to represent bear class
     */
    public int checkType(){return 0;}
    /**
     * returns strength level of individual bear as int
     * @return called bear's stength level
     */
    public int checkStrength(){ return strength;}
    /**
     * returns a String representing a single Bear object
     * includes type (Bear), gender (represented as M/F), strength level (followed by 'S'), and age (followed by 'CO' for cycles old)
     * @return String representing the called Bear object
     */
    public String toString(){
        return "(Bear "+(gender==Gender.FEMALE?"F ":"M ")+strength+"S "+age+"CO)";
    }
}
