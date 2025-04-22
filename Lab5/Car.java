/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labassignment5_stidam_lillia;

/**
 *
 * @author lilli
 */
public class Car extends Vehicle{ //child class of Vehicle
    
    public Car(String make, String model, int year, int fuel_capacity, Engine engine){
        super(make, model, year, fuel_capacity, engine); //inherited
    };
    
    public void open_trunk(){
        System.out.println("Trunk is open.");
    };
    
    public void close_trunk(){
        System.out.println("Trunk is closed.");
    }
    
    @Override  //method overriding of parent
    public void start(){
        System.out.println("Car's engine starts with a hum.");
        super.start(); //calling parent method
    }
}
