/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.customer;

/**
 *
 * @author bhavanidevulapalli
 */
public class Wallet {
    private double balance;

    public Wallet() {
        this.balance = 0.0; // Start with zero balance
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addFunds(double amount) {
        balance += amount;
    }

    public void deductFunds(double amount) {
        balance -= amount;
    }
}
