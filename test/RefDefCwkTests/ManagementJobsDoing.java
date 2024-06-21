package RefDefCwkTests;

import RefDefCwk.ITProject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import RefDefCwk.BITS;

/**
 * Provides tests for the basic BITS setup
 * @author comqaam
 */
public class ManagementJobsDoing {
    
    BITS pr;
    
    // Just some local methods
    private boolean containsText(String text, List<String> str) {
        boolean result = true;
        for (String s : str) {
            result = result && text.toLowerCase().contains(s.toLowerCase());
        }
        return result;
    }
    
    private boolean containsText2(String text, String s1, String s2, String s3) {
        boolean result = false;
        result = text.contains(s1) && text.contains(s2) && text.contains(s3) ;
        return result;
    }
    
    public ManagementJobsDoing() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //create the ITProject object used before each test method
        pr = new ITProject("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }
    
// Check if staff can do Jobs
    @Test
    public void technicianDoingSoftware()
    {
        //should not be eligible
        pr.hireStaff("Bela");
        String done = (pr.doJob(102)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "no","staff","available" )));
        int expected = 800;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void technicianDoingDesign()
    {
        //should not be eligible
        pr.hireStaff("Bela");
        String done = (pr.doJob(100)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "no","staff","available" )));
        int expected = 700;
        assertTrue(expected == pr.getAccount() && result);        
    }
    
    @Test
    public void technicianDoingHardwareNoExperience()
    {
        //should be eligible but no experience
        pr.hireStaff("Bela");
        String done = (pr.doJob(101)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "not","completed","inexperience" )));
        int expected = 750;
        assertTrue(expected == pr.getAccount() && result);     
    }
     
    @Test
    public void technicianDoingHwareWithExperience()
    {
        //should be eligible with experience - can do job
        pr.getAccount();
        pr.hireStaff("Ceri");
        String done = (pr.doJob(101)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "completed","ceri" )));
        int expected = 1550;
        assertTrue(expected == pr.getAccount() && result);       
    }
    
    @Test
    public void programmerDoingHardware()
    {
        //should not be eligible
        pr.hireStaff("Gani");
        String done = (pr.doJob(101)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "no","staff","available" )));
        int expected = 650;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void programmerDoingSoftwareNoExperience()
    {
        //should be eligible but no experience
        pr.hireStaff("Gani");
        String done = (pr.doJob(102)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "not","completed","inexperience" )));
        int expected = 700;
        assertTrue(expected == pr.getAccount() && result);     
    }
    
    @Test
    public void programmerDoingSoftwareWithExp()
    {
        //should be eligible with experience
        pr.hireStaff("Eli");
        String done = (pr.doJob(102)).toLowerCase();
        boolean result = containsText(done, new ArrayList<>(Arrays.asList(
                       "completed ","eli" )));
        int expected = 1400;
        assertTrue(expected == pr.getAccount() && result);     
    }
    
    @Test
    public void programmerDoingDesignNoExperience()
    {
        //should be eligible but no experience
        pr.hireStaff("Gani");
        String done = (pr.doJob(100)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "not","completed","inexperience" )));
        int expected = 600;
        assertTrue(expected == pr.getAccount() && result);     
    }
    
     @Test
    public void programmerDoingDesignWithExp()
    {
        //should be eligible and experienced
        pr.hireStaff("Eli");
        String done = (pr.doJob(100)).toLowerCase();
        boolean result = containsText(done, new ArrayList<>(Arrays.asList(
                       "completed ","eli" )));
        int expected = 1000;
        assertTrue(expected == pr.getAccount() && result);     
    }
    
    @Test
    public void analystDoingHardware()
    {
        //should not be eligible
        pr.hireStaff("Amir");
        String done = (pr.doJob(101)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "no","staff","available" )));
        int expected = 550;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void analystDoingSoftwareNoProg()
    {
        //should not be eligible
        pr.hireStaff("Amir");
        String done = (pr.doJob(101)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "no","staff","available" )));
        int expected = 550;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void analystDoingSoftwareWithProgNoExp()
    {
        //should be eligible but inexperienced
        pr.hireStaff("Jaga");
        String done = (pr.doJob(104)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "not","completed","inexperience" )));
        int expected = 350;
        assertTrue(expected == pr.getAccount() && result);      
    }

    @Test
    public void analystDoingSoftwareWithProgWithExp()
    {
        //should be eligible but inexperienced
        pr.hireStaff("Jaga");
        String done = (pr.doJob(102)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "completed","jaga" )));
        int expected = 2500;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void analystDoingDesignWithProgNoExp()
    {
        //should be eligible but inexperienced
        pr.hireStaff("Amir");
        String done = (pr.doJob(100)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "not","completed","inexperience" )));
        int expected = 500;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void analystDoingDesignWithProgWithExp()
    {
        //should be eligible but inexperienced
        pr.hireStaff("Firat");
        String done = (pr.doJob(100)).toLowerCase();
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "completed","firat" )));
        int expected = 1600;
        assertTrue(expected == pr.getAccount() && result);      
    }
    
    @Test
    public void doingJobsInTeam1()
    {
        pr.hireStaff("Amir");
        pr.hireStaff("Gani");
        pr.hireStaff("Hui");
        //Do Jobs
        pr.doJob(101); // Account 850 done by Hela - on holiday
        pr.doJob(100); // Account 650 Alan chosen bit inexperienced
        pr.doJob(104); // Account 450 Gita chosen but inexperienced
        int expected = 300;
        assertTrue(expected == pr.getAccount());
    }
    
    @Test
    public void doingJobsInTeam2()
    {
        pr.hireStaff("Amir");
        pr.hireStaff("Gani");
        pr.hireStaff("Hui");
        //Do Jobs
        pr.doJob(101); // Account 850 done by Hela - on holiday
        pr.doJob(100); // Account 650 Alan chosen bit inexperienced
        pr.doJob(101); // Account 450 No one available
        int expected = 500;
        assertTrue(expected == pr.getAccount());
    }
    
    @Test
    public void onHoliday()
    {
        // 
        pr.hireStaff("Hui"); //Account = 550
        //Do Job
        pr.doJob(101); // Account 1350 done by Hela
        pr.doJob(101); // not done Account 1200 as Hela on hoilday
        pr.staffRejoinTeam("Hui");
        pr.doJob(101); // job done - account 2000
        int expected = 2000;
        assert(pr.getAccount()== 2000);
    }
    
    @Test
    public void gettingOverdrawn()
    {
        pr.hireStaff("Amir");
        pr.hireStaff("Gani");
        pr.hireStaff("Hui");
        //Can't do job
        String done = pr.doJob(100); // not done Account -150
        boolean result = containsText(done,new ArrayList<>(Arrays.asList(
                       "not","completed", "overdrawn" )));
        int expected = -150;
        assertTrue((expected == pr.getAccount()) && result);
    }

}

