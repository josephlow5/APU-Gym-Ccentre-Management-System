package apu.gym.centre.management.system.Objects;
public class Member {
    private String Name;
    private String Email;
    private String Gender;
    private int Age;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public Member(String Name, String Email, String Gender, int Age) {
        this.Name = Name;
        this.Email = Email;
        this.Gender = Gender;
        this.Age = Age;
    }

    public String toString(){
        String OutputName = "";
        String OutputEmail = "";
        
        
        if(Name.length()>32){
            OutputName =  Name+"\t";
        }else if(Name.length()>14){
            OutputName =  Name+"\t\t";
        }else {
            OutputName =  Name+"\t\t\t";
        }
        
        if(Email.length()>32){
            OutputEmail =  Email+"\t";
        }else if(Email.length()>14){
            OutputEmail =  Email+"\t\t";
        }else {
            OutputEmail =  Email+"\t\t\t";
        }
        
        return OutputName+OutputEmail+Gender+"\t"+Age;
    }
}
