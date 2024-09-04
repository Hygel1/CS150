
/**
 * Interface defining methods to be used in l
 *
 * @author Sean McLoughlin
 * @version 9/3/2024
 */
public interface IntArrayListInterface
{
    public void add(int e);
    public void add(int index, int e);
    public int get(int index);
    public void clear();
    public boolean isEmpty();
    public int remove(int index);
    public int size();
    public int arraySize();
    public int emptyCount();
    public String toString();
    public void reset();
    public int next();
}
