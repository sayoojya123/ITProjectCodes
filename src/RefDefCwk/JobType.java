package RefDefCwk; 
import java.io.*;
/**
 * Enumeration class JobType - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum JobType implements Serializable
{
    HARDWARE(" Hardware"), SOFTWARE(" Software"), DESIGN (" Design");
    private String type;
    
    private JobType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}
