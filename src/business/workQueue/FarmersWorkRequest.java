/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workQueue;

import business.products.ProductDirectory;
import business.qualityTest.QualityTest;
import javax.swing.ImageIcon;

/**
 *
 * @author bhavanidevulapalli
 */
public class FarmersWorkRequest extends WorkRequest implements Comparable{
  //  private Employee employee;

    private QualityTest qualityTest;

    public QualityTest getQualityTest() {
        return qualityTest;
    }

    public void setQualityTest(QualityTest qualityTest) {
        this.qualityTest = qualityTest;
    }
    public FarmersWorkRequest()
    {
 
     QualityTest qualityTest=new QualityTest();
    }
    

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

   

   

    @Override
    public int compareTo(Object o) {
       
        return 0;
       
}
}
