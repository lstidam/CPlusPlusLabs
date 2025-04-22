/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labassignment5_stidam_lillia;

/**
 *
 * @author lilli
 */
public class Engine { //class Engine, will be used for composition
    protected int cylinders;
    
    public Engine(int cylinders){
        this.cylinders = cylinders;
    }
    
    public int get_num_cyl(){
        return cylinders;
    }
    public void start(){
        System.out.println("Engine with "+cylinders+" cylinders has started.");
    }
}
