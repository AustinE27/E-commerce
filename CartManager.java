/**
    A user's Cart contains the user's
    selected Cart Items for checkout.
    @author J. Hernandez-Velazquez
    @version 1.0
 */
package model;
import java.util.*;

/**
    Handles operations related to the
    user's shopping cart: adding items
    to cart and calculating totals.
 */
public class CartManager {
    // Maps SKU identifiers to their associated cart items
    private final Map<Integer, CartItem> cartItems = new HashMap<>();

    /**
        Adds a product to the cart. If the product
        already exists, its quantity is increased.
     */
    public void addToCart(Product product, int quantity) {
        CartItem item = cartItems.get(product.getSku());
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            cartItems.put(product.getSku(), new CartItem(product, quantity));
        }
    }

    /**
        Returns a list of all items currently
        in the user's shopping cart.
     */
    public List<CartItem> getAllItems() {
        return new ArrayList<>(cartItems.values());
    }

    /**
        Calculates and returns the total cost
        of all items in the shopping cart.
     */
    public double getTotal() {
        return cartItems.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
