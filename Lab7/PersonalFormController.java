/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


//controller for personal.fxml
public class PersonalFormController {

    //initalize all
    private String name;
    private String gender;
    private String age;

    @FXML
    protected TextField name_field;

    @FXML
    protected TextField age_field;

    @FXML
    protected ChoiceBox<String> gender_box;

    @FXML
    protected Button next_button;

    private Stage primaryStage;

    public void initialize() {
        // Initialize the gender choice box
        gender_box.getItems().addAll("Male", "Female", "Other", "Prefer Not To Say");
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    //method to save data
    public void saveFormData() {
        name = name_field.getText();
        age = age_field.getText();
        gender = gender_box.getValue();
    }

    // populate the fields with saved data
    public void populateFormData() {
        name_field.setText(name);
        age_field.setText(age);
        gender_box.setValue(gender);
    }

    //method called when next button is pressed, linked in Scene Builder 
    @FXML
    private void nextClicked() {
        // check all fields filled in
        if (name_field == null || name_field.getText() == null || name_field.getText().isEmpty() ||
        age_field == null || age_field.getText() == null || age_field.getText().isEmpty() ||
        gender_box == null || gender_box.getValue() == null) {
            showAlert("Error", "Please fill in all fields before proceeding.");
            return; // Stop the form from switching if fields are empty
        }
        
        // check age is number
        int ageInt;
        try {
            ageInt = Integer.parseInt(age_field.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid age.");
            return;
        }

        saveFormData();  // save data entered
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("school.fxml")); //open next form
            Parent root = loader.load();

            // Get the SchoolFormController
            SchoolFormController schoolFormController = loader.getController();
            schoolFormController.setPrimaryStage(primaryStage);
            schoolFormController.setPersonalFormController(this);

            Scene scene2 = new Scene(root, 400, 300);
            primaryStage.setScene(scene2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //show alert if error
        private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
