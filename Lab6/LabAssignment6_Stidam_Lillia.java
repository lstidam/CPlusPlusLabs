/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.labassignment6_stidam_lillia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Vehicle {
    String type, make, model, year, mileage;

    public Vehicle(String type, String make, String model, String year, String mileage) {
        this.type = type;
        this.model = model;
        this.make = make;
        this.year = year; 
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Make: " + make + ", Model: " + model + ", Year: " + year + ", Mileage: " + mileage;
    }
}

public class LabAssignment6_Stidam_Lillia extends JFrame {
    private final JTextField makeField, modelField, yearField, mileageField;
    private final JComboBox<String> typeComboBox;
    private final JTextArea garageTextArea;
    private final ArrayList<Vehicle> garageList;
    private int selectedIndex = -1; // Track the selected vehicle

    public LabAssignment6_Stidam_Lillia() {
        garageList = new ArrayList<>();

        // Create and configure JFrame
        setTitle("Tom's Garage");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for adding a new vehicle
        JPanel addVehiclePanel = new JPanel(new GridLayout(6, 2));
        addVehiclePanel.setBorder(BorderFactory.createTitledBorder("Add New Vehicle"));

        // Type (ComboBox)
        addVehiclePanel.add(new JLabel("Type:"));
        String[] types = { "Car", "Truck", "Motorcycle" };
        typeComboBox = new JComboBox<>(types);
        addVehiclePanel.add(typeComboBox);

        // Make
        addVehiclePanel.add(new JLabel("Make:"));
        makeField = new JTextField();
        addVehiclePanel.add(makeField);

        // Model
        addVehiclePanel.add(new JLabel("Model:"));
        modelField = new JTextField();
        addVehiclePanel.add(modelField);

        // Year
        addVehiclePanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        addVehiclePanel.add(yearField);

        // Mileage
        addVehiclePanel.add(new JLabel("Mileage:"));
        mileageField = new JTextField();
        addVehiclePanel.add(mileageField);

        // Add Vehicle Button
        JButton addButton = new JButton("Add Vehicle");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle(); //call function when button is pressed
            }
        });
        addVehiclePanel.add(addButton);

        // Clear Form Button
        JButton clearButton = new JButton("Clear Form");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm(); //call function when button is pressed
            }
        });
        addVehiclePanel.add(clearButton);

        add(addVehiclePanel, BorderLayout.NORTH);

        // Panel for displaying garage list
        JPanel garagePanel = new JPanel(new BorderLayout());
        garagePanel.setBorder(BorderFactory.createTitledBorder("Vehicles in Garage:"));

        // Text area to display vehicles
        garageTextArea = new JTextArea();
        garageTextArea.setEditable(false); // Make it read-only
        garageTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectVehicleAtMousePosition(e); //Call function when mouse is clicked in text area
            }
        });
        JScrollPane scrollPane = new JScrollPane(garageTextArea);
        garagePanel.add(scrollPane);
        add(garagePanel, BorderLayout.CENTER);

        // Remove Vehicle Button
        JButton removeVehicleButton = new JButton("Remove Selected Vehicle");
        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeVehicle(); //Call function when button pressed
            }
        });
        add(removeVehicleButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    
    //adding a vehicle 
    private void addVehicle() {
        String type = (String) typeComboBox.getSelectedItem();
        String make = makeField.getText();
        String model = modelField.getText();
        String year = yearField.getText();
        String mileage = mileageField.getText();

        // Check to see nothing is empty
        if (type.isEmpty() || make.isEmpty() || model.isEmpty() || year.isEmpty() || mileage.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add vehicle to garage list
        Vehicle vehicle = new Vehicle(type, make, model, year, mileage);
        garageList.add(vehicle); //add to ArrayList
        updateGarageTextArea(); //call function to update text area
        JOptionPane.showMessageDialog(this, "Vehicle added successfully!");
    }
    
    //clear form, remove all text
    private void clearForm() {
        makeField.setText("");
        modelField.setText("");
        yearField.setText("");
        mileageField.setText("");
    }

    //remove the selected vehicle from the garage
    private void removeVehicle() {
        if (selectedIndex >= 0 && selectedIndex < garageList.size()) {
            garageList.remove(selectedIndex); //remove from ArrayList
            updateGarageTextArea(); //call function to update text area
            selectedIndex = -1; // Reset selection
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to remove.", "Error", JOptionPane.ERROR_MESSAGE); //if nothing has been selected
        }
    }

    //update the text area
    private void updateGarageTextArea() {
        garageTextArea.setText(""); //clear all text, set it to blank string
        for (int i = 1; i < garageList.size(); i++) { //go through ArrayList and add
            garageTextArea.append(i + ": " + garageList.get(i) + "\n");
        }
    }

    //figure out which vehicle has been selected to be removed
    private void selectVehicleAtMousePosition(MouseEvent e) {
        int clickedLine = garageTextArea.viewToModel2D(e.getPoint()); //get clicked location
        int start = 0;
        int line = 0;

        try { //go through each line and see if the clicked location falls in that line
            for (line = 0; line < garageTextArea.getLineCount(); line++) {
                int lineStartOffset = garageTextArea.getLineStartOffset(line);
                int lineEndOffset = garageTextArea.getLineEndOffset(line);
                if (clickedLine >= lineStartOffset && clickedLine <= lineEndOffset) {
                    start = lineStartOffset;
                    break; //if location is found, break loop
                }
            }

            String selectedLineText = garageTextArea.getText(start, garageTextArea.getLineEndOffset(line) - start).trim(); //gets text of line selected
            if (selectedLineText.length() > 0) {
                selectedIndex = Integer.parseInt(selectedLineText.split(":")[0]); //gets the index of the selected line
            }
        } catch (Exception ex) { //catches exeception 
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LabAssignment6_Stidam_Lillia::new);
    }
}

