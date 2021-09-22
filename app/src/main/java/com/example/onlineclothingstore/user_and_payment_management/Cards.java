package com.example.onlineclothingstore.user_and_payment_management;

public class Cards {
    String cardname;
    String number;
    String expdate;
    String cv;

    public Cards(){

    }

    public Cards(String cardname, String number, String expdate, String cv) {
        this.cardname = cardname;
        this.number = number;
        this.expdate = expdate;
        this.cv = cv;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCardname() {
        return cardname;
    }

    public String getNumber() {
        return number;
    }

    public String getExpdate() {
        return expdate;
    }

    public String getCv() {
        return cv;
    }
}
