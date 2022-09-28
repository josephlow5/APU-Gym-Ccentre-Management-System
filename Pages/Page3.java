package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Trainer;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Page3 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 3;
    }
    public String getPageTitle(){
        return "Trainer - Menu";
    }
    
    public Trainer t = null;
    public void actionPerformed(ActionEvent a){
        try{
            t = (Trainer) APUGymCentreManagementSystem.login;
            if(a.getSource() == manage_sessions){
                APUGymCentreManagementSystem.page3.setVisible(false);
                APUGymCentreManagementSystem.page8.setVisible(true);
            } else if(a.getSource() == manage_payment){
                APUGymCentreManagementSystem.page3.setVisible(false);
                APUGymCentreManagementSystem.page9.setVisible(true);
            } else if(a.getSource() == manage_profile){
                APUGymCentreManagementSystem.page3.setVisible(false);
                APUGymCentreManagementSystem.page10.setVisible(true);
            } else if(a.getSource() == back){   
                APUGymCentreManagementSystem.page3.setVisible(false);
                APUGymCentreManagementSystem.page1.setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private Button  manage_sessions, manage_payment, manage_profile, back;
    public Page3(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        manage_sessions = new Button("Manage Sessions");
        manage_payment = new Button("Manage payment");
        manage_profile = new Button("Manage profile");
        back = new Button("Logout");
        
        manage_sessions.addActionListener(this);
        manage_payment.addActionListener(this);
        manage_profile.addActionListener(this);
        back.addActionListener(this);
        
        add(manage_sessions);
        add(manage_payment);
        add(manage_profile);
        add(back);
    }
}