/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import business.Idea.IdeaDirectory;
import business.customer.ReviewDirectory;
import business.feedback.FeedbackDirectory;
import business.network.Network;
import business.organization.Organization;
import business.person.PersonDirectory;
import business.products.ProductDirectory;
import business.report.ReportDirectory;
import java.util.ArrayList;

/**
 *
 * @author bhavanidevulapalli
 */
public class Business extends Organization{
        private static Business business;
        private ArrayList<Network> networkList;
        private ProductDirectory productDirectory;
        private IdeaDirectory ideaDirectory;
        private FeedbackDirectory feedbackDirectory;
        private PersonDirectory personDirectory;
        private ReviewDirectory reviewDirectory;
        
        private ReportDirectory reportDirectory;
 

    public static Business getInstance() 
     {
        if (business == null) 
        {
            business = new Business();
        }
        return business;
    }
     
     


 private Business()
    {
        super(null);
        
        networkList=new ArrayList<>();
        productDirectory=new ProductDirectory();

        ideaDirectory = new IdeaDirectory();

        feedbackDirectory = new FeedbackDirectory();

        personDirectory =new PersonDirectory();
        
        reviewDirectory = new ReviewDirectory();

        reportDirectory = new ReportDirectory();
    }

    public static Business getBusiness() {
        return business;
    }

    public static void setBusiness(Business business) {
        Business.business = business;
    }

    public ProductDirectory getProductDirectory() {
        return productDirectory;
    }

    public void setProductDirectory(ProductDirectory productDirectory) {
        this.productDirectory = productDirectory;
    }


    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public void setPersonDirectory(PersonDirectory personDirectory) {
        this.personDirectory = personDirectory;
    }

    public Network addNetwork()
    {
        Network n=new Network();
        networkList.add(n);
        return n;
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }

    public IdeaDirectory getIdeaDirectory() {
        return ideaDirectory;
    }

    public void setIdeaDirectory(IdeaDirectory ideaDirectory) {
        this.ideaDirectory = ideaDirectory;
    }

    public FeedbackDirectory getFeedbackDirectory() {
        return feedbackDirectory;
    }

    public void setFeedbackDirectory(FeedbackDirectory feedbackDirectory) {
        this.feedbackDirectory = feedbackDirectory;
    }

    public ReviewDirectory getReviewDirectory() {
        return reviewDirectory;
    }

    public void setReviewDirectory(ReviewDirectory reviewDirectory) {
        this.reviewDirectory = reviewDirectory;
    }

    public ReportDirectory getReportDirectory() {
        return reportDirectory;
    }

    public void setReportDirectory(ReportDirectory reportDirectory) {
        this.reportDirectory = reportDirectory;
    }

    
    
    
}
