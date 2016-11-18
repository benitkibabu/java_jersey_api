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
public class Account {
    int AccountNumber;
    int sortCode;
    double balance;
    int userID;

    public Account(int AccountNumber, int sortCode, double balance, int userID) {
        this.AccountNumber = AccountNumber;
        this.sortCode = sortCode;
        this.balance = balance;
        this.userID = userID;
    }

    public Account(int sortCode, double balance, int userID) {
        this.AccountNumber = new Random().nextInt(10000000);
        this.sortCode = sortCode;
        this.balance = balance;
        this.userID = userID;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public int getSortCode() {
        return sortCode;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserID() {
        return userID;
    }  
}
