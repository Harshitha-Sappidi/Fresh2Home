/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.enterprise;

import business.organization.Organization;
import business.organization.OrganizationDirectory;

/**
 *
 * @author bhavanidevulapalli
 */
public class Enterprise extends Organization{
    
    private EnterpriseType enterpriseType;
    private OrganizationDirectory organizationDirectory;

    public Enterprise(String name, EnterpriseType type) {
        super(name);
        this.enterpriseType = type;
        organizationDirectory = new OrganizationDirectory();
    }
    
    public enum EnterpriseType{
        PRODUCTIONENTERPRISE("Production Enterprise"),
        QUALITYASSURANCEENTERPRISE("Quality Assurance Enterprise"),
        KNOWLEDGETRANSFERENTERPRISE("Knowledge Transfer Enterprise"),
        SALESANDDELIVERYENTERPRISE("Sales & Delivery Enterprise");
        
        private String value;
        
        private EnterpriseType(String value){
            this.value=value;
        }
        public String getValue() {
            return value;
        }
        @Override
        public String toString(){
        return value;
    }
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }
    
  
    
     public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }
}


 