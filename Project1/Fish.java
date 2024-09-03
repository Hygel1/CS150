package Project1;

public class Fish extends Animal{
    
    /**
     * initializes fish object, sets age to 0 and randomly assigns gender
     */
    public Fish(){
        age=0;
        River.fishTracker++;
        if(Math.random()>.5){
            gender=Gender.MALE;
            River.maleTracker++;
        }
        else{
            gender=Gender.FEMALE;
            River.femaleTracker++;
        }
    }
    /**
     * advances age by one year
     */
    public void ageOneYear(){
        age++;
    }
    /**
     * returns 1 to represent fish class
     */
    public int checkType(){return 1;}

    /**
     * returns a string representing a single fish object
     * states type (Fish), gender (represented as M/F), and age (followed by 'CO' for cycles old)
     * @return String representing Fish object
     */
    public String toString(){
        return "(Fish "+(gender==Gender.FEMALE?"F ":"M ")+age+"CO)";
    }
}
