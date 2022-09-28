package apu.gym.centre.management.system.Objects;

public class User extends Member{
    private String Username;
    private String Password;
    
    public User(String Name, String Email, String Gender, int Age, String Username, String Password){
        super(Name, Email, Gender, Age);
        this.Username = Username;
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public String toString(){
        return super.toString()+"\t(user_type)\t(user_id)\t"+Username+"\t"+Password;
    }
}
