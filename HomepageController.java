/**
 * Controller for our site homepage.
 * Dynamically loads and displays products from the database to purchase.
 * and allows users to add items to the cart or navigate to the cart view.
 * 
 * Updated to V Box Display, enabled search and sorting for items.
 * @author Danny Carpio-Tovar & J. Hernandez-Velazquez
 * @version 2.0
 */
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
import java.util.List;
import java.util.stream.Collectors;

public class HomepageController {

    /**
     * Container for displaying product entries.
     */
    @FXML private VBox productContainer;

    /**
     * Button that opens the cart view.
     */
    @FXML private Button cartButton;

    /**
     * ScrollPane that wraps the product container.
     */
    @FXML private ScrollPane scrollPane;

    /**
     * Text field used to search for products by name.
     */
    @FXML private TextField searchBox;

    /**
     * Button to sort the product list by price.
     */
    @FXML private Button sortButton;

    /**
     * List holding all products fetched from the database.
     */
    private List<Product> allProducts;

    /**
     * Called when the FXML is initiated.
     * Initializes product list, sets up search and sorting behavior.
     */
    @FXML
    public void initialize() {
        /**
         * Fetch products dynamically from the database.
         */
        allProducts = ProductDAO.getProducts();

        if (allProducts.isEmpty()) {
            System.out.println("No products found in the database.");
        } else {
            updateProductView(allProducts);
        }

        /**
         * Search box listener for filtering products dynamically.
         */
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> filterProducts(newValue));

        /**
         * Sort button action for sorting products by price.
         */
        sortButton.setOnAction(e -> sortProductsByPrice());

        /**
         * Ensure ScrollPane shows content properly.
         */
        scrollPane.setContent(productContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    /**
     * Replaces the current product view with a given list of products.
     *
     * @param productsToShow list of products to display
     */
    private void updateProductView(List<Product> productsToShow) {
        productContainer.getChildren().clear();
        for (Product product : productsToShow) {
            addProductToView(product);
        }
    }

    /**
     * Adds a single product's info to the product homepage display.
     *
     * @param product the product to add
     */
    private void addProductToView(Product product) {
        VBox itemBox = new VBox(5);

        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label(String.format("$%.2f", product.getPrice()));
        Button addButton = new Button("Add to Cart");

        /**
         * Load product image dynamically from the given image path.
         */
        ImageView productImage = new ImageView();
        productImage.setFitWidth(250);
        productImage.setPreserveRatio(true);

        String imagePath = product.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                productImage.setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading image for: " + product.getName());
            }
        }

        addButton.setOnAction(e -> CartManager.getInstance().addProduct(product, 1));

        itemBox.getChildren().add(productImage);
        itemBox.getChildren().addAll(nameLabel, priceLabel, addButton);
        productContainer.getChildren().add(itemBox);
    }

    /**
     * Filters the list of products based on the text entered into the search box.
     *
     * @param searchText the text to filter product names by
     */
    private void filterProducts(String searchText) {
        String lowerText = searchText.toLowerCase();
        List<Product> filtered = allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerText))
                .collect(Collectors.toList());
        updateProductView(filtered);
    }

    /**
     * Sorts the list of products by price in ascending order and updates the view.
     */
    private void sortProductsByPrice() {
        List<Product> sorted = allProducts.stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .collect(Collectors.toList());
        updateProductView(sorted);
    }

    /**
     * Opens the cart view in a new window by loading the cart.fxml layout.
     */
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
