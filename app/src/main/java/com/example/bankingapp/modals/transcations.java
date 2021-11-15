package com.example.bankingapp.modals;

public class transcations {
    int id;
    String sender_name;
    String receiver_name;
double money;
String date;

    public transcations(int id,String sender_name,String receiver_name, double money,String date) {
        this.id=id;
        this.sender_name=sender_name;
        this.receiver_name = receiver_name;
        this.money = money;
        this.date=date;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getMoney() {
        return String.valueOf(money);
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }


}
