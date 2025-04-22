/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//main class
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception { //main window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("personal.fxml")); //load the first form, displaying personal.fxml
        Parent root = loader.load();

        PersonalFormController personalFormController = loader.getController(); //set the controller
        personalFormController.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Student Information"); //title
        primaryStage.setScene(new Scene(root, 400, 300)); //size
        primaryStage.show(); //makes visible
    }

    public static void main(String[] args) {
        launch(args);
    }
}

