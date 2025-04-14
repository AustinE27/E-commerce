package com.example.commerceproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the main checkout layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/commerceproj/checkout.fxml"));
        Parent root = loader.load();
        
        // Get the controller and initialize the checkout flow
        CheckoutController checkoutController = loader.getController();
        
        // Load the shipping info page
        checkoutController.loadShippingInfo();
        
        // Set up the stage
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}