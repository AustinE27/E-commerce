package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    @FXML
    private VBox productContainer;

    @FXML
    private Button cartButton;

    @FXML
    public void initialize() {
        addProductToView(new Product(1111, "Nvidia GeForce RTX 5090", 1999.99, "/com/example/ecommercestoreprojecttemp/geforce-rtx-5090-founders-edition-photo-test.png"));
        addProductToView(new Product(2222, "Wireless Keyboard", 27.99, "/com/example/ecommercestoreprojecttemp/keyboardtest.png"));
        addProductToView(new Product(3333, "Laptop", 1149.99, "/com/example/ecommercestoreprojecttemp/laptoptest.png"));
    }

    private void addProductToView(Product product) {
        VBox itemBox = new VBox(5);

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(product.getProductPhoto())));
        imageView.setFitWidth(250);
        imageView.setPreserveRatio(true);

        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label(String.format("$%.2f", product.getPrice()));
        Button addButton = new Button("Add to Cart");

        addButton.setOnAction(e -> {
            CartManager.getInstance().addProduct(product, 1);
        });

        itemBox.getChildren().addAll(imageView, nameLabel, priceLabel, addButton);
        productContainer.getChildren().add(itemBox);
    }

    @FXML
    private void openCart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecommercestoreprojecttemp/cart.fxml"));
            Parent cartRoot = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Your Cart");
            stage.setScene(new Scene(cartRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
