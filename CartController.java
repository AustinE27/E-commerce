/**
    NOT IMPLEMENTED/TESTED
    Controller for GUI Display.
    @author J. Hernandez-Velazquez
    @version 1.0
 */

package controller;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

/**
    Connects the UI cart table to
    display products and total cost.
 */
public class CartController {
    /**
        Displays the list of cart items in a table
        in the cart's GUI.
     */
    @FXML private TableView<CartItem> cartTable;

    /**
        Shows the product name for each item
        listed in the cart table.
     */
    @FXML private TableColumn<CartItem, String> nameCol;

    /**
        Shows the quantity selected for each
        cart item in the table.
     */
    @FXML private TableColumn<CartItem, Integer> qtyCol;

    /**
        Displays the total price per product line.
     */
    @FXML private TableColumn<CartItem, Double> priceCol;

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
        Initializes the cart table with columns of
        product name, quantity, and total price.
     */
    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getProduct().getName()));
        qtyCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(
                cellData.getValue().getQuantity()).asObject());
        priceCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(
                cellData.getValue().getTotalPrice()).asObject());

        cartTable.setItems(cartData);
    }

    /**
        Adds a test product to the cart
        and refreshes the cart and total.
     */
    @FXML
    private void onAddProduct() {
        Product product = new Product(1001, "Test Product", 19.99);
        cartManager.addToCart(product, 1);
        refreshCart();
    }

    /**
        Updates the cart view and
        total from the cart manager.
     */
    private void refreshCart() {
        cartData.setAll(cartManager.getAllItems());
        totalLabel.setText("Total: $" + String.format("%.2f", cartManager.getTotal()));
    }
}
