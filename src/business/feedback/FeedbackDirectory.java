/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.feedback;

import java.util.ArrayList;

/**
 *
 * @author sushmaka
 */
public class FeedbackDirectory {
    
     private ArrayList<Feedback> feedbackList;

    public FeedbackDirectory() {
    }
    

    public FeedbackDirectory(ArrayList<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(ArrayList<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }
    
    public void addFeed(Feedback feed) {
        if (feedbackList == null) {
            feedbackList = new ArrayList<Feedback>();
        }
        feedbackList.add(feed);
    }
    
    public ArrayList<Feedback> getFeedbackListByIdeaId(int ideaId) {
        ArrayList<Feedback> filteredList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            if (feedback.getIdeaId() == ideaId) {
                filteredList.add(feedback);
            }
        }
        return filteredList;
    }
    
    public void removeFeedbackByIdeaId(int ideaId) {
        feedbackList.removeIf(feedback -> feedback.getIdeaId() == ideaId);
    }   
    
    public Feedback getFeedbackById(int feedId) {
        for (Feedback feedback : feedbackList) {
            if (feedback.getFeedId() == feedId) {
                return feedback;
            }
        }
        return null; // If feedback with given ID is not found
    }
    
}
