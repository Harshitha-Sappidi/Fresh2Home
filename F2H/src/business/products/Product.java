/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.products;

import javax.swing.ImageIcon;

/**
 *
 * @author sushmaka
 */
public class Product {

    private int productId;
    private String Productname;
    private String category;
    private double price;
    private int quantity;
    private String description;
    private String farmersId;
    private ImageIcon image;
    private Status status;

    private static int count = 1;
    private double offeredPrice;

    public Product() {
    }

    public Product(String Productname, String category, double price, int quantity, String description, ImageIcon image, String farmersId, Status status, double OfferedPrice) {

        this.productId = count++;
        this.Productname = Productname;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.farmersId = farmersId;
        this.status = status;

        this.offeredPrice = OfferedPrice;

    }

    public Product(int productID, String productName, double productPrice, int productQuantity) {
        this.productId = productID;
        this.Productname = productName;
        this.price = productPrice;
        this.quantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return Productname;
    }

    public void setName(String name) {
        this.Productname = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String Productname) {
        this.Productname = Productname;
    }

    public String getFarmersId() {
        return farmersId;
    }

    public void setFarmersId(String farmersId) {
        this.farmersId = farmersId;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String toString() {
        return Productname;
    }

    public enum Status {
        TO_BE_REVIEWED,
        AWAITING_FARMER_APPROVAL,
        REVIEWED

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }
    
    

}
