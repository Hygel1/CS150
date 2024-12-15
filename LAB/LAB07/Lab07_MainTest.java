import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
/**
 * The test class Lab07_MainTest.
 *
 * @author  Sean McLoughlin
 * @version 11/12/24
 */
public class Lab07_MainTest
{
    /**
     * performs unit test for evaluatePostfix by testing method return values against known values
     */
    @Test
    @DisplayName("TM: evaluatePostfix")
    public static void evaluatePostfixTest() throws Exception{
        Lab07_Main q = new Lab07_Main();
        assertTrue(q.evaluatePostfix("4 2 + 3 *")==(4+2)*3);
        assertTrue(q.evaluatePostfix("3 2 * 1 + / 2")==(3*2+1)/2);
        assertTrue(q.evaluatePostfix("2 3 ^ 9 - 8 +")==Math.pow(2,3)-9+8);
        assertFalse(q.evaluatePostfix("3 ^ 2 + 1")==Math.pow(3,2)+1); //this should evaluate false, equation is incorrectly formatted
    }
    /**
     * performs unit test for simpleInfixTest by testing method return values against known values
     */
    @Test
    @DisplayName("TM: SimpleInfixtoPostfix")
    public static void simpleInfixTest(){
        Lab07_Main q = new Lab07_Main();
        assertEquals(q.simpleInfixToPostfix("3 * 2 + 1"), "3 2 * 1 +");
        assertEquals(q.simpleInfixToPostfix("3 + 2 * 1"), "3 2 1 * +");
        assertEquals(q.simpleInfixToPostfix("3 * 2 / 1 + 4"), "3 2 * 1 / 4 +");
    }
    /**
     * performs unit test for infixToPostfix by testing method return values against known values
     */
    @Test
    @DisplayName("TM: infixtoPostfix")
    public static void infixToPostfixTest(){
        Lab07_Main q = new Lab07_Main();
        assertEquals(q.infixToPostfix("3 * ( 2 + 1 )"), "2 1 + 3 *");
        assertEquals(q.infixToPostfix("3 + 3 ^ 2"), "3 2 ^ 3 +");
        assertEquals(q.infixToPostfix(" 3 ^ ( 7 ^ 2 ) - 3"), "3 7 2 ^ ^ 3 -");
    }
    /**
     * performs unit test for evaluateInfix by testing method return values against known values
     */
    @Test
    @DisplayName("TM: infixtoPostfix")
    public static void evaluateInfixTest() throws Exception{
        Lab07_Main q = new Lab07_Main();  
        assertEquals(q.evaluateInfix("3 * 2 + 1"), 3*2+1);
        assertEquals(q.evaluateInfix("3 * ( 2 + 1 )"), 3*(2+1));
        assertEquals(q.evaluateInfix("3 ^ ( 2 + 4 ) * 5"), (int)Math.pow(3,(2+4))*5);
    }
}
