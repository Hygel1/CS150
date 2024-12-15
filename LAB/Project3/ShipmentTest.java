

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * The test class ShipmentTest.
 *
 * @author  Sean McLoughlin
 * @version 12/14/24
 */
public class ShipmentTest
{
    /**
     * tests add method by building Shipment object and testing that the set value is read back by the get method - testing both methods
     */
    @Test
    @DisplayName("TM: set/getTruck")
    public static void testTruck(){
        Shipment s=new Shipment(new Warehouse(), new Warehouse());
        Truck t1=new Truck(new ArrayList());
        s.setTruck(t1);
        assertTrue(s.getTruck().getIten().size()==0);
        ArrayList<Shipment> ship=new ArrayList();ship.add(new Shipment(new Warehouse(), new Warehouse()));
        Truck t2=new Truck(ship);
        s.setTruck(t2);
        assertTrue(s.getTruck().getIten().size()==1);
    }
    /**
     * tests add method by building Shipment values with predictable results, then testing against each other
     */
    @Test
    @DisplayName("TM: compareTo")
    public static void compareTest(){
        Warehouse w1=new Warehouse(3,new int[]{1,1});
        Warehouse w2=new Warehouse(3,new int[]{1,2});
        Warehouse w3=new Warehouse(3,new int[]{5,2});
        
        Shipment s1=new Shipment(w1,w2); //comparing val will be 1+1=2
        Shipment s2=new Shipment(w2,w3); //comparing value will be 2+4=6
        
        ArrayList<Shipment> ship1=new ArrayList(); ship1.add(s1);
        ArrayList<Shipment> ship2=new ArrayList(); ship2.add(s2);
        
        Truck t1=new Truck(ship1, new int[]{1,2},7);
        Truck t2=new Truck(ship2,new int[]{5,2},7);
        
        assertTrue(s1.compareTo(s2)==-1);
        assertTrue(s2.compareTo(s1)==1);
    }
}
