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
    
    @FXML
    public void initialize() {
        // Add some sample cart items
        addSampleCartItems();
        updateCartSummary();
    }
    
    private void addSampleCartItems() {
        cartItems.add(new CartItem("Sample Product 1", 29.99, "SKU001"));
        cartItems.add(new CartItem("Sample Product 2", 49.99, "SKU002"));
        cartItems.add(new CartItem("Sample Product 3", 19.99, "SKU003"));
        
        // Calculate subtotal
        subtotal = cartItems.stream().mapToDouble(CartItem::getPrice).sum();
    }
    
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
    
    public void setShippingCost(double cost) {
        this.shippingCost = cost;
        updateCartSummary();
    }
    
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
    
    public void loadOrderConfirmation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/commerceproj/order-confirmation.fxml"));
            Parent confirmation = loader.load();
            confirmationController = loader.getController();
            confirmationController.setCheckoutController(this);
            confirmationController.setShippingController(shippingController);
            confirmationController.setPaymentController(paymentController);
            mainContent.getChildren().setAll(confirmation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Inner class to represent cart items
    public static class CartItem {
        private String name;
        private double price;
        private String sku;
        
        public CartItem(String name, double price, String sku) {
            this.name = name;
            this.price = price;
            this.sku = sku;
        }
        
        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getSku() { return sku; }
    }
} 