/**
    Controller for GUI Display.
    Here is where test Products are stored. Line 61.
    Updated for Picture Display on cart's ListView per cart item.
    @author J. Hernandez-Velazquez
    @version 3.0
 */

package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.image.ImageView;
import model.*;

/**
    Connects the UI cart ListView to
    dynamically display products and total cost.
 */
public class CartController {

    /**
        Displays the list of cart items dynamically
        in the cart's GUI ListView.
     */
    @FXML private ListView<HBox> cartListView;

    /**
        Displays the combined subtotal cost of
        all items in the cart.
     */
    @FXML private Label totalLabel;

    /**
        Holds the observable list of cart items
        for display as well as refresh.
     */
    private final ObservableList<CartItem> cartData = FXCollections.observableArrayList();

    /**
        Allows the adding of products, gathering
        product info and subtotal.
     */
    private final CartManager cartManager = new CartManager();

    /**
        Initializes the cart view by
        showing all cart items dynamically.
     */
    @FXML
    public void initialize() {


        //                          REMOVE LATER THESE ARE TEST PRODUCTS
        //               call cartManager.addProduct(productName, 1) to add to cart


        Product testProduct = new Product(123456, "Laptop", 1149.99, "/images/laptoptest.png");
        Product testProduct2 = new Product(12346, "Wireless Keyboard", 27.99, "/images/keyboardtest.png");

        cartManager.addProduct(testProduct, 1);
        cartManager.addProduct(testProduct2, 2);

        refreshCartView();
    }

    /**
        Refreshes the cart ListView with
        current cart items that have quantity > 0.
     */
    private void refreshCartView() {
        cartListView.getItems().clear();

        double subtotal = 0.0;

        for (CartItem item : cartManager.getCartItems()) {
            if (item.getQuantity() > 0) {
                HBox itemBox = new HBox(15); // 15px spacing

                // Product Image
                ImageView productImage = new ImageView();
                productImage.setFitWidth(50);
                productImage.setFitHeight(50);
                String imagePath = item.getProduct().getProductPhoto();
                if (imagePath != null && !imagePath.isEmpty()) {
                        Image image = new Image(getClass().getResourceAsStream(imagePath));
                        productImage.setImage(image);
                }

                // Product Name Text
                Text productName = new Text(item.getProduct().getName());
                productName.setStyle("-fx-font-size: 16;");

                // Quantity Choice Box
                ChoiceBox<Integer> quantityChoiceBox = new ChoiceBox<>();
                for (int i = 1; i <= 10; i++) quantityChoiceBox.getItems().add(i);
                quantityChoiceBox.setValue(item.getQuantity());
                quantityChoiceBox.setOnAction(e -> {
                    cartManager.updateItemQuantity(item.getProduct().getSku(), quantityChoiceBox.getValue());
                    refreshCartView();
                });

                // Remove Button
                Button removeButton = new Button("Remove");
                removeButton.setStyle("-fx-font-size: 10;");
                removeButton.setOnAction(e -> {
                    cartManager.updateItemQuantity(item.getProduct().getSku(), 0);
                    refreshCartView();
                });

                // Subtotal label for this item (price * quantity)
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
}
