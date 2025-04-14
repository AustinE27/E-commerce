package com.example.commerceproj;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ShippingInfoController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipField;
    @FXML
    private RadioButton standardShipping;
    @FXML
    private RadioButton fastShipping;
    @FXML
    private Button continueButton;
    
    private ToggleGroup shippingGroup;
    private CheckoutController checkoutController;
    
    @FXML
    public void initialize() {
        // Create and set up the ToggleGroup
        shippingGroup = new ToggleGroup();
        standardShipping.setToggleGroup(shippingGroup);
        fastShipping.setToggleGroup(shippingGroup);
        standardShipping.setSelected(true);
        
        // Disable continue button until all fields are filled
        continueButton.setDisable(true);
        
        // Add listeners to all fields to enable/disable continue button
        addFieldListeners();
    }
    
    private void addFieldListeners() {
        // Create a listener that checks if all required fields are filled
        javafx.beans.value.ChangeListener<String> listener = (observable, oldValue, newValue) -> {
            boolean allFieldsFilled = !emailField.getText().isEmpty() &&
                                    !nameField.getText().isEmpty() &&
                                    !addressField.getText().isEmpty() &&
                                    !cityField.getText().isEmpty() &&
                                    !stateField.getText().isEmpty() &&
                                    !zipField.getText().isEmpty();
            continueButton.setDisable(!allFieldsFilled);
        };
        
        // Add the listener to all text fields
        emailField.textProperty().addListener(listener);
        nameField.textProperty().addListener(listener);
        addressField.textProperty().addListener(listener);
        cityField.textProperty().addListener(listener);
        stateField.textProperty().addListener(listener);
        zipField.textProperty().addListener(listener);
    }
    
    public void setCheckoutController(CheckoutController controller) {
        this.checkoutController = controller;
    }
    
    @FXML
    private void handleContinue() {
        if (checkoutController != null) {
            // Update shipping cost based on selected method
            double shippingCost = standardShipping.isSelected() ? 15.0 : 30.0;
            checkoutController.setShippingCost(shippingCost);
            
            // Proceed to payment info
            checkoutController.loadPaymentInfo();
        }
    }
    
    public String getEmail() { return emailField.getText(); }
    public String getName() { return nameField.getText(); }
    public String getAddress() { return addressField.getText(); }
    public String getCity() { return cityField.getText(); }
    public String getState() { return stateField.getText(); }
    public String getZip() { return zipField.getText(); }
    public String getShippingMethod() { 
        return standardShipping.isSelected() ? "Standard" : "Fast"; 
    }
} 