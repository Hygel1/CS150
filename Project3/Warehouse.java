
/**
 * Warehouse class represents a stationary location on the map for trucks to visit to either pick up or drop off assigned packages
 *
 * @author Sean McLoughlin
 * @version 12/14/24
 */
import java.io.FileWriter;
import java.io.IOException;
public class Warehouse implements Schedule
{
    ArrayList<Truck> queue;
    Truck[] docks; //true if dock is open, false if full
    int[] position; //2 index array representing position
    public static int pubID;
    private int localID;
    /**
     * No constructor warehouse constructor, initializes all fields with either empty or random values
     */
    public Warehouse(){
        queue = new ArrayList<Truck>();
        docks = new Truck[(int)(Math.random()*3+1)];
        for(int i=0;i<docks.length;i++) docks[i]=null; //initialize all docks to open
        position=new int[2];
        position[0]=(int)(Math.random()*16);position[1]=(int)(Math.random()*16); //set position
        pubID++;
        localID=pubID;
    }
    /**
     * testing constructor with designated values for number of docks and position on map
     * @param numDocks int value representing number of indices to make docks array
     * @param position int array representing position on map
     */
    public Warehouse(int numDocks, int[] position){
        queue=new ArrayList<Truck>();
        docks=new Truck[numDocks];
        this.position=position;
        pubID++;
        localID=pubID;
    }
    /**
     * returns local ID of called warehouse instance
     * @return int value representing called warehouse's instance ID
     */
    public int getID(){
        return localID;
    }
    /**
     * returns 2 index int array representing location of warehouse
     * @return 2 index int array representing location of warehouse
     */
    public int[] getLocation(){
        return position;
    }
    /**
     * searches through docks array to find open dock, returns index of open dock (-1 if there are none)
     * @return index of open dock, -1 if there are no open indices
     */
    public int dockAvailable(){
        for(int i=0;i<docks.length;i++){
            if(docks[i]==null)return i; //if open dock is found, return its index
        }
        return -1; //if no open dock is found, return -1
    }
    /**
     * Searches for open dock and claims it for passed Truck object if there is an available spot, returns false if there are no docks available
     * @param caller Truck type object representing Truck attempting to claim dock
     * @return boolean variable representing whether the claim was successful
     */
    public boolean takeDock(Truck caller){
        int ind=dockAvailable();
        if(!queue.contains(caller)) queue.add(caller); //no matter what, if the truck is not currently in the queue, add it
        if(ind>-1&&(queue.get(0).equals(caller))){ //if there is a spot open and there is nobody else waiting or the caller is in the front of the queue
            docks[ind]=caller; //claim the dock
            if(queue.size()>0) queue.remove(0); //remove the calling truck from the queue as they are currently being serviced
            return true; //truck got into dock
        } //cases left are: spot open but truck is not next in line, no spot open and truck is not next in line -- both should be handled by another call
        else{
            return false;
        }
    }
    /**
     * looks for dock occupied by passed Truck and opens it up to new truck by setting index to null
     */
    public void leaveDock(Truck caller){
        for(int i=0;i<docks.length;i++){
            if(docks[i]!=null&&docks[i].equals(caller)) docks[i]=null;
        }
    }
    /**
     * uses warehouse location to determine whether two warehouses are the same
     * @param w other warehouse to be considered
     * @return boolean type value representing whether the warehouses are equal
     */
    public boolean equals(Warehouse w){
        return position[0]==w.getLocation()[0]&&position[1]==w.getLocation()[1];
    }
    /**
     * prints a String representation of the current state of this Warehouse object to global MapLOG.log file in format
     * Warehouse (ID): (list of docks and which trucks are taking them, null if open)
     * @return int type value representing whether the log operation was successful (1=successful, -1=fail)
     */
    public int log_status(){
        try{
            FileWriter file=new FileWriter("MapLOG.log",true);
            String rtn="Warehouse "+localID+": [";
            for(int i=0;i<docks.length;i++) rtn+=docks[i]+", ";
            rtn=rtn.substring(0,rtn.length()-2)+"]\n"; //get rid of excess comma and close bracket
            file.write(rtn);
            file.close();
        }catch(IOException e){
            System.out.println("IO in warehouses log");
            return -1;
        }
        return 1;
    }
    /**
     * no action is performed by Warehouse class, this method should never be called
     */
    public void action(){
        //no activity is imposed by Warehouse class
    }
    /**
     * returns String representation of this Warehouse instance with W marker and local ID number
     * @return String representation of this object with W marked and ID number
     */
    public String toString(){
        return "W"+localID;
    }
    /**
     * testing method to see values in dock array
     * @return Truck array type value representing current state of docks
     */
    public Truck[] getDocks(){
        return docks;
    }
    /**
     * testing method -- returns truck queue
     * @return ArayList of Truck objects representing current queue
     */
    public ArrayList<Truck> getQ(){
        return queue;
    }
}
