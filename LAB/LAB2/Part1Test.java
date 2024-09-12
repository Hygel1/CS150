

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class Part1Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Part1Test
{
    /**
     * Default constructor for test class Part1Test
     */
    public Part1Test()
    {
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.out.println("in setup");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        System.out.println("in teardown");
    }
    /**
     * performs unit test for String equals method
     */
    @Test
    @DisplayName("TM: Equals")
    public void testEquals(){
        assertTrue("this".equals("this"));
        assertFalse("this".equals("that"));
        assertFalse("this".equals("This"));
    }
    /**
     * performs unit test for String concat method
     */
    @Test
    @DisplayName("TM: Concat")
    public void testConcat(){
        assertTrue("this".concat("that").equals("thisthat"));
        assertTrue("test".concat("me").equals("testme"));
        assertTrue("another".concat("test").equals("anothertest"));
    }
    /**
     * performs unit test for String compareTo method
     */
    @Test
    @DisplayName("TM: Compare")
    public void testCompareTo(){
        assertTrue("this".compareTo("this")==0);
        assertTrue("a".compareTo("B")>0);
        assertTrue("a".compareTo("A")>0);
    }
    /**
     * performs unit test for String charAt method
     */
    @Test
    @DisplayName("TM: charAt")
    public void testCharAt(){
        assertTrue("this".charAt(0)=='t');
        assertTrue("another".charAt(3)=='t');
        assertTrue("character".charAt(2)=='a');
    }
    /**
     * performs unit test for String indexOf method
     */
    @Test
    @DisplayName("TM: IndexOf")
    public void testIndexOf(){
        assertTrue("this".indexOf("t")==0);
        assertTrue("find the space".indexOf(" ")==4);
        assertTrue("find space".indexOf("y")==-1);
    }
    /**
     * performs unit test for String substring method
     */
    @Test
    @DisplayName("TM: substring")
    public void testSubstring(){
        assertTrue("find the string".substring(1,4).equals("ind"));
        assertTrue("where is the substring".substring(13).equals("substring"));
        assertTrue("find it".substring(0).equals("find it"));
    }
    /**
     * performs unit test for String split method
     */
    @Test
    @DisplayName("TM: split")
    public void testSplit(){
         assertArrayEquals("this,that,another".split(","),new String[] {"this","that","another"});
         assertArrayEquals("anatap".split("a"),new String[] {"","n","t","p"});
         assertArrayEquals("test this method".split(" "), new String[] {"test","this","method"});
    }
}
