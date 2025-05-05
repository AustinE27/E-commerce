package com.example.ecommercestoreprojecttemp;

import javafx.collections.ObservableList;
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

    @FXML
    private ListView<HBox> cartListView;

    @FXML
    private Label totalLabel;

    @FXML
    private Button checkoutButton;

    private final CartManager cartManager = CartManager.getInstance();



    @FXML
    public void initialize() {
        refreshCartView();
    }

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
                String imagePath = item.getProduct().getProductPhoto();
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
