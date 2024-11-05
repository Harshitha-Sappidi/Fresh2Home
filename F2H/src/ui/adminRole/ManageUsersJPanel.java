package ui.adminRole;

import business.Business;
import business.ConfigureASystem;
import business.enterprise.Enterprise;
import business.network.Network;
import business.organization.Organization;
import business.userAccount.UserAccount;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harshitha
 */
public class ManageUsersJPanel extends javax.swing.JPanel {

    private Business business;
    private ConfigureASystem configureASystem;
    private JPanel userProcessContainer;
    private UserAccount account;
    private Network network;
    private Enterprise enterprise;

    public ManageUsersJPanel(JPanel userProcessContainer, UserAccount account, Business business, Network network, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.business = business;
        this.network = network;
        this.enterprise = enterprise;
        refreshTable();
    }

    public void refreshTable() {
        int rowCount = userTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for (Network network : business.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganisationList()) {
                    // Assuming you want to filter by specific types of enterprises and organizations
                    for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                        Object[] row = new Object[3];
                        row[0] = ua.getUsername();
                        //row[1] = ua.getRole().getRoleType(ua);
                        row[1] = organization.getName(); // Use the organization obtained earlier
                        row[2] = enterprise.getName(); // Use the enterprise obtained earlier
                        model.addRow(row);

                        System.out.println("userlist: " + organization.getUserAccountDirectory().getUserAccountList());

                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 235, 230));

        userTable.setBackground(new java.awt.Color(255, 204, 204));
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Organisation", "Enterprise"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            userTable.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        lblTitle.setFont(new java.awt.Font("Khmer MN", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(212, 67, 30));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-batch-assign-30.png"))); // NOI18N
        lblTitle.setText("Manage Users");

        jLabel2.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 67, 30));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-back-button-30.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 67, 30));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-delete-user-34.png"))); // NOI18N
        jLabel1.setText("Delete");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/manageusers.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 187, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addContainerGap(254, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        AdminDashboardJPanel panel = new AdminDashboardJPanel(userProcessContainer, account, business, network, enterprise);
        userProcessContainer.add("AdminDashboardJPanel", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
                AdminDashboardJPanel panel = new AdminDashboardJPanel(userProcessContainer, account, business, network, enterprise);
        userProcessContainer.add("AdminDashboardJPanel", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables

}
