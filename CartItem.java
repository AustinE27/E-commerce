/**
    A user's Cart requires a selection
    of quantity and removal.
    @author J. Hernandez-Velazquez
    @version 1.0
 */
package model;

/**
    Represents an individual item added
    to a user's shopping cart.
 */
public class CartItem {
    /**
        Product datatype of
        cart item.
     */
    private Product product;

    /**
        Quantity of the
        selected product.
     */
    private int quantity;

    /**
        Creates a new cart item with a specific
        product and quantity selected.
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
        Returns the product tied to this
        cart item for sku, price, name.
     */
    public Product getProduct() {
        return product;
    }

    /**
        Retrieves the current number
        of this product in the cart.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
        Updates the quantity of the product
        in the user's cart.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
        Calculates the sum of items based
        on product price and quantity.
     */
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    /*
        Retrieves the image file linked
        to this product (currently unused).
     */
    // public png getImage() {
    //     return product.getImage();
    // }
}