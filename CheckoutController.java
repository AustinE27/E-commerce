package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller for handling checkout operations including cart summary,
 * navigation between checkout stages, and dynamic updates to totals.
 *
 * Author: Samuel Garcia
 * Date: 4/17/25
 */
public class CheckoutController {

    @FXML
    private VBox cartSummary;
    @FXML
    private VBox mainContent;

    private double subtotal = 0.0;
    private double shippingCost = 15.0;
    private double total = 0.0;

    private ShippingInfoController shippingController;
    private PaymentInfoController paymentController;
    private OrderConfirmationController confirmationController;

    @FXML
    public void initialize() {
        updateCartSummary();
        loadShippingInfo();
    }

    private void updateCartSummary() {
        cartSummary.getChildren().clear();

        //                      SETS SUBTOTAL TO 0 TO STOP 2X PRICE BUG
        subtotal = 0.0;

        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        // Add cart items
        for (CartItem item : cartItems) {
            if (item.getQuantity() > 0) {
                Label itemLabel = new Label(item.getProduct().getName() + " x" + item.getQuantity() +
                        " - $" + String.format("%.2f", item.getProduct().getPrice() * item.getQuantity()));
                cartSummary.getChildren().add(itemLabel);
                subtotal += item.getProduct().getPrice() * item.getQuantity();
            }
        }

        // Add subtotal
        Label subtotalLabel = new Label("Subtotal: $" + String.format("%.2f", subtotal));
        cartSummary.getChildren().add(subtotalLabel);

        // Add shipping
        Label shippingLabel = new Label("Shipping: $" + String.format("%.2f", shippingCost));
        cartSummary.getChildren().add(shippingLabel);

        // Add total
        total = subtotal + shippingCost;
        Label totalLabel = new Label("Total: $" + String.format("%.2f", total));
        totalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        cartSummary.getChildren().add(totalLabel);
    }

    public void setShippingCost(double cost) {
        this.shippingCost = cost;
        updateCartSummary();
    }

    public void loadShippingInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecommercestoreprojecttemp/shipping-info.fxml"));
            Parent shippingInfo = loader.load();
            shippingController = loader.getController();
            shippingController.setCheckoutController(this);
            mainContent.getChildren().setAll(shippingInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPaymentInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecommercestoreprojecttemp/payment-info.fxml"));
            Parent paymentInfo = loader.load();
            paymentController = loader.getController();
            paymentController.setCheckoutController(this);
            paymentController.setShippingController(shippingController);
            mainContent.getChildren().setAll(paymentInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadOrderConfirmation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecommercestoreprojecttemp/order-confirmation.fxml"));
            Parent confirmation = loader.load();
            confirmationController = loader.getController();
            confirmationController.setCheckoutController(this);
            confirmationController.setShippingController(shippingController);
            confirmationController.setPaymentController(paymentController);
            confirmationController.populateConfirmationDetails();
            mainContent.getChildren().setAll(confirmation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
