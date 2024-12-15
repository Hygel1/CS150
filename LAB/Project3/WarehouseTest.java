

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class WarehouseTest.
 *
 * @author  Sean McLoughlin 
 * @version 12/14/24
 */
public class WarehouseTest
{
    /**
     * tests add method by building Shipment object and testing that the set value is read back by the get method - testing both methods
     */
    @Test
    @DisplayName("TM: dock")
    public static void testDock(){
        Truck t=new Truck(new ArrayList());
        Truck t1=new Truck(new ArrayList());
        Truck t2=new Truck(new ArrayList());
        Truck t3=new Truck(new ArrayList());
        
        Warehouse w=new Warehouse(2, new int[]{2,3});
        w.takeDock(t);w.takeDock(t1);w.takeDock(t2);
        assertFalse(w.takeDock(t3));
        assertTrue(w.getDocks()[0].equals(t));
        w.leaveDock(t);
        assertFalse(w.takeDock(t3));
        assertTrue(w.takeDock(t2));
    }
}
