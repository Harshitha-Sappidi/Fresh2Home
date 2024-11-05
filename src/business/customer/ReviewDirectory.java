/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.customer;

import java.util.ArrayList;

/**
 *
 * @author sushmaka
 */
public class ReviewDirectory {
    
    private ArrayList<Review> reviewList;

    public ReviewDirectory() {
        reviewList = new ArrayList<>();
    }

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
    }
    
    
    
    
}
