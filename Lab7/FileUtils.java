/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.io.*;

//manages file choosing/writing
public class FileUtils {
    
    //saves student object to file
    public static void saveStudentData(Student student, Stage primaryStage) {
        //file chooser save display
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(primaryStage);

        //writing data to file
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Student Information:\n");
                writer.write("Name: " + student.getName() + "\n");
                writer.write("Age: " + student.getAge() + "\n");
                writer.write("Gender: " + student.getGender() + "\n");
                writer.write("\nSchool Details:\n");
                writer.write("Grade Level: " + student.getGradeLevel() + "\n");
                writer.write("Major: " + student.getMajor() + "\n");
                writer.write("GPA: " + student.getGpa() + "\n");
            } catch (IOException e) {
                showAlert("Error", "An error occurred while saving the file.");
            }
        }
    }
    
    //show alert if error 
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
