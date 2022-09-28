package apu.gym.centre.management.system.Objects;

import java.util.UUID;

public class Customer extends Member{
    private String CustomerID;
    
    public Customer(String Name, String Email, String Gender, int Age){
        super(Name,Email,Gender,Age);
        String uniqueID = UUID.randomUUID().toString();
        CustomerID = "C"+uniqueID;
    }
    public Customer(String Name, String Email, String Gender, int Age, String CustomerID){
        super(Name,Email,Gender,Age);
        this.CustomerID = CustomerID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    
    public String toString(){
        return super.toString()+"\tCustomer\t"+CustomerID;
    }
}