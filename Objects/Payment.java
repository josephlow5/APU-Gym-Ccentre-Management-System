package apu.gym.centre.management.system.Objects;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Payment {
    private String PaymentID;
    private String From;
    private String To;
    private double Amount;
    private String Date;

    public Payment(String From, String To, double Amount) {
        String uniqueID = UUID.randomUUID().toString();
        this.PaymentID = "P"+uniqueID;
        this.From = From;
        this.To = To;
        this.Amount = Amount;
        Date date = new Date();
        String day = String.format(Locale.UK, "%td", date);
        String month = String.format(Locale.UK, "%tb", date);
        String year = String.format(Locale.UK, "%tY", date);
        String Date = day + " " + month + " "+ year;
        this.Date = Date;
    }
    public Payment(String PaymentID, String From, String To, double Amount, String Date) {
        this.PaymentID = PaymentID;
        this.From = From;
        this.To = To;
        this.Amount = Amount;
        this.Date = Date;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(String PaymentID) {
        this.PaymentID = PaymentID;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
}
