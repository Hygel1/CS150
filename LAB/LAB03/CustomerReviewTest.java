

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class CustomerReviewTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerReviewTest
{
    /**
     * Default constructor for test class CustomerReviewTest
     */
    public CustomerReviewTest()
    {
    }
    /**
     * tests getCustomerName function by defining an instance of CustomerReview and checking against given parameters
     */
    @Test
    @DisplayName("TM: getCustomerName")
    public void testGetName(){
        CustomerReview c1 = new CustomerReview("a","b");
        assertTrue(c1.getCustomerName().equals("a"));
        CustomerReview c2 = new CustomerReview("C","a");
        assertFalse(c2.getCustomerName().equals("c"));
    }
    /**
     * tests getLatestReview function by defining an instance of CustomerReview and checking against given parameters
     */
    @Test
    @DisplayName("TM: getLatestReview")
    public void testGetReview(){
        CustomerReview c1 = new CustomerReview("a","b");
        assertTrue(c1.getLatestReview().equals("b"));
        CustomerReview c2 = new CustomerReview("c","W");
        assertFalse(c2.getLatestReview().equals("w"));
    }
    /**
     * tests equals function by defining an instance of CustomerReview and checking against other instances of CustomerReview and VIPCustomerReview
     */
    @Test
    @DisplayName("TM: equals")
    public void testEquals(){
      CustomerReview c1 = new CustomerReview("a","a");
      CustomerReview c2 = new CustomerReview("a","a");
      CustomerReview c3 = new CustomerReview("b","a");
      VIPCustomerReview v1 = new VIPCustomerReview("a","a");
      assertTrue(c1.equals(c2));
      assertFalse(c1.equals(c3));
      assertFalse(c1.equals(v1));
    }
    /**
     * tests hashCode function by defining an instance of CustomerReview and checking against other instances of CustomerReview and VIPCustomerReview
    */
    @Test
    @DisplayName("TM: hashCode")
    public void testHashCode(){
        CustomerReview c1 = new CustomerReview("a","a");
        CustomerReview c2 = new CustomerReview("a","a");
        CustomerReview c3 = new CustomerReview("b","a");
        VIPCustomerReview v1 = new VIPCustomerReview("a","a");
        assertTrue(c1.hashCode()==c2.hashCode());
        assertTrue(c1.hashCode()==v1.hashCode());
        assertFalse(c1.hashCode()==c3.hashCode());
    }
    /**
     * tests toString method by defining a CustomerReview instance and checking toString result against expected returned String value
     */
    @Test
    @DisplayName("TM: toString")
    public void testString(){
        CustomerReview c1 = new CustomerReview("a","a");
        CustomerReview c2 = new CustomerReview("b","a");
        assertTrue(c1.toString().equals("Name: a Review: a"));
        assertTrue(c2.toString().equals("Name: b Review: a"));
    }
}
