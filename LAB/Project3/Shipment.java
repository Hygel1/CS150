
/**
 * Shipments to be carried from warehouse to warehouse by truck objects. Each shipment is assigned 2 warehouses and a truck to get from warehouse to warehouse
 *
 * @author Sean McLoughlin
 * @version 12/14/24
 */
public class Shipment implements Comparable<Shipment>
{
    private int size;
    public static int shipmentID;
    private int localID;
    private Warehouse location; //indicates destination to currently move toward
    private Warehouse destination; //holds dropoff location until it can replace location variable
    Truck truck; //truck to carry shipment
    private boolean pickedUp;
    /**
     * Shipment constructor taking pickup and dropoff locations for shipment instance. Also assigns other field values
     * @param location Warehouse object representing pickup location
     * @param destination Warehouse object representing dropoff location
     */
    public Shipment(Warehouse location, Warehouse destination){
        shipmentID++; //increment static variable from last call
        localID=shipmentID; //set shipmentID to new value of static id variable
        size=(int)(Math.random()*3)+1; //random (1-3) size
        this.location=location;
        this.destination=destination;
        pickedUp=false;
    }
    /**
     * testing shipment constructor to designate shipment size
     * @param w1 Shipment type value representing pickup location
     * @param w2 Shipment type value representing dropoff location
     * @param size int type value representing size of shipment in 'units'
     */
    public Shipment(Warehouse w1, Warehouse w2, int size){
        shipmentID++;
        localID=shipmentID;
        this.size=size;
        location=w1;
        destination=w2;
        pickedUp=false;
    }
    /**
     * sets value representing truck to pick up this shipment. Necessary to avoid creation time issues with PriorityQueue
     * @param truck Truck type value representing truck to pick up this shipment
     */
    public void setTruck(Truck truck){
        this.truck=truck;
    }
    /**
     * returns truck assigned to pick up shipment instance
     * @return Truck type value representing truck to pick up this shipment
     */
    public Truck getTruck(){
        return truck;
    }
    /**
     * compares called shipment to passed shipment instance using a combination of distance to their truck and creation time (localID)
     * @param t Shipment object representing instance to be compared to the called instance
     * @return int type value representing difference between value (pos. if this>that, neg. if this<that)
     */
    public int compareTo(Shipment t){
        double dThis = localID+Math.sqrt(Math.pow((location.getLocation()[0]-truck.getLocation()[0]),2)+Math.pow((location.getLocation()[1]=truck.getLocation()[1]),2));
        double dThat = t.getID()+Math.sqrt(Math.pow((t.getLocation().getLocation()[0]-t.getTruck().getLocation()[0]),2)+Math.pow((t.getLocation().getLocation()[1]-t.getTruck().getLocation()[1]),2));
        if(dThis>dThat) return 1;
        return -1;
    }
    /**
     * returns number of 'units' contained by shipment representing shipment size
     * @return int type value representing number of units in shipment determining size
     */
    public int size(){
        return size;
    }
    /**
     * reutrns the local ID value for shipment instance
     * @return int type value representing current Shipment instance ID
     */
    public int getID(){
        return localID;
    }
    /**
     * returns current destination of interest for Shipment instance
     * if shipment has not yet been picked up, method will return pickup location of shipment, otherwise it will return the dropoff location
     * @return current location of interest for Shipment object
     */
    public Warehouse getLocation(){
        if(pickedUp) return destination;
        return location;
    }
    /**
     * tells Shipment instance that it has been picked up by a truck
     */
    public void pickUp(){
        pickedUp=true;
    }
    /**
     * indicates whether a shipment has been picked up by its assigned truck
     * @return boolean value representing whether shipment has been picked up
     */
    public boolean hasBeenPickedUp(){
        return pickedUp;
    }
    /**
     * find distance from passed location using pythagorean theorem
     * @param other int array (2 ind) representing location to be referenced
     * @return distance from called Shipment to passed location value
     */
    public double getDistance(int[] other){
        return Math.sqrt(Math.pow(other[0]-location.getLocation()[0],2)+Math.pow(other[1]-location.getLocation()[1],2));
    }
    /**
     * returns String representation of called shipment instance with S identifier, local ID, and shipment size
     * @return String type value representing called object
     */
    public String toString(){
        return "S"+localID+" ("+size+")";
    }
}
