
/**
 * Shows usage of the Vehicle class and its subclasses
 *
 * @author Jon Dahl
 * @version 0.01
 */
public class Demo
{
    public static void main(String[] args)
    {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Vehicle(-2, 3);
        vehicles[1] = new Bike(1, 2);
        vehicles[2] = new Car(5, 1, 4, 16, 32);
        
        for (Vehicle vehicle : vehicles)
        {
            System.out.println("Start at:");
            System.out.println(vehicle);
            vehicle.move(3, 0);
            System.out.println("After move(3, 0):");
            System.out.println(vehicle);
            vehicle.move(-100, -100);
            System.out.println("After move(-100, -100):");
            System.out.println(vehicle);
        }
    }
}
