/**
    Manages the shopping cart logic.
    Handles adding products, updating quantities, and providing cart data.
    @author J. Hernandez-Velazquez.
    @version 2.0
 */

package model;

import java.util.List;
import java.util.ArrayList;

/**
    Controls operations for adding, updating,
    and retrieving items in the shopping cart.
 */
public class CartManager {

    /**
        Holds the list of all items currently
        present in the shopping cart.
     */
    private final List<CartItem> cartItems = new ArrayList<>();

    /**
        Returns the current list of CartItem objects
        in the shopping cart.
        @return list of cart items
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
        Updates the quantity of a cart item given its
        unique SKU identifier (as int).
        If the item is found, quantity is updated.
     */
    public void updateItemQuantity(int sku, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getSku() == sku) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    /**
        Adds a product to the cart, or if already present,
        increments the quantity.
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
}
