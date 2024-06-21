/*
 * To change this license header, choose License Headers in ITProject Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RefDefCwkTests;

import RefDefCwk.ITProject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.*;

import RefDefCwk.BITS;

/**
 * Provides tests for the basic BITS setup
 *
 * @author comqaam
 */
public class ManagementGeneral {

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
        result = text.contains(s1) && text.contains(s2) && text.contains(s3);
        return result;
    }

    public ManagementGeneral() {
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
        pr = new ITProject("Olenka", 1000);
    }

    @After
    public void tearDown() {
    }

//***** Tests for ITProject just after creation

    @Test
    public void checkAccountAtStart() {
        //Checks that account is set to the parameter in setUp() above
        assertEquals(1000.0, pr.getAccount(), 0.5);
    }

    @Test
    public void checkTeamAtStart() {
        //Checks that team is empty at the start
        assertTrue((pr.getTeam().contains("No staff hired")));
    }

    @Test
    public void isNotOverdrawnAtStart() {
        assertTrue(!pr.isOverdrawn());
    }

// ************ Jobs **********

    @Test
    public void isInJobRange() {
        // Checks that jobs have been loaded and can retrieve a job
        boolean actual = true;
        for (int x = 100; x <= 106; x++) {
            actual = actual && pr.isJob(x);
        }
        assertTrue(actual);
    }

    @Test
    public void isNotInJobRange() {
        // Checks correct response to out of range job numbers
        boolean actual = true;
        int x = 99;
        int y = 108;
        actual = !pr.isJob(x) && !pr.isJob(y);
        assertTrue(actual);
    }


    // Just checking getAllJobs for a few of the Jobs
    @Test
    public void Job100InAllJobs() {
        String str = pr.getAllJobs();
        boolean result = containsText2(str, "Design", "3", "10");
        assertTrue(result);
    }

    @Test
    public void Job104InAllJobs() {
        String str = pr.getAllJobs();
        boolean result2 = containsText2(str, "Software", "7", "15");
        assertTrue(result2);
    }

    @Test
    public void Job106InAllJobs() {
        String str = pr.getAllJobs();
        boolean result3 = containsText2(str, "Hardware", "5", "20");
        assertTrue(result3);
    }

//************** Staff *********************

    // Checks just a few staff details
    @Test
    public void checkDetailsOfAmir() {
        String mem = pr.getStaff("Amir");
        boolean result = containsText(mem, new ArrayList<>(Arrays.asList(
                "Amir", "Designer", "2", "300", "30", "false")));
        assertTrue(result);
    }

    @Test
    public void checkDetailsOfJaga() {
        String str = pr.getStaff("Jaga");
        boolean result = containsText(str, new ArrayList<>(Arrays.asList(
                "Jaga", "Designer", "4", "300", "60", "true")));
        assertTrue(result);
    }

    @Test
    public void checkDetailsOfBela() {
        String str = pr.getStaff("Bela");
        boolean result = containsText(str, new ArrayList<>(Arrays.asList(
                "Bela", "Engineer", "2", "100", "30", "Available", "false")));
        assertTrue(result);
    }

    @Test
    public void checkDetailsOfCeri() {
        String str = pr.getStaff("Ceri");
        boolean result = containsText(str, new ArrayList<>(Arrays.asList(
                "Ceri", "Engineer", "4", "250", "40", "Available", "true")));
        assertTrue(result);
    }

    @Test
    public void checkDetailsOfEli() {
        String str = pr.getStaff("Eli");
        boolean result = containsText(str, new ArrayList<>(Arrays.asList(
                "Eli", "Programmer", "2", "200", "20", "Available")));
        assertTrue(result);
    }

    @Test
    public void checkDetailsOfNonExistantJohn() {
        // Checks for non-existent John
        String str = pr.getStaff("John");
        boolean result = containsText(str, new ArrayList<>(Arrays.asList(
                "No such staff")));
        assertTrue(result);
    }

    @Test
    public void checkAvailableStaffAtStart() {
        // Checks getAllAvailableStaff()
        String str = pr.getAllAvailableStaff();
        boolean result = containsText(str, new ArrayList<>(Arrays.asList(
                "Amir", "Bela",
                "Ceri", "Dana", "Eli", "Firat", "Gani", "Hui", "Jaga")));
        assertTrue(result);
    }


    /*** My Tests ***/

    //************* Project status ************

