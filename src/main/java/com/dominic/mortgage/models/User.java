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
public class User {
    int id;
    String name;
    String email;
    String address;

//    public User(int id, String name, String email, String address) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.address = address;
//    }

    public User(String name, String email, String address) {
        this.id = new Random().nextInt(10000000);
        this.name = name + "" ;
        this.email = email + "";
        this.address = address + "";
    }    
    
    public String details(){
        return  (name + ", " + email + ", "+ address); 
    }
}
