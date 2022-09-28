package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Manager;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Trainer;
import apu.gym.centre.management.system.Objects.User;
import static apu.gym.centre.management.system.Pages.Page7.auto_withdraw;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Page1 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 1;
    }
    public String getPageTitle(){
        return "Main Menu";
    }
    
    public void actionPerformed(ActionEvent a){
        try{
            if(a.getSource() == login_manager){
                String login_manager_username = JOptionPane.showInputDialog("Enter username:");
                if(DataIO.find_member("Manager","Username",login_manager_username) != null){
                    Member m = DataIO.find_member("Manager","Username",login_manager_username);
                    User u = (User) m;
                    Manager mng = (Manager) m;
                    String login_manager_password = JOptionPane.showInputDialog("Enter password:");
                    if(mng.getPassword().equals(login_manager_password)){
                        APUGymCentreManagementSystem.login = u;
                        APUGymCentreManagementSystem.page1.setVisible(false);
                        APUGymCentreManagementSystem.page2.setVisible(true);
                    } else{
                        throw new Exception();
                    }
                } else{
                    throw new Exception();
                }
            } else if(a.getSource() == login_trainer){
                String login_trainer_username = JOptionPane.showInputDialog("Enter username:");
                if(DataIO.find_member("Trainer","Username",login_trainer_username) != null){
                    Member m = DataIO.find_member("Trainer","Username",login_trainer_username);
                    User u = (User) m;
                    Trainer t = (Trainer) m;
                    String login_trainer_password = JOptionPane.showInputDialog("Enter password:");
                    if(t.getPassword().equals(login_trainer_password)){
                        APUGymCentreManagementSystem.login = u;
                        APUGymCentreManagementSystem.page1.setVisible(false);
                        APUGymCentreManagementSystem.page3.setVisible(true);
                    } else{
                        throw new Exception();
                    }
                } else{
                    throw new Exception();
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
        
        String auto_withdrawal = auto_withdraw();
        if(auto_withdrawal!=null){
            JOptionPane.showMessageDialog(this, auto_withdrawal);
        }
    }
    
    private Button login_manager, login_trainer;
    public Page1(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        login_manager = new Button("Login as Manager");
        login_trainer = new Button("Login as Trainer");
        
        login_manager.addActionListener(this);
        login_trainer.addActionListener(this);
        
        add(login_manager);
        add(login_trainer);
        setVisible(true);
    }

}
