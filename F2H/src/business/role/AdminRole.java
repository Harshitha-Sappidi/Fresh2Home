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
import ui.adminRole.AdminDashboardJPanel;

/**
 *
 * @author bhavanidevulapalli
 */
public class AdminRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Business business, Network network, Enterprise enterprise) {
        return new AdminDashboardJPanel( userProcessContainer,  account,  business,  network,  enterprise);
    }

   
}
