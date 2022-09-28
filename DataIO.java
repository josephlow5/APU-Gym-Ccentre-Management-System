package apu.gym.centre.management.system;

import apu.gym.centre.management.system.Objects.Customer;
import apu.gym.centre.management.system.Objects.Feedback;
import apu.gym.centre.management.system.Objects.Manager;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Payment;
import apu.gym.centre.management.system.Objects.Session;
import apu.gym.centre.management.system.Objects.Trainer;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {
    public static ArrayList<Member> allMembers = new ArrayList<Member>();
    public static ArrayList<Session> allSessions = new ArrayList<Session>();
    public static ArrayList<Payment> allPayments = new ArrayList<Payment>();
    
    public static double HOUR_Rate = 25.0;
    public static boolean auto_withdrawal = false;
    public static boolean email_notification = false;
    public static double Trainer_Profit_Rate = 0.94;
    
    public static void read(){
        try{
            Scanner users_scanner = new Scanner(new File("users.txt"));
            while(users_scanner.hasNext()){
                String Name = users_scanner.nextLine();
                String Email = users_scanner.nextLine();
                String Gender = users_scanner.nextLine();
                int Age = Integer.parseInt(users_scanner.nextLine());
                String type = users_scanner.nextLine();
                if(type.equals("Customer")){
                    String CustomerID = users_scanner.nextLine();
                    Customer c = new Customer(Name,Email,Gender,Age,CustomerID);
                    allMembers.add(c);
                }
                else if(type.equals("Trainer")){
                    String TrainerID = users_scanner.nextLine();
                    double Balance = Double.parseDouble(users_scanner.nextLine());
                    String Username = users_scanner.nextLine();
                    String Password = users_scanner.nextLine();
                    Trainer t = new Trainer(Name,Email,Gender,Age,Username,Password,TrainerID,Balance);
                    allMembers.add(t);
                }
                else if(type.equals("Manager")){
                    String ManagerID = users_scanner.nextLine();
                    String Username = users_scanner.nextLine();
                    String Password = users_scanner.nextLine();
                    Manager mng = new Manager(Name,Email,Gender,Age,Username,Password,ManagerID);
                    allMembers.add(mng);
                }
                users_scanner.nextLine();
            }
            
            Scanner sessions_scanner = new Scanner(new File("sessions.txt"));
            while(sessions_scanner.hasNext()){
                String SessionID = sessions_scanner.nextLine();
                Customer Customer = (Customer) find_member("Customer","ID",sessions_scanner.nextLine());  
                Trainer Trainer = (Trainer) find_member("Trainer","ID",sessions_scanner.nextLine());
                if(Customer == null || Trainer == null){
                    throw new Exception();
                }
                String Date = sessions_scanner.nextLine();
                int Time = Integer.parseInt(sessions_scanner.nextLine());
                int Duration = Integer.parseInt(sessions_scanner.nextLine());
                double Charge = Double.parseDouble(sessions_scanner.nextLine());
                Feedback Feedback = new Feedback(sessions_scanner.nextLine());
                String Status = sessions_scanner.nextLine();
                
                Session s = new Session(SessionID, Customer, Trainer, Date, Time, Duration, Charge, Feedback, Status);
                allSessions.add(s);
                sessions_scanner.nextLine();
            }
            
            Scanner payments_scanner = new Scanner(new File("payments.txt"));
            while(payments_scanner.hasNext()){                
                String PaymentID = payments_scanner.nextLine();
                String From = payments_scanner.nextLine();
                String To = payments_scanner.nextLine();
                double Amount = Double.parseDouble(payments_scanner.nextLine());
                String Date = payments_scanner.nextLine();
                
                Payment p = new Payment(PaymentID, From, To, Amount, Date);
                allPayments.add(p);
                payments_scanner.nextLine();
            }

            Scanner configs_scanner = new Scanner(new File("configs.txt"));
            while(configs_scanner.hasNext()){    
                HOUR_Rate = Double.parseDouble(configs_scanner.nextLine());
                String auto_withdrawal_text = configs_scanner.nextLine();
                if(auto_withdrawal_text.equals("1")){
                    auto_withdrawal = true;
                }else{
                    auto_withdrawal = false;
                }
                String email_notification_text = configs_scanner.nextLine();
                if(email_notification_text.equals("1")){
                    email_notification = true;
                }else{
                    email_notification = false;
                }
                configs_scanner.nextLine();
            }
            
        } catch(Exception e){
            System.out.println("Error in read!");
        }
    }
    public static void write(){
        try{
            PrintWriter users_writter = new PrintWriter("users.txt");
            for(Member m : allMembers){
                users_writter.println(m.getName());
                users_writter.println(m.getEmail());
                users_writter.println(m.getGender());
                users_writter.println(m.getAge());
                if(m instanceof Customer){
                    Customer c = (Customer) m;
                    users_writter.println("Customer");
                    users_writter.println(c.getCustomerID());
                }
                else if(m instanceof Trainer){
                    Trainer t = (Trainer) m;
                    users_writter.println("Trainer");
                    users_writter.println(t.getTrainerID());
                    users_writter.println(t.getBalance());
                    users_writter.println(t.getUsername());
                    users_writter.println(t.getPassword());
                }
                else if(m instanceof Manager){
                    Manager mng = (Manager) m;
                    users_writter.println("Manager");
                    users_writter.println(mng.getManagerID());
                    users_writter.println(mng.getUsername());
                    users_writter.println(mng.getPassword());
                }
                users_writter.println();
            }
            users_writter.close();
            
            PrintWriter sessions_writter = new PrintWriter("sessions.txt");
            for(Session s : allSessions){
                sessions_writter.println(s.getSessionID());
                sessions_writter.println(s.getCustomer().getCustomerID());
                sessions_writter.println(s.getTrainer().getTrainerID());
                sessions_writter.println(s.getDate());
                sessions_writter.println(s.getTime());
                sessions_writter.println(s.getDuration());
                sessions_writter.println(s.getCharge());
                sessions_writter.println(s.getFeedback().toString());
                sessions_writter.println(s.getStatus());
                sessions_writter.println();
            }
            sessions_writter.close();
            
            PrintWriter payments_writter = new PrintWriter("payments.txt");
            for(Payment p : allPayments){
                payments_writter.println(p.getPaymentID());
                payments_writter.println(p.getFrom());
                payments_writter.println(p.getTo());
                payments_writter.println(p.getAmount());
                payments_writter.println(p.getDate());
                payments_writter.println();
            }
            payments_writter.close();
            
            PrintWriter configs_writter = new PrintWriter("configs.txt");
            configs_writter.println(HOUR_Rate);
            if(auto_withdrawal){
                configs_writter.println(1);
            }else{
                configs_writter.println(0);
            }
            if(email_notification){
                configs_writter.println(1);
            }else{
                configs_writter.println(0);
            }
            configs_writter.println();
            configs_writter.close();
        } catch(Exception e){
            System.out.println("Error in write!");
        }
    }
    
    public static Session find_session(String SessionID){
        Session found = null;
        for(Session s: allSessions){
            if(s.getSessionID().equals(SessionID)){
                found = s;
            }
        }
        return found;
    }
    public static Member find_member(String type,String condition,String parameter){
        Member found = null;
        for(Member m : allMembers){
                if(type.equals("Customer")){
                    if(m instanceof Customer){
                        Customer c = (Customer) m;
                        if(condition.equals("ID")){
                            if(c.getCustomerID().equals(parameter)){
                                found = c;
                                break;  
                            } 
                        }
                        if(condition.equals("Name")){
                            if(c.getName().equals(parameter)){
                                found = c;
                                break;  
                            } 
                        }
                        if(condition.equals("Email")){
                            if(c.getEmail().equals(parameter)){
                                found = c;
                                break;  
                            } 
                        }
                    }
                }

                if(type.equals("Trainer")){
                    if(m instanceof Trainer){
                        Trainer t = (Trainer) m;
                        if(condition.equals("ID")){
                            if(t.getTrainerID().equals(parameter)){
                                found = t;
                                break;  
                            }
                        }
                        if(condition.equals("Name")){
                            if(t.getName().equals(parameter)){
                                found = t;
                                break;  
                            } 
                        }
                        if(condition.equals("Email")){
                            if(t.getEmail().equals(parameter)){
                                found = t;
                                break;  
                            } 
                        }
                        if(condition.equals("Username")){
                            if(t.getUsername().equals(parameter)){
                                found = t;
                                break;  
                            }
                        }
                    }
                }

                if(type.equals("Manager")){
                    if(m instanceof Manager){
                        Manager mng = (Manager) m;
                        if(condition.equals("ID")){
                            if(mng.getManagerID().equals(parameter)){
                                found = mng;
                                break;  
                            }
                        }
                        if(condition.equals("Name")){
                            if(mng.getName().equals(parameter)){
                                found = mng;
                                break;  
                            } 
                        }
                        if(condition.equals("Email")){
                            if(mng.getEmail().equals(parameter)){
                                found = mng;
                                break;  
                            } 
                        }
                        if(condition.equals("Username")){
                            if(mng.getUsername().equals(parameter)){
                                found = mng;
                                break;  
                            }
                        }
                    }
                }
        }
        return found;
    }
}
