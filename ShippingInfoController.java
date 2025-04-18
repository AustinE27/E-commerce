package com.example.commerceproj;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller that handles the user's shipping information and navigation to payment.
 *
 * Author: Samuel Garcia
 * Date: 4/17/25
 */
public class ShippingInfoController {
    @FXML private TextField emailField;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField cityField;
    @FXML private TextField stateField;
    @FXML private TextField zipField;
    @FXML private RadioButton standardShipping;
    @FXML private RadioButton fastShipping;
    @FXML private Button continueButton;

    private ToggleGroup shippingGroup;
    private CheckoutController checkoutController;

    /**
     * Initializes the controller and sets up validation for form fields.
     */
    @FXML
    public void initialize() {
        shippingGroup = new ToggleGroup();
        standardShipping.setToggleGroup(shippingGroup);
        fastShipping.setToggleGroup(shippingGroup);
        standardShipping.setSelected(true);

        continueButton.setDisable(true);
        addFieldListeners();
    }

    /**
     * Adds listeners to fields to enable continue button when all are filled.
     */
    private void addFieldListeners() {
        javafx.beans.value.ChangeListener<String> listener = (obs, oldVal, newVal) -> {
            boolean filled = !emailField.getText().isEmpty() &&
                    !nameField.getText().isEmpty() &&
                    !addressField.getText().isEmpty() &&
                    !cityField.getText().isEmpty() &&
                    !stateField.getText().isEmpty() &&
                    !zipField.getText().isEmpty();
            continueButton.setDisable(!filled);
        };

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

    /**
     * Proceeds to the payment info screen, setting the shipping cost.
     */
    @FXML
    private void handleContinue() {
        if (checkoutController != null) {
            double shippingCost = standardShipping.isSelected() ? 15.0 : 30.0;
            checkoutController.setShippingCost(shippingCost);
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
