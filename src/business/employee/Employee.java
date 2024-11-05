/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.employee;

/**
 *
 * @author bhavanidevulapalli
 */
public abstract class Employee {
    
    private String employeeName;
    private int employeeId;
    private static int count = 1;
    

    public Employee() {
        employeeId = count;
        count++;
    }

    public int getId() {
        return employeeId;
    }

    public void setName(String name) {
        this.employeeName = name;
    }

    
    public String getName() {
        return employeeName;
    }

    @Override
    public String toString() {
        return employeeName;
    }
    
    
}

