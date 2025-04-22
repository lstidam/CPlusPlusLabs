/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.labassignment5_stidam_lillia;
import java.util.Scanner;

/**
 *
 * @author lilli
 */
public class LabAssignment5_Stidam_Lillia {

    public static void main(String[] args) {
        Vehicle[] garage = new Vehicle[100]; //creating array 
        int num_in_garage =0; //used for count
        Scanner scanner = new Scanner(System.in);
        String response;
        do{
            System.out.println("Do you want to add a vehicle? (Yes/No)"); //main loop, enter options
            response = scanner.nextLine();
            if ("Yes".equals(response)){
                System.out.println("Enter vehicle type (Car, Truck, Bike):");
                String type = scanner.nextLine();

                System.out.println("Enter make:");
                String make = scanner.nextLine();

                System.out.println("Enter model:");
                String model = scanner.nextLine();

                System.out.println("Enter year:");
                int year = scanner.nextInt();

                System.out.println("Enter fuel capacity:");
                double fuel_capacity = scanner.nextInt();
                
                System.out.println("Enter number of cylinders in engine:");
                int cylinders = scanner.nextInt();
                Engine engine = new Engine(cylinders);
                
                Vehicle vehicle = null;
                
                switch (type.toLowerCase()){ //identifies which object to make
                    case "car":
                        vehicle = new Car(make, model, year, (int) fuel_capacity, engine); 
                        break;
                    case "truck":
                        vehicle = new Truck(make, model, year, (int) fuel_capacity, engine);
                        break;
                    case "bike":
                        vehicle = new Bike(make, model, year, (int) fuel_capacity, engine);
                        break;
                    default: 
                        System.out.println("Invalid vehicle type.");
                }
                
                if (vehicle != null){
                    garage[num_in_garage]= vehicle; //adds vehicle to garage array, example of polymorphism (adds all child types to array of parent type
                    num_in_garage++;
                }
                scanner.nextLine(); 
            }
        }while (response.equalsIgnoreCase("Yes"));
        
        System.out.println();
        System.out. println("Tom's Garage: "); //outputs vechile info
        for (int i = 0; i < num_in_garage; i++) {
            System.out.println("Make: "+ garage[i].make);
            System.out.println("Model: "+ garage[i].model);
            System.out.println("Year: "+ garage[i].year);
            System.out.println("Engine Type: "+ garage[i].engine.get_num_cyl()+" cylinder engine");
            System.out.println();
        }
        
        System.out. println("Starting all vehicles: "); //starts vehicles
        for (int i = 0; i < num_in_garage; i++) {
            garage[i].start(); //example of polymorphism
            garage[i].engine.start();//example of polymorphism
            System.out.println();
        }
        
        System.out. println("Refuling all vehicles: "); //refuels
        for (int i = 0; i < num_in_garage; i++) {
            garage[i].refuel(2);
            garage[i].refuel();
        }
        
        System.out.println();
        System.out. println("Stopping all vehicles: "); //stops
        for (int i = 0; i < num_in_garage; i++) {
            garage[i].stop();
        }

        scanner.close();
      
    }
}
