/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.volunteerRole;

import business.Business;
import business.enterprise.Enterprise;
import business.enterprise.KnowledgeTransferEnterprise;
import business.network.Network;
import business.organization.Organization;
import business.organization.VolunteersOrganization;
import business.userAccount.UserAccount;
import business.workQueue.VolunteerRequest;
import business.workQueue.WorkRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bhavanidevulapalli
 */
public class VolunteerDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VolunteerDashboardJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private Business business;
    private Network network;
    private Enterprise enterprise;

    private WorkRequest request;
    private boolean view = false;
    private boolean licenPlateCaptured = false;
     private Organization org;

    public VolunteerDashboardJPanel(JPanel userProcessContainer, UserAccount account, Business business, Network network, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.business = business;
        this.network = network;

        populateTable();
//        viewButtons(view);
//     
//        additionalDetailsTable.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRequestSummary = new javax.swing.JTable();
        btnAssignTome = new javax.swing.JButton();
        UpdatePanel = new javax.swing.JPanel();
        lblOrderID = new javax.swing.JLabel();
        txtFarmersID = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        lblMessages = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtComments = new javax.swing.JTextField();
        StatusComboBox = new javax.swing.JComboBox<>();
        DeliveredBy = new javax.swing.JLabel();
        txtDeliveredBy = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        lblRequestID = new javax.swing.JLabel();
        txtRequestID = new javax.swing.JTextField();
        txtOrderId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        label.setFont(new java.awt.Font("Khmer MN", 1, 24)); // NOI18N
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("Volunteer Dashboard");

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));

        tblRequestSummary.setBackground(new java.awt.Color(255, 208, 151));
        tblRequestSummary.setFont(new java.awt.Font("Khmer MN", 0, 14)); // NOI18N
        tblRequestSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestID", "FarmerID", "Date", "Status", "Location", "Comments", "Delivered On", "Delivered By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRequestSummary);

        btnAssignTome.setBackground(new java.awt.Color(255, 153, 51));
        btnAssignTome.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnAssignTome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-batch-assign-30.png"))); // NOI18N
        btnAssignTome.setText("Assign To Me ");
        btnAssignTome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignTomeActionPerformed(evt);
            }
        });

        UpdatePanel.setBackground(new java.awt.Color(255, 208, 151));

        lblOrderID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblOrderID.setText("Farmers ID");

        txtFarmersID.setEditable(false);
        txtFarmersID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        lblDate.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblDate.setText("Date :");

        lblMessages.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblMessages.setText("Message For Customer :");

        lblStatus.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblStatus.setText("Status :");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        txtComments.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        StatusComboBox.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "RequestRaised", "RequestResolved" }));
        StatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusComboBoxActionPerformed(evt);
            }
        });

        DeliveredBy.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        DeliveredBy.setText("Delivered By :");

        txtDeliveredBy.setEditable(false);
        txtDeliveredBy.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        btnSubmit.setBackground(new java.awt.Color(201, 226, 244));
        btnSubmit.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-shipment-logistic-30.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        lblRequestID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblRequestID.setText("Request ID :");

        txtRequestID.setEditable(false);
        txtRequestID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        txtRequestID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRequestIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpdatePanelLayout = new javax.swing.GroupLayout(UpdatePanel);
        UpdatePanel.setLayout(UpdatePanelLayout);
        UpdatePanelLayout.setHorizontalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDate)
                            .addComponent(lblStatus)
                            .addComponent(lblMessages)
                            .addComponent(DeliveredBy)
                            .addComponent(lblRequestID)
                            .addComponent(lblOrderID))
                        .addGap(68, 68, 68)
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDeliveredBy, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFarmersID)
                            .addComponent(StatusComboBox, 0, 283, Short.MAX_VALUE)
                            .addComponent(txtComments)
                            .addComponent(txtRequestID))
                        .addGap(41, 41, 41))
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        UpdatePanelLayout.setVerticalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOrderID)
                    .addComponent(txtFarmersID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMessages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeliveredBy)
                    .addComponent(txtDeliveredBy, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRequestID)
                    .addComponent(txtRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubmit)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/canva-delivery-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnAssignTome)
                        .addGap(27, 27, 27)
                        .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 359, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAssignTome)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 1786, Short.MAX_VALUE)
                .addGap(488, 488, 488))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(46, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1218, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAssignTomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignTomeActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblRequestSummary.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Pls select a row!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UpdatePanel.setVisible(true);
        Object valueAt = tblRequestSummary.getValueAt(selectedRow, 1);
        txtFarmersID.setText(String.valueOf(valueAt));

        int RequestID = (int) tblRequestSummary.getValueAt(selectedRow, 0);
        txtRequestID.setText(String.valueOf(RequestID));
        ArrayList<WorkRequest> workRequestList = org.getWorkQueue().getWorkRequestList();

        String Status = (String) StatusComboBox.getSelectedItem();
        if (Status == "RequestResolved") {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(new Date());
            txtDate.setText(formattedDate);
        } else {
            for (WorkRequest request : workRequestList) {
                if (((VolunteerRequest) request).getRequestID() == RequestID) {
                    Date requestDate = ((VolunteerRequest) request).getRequestDate();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(requestDate); // Format the date
                    txtDate.setText(formattedDate); // Set the formatted date to the text field
                }
            }
        }

        txtComments.setText(txtComments.getText());

        txtDeliveredBy.setText(account.getUsername());
    }//GEN-LAST:event_btnAssignTomeActionPerformed

    private void StatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusComboBoxActionPerformed

    }//GEN-LAST:event_StatusComboBoxActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        try {
            // TODO add your handling code here:
            String FarmersID = txtFarmersID.getText();

            String Status = (String) StatusComboBox.getSelectedItem();

            String DeliveryDate = txtDate.getText();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(DeliveryDate);

            String RequestID = txtRequestID.getText();
            int requestIdInt = Integer.parseInt(RequestID);
            ArrayList<WorkRequest> workRequestList = org.getWorkQueue().getWorkRequestList();
            for (WorkRequest request : workRequestList) {
                if (request instanceof VolunteerRequest) {
                    if (((VolunteerRequest) request).getRequestID() == requestIdInt) {
                        request.setReceiver(account);
                        ((VolunteerRequest) request).setStatus(Status);

                        ((VolunteerRequest) request).setRequestCompletedDate(date);
                    }

                    populateTable();
                    UpdatePanel.setVisible(false);
                }

            }
            populateTable();
            UpdatePanel.setVisible(false);
        } catch (ParseException ex) {
            // Logger.getLogger(DeliveryDashboardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblRequestSummary.getModel();
        model.setRowCount(0);

        ArrayList<Network> networkList = business.getNetworkList();
        for (Network network : networkList) {
            ArrayList<Enterprise> enterpriseList = network.getEnterpriseDirectory().getEnterpriseList();
            for (Enterprise enterprise : enterpriseList) {
                if (enterprise instanceof KnowledgeTransferEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganisationList()) {
                        if (organization instanceof VolunteersOrganization) {
                            org = organization;
                            break;
                        }
                    }
                }
            }
        }

        Object[] row = new Object[10];

        if (null != org) {
            for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
                if (request instanceof VolunteerRequest) {
                    VolunteerRequest volunteerWorkRequest = (VolunteerRequest) request;

                    row[0] = volunteerWorkRequest.getRequestID();
                    row[1] = volunteerWorkRequest.getSender().getUsername();
                    row[2] = volunteerWorkRequest.getRequestDate();
                    row[3] = volunteerWorkRequest.getStatus();
                    row[4] = volunteerWorkRequest.getLocation();
                    row[5] = volunteerWorkRequest.getMessage();
                    String status = volunteerWorkRequest.getStatus();

                    if (status.equalsIgnoreCase("RequestResolved")) {
                        row[6] = volunteerWorkRequest.getRequestCompletedDate();
                        row[7] = volunteerWorkRequest.getReceiver().getUsername();
                    }
                }

                model.addRow(row);
                tblRequestSummary.setRowHeight(150);
            }
        }
    }
    private void txtRequestIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRequestIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRequestIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DeliveredBy;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JPanel UpdatePanel;
    private javax.swing.JButton btnAssignTome;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMessages;
    private javax.swing.JLabel lblOrderID;
    private javax.swing.JLabel lblRequestID;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblRequestSummary;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDeliveredBy;
    private javax.swing.JTextField txtFarmersID;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtRequestID;
    // End of variables declaration//GEN-END:variables
}
