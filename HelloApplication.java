package com.example.commerceproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launches the checkout JavaFX application and loads the main checkout screen.
 *
 * Author: Samuel Garcia
 * Date: 4/17/25
 */
public class HelloApplication extends Application {

    /**
     * Entry point for JavaFX applications. Sets up the main scene and initiates checkout flow.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file is not found or cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/commerceproj/checkout.fxml"));
        Parent root = loader.load();

        CheckoutController checkoutController = loader.getController();
        checkoutController.loadShippingInfo();

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }
}
