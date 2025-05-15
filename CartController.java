/**
 * Controller for displaying and interacting with the shopping cart UI.
 * Displays cart items with pictures and allows for quantity adjustments.
 *
 * Updated for subtotal calculator and scene transition to Checkout.
 * @author J. Hernandez-Velazquez
 * @version 2.0
 */
package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;
import java.io.IOException;

public class CartController {

    /**
     * ListView displaying cart items as HBox components.
     */
    @FXML
    private ListView<HBox> cartListView;

    /**
     * Label to display the cart subtotal.
     */
    @FXML
    private Label totalLabel;

    /**
     * Button to proceed to the checkout screen.
     */
    @FXML
    private Button checkoutButton;

    /**
     * Reference to the CartManager singleton for cart operations.
     */
    private final CartManager cartManager = CartManager.getInstance();

    /**
     * Called automatically when the FXML is loaded.
     * Initializes the cart view.
     */
    @FXML
    public void initialize() {
        refreshCartView();
    }

    /**
     * Refreshes the cart display by clearing and repopulating the ListView.
     * Displays product image, name, quantity choice box, remove button, and line total.
     * Updates the subtotal label at the bottom.
     */
    private void refreshCartView() {
        cartListView.getItems().clear();
        double subtotal = 0.0;

        List<CartItem> items = cartManager.getCartItems();
        for (CartItem item : items) {
            if (item.getQuantity() > 0) {
                HBox itemBox = new HBox(15); // 15px spacing

                ImageView productImage = new ImageView();
                productImage.setFitWidth(50);
                productImage.setFitHeight(50);
                String imagePath = item.getProduct().getImagePath();
                if (imagePath != null && !imagePath.isEmpty()) {
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    productImage.setImage(image);
                }

                Text productName = new Text(item.getProduct().getName());
                productName.setStyle("-fx-font-size: 16;");

                ChoiceBox<Integer> quantityChoiceBox = new ChoiceBox<>();
                for (int i = 1; i <= 10; i++) quantityChoiceBox.getItems().add(i);
                quantityChoiceBox.setValue(item.getQuantity());
                quantityChoiceBox.setOnAction(e -> {
                    cartManager.updateItemQuantity(item.getProduct().getSku(), quantityChoiceBox.getValue());
                    refreshCartView();
                });

                Button removeButton = new Button("Remove");
                removeButton.setStyle("-fx-font-size: 10;");
                removeButton.setOnAction(e -> {
                    cartManager.updateItemQuantity(item.getProduct().getSku(), 0);
                    refreshCartView();
                });

                double lineTotal = item.getProduct().getPrice() * item.getQuantity();
                Text lineTotalText = new Text(String.format("$%.2f", lineTotal));
                lineTotalText.setStyle("-fx-font-size: 14;");

                itemBox.getChildren().addAll(productImage, productName, quantityChoiceBox, removeButton, lineTotalText);
                cartListView.getItems().add(itemBox);

                subtotal += lineTotal;
            }
        }

        totalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
    }

    /**
     * Navigates to the checkout view by loading the corresponding FXML layout.
     * Opens the checkout scene in a new stage.
     */
    @FXML
    private void goToCheckout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecommercestoreprojecttemp/checkout.fxml"));
            Parent checkoutRoot = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Checkout");
            stage.setScene(new Scene(checkoutRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
