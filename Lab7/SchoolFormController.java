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


//controller for school.fxml
public class SchoolFormController {

    //initalize all 
    @FXML
    private ComboBox<String> grade_box;

    @FXML
    private TextField major_field;

    @FXML
    private TextField gpa_field;

    @FXML
    private Button back_button;

    @FXML
    private Button submit_button;

    private Stage primaryStage;
    private PersonalFormController personalFormController;

    public void initialize() {
        // set options
        grade_box.getItems().addAll("Freshman", "Sophomore", "Junior", "Senior");
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setPersonalFormController(PersonalFormController controller) {
        this.personalFormController = controller;
    }

    
    //action if back button is pressed, linked in Scene Builder
    @FXML
    private void backClicked() {
        try {
            //reload scene with personal.fxml form 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("personal.fxml"));
            Parent root = loader.load();

            // set controller
            PersonalFormController personalController = loader.getController();
            personalController.setPrimaryStage(primaryStage);

            // populate personal form with data
            personalController.populateFormData();

            Scene scene1 = new Scene(root, 400, 300);
            primaryStage.setScene(scene1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //action if submit button is pressed, linked in Scene Builder
    @FXML
    private void submitClicked() {
        // get data from form
        String name = personalFormController.name_field.getText();
        int age = Integer.parseInt(personalFormController.age_field.getText());
        String gender = personalFormController.gender_box.getValue();

        String gradeLevel = grade_box.getValue();
        String major = major_field.getText();
        String gpaInput = gpa_field.getText();

        
        // make sure every field is filled in
        if (grade_box.getValue() == null || major_field.getText().isEmpty() || gpa_field.getText().isEmpty()) {
            showAlert("Validation Error", "Please fill in all fields before proceeding.");
            return; // Stop the form from switching if fields are empty
        }
        // make sure gpa is valid number
        double gpa;
        try {
            gpa = Double.parseDouble(gpaInput);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid GPA.");
            return;
        }
        
        //add saved data as a new student
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setGradeLevel(gradeLevel);
        student.setMajor(major);
        student.setGpa(gpa);
        
        //call function from FileUtils to save the student to file
        FileUtils.saveStudentData(student, primaryStage);

        // Close window 
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }

    //show alert if error
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
