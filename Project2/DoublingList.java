
/**
 * Contains a collection of linked nodes, each with double the capacity of the previous (starting at capacity 1)
 * dead (null) spots are not considered an index of the list are are used to shift values into in the event of an addition to the list
 * dead (null) spots are ignored until the number of acitve elements reaches a minimum (2^(k-2)) number, in which case, elements are pushed together in the front of the list
 * much of this class is testes implicitly using the Iterator test class and realiance on other methods (listIterator(), constructors, helpers)
 *
 * @author Sean McLoughlin
 * @version 10/28/24
 */
import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
public class DoublingList<E> extends AbstractSequentialList<E>
{
    private Node header, footer, init;
    private Iterator iterator;
    /**
     * builds new DoublingList containing one value (and one node)
     * @param value E type object containing value ot place in list
     */
    public DoublingList(E value){
        init=new Node(1,value);
        headerFooter();
    }
    /**
     * builds new DoublingList with values passed in array
     * @param values array containing E type values which should be inserted into list
     */
    public DoublingList(E[] values){
        init=new Node(1, values);
        headerFooter();
    }
    /**
     * returns new listIterator referencing calling list and sets pointer value to passed index value
     * @param index int value representing desired pointer value
     * @return ListIterator object to be used for list traversal
     */
    public ListIterator listIterator(int index){
        iterator=new Iterator(this);
        iterator.setPointer(index);
        return iterator;
    }
    /**
     * sets header and footer values to wrap around initial node
     */
    public void headerFooter(){
        header=new Node(true, init);
        Node n=init;
        while(n.next()!=null) n=n.next(); //find last node (only really helpful in case of array constructor)
        footer=new Node(false, n);
    }
    /**
     * returns size of list by recursively moving through all nodes and adding sizes
     * @return total number of elements in the array
     */
    public int size(){
        return init.size();
    }
    /**
     * adds a value at a specified index relative to the list (null values do NOT count as indices in the list)
     * once added, values in the list will be 'bumped' up/down the list until a null spot is found to absorb the new addition
     * a full list will warrant the addition of a new ndoe to the end of the list to provide an absorbing null element
     * code for this method is intentionally compact since there is very little full overlap across parts, so the code is easier to read as a unit rather than split up
     * @param ind int value representing the index at which the value should be added
     * @param e E type value representing value ot be added to the list
     */
    public void add(int ind, E e){
        if(e==null) throw new NullPointerException();
        int capcity = findCap();
        if(!hasSpace()){ //if there exists no empty spaces in list, append new node to end of list
            Node n=init;
            while(n.next()!=null) n=n.next(); //find last node
            n.setNext(new Node(2*n.nodeCap()));
        }
        int indCurr=0;
        Node node = init, foundBefore=null;
        while(indCurr+node.sizeCurr()<ind){ //while the current node is before the passed index value
            if(node.sizeCurr()<node.nodeCap()) foundBefore=node; //if node with empty value is found, mark position
            indCurr+=node.sizeCurr();
            node=node.next(); //increment node
        } //after loop run, node=node containing index, foundBefore=node containing empty index (null of none found)
        if(node.sizeCurr()<node.nodeCap()){//check current node for null spot
            int nullInd=-1, foundInd=0,i=0;
            while(nullInd<0||indCurr!=ind){ //increment to find places
                if(node.getCurr(i)==null) nullInd=i;
                if(indCurr<ind&&node.getCurr(foundInd)!=null) indCurr++;
                if(indCurr<ind)foundInd++;
                i++;
            } //foundInd=index of target, nullInd=index in node of list index target
            if(nullInd>foundInd){ //moving up
                for(int k=nullInd;k>foundInd;k--){
                    node.setVal(k,node.getCurr(k-1));
                }
            }
            else{
                for(int k=nullInd;k<foundInd;k++){
                    node.setVal(k, node.getCurr(k+1));
                }
            }
            node.setVal(foundInd,e);
            node.incrSize(1);
        }
        else if(foundBefore==null){ //if there are no preceding empty slots, begin rightward shift
            Node hold=node;
            while(hold.size()>hold.nodeCap()&&hold.next()!=null){ //find nearest node containing an empty slot
                hold=hold.next();
            } //potential errors coming out of loop: there exist no empty slots and a new node must be created, 
            if(hold.size()==hold.nodeCap()){ //if there exists no empty slots, make new node and set to hold
                hold.setNext(new Node(hold.nodeCap()*2, hold.getCurr(hold.nodeCap()-1))); //make a new node containing final value of old final list
                hold.next().setPrevious(hold);
                hold=hold.next();
            }
            else hold.incrSize(1); //increment size of node receiving value if node wasn't just created with new pushed value (size would already be correct)
            int indEmp;
            while(!node.equals(hold)){ //while not in the node containing add index
                indEmp=0;
                for(int i=0;hold.getCurr(i)!=null;i++) indEmp++; //indEmp will end up as the index of the null value
                while(indEmp>0){ //stops at 1 so can pull from below
                    hold.setVal(indEmp, hold.getCurr(indEmp-1));
                    indEmp--;
                }
                hold=hold.previous();
                hold.next().setVal(0, hold.getCurr(hold.sizeCurr()-1)); //set first of last to last of new
                indEmp=hold.sizeCurr()-1;
            } //after this loop has finished, everything following the node containing the empty slot should be correct
            int target=0;
            while(indCurr!=ind){
                if(node.getCurr(target)!=null) indCurr++; //if the value at the current index of the node is not null, increment total index counter
                target++;
            }
            for(int i=node.sizeCurr()-1;i>target;i--){ //approach target index but don't reach (can reach, it doesn't really matter)
                node.setVal(i,node.getCurr(i-1));//set next index down to value held at i index
            }
            node.setVal(target, e);
        }
        else{ //if there was a previously found node with space, foundBefore contains the Node value of the node with space
            foundBefore.incrSize(1); //increment size of Node receiving a new element
            int indEmp=1;
            for(int i=0;foundBefore.getCurr(i)!=null;i++) indEmp++;
            while(!node.equals(foundBefore)){
                while(indEmp<foundBefore.nodeCap()-2){ //stop one before end of list to allow pull from above
                    node.setVal(indEmp, node.getCurr(indEmp+1));
                    indEmp++;
                }
                node.setVal(node.nodeCap()-1, node.next().getCurr(0));
                node=node.next();
            }
            //indCurr=index before reachign currrent node, ind=final index
            int indHold=0;
            while(indCurr+indHold!=ind) //after loop finishes, infHold will be equal to the index in 'node' which contains the list index to be added to
                if(node.getCurr(indCurr+indHold)!=null) indHold++; 
            for(int i=0;i<indHold;i++) //pull from above until value at target it pulled
                node.setVal(i, node.getCurr(i+1));
            node.setVal(indHold, e);
        }
        //return true;
    }
    /**
     * removes element at passed index and returns value that was previously stored
     * if the list is foudn to have reached the minimum number fo elements allowed to be stored, elements are pushed to the front of the list
     * code for this method is intentionally compact since there is very little full overlap across parts, so the code is easier to read as a unit rather than split up
     * @param ind int value representing the lsit index of the item to be removed
     * @return E type object representing object previously stored at specified index
     */
    public E remove(int ind){
        if(ind>size()) throw new NoSuchElementException();
        E val;
        int indCurr=0;
        Node n=init;
        while(indCurr+n.sizeCurr()<=ind){ //find node conatining item to remove
            indCurr+=n.sizeCurr();
            n=n.next();
        }
        int indHold=0;
        int indMove=0;
        while(indCurr<ind||ind==0){ //find index inside of node containing item to remove
            if(n.getCurr(indMove)!=null){
            indCurr++;
            if(ind==0){
                indMove++;
                break;
            }
        }
            indMove++;
        }
        val=(E)n.getCurr(indMove);
        /*for(int i=indMove;i<n.nodeCap()-2;i++) //implicitly remove element
            n.setVal(i,n.getCurr(i+1));
            */
        n.setVal(indMove, null); //remove node
        n.incrSize(-1); //increment size
        //n.setVal(n.nodeCap()-2, null); //correct final value
        if((findSize()+1)<(2^(nodeCount()-2)-1)){ //if shift is required
            Node node=init, nodeOpen=init;
            int indOpen=0, indNext;
            while(node!=null){ //compact all to front
                while(nodeOpen.getCurr(indOpen)!=null){ //move through to find empty slot
                    indOpen++;
                    if(indOpen==nodeOpen.nodeCap()){
                        indOpen=0;
                        nodeOpen=nodeOpen.next();
                    }
                }
                node=nodeOpen; //reset to earlest known nonnull point
                indNext=indOpen; //set pointer to start immediately after earliest known nonnull point
                while(node.getCurr(indNext)==null){ //move through to find nonEmpty slot
                    indNext++;
                    if(indNext==node.nodeCap()){
                        indNext=0;
                        node=node.next();
                    }
                } //after this loop has finished, indOpen points to the frontmost empty slot in the list, contained by nodeOpen | indNext points to next non empty slot in list contained by node
                nodeOpen.setVal(indOpen, node.getCurr(indNext)); //perform 'swap' between null value and full value
                node.setVal(indNext, null);

            }
        }
        return val;
    }
    public void correctSize(){
        Node n=init;
        while(n!=null){
            int counter=0;
            for(int i=0;i<n.nodeCap();i++){
                if(n.getCurr(i)!=null) counter++;
            }
            n.setSize(counter);
        }
    }
    /**
     * recursively finds whether there exists space to add an element somewhere in the list
     * @return boolean representing whether there exists a null element to be filled by the list
     */
    public boolean hasSpace(){
        if(init==null)return false;
        return (init.hasSpace());
    }
    /**
     * recursively finds the full capcity of the list
     * @return int value representing the full capacity of the list
     */
    public int findCap(){
        return init.findCap();
    }
    /**
     * recursively finds the full size of the list
     * @return int value representing total number of values held by list
     */
    public int findSize(){
        return init.sizeFull();
    }
    /**
     * returns the number of nodes held by the list
     * @return int value representing the number of nodes contained in list
     */
    public int nodeCount(){
        Node node=init;
        int count=1;
        while(node.next()!=null){ 
            count++;
            node=node.next();
        }
        return count;
    }
    /**
     * returns item at point in list (omits nonreal (null) indices)
     * @param index lsit index to find element at
     * @return element stored at passed list index
     */
    public E get(int index){
        Node n = init;
        int currInd=0;
        while(currInd+n.sizeCurr()<=index){ //move through list until node containing item to return is found
            currInd+=n.sizeCurr();
            n=n.next();
        }
        int nodeInd=0;
        while(currInd!=index){ //move through node to find local index that points to desired list index
            if(n.getCurr(nodeInd)!=null) currInd++;
            nodeInd++;
        }
        return (E)n.getCurr(nodeInd);
    }
    /**
     * returns last node in list via footer's previous pointer
     * @return last node contained by current list
     */
    public Node lastNode(){
        return footer.previous();
    }
    /**
     * returns first node in list via header's next pointer
     * @return first node contained by current list
     */
    public Node firstNode(){
        return header.next();
    }
    /**
     * parses list to readable String value
     */
    public String toString(){
        ArrayList<E> stringHelper = new ArrayList<E>();
        for(Node n=init; n!=null;n=n.next()){
            for(int i=0;i<n.nodeCap();i++){
                if(n.getCurr(i)!=null) stringHelper.add((E)n.getCurr(i));
            }
        }
        return stringHelper.toString();
    }
}
