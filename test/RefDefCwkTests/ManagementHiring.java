package RefDefCwkTests;

import RefDefCwk.ITProject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import RefDefCwk.BITS;

public class ManagementHiring
{
    BITS pr;
    public ManagementHiring() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pr = new ITProject("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void hireStaff(){
        int expected = 700;
        pr.hireStaff("Amir");
        assertTrue(expected == pr.getAccount());
    }
    
    @Test
    public void hiredStaffInTeam() {
        int expected = 600;
        pr.hireStaff("Amir");
        pr.hireStaff("Bela");
        boolean budgetOK = (expected == pr.getAccount());
        assertTrue(pr.isInTeam("Amir") && pr.isInTeam("Bela")&& budgetOK);
    }
    
    @Test
    public void hiredStaffNotAvailable() {
        boolean result = true;
        pr.hireStaff("Amir");
        pr.hireStaff("Bela");
        String actual = pr.getAllAvailableStaff();
        result = !(actual.contains("Amir") || actual.contains("Bela"));
        assertTrue(result);
    }
    
    @Test 
    public void notEnoughMoney() {
        pr.hireStaff("Amir");
        pr.hireStaff("Dana");
        pr.hireStaff("Hui");
        pr.hireStaff("Firat");
        boolean result = (pr.getAccount()==50);
        result = result && !pr.isInTeam("Firat");
        assertTrue(result);
    }
    
    @Test 
    public void notSuchStaff() {
        pr.hireStaff("John");
        boolean result = (pr.getAccount()==1000);
        result = result && !pr.isInTeam("John");
        assertTrue(result);
    }


    /********* Mytests **************/

    @Test
    public void hireStaffBela(){
        String hireResult = pr.hireStaff("Bela");
        String result = "Staff Bela hired\nAccount = £ 900.0\n";
        assertEquals(hireResult,result);
    }

    @Test
    public void hireStaffWithLowerCase(){
        String hireResult = pr.hireStaff("hui");
        String result = "Staff hui hired\nAccount = £ 550.0\n";
        assertEquals(result, hireResult);
    }

    @Test
    public void cannotHireStaffInTeam(){
        pr.hireStaff("Amir");
        String hireResult = pr.hireStaff("Amir");
        String expected = "Staff Amir already hired\nAccount = £ 700.0\n";
        assertEquals(expected, hireResult);

    }

    @Test
    public void viewStaffsAvailableAfterHiringAStaff(){
        pr.hireStaff("Bela");
        String result = pr.getAllAvailableStaff();
        String expected = "************ Staff for Hire********\nAmir, Ceri, Dana, Eli, Firat, Gani, Hui, Jaga\n";
        assertEquals(result, expected);
    }

    @Test
    public void viewStaffsInTeam(){
        pr.hireStaff("Bela");
        pr.hireStaff("Ceri");
        String result = pr.getTeam();
        String expected = "************ TEAM ********\nBela,Ceri\n";
        assertEquals(result, expected);
    }

    @Test
    public void hireStaffWhoIsNotInList(){
        String hireResult = pr.hireStaff("Jerry");
        String result = "Staff Jerry not found\nAccount = £ 1000.0\n";
        assertEquals(hireResult, result);
    }

    @Test
    public void checkProjectInfoAfterHiringEli(){
        pr.hireStaff("Eli");
        String result = pr.toString();
        String out = "Project Overview : \n****************************************\nManager Name : Olenka\nProject Account : 800.0\nAccount Overdrawn : No\nTeam members : ************ TEAM ********\nEli\n\n";
        assertEquals(result,out);
    }

}
