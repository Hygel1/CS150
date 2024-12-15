
/**
 * Master class to control the entire map. Builds all objects on map and designates move times
 * Map class is fully implicitly tested by the testing of all other classes
 *
 * @author Sean McLoughlin
 * @version 12/14/24
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Map implements Schedule

{
    public static int round;
    ArrayList<Truck> trucks;
    ArrayList<Warehouse> warehouses;
    ArrayList<Shipment> shipments;
    int cycle;
    /**
     * constructor to set all field values to a beginning state
     * builds random trucks, warehouses, and shipments to be used throughout the simulation
     */
    public Map(){
        cycle=0;
        round=0;
        trucks = new ArrayList<Truck>();
        warehouses=new ArrayList<Warehouse>();
        int numTrucks=(int)(Math.random()*15);
        int numWarehouses=(int)(Math.random()*5);
        for(int i=0;i<numWarehouses;i++) warehouses.add(new Warehouse());
        for(int i=0;i<numTrucks;i++){
            //ArrayList<Shipment> holdShip=new ArrayList();
            ArrayList<Shipment> holdShip=new ArrayList<>();
            for(int n=0;n<4;n++){ //max 4 shipments per truck
                int wH=(int)(Math.random()*numWarehouses);
                int wH2=(int)(Math.random()*numWarehouses); while(wH2==wH) wH2=(int)(Math.random()*numWarehouses); //ensure that pickup and dropoff are not the same location
                holdShip.add(new Shipment(warehouses.get(wH),warehouses.get(wH2))); //add a new shipment with a randomly selected pickup and dropoff
            }
            //Truck hold=new Truck
            trucks.add(new Truck(holdShip)); //add truck with randomly generated shipments
        }
        //there should now be a full stocked warehouses list and trucks list (with each truck having its own list of shipments)
        try{ //deletes any existing file with same name (likely a previous run) and replaces with empty file and runID (random number used to determine whether file replacement works)
            File file=new File("MapLOG.log");
            file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("ID: "+(int)(Math.random()*1000)+"\n");
            writer.close();
        }catch(IOException e){
            System.out.println("IO in Map constructor");
        }
    }
    /**
     * testing constructor with predetermined truck and warehouse values
     * @param t ArrayList of truck values to be used in simulation
     * @param w ArrayList of warehouse vaules to be used in simulation
     */
    public Map(ArrayList<Truck> t, ArrayList<Warehouse> w){
        cycle=0;
        round=0;
        trucks=t;
        warehouses=w;
        try{ //deletes any existing file with same name (likely a previous run) and replaces with empty file and runID (random number used to determine whether file replacement works)
            File file=new File("MapLOG.log");
            file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("ID: "+(int)(Math.random()*1000)+"\n");
            writer.close();
        }catch(IOException e){
            System.out.println("IO in Map constructor");
        }
    }
    /**
     * runs simulation until there are no more trucks to act on the map
     */
    public void runSim(){
        while(trucks.size()>0) action();
    }
    /**
     * runs simulation a designated number of cycles, helpful for testing -- will not run excess cycles if there are no more trucks to act on
     * @param num number of cycles to be considered
     */
    public void runSim(int num){
        for(int i=0;i<num&&trucks.size()>0;i++) action();
    }
    /**
     * performs one hour of action on the map by incrementing each truck one hour and updating log file
     */
    public void action(){
        cycle++;
        round++;
        for(int i=0;i<trucks.size();i++){
            trucks.get(i).action();
            if(trucks.get(i).finished()) trucks.remove(i);
        }
        log_status();
    }
    /**
     * updates log file with every relevant object's information. Trucks, warehouses, and map give their own logs; shipments are implicitly logged through trucks
     * @return int value representing successful log (1=successful, -1=fail)
     */
    public int log_status(){
        try{ //log status of whole map
            FileWriter file=new FileWriter("MapLOG.log",true);
            String rtn="Cycle: "+cycle+"\n";
            rtn+="      Trucks("+trucks.size()+"): "+trucks.toString()+"\n";
            rtn+="      Warehouses("+warehouses.size()+"): "+warehouses.toString()+"\n";
            file.write(rtn);
            file.close();
        }catch(IOException e){
            System.out.println("IO in Map log");
            return -1;
        }
        for(int i=0;i<trucks.size();i++) trucks.get(i).log_status();
        for(int i=0;i<warehouses.size();i++) warehouses.get(i).log_status();
        return 1;
    }
    /**
     * testing method to return trucks list
     * @return ArrayList of trucks representing current trucks
     */
    public ArrayList<Truck> getTrucks(){
        return trucks;
    }
}
