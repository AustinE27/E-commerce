package com.example.commerceproj;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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
    
    private List<CartItem> cartItems = new ArrayList<>();
    private double subtotal = 0.0;
    private double shippingCost = 15.0;
    private double total = 0.0;
    
    private ShippingInfoController shippingController;
    private PaymentInfoController paymentController;
    private OrderConfirmationController confirmationController;

    /**
     * Initializes the controller with sample items and updates the cart summary.
     */
    @FXML
    public void initialize() {
        // Add some sample cart items
        addSampleCartItems();
        updateCartSummary();
    }

    /**
     * Adds example items to the cart and calculates the subtotal.
     */
    private void addSampleCartItems() {
        cartItems.add(new CartItem("Sample Product 1", 29.99, "SKU001"));
        cartItems.add(new CartItem("Sample Product 2", 49.99, "SKU002"));
        cartItems.add(new CartItem("Sample Product 3", 19.99, "SKU003"));
        
        // Calculate subtotal
        subtotal = cartItems.stream().mapToDouble(CartItem::getPrice).sum();
    }

    /**
     * Updates the UI component that displays the current cart, subtotal, shipping, and total.
     */
    private void updateCartSummary() {
        cartSummary.getChildren().clear();
        
        // Add cart items
        for (CartItem item : cartItems) {
            Label itemLabel = new Label(item.getName() + " - $" + String.format("%.2f", item.getPrice()));
            cartSummary.getChildren().add(itemLabel);
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

    /**
     * Sets the shipping cost and updates the summary display.
     */
    public void setShippingCost(double cost) {
        this.shippingCost = cost;
        updateCartSummary();
    }

    /**
     * Loads the shipping information screen.
     */
    public void loadShippingInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/commerceproj/shipping-info.fxml"));
            Parent shippingInfo = loader.load();
            shippingController = loader.getController();
            shippingController.setCheckoutController(this);
            mainContent.getChildren().setAll(shippingInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the payment information screen and passes the shipping info.
     */
    public void loadPaymentInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/commerceproj/payment-info.fxml"));
            Parent paymentInfo = loader.load();
            paymentController = loader.getController();
            paymentController.setCheckoutController(this);
            paymentController.setShippingController(shippingController);
            mainContent.getChildren().setAll(paymentInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the order confirmation screen and passes the necessary data.
     */
    public void loadOrderConfirmation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/commerceproj/order-confirmation.fxml"));
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

    /**
     * Represents an item in the cart with name, price, and SKU.
     */
    public static class CartItem {
        private String name;
        private double price;
        private String sku;

        /**
         * Constructs a CartItem with given name, price, and SKU.
         *
         * @param name the name of the product
         * @param price the price of the product
         * @param sku the stock keeping unit identifier
         */
        public CartItem(String name, double price, String sku) {
            this.name = name;
            this.price = price;
            this.sku = sku;
        }

        
        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getSku() { return sku; }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
} 