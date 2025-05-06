package com.example.ecommercestoreprojecttemp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HomepageController {

    @FXML private VBox productContainer;
    @FXML private Button cartButton;
    @FXML private ScrollPane scrollPane;
    @FXML private TextField searchBox;
    @FXML private Button sortButton;

    private List<Product> allProducts = new ArrayList<>();

    @FXML
    public void initialize() {

        //                                      TEST DUMMY PRODUCTS NOT CONNECTED TO DATABASE

        allProducts = List.of(
                new Product(1, "Phone", 899.99, "/com/example/ecommercestoreprojecttemp/ProductPhotos/phone.png"),
                new Product(2, "Refrigerator", 1299.99, "/com/example/ecommercestoreprojecttemp/ProductPhotos/refrigerator.png"),
                new Product(3, "Dishwasher", 750.00, "/com/example/ecommercestoreprojecttemp/ProductPhotos/dishwasher.png"),
                new Product(4, "Microwave", 349.99, "/com/example/ecommercestoreprojecttemp/ProductPhotos/microwave.png")
        );

        updateProductView(allProducts);

        // Search box listener
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filterProducts(newValue);
        });

        // Sort button action
        sortButton.setOnAction(e -> {
            sortProductsByPrice();
        });

        // Ensure scrollPane displays content properly
        scrollPane.setContent(productContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }


    //Used for Search & Sort By Lowest
    private void updateProductView(List<Product> productsToShow) {
        productContainer.getChildren().clear();
        for (Product product : productsToShow) {
            addProductToView(product);
        }
    }

    private void addProductToView(Product product) {
        VBox itemBox = new VBox(5);

        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label(String.format("$%.2f", product.getPrice()));
        Button addButton = new Button("Add to Cart");

        ImageView imageView = null;
        if (product.getProductPhoto() != null && !product.getProductPhoto().isEmpty()) {
            try {
                imageView = new ImageView(new Image(getClass().getResource(product.getProductPhoto()).toExternalForm()));
                imageView.setFitWidth(250);
                imageView.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println("Error loading image for: " + product.getName());
            }
        }

        addButton.setOnAction(e -> {
            CartManager.getInstance().addProduct(product, 1);
        });

        if (imageView != null) {
            itemBox.getChildren().add(imageView);
        }
        itemBox.getChildren().addAll(nameLabel, priceLabel, addButton);
        productContainer.getChildren().add(itemBox);
    }

    //Keyword Search function
    private void filterProducts(String searchText) {
        String lowerText = searchText.toLowerCase();
        List<Product> filtered = allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerText))
                .collect(Collectors.toList());
        updateProductView(filtered);
    }

    //Lowest to Highest Sort
    private void sortProductsByPrice() {
        List<Product> sorted = allProducts.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
        updateProductView(sorted);
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
