/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.customerRole;

import business.Business;
import business.customer.Cart;
import business.customer.Order;
import static business.customer.Order.ordersList;
import business.enterprise.Enterprise;
import business.network.Network;
import business.products.Product;
import business.userAccount.UserAccount;
import business.workQueue.WorkRequest;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import ui.adminRole.AdminDashboardJPanel;

/**
 *
 * @author bhavanidevulapalli
 */
public class SalesReport extends javax.swing.JPanel {

    /**
     * Creates new form SalesReport
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private Business business;
    private Network network;
    private Enterprise enterprise;
    private WorkRequest request;

    public SalesReport(JPanel userProcessContainer, UserAccount account, Business business, Network network, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.business = business;
        this.network = network;
        this.enterprise = enterprise;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalesReport = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        SalesReportJPanel = new javax.swing.JPanel();

        btnSalesReport.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnSalesReport.setText("Sales Report");
        btnSalesReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesReportActionPerformed(evt);
            }
        });

        btnback.setBackground(new java.awt.Color(202, 224, 240));
        btnback.setFont(new java.awt.Font("Khmer MN", 1, 18)); // NOI18N
        btnback.setForeground(new java.awt.Color(0, 91, 191));
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/images/login.png"))); // NOI18N
        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        SalesReportJPanel.setBackground(new java.awt.Color(255, 204, 204));
        SalesReportJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Show Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        SalesReportJPanel.setFont(new java.awt.Font(".SF NS Mono", 0, 14)); // NOI18N
        SalesReportJPanel.setLayout(new javax.swing.BoxLayout(SalesReportJPanel, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalesReport)
                    .addComponent(btnback))
                .addContainerGap(769, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(61, Short.MAX_VALUE)
                    .addComponent(SalesReportJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(239, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnback)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 646, Short.MAX_VALUE)
                .addComponent(btnSalesReport)
                .addGap(251, 251, 251))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(199, Short.MAX_VALUE)
                    .addComponent(SalesReportJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(335, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        AdminDashboardJPanel panel = new AdminDashboardJPanel(userProcessContainer, account, business, network, enterprise);
        userProcessContainer.add("AdminDashboardJPanel", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesReportActionPerformed
      DefaultCategoryDataset dataset = createDataset();

    // Create bar chart
    JFreeChart chart = ChartFactory.createBarChart("Number of Sales by Category",
            "Category", "Sales", dataset);
    
    // Customize the chart if needed
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setDrawBarOutline(true);
    
     // Set border for the plot
    plot.setOutlinePaint(Color.BLACK); // Set outline color
    plot.setOutlineStroke(new BasicStroke(2)); 
    
    // Set custom colors for different categories
//    renderer.setSeriesPaint(0, Color.CYAN);
//    renderer.setSeriesPaint(1, Color.GREEN);
//    renderer.setSeriesPaint(2, Color.RED);
//    renderer.setSeriesPaint(3, Color.YELLOW);
//    renderer.setSeriesPaint(4, Color.ORANGE);
//    renderer.setSeriesPaint(5, Color.WHITE);
//    renderer.setSeriesPaint(6, Color.PINK);
//    renderer.setSeriesPaint(7, Color.BLUE);
//    renderer.setSeriesPaint(8, Color.MAGENTA);
  
    // Add more colors as needed
    
    // Create chart panel and add it to SalesReportJPanel
    ChartPanel chartPanel = new ChartPanel(chart);
    SalesReportJPanel.removeAll();
    SalesReportJPanel.add(chartPanel);
    SalesReportJPanel.repaint();
    SalesReportJPanel.updateUI();
   
        
    }//GEN-LAST:event_btnSalesReportActionPerformed
    // TODO add your handling code here

private DefaultCategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Count the number of sales for each category
    Map<String, Integer> categorySalesMap = countCategorySales();

    // Add data to dataset
    for (Map.Entry<String, Integer> entry : categorySalesMap.entrySet()) {
        dataset.addValue(entry.getValue(), "Sales", entry.getKey());
    }

    return dataset;
}

    private Map<String, Integer> countCategorySales() {
        Map<String, Integer> categorySalesMap = new HashMap<>();

        // Loop through orders and count sales for each category
        for (Order order : ordersList) {
            Cart cart = order.getCart();
            for (Product product : cart.getProductMap().values()) {
                String category = product.getCategory();
                categorySalesMap.put(category, categorySalesMap.getOrDefault(category, 0) + 1);
            }
        }

        return categorySalesMap;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SalesReportJPanel;
    private javax.swing.JButton btnSalesReport;
    private javax.swing.JButton btnback;
    // End of variables declaration//GEN-END:variables
}