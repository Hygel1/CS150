
/**
 * LinkedList containing toString and addToBack methods, uses Node class to represent each index
 *
 * @author Sean McLoughlin
 * @version 10/24/2024
 */
public class SnobbyLinkedList<E>
{
    Node<E> init;
    public SnobbyLinkedList(){

    }
    /**
     * appends Node value to back of list headed by init node (omits if value is already represented in list)
     * if the list contains 0 values (init=null) the passed node will become the list header
     * @param N Node type object to be potentially appended to back of list
     * @return E type object representing object added (null if the object has already been represented in the list)
     */
    public E addToBack(Node n){
        if(init==null){
            init=n;
            return (E)n.getVal();
        }
        return init.addToBack(n);
    }
    /**
     * returns a comma separated readable representation of the full list
     * @return readable representation of lsit beginning at init node
     */
    public String toString(){
        return "{"+init.toString()+"}";
    }
}
