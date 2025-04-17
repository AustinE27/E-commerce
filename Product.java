/**
    TEMP Product datatype TESTER for CartItem.
    @author J. Hernandez-Velazquez
    @version 1.0
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int sku;
    private String name;
    private double price;

    public Product(int sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return name + " - $" + price;
    }

    public static class TestProductProvider {
        public static List<Product> getSampleProducts() {
            List<Product> products = new ArrayList<>();
            products.add(new Product(101, "Wireless Mouse", 19.99));
            products.add(new Product(102, "Mechanical Keyboard", 79.99));
            products.add(new Product(103, "HD Monitor", 149.99));
            return products;
        }
    }
}