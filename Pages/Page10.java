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


public class Page10 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 10;
    }
    public String getPageTitle(){
        return "Trainer - Manage Profile";
    }
    
    public Trainer t = null;
    public void actionPerformed(ActionEvent a){
        try{
            t = APUGymCentreManagementSystem.page3.t;
            //edit trainer name
            if(a.getSource() == edit_name){
                String new_name = JOptionPane.showInputDialog(t.getName()+", please enter your new name:");
                if(new_name == null){
                    throw new Exception();
                }
                t.setName(new_name);
                DataIO.write();
                JOptionPane.showMessageDialog(this, t.getName()+", your name updated!");
                
            //edit trainer email
            } else if(a.getSource() == edit_email){
                String new_email = JOptionPane.showInputDialog("Your email is now: "+t.getEmail()+", please enter your new email:");
                if(new_email == null){
                    throw new Exception();
                }
                t.setEmail(new_email);
                DataIO.write();
                JOptionPane.showMessageDialog(this, "Successfully edited! "+t.getName()+", your email is now: "+t.getEmail());

            //edit trainer username
            } else if(a.getSource() == edit_username){
                String new_username = JOptionPane.showInputDialog("Your username is now: "+t.getUsername()+", please enter your new username:");
                if(new_username == null){
                    throw new Exception();
                }
                t.setUsername(new_username);
                DataIO.write();
                JOptionPane.showMessageDialog(this, "Successfully edited! "+t.getName()+", your username is now: "+t.getUsername());
                
            //edit trainer password
            } else if(a.getSource() == edit_password){
                String confirm_password = JOptionPane.showInputDialog("Please enter your current password.");
                if(!confirm_password.equals(t.getPassword())){
                    throw new Exception();
                }
                String new_password = JOptionPane.showInputDialog("Please enter your new username:");
                if(new_password == null){
                    throw new Exception();
                }
                t.setPassword(new_password);
                DataIO.write();
                JOptionPane.showMessageDialog(this, t.getName()+", your password is edited!");
                
            //print trainer's profile
            } else if(a.getSource() == check_profile){
                JOptionPane.showMessageDialog(this,"Here are your profile info:\n\n"+"Name: "+t.getName()+
                        "\nGender: "+t.getGender()+"\nAge: "+t.getAge()+"\nEmail: "+t.getEmail()+"\nUsername: "+t.getUsername());
                
            //return
            } else if(a.getSource() == Return){
                APUGymCentreManagementSystem.page10.setVisible(false);
                APUGymCentreManagementSystem.page3.setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button edit_name,edit_email,Return,check_profile,edit_username,edit_password;
    public Page10(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        edit_name = new Button("Edit name");
        edit_email = new Button("Edit email");
        edit_username = new Button("Edit username");
        edit_password = new Button("Edit password");
        check_profile = new Button("Check profile");
        Return = new Button("Back");
        
        edit_name.addActionListener(this);
        edit_email.addActionListener(this);
        edit_username.addActionListener(this);
        edit_password.addActionListener(this);
        check_profile.addActionListener(this);
        Return.addActionListener(this);
        
        add(check_profile);
        add(edit_name);
        add(edit_email);
        add(edit_username);
        add(edit_password);
        add(Return);
    }
}
