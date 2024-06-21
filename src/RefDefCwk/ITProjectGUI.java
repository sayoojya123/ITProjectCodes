package RefDefCwk;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Provide a GUI interface for the simulation
 * 
 * @author A.A.Marczyk
 * @version 20/04/24
 */
public class ITProjectGUI 
{
    private BITS mg = new ITProject("Mary",1000);
    private JFrame myFrame = new JFrame("Project GUI");
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton listBtn = new JButton("List all Staff");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();
    private JButton hireStaffBtn = new JButton("Hire Staff");
    private JButton listTeamBtn = new JButton("List Team");
    private JButton clearBtn = new JButton("Clear");


    public static void main(String[] args)
    {
        new ITProjectGUI();
    }
    
    
    public ITProjectGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
        listing.setVisible(true);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);

        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(6,1));
        eastPanel.add(listBtn);
        eastPanel.add(hireStaffBtn);
        eastPanel.add(listTeamBtn);
        eastPanel.add(clearBtn);
        eastPanel.add(quitBtn);

        listBtn.addActionListener(new ListHandler());
        hireStaffBtn.addActionListener(new HireStaffHandler());
        listTeamBtn.addActionListener(new ListTeamHandler());
        clearBtn.addActionListener(new ClearTextHandler());
        quitBtn.addActionListener(new QuitHandler());

        listBtn.setVisible(true);
        hireStaffBtn.setVisible(true);
        listTeamBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Jobs");
        menubar.add(fileMenu);
        
        
        JMenuItem view = new JMenuItem("View project state");
        view.addActionListener(new StateHandler());
        fileMenu.add(view);

        JMenuItem listJobsItem = new JMenuItem("List all jobs");
        listJobsItem.addActionListener(new ListJobsHandler());
        fileMenu.add(listJobsItem);

        JMenuItem doAJobItem = new JMenuItem("Do a Job");
        doAJobItem.addActionListener(new DoAJobHandler());
        fileMenu.add(doAJobItem);

        
            
    }

// Menu item handlers

    private class StateHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = mg.toString();
            listing.setText(result);

        }
    }
    

// Button handlers
    
    private class ListHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            listing.setVisible(true);
            String xx = mg.getAllAvailableStaff();
            listing.setText(xx);
        }
    }
   
    
    private class QuitHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int answer = JOptionPane.showConfirmDialog(myFrame,
                "Are you sure you want to quit?","Finish",
                JOptionPane.YES_NO_OPTION);
            // closes the application
            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0); //closes the application
            }              
        }
    }


    private class ListJobsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String jobs = mg.getAllJobs();
            listing.setText(jobs);


        }
    }

    private class DoAJobHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int jobNum = Integer.parseInt(JOptionPane.showInputDialog("Enter Job Number : "));
            String jobOutcome = mg.doJob(jobNum);
            JOptionPane.showMessageDialog(myFrame, jobOutcome);
        }
    }

    private class HireStaffHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String staffName = JOptionPane.showInputDialog("Enter Staff Name : ");
            String hireOutcome = mg.hireStaff(staffName);
            JOptionPane.showMessageDialog(myFrame, hireOutcome);

        }
    }

    private class ListTeamHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String teamMembers = mg.getTeam();
            listing.setText(teamMembers);

        }
    }

    private class ClearTextHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            listing.setText("");
        }
    }
}
   
