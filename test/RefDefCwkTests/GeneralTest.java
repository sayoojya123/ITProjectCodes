package RefDefCwkTests;

import RefDefCwk.ITProject;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import RefDefCwk.BITS;

/**
 * The test class GeneralTest, to test setup and some general 
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GeneralTest
{
    BITS pr;
    /**
     * Default constructor for test class SetupTest
     */
    public GeneralTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        //create the ITProject object used before each test method
        pr = new ITProject("Olenka", 1000);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    // Just a local method
     private boolean containsText(String text, String[] s) {
        boolean check = true;
        for(int i=0; i < s.length; i++)
        {
            check = check && text.contains(s[i]);
        }
        
        return check;
    }

    @Test
    public void allStaffList()
    {
        //Checks that staff data has been loaded (not a full test)
        boolean result = true;
        String xx[] = {"Amir","Bela",
                    "Ceri","Dana","Eli","Firat","Gani","Hui","Jaga"};
        String actual = pr.getAllAvailableStaff();
        for(String staff: xx) {
            result = result && actual.contains(staff);
        }
        assertTrue(result);
    }

    @Test
    public void checkAccountAtStart() {
        //Checks that account is set to the parameter in setUp() above
        assertEquals(1000.0, pr.getAccount(),0.99);
    }
    
    @Test
    public void isNotOverdrawnAtStart() {
        assertTrue(!pr.isOverdrawn());
    }

    @Test
    public void checkTeamAtStart() {
        //Checks that team is empty at the start
        assertTrue((pr.getTeam().contains("No staff hired")));
    }

    @Test
    public void isInJobRange() {
        // Checks that jobs have been loaded and can retrieve a job
        boolean actual = true;
        for (int x=100; x<=106; x++){
            actual = actual && pr.isJob(x);
        }
        assertTrue(actual);
    }

    @Test
    public void isNotInJobRange() {
        // Checks correct response to out of range job numbers
        boolean actual = true;
        int x = 99;
        int y = 107;
        actual =  !pr.isJob(x) && !pr.isJob(x);
        assertTrue(actual);
    }

    // Just checking getAllJobs for a few of the Jobs
    @Test
    public void Job100InAllJobs() {
        String str = pr.getAllJobs();
        String[] xx = {"100","Design","3","10","200"};
        boolean result1 = containsText(str,xx);
        assertTrue(result1);
    }
    
 
    // Checks just a few staff details
     @Test
     public void checkDetailsOfAmir() {
         String str = pr.getStaff("Amir");
         String[] mem = {"Amir","Designer","2", "300","30","Available", "false"};
         boolean result = containsText(str,mem);
         assertTrue(result); 
     }

     @Test
     public void checkDetailsOfBob() {
         String str = pr.getStaff("Bela");
           String[] mem = {"Bela","Engineer","2", "100","30","Available", "false"};
         boolean result = containsText(str,mem);
         assertTrue(result); 
     }
 
     @Test
     public void checkDetailsOfEla() {
         String str = pr.getStaff("Eli");
         String[] mem = {"Eli","Programmer","2", "200","20","Available"};
         boolean result = containsText(str,mem);
         assertTrue(result); 
     }
 
     @Test
     public void checkDetailsOfNonExistentJohn() {
         // Checks for non-existent John
         String str = pr.getStaff("John");
         String[] mem ={"No such staff"};
         boolean result = containsText(str,mem);
         assertTrue(result); 
     }
     
     @Test
     public void checkAvailableStaffAtStart() {
         // Checks getAllAvailableStaff()
         String str = pr.getAllAvailableStaff();
         String[] staff = {"Amir","Bela",
                    "Ceri","Dana","Eli","Firat","Gani","Hui","Jaga"};
         boolean result = containsText(str,staff);
         assertTrue(result); 
     } 
 
 }
