/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.customer;

/**
 *
 * @author sushmaka
 */
public class Review {
     private int reviewId;
    private int orderId;
    private String productOwnerId;  // Farmer's user ID who owns the product
    private int rating;
    private String comments;
    private static int nextId = 1;

    public Review(int orderId, String productOwnerId, int rating, String comments) {
        this.reviewId = nextId++;
        this.orderId = orderId;
        this.productOwnerId = productOwnerId;
        this.rating = rating;
        this.comments = comments;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductOwnerId() {
        return productOwnerId;
    }

    public void setProductOwnerId(String productOwnerId) {
        this.productOwnerId = productOwnerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    

    
}
