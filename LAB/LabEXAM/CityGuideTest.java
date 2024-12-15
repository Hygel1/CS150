

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CityGuideTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CityGuideTest
{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Test
    public void testAdd()
    {
        CityGuide guide = new CityGuide();
        guide.add(new RestaurantReview("a", 1));
        guide.add(new RestaurantReview("a", 2));
        guide.add(new RestaurantReview("a", 4));
        guide.add(new RestaurantReview("a", 3));
        guide.add(new RestaurantReview("a", 5));
        assertTrue(guide.get(0).getRating()==5);
        assertTrue(guide.get(1).getRating()==4);
        assertTrue(guide.get(2).getRating()==3);
        assertTrue(guide.get(3).getRating()==2);
        assertTrue(guide.get(4).getRating()==1);
    }
}
