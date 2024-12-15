

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Tests Truck class by following the journey of a single truck throughout several cycles of the program. Most methods used in Truck class are helper methods or accessors, so the only relevant unit test is of the action() method
 * The functioning of the action() method is reliant on the correctness of the constructor and field variables
 * Some other trucks in the same program will be tested to evaluate how edge cases perform, but the central test is of a single truck
 * This method also implicitly tests the Map class as the main function of the Map class is to manage the Truck objects
 *
 * @author  Sean McLoughlin
 * @version (a version number or a date)
 */
public class TruckTest
{
    /**
     * Tests Truck's action() method by incrementing cycles and comparing state and distance to expected values
     * Test method evaluates:
     *      Truck's first state (moving state transition), 
     *      Truck's moving state (3 successive courses, 1-2=normal motion, 2-3=transition)
     *      Truck's unloading state (3 successive courses, 1-2=normal unload, 2-3=transition)
     *      Truck's loading state (3 successive courses, 1-2=normal load, 2-3=transition)
     *      Truck's waiting state (The majority of this is included in the Warehouse unit test, but this method tests 1 waiting state)
     */
    @Test
    @DisplayName("TM: Truck")
    public static void testTruck(){
        ArrayList<Warehouse> warehouses=new ArrayList<Warehouse>();
        ArrayList<ArrayList<Shipment>> shipments=new ArrayList<>();
        ArrayList<Truck> trucks=new ArrayList<Truck>();
        
        Warehouse w1=new Warehouse(3, new int[] {2,5});
        Warehouse w2=new Warehouse(2, new int[] {9,15});
        Warehouse w3=new Warehouse(3, new int[] {8,2});
        Warehouse w4=new Warehouse(1, new int[] {15, 1});
        warehouses.add(w1);warehouses.add(w2);warehouses.add(w2);warehouses.add(w4);
        shipments.add(new ArrayList<Shipment>());shipments.add(new ArrayList<Shipment>());shipments.add(new ArrayList<Shipment>());shipments.add(new ArrayList<Shipment>());
        
        Shipment s1=new Shipment(w1,w3,2);
        Shipment s2=new Shipment(w3,w1,2);
        Shipment s3=new Shipment(w3,w1,2);
        Shipment s4=new Shipment(w4,w2,1);
        shipments.get(0).add(s1);shipments.get(0).add(s2);shipments.get(0).add(s3);shipments.get(0).add(s4);
        Shipment s5=new Shipment(w1,w3,2);
        Shipment s6=new Shipment(w3,w1,2);
        Shipment s7=new Shipment(w3,w1,2);
        Shipment s8=new Shipment(w4,w2,1);
        shipments.get(1).add(s5);shipments.get(1).add(s6);shipments.get(1).add(s7);shipments.get(1).add(s7);
        Shipment s9=new Shipment(w1,w3,2);
        Shipment s10=new Shipment(w3,w1,2);
        Shipment s11=new Shipment(w3,w1,2);
        Shipment s12=new Shipment(w4,w2,1);
        shipments.get(2).add(s9);shipments.get(2).add(s10);shipments.get(2).add(s11);shipments.get(2).add(s12);
        Shipment s13=new Shipment(w1,w3,2);
        Shipment s14=new Shipment(w3,w1,2);
        Shipment s15=new Shipment(w3,w1,2);
        Shipment s16=new Shipment(w4,w2,1);
        shipments.get(3).add(s13);shipments.get(3).add(s14);shipments.get(3).add(s15);shipments.get(3).add(s16);  
        
        Truck t1 = new Truck(shipments.get(0), new int[] {3,3},5);
        Truck t2 = new Truck(shipments.get(1), new int[] {12,10},2);
        Truck t3 = new Truck(shipments.get(2), new int[] {9,8},3);
        Truck t4 = new Truck(shipments.get(3), new int[] {5,5},4);
        trucks.add(t1);trucks.add(t2);trucks.add(t3);trucks.add(t4);
        
        Map map=new Map(trucks, warehouses);
       // map.runSim();
       
       map.action();
       assertTrue(map.getTrucks().size()==4);
       assertTrue(map.getTrucks().get(0).getState()==1);
       assertTrue((int)map.getTrucks().get(0).getDistance()==(int)Math.sqrt(Math.pow(3-15,2)+Math.pow(3-1,2))-map.getTrucks().get(0).getSpeed());
       map.action(); //moving state 1-2
       assertTrue(map.getTrucks().get(0).getState()==1);
       for(int i=0;i<11;i++)map.action(); //moving state 2-3
       assertTrue(map.getTrucks().get(0).getState()==2); //loading state
       assertTrue(map.getTrucks().get(0).getDistance()==1);
       map.action(); //loading state 1-2
       assertTrue(map.getTrucks().get(0).getState()==0); //finished loading, back to FindJob state C14
       for(int i=0;i<7;i++) map.action();
       assertTrue(map.getTrucks().get(0).getLoad().peek().equals(s3)); //check Stack for last loaded
       for(int i=0;i<5;i++)map.action();
       assertTrue(!map.getTrucks().get(0).getLoad().peek().equals(s3)); //check that last loaded is not in in last truck slot anymore
       assertTrue(map.getTrucks().get(0).getState()==3); //enters unloading state
       assertTrue(map.getTrucks().get(0).getActive().equals(s3)); //check active load C26
       for(int i=0;i<36;i++) map.action();
       assertTrue(map.getTrucks().size()==0); //followed truck is last to finish, when it finished, the simlation should have no more trucks loaded
       
       
    }
}
