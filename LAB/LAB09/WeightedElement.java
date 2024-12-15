/**
 * Class to store value and weight objects to be used in PriorityQueue type object
 *
 * @author Sean McLoughlin
 * @version 12/5/24
 */
public class WeightedElement<E, W extends Comparable> implements Comparable<WeightedElement<E, W>>{
    E value; //held value
    W weight; //value to be considered in comparison
    /**
     * builds new Weighted element value containing stored value and weight
     * @param value E type object representing value being stored
     * @param weight W type value representing value to be considered in comparison
     */
    public WeightedElement(E value, W weight){
        this.value=value;
        this.weight=weight;
    }
    /**
     * compares this WeightedElement object against another passed WeightedElement object based on their held weights
     * @param WeightedElement object to be compared
     * @return int value representing the difference between objects in format this minus that
     */
    public int compareTo(WeightedElement w){
        return weight.compareTo(w.getWeight());
    }
    /**
     * returns String type value representing held value
     * @return Strign type value representing String interpretation of the held value
     */
    public String toString(){
        return value+"";
    }
    /**
     * accessor method returning Weight value held by this WeightedElement
     * @return W type value representing weight value
     */
    public W getWeight(){
        return weight;
    }
}
