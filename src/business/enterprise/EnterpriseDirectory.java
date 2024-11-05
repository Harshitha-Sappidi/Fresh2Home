/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.enterprise;

import java.util.ArrayList;

/**
 *
 * @author bhavanidevulapalli
 */
public class EnterpriseDirectory {

    private ArrayList<Enterprise> enterpriseList;

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(ArrayList<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<Enterprise>();
    }

    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type, String address) {

        Enterprise enterprise = null;

        if (null != type) switch (type) {
            case PRODUCTIONENTERPRISE -> {
                enterprise = new ProductionEnterprise(name);
                enterpriseList.add(enterprise);
            }
            case QUALITYASSURANCEENTERPRISE -> {
                enterprise = new QualityAssuranceEnterprise(name);
                enterpriseList.add(enterprise);
            }
            case SALESANDDELIVERYENTERPRISE -> {
                enterprise = new SalesAndDeliveryEnterprise(name);
                enterpriseList.add(enterprise);
            }
            case KNOWLEDGETRANSFERENTERPRISE -> {
                enterprise = new KnowledgeTransferEnterprise(name);
                enterpriseList.add(enterprise);
            }
            default -> {
            }
        }

        return enterprise;
    }

    
}
