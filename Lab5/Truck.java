/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labassignment5_stidam_lillia;

/**
 *
 * @author lilli
 */
public class Truck extends Vehicle { //child
    public Truck(String make, String model, int year, int fuel_capacity, Engine engine){
        super(make, model, year, fuel_capacity, engine);//inherited
    };
    
    public void drop_tailgate(){
        System.out.println("Tailgate has been dropped");
    };
    
    @Override //method overriding
    public void start(){
        System.out.println("Truck's engine roars to life.");
        super.start(); //calling parent method
    }
}
