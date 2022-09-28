package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Customer;
import apu.gym.centre.management.system.Objects.Manager;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Payment;
import apu.gym.centre.management.system.Objects.Session;
import apu.gym.centre.management.system.Objects.Trainer;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Page6 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 6;
    }
    public String getPageTitle(){
        return "Manager - Report and Statistic";
    }
    
    public void actionPerformed(ActionEvent a){
        try{
            if(a.getSource() == user_count){
                String user_count = User_Count();
                JTextArea outputTextArea = new JTextArea(user_count.trim());
                outputTextArea.setOpaque(false);
                JOptionPane.showMessageDialog(this, outputTextArea);
            } else if(a.getSource() == session_count){
                String session_count = Session_Count();
                JTextArea outputTextArea = new JTextArea(session_count.trim());
                outputTextArea.setOpaque(false);
                JOptionPane.showMessageDialog(this, outputTextArea);
            } else if(a.getSource() == payment_report){
                String payment_report = Payment_Report();
                JTextArea outputTextArea = new JTextArea(payment_report.trim());
                outputTextArea.setOpaque(false);
                JOptionPane.showMessageDialog(this, outputTextArea);
            } else if(a.getSource() == back){
                APUGymCentreManagementSystem.page6.setVisible(false);
                APUGymCentreManagementSystem.page2.setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button user_count, session_count, payment_report, back;
    public Page6(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        user_count = new Button("User Statistics");
        session_count = new Button("Session Statistics");
        payment_report = new Button("Payment Report");
        back = new Button("Back");
        
        user_count.addActionListener(this);
        session_count.addActionListener(this);
        payment_report.addActionListener(this);
        back.addActionListener(this);
        
        add(user_count);
        add(session_count);
        add(payment_report);
        add(back);
    }
    
    private String User_Count(){
        int overall_count = 0;
        int customer_count = 0, trainer_count = 0, manager_count = 0;
        int male_count = 0, female_count = 0;
        int sum_of_age = 0;
        for(Member m: DataIO.allMembers){
            overall_count += 1;
            if(m instanceof Customer){
                customer_count += 1;
            }
            if(m instanceof Trainer){
                trainer_count += 1;
            }
            if(m instanceof Manager){
                manager_count += 1;
            }
            if(m.getGender().equals("Male")){
                male_count += 1;
            }
            if(m.getGender().equals("Female")){
                female_count += 1;
            }
            sum_of_age += m.getAge();
        }        
        
        int average_age = sum_of_age/overall_count;
        String output = "There are totally "+overall_count+" members in this system.\n\n"
                +"Within that, there is/are "+customer_count+" customers.\n"
                +"Within that, there is/are "+trainer_count+" trainers.\n"
                +"Within that, there is/are "+manager_count+" managers.\n\n"
                +"Within that, there is/are "+male_count+" male.\n"
                +"Within that, there is/are "+female_count+" female.\n\n"
                +"The average age of all members is this system is "+average_age+" years old.\n";
        return output;
    }
    
    private String Session_Count(){
        int overall_count = 0;
        int feedback_count = 0, sum_of_feedback_stars = 0;
        int scheduled_count=0,finished_count=0,cancelled_count=0;
        int total_duration=0;
        for (Session s: DataIO.allSessions){
            overall_count += 1;
            if(!s.getFeedback().getComment().equals("NOT YET RATED")){
                feedback_count += 1;
                sum_of_feedback_stars += s.getFeedback().getStars();
            }
            switch(s.getStatus()){
                case "Scheduled":
                    scheduled_count += 1;
                    break;
                case "Finished":
                    finished_count += 1;
                    total_duration += s.getDuration();
                    break;
                case "Cancelled":
                    cancelled_count += 1;
                    break;
            }
        }        
        
        String output = "";
        if(overall_count==0){
            output += "There is no sessions yet.\n";
        }else{
            double average_feedback_star = (double)sum_of_feedback_stars/(double)feedback_count;
            average_feedback_star = Math.round(average_feedback_star*100)/100;
            int average_duration = total_duration/finished_count;

            output += "There are totally "+overall_count+" sessions in this system.\n\n"
                    +"Within that, there is/are "+scheduled_count+" scheduled and pending sessions.\n"
                    +"Within that, there is/are "+finished_count+" finished sessions.\n"
                    +"Within that, there is/are "+cancelled_count+" cancelled sessions..\n\n"
                    +"Overall, the average feedback is "+average_feedback_star+" stars in the range of 0.0 - 5.0.\n\n"
                    +"Besides that, a total of "+total_duration+" hours of sessions have been completed.\n"
                    +"In average, each session have a duration of "+average_duration+" hours.\n\n";
        }
                
        return output;
    }
    
    private String Payment_Report(){
        int scheduled_count=0,finished_count=0;
        double total_charge=0.0;
        double received_charge=0.0;
        double scheduled_charge=0.0;
        for (Session s: DataIO.allSessions){
            switch(s.getStatus()){
                case "Scheduled":
                    scheduled_count += 1;
                    received_charge += s.getCharge();
                    scheduled_charge += s.getCharge();
                    break;
                case "Finished":
                    finished_count += 1;
                    received_charge += s.getCharge();
                    break;
            }
            total_charge += s.getCharge();
        }        
        double average_charge = received_charge/(double)(scheduled_count+finished_count);
        average_charge = Math.round(average_charge);
        
        double received = 0.0;
        double payout = 0.0;
        for (Payment p: DataIO.allPayments){
            if(p.getFrom().equals("System")){
                payout += p.getAmount();
            }
            if(p.getTo().equals("System")){
                received += p.getAmount();
            }
        }
        double left = received_charge - payout;
        
        double pending_to_pay = 0.0;
        for (Member m: DataIO.allMembers){
            if(m instanceof Trainer){
                Trainer t = (Trainer) m;
                if(t.getBalance()>0){
                    pending_to_pay += t.getBalance();
                }
            }
        }
        
        double after_trainers = left-pending_to_pay;
        double unrealized = scheduled_charge*(1-DataIO.Trainer_Profit_Rate);
        unrealized = Math.round(unrealized*100)/100;
        double profit = after_trainers - scheduled_charge;
        double overall = unrealized+profit;
        
        String output = "";
        if(total_charge == 0.0){
            output += "There is no payment made yet.\n";
        }else{
            output += "The total sales made is RM"+total_charge+".\n";
            if(received_charge != total_charge){
                double refund = total_charge - received_charge;
                output += "However, the actual payment received is RM"+received_charge+",\n"+
                        "Due to refund of RM"+refund+" to cancelled sessions.\n";
            }
            output += "\nIn short, the average sale of each session is RM"+average_charge+".\n\n";

            output += "The system has paid RM"+payout+" to trainers.\n";
            output += "The money left in this system now is RM"+left+".\n";
            output += "Waiting for the trainers to withdraw RM"+pending_to_pay+".\n\n";
            
            output += "After paying to trainers, the system will left RM"+after_trainers+".\n";
            output += "Within that, RM"+scheduled_charge+" is the collected charges of ongoing sessions.\n";
            output += "The ongoing sessions are expected to have a unrealized profit of RM"+unrealized+".\n\n";
            
            output += "As for finished sessions, the realized profit made in this system is RM"+profit+".\n";
            output += "To conclude, the overall profit made in this system is RM"+overall+".\n";
        }
                
        return output;
    }
}
