package RefDefCwk;

import java.io.*;
import java.util.*;

/**
 * provides a command line interface
 *
 * @author A.A.Marczyk
 * @version 20/04/23
 */
public class ITProjectUI {
    private static BITS man1;
    private static Scanner myIn = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        String managerName;

        try {
            System.out.println("Enter manager's name");
            String mg = myIn.nextLine();
            man1 = new ITProject(mg, 1000); // create
            choice = 100;
            while (choice != 0) {
                choice = getMenuItem();
                if (choice == 1) {
                    System.out.println(man1.toString());
                } else if (choice == 2) {
                    System.out.println(man1.getAllAvailableStaff());
                } else if (choice == 3) {
                    System.out.println("Enter Staff name");
                    String nme = (myIn.nextLine()).trim();
                    if (!man1.isOverdrawn()) {
                        System.out.println(man1.hireStaff(nme));
                    } else {
                        System.out.println("Project is overdrawn");
                    }
                } else if (choice == 4) // implement this option
                {
                    System.out.println(man1.getTeam());
                } else if (choice == 5) {
                    System.out.println(man1.getAllJobs());
                } else if (choice == 6) // implement this option
                {
                    System.out.println("Enter the job number");
                    int jobNo = Integer.parseInt((myIn.nextLine()).trim());
                    String jobOutcome = man1.doJob(jobNo);
                    System.out.println(jobOutcome);

                } else if (choice == 7) {
                    System.out.println("Enter Staff name");
                    String nme = (myIn.nextLine()).trim();
                    if (man1.isInTeam(nme)) {
                        System.out.println(man1.staffRejoinTeam(nme));
                    }
                }

//                 Only if implementing Task 2.5
                else if (choice == 8) {
                    System.out.println("Write to file");
                    System.out.println("Enter filename");
                    String filname = (myIn.nextLine()).trim();
                    man1.saveITProject(filname);
                    System.out.println("ITProject saved to " + filname + " successfully !");
                } else if (choice == 9) // Task 6.6 only
                {
                    System.out.println("Restore from file");
                    System.out.println("Enter filename");
                    String fname = (myIn.nextLine()).trim();
                    man1 = man1.restoreITProject(fname);
                    System.out.println(man1.getTeam());
                }
//                else if (choice ==10)//demo
//                {
//                    System.out.println(man1.getAccount());
//			    System.out.println("Is overdrawn: " + man1.isOverdrawn());
//                    System.out.println(man1.getTeam());
//                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Thank-you");
    }

    private static int getMenuItem() throws IOException {
        int choice = 100;
        System.out.println("Main Menu");
        System.out.println("0. quit");
        System.out.println("1. list project information");
        System.out.println("2. list staff available for hire");
        System.out.println("3. hire staff for team");
        System.out.println("4. list staff in the team");
        System.out.println("5. list jobs ");
        System.out.println("6. do a job");
        System.out.println("7. staff rejoin team from holiday");
        //Only if implementing Task 6.6
        System.out.println("8. save game");
        System.out.println("9. restore game");


        while (choice < 0 || choice > 9) {
            System.out.println("Enter the number of your choice");
            choice = myIn.nextInt();
        }
        myIn.nextLine();
        return choice;
    }
}