package RefDefCwk; 
import java.io.*;
/**
 * Enumeration class StaffState - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum StaffState implements Serializable
{
    AVAILABLE("Available"), WORKING ("Working"), ONHOLIDAY("On holiday");
    private String state;
    
    private StaffState(String st)
    {
        state = st;
    }
    
    public String toString()
    {
        return state;
    }
}
