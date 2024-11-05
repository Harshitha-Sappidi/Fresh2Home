/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.customer;

/**
 *
 * @author bhavanidevulapalli
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private String customerUserName;
    private Date orderDate;
    private double totalAmount;
    private String orderStatus;
    private Cart cart;
    public static List<Order> ordersList = new ArrayList<>();
    private static int nextID = 1;
      private Date DeliveryDate;
       private int requestId; 
      
     

    // Constructor
    public Order(String customerUserName, Date orderDate, double totalAmount, String orderStatus, Cart cart) {
        this.orderId = nextID++;
        this.customerUserName = customerUserName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.cart = cart;
     
        
    }
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerUserName;
    }

    public void setCustomerId(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(Date DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

      public void updateOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
        this.DeliveryDate = new Date(); // Set the status change date to the current system date
    }
       public static Order getOrderById(int orderId) {
        for (Order order : ordersList) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null; // Return null if the order with the specified ID is not found
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

  
       
}
