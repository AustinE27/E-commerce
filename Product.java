/**
    Product datatype tester for CartItem.
    Updated to show pictures per product.
    @author J. Hernandez-Velazquez
    @version 2.0
 */
package com.example.ecommercestoreprojecttemp;


import java.util.ArrayList;
import java.util.List;

public class Product {
    private int sku;
    private String name;
    private double price;
    private String productPhoto;

    public Product(int sku, String name, double price, String productPhoto) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.productPhoto = productPhoto;
    }

    public int getSku() {
        return sku;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getProductPhoto() {
        return productPhoto;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }

}