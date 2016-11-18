/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dominic.mortgage.models;

import java.util.Random;

/**
 *
 * @author x08424179
 */
public class Transaction {
    int id;
    String type;
    String date;
    double postBalance;
    int accountNumber;

    public Transaction(int id, String type, String date, double postBalance, int accountNumber) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.postBalance = postBalance;
        this.accountNumber = accountNumber;
    }

    public Transaction(String type, String date, double postBalance, int accountNumber) {
        this.id = new Random().nextInt(10000000);
        this.type = type;
        this.date = date;
        this.postBalance = postBalance;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    
    
    
}
