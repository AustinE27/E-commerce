package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller for handling checkout operations, including displaying
 * the cart summary, navigating between checkout stages, and updating totals.
 *
 * Initializes by showing the cart summary and loading the shipping info view.
 *
 * @author Samuel Garcia
 * @version 1.0
 * 4/17/25
 */
public class CheckoutController {

    /** Container displaying each cart item, subtotal, shipping, and total. */
    @FXML
    private VBox cartSummary;

    /** Main content area where different checkout steps (shipping, payment, confirmation) are loaded. */
    @FXML
    private VBox mainContent;

    /** Current subtotal computed from cart items. */
    private double subtotal = 0.0;

    /** Current shipping cost (defaulted to standard rate). */
    private double shippingCost = 15.0;

    /** Grand total (subtotal + shipping). */
    private double total = 0.0;

    /** Controller for the shipping information step. */
    private ShippingInfoController shippingController;

    /** Controller for the payment information step. */
    private PaymentInfoController paymentController;

    /** Controller for the order confirmation step. */
    private OrderConfirmationController confirmationController;

    /**
     * Called automatically after FXML loading.
     * Updates the cart summary display and begins the checkout flow
     * by loading the shipping info screen.
     */
    @FXML
    public void initialize() {
        updateCartSummary();
        loadShippingInfo();
    }

    /**
     * Rebuilds the cart summary panel: clears existing entries,
     * recalculates subtotal, and displays each item line along with
     * subtotal, shipping cost, and total.
     */
    private void updateCartSummary() {
        cartSummary.getChildren().clear();
        subtotal = 0.0;  // reset to avoid duplicate accumulation

        List<CartItem> cartItems = CartManager.getInstance().getCartItems();
        for (CartItem item : cartItems) {
            if (item.getQuantity() > 0) {
                Label itemLabel = new Label(
                        item.getProduct().getName() +
                                " x" + item.getQuantity() +
                                " - $" + String.format("%.2f",
                                item.getProduct().getPrice() * item.getQuantity()
                        )
                );
                cartSummary.getChildren().add(itemLabel);
                subtotal += item.getProduct().getPrice() * item.getQuantity();
            }
        }

        Label subtotalLabel = new Label("Subtotal: $" + String.format("%.2f", subtotal));
        cartSummary.getChildren().add(subtotalLabel);

        Label shippingLabel = new Label("Shipping: $" + String.format("%.2f", shippingCost));
        cartSummary.getChildren().add(shippingLabel);

        total = subtotal + shippingCost;
        Label totalLabel = new Label("Total: $" + String.format("%.2f", total));
        totalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        cartSummary.getChildren().add(totalLabel);
    }

    /**
     * Updates the shipping cost and refreshes the cart summary to reflect the change.
     *
     * @param cost new shipping cost to apply
     */
    public void setShippingCost(double cost) {
        this.shippingCost = cost;
        updateCartSummary();
    }

    /**
     * Loads the shipping information FXML into the main content area
     * and passes this controller to the ShippingInfoController.
     */
    public void loadShippingInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/ecommercestoreprojecttemp/shipping-info.fxml")
            );
            Parent shippingInfo = loader.load();
            shippingController = loader.getController();
            shippingController.setCheckoutController(this);
            mainContent.getChildren().setAll(shippingInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the payment information FXML into the main content area,
     * passes required controllers, and preserves shipping data.
     */
    public void loadPaymentInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/ecommercestoreprojecttemp/payment-info.fxml")
            );
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
     * Loads the order confirmation FXML into the main content area,
     * injects all prior controllers, populates confirmation details,
     * and displays the final confirmation screen.
     */
    public void loadOrderConfirmation() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/ecommercestoreprojecttemp/order-confirmation.fxml")
            );
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

