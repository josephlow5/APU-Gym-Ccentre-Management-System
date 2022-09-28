package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Payment;
import apu.gym.centre.management.system.Objects.Trainer;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Page7 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 7;
    }
    public String getPageTitle(){
        return "Manager - Advanced administration";
    }
    
    public void actionPerformed(ActionEvent a){
        try{
            if(a.getSource() == monthly_withdrawal){
                if(DataIO.auto_withdrawal == true){
                    DataIO.auto_withdrawal = false;
                    JOptionPane.showMessageDialog(this, "Auto Monthly Withdrawal disabled!");
                }else{
                    DataIO.auto_withdrawal = true;
                    JOptionPane.showMessageDialog(this, "Auto Monthly Withdrawal enabled! Trainers will automatically receive their payment on each 15th!"); 
                    String auto_withdrawal = auto_withdraw();
                    if(auto_withdrawal!=null){
                        JOptionPane.showMessageDialog(this, auto_withdrawal);
                    }
                }
                DataIO.write();
            } else if(a.getSource() == email_notification){
                if(DataIO.email_notification == true){
                    DataIO.email_notification = false;
                    JOptionPane.showMessageDialog(this, "Auto Email Notification disabled!");
                }else{
                    DataIO.email_notification = true;
                    JOptionPane.showMessageDialog(this, "Auto Email Notification enabled! Users will automatically receive notification via email now!"); 
                }
                DataIO.write();
            } else if(a.getSource() == peak_hour_mode){
                if(DataIO.HOUR_Rate == 40.0){
                    DataIO.HOUR_Rate = 25.0;
                    JOptionPane.showMessageDialog(this, "Peak Hour Mode disabled!");
                }else{
                    DataIO.HOUR_Rate = 40.0;
                    JOptionPane.showMessageDialog(this, "Peak Hour Mode enabled!"); 
                }
                DataIO.write();
            } else if(a.getSource() == back){
                APUGymCentreManagementSystem.page7.setVisible(false);
                APUGymCentreManagementSystem.page2.setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button monthly_withdrawal, email_notification, peak_hour_mode, back;
    public Page7(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        monthly_withdrawal = new Button("Auto Monthly Withdrawal for Trainer");
        email_notification = new Button("Auto Email Notification");
        peak_hour_mode = new Button("Peak Hour Mode");
        back = new Button("Back");
        
        monthly_withdrawal.addActionListener(this);
        email_notification.addActionListener(this);
        peak_hour_mode.addActionListener(this);
        back.addActionListener(this);

        add(monthly_withdrawal);
        add(email_notification);
        add(peak_hour_mode);
        add(back);
    }
    
    
    public static String auto_withdraw(){
        if(DataIO.auto_withdrawal){
            Date date = new Date();
            String day = String.format(Locale.UK, "%td", date);
            if(day.equals("15")){
                int count = 0;
                double amount = 0.0;
                for(Member m:DataIO.allMembers){
                    if (m instanceof Trainer){
                        Trainer t = (Trainer) m;
                        double withdraw = t.getBalance();
                        if(withdraw > 0.0){
                            t.setBalance(t.getBalance()-withdraw);
                            Payment p = new Payment("System", t.getTrainerID(), withdraw);
                            DataIO.allPayments.add(p);
                            DataIO.write(); 
                            count += 1;
                            amount += withdraw;
                        }                    
                    }
                }
                if(count!=0){
                    return "Successfully automatically withdraw RM"+amount+" for "+count+" trainer(s)!"; 
                }
            }
        }
        return null;
    }
}
