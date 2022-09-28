package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Feedback;
import apu.gym.centre.management.system.Objects.Payment;
import apu.gym.centre.management.system.Objects.Session;
import apu.gym.centre.management.system.Objects.Trainer;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Page8 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 8;
    }
    public String getPageTitle(){
        return "Trainer - Manage Sessions";
    }
    
    public Trainer t = null;
    public void actionPerformed(ActionEvent a){
        try{
            t = APUGymCentreManagementSystem.page3.t;
            //Check Sessions
            if(a.getSource() == check_sessions){
                new check_session();
                
            //Return
            } else if(a.getSource() == Return){
                APUGymCentreManagementSystem.page8.setVisible(false);
                APUGymCentreManagementSystem.page3.setVisible(true);
                
            //Absent Sessions
            } else if(a.getSource() == absent_session){
                String SessionChosen = SelectSession();
                if(SessionChosen == null){
                    throw new Exception();
                }
                String SessionID = SessionChosen.split("\\|")[0];
                Session s = DataIO.find_session(SessionID);
                s.setStatus("Cancelled");
                String absence_reason = JOptionPane.showInputDialog("Enter reason of absence:");
                if(absence_reason == null){
                    throw new Exception();
                }
                Feedback feedback = new Feedback(0,absence_reason);
                s.setFeedback(feedback);
                DataIO.write();
                JOptionPane.showMessageDialog(this, s.getSessionID()+"Successfully Cancelled!");
                
            //Finish Sessions
            } else if(a.getSource() == finish_session){
                String SessionChosen = SelectSession();
                if(SessionChosen == null){
                    throw new Exception();
                }
                String SessionID = SessionChosen.split("\\|")[0];
                Session s = DataIO.find_session(SessionID);
                s.setStatus("Finished");
                //after finish session,ask user the feedback
                String input = JOptionPane.showInputDialog("Please Rate from 1(Very Not Satisfied) to 5(Very Satisfied).");
                if(input == null){
                    throw new Exception();
                }
                int feedback_star = Integer.parseInt(input);
                String feedback_comment = JOptionPane.showInputDialog("Enter Comment of the Feedback:");
                if(feedback_comment == null){
                    throw new Exception();
                }        
                Feedback feedback = new Feedback(feedback_star,feedback_comment);
                s.setFeedback(feedback);
                //Add Trainer Profit to Balance on Finish
                t.setBalance(t.getBalance()+s.getCharge()*DataIO.Trainer_Profit_Rate);
                DataIO.write();
                JOptionPane.showMessageDialog(this, s.getSessionID()+"Successfully Finished!");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button check_sessions, finish_session, absent_session,Return,Return2;
    private JTextArea session;
    public Page8(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        check_sessions = new Button("Check sessions");
        finish_session = new Button("Finish session");
        absent_session = new Button("Absent session");
        Return = new Button("Back");
        
        check_sessions.addActionListener(this);
        finish_session.addActionListener(this);
        absent_session.addActionListener(this);
        Return.addActionListener(this);
        
        add(check_sessions);
        add(finish_session);
        add(absent_session);
        add(Return);
    }
    
    
    public class check_session extends JFrame implements ActionListener{
        public check_session(){
            setTitle("Sessions");
            setSize(1000,600);
            setLocation(350,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());
            Return2 = new Button("Return");
            session=new JTextArea("Here are your session!");
            session.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
            Return2.addActionListener(this);
            Return2.setLocation(250,200);

            //find login trainer,print related session
            for(Session s:DataIO.allSessions){
                if(t.equals(s.getTrainer())){
                    session.append("\n\nSession ID:"+s.getSessionID()+"\nCustomer:"+s.getCustomer().getName()+"\tTrainer:"+s.getTrainer().getName()+
                    "\nDate:"+s.getDate()+"\tTime:"+s.getTime()+"\nDuration:"+s.getDuration()+"\t\tCharge:"+s.getCharge()+
                    "\nFeedback:"+s.getFeedback().getStars()+"\t\tComment:"+s.getFeedback().getComment()+"\nStatus:"+s.getStatus());
                }
            }
            
            add(Return2);
            add(session);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent b){
            if(b.getSource() == Return2){
                setVisible(false);
            }
        }
    }
    
    private String SelectSession(){
        ArrayList<String> AllSessions = new ArrayList<>();
        for (Session s: DataIO.allSessions){
            if(t.equals(s.getTrainer()) && s.getStatus().equals("Scheduled")){
            AllSessions.add(s.toString());}
        }
        String[] all_sessions_choices = AllSessions.toArray(new String[0]);
        String session_chosen = (String) JOptionPane.showInputDialog(null, "Please select the session you want.",
            "The Selection of Session", JOptionPane.QUESTION_MESSAGE, null,
            all_sessions_choices,
            all_sessions_choices[0]);
        return session_chosen;
    }
}


