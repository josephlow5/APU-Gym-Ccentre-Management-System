package apu.gym.centre.management.system.Objects;

import java.util.UUID;

public class Manager extends User{
    private String ManagerID;
    public Manager(String Name, String Email, String Gender, int Age, String Username, String Password){
        super(Name, Email,Gender, Age, Username, Password);
        String uniqueID = UUID.randomUUID().toString();
        ManagerID = "M"+uniqueID;
    }
    public Manager(String Name, String Email, String Gender, int Age, String Username, String Password, String ManagerID){
        super(Name, Email,Gender, Age, Username, Password);
        this.ManagerID = ManagerID;
    }

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String ManagerID) {
        this.ManagerID = ManagerID;
    }
    
    @Override
    public String toString(){
        return super.toString().replace("(user_type)", "Manager").replace("(user_id)", ManagerID);
    }
}
