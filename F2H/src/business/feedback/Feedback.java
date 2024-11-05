/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.feedback;

import java.util.Date;

/**
 *
 * @author sushmaka
 */
public class Feedback {
    private int feedId;
    private int ideaId;
    private String details;
    private String author;
    private int personId;
    private Date date;

    public Feedback(int feedId, int ideaId, String details, String author, int personId) {
        this.feedId = feedId;
        this.ideaId = ideaId;
        this.details = details;
        this.author = author;
        this.date = new Date();
        this.personId = personId;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
