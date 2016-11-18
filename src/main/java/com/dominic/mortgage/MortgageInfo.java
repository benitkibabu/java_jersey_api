package com.dominic.mortgage;

public class MortgageInfo {
    
    double monthlyRate;
    double months;
    double years;
    double payment;
    double amount;
        
    public MortgageInfo(double amount, double rate, int years) {
        this.years = years;
        this.amount = amount;
        this.monthlyRate = (rate / 100.0 )/ 12.0;
        this.months = years * 12;
        
        payment = amount * (monthlyRate * Math.pow(1 + monthlyRate, months) ) / 
                ( Math.pow(1+ monthlyRate, months) - 1);
                
    }


}
