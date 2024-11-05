/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.organization;

import business.organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author bhavanidevulapalli
 */
public class OrganizationDirectory {
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganisationList() {
        return organizationList;
    }
    
     public Organization createOrganization(Type type){
        Organization organization = null;
        if (type.getValue().equals(Type.FARMERSORGANIZATION.getValue())){
            organization = new FarmersOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.ACCREDITATIONBOARDORGANIZATION.getValue())){
            organization = new AccreditationBoardOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.RESEARCHORGANIZATION.getValue())){
            organization = new ResearchersOrganization();
            organizationList.add(organization);
        }else if (type.getValue().equals(Type.VOLUNTEERINGORGANIZATION.getValue())){
            organization = new VolunteersOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.DELIVERYPRGANIZATION.getValue())){
            organization = new DeliveryOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.ONLINESTORE.getValue())){
            organization = new OnlineStore();
            organizationList.add(organization);
        }
        
        
        
        return organization;
    }
    
     
    
}
