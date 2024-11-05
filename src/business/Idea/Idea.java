/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.Idea;

import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author sushmaka
 */
public class Idea {
    
    private int ideaId;
    private String title;
    private String description;
    private boolean isImplemented;
    private Date createdDate;
    private String author;
    private ImageIcon ideaImage;
    private int personId;

    public Idea() {
        
    }
    
    public Idea( int ideaId, String title, String description, ImageIcon ideaImage, String author, int personId) {
        this.ideaId = ideaId;
        this.title = title;
        this.description = description;
        this.ideaImage = ideaImage;
        this.isImplemented = false;
        this.author = author;
        this.personId = personId;
        this.createdDate = new Date();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsImplemented() {
        return isImplemented;
    }

    public void setIsImplemented(boolean isImplemented) {
        this.isImplemented = isImplemented;
    }

    public ImageIcon getIdeaImage() {
        return ideaImage;
    }

    public void setIdeaImage(ImageIcon ideaImage) {
        this.ideaImage = ideaImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
}
