
/**
 * Interface to ensure that Classes conform to basic operations required by simulation
 *
 * @author Sean McLoughlin
 * @version 12/14/24
 */
public interface Schedule
{
    //called each hour, forcing hourly action to be taken
    public void action();
    //stores object's current status in log file
    public int log_status();
}
