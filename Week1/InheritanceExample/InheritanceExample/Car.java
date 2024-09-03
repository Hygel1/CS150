
/**
 * A car simulation with simple gas tank behavior
 *
 * @author Jon Dahl
 * @version 0.01
 */
public class Car extends Vehicle
{
    protected float gasLevel, gasCapacity, mpg;

    /**
     * Constructor for objects of class Car
     * @param x initial x position
     * @param y initial y position
     * @param level intial gas tank level
     * @param capacity gas tank capacity
     * @param mpg gas efficieny
     */
    public Car(float x, float y, float level, float capacity, float mpg)
    {
        super(x, y);
        gasLevel = level;
        gasCapacity = capacity;
        this.mpg = mpg;
    }

    /**
     * Fills up the gas tank
     */
    public void fillUp()
    {
        gasLevel = gasCapacity;
    }
    
    @Override
    public boolean move(float x, float y)
    {
        float distance = (float) Math.sqrt(x * x + y * y);
        float gasConsumption = distance / mpg;
        if (gasConsumption < gasLevel)
        {
            super.move(x, y);
            gasLevel -= gasConsumption;
            return true;
        } else
        {
            System.err.println("Not enough gas!");
            return false;
        }
    }
    
    /**
     * Gives the car's position and gas level
     * @return phrase giving position and gas level
     */
    @Override
    public String toString()
    {
        return super.toString() + " with " + gasLevel + " gallons of gas";
    }
}
