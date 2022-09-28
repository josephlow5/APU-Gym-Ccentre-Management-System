package apu.gym.centre.management.system.Pages;

import apu.gym.centre.management.system.APUGymCentreManagementSystem;
import apu.gym.centre.management.system.DataIO;
import apu.gym.centre.management.system.Objects.Customer;
import apu.gym.centre.management.system.Objects.Manager;
import apu.gym.centre.management.system.Objects.Member;
import apu.gym.centre.management.system.Objects.Trainer;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Page4 extends JFrame implements ActionListener, Page{
    public int getPageNumber(){
        return 4;
    }
    public String getPageTitle(){
        return "Manager - Manage Users";
    }
    
    public void actionPerformed(ActionEvent a){
        try{
            //Button: Back
            if(a.getSource() == back){
                APUGymCentreManagementSystem.page4.setVisible(false);
                APUGymCentreManagementSystem.page2.setVisible(true);
                
            //Button: Check and Search
            } else if(a.getSource() == check){
                String get_category_to_show = AskCategoryToShow();
                if(get_category_to_show==null){
                    throw new Exception();
                }
                String get_name_to_search = "";
                if(get_category_to_show.equals("Search by Name")){
                    get_name_to_search = JOptionPane.showInputDialog("Enter Name to Search:");
                }
                String toShow = ShowMembers(get_category_to_show,get_name_to_search);
                if(!toShow.equals(Format_toShow_Member)){
                    JTextArea outputTextArea = new JTextArea(toShow.trim());
                    outputTextArea.setOpaque(false);
                    JOptionPane.showMessageDialog(this, outputTextArea);
                }else{
                    JOptionPane.showMessageDialog(this, "No result found!");
                }
                
            //Button: Delete Users
            } else if(a.getSource() == delete){
                //Find the member to edit
                String get_user_type = AskUserType();
                if(get_user_type==null){
                    throw new Exception();
                }
                String get_name_to_search = JOptionPane.showInputDialog("Enter Full Name of the "+get_user_type+":");
                if(DataIO.find_member(get_user_type,"Name",get_name_to_search) == null){
                    throw new Exception();
                }
                Member m = DataIO.find_member(get_user_type,"Name",get_name_to_search);
                Manager login = (Manager) APUGymCentreManagementSystem.login;
                String confirm_password = JOptionPane.showInputDialog("Please enter your password again to confirm delete user "+get_name_to_search+":");
                if(login.getPassword().equals(confirm_password)){
                    DataIO.allMembers.remove(m);
                    DataIO.write();
                    JOptionPane.showMessageDialog(this, "Successfully deleted user "+get_name_to_search+"!");
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid password, Please try again!");
                }
            
            //Button: Register
            } else if(a.getSource() == register){
                String get_user_type = AskUserType();
                if(get_user_type==null){
                    throw new Exception();
                }
                String get_user_name = JOptionPane.showInputDialog("Enter Full Name:");
                if(DataIO.find_member(get_user_type,"Name",get_user_name) != null){
                    throw new Exception();
                }
                String get_user_email = JOptionPane.showInputDialog("Enter Email:");
                if(DataIO.find_member(get_user_type,"Email",get_user_email) != null){
                    throw new Exception();
                }
                String get_user_gender = AskUserGender();
                if(get_user_gender==null){
                    throw new Exception();
                }
                int get_user_age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
                
                switch(get_user_type){
                    case "Customer":
                        Customer c = new Customer(get_user_name,get_user_email,get_user_gender,get_user_age);
                        DataIO.allMembers.add(c);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully registered customer "+get_user_name+"!");
                        break;
                    case "Trainer":
                        String get_user_username_trainer = JOptionPane.showInputDialog("Enter Username:");
                        if(DataIO.find_member(get_user_type,"Username",get_user_username_trainer) != null){
                            throw new Exception();
                        }
                        String get_user_password_trainer = JOptionPane.showInputDialog("Enter Password:");
                        Trainer t = new Trainer(get_user_name,get_user_email,get_user_gender,get_user_age,get_user_username_trainer,get_user_password_trainer);
                        DataIO.allMembers.add(t);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully registered trainer "+get_user_name+"!");
                        break;
                    case "Manager":
                        String get_user_username_manager = JOptionPane.showInputDialog("Enter Username:");
                        if(DataIO.find_member(get_user_type,"Username",get_user_username_manager) != null){
                            throw new Exception();
                        }
                        String get_user_password_manager = JOptionPane.showInputDialog("Enter Password:");
                        Manager mng = new Manager(get_user_name,get_user_email,get_user_gender,get_user_age,get_user_username_manager,get_user_password_manager);
                        DataIO.allMembers.add(mng);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully registered manager "+get_user_name+"!");
                        break;
                }
                
            //Button: Edit
            } else if(a.getSource() == edit){
                //Find the member to edit
                String get_user_type = AskUserType();
                if(get_user_type==null){
                    throw new Exception();
                }
                String get_name_to_search = JOptionPane.showInputDialog("Enter Full Name of the "+get_user_type+":");
                if(DataIO.find_member(get_user_type,"Name",get_name_to_search) == null){
                    throw new Exception();
                }
                Member m = DataIO.find_member(get_user_type,"Name",get_name_to_search);
                
                //Get data to edit, what to edit to, validate, and execute edit
                String get_data_to_edit = AskDataToEdit();
                if(get_data_to_edit==null){
                    throw new Exception();
                }
                switch(get_data_to_edit){
                    case "Name":
                        String get_edit_to_name = JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?");
                        if(DataIO.find_member(get_user_type,get_data_to_edit,get_edit_to_name) != null){
                            throw new Exception();
                        }
                        m.setName(get_edit_to_name);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_name+"!");
                        break;
                    case "Email":
                        String get_edit_to_email = JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?");
                        if(DataIO.find_member(get_user_type,get_data_to_edit,get_edit_to_email) != null){
                            throw new Exception();
                        }
                        m.setEmail(get_edit_to_email);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_email+"!");
                        break;
                    case "Gender":
                        String get_edit_to_gender = AskUserGender();
                        if(get_edit_to_gender==null){
                            throw new Exception();
                        }
                        m.setGender(get_edit_to_gender);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_gender+"!");
                        break;
                    case "Age":
                        int get_edit_to_age = Integer.parseInt(JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?"));
                        m.setAge(get_edit_to_age);
                        DataIO.write();
                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_age+"!");
                        break;
                    default:
                        switch(get_user_type){
                            case "Customer":
                                throw new Exception();
                            case "Trainer":
                                Trainer t = (Trainer) DataIO.find_member(get_user_type,"Name",get_name_to_search);
                                switch(get_data_to_edit){
                                    case "Username":
                                        String get_edit_to_username = JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?");
                                        if(DataIO.find_member(get_user_type,get_data_to_edit,get_edit_to_username) != null){
                                            throw new Exception();
                                        }
                                        t.setUsername(get_edit_to_username);
                                        DataIO.write();
                                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_username+"!");
                                        break;
                                    case "Password":
                                        String get_edit_to_password = JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?");
                                        if(DataIO.find_member(get_user_type,get_data_to_edit,get_edit_to_password) != null){
                                            throw new Exception();
                                        }
                                        t.setPassword(get_edit_to_password);
                                        DataIO.write();
                                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_password+"!");
                                        break;
                                    case "Balance":
                                        double get_edit_to_balance = Double.parseDouble(JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?"));
                                        t.setBalance(get_edit_to_balance);
                                        DataIO.write();
                                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_balance+"!");
                                        break;
                                }
                                break;
                            case "Manager":
                                Manager mng = (Manager) DataIO.find_member(get_user_type,"Name",get_name_to_search);
                                switch(get_data_to_edit){
                                    case "Username":
                                        String get_edit_to_username = JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?");
                                        if(DataIO.find_member(get_user_type,get_data_to_edit,get_edit_to_username) != null){
                                            throw new Exception();
                                        }
                                        mng.setUsername(get_edit_to_username);
                                        DataIO.write();
                                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_username+"!");
                                        break;
                                    case "Password":
                                        String get_edit_to_password = JOptionPane.showInputDialog("Edit to what "+get_data_to_edit+"?");
                                        if(DataIO.find_member(get_user_type,get_data_to_edit,get_edit_to_password) != null){
                                            throw new Exception();
                                        }
                                        mng.setPassword(get_edit_to_password);
                                        DataIO.write();
                                        JOptionPane.showMessageDialog(this, "Successfully Edited "+get_data_to_edit+" of "+get_user_type+" to "+get_edit_to_password+"!");
                                        break;
                                    case "Balance":
                                        throw new Exception();
                                }
                                break;
                        }
                        break;
                }
                
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
    
    private Button register,edit,delete,check,back;
    public Page4(){
        setSize(500,300);
        setLocation(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        register = new Button("Register Users");
        edit = new Button("Edit Users");
        delete = new Button("Delete Users");
        check = new Button("Check or Search Users");
        back = new Button("Back");
        
        register.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
        check.addActionListener(this);
        back.addActionListener(this);;
        
        add(register);
        add(edit);
        add(delete);
        add(check);
        add(back);
    }
    
    //Functions: Multiple Choices Selections
    private String AskUserType(){
        //User Type Choices
        String[] user_type_choices = { "Customer", "Trainer", "Manager" };
        String get_user_type = (String) JOptionPane.showInputDialog(null, "Which user type?",
            "The Type of User", JOptionPane.QUESTION_MESSAGE, null,
            user_type_choices,
            user_type_choices[0]);
        return get_user_type;
    }
    private String AskUserGender(){
        String[] user_gender_choices = { "Male", "Female"};
        String get_user_gender = (String) JOptionPane.showInputDialog(null, "Select the gender",
            "The Gender of User", JOptionPane.QUESTION_MESSAGE, null,
            user_gender_choices,
            user_gender_choices[0]);
        return get_user_gender;
    }
    private String AskDataToEdit(){
        String[] data_to_edit_choices = { "Name", "Email", "Gender", "Age", "Username", "Password", "Balance"};
        String get_data_to_edit = (String) JOptionPane.showInputDialog(null, "Which data to edit?",
            "The Selection of Data to Edit", JOptionPane.QUESTION_MESSAGE, null,
            data_to_edit_choices,
            data_to_edit_choices[0]);
        return get_data_to_edit;
    }
    private String AskCategoryToShow(){
        String[] category_to_show_choices = { "All Users", "All Customers", "All Trainer", "All Managers", "Search by Name"};
        String get_category_to_show = (String) JOptionPane.showInputDialog(null, "What category you want to check?",
            "The Selection of Category to Show", JOptionPane.QUESTION_MESSAGE, null,
            category_to_show_choices,
            category_to_show_choices[0]);
        return get_category_to_show;
    }
    
    //Functions: Filter, Select and Show Specific Member Data
    String Format_toShow_Member = "Full Name\t\t\tEmail\t\t\tGender\tAge\tType\tUser ID\t\t\tUsername\tPassword\tBalance\n"
                + "=========\t\t\t=====\t\t\t======\t===\t====\t=======\t\t\t========\t========\t=======\n\n";
    private String ShowMembers(String condition,String condition_parameter){
        String toShow = Format_toShow_Member;
        for(Member m: DataIO.allMembers){
            switch(condition){
                case "All Customers":
                    if(m instanceof Customer){
                        Customer c = (Customer) m;
                        toShow += c.toString() + "\n";
                    }
                    break;
                case "All Trainer":
                    if(m instanceof Trainer){
                        Trainer t = (Trainer) m;
                        toShow += t.toString() + "\n";
                    }
                    break;
                case "All Managers":
                    if(m instanceof Manager){
                        Manager mng = (Manager) m;
                        toShow += mng.toString() + "\n";
                    }
                    break;
                case "All Users":
                    if(m instanceof Customer){
                        Customer c = (Customer) m;
                        toShow += c.toString() + "\n";
                    }else if(m instanceof Trainer){
                        Trainer t = (Trainer) m;
                        toShow += t.toString() + "\n";
                    }else if(m instanceof Manager){
                        Manager mng = (Manager) m;
                        toShow += mng.toString() + "\n";
                    }
                    break;
                case "Search by Name":
                    if(m.getName().contains(condition_parameter)){
                        if(m instanceof Customer){
                            Customer c = (Customer) m;
                            toShow += c.toString() + "\n";
                        }else if(m instanceof Trainer){
                            Trainer t = (Trainer) m;
                            toShow += t.toString() + "\n";
                        }else if(m instanceof Manager){
                            Manager mng = (Manager) m;
                            toShow += mng.toString() + "\n";
                        }
                    }
                    break;
            }
        }
        return toShow;
    }
}
