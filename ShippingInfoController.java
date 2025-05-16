package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller for collecting and validating shipping information.
 * Manages input fields, enforces required entries, and passes
 * chosen shipping method and cost back to the checkout flow.
 *
 * @author Samuel Garcia
 * @version 1.0
 * 4/17/25
 */
public class ShippingInfoController {

    /** Text field for the customer’s email address. */
    @FXML private TextField emailField;

    /** Text field for the customer’s full name. */
    @FXML private TextField nameField;

    /** Text field for the street address. */
    @FXML private TextField addressField;

    /** Text field for the city. */
    @FXML private TextField cityField;

    /** Text field for the state or province. */
    @FXML private TextField stateField;

    /** Text field for the ZIP or postal code. */
    @FXML private TextField zipField;

    /** Radio button for selecting standard shipping. */
    @FXML private RadioButton standardShipping;

    /** Radio button for selecting expedited (fast) shipping. */
    @FXML private RadioButton fastShipping;

    /** Button to continue to the payment information step. */
    @FXML private Button continueButton;

    /** ToggleGroup to ensure only one shipping option is selected. */
    private ToggleGroup shippingGroup;

    /** Reference to the CheckoutController to advance the order process. */
    private CheckoutController checkoutController;

    /**
     * Called automatically after FXML loading.
     * Initializes the shipping options, default selection, and disables
     * the continue button until all fields are filled.
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
     * Adds listeners to all text fields to enable the continue button
     * only when every shipping field has non-empty input.
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

    /**
     * Injects the CheckoutController to allow passing of shipping cost
     * and navigation to the payment information screen.
     *
     * @param controller instance of CheckoutController
     */
    public void setCheckoutController(CheckoutController controller) {
        this.checkoutController = controller;
    }

    /**
     * Handles the continue button action.
     * Calculates shipping cost based on selected method and
     * instructs the checkout flow to load the payment view.
     */
    @FXML
    private void handleContinue() {
        if (checkoutController != null) {
            double shippingCost = standardShipping.isSelected() ? 15.0 : 30.0;
            checkoutController.setShippingCost(shippingCost);
            checkoutController.loadPaymentInfo();
        }
    }

    /** @return the entered email address. */
    public String getEmail() {
        return emailField.getText();
    }

    /** @return the entered customer name. */
    public String getName() {
        return nameField.getText();
    }

    /** @return the entered street address. */
    public String getAddress() {
        return addressField.getText();
    }

    /** @return the entered city. */
    public String getCity() {
        return cityField.getText();
    }

    /** @return the entered state or province. */
    public String getState() {
        return stateField.getText();
    }

    /** @return the entered ZIP or postal code. */
    public String getZip() {
        return zipField.getText();
    }

    /**
     * @return the selected shipping method as a string,
     *         either "Standard" or "Fast".
     */
    public String getShippingMethod() {
        return standardShipping.isSelected() ? "Standard" : "Fast";
    }
}
