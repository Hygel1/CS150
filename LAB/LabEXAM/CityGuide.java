
/**
 * Write a description of class CityGuide here.
 *
 * @author Sean McLoughlin
 * @version 10/24/2024
 */
import java.util.ArrayList;
public class CityGuide extends ArrayList<RestaurantReview>
{
    public boolean add(RestaurantReview e){
        for(int i=0;i<size();i++){
            if(e.compareTo(get(i))<0){
                add(i, e);
                return true;
            }
        }
        add(size(),e);
        return true;
    }
}
