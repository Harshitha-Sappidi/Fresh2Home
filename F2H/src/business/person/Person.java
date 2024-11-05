/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.person;

import business.customer.Wallet;

/**
 *
 * @author bhavanidevulapalli
 */
public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNo;

    private String address;
    private static int count = 1;
    private Wallet wallet;

    
    

//      public Person() {
//        }
    
    public Person( String firstName, String lastName, String emailId, String phoneNo,String address) {

       
       this.personId = count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.address=address;
        this.wallet = new Wallet();
        
    }


    public int getPersonId() {
        return personId;
    }




    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

   


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getaddress() {

        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    

  


    
}
