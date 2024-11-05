/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workQueue;

import business.workQueue.ResearcherWorkRequest.Status;
import java.util.Date;

/**
 *
 * @author sushmaka
 */
public class ResearcherWorkRequest extends WorkRequest{
    
    private int requestId;
    private Date requestDate;
    private String researcherName;
    private String researcherContact;
    private String volunteerName;
    private String volunteerContact;
    private Status reqStatus;
    private String description;
    private String resumePdfUrl;
    private int count = 1;
    
    public enum Status {
        PENDING,
        PROCESSING,
        ACCEPTED,
        REJECTED

    }

    public ResearcherWorkRequest() {
        this.requestId = count++;
        this.requestDate = new Date();
        this.reqStatus = Status.PENDING;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getResearcherName() {
        return researcherName;
    }

    public void setResearcherName(String researcherName) {
        this.researcherName = researcherName;
    }

    public String getResearcherContact() {
        return researcherContact;
    }

    public void setResearcherContact(String researcherContact) {
        this.researcherContact = researcherContact;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerContact() {
        return volunteerContact;
    }

    public void setVolunteerContact(String volunteerContact) {
        this.volunteerContact = volunteerContact;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResumePdfUrl() {
        return resumePdfUrl;
    }

    public void setResumePdfUrl(String resumePdfUrl) {
        this.resumePdfUrl = resumePdfUrl;
    }

    public Status getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Status reqStatus) {
        this.reqStatus = reqStatus;
    }

   
    
    
    
    
    
}
