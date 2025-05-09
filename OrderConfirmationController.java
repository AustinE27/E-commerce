package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.Random;

public class OrderConfirmationController {
    @FXML
    private Label orderNumberLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label cardLastFourLabel;
    @FXML
    private Label cardNameLabel;
    @FXML
    private VBox orderSummary;

    private CheckoutController checkoutController;
    private ShippingInfoController shippingController;
    private PaymentInfoController paymentController;

    @FXML
    public void initialize() {
        String orderNumber = generateOrderNumber();
        orderNumberLabel.setText("Order #" + orderNumber);
    }

    private String generateOrderNumber() {
        Random random = new Random();
        return String.format("ORD-%06d", random.nextInt(1000000));
    }

    public void setCheckoutController(CheckoutController controller) {
        this.checkoutController = controller;
    }

    public void setShippingController(ShippingInfoController controller) {
        this.shippingController = controller;
    }

    public void setPaymentController(PaymentInfoController controller) {
        this.paymentController = controller;
    }

    public void populateConfirmationDetails() {
        if (shippingController != null) {
            emailLabel.setText("Email: " + shippingController.getEmail());
            nameLabel.setText("Name: " + shippingController.getName());
            addressLabel.setText("Address: " + shippingController.getAddress() + ", " +
                    shippingController.getCity() + ", " +
                    shippingController.getState() + " " +
                    shippingController.getZip());
        }

        if (paymentController != null) {
            String cardNumber = paymentController.getCardNumber();
            String cardName = paymentController.getCardName();
            if (cardNumber.length() >= 4) {
                cardLastFourLabel.setText("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
            }
            cardNameLabel.setText("Name on Card: " + cardName);
        }
    }
}
