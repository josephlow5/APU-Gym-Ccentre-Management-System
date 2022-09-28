package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Payment;
import apu.gym.centre.management.system.Objects.Trainer;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Page9 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 9;
    }
    public String getPageTitle(){
        return "Trainer - Manage Payment";
    }
    
    public Trainer t = null;
    public void actionPerformed(ActionEvent a){
        try{
            t = APUGymCentreManagementSystem.page3.t;
            //Check Balance
            if(a.getSource() == check_balance){
                JOptionPane.showMessageDialog(this,t.getName()+", your balance is now: RM "+t.getBalance());
                
            //Withdraw Balance
            } else if(a.getSource() == withdraw_balance){
                String input = JOptionPane.showInputDialog("Your balance is now: "+t.getBalance()+", \nPlease enter how much you want to withdraw: ");
                if(input == null){
                    throw new Exception();
                }
                double withdraw = Integer.parseInt(input);
                if(t.getBalance() < withdraw){
                    throw new Exception();
                }
                boolean withdrawal = withdraw(t,withdraw);
                if(!withdrawal){
                    throw new Exception();
                }
                
            //Check Record
            } else if(a.getSource() == check_record){
                new check_record();
                
            //Return
            } else if(a.getSource() == Return){
                APUGymCentreManagementSystem.page9.setVisible(false);
                APUGymCentreManagementSystem.page3.setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button check_balance,withdraw_balance,check_record,Return,Return2;
    private JTextArea session;
    public Page9(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        check_balance = new Button("Check balance");
        withdraw_balance = new Button("Withdraw balance");
        check_record = new Button("Check record");
        Return = new Button("Back");
        
        check_balance.addActionListener(this);
        withdraw_balance.addActionListener(this);
        check_record.addActionListener(this);
        Return.addActionListener(this);
        
        add(check_balance);
        add(withdraw_balance);
        add(check_record);
        add(Return);    
    }

    public class check_record extends JFrame implements ActionListener{
        public check_record(){
            setTitle("Payment record");
            setSize(700,500);
            setLocation(350,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());
            Return2 = new Button("Return");
            session=new JTextArea("Here are your payment record!");
            session.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
            Return2.addActionListener(this);
            Return2.setLocation(250,200);

            add(Return2);
            add(session);

            //find trainer's payment record
            for(Payment p:DataIO.allPayments){
                if(t.getTrainerID().equals(p.getTo())){
                    session.append("\n\nPayment ID:"+p.getPaymentID()+"\nFrom:"+p.getFrom()+"\t\tTo:"+t.getName()+
                    "\nAmount:"+p.getAmount()+"\t\tDate:"+p.getDate());
                }
            }
            setVisible(true);
        }
        public void actionPerformed(ActionEvent b){
            if(b.getSource() == Return2)
                setVisible(false);
        }
    }
    
    public boolean withdraw(Trainer t, double withdraw){
        try{
            t.setBalance(t.getBalance()-withdraw);
            //add withdraw record into payment
            Payment p = new Payment("System", t.getTrainerID(), withdraw);
            DataIO.allPayments.add(p);
            DataIO.write();
            JOptionPane.showMessageDialog(this,"Successfully withdraw RM "+withdraw+".\nYour balance is now: RM "+t.getBalance()); 
        }catch(Exception e){
            return false;
        }
        return true;
    }

}

