/**
 * Product datatype tester for CartItem.
 *
 * Updated to show pictures per product.
 * @author J. Hernandez-Velazquez
 * @version 2.0
 */
package com.example.ecommercestoreprojecttemp;


public class Product {

    /**
     * Stock Keeping Unit (unique product identifier).
     */
    private int sku;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Price of the product.
     */
    private double price;

    /**
     * URL or path to the product photo.
     */
    private String productPhoto;

    /**
     * Constructs a Product with the given SKU, name, price, and photo.
     *
     * @param sku           unique identifier for the product
     * @param name          name of the product
     * @param price         price of the product
     * @param productPhoto  photo URL or file path for the product
     */
    public Product(int sku, String name, double price, String productPhoto) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.productPhoto = productPhoto;
    }

    /**
     * Returns the SKU of the product.
     *
     * @return the product's SKU
     */
    public int getSku() {
        return sku;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return the product's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the photo URL or path of the product.
     *
     * @return the product's photo
     */
    public String getProductPhoto() {
        return productPhoto;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return product name and price as a string
     */
    @Override
    public String toString() {
        return name + " - $" + price;
    }

}