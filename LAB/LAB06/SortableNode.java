
/**
 * Contains a value and next node object to represent a single index in a linked list with sorting abilities
 *
 * @author Sean McLoughlin
 * @version 10/22/24
 */
import java.util.ArrayList;
public class SortableNode<E extends Comparable<? super E>> extends Node<E>{
    SortableNode next;
    /**
     * builds new SortableNode object containing passed E value
     */
   public SortableNode(E d){
       super(d);
   }
   /**
    * builds new list of SortableNodes based off of a passed list of Node ojbects
    */
   public SortableNode(Node n){
       super((E)n.getVal());
       if(next!=null) next=new SortableNode(n.getNext());
   }
   /**
    * builds new SortaeblNore object using values contained in passed arraylist object
    */
   public SortableNode(ArrayList<E> arr){
       super(arr);
       initNext();
   }
   private void initNext(){
       if(super.next!=null){
        next=new SortableNode(super.next);
        next.initNext();
    }
   }
   /**
    * partitions list based on passed partition value
    * @param value E type object representing partition value
    */
   public void partition(E value){
       SortableNode s1=null, s2=null;
       partitionHelper(value, s1, s2);
        if(s1!=null) s1.addToEnd(s2);
        else s1=s2;
        if(s1!=null){ 
            next=s1.next;
            val=(E)s1.getVal();
        }
   }
   /**
    * recursively creates two linked lists of SortableNodes partitioning based on passed part value
    * @param part E type object defining value to split list based on
    * @param s1 SortableNode heading the list containing values smaller than partition value
    * @param s2 SortableNode heading the list containing values larger than partition value
    */
   public void partitionHelper(E part, SortableNode s1, SortableNode s2){
       if(part.compareTo(val)>0){ 
           if(s2!=null) s2.addToEnd(val);
           else s2=new SortableNode(val);
           if(this.next!=null)
           next.partitionHelper(part, s1, s2);
        }
        else{
            if(s1!=null) s1.addToEnd(val);
            else s1=new SortableNode(val);
            if(this.next!=null) 
            next.partitionHelper(part, s1, s2);
        }
   }
   /**
    * sorts linked list by swapping values to achieve increasing order
    */
   public void sort(){
       while(next!=null){
           SortableNode low=sortHelper(this); //find smallest val
           E valHold=(E)low.setVal(val); //replace val stored in smallest node with current val
           setVal(valHold); //set current val to found smallest
           next.sort(); //move down list and repeat
       }
   }
   /**
    * recursively finds smallest value in list and returns SortableNode ojbect containing that value
    * @param curr SortableNode object to begin list search -- passes the smallest found value in recursion
    * @return SortableNode object containing the smallest value in the list
    */
   public SortableNode sortHelper(SortableNode curr){
       SortableNode hold=curr;
       if(val.compareTo((E)curr.getVal())<0) hold=curr; //hold=lesser of current object val and passed node val
       if(next==null) return hold; //if last value, return smaller value
       return next.sortHelper(hold);
   }
   /**
    * replaces val value with passed E type object, then returns old value
    * @param hold E type value indicating value to replace current value with
    * @return value held by val before being replaced
    */
   public E setVal(E hold){
       E before=val;
       val=hold;
       return before;
   }
   /**
    * sorts list and eliminates all identical values
    */
   public void uniqueSort(){
       sort();
       uSortHelper();
   }
   /**
    * recursively finds and removes identical values in linkedlist
    */
   public void uSortHelper(){
       if(next==null) return;
       if(val.compareTo((E)next.getVal())==0) next=(SortableNode)next.getNext(); //if current and next are equal, skip next node
       next.uSortHelper();
   }
}
