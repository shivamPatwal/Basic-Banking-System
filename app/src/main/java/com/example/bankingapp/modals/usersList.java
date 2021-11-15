package com.example.bankingapp.modals;

public class usersList {

    String name;
    int accountNo;
    String emailId;
    String ifscCode;
    String phoneNo;
   int id;
    double  accountBalance;

    public usersList(int id,String name, int accountNo, String emailId, String ifscCode, String phoneNo, double accountBalance) {
     this.id=id;
        this.name = name;
        this.accountNo = accountNo;
        this.emailId = emailId;
        this.ifscCode = ifscCode;
        this.phoneNo = phoneNo;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }
    public int getId() {
       return id;
    }

    public String getAccountNo() {
        return String.valueOf(accountNo);
    }

    public String getEmailId() {
        return emailId;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAccountBalance() {
        return String.valueOf(accountBalance);
    }
    public Double getAccountBal() { return accountBalance; }


}
