/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.report;

import java.util.ArrayList;

/**
 *
 * @author sushmaka
 */
public class ReportDirectory {
    
    private ArrayList<Report> reportList;

    public ReportDirectory() {
        this.reportList = new ArrayList<>(); 
    }

    public ArrayList<Report> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }
    
    public void addReport(Report report) {
        this.reportList.add(report);
    }
    
}
