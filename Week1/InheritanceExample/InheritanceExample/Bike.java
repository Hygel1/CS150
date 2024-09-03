
/**
 * A bike simulation where the biker refuses to go into negative values
 *
 * @author Jon Dahl
 * @version 0.01
 */
public class Bike extends Vehicle
{
    /**
     * Position constructor for objects of class Bike
     * @param x initial x position
     * @param y initial y position
     */
    public Bike(float x, float y)
    {
        super(x, y);
        if (this.x < 0) this.x = 0;
        if (this.y < 0) this.y = 0;
    }

    public boolean move(float x, float y)
    {
        if (this.x + x > 0 && this.y + y > 0)
        {
            return super.move(x, y);
        } else
        {
            System.err.println("I'm scared to go there!");
            return false;
        }
    }
}
