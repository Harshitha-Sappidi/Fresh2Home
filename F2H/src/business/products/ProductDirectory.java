/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.products;

import java.util.ArrayList;

/**
 *
 * @author sushmaka
 */
public class ProductDirectory {

    private ArrayList<Product> productList;

    public ProductDirectory() {
        this.productList = new ArrayList<Product>();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public Product getProductFromProductID(int productID) {
        for (Product product : this.productList) {
            if (product.getProductId() == productID) {
                return product;
            }
        }
        return null;
    }

    public double calculateEstimationCost(double pricePerPound, int quantityInPounds) {
        return pricePerPound * quantityInPounds;
    }

      public void removeProduct(Product product) {
        productList.remove(product);
    }
}
