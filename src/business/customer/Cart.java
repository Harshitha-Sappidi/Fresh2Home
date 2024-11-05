/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.customer;

import business.products.Product;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private int cartID;
    private String customerID;
    private Map<Integer, Product> productMap;
    private static int nextInt = 1;

    // Default constructor
    public Cart(String customerID) {
        this.cartID = nextInt++;
        this.customerID = customerID;
        this.productMap = new HashMap<>();
    }

    // Getter for cartID
    public int getCartID() {
        return cartID;
    }

    // Setter for cartID
    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    // Getter for customerID
    public String getCustomerID() {
        return customerID;
    }

    // Setter for customerID
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    // Getter for productMap
    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    // Setter for productMap
    public void setProductMap(Map<Integer, Product> productMap) {
        this.productMap = productMap;
    }

    // Method to add products to the cart
    public void addProduct(int productID, Product product) {
        productMap.put(productID, product);
    }

}
