package com.group;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML file from resources
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout.fxml"));
            Parent root = loader.load();
            
            // Get controller (auto call initialize())
            Object controller = loader.getController();
            System.out.println("Controller loaded: " + controller.getClass().getName());
            
            // Create Scene
            Scene scene = new Scene(root, 927, 630); // From FXML
            
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            
            // Set up Stage
            primaryStage.setTitle("Robot DSL Interpreter");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            System.out.println("Application started successfully!");
            
        } catch (IOException e) {
            System.err.println("Failed to load FXML: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Launching Robot DSL Application...");
        launch(args);
    }
}