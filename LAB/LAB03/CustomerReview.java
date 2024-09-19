
/**
 * Write a description of class CustomerReview here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CustomerReview
{
    private String customerName, latestReview;
    /**
     * builds instance of CustomerReview object, setting customerReview and latestReview String values
     * @param customer value to be set to customerReview field
     * @param latest value to be set to latestReview field
     */
    public CustomerReview(String customer, String latest){
        customerName=customer;
        latestReview=latest;        
    }
    /**
     * returns customerName as String value
     * @return String value representing customer name
     */
    public String getCustomerName(){
        return customerName;
    }
    /**
     * returns latestReview as String value
     * @return String value representing latest review
     */
    public String getLatestReview(){
        return latestReview;
    }
    /**
     * returns true when passed object is of same type and holds same customerName value
     * @param review object to be tested for equality
     * @return boolean representing the equality of host and passed object
     */
    public boolean equals(Object review){
         try{
             //cast review object to CustomerReview, call name getter
             return ((CustomerReview)review).getCustomerName().equals(customerName);
         }// if wrong type, will throw ClassCastException, throws false as objects are not of same type
         catch(ClassCastException e){return false;}
    }
    /**
     * returns hashcode value of customer name
     * @return hash code value representing the hash code of the customerName String
     */
    public int hashCode(){
        return customerName.hashCode();
    }
    /**
     * returns String representing review object in format Name: customerName Review: latestReview
     * @return String value representing the customerName and latestReview
     */
    public String toString(){
        return "Name: "+customerName+" Review: "+latestReview;
    }
}
