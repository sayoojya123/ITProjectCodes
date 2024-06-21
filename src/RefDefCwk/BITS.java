package RefDefCwk; 
/**
 * This class specifies the behaviour expected from the BITS system
 as required for 5COM2007 Referred/Deferred Cwk - June 2024 
 specified in the BITS interface
 * @author A.A.Marczyk 
 * @version 25/04/2024
 */
public interface BITS
{
    
//**************** BITS ************************** 
    
    /**Returns a String representation of the state of the project,
     * including the name of the trainee manager, state of the project account,
     * whether overdrawn or not, the staff in the team (or, "No staff" 
     * if team is empty)
     * @return a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, and the staff currently in the 
     * team,(or, "No staff" if team is empty)
     **/
    public String toString();
    
    
    /** Returns the amount of money in the account
     * @returns the amount of money in the account
     */
    public double getAccount();
    
    /** Returns true if project account <=0 and the team has no staff 
     * who can leave. 
     * @returns true if project account <=0 and the team has no staff 
     * who can leave. 
     */
    public boolean isOverdrawn();

    //********************** All Jobs************************* 
    /** Returns true if the number represents a job
     * @param num is the reference number of the job
     * @returns true if the reference number represents a job
     **/
     public boolean isJob(int num);

    
    /** Returns a String representation of all jobs 
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs();
        
    /** Returns a String with information about specified job
     * @param no - number of the specified job
     * @return returns a String representation of all jobs
     **/
    public String getJob(int no);
    
//*********************** All Staff *************************   
    
    /** Returns details of a staff member with the given name, 
     * (staff may be in or out of the team)
     * @param name the name of the required staff member
     * @return details of a staff member with the name specified 
     * in the parameter
     **/
    public String getStaff(String name);
    
    /**Returns a String representation of the details of all staff 
     * available for hire
     * @return a String representation of the details of all staff 
     * available for hire
     **/
    public String getAllAvailableStaff();
    
  
 // ***************** Team Staff ************************   

    /** Allows staff to be added to the team, if there is enough  
     * money in the account for the retainer.The hired staff member's 
     * state is set to "working" and their retainer is deducted from
     * the project account. Return the result of the hire; all messages 
     * should include the staff name and state of the project account
     * @param name is the name of the staff member
     * @return "Not found" if staff not found, "Already hired" if staff 
     * is already hired, "Not enough money" if not enough money in the 
     * account,"Hired" if staff are hired.All messages should include 
     * the staff name and state of the project account
     **/        
    public String hireStaff(String name);
    
        
   /** Returns true if the staff with the specified name 
     * is in the team, false otherwise.
     * @param name is the name of the staff
     * @return true if the staff with the name is in the team, 
     * false otherwise.
     **/
    public boolean isInTeam(String name);

    
    /**Returns a String representation of the staff in the project team
     * (including those on holiday), or the message "No staff hired"
     * @return a String representation of the staff in the project team
     **/
    public String getTeam();


// ***************** Simulation ************************ 
    /** Retrieves the job represented by the job number, or returns "No 
     * such job".If job exists, finds a staff member in the team who can 
     * do the job.The results of doing a job will be one of the following: 
     * " Job completed by..." - adds the cost of the job to account and include 
     *                           name of staff staff go "on holiday", 
     * " Job not completed as no staff available" - deduct job penalty from account,
     * " Job not completed due to staff inexperience" - deduct penalty from 
     *                           the account.
     * If a job is not completed and the project account becomes negative, 
     * add "ITProject is overdrawn " to the output. 
     * @param jbNo is the reference number of the job
     * @return a String showing the result of doing the job(as above)
     */ 
    public String doJob(int jbNo);

    /**Staff rejoin the team after holiday by setting state to "working" 
     * @param the name of the staff rejoining the team after holiday
     */
    public String staffRejoinTeam(String name);
         
//     ***************   task 2.5 file write/read  *********************
    /** Writes the ITProject object to the specified file using serialization
     * @param fname name of file to which schedule is written
     */
    public void saveITProject(String filename);


    /** Reads the ITProject object from specified file using serialization and restores it
     * @param fname name of file from which schedule is read
     */
    public ITProject restoreITProject(String filename);

    /** Reads jobs from a text file such as "jobs.txt" - provided
     *  @filename - name of the text file
     */
    public void readJobs(String filename);
    
    
}
   