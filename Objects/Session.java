package apu.gym.centre.management.system.Objects;

import apu.gym.centre.management.system.DataIO;
import java.util.UUID;

public class Session {
    private String SessionID;
    private Customer Customer;
    private Trainer Trainer;
    private String Date;
    private int Time;
    private int Duration;
    private double Charge;
    private Feedback Feedback;
    private String Status;

    public Session(Customer Customer, Trainer Trainer, String Date, int Time, int Duration) {
        String uniqueID = UUID.randomUUID().toString();
        this.SessionID = "S"+uniqueID;;
        this.Customer = Customer;
        this.Trainer = Trainer;
        this.Date = Date;
        this.Time = Time;
        this.Duration = Duration;
        this.Charge = Duration*DataIO.HOUR_Rate;
        this.Feedback = new Feedback(0,"NOT YET RATED");
        this.Status = "Scheduled";
    }
    public Session(String SessionID, Customer Customer, Trainer Trainer, String Date, int Time, int Duration, double Charge, Feedback Feedback, String Status) {
        this.SessionID = SessionID;
        this.Customer = Customer;
        this.Trainer = Trainer;
        this.Date = Date;
        this.Time = Time;
        this.Duration = Duration;
        this.Charge = Charge;
        this.Feedback = Feedback;
        this.Status = Status;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public Trainer getTrainer() {
        return Trainer;
    }

    public void setTrainer(Trainer Trainer) {
        this.Trainer = Trainer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public double getCharge() {
        return Charge;
    }

    public void setCharge(double Charge) {
        this.Charge = Charge;
    }

    public Feedback getFeedback() {
        return Feedback;
    }

    public void setFeedback(Feedback Feedback) {
        this.Feedback = Feedback;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    public String toString(){
        return SessionID+"|"+Date+"|"+Time+"|"+Customer.getName()+"|"+Trainer.getName()+"|"+Duration+" hour(s)|"+Status;
    }
    
    public String toFullString(){
        String CustomerName = "";
        if(Customer.getName().length()>32){
            CustomerName = Customer.getName() + "\t";
        }else if(Customer.getName().length()>14){
            CustomerName = Customer.getName() + "\t\t";
        }else{
            CustomerName = Customer.getName() + "\t\t\t";
        }
        
        String TrainerName = "";
        if(Trainer.getName().length()>32){
            TrainerName = Trainer.getName() + "\t";
        }else if(Trainer.getName().length()>14){
            TrainerName = Trainer.getName() + "\t\t";
        }else{
            TrainerName = Trainer.getName() + "\t\t\t";
        }
        
        return SessionID+"\t"+Date+"\t"+Time+"\t"+CustomerName+TrainerName+Duration+" hour(s)\t"+Status+
                "\t"+Feedback.toDisplayString();
    }
}
