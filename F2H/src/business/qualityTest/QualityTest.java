/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.qualityTest;

/**
 *
 * @author bhavanidevulapalli
 */


import business.products.ProductDirectory;
import javax.swing.ImageIcon;

public class QualityTest {
    private String testComments;
    private int productId;
    private String productName;
    private double actualPrice;
    private int quantity;
    private int estimationCost;
    private ImageIcon Image;
    private String reviewStatus;
    private double OfferedPrice;
    
    private static int requestId;
    private ProductDirectory productDirectory;
    int count;
    public QualityTest()
    {
 
       count++;
    requestId=count;
    ProductDirectory products=new ProductDirectory();
    }

    public String getTestComments() {
        return testComments;
    }

    public void setTestComments(String testComments) {
        this.testComments = testComments;
    }

   
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

   

    

    public ImageIcon getImage() {
        return Image;
    }

    public void setImage(ImageIcon Image) {
        this.Image = Image;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

   

    public static int getRequestId() {
        return requestId;
    }

    public static void setRequestId(int requestId) {
        QualityTest.requestId = requestId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductDirectory getProductDirectory() {
        return productDirectory;
    }

    public void setProductDirectory(ProductDirectory productDirectory) {
        this.productDirectory = productDirectory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEstimationCost() {
        return estimationCost;
    }

    public void setEstimationCost(int estimationCost) {
        this.estimationCost = estimationCost;
    }

    public double getOfferedPrice() {
        return OfferedPrice;
    }

    public void setOfferedPrice(double OfferedPrice) {
        this.OfferedPrice = OfferedPrice;
    }
    
    
}
