/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.Idea;

import java.util.ArrayList;

/**
 *
 * @author sushmaka
 */
public class IdeaDirectory {
    
     private ArrayList<Idea> ideaList;

    public IdeaDirectory() {
        ideaList = new ArrayList<Idea>();
    }
    

    public ArrayList<Idea> getIdeaList() {
        return ideaList;
    }

    public void setIdeaList(ArrayList<Idea> ideaList) {
        this.ideaList = ideaList;
    }
    
    public void addIdea(Idea idea) {
        if (ideaList == null) {
            ideaList = new ArrayList<Idea>();
        }
        ideaList.add(idea);
    }
    

    
    public Idea getIdeaById(int id) {
        for (Idea idea : ideaList) {
            if (idea.getIdeaId()== id) { 
                return idea;
            }
        }
        return null; 
    }
    
    public void removeIdea(Idea i) {
        ideaList.remove(i);
    }
}
