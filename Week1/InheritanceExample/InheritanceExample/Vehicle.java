
/**
 * A simple simulation of a vehicle in 2D.
 *
 * @author Jon Dahl
 * @version 0.01
 */
public class Vehicle
{
    protected float x, y;

    /**
     * Position constructor for objects of class Vehicle
     * @param x initial x position
     * @param y initial y position
     */
    public Vehicle(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the vehicle a specified amount
     * @param x amount to move in the x direction
     * @param y amount to move in the y direction
     * @return true if able to move
     */
    public boolean move(float x, float y)
    {
        this.x += x;
        this.y += y;
        return true;
    }
    
    /**
     * Gives the vehicle's position
     * @return phrase giving the position
     */
    @Override
    public String toString()
    {
        return "Vehicle at (" + x + ", " + y + ")";
    }
}
