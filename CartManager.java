/**
 * Handles all cart-related operations in the e-commerce application.
 * Supports adding items, updating quantities and retrieving the current cart.
 *
 * Updated to fix item quantity bug.
 * @author J. Hernandez-Velazquez
 * @version 2.0
 */
package com.example.ecommercestoreprojecttemp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages the shopping cart functionality using a singleton pattern.
 * Allows adding products, updating quantities, retrieving items, and clearing the cart.
 */
public class CartManager {

    /**
     * Singleton instance of CartManager.
     */
    private static com.example.ecommercestoreprojecttemp.CartManager instance;

    /**
     * Internal list of cart items.
     */
    private final ObservableList<CartItem> cartItems = FXCollections.observableArrayList();

    /**
     * Private constructor to prevent external instantiation.
     */
    private CartManager() {
    }

    /**
     * Returns the singleton instance of CartManager.
     *
     * @return the shared CartManager instance
     */
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    /**
     * Adds a product to the cart or updates its quantity if already present.
     *
     * @param product  the product to add
     * @param quantity the quantity to add
     */
    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getSku() == product.getSku()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    /**
     * Returns all cart items as an observable list.
     *
     * @return observable list of cart items
     */
    public ObservableList<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Updates the quantity of a specific product in the cart.
     *
     * @param sku      the SKU of the product to update
     * @param quantity the new quantity (removes item if quantity <= 0)
     */
    public void updateItemQuantity(int sku, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getSku() == sku) {
                if (quantity <= 0) {
                    cartItems.remove(item);
                } else {
                    item.setQuantity(quantity);
                }
                break;
            }
        }
    }

    /**
     * Clears all items from the cart.
     */
    public void clearCart() {
        cartItems.clear();
    }
}
