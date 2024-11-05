/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workQueue;

import java.util.ArrayList;

/**
 *
 * @author bhavanidevulapalli
 */
public class WorkQueue {
    
    private  ArrayList<WorkRequest> workRequestList;

    public WorkQueue() {
        workRequestList = new ArrayList<>();
    }

    public ArrayList<WorkRequest> getWorkRequestList() {
        return workRequestList;
    }
    
    public void addWorkRequest(WorkRequest workRequest) {
        workRequestList.add(workRequest);
    }
    
   
     public WorkRequest getWorkRequestByRequestId(int requestId) {
        for (WorkRequest workRequest : workRequestList) {
            if (workRequest instanceof DeliveryWorkRequest) {
                DeliveryWorkRequest deliveryWorkRequest = (DeliveryWorkRequest) workRequest;
             
                if (deliveryWorkRequest.getRequestID() == requestId) {
                    return workRequest;
                }
            }
        }
        return null; // Return null if the work request with the specified request ID is not found
    }
     
     
    
    
}
