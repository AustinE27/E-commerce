package com.example.ecommercestoreprojecttemp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application entry point for the JavaFX e-commerce store.
 * Loads the homepage FXML and displays the primary stage.
 *
 * @author Samuel Garcia
 * @version 1.0
 * 4/28/25
 */
public class Main extends Application {

    /**
     * Initializes and shows the primary JavaFX stage with the homepage scene.
     *
     * @param primaryStage the main application window
     * @throws Exception if the FXML resource cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/example/ecommercestoreprojecttemp/Homepage.fxml")
        );
        primaryStage.setTitle("E-commerce Store");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}