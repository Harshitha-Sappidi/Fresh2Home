/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.userAccount;

import business.employee.Employee;
import business.person.Person;
import business.role.Role;
import java.util.ArrayList;

/**
 *
 * @author bhavanidevulapalli
 */
public class UserAccountDirectory {

    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public UserAccount authenticateUser(String username, String password) {
        for (UserAccount ua : userAccountList) {
            System.out.println(ua.getUsername() + "   " + ua.getPassword());
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)) {
                return ua;
            }
        }
        return null;
    }

    public UserAccount createUserAccount(Person person, String username, String password, Employee employee, Role role) {
        UserAccount userAccount = new UserAccount();
        userAccount.setPerson(person);
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setEmployee(employee);
        userAccount.setRole(role);
        userAccountList.add(userAccount);
        return userAccount;
    }

    public boolean checkIfUsernameIsUnique(String username) {
        for (UserAccount ua : userAccountList) {
            if (ua.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    
     public UserAccount findUserByUserName(String UserName) {
        for (UserAccount user :userAccountList ) {
            if (user.getUsername() == UserName) {
                return user;
            }
        }
        return null; // Person with the specified ID not found
    }
     
        public void removeUser(UserAccount user) {
        this.userAccountList.remove(user);
    }
}
