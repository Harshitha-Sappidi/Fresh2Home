/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.person;

import java.util.ArrayList;

/**
 *
 * @author sushmaka
 */
public class PersonDirectory {
    private ArrayList<Person> personList;

    public PersonDirectory() {
        this.personList = new ArrayList<Person>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }
    public void addNewPerson(Person person) {
        this.personList.add(person);
    }
    

    public void removePerson(Person person) {
        this.personList.remove(person);
    }
    public Person findPersonById(int id) {
        for (Person person : personList) {
            if (person.getPersonId() == id) {
                return person;
            }
        }
        return null; // Person with the specified ID not found
    }
}
