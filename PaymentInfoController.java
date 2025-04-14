package com.example.commerceproj;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PaymentInfoController {
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField cardNameField;
    @FXML
    private TextField expiryMonthField;
    @FXML
    private TextField expiryYearField;
    @FXML
    private TextField cvvField;
    @FXML
    private Button confirmButton;
    
    private CheckoutController checkoutController;
    private ShippingInfoController shippingController;
    
    @FXML
    public void initialize() {
        // Add input validation
        cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cardNumberField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 16) {
                cardNumberField.setText(newValue.substring(0, 16));
            }
        });
        
        expiryMonthField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                expiryMonthField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 2) {
                expiryMonthField.setText(newValue.substring(0, 2));
            }
        });
        
        expiryYearField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                expiryYearField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 2) {
                expiryYearField.setText(newValue.substring(0, 2));
            }
        });
        
        cvvField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cvvField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 3) {
                cvvField.setText(newValue.substring(0, 3));
            }
        });
        
        // Add validation for confirm button
        confirmButton.setDisable(true);
        addValidationListeners();
    }
    
    private void addValidationListeners() {
        // Create a list of all required fields
        TextField[] requiredFields = {cardNumberField, cardNameField, expiryMonthField, expiryYearField, cvvField};
        
        // Add listeners to all fields
        for (TextField field : requiredFields) {
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                boolean allFieldsFilled = true;
                for (TextField f : requiredFields) {
                    if (f.getText().trim().isEmpty()) {
                        allFieldsFilled = false;
                        break;
                    }
                }
                confirmButton.setDisable(!allFieldsFilled);
            });
        }
    }
    
    @FXML
    private void handleConfirm() {
        // Validate card number length
        if (cardNumberField.getText().length() != 16) {
            showError("Card number must be 16 digits");
            return;
        }
        
        // Validate expiry date
        try {
            int month = Integer.parseInt(expiryMonthField.getText());
            int year = Integer.parseInt(expiryYearField.getText());
            if (month < 1 || month > 12) {
                showError("Invalid expiry month");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Invalid expiry date");
            return;
        }
        
        // Validate CVV
        if (cvvField.getText().length() != 3) {
            showError("CVV must be 3 digits");
            return;
        }
        
        // Proceed to order confirmation
        if (checkoutController != null) {
            checkoutController.loadOrderConfirmation();
        }
    }
    
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void setCheckoutController(CheckoutController controller) {
        this.checkoutController = controller;
    }
    
    public void setShippingController(ShippingInfoController controller) {
        this.shippingController = controller;
    }
    
    public String getCardNumber() { return cardNumberField.getText(); }
    public String getCardName() { return cardNameField.getText(); }
    public String getExpiryMonth() { return expiryMonthField.getText(); }
    public String getExpiryYear() { return expiryYearField.getText(); }
    public String getCvv() { return cvvField.getText(); }
} 