package RefDefCwk;

import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from the BITS system
 * as required for 5COM2007 Referred/Deferred Cwk - June 2024 and
 * specified in the BITS interface
 *
 * @author - Anisha Mariya Siby , SRN - 21082593
 * @author -
 * (or leave blank, if working individually)
 * @author A.A.Marczyk
 * @version 24/04/2024
 */
public class ITProject implements BITS, Serializable {
    //Note: ArrayLists instead of HashMaps is OK, but then there will be code differences

    private String name;
    private double account;
    private List<Staff> allStaffs = new ArrayList<>();
    private List<Staff> membersInTeam = new ArrayList<>();
    private List<Job> allJobs = new ArrayList<>();


//**************** BITS ************************** 

    /**
     * Constructor requires the name of the trainee manager and initial budget. Staff
     * and jobs are also set up,  with all staff set to "available" for hire.
     *
     * @param manager the name of the trainee manager running the simulation
     * @param budget  the initial budget allocated to the project account
     */
    public ITProject(String manager, double budget) {
        name = manager;
        account = budget;
        setupStaff();
        setupJobs();
    }

    /**
     * Constructor requires the name of the trainee manager, initial budget
     * and name of jobs file. Staff are also set up,  with all staff set to
     * "available" for hire. Jobs are "read from a file
     *
     * @param manager the name of the trainee manager running the simulation
     * @param budget  the initial budget allocated to the project account
     * @param jobfile the name of the jobs file
     */
    public ITProject(String manager, double budget, String jobfile) {
        name = manager;
        account = budget;
        setupStaff();
        readJobs(jobfile);
    }

    /**
     * Returns a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, the staff in the team (or, "No staff"
     * if team is empty)
     *
     * @return a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, and the staff currently in the
     * team,(or, "No staff" if team is empty)
     **/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project Overview : ").append("\n");
        sb.append("*".repeat(40)).append("\n");
        sb.append("Manager Name : ").append(name).append("\n");
        sb.append("Project Account : ").append(account).append("\n");
        String accountOverdrawn = isOverdrawn() ? "Yes" : "No";
        sb.append("Account Overdrawn : ").append(accountOverdrawn).append("\n");
        String teamNames = getTeam();
        sb.append("Team members : ").append(teamNames).append("\n");

        return sb.toString();
    }


    /**
     * returns the amount of money in the account
     *
     * @returns the amount of money in the account
     */
    public double getAccount() {
        return account;
    }


    /**
     * returns true if project account <=0 and the team has no staff
     * who can leave.
     *
     * @returns true if project account <=0 and the team has no staff
     * who can leave.
     */
    public boolean isOverdrawn() {
        return account <= 0;
    }

