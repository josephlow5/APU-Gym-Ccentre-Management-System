package apu.gym.centre.management.system.Objects;

import java.util.UUID;

public class Trainer extends User{
    private String TrainerID;
    private double Balance;
    public Trainer(String Name, String Email, String Gender, int age, String Username, String Password){
        super(Name, Email,Gender, age, Username, Password);
        String uniqueID = UUID.randomUUID().toString();
        TrainerID = "T"+uniqueID;
        Balance = 0;
    }
    public Trainer(String Name, String Email, String Gender, int age, String Username, String Password, String TrainerID, double Balance){
        super(Name, Email,Gender, age, Username, Password);
        this.TrainerID = TrainerID;
        this.Balance = Balance;
    }

    public String getTrainerID() {
        return TrainerID;
    }

    public void setTrainerID(String TrainerID) {
        this.TrainerID = TrainerID;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
    
    public String toString(){
        return super.toString().replace("(user_type)", "Trainer").replace("(user_id)", TrainerID)+"\t"+Balance;
    }
}
