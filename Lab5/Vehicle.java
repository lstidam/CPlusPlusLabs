/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labassignment5_stidam_lillia;

/**
 *
 * @author lilli
 */
public class Vehicle { //parent class
    public String make;
    public String model;
    public int year;
    public int fuel_capacity;
    protected Engine engine; //engine object as property, composition
    
    public Vehicle(String make, String model, int year, int fuel_capacity, Engine engine){
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuel_capacity = fuel_capacity;
        this.engine = engine;
    };
    
    public void start(){
        System.out.println(make + " " + model+ " has been started.");
    };
    
    public void stop(){
        System.out.println(make + " " + model+ " has been stopped.");
    };
    
    public void refuel(){
        System.out.println(make + " " + model + " has been refueled.");
    };
    
    public void refuel(double amount){ //method overloading
        System.out.println("Added " + amount+ " gallon(s) of fuel to "+make + " " + model);
    }
}
