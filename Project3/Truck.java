
/**
 * Truck class to deliver given shipments to Warehouse calsses across map.
 * Begins with list of shipments to pick up/drop off, traverses across map picking up and dropping off shipments
 *
 * @author Sean McLoughlin
 * @version 12/14/24
 */
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Truck implements Schedule
{
    private Stack<Shipment> heldShipments; //will store orders in order of LIFO so that only pop() and peek() are necessary
    private PriorityQueue<Shipment> orders; //stores orders that are yet to have been picked up
    private int maxShipments; //stores max number of 'units' that can be picked up by truck
    int currentlyHeld; //stores number of 'units' that are currently beign held by truck
    private int speed; //holds the number of 'miles' that can be traversed by the truck in one tick
    private int[] location; //current location of truck -- not updated except when reaching warehouse
    private double distance; //distance to next shipment
    private int state; //0=find job, 1=moving, 2=loading, 3=unloading, 4 waiting
    private Shipment nextPursuit; //shipment object being actively pursued
    public static int pubID;
    public int localID; //id number for current truck instance
    private int cycle; //incremented every time action() is called
    /**
     * Truck constructor with param containing a set of orders to be added to the itenerary
     *      orders list is vetted to ensure that there are no orders which are impossible for the truck to pick up
     * Fields are initialized to appropriate values, maxShipments is set to random number between 2 and 5 -- speed is set to the corresponding value
     * Orders are transferes to a PriorityQueue and ordered in a way that considers both time of creation and distance from truck
     * @param orders ArrayList of Shipment variables representing orders to be picked up by the truck
     */
    public Truck(ArrayList<Shipment> orders){
        maxShipments=(int)(Math.random()*4)+2; //this should remain the same throughout the program
        for(int i=0;i<orders.size();i++){ 
            orders.get(i).setTruck(this); //set truck so that queue can properly order
            if(orders.get(i).size()>maxShipments){
                 orders.remove(i); //remove ineligible shipments - eg. when shipment size is greater than truck size
            //truck makes its own size and shipment creation is removed from truck control, this means that on occasion, a truck can have a shipment of greater size than it can carry in one load
            i--;
            }
        }
        heldShipments=new Stack<Shipment>();
        this.orders=new PriorityQueue<Shipment>();
        location=new int[2];location[0]=(int)(Math.random()*16);location[1]=(int)(Math.random()*16); //generate starting location
        for(int i=0;i<orders.size();i++) this.orders.add(orders.get(i)); //copy values of param into itenerary
        
        currentlyHeld=0;
        speed=6-maxShipments; //this should remain the same throughout the program
        distance=0; //initialize, value doesn't matter as it is updated before use
        state=0; //set initial state to 'find job'
        nextPursuit=null; //initialize, value doesn't matter as it is updated before use

        pubID++;
        localID=pubID;
        cycle=0;
        /* used for separation of log files
        try{
        File fileInit = new File("Truck"+localID+".log");
        fileInit.delete(); //deletes file if it already exists (from previous run most likely)
        fileInit.createNewFile(); //builds new empty file
        }catch(IOException e){
            System.out.println("IO in Truck Constructor");
        }
        */
    }
    /**
     * Prints current status of this truck to the Program-wide MapLOG file
     * Printed in format:
     * Truck (truckID) | Location [x,y] | Storing: carrying/capacity | State: currentState | Active Shipment: shipmentOfInterest
     *      On Board Shipments (size): (list of shipments on truck in FILO order) | Eligible: (last shipment to be added to truck)
     *      Itenerary (size): (list of shipments still to be picked up)
     * 
     * @return int type value representing whether the log was successful, 1=success, -1=failure
     */
    public int log_status(){
        try{
        //FileWriter writer=new FileWriter("Truck"+localID+".log",true);
        FileWriter writer=new FileWriter("MapLOG.log",true);
            String rtn="Truck "+localID+"("+cycle+"): ";
            rtn+="Location: ["+location[0]+", "+location[1]+"] | ";
            rtn+="Storing "+currentlyHeld+"/"+maxShipments+" | ";
            if(state==0) rtn+="State = FindJob | ";
            else if(state==1) rtn+="State = Moving, Distance left: "+distance+" | ";
            else if(state==2) rtn+="State = Loading, Time left: "+(int)distance+" | ";
            else if(state==3) rtn+="State = Unloading, Time left: "+(int)distance+" | ";
            else if(state==4) rtn+="State = Waiting for dock at warehouse "+nextPursuit.getLocation().getID()+" | ";
            rtn+="Active Shipment: "+nextPursuit;
            rtn+="\n        On Board Shipments("+heldShipments.size()+"): "+heldShipments.toString()+" | Eligible: "+(heldShipments.size()>0?heldShipments.peek():"truck empty");
            rtn+="\n        Itenerary ("+orders.size()+"): "+orders.toString()+"\n";
            writer.write(rtn);

        writer.close();
        return 1;
        }
        catch(IOException e){
            return -1;
        }
    }
    /**
     * truck testing constructor -- identical to general constructor but begins at preset location adn has preset ned size
     * @param orders ArrayList of orders to be added to truck's itenerary
     * @param location 2 index int array defining truck's starting location
     * @param size int value representing the truck's bed size
     */
        public Truck(ArrayList<Shipment> orders, int[] location, int size){
        maxShipments=size; //this should remain the same throughout the program
        for(int i=0;i<orders.size();i++){ 
            orders.get(i).setTruck(this); //set truck so that queue can properly order
            if(orders.get(i).size()>maxShipments){
                 orders.remove(i); //remove ineligible shipments - eg. when shipment size is greater than truck size
            //truck makes its own size and shipment creation is removed from truck control, this means that on occasion, a truck can have a shipment of greater size than it can carry in one load
            i--;
            }
        }
        heldShipments=new Stack<Shipment>();
        this.orders=new PriorityQueue<Shipment>();
        this.location=location;
        for(int i=0;i<orders.size();i++) this.orders.add(orders.get(i)); //copy values of param into itenerary
        
        currentlyHeld=0;
        speed=6-maxShipments; //this should remain the same throughout the program
        distance=0; //initialize, value doesn't matter as it is updated before use
        state=0; //set initial state to 'find job'
        nextPursuit=null; //initialize, value doesn't matter as it is updated before use

        pubID++;
        localID=pubID;
        cycle=0;
        /* used for separation of log files
        try{
        File fileInit = new File("Truck"+localID+".log");
        fileInit.delete(); //deletes file if it already exists (from previous run most likely)
        fileInit.createNewFile(); //builds new empty file
        }catch(IOException e){
            System.out.println("IO in Truck Constructor");
        }
        */
    }
    /**
     * Designates the action to be performed by the truck for this hour. FSM manager method
     * increments cycle variable to track number of hours elapsed
     */
    public void action(){
        cycle++;
        if(finished());
        else if(state==0) stateZero();
        else if(state==1) stateOne();
        else if(state==2) stateTwo();
        else if(state==3) stateThree();
        else if(state==4) stateFour();
    }
    /**
     * findJob state
     * finds next job, updates variables accordingly, moves for one hours worth, then passes to either waiting or moving state
     */
    private void stateZero(){
        nextPursuit=findNext();
        distance=nextPursuit.getDistance(location)-speed; //set distance and move one hour
        if(distance<=0){ //if the destination was less than one hour away
            state=4;
            stateFour();
        }
        else state=1; //if package found and arrival is >1hr away, set state to moving
    }
    /**
     * moving state
     * advances toward destination and updates state if location is found
     */
    private void stateOne(){
        distance-=speed; //move toward destination
        if(distance<=0){ //if arrived, enter state four
            state=4;
            stateFour();
        } //otherwise no action needed
    }
    /**
     * loading state
     * decrements 'distance' (time until done loading) and check if finished (distance=0)
     * if finished, add to stack, remove from itenerary, change to state 0
     */
    private void stateTwo(){
        distance--;
        if(distance==0){ 
            nextPursuit.getLocation().leaveDock(this); //if finished, leave dock
            nextPursuit.pickUp(); //mark shipment as picked up
            currentlyHeld+=nextPursuit.size(); //add size to tally of held 'units'
            heldShipments.add(nextPursuit); //load shipment onto truck
            nextPursuit=null;
            state=0; //next hour, the truck should search for pursuits
        }
    }
    /**
     * dropping state
     * decrements 'distance' (time until done loading) and check if finished (distance=0)
     * if finished, reomve from stack, change to state 0
     */
    private void stateThree(){
        distance--;
        if(distance==0){
            nextPursuit.getLocation().leaveDock(this); //if finished, leave dock
            currentlyHeld-=nextPursuit.size(); //drop off package
            nextPursuit=null;
            state=0; //next hour, the truck should be searching for pursuits
        }
    }
    /**
     * waiting/arrival state -- called if in waiting state or if arrived at warehouse in some motion state
     * updates variables according to state 4, attempt to take dock, if successful update distance to represent time left and enter loading/unloading state
     */
    private void stateFour(){
        if(nextPursuit.getLocation().takeDock(this)==true){ //attempt to take dock, if successful...
            distance=nextPursuit.size(); //use distance variable as hold variable while in loading/unloading state
            if(nextPursuit.hasBeenPickedUp()==false) state=2; //if loading, set to loading state
            else state=3; //if unloading set to unloading state
        }
    }
    /**
     * find next Shipment to be pursued by taking from either fromt of queue or top of stack
     * @return Shipment to be pursued next
     */
    public Shipment findNext(){
        if(orders.size()==0||orders.peek().size()+currentlyHeld>maxShipments||heldShipments.size()>0&&orders.peek().compareTo(heldShipments.peek())>0) //if there is nothing to pick up or pickup is too large
            return heldShipments.pop(); //begin toward dropoff location
        else return orders.poll(); //otherwise begin toward next pickup location
    }
    /**
     * checks if there exists any more shipments to be picked up or dropped off, if not the Truck is done with its job
     * @return boolean value representing whether the truck has finished all tasks
     */
    public boolean finished(){
        return nextPursuit==null&&orders.isEmpty()&&heldShipments.isEmpty(); //if there exist no more orders to take action on, the process is over
    }
    /**
     * accessor method for the last known position of the truck on the map
     * @return 2 index int array representing the truck's position
     */
    public int[] getLocation(){
        return location;
    }
    /**
     * returns readable representation of current truck with T marker and local ID
     * @return String representation of object containing T marker and ID number
     */
    public String toString(){
        return "T"+localID;
    }
    /**
     * testing method to return Shipment values which have been loaded onto the truck
     * @return Stack type value representing values loaded onto truck
     */
    public Stack getLoad(){
        return heldShipments;
    }
    /**
     * testing method to return Shipment values on itenerary
     * @return PriorityQueue type value representing Itenerary of shipments to be picked up by truck
     */
    public PriorityQueue getIten(){
        return orders;
    }
    /**
     * returns unique localID value for this truck instance
     * @return
     */
    public int getID(){
        return localID;
    }
    /**
     * checks 2 truck instances for equality using thier unique localID values
     * @param val Truck type value to be considered
     * @return boolean value representing equality
     */
    public boolean equals(Truck val){
        return val.getID()==localID;
    }
    /**
     * testing method to access distance value
     * @return double value representing distance variable
     */
    public double getDistance(){
        return distance;
    }
    /**
     * testing method to return state value to determine current Truck action
     * @return int value representing state variable
     */
    public int getState(){
        return state;
    }
    /**
     * testing method to get speed value of Truck
     * @return int value representing truck speed
     */
    public int getSpeed(){
        return speed;
    }
    /**
     * testing method to get current active pursuit
     * @return Shipment variable representing active pursuit
     */
    public Shipment getActive(){
        return nextPursuit;
    }
}
