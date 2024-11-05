/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.role;

import business.Business;
import business.enterprise.Enterprise;
import business.network.Network;
import business.userAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author bhavanidevulapalli
 */
public abstract class Role {

    public enum RoleType {

        FARMER("Farmer"),
        POULTRY("Poultry Producers"),
        HONEYANDOTHER("Honey & Other Commodities Cultivator"),
        RESEARCHER("Researcher"),
        DELIVERYPERSON("Delivery Person"),
        VOLUNTEER("Volunteer"),
        CERTIFICATIONSPECIALIST("Certification Specialist"),
        CUSTOMER("Customer"),
        ADMIN("Admin");

        private String value;

        private RoleType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public RoleType getRoleType(UserAccount userAccount) {
        // Retrieve the role associated with the user account
        RoleType roleType = null;
        if (userAccount != null && userAccount.getRole() != null) {
            // Check the class type of the user account's role
            for (RoleType type : RoleType.values()) {
                if (userAccount.getRole().getClass().getSimpleName().equals(type.name())) {
                    roleType = type;
                    break;
                }
            }
        }
        return roleType;
    }

    public abstract JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Business business, Network network, Enterprise enterprise);

}
