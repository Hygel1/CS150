package Project1;
/**
 * represents a parent class to be inherited by animals used by the River class
 */
public abstract class Animal {
    protected enum Gender{FEMALE, MALE}
    protected Gender gender;
    protected int age;
    /**
     * returns age value for called object
     * @return value representing the age of called object
     */
    public int checkAge(){
        return age;
    }
    /**
     * returns gender value for called object
     * @return MALE or FEMALE enum value
     */
    public Gender checkGender(){
        return gender;
    }
    /**
     * returns either 1 or 0 to represent the called object
     * @return value 1 for fish, value 0 for bear
     */
    public abstract int checkType();
    /**
     * advances age by one year and assigns corresponding strength level for bear object
     */
    public abstract void ageOneYear();
}
