/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.deliveryRole;

import business.Business;
import business.Idea.Idea;
import business.customer.Order;
import business.enterprise.Enterprise;
import business.enterprise.SalesAndDeliveryEnterprise;
import business.feedback.Feedback;
import business.network.Network;
import business.organization.DeliveryOrganization;
import business.organization.Organization;
import business.userAccount.UserAccount;
import business.workQueue.DeliveryWorkRequest;
import business.workQueue.WorkRequest;
import java.awt.Component;
import java.awt.Image;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author bhavanidevulapalli
 */
public class DeliveryDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DeliveryDashboardJPanel
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

    ImageIcon ideaImage;
    private int userId;

    public DeliveryDashboardJPanel(JPanel userProcessContainer, UserAccount account, Business business, Network network, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.business = business;
        this.network = network;
        this.org = null;
        populateTable();
        UpdatePanel.setVisible(false);
        Feedbacks.setVisible(false);
        
        Delivery.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
        ResearcherStateChanged(evt);
            }
        });

        Delivery.addChangeListener(e -> {
            int selectedIndex = Delivery.getSelectedIndex();
            if (selectedIndex == 1) {
                populateIdeaTable1();
                setupTableTextWrap1();

            }
        });

        tblViewIdea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblViewIdeaMouseClicked(evt);
            }
        });

    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblordersummary.getModel();
        model.setRowCount(0);
        ArrayList<Network> networkList = business.getNetworkList();
        for (Network network : networkList) {
            ArrayList<Enterprise> enterpriseList = network.getEnterpriseDirectory().getEnterpriseList();
            for (Enterprise enterprise : enterpriseList) {
                if (enterprise instanceof SalesAndDeliveryEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganisationList()) {
                        if (organization instanceof DeliveryOrganization) {
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
                if (request instanceof DeliveryWorkRequest) {
                    DeliveryWorkRequest deliveryWorkRequest = (DeliveryWorkRequest) request;

                    row[0] = deliveryWorkRequest.getRequestID();
                    row[1] = deliveryWorkRequest.getOrder().getOrderId();
                    row[2] = deliveryWorkRequest.getOrder().getOrderDate();
                    row[3] = deliveryWorkRequest.getOrder().getCustomerId();
                    row[4] = deliveryWorkRequest.getOrder().getOrderStatus();
                    row[5] = request.getSender().getPerson().getaddress();

                    if (deliveryWorkRequest.getOrder().getOrderStatus() == "Delivered") {
                        row[6] = deliveryWorkRequest.getOrder().getDeliveryDate();
                        row[7] = deliveryWorkRequest.getReceiver().getUsername();
                    } else {
                        row[6] = "Not yet picked for Delivery.";
                        row[7] = "Delivery person not assigned yet.";
                    }
                }

                model.addRow(row);
                tblordersummary.setRowHeight(150);
            }
        }
    }

    private void ResearcherStateChanged(javax.swing.event.ChangeEvent evt) {
        JTabbedPane tabSource = (JTabbedPane) evt.getSource();
        int selectedIndex = tabSource.getSelectedIndex();
        String selectedTabTitle = tabSource.getTitleAt(selectedIndex);

        if ("Blog".equals(selectedTabTitle)) {

            Feedbacks.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    innerTabbedPaneStateChanged(evt);
                }
            });
        }
    }

    private void tblViewIdeaMouseClicked(java.awt.event.MouseEvent evt) {

        if (tblViewIdea1.getSelectedRow() >= 0) {
            Feedbacks.setVisible(false);
        }
    }

    private void innerTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {
        JTabbedPane tabSource = (JTabbedPane) evt.getSource();
        int selectedIndex = tabSource.getSelectedIndex();

        final int VIEW_IDEA_TAB_INDEX = 1;
        final int ADD_FEEDBACK_TAB_INDEX = 2;

        // Handling View Idea tab
        if (selectedIndex == VIEW_IDEA_TAB_INDEX) {
            int selectedRow = tblViewIdea1.getSelectedRow();
            if (selectedRow != -1) {
                int ideaId = Integer.parseInt(tblViewIdea1.getValueAt(selectedRow, 0).toString());
                Idea idea = business.getIdeaDirectory().getIdeaById(ideaId);
                if (idea != null) {
                    txtTitle3.setText(idea.getTitle());
                    updateDescription1.setText(idea.getDescription());
                    ImageIcon imageIcon = idea.getIdeaImage();
                    if (imageIcon != null) {
                        ideaImg2.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                    } else {
                        ideaImg2.setIcon(null);
                        ideaImg2.setText("<No Image>");
                    }
                }
            }
        } else if (selectedIndex == ADD_FEEDBACK_TAB_INDEX) {
            int selectedRow = tblViewIdea1.getSelectedRow();
            if (selectedRow != -1) {
                int ideaId = Integer.parseInt(tblViewIdea1.getValueAt(selectedRow, 0).toString());
                Idea idea = business.getIdeaDirectory().getIdeaById(ideaId);
                if (idea != null) {
                    txtTitle4.setText(idea.getTitle());
                    updateDescription2.setText("");
                }
            }
        }
    }

    public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Delivery = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblordersummary = new javax.swing.JTable();
        btnAssignTome = new javax.swing.JButton();
        UpdatePanel = new javax.swing.JPanel();
        lblOrderID = new javax.swing.JLabel();
        txtOrderID = new javax.swing.JTextField();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Blog = new javax.swing.JPanel();
        btnSearch1 = new javax.swing.JButton();
        txtSearch1 = new javax.swing.JTextField();
        btnGetFeedback1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblViewIdea1 = new javax.swing.JTable();
        Feedbacks = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        feedbackScroll1 = new javax.swing.JScrollPane();
        tblViewFeedback1 = new javax.swing.JTable();
        View = new javax.swing.JPanel();
        txtTitle3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        updateDescription1 = new javax.swing.JTextField();
        ideaImg2 = new javax.swing.JLabel();
        lblImg2 = new javax.swing.JLabel();
        add = new javax.swing.JPanel();
        txtTitle4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        updateDescription2 = new javax.swing.JTextField();
        btnSave2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        header = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 153, 51));

        Delivery.setBackground(new java.awt.Color(255, 153, 51));
        Delivery.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        tblordersummary.setBackground(new java.awt.Color(255, 208, 151));
        tblordersummary.setFont(new java.awt.Font("Khmer MN", 0, 14)); // NOI18N
        tblordersummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestID", "OrderID", "Date", "CustomerID", "Status", "Location", "Delivered On", "Delivered By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblordersummary);

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
        lblOrderID.setText("Order ID :");

        txtOrderID.setEditable(false);
        txtOrderID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        txtOrderID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderIDActionPerformed(evt);
            }
        });

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
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "OrderPlaced", "Delivered" }));
        StatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusComboBoxActionPerformed(evt);
            }
        });

        DeliveredBy.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        DeliveredBy.setText("Delivered By :");

        txtDeliveredBy.setEditable(false);
        txtDeliveredBy.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        txtDeliveredBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeliveredByActionPerformed(evt);
            }
        });

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
                            .addComponent(txtOrderID)
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
                    .addComponent(txtOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblMessages))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdatePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, 0)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeliveredBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDeliveredBy, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRequestID)
                    .addComponent(txtRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubmit)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/TwuB-ezgif.com-crop.gif"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/canva-delivery-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAssignTome)
                        .addGap(27, 27, 27)
                        .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAssignTome)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        Delivery.addTab("View Request", jPanel1);

        Blog.setBackground(new java.awt.Color(0, 102, 153));
        Blog.setPreferredSize(new java.awt.Dimension(1100, 750));

        btnSearch1.setBackground(new java.awt.Color(204, 255, 255));
        btnSearch1.setText("Search");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        txtSearch1.setBackground(new java.awt.Color(204, 255, 255));
        txtSearch1.setText("Enter Id or title");

        btnGetFeedback1.setBackground(new java.awt.Color(204, 255, 255));
        btnGetFeedback1.setText("click me!!");
        btnGetFeedback1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetFeedback1ActionPerformed(evt);
            }
        });

        tblViewIdea1.setBackground(new java.awt.Color(232, 255, 254));
        tblViewIdea1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Title", "Description", "Image", "Created Date", "Author"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tblViewIdea1);

        Feedbacks.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        tblViewFeedback1.setBackground(new java.awt.Color(232, 255, 254));
        tblViewFeedback1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Provided By:", "Feedback", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        feedbackScroll1.setViewportView(tblViewFeedback1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(feedbackScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1601, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(feedbackScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
        );

        Feedbacks.addTab("Feedbacks ", jPanel2);

        View.setBackground(new java.awt.Color(204, 255, 255));

        txtTitle3.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Title:");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Description:");

        updateDescription1.setEnabled(false);
        jScrollPane3.setViewportView(updateDescription1);

        ideaImg2.setBackground(new java.awt.Color(255, 255, 255));
        ideaImg2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        ideaImg2.setText("<No Image>");
        ideaImg2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ideaImg2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ideaImg2.setMaximumSize(new java.awt.Dimension(73, 19));
        ideaImg2.setMinimumSize(new java.awt.Dimension(73, 19));
        ideaImg2.setPreferredSize(new java.awt.Dimension(73, 19));

        lblImg2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblImg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblImg2.setText("Image:");

        javax.swing.GroupLayout ViewLayout = new javax.swing.GroupLayout(View);
        View.setLayout(ViewLayout);
        ViewLayout.setHorizontalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ideaImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );
        ViewLayout.setVerticalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewLayout.createSequentialGroup()
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ViewLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ViewLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel9))
                            .addGroup(ViewLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3))))
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ideaImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImg2))
                        .addGap(35, 35, 35)))
                .addGap(41, 41, 41))
        );

        Feedbacks.addTab("View Idea", View);

        add.setBackground(new java.awt.Color(204, 255, 255));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Idea Title:");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Feedback:");

        jScrollPane5.setViewportView(updateDescription2);

        btnSave2.setText("Save");
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnSave2)))
                .addContainerGap(954, Short.MAX_VALUE))
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE))
                            .addGroup(addLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5)))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))))
        );

        Feedbacks.addTab("Add Feedback", add);

        jLabel13.setBackground(new java.awt.Color(204, 255, 255));
        jLabel13.setForeground(new java.awt.Color(204, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Selected Idea..");

        javax.swing.GroupLayout BlogLayout = new javax.swing.GroupLayout(Blog);
        Blog.setLayout(BlogLayout);
        BlogLayout.setHorizontalGroup(
            BlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
            .addGroup(BlogLayout.createSequentialGroup()
                .addGap(723, 723, 723)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGetFeedback1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Feedbacks)
            .addComponent(jScrollPane9)
        );
        BlogLayout.setVerticalGroup(
            BlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch1)
                    .addComponent(btnSearch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetFeedback1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Feedbacks, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );

        Delivery.addTab("Blog", Blog);

        header.setBackground(new java.awt.Color(255, 153, 51));
        header.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        header.setForeground(new java.awt.Color(0, 102, 153));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-delivery-70.png"))); // NOI18N
        header.setText("Delivery Person Work Area");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Delivery, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Delivery, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtRequestIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRequestIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRequestIDActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        try {
            // TODO add your handling code here:
            String OrderID = txtOrderID.getText();
            int orderIdInt = Integer.parseInt(OrderID);

            String Status = (String) StatusComboBox.getSelectedItem();
            Order ord = Order.getOrderById(orderIdInt);
            ord.setOrderStatus(Status);
            String DeliveryDate = txtDate.getText();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(DeliveryDate);
            ord.setDeliveryDate(date);

            String RequestID = txtRequestID.getText();
            int requestIdInt = Integer.parseInt(RequestID);
            ArrayList<WorkRequest> workRequestList = org.getWorkQueue().getWorkRequestList();
            for (WorkRequest request : workRequestList) {
                if (request instanceof DeliveryWorkRequest) {
                    if (((DeliveryWorkRequest) request).getRequestID() == requestIdInt) {
                        request.setReceiver(account);
                        break;
                    }
                }
            }
            populateTable();
            UpdatePanel.setVisible(false);
        } catch (ParseException ex) {
            Logger.getLogger(DeliveryDashboardJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSubmitActionPerformed

    //}
    private void txtOrderIDActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void btnAssignTomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignTomeActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblordersummary.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Pls select a row!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UpdatePanel.setVisible(true);

        int OrderID = (int) tblordersummary.getValueAt(selectedRow, 1);
        txtOrderID.setText(String.valueOf(OrderID));

        int RequestID = (int) tblordersummary.getValueAt(selectedRow, 0);
        txtRequestID.setText(String.valueOf(RequestID));

        txtComments.setText(txtComments.getText());
        String Status = (String) StatusComboBox.getSelectedItem();
        if (Status == "Delivered") {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(new Date());
            txtDate.setText(formattedDate);
        } else {
            Order orderById = Order.getOrderById(OrderID);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(orderById.getOrderDate());
            txtDate.setText(formattedDate);

        }
        txtDeliveredBy.setText(account.getUsername());

    }//GEN-LAST:event_btnAssignTomeActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearch1.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tblViewIdea1.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Idea idea : business.getIdeaDirectory().getIdeaList()) {

            if ((Integer.toString(idea.getIdeaId()).contains(searchText)
                    || idea.getTitle().toLowerCase().contains(searchText))) {

                // Add the idea to the table
                ImageIcon icon = idea.getIdeaImage();
                if (icon != null) {
                    // Scale the icon if necessary
                    Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(image);
                }

                model.addRow(new Object[]{
                    idea.getIdeaId(),
                    idea.getTitle(),
                    idea.getDescription(),
                    icon,
                    formatDate(idea.getCreatedDate())
                });
            }
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnGetFeedback1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetFeedback1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblViewIdea1.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int ideaId = Integer.parseInt(tblViewIdea1.getValueAt(selectedRow, 0).toString());
                populateFeedbackTable1(ideaId);
                Feedbacks.setVisible(true); // Make the feedback table visible for Blog
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error parsing idea ID from the selected row.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an idea to view feedback.");
        }
    }//GEN-LAST:event_btnGetFeedback1ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
        if (updateDescription2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedRow = tblViewIdea1.getSelectedRow();
        int ideaId = Integer.parseInt(tblViewIdea1.getValueAt(selectedRow, 0).toString());
        Idea existingIdea = business.getIdeaDirectory().getIdeaById(ideaId);
        String author = account.getPerson().getFirstName() + " " + account.getPerson().getLastName();

        if (existingIdea != null) {
            txtTitle4.setText(existingIdea.getTitle());
        }
        String details = updateDescription2.getText();

        int lastfeedId = 0;
        for (Feedback feedback : business.getFeedbackDirectory().getFeedbackList()) {
            if (feedback.getFeedId() > lastfeedId) {
                lastfeedId = feedback.getFeedId();
            }
        }
        int feedId = lastfeedId + 1;

        Feedback feedback = new Feedback(feedId, ideaId, details, author, account.getPerson().getPersonId());

        business.getFeedbackDirectory().addFeed(feedback);

        JOptionPane.showMessageDialog(this, "Feedback added successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);

        // Clear the form fields
        txtTitle4.setText("");
        updateDescription2.setText("");
        populateFeedbackTable1(ideaId);
    }//GEN-LAST:event_btnSave2ActionPerformed

    public void populateIdeaTable1() {
        DefaultTableModel model = (DefaultTableModel) tblViewIdea1.getModel();
        model.setRowCount(0);

        for (Idea idea : business.getIdeaDirectory().getIdeaList()) {
            ImageIcon icon = idea.getIdeaImage();
            if (icon != null) {
                // Scale the icon if necessary
                Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
            }

            model.addRow(new Object[]{
                idea.getIdeaId(),
                idea.getTitle(),
                idea.getDescription(),
                icon,
                formatDate(idea.getCreatedDate()),
                idea.getAuthor()
            });
        }
    }

    public void populateFeedbackTable1(int ideaId) {
        DefaultTableModel model = (DefaultTableModel) tblViewFeedback1.getModel();
        model.setRowCount(0);

        for (Feedback feedback : business.getFeedbackDirectory().getFeedbackListByIdeaId(ideaId)) {
            model.addRow(new Object[]{
                feedback.getFeedId(),
                feedback.getAuthor(),
                feedback.getDetails(),
                formatDate(feedback.getDate())
            });
        }

        tblViewFeedback1.setRowHeight(50);
    }

    private void setupTableTextWrap1() {
        tblViewIdea1.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private final JTextArea textArea = new JTextArea();

            {
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setOpaque(true);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                } else {
                    textArea.setText(value != null ? value.toString() : "");
                    textArea.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                    textArea.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                    return textArea;
                }
            }
        });

        // Adjust row height to accommodate text area
        tblViewIdea1.setRowHeight(150); // You might need to adjust this value

        // Apply the same renderer and row height to the feedback table
        tblViewFeedback1.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private final JTextArea textArea = new JTextArea();

            {
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setOpaque(true);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                } else {
                    textArea.setText(value != null ? value.toString() : "");
                    textArea.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                    textArea.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                    return textArea;
                }
            }
        });

        // Adjust row height for feedback table
        tblViewFeedback1.setRowHeight(50); // You might need to adjust this value
    }
    private void StatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusComboBoxActionPerformed

    }//GEN-LAST:event_StatusComboBoxActionPerformed

    private void txtDeliveredByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeliveredByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeliveredByActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Blog;
    private javax.swing.JLabel DeliveredBy;
    private javax.swing.JTabbedPane Delivery;
    private javax.swing.JTabbedPane Feedbacks;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JPanel UpdatePanel;
    private javax.swing.JPanel View;
    private javax.swing.JPanel add;
    private javax.swing.JButton btnAssignTome;
    private javax.swing.JButton btnGetFeedback1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JScrollPane feedbackScroll1;
    private javax.swing.JLabel header;
    private javax.swing.JLabel ideaImg2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblImg2;
    private javax.swing.JLabel lblMessages;
    private javax.swing.JLabel lblOrderID;
    private javax.swing.JLabel lblRequestID;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblViewFeedback1;
    private javax.swing.JTable tblViewIdea1;
    private javax.swing.JTable tblordersummary;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDeliveredBy;
    private javax.swing.JTextField txtOrderID;
    private javax.swing.JTextField txtRequestID;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtTitle3;
    private javax.swing.JTextField txtTitle4;
    private javax.swing.JTextField updateDescription1;
    private javax.swing.JTextField updateDescription2;
    // End of variables declaration//GEN-END:variables
}
