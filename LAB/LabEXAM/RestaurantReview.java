
/**
 *
 * @author Sean McLoughlin
 * @version 10/24/2024
 */
public class RestaurantReview implements Comparable<RestaurantReview>
{
    String restaurantName;
    float rating;
    public RestaurantReview(String name, float rate){
        restaurantName = name;
        rating = rate;
    }
    /**
     * compares two RestaurantReview classes based on their rating values
     * @return int representation of how values compare to each other, higher rating means lesser value
     */
    public int compareTo(RestaurantReview other){
        if(rating==other.getRating()) return 0;
        if(rating-other.getRating()>0) return -1;
        return 1;
    }
    /**
     * returns value stored by rating float value
     * @return float type value stored currently as rating
     */
    public float getRating(){
        return rating;
    }
    /**
     * sets rating value to passed float value
     * @param rating float type value to be used as rating
     */
    public void setRating(float rating){
        this.rating=rating;
    }
    /**
     * returns value stored in RestaurantName Strign
     * @return String type object representing restaurantName value
     */
    public String getRestaurantName(){
        return restaurantName;
    }
    /**
     * sets restaurantName Strign value to passed Strign value
     * @param name String type object to be used as restaurantName value
     */
    public void setRestaurantName(String name){
        restaurantName=name;
    }
}
