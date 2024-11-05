/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.certificationSpecialist;

import business.Business;
import business.Idea.Idea;
import business.enterprise.Enterprise;
import business.enterprise.ProductionEnterprise;
import business.feedback.Feedback;
import business.network.Network;
import business.organization.FarmersOrganization;
import business.organization.Organization;
import business.products.Product;
import business.report.Report;
import business.userAccount.UserAccount;
import business.workQueue.FarmersApproval;
import business.workQueue.WorkRequest;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author bhavanidevulapalli
 */
public class CertificationSpecialistDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CertificationSpecialistDashoboardJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private Business business;
    private Network network;
    private Enterprise enterprise;
    private WorkRequest request;
    private boolean view = false;
    private FarmersOrganization farmerOrganization;
    private String currentFilePath;

    ImageIcon ideaImage;
    private int userId;

    public CertificationSpecialistDashboardJPanel(JPanel userProcessContainer, UserAccount account, Business business, Network network, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.business = business;
        this.network = network;
        this.enterprise = enterprise;

        String userName = account.getUsername();
        int userid = account.getPerson().getPersonId();
        System.out.println(business.getUserAccountDirectory().getUserAccountList().size());
        txtCertificationUserName.setText(userName);

        this.userId = account.getPerson().getPersonId();

        UpdatePanel.setVisible(false);
        Feedbacks.setVisible(false);

        ViewJPanel.setVisible(false);

        certificate.addChangeListener(e -> {
            int selectedIndex = certificate.getSelectedIndex();
            if (selectedIndex == 1) {
                populateIdeaTable1();
                setupTableTextWrap1();

            }
        });

        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JTable table = (JTable) evt.getSource();
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());

                // Assuming the file path is in the 3rd column (index 2)
                if (row >= 0 && col == 2) {
                    String filePath = table.getValueAt(row, col).toString();
                    openPDF(filePath);
                }
            }
        });

        certificate.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                String selectedTabTitle = tabbedPane.getTitleAt(selectedIndex);
                System.out.println("Tab selected: " + selectedTabTitle); // Debug statement

                if ("Report".equals(selectedTabTitle.trim())) {
                    System.out.println("Populating report table..."); // Debug statement
                    populateReportTable();
                }
            }
        });

        certificate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ResearcherStateChanged(evt);
            }
        });

        tblViewIdea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblViewIdeaMouseClicked(evt);
            }
        });
        populateViewRequestTable();

    }

    private void tblViewIdeaMouseClicked(java.awt.event.MouseEvent evt) {

        if (tblViewIdea1.getSelectedRow() >= 0) {
            Feedbacks.setVisible(false);
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

    public void populateReportTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        List<Report> reports = business.getReportDirectory().getReportList();
        if (reports == null || reports.isEmpty()) {
            System.out.println("No reports found or Report list is null."); // Debug statement
            JOptionPane.showMessageDialog(null, "No reports available or Report directory is not initialized.", "No Reports", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Report report : reports) {
            Object[] row = new Object[]{
                report.getName(),
                report.getFileDescription(),
                report.getFilePath().toString()
            };
            model.addRow(row);
        }

        if (model.getRowCount() == 0) {
            System.out.println("Reports found, but none added to the table."); // Debug statement
        } else {
            System.out.println("Reports successfully added to the table."); // Debug statement
        }

        jTable1.revalidate(); // Refresh table
        jTable1.repaint(); // Refresh table display
    }

    public void openPDF(String filePath) {
        if (!Desktop.isDesktopSupported()) {
            JOptionPane.showMessageDialog(null, "Desktop is not supported!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
                return;
            }

            // Try loading from resources if the direct file path doesn't exist
            URL resourceUrl = getClass().getResource(filePath);
            if (resourceUrl != null) {
                File resourceFile = new File(resourceUrl.toURI());
                if (resourceFile.exists()) {
                    Desktop.getDesktop().open(resourceFile);
                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "File does not exist: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void innerTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {
        JTabbedPane tabSource = (JTabbedPane) evt.getSource();
        int selectedIndex = tabSource.getSelectedIndex();

        final int VIEW_IDEA_TAB_INDEX = 1;
        final int ADD_FEEDBACK_TAB_INDEX = 2;

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

    private void populateViewRequestBySelectionTable() {
        DefaultTableModel model = (DefaultTableModel) tblView.getModel();
        model.setRowCount(0);

        String Status = (String) StatusCmbBox.getSelectedItem();
        ArrayList<Product> ProductList = business.getProductDirectory().getProductList();

        for (Product product : ProductList) {

            Product.Status status1 = product.getStatus();
            String productStatusString = status1.toString();
            if (Status.equalsIgnoreCase(productStatusString)) {
                Object[] row = new Object[10];
                String productName = product.getName();
                int productId = product.getProductId();
                double price = product.getPrice();
                int quantity = product.getQuantity();

                double EstimationCost = business.getProductDirectory().calculateEstimationCost(price, quantity);

                row[0] = productId;
                row[1] = productName;
                row[2] = price;
                row[3] = quantity;
                row[4] = EstimationCost;
                row[5] = product.getImage();
                row[6] = product.getStatus();
                row[7] = (product.getOfferedPrice() == 0.00) ? "TBD" : product.getOfferedPrice();

                model.addRow(row);
                tblView.setRowHeight(150);
            }
        }

        tblView.getColumnModel().getColumn(5).setCellRenderer(new ImageRenderer());
        tblView.setModel(model);
        tblView.setPreferredScrollableViewportSize(new Dimension(tblView.getPreferredScrollableViewportSize().width, 150));
        tblView.revalidate();
    }

    private void populateViewRequestTable() {
        DefaultTableModel model = (DefaultTableModel) tblView.getModel();
        model.setRowCount(0);

        String Status = (String) StatusComboBox.getSelectedItem();
        ArrayList<Product> ProductList = business.getProductDirectory().getProductList();

        for (Product product : ProductList) {
            Object[] row = new Object[10];
            String productName = product.getName();
            int productId = product.getProductId();
            double price = product.getPrice();
            int quantity = product.getQuantity();

            double EstimationCost = business.getProductDirectory().calculateEstimationCost(price, quantity);

            row[0] = productId;
            row[1] = productName;
            row[2] = price;
            row[3] = quantity;
            row[4] = EstimationCost;
            row[5] = product.getImage();
            row[6] = product.getStatus();
            row[7] = (product.getOfferedPrice() == 0.00) ? "TBD" : product.getOfferedPrice();
            row[8] = product.getDescription();
            model.addRow(row);
            tblView.setRowHeight(150);
        }

        tblView.getColumnModel().getColumn(5).setCellRenderer(new ImageRenderer());
        tblView.setModel(model);
        tblView.setPreferredScrollableViewportSize(new Dimension(tblView.getPreferredScrollableViewportSize().width, 150));
        tblView.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JLabel();
        txtCertificationUserName = new javax.swing.JTextField();
        certificate = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblView = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        UpdatePanel = new javax.swing.JPanel();
        lblProductID = new javax.swing.JLabel();
        txtProduct = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        lblComments = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtComments = new javax.swing.JTextField();
        StatusComboBox = new javax.swing.JComboBox<>();
        CertifiedBy = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        StatusCmbBox = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();
        btnView1 = new javax.swing.JButton();
        ViewJPanel = new javax.swing.JPanel();
        lblProductID1 = new javax.swing.JLabel();
        txtProductID = new javax.swing.JTextField();
        lblComments1 = new javax.swing.JLabel();
        updateDescription = new javax.swing.JTextField();
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
        jScrollPane2 = new javax.swing.JScrollPane();
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
        Report = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnAttach = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 113, 178));

        header.setBackground(new java.awt.Color(255, 204, 204));
        header.setFont(new java.awt.Font("Khmer MN", 1, 36)); // NOI18N
        header.setForeground(new java.awt.Color(255, 204, 204));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-expert-96.png"))); // NOI18N
        header.setText("Certification Specialist Work Area");

        txtCertificationUserName.setBackground(new java.awt.Color(184, 221, 243));
        txtCertificationUserName.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        certificate.setBackground(new java.awt.Color(184, 221, 243));
        certificate.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 113, 178));

        tblView.setBackground(new java.awt.Color(184, 221, 243));
        tblView.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        tblView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "Product Name", "Price", "Quantity", "Estimation Total Cost", "Image", "ReviewStatus", "Offered Price", "Comments"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblView.setGridColor(new java.awt.Color(204, 204, 204));
        tblView.setSelectionBackground(new java.awt.Color(0, 61, 133));
        jScrollPane1.setViewportView(tblView);

        btnUpdate.setBackground(new java.awt.Color(255, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-update-30.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        UpdatePanel.setBackground(new java.awt.Color(184, 221, 243));

        lblProductID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblProductID.setText("ProductId :");

        txtProduct.setEditable(false);
        txtProduct.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        txtProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductActionPerformed(evt);
            }
        });

        lblPrice.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblPrice.setText("Offered Price :");

        lblComments.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblComments.setText("Comments :");

        lblStatus.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblStatus.setText("Status :");

        txtPrice.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        txtComments.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        StatusComboBox.setBackground(new java.awt.Color(255, 204, 204));
        StatusComboBox.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "TO_BE_REVIEWED", "AWAITING_FARMER_APPROVAL", "REVIEWED" }));
        StatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusComboBoxActionPerformed(evt);
            }
        });

        CertifiedBy.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        CertifiedBy.setText("Certified By :");

        txtname.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N

        btnSubmit.setBackground(new java.awt.Color(255, 204, 204));
        btnSubmit.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/icons8-submit-30.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/certifiedorganic.jpeg"))); // NOI18N

        javax.swing.GroupLayout UpdatePanelLayout = new javax.swing.GroupLayout(UpdatePanel);
        UpdatePanel.setLayout(UpdatePanelLayout);
        UpdatePanelLayout.setHorizontalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblProductID)
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblComments, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CertifiedBy, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(56, 56, 56)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StatusComboBox, 0, 0, Short.MAX_VALUE)
                    .addComponent(txtPrice)
                    .addComponent(txtComments)
                    .addComponent(txtProduct)
                    .addComponent(txtname))
                .addContainerGap())
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        UpdatePanelLayout.setVerticalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrice)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblComments)
                            .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CertifiedBy)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        btnSearch.setBackground(new java.awt.Color(255, 204, 204));
        btnSearch.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/search-30.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        StatusCmbBox.setBackground(new java.awt.Color(255, 204, 204));
        StatusCmbBox.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        StatusCmbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "TO_BE_REVIEWED", "AWAITING_FARMER_APPROVAL", "REVIEWED" }));
        StatusCmbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusCmbBoxActionPerformed(evt);
            }
        });

        btnView.setBackground(new java.awt.Color(255, 204, 204));
        btnView.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/view.png"))); // NOI18N
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnView1.setBackground(new java.awt.Color(255, 204, 204));
        btnView1.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnView1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/view.png"))); // NOI18N
        btnView1.setText("View");
        btnView1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView1ActionPerformed(evt);
            }
        });

        ViewJPanel.setBackground(new java.awt.Color(153, 204, 255));

        lblProductID1.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblProductID1.setText("ProductId :");

        txtProductID.setEditable(false);
        txtProductID.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        txtProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductIDActionPerformed(evt);
            }
        });

        lblComments1.setFont(new java.awt.Font("Khmer MN", 0, 18)); // NOI18N
        lblComments1.setText("Comments :");

        updateDescription.setEnabled(false);

        javax.swing.GroupLayout ViewJPanelLayout = new javax.swing.GroupLayout(ViewJPanel);
        ViewJPanel.setLayout(ViewJPanelLayout);
        ViewJPanelLayout.setHorizontalGroup(
            ViewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewJPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(ViewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblComments1)
                    .addComponent(lblProductID1))
                .addGap(18, 18, 18)
                .addGroup(ViewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewJPanelLayout.createSequentialGroup()
                        .addComponent(txtProductID, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addGap(104, 104, 104))
                    .addComponent(updateDescription)))
        );
        ViewJPanelLayout.setVerticalGroup(
            ViewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewJPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(ViewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductID1)
                    .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ViewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewJPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblComments1))
                    .addGroup(ViewJPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(updateDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnView1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)
                        .addComponent(StatusCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 673, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ViewJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnView)
                                .addGap(163, 163, 163)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(StatusCmbBox)
                        .addComponent(btnView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnView))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(ViewJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1295, Short.MAX_VALUE))
        );

        certificate.addTab("View Products", jPanel1);

        Blog.setBackground(new java.awt.Color(0, 113, 178));
        Blog.setPreferredSize(new java.awt.Dimension(1100, 750));

        btnSearch1.setText("Search");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

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

        Feedbacks.setBackground(new java.awt.Color(184, 221, 243));
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
            .addComponent(feedbackScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1357, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(feedbackScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );

        Feedbacks.addTab("Feedbacks ", jPanel2);

        View.setBackground(new java.awt.Color(232, 255, 254));

        txtTitle3.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Title:");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Description:");

        updateDescription1.setEnabled(false);
        jScrollPane2.setViewportView(updateDescription1);

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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(jScrollPane2))))
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ideaImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImg2))
                        .addGap(35, 35, 35)))
                .addGap(41, 41, 41))
        );

        Feedbacks.addTab("View Idea", View);

        add.setBackground(new java.awt.Color(232, 255, 254));

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
                .addContainerGap(704, Short.MAX_VALUE))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
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
                .addGap(717, 717, 717)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        certificate.addTab("Blog", Blog);

        Report.setBackground(new java.awt.Color(0, 113, 178));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "File Description", "File"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        btnAttach.setBackground(new java.awt.Color(0, 102, 153));
        btnAttach.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnAttach.setForeground(new java.awt.Color(204, 255, 255));
        btnAttach.setText("Attach");
        btnAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(0, 102, 153));
        btnRemove.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(204, 255, 255));
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 102, 153));
        btnAdd.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(204, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReportLayout = new javax.swing.GroupLayout(Report);
        Report.setLayout(ReportLayout);
        ReportLayout.setHorizontalGroup(
            ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportLayout.createSequentialGroup()
                .addGroup(ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ReportLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(40, 40, 40)
                        .addGroup(ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addGroup(ReportLayout.createSequentialGroup()
                                .addComponent(btnAttach)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdd))
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemove)
                        .addGap(12, 12, 12))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(522, Short.MAX_VALUE))
        );
        ReportLayout.setVerticalGroup(
            ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove))
                .addGap(29, 29, 29)
                .addGroup(ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAttach)
                    .addComponent(btnAdd))
                .addContainerGap(1450, Short.MAX_VALUE))
        );

        certificate.addTab("Report", Report);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCertificationUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(certificate)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCertificationUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(certificate)
                .addGap(89, 89, 89))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblView.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Pls select a row!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ViewJPanel.setVisible(true);
        int productID = (int) tblView.getValueAt(selectedRow, 0);
        txtProductID.setText(String.valueOf(productID));
        Product product = business.getProductDirectory().getProductFromProductID(productID);

        updateDescription.setText(product.getDescription());


    }//GEN-LAST:event_btnViewActionPerformed

    private void StatusCmbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusCmbBoxActionPerformed

    }//GEN-LAST:event_StatusCmbBoxActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        populateViewRequestBySelectionTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        String ProductID = txtProduct.getText();
        ArrayList<Product> ProductList = business.getProductDirectory().getProductList();

        for (Product product : ProductList) {
            if (ProductID.equalsIgnoreCase(String.valueOf(product.getProductId()))) {
                product.setOfferedPrice(Double.valueOf(txtPrice.getText()));
                product.setDescription(txtComments.getText());
                String selectedStatus = (String) StatusComboBox.getSelectedItem();
                Product.Status status = Product.Status.valueOf(selectedStatus.toUpperCase());
                product.setStatus(status);

            }
        }
        int ProductID1 = Integer.parseInt(ProductID);
        Product productFromProductID = business.getProductDirectory().getProductFromProductID(ProductID1);

        FarmersApproval farmersApproval = new FarmersApproval();
        farmersApproval.setMessage(txtComments.getText());
        farmersApproval.setSender(account);

        farmersApproval.setStatus("AWAITING_FARMER_APPROVAL");

        String farmersId = productFromProductID.getFarmersId();
        UserAccount findUserByUserName = business.getUserAccountDirectory().findUserByUserName(farmersId);
        farmersApproval.setReceiver(findUserByUserName);

        ArrayList<Network> networkList = business.getNetworkList();
        for (Network network : networkList) {
            ArrayList<Enterprise> enterpriseList = network.getEnterpriseDirectory().getEnterpriseList();
            for (Enterprise enterprise : enterpriseList) {
                if (enterprise instanceof ProductionEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganisationList()) {
                        if (organization instanceof FarmersOrganization) {
                            organization.getWorkQueue().getWorkRequestList().add(farmersApproval);
                            account.getWorkQueue().getWorkRequestList().add(farmersApproval);
                            System.out.println(organization.getWorkQueue().getWorkRequestList().size());

                        }
                    }
                }
            }
        }

        populateViewRequestTable();
        UpdatePanel.setVisible(false);
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void StatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusComboBoxActionPerformed

    }//GEN-LAST:event_StatusComboBoxActionPerformed

    private void txtProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        ViewJPanel.setVisible(false);
        int selectedRow = tblView.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Pls select a row!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UpdatePanel.setVisible(true);

        int product = (int) tblView.getValueAt(selectedRow, 0);
        txtProduct.setText(String.valueOf(product));
        Object valueAt = tblView.getValueAt(selectedRow, 2);
        txtPrice.setText((String.valueOf(valueAt)));
        txtComments.setText(txtComments.getText());
        String Status = (String) StatusComboBox.getSelectedItem();
        txtname.setText(txtCertificationUserName.getText());
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnView1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView1ActionPerformed
        // TODO add your handling code here:
        populateViewRequestTable();
    }//GEN-LAST:event_btnView1ActionPerformed

    private void txtProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductIDActionPerformed

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a PDF file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            try {
                Path destDirectory = Paths.get(System.getProperty("user.dir"), "src", "pdf");
                if (!Files.exists(destDirectory)) {
                    Files.createDirectories(destDirectory);
                }
                Path target = destDirectory.resolve(fileToOpen.getName());

                // Copy file with StandardCopyOption.REPLACE_EXISTING to overwrite existing files with the same name
                Files.copy(fileToOpen.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                currentFilePath = target.toString();  // Update the path only on successful copy
                JOptionPane.showMessageDialog(this, "File saved successfully to PDF directory!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to save the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            currentFilePath = null;  // Reset path if operation is canceled
        }
    }//GEN-LAST:event_btnAttachActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String filePath = (String) jTable1.getValueAt(selectedRow, 2); // Assuming the file path is in the 3rd column
            File fileToDelete = new File(filePath);
            if (fileToDelete.delete()) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "File deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a file to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String fileName = jTextField1.getText(); // Assuming jTextField1 is for the file name
        String fileDescription = jTextArea1.getText(); // Assuming jTextArea1 is for the file description
        String filePath = currentFilePath; // Use the path from the class variable

        if (!fileName.isEmpty() && !fileDescription.isEmpty() && filePath != null && !filePath.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{fileName, fileDescription, filePath});
            jTextField1.setText("");
            jTextArea1.setText("");
            JOptionPane.showMessageDialog(null, "File details added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all fields and attach a file.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

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

    public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Blog;
    private javax.swing.JLabel CertifiedBy;
    private javax.swing.JTabbedPane Feedbacks;
    private javax.swing.JPanel Report;
    private javax.swing.JComboBox<String> StatusCmbBox;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JPanel UpdatePanel;
    private javax.swing.JPanel View;
    private javax.swing.JPanel ViewJPanel;
    private javax.swing.JPanel add;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnGetFeedback1;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.JButton btnView1;
    private javax.swing.JTabbedPane certificate;
    private javax.swing.JScrollPane feedbackScroll1;
    private javax.swing.JLabel header;
    private javax.swing.JLabel ideaImg2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblComments;
    private javax.swing.JLabel lblComments1;
    private javax.swing.JLabel lblImg2;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProductID;
    private javax.swing.JLabel lblProductID1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblView;
    private javax.swing.JTable tblViewFeedback1;
    private javax.swing.JTable tblViewIdea1;
    private javax.swing.JTextField txtCertificationUserName;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProduct;
    private javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtTitle3;
    private javax.swing.JTextField txtTitle4;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField updateDescription;
    private javax.swing.JTextField updateDescription1;
    private javax.swing.JTextField updateDescription2;
    // End of variables declaration//GEN-END:variables
public class ImageRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                JLabel label = new JLabel();
                label.setIcon((ImageIcon) value);
                return label;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

}
