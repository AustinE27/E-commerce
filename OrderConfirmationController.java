package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.Random;

/**
 * Controller for the order confirmation screen.
 * Generates a unique order number and populates shipping and payment details.
 * Displays a summary of the completed order to the user.
 *
 * @author Samuel Garcia
 * @version 1.0
 * 4/28/25
 */
public class OrderConfirmationController {

    /** Label displaying the generated order number. */
    @FXML
    private Label orderNumberLabel;

    /** Label displaying the customer’s email address. */
    @FXML
    private Label emailLabel;

    /** Label displaying the customer’s full name. */
    @FXML
    private Label nameLabel;

    /** Label displaying the customer’s shipping address. */
    @FXML
    private Label addressLabel;

    /** Label showing the last four digits of the payment card. */
    @FXML
    private Label cardLastFourLabel;

    /** Label showing the name on the payment card. */
    @FXML
    private Label cardNameLabel;

    /** Container for displaying a summary of items in the order. */
    @FXML
    private VBox orderSummary;

    /** Reference to the checkout controller for order details. */
    private CheckoutController checkoutController;

    /** Reference to the shipping info controller for address details. */
    private ShippingInfoController shippingController;

    /** Reference to the payment info controller for card details. */
    private PaymentInfoController paymentController;

    /**
     * Called automatically after FXML loading.
     * Generates a random order number and updates the label.
     */
    @FXML
    public void initialize() {
        String orderNumber = generateOrderNumber();
        orderNumberLabel.setText("Order #" + orderNumber);
    }

    /**
     * Creates a pseudo-random order number in the format "ORD-######".
     *
     * @return formatted order number string
     */
    private String generateOrderNumber() {
        Random random = new Random();
        return String.format("ORD-%06d", random.nextInt(1_000_000));
    }

    /**
     * Sets the CheckoutController to retrieve order item details.
     *
     * @param controller instance of CheckoutController
     */
    public void setCheckoutController(CheckoutController controller) {
        this.checkoutController = controller;
    }

    /**
     * Sets the ShippingInfoController to retrieve shipping details.
     *
     * @param controller instance of ShippingInfoController
     */
    public void setShippingController(ShippingInfoController controller) {
        this.shippingController = controller;
    }

    /**
     * Sets the PaymentInfoController to retrieve payment details.
     *
     * @param controller instance of PaymentInfoController
     */
    public void setPaymentController(PaymentInfoController controller) {
        this.paymentController = controller;
    }

    /**
     * Populates all confirmation labels with data from the shipping and payment controllers.
     * Formats and masks sensitive information (e.g., card number).
     */
    public void populateConfirmationDetails() {
        if (shippingController != null) {
            emailLabel.setText("Email: " + shippingController.getEmail());
            nameLabel.setText("Name: " + shippingController.getName());
            addressLabel.setText(
                    "Address: " +
                            shippingController.getAddress() + ", " +
                            shippingController.getCity() + ", " +
                            shippingController.getState() + " " +
                            shippingController.getZip()
            );
        }

        if (paymentController != null) {
            String cardNumber = paymentController.getCardNumber();
            String cardName = paymentController.getCardName();
            if (cardNumber.length() >= 4) {
                cardLastFourLabel.setText(
                        "Card: **** **** **** " +
                                cardNumber.substring(cardNumber.length() - 4)
                );
            }
            cardNameLabel.setText("Name on Card: " + cardName);
        }
    }
}