package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Customer;
import apu.gym.centre.management.system.Objects.Feedback;
import apu.gym.centre.management.system.Objects.Manager;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Payment;
import apu.gym.centre.management.system.Objects.Session;
import apu.gym.centre.management.system.Objects.Trainer;
import apu.gym.centre.management.system.SendEmail;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Page5 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 5;
    }
    public String getPageTitle(){
        return "Manager - Manage Sessions";
    }
    
    public void actionPerformed(ActionEvent a){
        try{
            //Button: Back
            if(a.getSource() == back){
                APUGymCentreManagementSystem.page5.setVisible(false);
                APUGymCentreManagementSystem.page2.setVisible(true);
                
            //Button: Show All Session
            } else if(a.getSource() == check_session){
                String toShow = ShowMembers();
                if(!toShow.equals(Format_toShow_Session)){
                    JTextArea outputTextArea = new JTextArea(toShow.trim());
                    outputTextArea.setOpaque(false);
                    JOptionPane.showMessageDialog(this, outputTextArea);
                }else{
                    JOptionPane.showMessageDialog(this, "No session found!");
                }
                
            //Button: Delete Session         
            } else if(a.getSource() == delete_session){
                String SessionChosen = SelectSession();
                if(SessionChosen == null){
                    throw new Exception();
                }
                String SessionID = SessionChosen.split("\\|")[0];
                Session s = DataIO.find_session(SessionID);
                Manager login = (Manager) APUGymCentreManagementSystem.login;
                String confirm_password = JOptionPane.showInputDialog("Please enter your password again to confirm delete session "+SessionID+":");
                if(login.getPassword().equals(confirm_password)){
                    DataIO.allSessions.remove(s);
                    DataIO.write();
                    JOptionPane.showMessageDialog(this, "Successfully deleted session "+SessionID+"!");
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid password, Please try again!");
                }
            
            //Button: Add Session
            } else if(a.getSource() == add_session){
                String CustomerChosen = SelectCustomer();
                if(CustomerChosen == null){
                    throw new Exception();
                }
                Customer c = (Customer) DataIO.find_member("Customer","Name",CustomerChosen);
                String TrainerChosen = SelectTrainer();
                if(TrainerChosen == null){
                    throw new Exception();
                }
                Trainer t = (Trainer) DataIO.find_member("Trainer","Name",TrainerChosen);
                String Date = JOptionPane.showInputDialog("Enter Date of Session (YYYYMMDD):");
                int Time = Integer.parseInt(JOptionPane.showInputDialog("Enter Time of Session (1-24):"));
                if(Time<0 || Time > 24){
                    throw new Exception();
                }
                int Duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Duration of Session (hours):"));
                if(Duration > 12){  //Assumption: The maximum duration of a session is 12 hours
                    throw new Exception();
                }
                Session s = new Session(c,t,Date,Time,Duration);
                DataIO.allSessions.add(s);
                //Customer Payment to System
                Payment p = new Payment(c.getCustomerID(), "System", s.getCharge());
                DataIO.allPayments.add(p);
                DataIO.write();
                String message = "Successfully booked session for Customer "+CustomerChosen+" and Trainer "+TrainerChosen+
                        " on "+Date+" at "+Time+", a "+Duration+" hour(s) session, charged RM"+s.getCharge()+"!";
                JOptionPane.showMessageDialog(this, message);
                if(DataIO.email_notification){
                    SendEmail.send(c.getEmail(), "Dear Customer "+CustomerChosen+", You have booked a session!", message);
                    SendEmail.send(t.getEmail(), "Dear Trainer "+TrainerChosen+", Someone have booked a session with you!", message);
                }
                
            //Button: Edit Session
            } else if(a.getSource() == edit_session){
                String SessionChosen = SelectSession();
                if(SessionChosen == null){
                    throw new Exception();
                }
                String SessionID = SessionChosen.split("\\|")[0];
                Session s = DataIO.find_session(SessionID);
                String get_data_to_edit =  AskDataToEdit();
                if(get_data_to_edit == null){
                    throw new Exception();
                }
                switch(get_data_to_edit){
                    case "Customer":
                        String CustomerChosen = SelectCustomer();
                        if(CustomerChosen == null){
                            throw new Exception();
                        }
                        Customer c = (Customer) DataIO.find_member("Customer","Name",CustomerChosen);
                        s.setCustomer(c);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+CustomerChosen+"!");
                        break;
                    case "Trainer":
                        String TrainerChosen = SelectTrainer();
                        if(TrainerChosen == null){
                            throw new Exception();
                        }
                        Trainer t = (Trainer) DataIO.find_member("Trainer","Name",TrainerChosen);
                        s.setTrainer(t);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+TrainerChosen+"!");
                        break;
                    case "Date":
                        String Date = JOptionPane.showInputDialog("Enter New Date of Session (YYYYMMDD):");
                        s.setDate(Date);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+Date+"!");
                        break;
                    case "Time":
                        int Time = Integer.parseInt(JOptionPane.showInputDialog("Enter New Time of Session (1-24):"));
                        if(Time<0 || Time > 24){
                            throw new Exception();
                        }
                        s.setTime(Time);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+Time+"!");
                        break;
                    case "Duration":
                        int Duration = Integer.parseInt(JOptionPane.showInputDialog("Enter New Duration of Session (hours):"));
                        if(Duration > 12){  //Assumption: The maximum duration of a session is 12 hours
                            throw new Exception();
                        }
                        s.setDuration(Duration);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+Duration+"!");
                        break;
                    case "Charge":
                        double Charge = Double.parseDouble(JOptionPane.showInputDialog("Enter New Charge of Session (RM):"));
                        s.setCharge(Charge);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+Charge+"!");
                        break;
                    case "Feedback":
                        int feedback_star = SelectFeedbackStar();
                        String feedback_comment = JOptionPane.showInputDialog("Enter Comment of the Feedback:");
                        Feedback feedback = new Feedback(feedback_star,feedback_comment);
                        s.setFeedback(feedback);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+feedback.toDisplayString()+"!");
                        break;
                    case "Status":
                        String status = AskStatus();
                        s.setStatus(status);
                        //Add Trainer Profit to Balance on Finish
                        if(status.equals("Finished")){
                            t = s.getTrainer();
                            t.setBalance(t.getBalance()+s.getCharge()*DataIO.Trainer_Profit_Rate);
                        }
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of Session "+SessionID+" to "+status+"!");
                        break;
                }
                
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button add_session, check_session, edit_session, delete_session, back;
    public Page5(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add_session = new Button("Add Sessions");
        edit_session = new Button("Edit Sessions");
        check_session = new Button("Check All Sessions");
        delete_session = new Button("Delete Sessions");
        back = new Button("Back");
        
        add_session.addActionListener(this);
        edit_session.addActionListener(this);
        check_session.addActionListener(this);
        delete_session.addActionListener(this);
        back.addActionListener(this);
        
        add(add_session);
        add(edit_session);
        add(check_session);
        add(delete_session);
        add(back);
    }
    
    //Functions: Multiple Choices Selections
    private String SelectCustomer(){
        ArrayList<String> AllCustomers = new ArrayList<>();
        for (Member m: DataIO.allMembers){
            if(m instanceof Customer){
                AllCustomers.add(m.getName());
            }
        }
        String[] all_customer_choices = AllCustomers.toArray(new String[0]);
        String customer_chosen = (String) JOptionPane.showInputDialog(null, "Which customer is booking this session?",
            "The Selection of Customer for Session", JOptionPane.QUESTION_MESSAGE, null,
            all_customer_choices,
            all_customer_choices[0]);
        return customer_chosen;
    }
    private String SelectTrainer(){
        ArrayList<String> AllTrainers = new ArrayList<>();
        for (Member m: DataIO.allMembers){
            if(m instanceof Trainer){
                AllTrainers.add(m.getName());
            }
        }
        String[] all_trainer_choices = AllTrainers.toArray(new String[0]);
        String trainer_chosen = (String) JOptionPane.showInputDialog(null, "Which trainer is attending this session?",
            "The Selection of Customer for Session", JOptionPane.QUESTION_MESSAGE, null,
            all_trainer_choices,
            all_trainer_choices[0]);
        return trainer_chosen;
    }
    private String SelectSession(){
        ArrayList<String> AllSessions = new ArrayList<>();
        for (Session s: DataIO.allSessions){
            AllSessions.add(s.toString());
        }
        String[] all_sessions_choices = AllSessions.toArray(new String[0]);
        String session_chosen = (String) JOptionPane.showInputDialog(null, "Please select the session you want.",
            "The Selection of Session", JOptionPane.QUESTION_MESSAGE, null,
            all_sessions_choices,
            all_sessions_choices[0]);
        return session_chosen;
    }
    private String AskDataToEdit(){
        String[] data_to_edit_choices = { "Customer", "Trainer", "Date", "Time", "Duration", "Charge", "Feedback", "Status"};
        String get_data_to_edit = (String) JOptionPane.showInputDialog(null, "Which data to edit?",
            "The Selection of Data to Edit", JOptionPane.QUESTION_MESSAGE, null,
            data_to_edit_choices,
            data_to_edit_choices[0]);
        return get_data_to_edit;
    }
    private int SelectFeedbackStar(){
        String[] all_feedback_star_choices = {"1","2","3","4","5"};
        String feedback_star_chosen = (String) JOptionPane.showInputDialog(null, "Please Rate from 1(Very Not Satisfied) to 5(Very Satisfied).",
            "The Rate of Feedback", JOptionPane.QUESTION_MESSAGE, null,
            all_feedback_star_choices,
            all_feedback_star_choices[0]);
        return Integer.parseInt(feedback_star_chosen);
    }
    private String AskStatus(){
        String[] status_choices = { "Scheduled", "Finished", "Cancelled"};
        String get_status = (String) JOptionPane.showInputDialog(null, "Which Status of Session?",
            "The Selection of Status", JOptionPane.QUESTION_MESSAGE, null,
            status_choices,
            status_choices[0]);
        return get_status;
    }
    
    //Functions: Show All Session Data
    String Format_toShow_Session = "Session ID\t\t\tDate\tTime\tCustomer\t\t\tTrainer\t\t\tDuration\tStatus\tFeedback\n"
                + "==========\t\t\t====\t====\t========\t\t\t=======\t\t\t========\t======\t========\n\n";
    private String ShowMembers(){
        String toShow = Format_toShow_Session;
        for (Session s: DataIO.allSessions){
            toShow += s.toFullString()+ "\n";
        }
        return toShow;
    }
}
