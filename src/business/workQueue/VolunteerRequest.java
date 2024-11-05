/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workQueue;

import java.util.Date;

/**
 *
 * @author bhavanidevulapalli
 */
public class VolunteerRequest extends WorkRequest {

    private Status status;

    private String VolunteerPersonName;

    private String CustomerName;

    private String Location;

    private int RequestID;

    private Date RequestDate;
    private static int count = 1;

    private Date RequestCompletedDate;
    private String Comments;

    public VolunteerRequest() {

        this.RequestID = count++;
    }

    public String getVolunteerPersonName() {
        return VolunteerPersonName;
    }

    public void setVolunteerPersonName(String VolunteerPersonName) {
        this.VolunteerPersonName = VolunteerPersonName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int RequestID) {
        this.RequestID = RequestID;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        VolunteerRequest.count = count;
    }

    public enum Status {
        RequestRaised,
        RequestResolved

    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(Date RequestDate) {
        this.RequestDate = RequestDate;
    }

    public Date getRequestCompletedDate() {
        return RequestCompletedDate;
    }

    public void setRequestCompletedDate(Date RequestCompletedDate) {
        this.RequestCompletedDate = RequestCompletedDate;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    
    
    

}
