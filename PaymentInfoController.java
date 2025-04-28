package com.example.commerceproj;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller that manages the user input and validation of payment details.
 *
 * Author: Samuel Garcia
 * Date: 4/17/25
 */
public class PaymentInfoController {
    @FXML private TextField cardNumberField;
    @FXML private TextField cardNameField;
    @FXML private TextField expiryMonthField;
    @FXML private TextField expiryYearField;
    @FXML private TextField cvvField;
    @FXML private Button confirmButton;

    private CheckoutController checkoutController;
    private ShippingInfoController shippingController;

    /**
     * Initializes the input listeners and field validation.
     */
    @FXML
    public void initialize() {
        cardNumberField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                cardNumberField.setText(newVal.replaceAll("[^\\d]", ""));
            }
            if (newVal.length() > 16) {
                cardNumberField.setText(newVal.substring(0, 16));
            }
        });

        expiryMonthField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                expiryMonthField.setText(newVal.replaceAll("[^\\d]", ""));
            }
            if (newVal.length() > 2) {
                expiryMonthField.setText(newVal.substring(0, 2));
            }
        });

        expiryYearField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                expiryYearField.setText(newVal.replaceAll("[^\\d]", ""));
            }
            if (newVal.length() > 2) {
                expiryYearField.setText(newVal.substring(0, 2));
            }
        });

        cvvField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                cvvField.setText(newVal.replaceAll("[^\\d]", ""));
            }
            if (newVal.length() > 3) {
                cvvField.setText(newVal.substring(0, 3));
            }
        });

        confirmButton.setDisable(true);
        addValidationListeners();
    }

    /**
     * Enables the confirm button only if all fields are filled.
     */
    private void addValidationListeners() {
        TextField[] fields = {cardNumberField, cardNameField, expiryMonthField, expiryYearField, cvvField};
        for (TextField field : fields) {
            field.textProperty().addListener((obs, oldVal, newVal) -> {
                boolean filled = true;
                for (TextField f : fields) {
                    if (f.getText().trim().isEmpty()) {
                        filled = false;
                        break;
                    }
                }
                confirmButton.setDisable(!filled);
            });
        }
    }

    /**
     * Handles the confirm action and validates all user input.
     */
    @FXML
    private void handleConfirm() {
        if (cardNumberField.getText().length() != 16) {
            showError("Card number must be 16 digits");
            return;
        }

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

        if (cvvField.getText().length() != 3) {
            showError("CVV must be 3 digits");
            return;
        }

        if (checkoutController != null) {
            checkoutController.loadOrderConfirmation();
        }
        if (shippingController != null) {
            String userEmail = shippingController.getEmail();
            String userName = shippingController.getName();
            String address = shippingController.getAddress() + ", " +
                    shippingController.getCity() + ", " +
                    shippingController.getState() + " " +
                    shippingController.getZip();

            String shippingMethod = shippingController.getShippingMethod();

            StringBuilder itemsList = new StringBuilder();
            for (CheckoutController.CartItem item : checkoutController.getCartItems()) {
                itemsList.append("- ").append(item.getName())
                        .append(" ($").append(String.format("%.2f", item.getPrice()))
                        .append(")\n");
            }

            String fromEmail = "forealisam@gmail.com";     // 🔥 your Gmail
            String appPassword = "uaqm fndv xnyd qklf";       // 🔥 your app password
            String subject = "Order Confirmation";

            String body = "Hi " + userName + ",\n\n" +
                    "Thank you for your purchase!\n\n" +
                    "📦 Items you ordered:\n" + itemsList.toString() + "\n" +
                    "Shipping to: " + address + "\n" +
                    "Shipping Method: " + shippingMethod + "\n\n" +
                    "We hope you enjoy your order!\n\n" +
                    "Best regards,\nCommerce Team";

            try {
                EmailSender.sendEmail(fromEmail, appPassword, userEmail, subject, body);
                System.out.println("Order confirmation email sent to " + userEmail);
            } catch (Exception e) {
                System.err.println("Failed to send confirmation email: " + e.getMessage());
            }
        }
    }

    /**
     * Displays an alert dialog with an error message.
     *
     * @param message error message to show
     */
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
