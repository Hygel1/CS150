
/**
 * Interface containing method headers to be used to define a generic tree
 *
 * @author Sean McLoughlin
 * @version 11/12/24
 */
public interface Tree<E>{
    public boolean insert(E e);
    public boolean contains(E e);
    public String preOrderString();
    public String postOrderString();
    public String inOrderString();
    public void empty();
    public boolean isEmpty();
}