//    check project status at start
    @Test
    public void checkProjectStatusAtStart() {
        String st = pr.toString();
        String out = "Project Overview : \n****************************************\nManager Name : Olenka\nProject Account : 1000.0\nAccount Overdrawn : No\nTeam members : No staff hired\n\n";
        assertEquals(st, out);
    }

    //    rejoin a staff who is on holiday
    @Test
    public void rejoinStaffFromHoliday() {
        pr.hireStaff("Ceri");
        pr.doJob(101);
        String rejoinOutcome = pr.staffRejoinTeam("Ceri");
        String expected = "Ceri rejoined the team after holiday";
        assertEquals(rejoinOutcome, expected);
    }

    //    rejoin a staff not on holiday
    @Test
    public void rejoinStaffNotOnHoliday() {
        pr.hireStaff("Amir");
        String rejoinOutcome = pr.staffRejoinTeam("Amir");
        String expected = "Amir is already available on team and is not on holiday";
        assertEquals(rejoinOutcome, expected);
    }

    //    rejoin a staff not in team
    @Test
    public void rejoinStaffNotInTeam() {
        String rejoinOutcome = pr.staffRejoinTeam("Firat");
        String expected = "Firat not in team so can't return from holiday";
        assertEquals(rejoinOutcome, expected);
    }

    //    rejoin a non-existant staff
    @Test
    public void rejoinStaffNotExist() {
        String rejoinOutcome = pr.staffRejoinTeam("George");
        String expected = "George not in team so can't return from holiday";
        assertEquals(rejoinOutcome, expected);
    }

    //    save a project
    @Test
    public void saveProject() {
        pr.hireStaff("Amir");
        pr.saveITProject("myproject.txt");
        File file = new File("myproject.txt");
        assertTrue(file.exists());
    }

    //    restore a saved prject
    @Test
    public void restoreSavedProject() {
        pr.hireStaff("Amir");
        pr.saveITProject("myproject.txt");
        double balanceAfterAmir = pr.getAccount();
        pr.hireStaff("Dana");
        double balanceAfterDana = pr.getAccount();
        pr = pr.restoreITProject("myproject.txt");
        double balanceAfterRestoration = pr.getAccount();
        assertTrue((balanceAfterAmir == balanceAfterRestoration) && (balanceAfterDana != balanceAfterRestoration));
    }

    // Checks the list of staff names right after initialization
    @Test
    public void checkStaffsAvailableForHireAtStart() {
        String st = pr.getAllAvailableStaff();
        String out = "************ Staff for Hire********\nAmir, Bela, Ceri, Dana, Eli, Firat, Gani, Hui, Jaga\n";
        assertEquals(st, out);
    }


    @Test
    public void listAllJobs() {
        String result = pr.getAllJobs();
        StringBuilder sb = new StringBuilder();
        sb.append("\n************ All Jobs ************\n");
        sb.append("\nJob Number: 100\n");
        sb.append("Job Type: Design\n");
        sb.append("Experience Required: 3\n");
        sb.append("Job Hours: 10\n");
        sb.append("Job Penalty: 200\n");
        sb.append("********************\n");
        sb.append("Job Number: 101\n");
        sb.append("Job Type: Hardware\n");
        sb.append("Experience Required: 3\n");
        sb.append("Job Hours: 20\n");
        sb.append("Job Penalty: 150\n");
        sb.append("********************\n");
        sb.append("Job Number: 102\n");
        sb.append("Job Type: Software\n");
        sb.append("Experience Required: 3\n");
        sb.append("Job Hours: 30\n");
        sb.append("Job Penalty: 100\n");
        sb.append("********************\n");
        sb.append("Job Number: 103\n");
        sb.append("Job Type: Design\n");
        sb.append("Experience Required: 9\n");
        sb.append("Job Hours: 25\n");
        sb.append("Job Penalty: 250\n");
        sb.append("********************\n");
        sb.append("Job Number: 104\n");
        sb.append("Job Type: Software\n");
        sb.append("Experience Required: 7\n");
        sb.append("Job Hours: 15\n");
        sb.append("Job Penalty: 350\n");
        sb.append("********************\n");
        sb.append("Job Number: 105\n");
        sb.append("Job Type: Hardware\n");
        sb.append("Experience Required: 8\n");
        sb.append("Job Hours: 35\n");
        sb.append("Job Penalty: 300\n");
        sb.append("********************\n");
        sb.append("Job Number: 106\n");
        sb.append("Job Type: Hardware\n");
        sb.append("Experience Required: 5\n");
        sb.append("Job Hours: 20\n");
        sb.append("Job Penalty: 400\n");
        sb.append("********************\n");

        String expected = sb.toString();
        assertEquals(expected, result);

    }

    @Test
    public void checkSecondConstructorOfProject() {
        ITProject pr2 = new ITProject("Tim", 1000, "./src/RefDefCwk/jobs.txt");
        String prStatus = pr2.toString();
        String expected = "Project Overview : \n****************************************\nManager Name : Tim\nProject Account : 1000.0\nAccount Overdrawn : No\nTeam members : No staff hired\n\n";
        assertEquals(expected, prStatus);
    }

}