//**********************Jobs************************* 

    /**
     * returns true if the number represents a job
     *
     * @param num is the reference number of the job
     * @returns true if the reference number represents a job
     **/
    public boolean isJob(int num) {
        for (Job job : allJobs) {
            if (num == job.getJobNum()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Provides a String representation of all jobs
     *
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs() {
        String s = "\n************ All Jobs ************\n\n";
        for (Job job : allJobs) {
            s += job.toString();
            s += "*".repeat(20) + "\n";
        }
        return s;
    }

    /**
     * Returns a String with information about specified job
     *
     * @param no - number of the specified job
     * @return returns a String representation of all jobs
     **/
    public String getJob(int no) {
        for (Job job : allJobs) {
            if (no == job.getJobNum()) {
                return job.toString();
            }
        }
        return "No such Job";
    }
    //*********************** Staff *************************    

    /**
     * Returns details of a staff member with the given name
     * (staff may be in or out of the team)
     *
     * @param name the name of the required staff member
     * @return details of a staff member with the name specified
     * in the parameter
     **/
    public String getStaff(String name) {
        for (Staff staff : allStaffs) {
            if (name.equalsIgnoreCase(staff.getName())) {
                return staff.toString();
            }
        }

        return "\nNo such staff";
    }


    /**
     * Returns a String representation of all staff available for hire
     *
     * @return a String representation of all staff available for hire
     **/
    public String getAllAvailableStaff() { //assumes allStaff is a Hashmap
        String s = "************ Staff for Hire********\n";

        // robustness check : respond when no staff is available to hire
        int staffAvailableCounter = 0;
        for (Staff staff : allStaffs) {
            if (staff.getStaffState() == StaffState.AVAILABLE) {
                s += staff.getName();
                s += ", ";
                staffAvailableCounter += 1;
            }
        }
        if (staffAvailableCounter == 0) {
            s += "No staff available";
        } else {
            s = s.substring(0, s.length() - 2) + "\n";
        }

        return s;
    }

    // ***************** Team Staff ************************

    /**
     * Allows staff to be added to the team, if there is enough
     * money in the account for the retainer.The hired staff member's
     * state is set to "working" and their retainer is deducted from
     * the project account. Return the result of the hire; all messages
     * should include the staff name and state of the project account
     *
     * @param name is the name of the staff member
     * @return "Not found" if staff not found, "Already hired" if staff
     * is already hired, "Not enough money" if not enough money in the
     * account,"Hired" if staff are hired.All messages should include
     * the staff name and state of the project account
     **/
    public String hireStaff(String name) {
        String result = "Staff " + name;

        Staff teamMember = new Staff();
        if (!getStaff(name).equals("\nNo such staff")) {
            teamMember = retreiveStaffDetails(name);
            if (teamMember.getStaffState() == StaffState.AVAILABLE) {
                if (account >= teamMember.getRetainer()) {
                    teamMember.setStaffState(StaffState.WORKING);
                    account -= teamMember.getRetainer();
                    membersInTeam.add(teamMember);
                    result += " hired";

                } else {
                    result += " cannot be hired. Not enough money";

                }
            } else {
                result += " already hired";
            }
        } else {
            result += " not found";
        }

        return result + "\nAccount = £ " + account + "\n";
    }

    /**
     * Returns a Staff object given a name of the
     * staff in lower or upper case
     *
     * @param name Name of the staff whose details needs
     *             to be retrieved
     * @return a Staff object
     */
    public Staff retreiveStaffDetails(String name) {
        Staff st = new Staff();
        for (Staff staff : allStaffs) {
            if (name.equalsIgnoreCase(staff.getName())) {
                st = staff;
            }
        }
        return st;
    }

    /**
     * Returns true if the staff with the specified name
     * is in the team, false otherwise.
     *
     * @param name is the name of the staff
     * @return true if the staff with the name is in the team,
     * false otherwise.
     **/
    public boolean isInTeam(String name) {

        for (Staff st : membersInTeam) {
            if (st.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns a String representation of the staff in the project team
     * (including those on holiday), or the message "No staff hired"
     *
     * @return a String representation of the staff in the project team
     **/
    public String getTeam() {
        String s = "************ TEAM ********\n";
        StringBuilder sb = new StringBuilder();
        int staffCounter = 0;
        for (Staff staff : membersInTeam) {
            sb.append(staff.getName()).append(",");
            staffCounter += 1;
        }
        if (staffCounter == 0) {
            s = "No staff hired\n";
        } else {
            s += sb.toString();
            if (s.endsWith(",")) {
                s = s.substring(0, s.length() - 1) + "\n";
            }
        }
        return s;
    }


    /**
     * Retrieves the job represented by the job number, or returns "No
     * such job".If job exists, finds a staff member in the team who can
     * do the job.The results of doing a job will be one of the following:
     * " Job completed by..." - adds the cost of the job to account and include name of staff
     * staff go "on holiday",
     * " Job not completed as no staff available" - deduct job penalty from account,
     * " Job not completed due to staff inexperience" - deduct penalty from
     * the account.
     * If a job is not completed and the project account becomes negative,
     * add "ITProject is overdrawn " to the output.
     *
     * @param jbNo is the reference number of the job
     * @return a String showing the result of doing the job(as above)
     */
    public String doJob(int jbNo) {
        String outcome = "\nJob No : " + jbNo + "\n";
        Job job;
        if (!getJob(jbNo).equals("No such Job")) {
            job = getAJob(jbNo);
            int inexperienceCounter = 0;
            int unavailabilityCounter = 0;
            Staff staff = getStaffForJob(job);
            if (staff != null) {
                boolean inExperienced = staff.getExperience() < job.getExpRequired();
                if (inExperienced) {
                    outcome += "Job not completed due to staff inexperience. ";
                    account -= job.getJobPenalty();
                    if (account < 0) {
                        outcome += "ITProject is overdrawn";
                    }
// staff inexperienced
                } else {
                    outcome += "Job completed by " + staff.getName();
                    account += job.getJobHours() * staff.getHourlyRate();
                    staff.setStaffState(StaffState.ONHOLIDAY);
                }

            } else {
//                no staff found
                outcome += "Job not completed as no staff available. ";
                account -= job.getJobPenalty();
                if (account < 0) {
                    outcome += "ITProject is overdrawn";
                }
            }
        } else {
            outcome += "No such Job";
        }
        return outcome;
    }

    /**
     * Staff rejoin the team after holiday by setting state to "working"
     *
     * @param the name of the staff rejoining the team after holiday
     * @return the outcome of the staff rejoin process
     */
    public String staffRejoinTeam(String name) {

        for (Staff staff : membersInTeam) {
            if (staff.getName().equalsIgnoreCase(name)) {
                if (staff.getStaffState().equals(StaffState.ONHOLIDAY)) {
                    staff.setStaffState(StaffState.WORKING);
                    return name + " rejoined the team after holiday";
                } else {
                    return name + " is already available on team and is not on holiday";
                }

            }
        }
        return name + " not in team so can't return from holiday";
    }
//****************** private methods for Task 6.1 functionality*******************

    private void setupStaff() {
        allStaffs.add(new Staff("Amir", 2, 300.0, "false", "false", 30.0, "Designer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Bela", 2, 100.0, "false", "false", 30.0, "Engineer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Ceri", 4, 250.0, "true", "false", 40.0, "Engineer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Dana", 2, 200.0, "false", "true", 20.0, "Programmer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Eli", 7, 200.0, "false", "true", 20.0, "Programmer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Firat", 6, 300.0, "false", "true", 90.0, "Designer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Gani", 2, 200.0, "false", "true", 20.0, "Programmer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Hui", 8, 450.0, "true", "false", 40.0, "Engineer", StaffState.AVAILABLE));
        allStaffs.add(new Staff("Jaga", 4, 300.0, "false", "true", 60.0, "Designer", StaffState.AVAILABLE));
    }


    private void setupJobs() {

        allJobs.add(new Job(100, "Design", 3, 10, 200));
        allJobs.add(new Job(101, "Hardware", 3, 20, 150));
        allJobs.add(new Job(102, "Software", 3, 30, 100));
        allJobs.add(new Job(103, "Design", 9, 25, 250));
        allJobs.add(new Job(104, "Software", 7, 15, 350));
        allJobs.add(new Job(105, "Hardware", 8, 35, 300));
        allJobs.add(new Job(106, "Hardware", 5, 20, 400));
    }

// May be helpful    

    /**
     * Returns a Job give a job number
     *
     * @param num number of job to be retrieved
     * @return Job object corresponding to job number
     */
    private Job getAJob(int num) {
        Job jb = null;
        int nu;
        for (Job job : allJobs) {
            if (job.getJobNum() == num) {
                jb = job;
            }
        }
        return jb;
    }

    /**
     * Returns the details of a matching staff given a job
     *
     * @param jbb job to be performed
     * @return Details of the first found staff that could do the job
     */
    private Staff getStaffForJob(Job jbb) {
        boolean staffFound = false;
        for (Staff staff : membersInTeam) {
            if (staff.getStaffState().equals(StaffState.WORKING)) {
                switch (jbb.getJobType()) {
                    case "Software":
                        if (staff.getRole().equals("Engineer")) {
                            staffFound = false;
                        } else if (staff.getRole().equals("Programmer")) {
                            staffFound = true;
                        } else {
                            if (staff.canProgram()) {
                                staffFound = true;
                            } else {
                                staffFound = false;
                            }
                        }
                        break;

                    case "Hardware":
                        if (staff.getRole().equals("Engineer")) {
                            staffFound = true;
                        } else if (staff.getRole().equals("Programmer")) {
                            staffFound = false;
                        } else {
                            staffFound = false;
                        }
                        break;

                    case "Design":
                        if (staff.getRole().equals("Engineer")) {
                            staffFound = false;
                        } else if (staff.getRole().equals("Programmer")) {
                            staffFound = true;
                        } else {
                            staffFound = true;
                        }
                        break;
                }
            }
            if (staffFound) {
                return staff;
            }
        }

        return null;
    }


// Task 2.5
    // ***************   file write/read  *********************

    /**
     * Writes the ITProject object to the specified file using serialization
     *
     * @param filename name of file to which schedule is written
     */
    public void saveITProject(String filename) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//    
//    
//    

    /**
     * Reads ITProject from specified file using serialization and restores it
     *
     * @param filename name of file from which schedule is read
     */
    public ITProject restoreITProject(String filename) {   // uses object serialisation
        ITProject yyy = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            yyy = (ITProject) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return yyy;
    }

    /**
     * Reads jobs from a text file such as "jobs.txt" - provided
     *
     * @filename - name of the text file
     */
    public void readJobs(String fname) {
        List<Job> jbs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fname));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] jobFields = line.split(",");
                int jobNum = Integer.parseInt(jobFields[0]);
                String jobType = jobFields[1];
                int expRequired = Integer.parseInt(jobFields[2]);
                int jobHours = Integer.parseInt(jobFields[3]);
                int jobPenalty = Integer.parseInt(jobFields[4]);

                Job job = new Job(jobNum, jobType, expRequired, jobHours, jobPenalty);
                jbs.add(job);
            }
            allJobs = jbs;
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
