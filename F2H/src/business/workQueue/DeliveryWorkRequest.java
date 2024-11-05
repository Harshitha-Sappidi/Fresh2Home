/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workQueue;

import business.customer.Order;

/**
 *
 * @author bhavanidevulapalli
 */
public class DeliveryWorkRequest extends WorkRequest {

    private Status status;

    private String deliveryAddress;
    private String deliveryPersonName;
    private Status deliveryStatus;
    private String CustomerName;
    private Order order;
   
    private  int RequestID;
    private static int count=1;
    
    public DeliveryWorkRequest() {
        deliveryStatus = Status.OrderPlaced; // Default status is ReadyToShip
        this.RequestID=count++;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPersonName() {
        return deliveryPersonName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        this.deliveryPersonName = deliveryPersonName;
    }

    public Status getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Status deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        // You can customize the output as needed
        return "Delivery Work Request: " + deliveryPersonName + " - " + deliveryStatus;
    }

    public enum Status {
        OrderPlaced,
        InTransit,
        Delivered

    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
//    

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int RequestID) {
        this.RequestID = RequestID;
    }
    

//    public static int getRequestID() {
//        return RequestID;
//    }
//
//    public static void setRequestID(int RequestID) {
//        DeliveryWorkRequest.RequestID = RequestID;
//    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
