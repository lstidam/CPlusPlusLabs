/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labassignment5_stidam_lillia;

/**
 *
 * @author lilli
 */
public class Bike extends Vehicle { //child 
    public Bike(String make, String model, int year, int fuel_capacity, Engine engine){
        super(make, model, year, fuel_capacity, engine);//inherited
    };
    
    public void wheelie(){
        System.out.println("You just popped a wheelie");
    };
    
    @Override //method overriding
    public void start(){
        System.out.println("Bike's engine vibrates to life.");
        super.start(); //calling parent method
    }
}
