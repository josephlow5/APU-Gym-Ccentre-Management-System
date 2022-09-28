package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Manager;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Page2 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 2;
    }
    public String getPageTitle(){
        return "Manager - Menu";
    }
    
    public void actionPerformed(ActionEvent a){
        try{
            if(a.getSource() == manage_users){
                APUGymCentreManagementSystem.page2.setVisible(false);
                APUGymCentreManagementSystem.page4.setVisible(true);
            } else if(a.getSource() == manage_sessions){
                APUGymCentreManagementSystem.page2.setVisible(false);
                APUGymCentreManagementSystem.page5.setVisible(true);
            } else if(a.getSource() == report_and_statistics){
                APUGymCentreManagementSystem.page2.setVisible(false);
                APUGymCentreManagementSystem.page6.setVisible(true);
            } else if(a.getSource() == advanced_administration){
                APUGymCentreManagementSystem.page2.setVisible(false);
                APUGymCentreManagementSystem.page7.setVisible(true);
            } else if(a.getSource() == stop){
                Manager login = (Manager) APUGymCentreManagementSystem.login;
                String confirm_password = JOptionPane.showInputDialog("Please enter your password again to confirm:");
                if(login.getPassword().equals(confirm_password)){
                    DataIO.write();
                    JOptionPane.showMessageDialog(this, "Good bye!");
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid password, Please try again!");
                }
            }else if(a.getSource() == back){
                APUGymCentreManagementSystem.page2.setVisible(false);
                APUGymCentreManagementSystem.page1.setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button manage_users, manage_sessions, report_and_statistics, advanced_administration, stop, back;
    public Page2(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        manage_users = new Button("Manage Users");
        manage_sessions = new Button("Manage Sessions");
        report_and_statistics = new Button("Report and Statistics");
        advanced_administration = new Button("Advanced Administration");
        stop = new Button("Stop");
        back = new Button("Logout");
        
        manage_users.addActionListener(this);
        manage_sessions.addActionListener(this);
        report_and_statistics.addActionListener(this);
        advanced_administration.addActionListener(this);
        stop.addActionListener(this);
        back.addActionListener(this);
        
        add(manage_users);
        add(manage_sessions);
        add(report_and_statistics);
        add(advanced_administration);
        add(stop);
        add(back);
    }
}
