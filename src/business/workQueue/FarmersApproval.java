/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workQueue;

/**
 *
 * @author bhavanidevulapalli
 */
public class FarmersApproval extends WorkRequest{
     private  int RequestID;
        private static int count=1;
      public FarmersApproval() {
       
        this.RequestID=count++;
    }
}
