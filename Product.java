/**
    TEMP Product datatype TESTER for CartItem.
    @author J. Hernandez-Velazquez
    @version 2.0
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

}