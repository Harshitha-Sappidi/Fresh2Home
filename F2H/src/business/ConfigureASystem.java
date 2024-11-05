/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import business.Idea.Idea;
import business.customer.Cart;
import business.customer.Order;


import business.employee.DeliveryEmp;
import business.employee.ResearcherEmp;
import business.employee.VounteerEmp;
import business.employee.CertificationSpecialistEmp;

import business.employee.BusinessAdmin;

import business.enterprise.Enterprise;
import business.enterprise.Enterprise.EnterpriseType;
import business.enterprise.SalesAndDeliveryEnterprise;
import business.feedback.Feedback;
import business.network.Network;
import business.organization.AccreditationBoardOrganization;
import business.organization.DeliveryOrganization;
import business.organization.FarmersOrganization;
import business.organization.OnlineStore;
import business.organization.Organization;
import business.organization.Organization.Type;

import business.organization.ResearchersOrganization;
import business.organization.VolunteersOrganization;
import business.person.Person;

import business.products.Product;
import business.report.Report;
import business.report.ReportDirectory;

import business.role.AdminRole;
import business.role.CertificationSpecialist;
import business.role.CustomerRole;
import business.role.DeliveryPerson;
import business.role.FarmerRole;
import business.role.HoneyAndOtherCommoditiesCultivator;
import business.role.PoultryProducerRole;
import business.role.Researcher;
import business.role.Volunteer;
import business.userAccount.UserAccount;
import business.workQueue.DeliveryWorkRequest;

import java.awt.Component;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bhavanidevulapalli
 */
public class ConfigureASystem {

    public static Business configure() {

        Business business = Business.getInstance();

        Network n1 = business.addNetwork();
        n1.setName("Fresh2HomeBoston");

        Enterprise productionEnterprise = n1.getEnterpriseDirectory().createAndAddEnterprise("Production Enterprise", EnterpriseType.PRODUCTIONENTERPRISE, "360 Huntington Ave, Boston, MA 02115");
        Enterprise qualityAssuranceEnterprise = n1.getEnterpriseDirectory().createAndAddEnterprise("Quality Assurance Enterprise", EnterpriseType.QUALITYASSURANCEENTERPRISE, "257 Cente Street, Roxbury, MA 02119");
        Enterprise knowledgeTransferEnterprise = n1.getEnterpriseDirectory().createAndAddEnterprise("Knowledge Transfer Enterprise", EnterpriseType.KNOWLEDGETRANSFERENTERPRISE, "257 Jackson Square, Roxbury, MA 02129");
        Enterprise salesAndDeliveryEnterprise = n1.getEnterpriseDirectory().createAndAddEnterprise("Sales & Delivery Enterprise", EnterpriseType.SALESANDDELIVERYENTERPRISE, "257 Jackson Square, Roxbury, MA 02129");

        //Admin Account
        BusinessAdmin Admin = new BusinessAdmin();
        business.getEmployeeDirectory().createEmployee(Admin);
        Person person0 = new Person("Likith", "Kumar", "likithakumar@gmail.com", "+1-(857)-122-3648", "74 Brighton, Boston");
        business.getPersonDirectory().addNewPerson(person0);
        UserAccount admin = business.getUserAccountDirectory().createUserAccount(person0, "esadmin", "esadmin", Admin, new AdminRole());

        //Farmer Account
        Person person1 = new Person("Ramesh", "Kumar", "rameshkumar@gmail.com", "+1-(857)-222-3748", "74 Fenway, Boston");
        business.getPersonDirectory().addNewPerson(person1);
        UserAccount farmerAccount1 = business.getUserAccountDirectory().createUserAccount(person1, "Ramesh", "Kumar", null, new FarmerRole());

        Person person3 = new Person("Jane", "Smith", "janesmith@gmail.com", "+1-(123)-456-7890", "456 Oak St, Springfield");
        business.getPersonDirectory().addNewPerson(person3);
        UserAccount farmerAccount2 = business.getUserAccountDirectory().createUserAccount(person3, "Jane", "farmer", null, new FarmerRole());

        Person person4 = new Person("Michael", "Johnson", "michaeljohnson@gmail.com", "+1-(555)-789-1234", "789 Elm St, Rivertown");
        business.getPersonDirectory().addNewPerson(person4);
        UserAccount farmerAccount3 = business.getUserAccountDirectory().createUserAccount(person4, "Michael", "farmer", null, new FarmerRole());

        Person person5 = new Person("Emily", "Brown", "emilybrown@gmail.com", "+1-(333)-222-1111", "321 Pine St, Lakeview");
        business.getPersonDirectory().addNewPerson(person5);
        UserAccount farmerAccount4 = business.getUserAccountDirectory().createUserAccount(person5, "Emily", "farmer", null, new FarmerRole());

        Person person6 = new Person("David", "Lee", "davidlee@gmail.com", "+1-(999)-888-7777", "987 Maple St, Hillside");
        business.getPersonDirectory().addNewPerson(person6);
        UserAccount farmerAccount5 = business.getUserAccountDirectory().createUserAccount(person6, "David", "farmer", null, new FarmerRole());

        Person person7 = new Person("Jessica", "Garcia", "jessicagarcia@gmail.com", "+1-(444)-333-2222", "741 Birch St, Valley");
        business.getPersonDirectory().addNewPerson(person7);
        UserAccount farmerAccount6 = business.getUserAccountDirectory().createUserAccount(person7, "Jessica", "farmer", null, new FarmerRole());

        Person person8 = new Person("Christopher", "Martinez", "christophermartinez@gmail.com", "+1-(111)-222-3333", "852 Oak St, Lakeside");
        business.getPersonDirectory().addNewPerson(person8);
        UserAccount farmerAccount7 = business.getUserAccountDirectory().createUserAccount(person8, "Christopher", "poultry", null, new PoultryProducerRole());

        Person person9 = new Person("Jennifer", "Lopez", "jenniferlopez@gmail.com", "+1-(777)-888-9999", "963 Elm St, Hilltop");
        business.getPersonDirectory().addNewPerson(person9);
        UserAccount farmerAccount8 = business.getUserAccountDirectory().createUserAccount(person9, "Jennifer", "poultry", null, new PoultryProducerRole());

        Person person10 = new Person("Matthew", "Clark", "matthewclark@gmail.com", "+1-(666)-555-4444", "159 Pine St, Mountainview");
        business.getPersonDirectory().addNewPerson(person10);
        UserAccount farmerAccount9 = business.getUserAccountDirectory().createUserAccount(person10, "Matthew", "honey", null, new HoneyAndOtherCommoditiesCultivator());

        Person person11 = new Person("Sarah", "Hernandez", "sarahhernandez@gmail.com", "+1-(222)-333-4444", "753 Birch St, Riverside");
        business.getPersonDirectory().addNewPerson(person11);
        UserAccount farmerAccount10 = business.getUserAccountDirectory().createUserAccount(person11, "Sarah", "honey", null, new HoneyAndOtherCommoditiesCultivator());

        Person person12 = new Person("Daniel", "Young", "danielyoung@gmail.com", "+1-(888)-777-6666", "357 Maple St, Lakeshore");
        business.getPersonDirectory().addNewPerson(person12);
        UserAccount farmerAccount11 = business.getUserAccountDirectory().createUserAccount(person12, "Daniel", "honey", null, new HoneyAndOtherCommoditiesCultivator());

        Person person13 = new Person("Ashley", "Liu", "ashleyliu@gmail.com", "+1-(777)-666-5555", "852 Elm St, Hillcrest");
        business.getPersonDirectory().addNewPerson(person13);
        UserAccount farmerAccount12 = business.getUserAccountDirectory().createUserAccount(person13, "Ashley", "farmer", null, new HoneyAndOtherCommoditiesCultivator());

        //Volunteer Account
        VounteerEmp volunteer0 = new VounteerEmp();
        business.getEmployeeDirectory().createEmployee(volunteer0);
        Person person14 = new Person("Kevin", "Gonzalez", "kevingonzalez@gmail.com", "+1-(555)-444-3333", "741 Pine St, Lakeview");
        business.getPersonDirectory().addNewPerson(person14);
        UserAccount volunteerAccount00 = business.getUserAccountDirectory().createUserAccount(person14, "Kevin", "vol", volunteer0, new Volunteer());

        Person person15 = new Person("Amanda", "Wang", "amandawang@gmail.com", "+1-(444)-333-2222", "369 Oak St, Lakeside");
        business.getPersonDirectory().addNewPerson(person15);
        VounteerEmp volunteer1 = new VounteerEmp();
        business.getEmployeeDirectory().createEmployee(volunteer1);
        UserAccount volunteerAccount01 = business.getUserAccountDirectory().createUserAccount(person15, "Amanda", "vol", volunteer1, new Volunteer());

        Person person16 = new Person("Ryan", "Nguyen", "ryannguyen@gmail.com", "+1-(333)-222-1111", "123 Maple St, Hilltop");
        business.getPersonDirectory().addNewPerson(person16);
        VounteerEmp volunteer2 = new VounteerEmp();
        business.getEmployeeDirectory().createEmployee(volunteer2);
        UserAccount volunteerAccount02 = business.getUserAccountDirectory().createUserAccount(person16, "Ryan", "vol", volunteer2, new Volunteer());

        //Researcher Account
        ResearcherEmp researcher0 = new ResearcherEmp();
        business.getEmployeeDirectory().createEmployee(researcher0);
        Person person2 = new Person("Sachin", "Reddy", "sachinreddy@gmail.com", "+1-(717)-202-3798", "70 Fenway, Boston");
        business.getPersonDirectory().addNewPerson(person2);
        UserAccount researcherAccount00 = business.getUserAccountDirectory().createUserAccount(person2, "Sachin", "Reddy", researcher0, new Researcher());

        Person person17 = new Person("Nicole", "Patel", "nicolepatel@gmail.com", "+1-(222)-333-4444", "456 Birch St, Riverside");
        business.getPersonDirectory().addNewPerson(person17);
        ResearcherEmp researcher1 = new ResearcherEmp();
        business.getEmployeeDirectory().createEmployee(researcher1);
        UserAccount researcherAccount01 = business.getUserAccountDirectory().createUserAccount(person17, "Nicole", "researcher", researcher1, new Researcher());

        Person person18 = new Person("Joshua", "Kim", "joshuakim@gmail.com", "+1-(111)-222-3333", "753 Pine St, Lakeview");
        business.getPersonDirectory().addNewPerson(person18);
        ResearcherEmp researcher2 = new ResearcherEmp();
        business.getEmployeeDirectory().createEmployee(researcher2);
        UserAccount researcherAccount02 = business.getUserAccountDirectory().createUserAccount(person18, "Joshua", "researcher", researcher2, new Researcher());

        Person person19 = new Person("Stephanie", "Singh", "stephaniesingh@gmail.com", "+1-(999)-888-7777", "159 Elm St, Lakeside");
        business.getPersonDirectory().addNewPerson(person19);
        ResearcherEmp researcher3 = new ResearcherEmp();
        business.getEmployeeDirectory().createEmployee(researcher3);
        UserAccount researcherAccount03 = business.getUserAccountDirectory().createUserAccount(person19, "Stephanie", "researcher", researcher3, new Researcher());

        //Certificate Specialist Account
        Person person20 = new Person("Brandon", "Wong", "brandonwong@gmail.com", "+1-(888)-777-6666", "357 Birch St, Hillcrest");
        business.getPersonDirectory().addNewPerson(person20);
        CertificationSpecialistEmp certSpecialist = new CertificationSpecialistEmp();
        business.getEmployeeDirectory().createEmployee(certSpecialist);
        UserAccount certSpecialist0 = business.getUserAccountDirectory().createUserAccount(person20, "Brandon", "cert", certSpecialist, new CertificationSpecialist());

        Person person21 = new Person("Michelle", "Chen", "michellechen@gmail.com", "+1-(777)-666-5555", "852 Pine St, Riverside");
        business.getPersonDirectory().addNewPerson(person21);
        CertificationSpecialistEmp certSpecialist1 = new CertificationSpecialistEmp();
        business.getEmployeeDirectory().createEmployee(certSpecialist1);
        UserAccount certSpecialist01 = business.getUserAccountDirectory().createUserAccount(person21, "Michelle", "cert", certSpecialist1, new CertificationSpecialist());

        Person person22 = new Person("Alexander", "Patel", "alexanderpatel@gmail.com", "+1-(555)-444-3333", "741 Elm St, Lakeview");
        business.getPersonDirectory().addNewPerson(person22);
        CertificationSpecialistEmp certSpecialist2 = new CertificationSpecialistEmp();
        business.getEmployeeDirectory().createEmployee(certSpecialist2);
        UserAccount certSpecialist02 = business.getUserAccountDirectory().createUserAccount(person22, "Alexander", "cert", certSpecialist2, new CertificationSpecialist());

        Person person23 = new Person("Lauren", "Tran", "laurentran@gmail.com", "+1-(444)-333-2222", "369 Birch St, Lakeside");
        business.getPersonDirectory().addNewPerson(person23);
        CertificationSpecialistEmp certSpecialist3 = new CertificationSpecialistEmp();
        business.getEmployeeDirectory().createEmployee(certSpecialist3);
        UserAccount certSpecialist03 = business.getUserAccountDirectory().createUserAccount(person23, "Lauren", "cert", certSpecialist3, new CertificationSpecialist());

        //Delivery Agents accounts
        Person person24 = new Person("Justin", "Gupta", "justingupta@gmail.com", "+1-(333)-222-1111", "123 Oak St, Hilltop");
        business.getPersonDirectory().addNewPerson(person24);
        DeliveryEmp deliveryAgent0 = new DeliveryEmp();
        business.getEmployeeDirectory().createEmployee(deliveryAgent0);
        UserAccount deliveryAgent00 = business.getUserAccountDirectory().createUserAccount(person24, "Justin", "del", deliveryAgent0, new DeliveryPerson());

        Person person25 = new Person("Megan", "Chang", "meganchang@gmail.com", "+1-(222)-333-4444", "456 Maple St, Riverside");
        business.getPersonDirectory().addNewPerson(person25);
        DeliveryEmp deliveryAgent1 = new DeliveryEmp();
        business.getEmployeeDirectory().createEmployee(deliveryAgent1);
        UserAccount deliveryAgent01 = business.getUserAccountDirectory().createUserAccount(person25, "Megan", "del", deliveryAgent1, new DeliveryPerson());

        Person person26 = new Person("Kyle", "Singh", "kylesingh@gmail.com", "+1-(111)-222-3333", "753 Pine St, Lakeview");
        business.getPersonDirectory().addNewPerson(person26);
        DeliveryEmp deliveryAgent2 = new DeliveryEmp();
        business.getEmployeeDirectory().createEmployee(deliveryAgent2);
        UserAccount deliveryAgent02 = business.getUserAccountDirectory().createUserAccount(person26, "Kyle", "del", deliveryAgent2, new DeliveryPerson());

        Person person27 = new Person("Samantha", "Kim", "samanthakim@gmail.com", "+1-(999)-888-7777", "159 Elm St, Lakeside");
        business.getPersonDirectory().addNewPerson(person27);
        DeliveryEmp deliveryAgent3 = new DeliveryEmp();
        business.getEmployeeDirectory().createEmployee(deliveryAgent3);
        UserAccount deliveryAgent03 = business.getUserAccountDirectory().createUserAccount(person27, "Samantha", "del", deliveryAgent3, new DeliveryPerson());

        //Customer accounts
        Person person28 = new Person("Suresh", "Reddy", "tylerwong@gmail.com", "+1-(888)-777-6666", "357 Birch St, Hillcrest");
        business.getPersonDirectory().addNewPerson(person28);
        UserAccount customer1 = business.getUserAccountDirectory().createUserAccount(person4, "s", "s", null, new CustomerRole());

        // Assuming 'icon' is the variable name you used to store your ImageIcon object
        ImageIcon icon = new ImageIcon(ConfigureASystem.class.getResource("/Images/composting.jpg"));

        String compostingDescription = "Composting is a natural process where microorganisms break down organic matter, such as food scraps, yard waste, and manure, into nutrient-rich soil conditioner. This process transforms organic waste into a valuable resource that can be used to improve soil health and fertility in agriculture.\n"
                + "\n"
                + "Farmers can implement composting on their farms in the following steps:\n"
                + "\n"
                + "    Select a Site: Choose a suitable location for the compost pile or bin. It should be well-drained and easily accessible.\n"
                + "\n"
                + "    Gather Materials: Collect a mix of green (nitrogen-rich) and brown (carbon-rich) materials. Green materials include fruit and vegetable scraps, grass clippings, and manure. Brown materials include leaves, straw, and paper.\n"
                + "\n"
                + "    Build the Compost Pile: Layer the green and brown materials in the compost pile or bin. Aim for a mix of about two-thirds brown materials to one-third green materials. Add water to keep the pile moist, but not soggy.\n"
                + "\n"
                + "    Turn the Compost: Turn the compost pile regularly to aerate it and speed up the decomposition process. This also helps maintain the right moisture level.\n"
                + "\n"
                + "    Monitor the Compost: Keep an eye on the temperature of the compost pile. It should heat up as the materials decompose. If the pile is too hot, add more brown materials. If it's too cold, add more green materials.\n"
                + "\n"
                + "    Use the Compost: Once the compost is dark, crumbly, and earthy-smelling, it is ready to use. Farmers can use compost to improve soil structure, add nutrients to the soil, and retain moisture.\n"
                + "\n"
                + "Implementing composting can benefit farmers in several ways:\n"
                + "\n"
                + "    Soil Health: Compost improves soil structure, increases nutrient content, and promotes beneficial microbial activity, leading to healthier soil and better crop growth.\n"
                + "\n"
                + "    Fertility Management: Compost provides a natural source of nutrients for plants, reducing the need for chemical fertilizers.\n"
                + "\n"
                + "    Waste Reduction: Composting reduces the amount of organic waste that ends up in landfills, helping farmers manage waste more sustainably.\n"
                + "\n"
                + "    Cost Savings: By producing their own compost, farmers can save money on fertilizers and soil amendments.\n"
                + "\n"
                + "    Environmental Benefits: Composting helps reduce greenhouse gas emissions and promotes sustainable agriculture practices.\n";

        Idea organicFarmingIdea = new Idea(1, "Creative Composting Strategies", compostingDescription, icon, "Nicole Patel", person17.getPersonId());
        business.getIdeaDirectory().addIdea(organicFarmingIdea);

        Feedback feedback1 = new Feedback(1, organicFarmingIdea.getIdeaId(), "Great idea! Looking forward to seeing it implemented.", "Sachin Reddy", person2.getPersonId());
        business.getFeedbackDirectory().addFeed(feedback1);

        Feedback feedback2 = new Feedback(2, organicFarmingIdea.getIdeaId(), "I have some suggestions for improvement. Can we discuss?", "Michael Johnson", person4.getPersonId());
        business.getFeedbackDirectory().addFeed(feedback2);

        ImageIcon icon1 = new ImageIcon(ConfigureASystem.class.getResource("/Images/organicchickenFeed.jpg"));
        String chickenFeedIdeaDescription = "Organic chicken feed plays a crucial role in ensuring the health and productivity of poultry. "
                + "By focusing on natural, sustainable, and nutrient-rich ingredients, we can provide chickens with a diet that promotes growth, "
                + "immune system strength, and overall well-being. Let's explore innovative ideas and methods for producing organic chicken feed "
                + "that meets the highest standards of quality and nutrition.";

        Idea chickenFeedIdea = new Idea(2, "Organic Chicken Feed", chickenFeedIdeaDescription, icon1, "Joshua Kim", person18.getPersonId());
        business.getIdeaDirectory().addIdea(chickenFeedIdea);
        Feedback feedback3 = new Feedback(3, chickenFeedIdea.getIdeaId(), "I fully support this idea. Organic chicken feed is essential for healthy poultry production.", "David Lee", person6.getPersonId());
        business.getFeedbackDirectory().addFeed(feedback3);

//Creating a new Farmer's Organisation
        productionEnterprise.getOrganizationDirectory().createOrganization(Type.FARMERSORGANIZATION);

        for (Organization org : productionEnterprise.getOrganizationDirectory().getOrganisationList()) {
            if (org instanceof FarmersOrganization) {
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount1);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount2);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount3);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount4);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount5);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount6);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount7);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount8);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount9);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount10);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount11);
                org.getUserAccountDirectory().getUserAccountList().add(farmerAccount12);

            }
        }

        qualityAssuranceEnterprise.getOrganizationDirectory().createOrganization(Type.ACCREDITATIONBOARDORGANIZATION);
        for (Organization org : qualityAssuranceEnterprise.getOrganizationDirectory().getOrganisationList()) {
            if (org instanceof AccreditationBoardOrganization) {
                org.getUserAccountDirectory().getUserAccountList().add(certSpecialist0);
                org.getUserAccountDirectory().getUserAccountList().add(certSpecialist01);
                org.getUserAccountDirectory().getUserAccountList().add(certSpecialist02);
                org.getUserAccountDirectory().getUserAccountList().add(certSpecialist03);
            }
        }

        knowledgeTransferEnterprise.getOrganizationDirectory().createOrganization(Type.RESEARCHORGANIZATION);
        for (Organization org : knowledgeTransferEnterprise.getOrganizationDirectory().getOrganisationList()) {
            if (org instanceof ResearchersOrganization) {
                org.getUserAccountDirectory().getUserAccountList().add(researcherAccount00);
                org.getUserAccountDirectory().getUserAccountList().add(researcherAccount01);
                org.getUserAccountDirectory().getUserAccountList().add(researcherAccount02);
                org.getUserAccountDirectory().getUserAccountList().add(researcherAccount03);
            }
        }
        knowledgeTransferEnterprise.getOrganizationDirectory().createOrganization(Type.VOLUNTEERINGORGANIZATION);
        for (Organization org : knowledgeTransferEnterprise.getOrganizationDirectory().getOrganisationList()) {
            if (org instanceof VolunteersOrganization) {
                org.getUserAccountDirectory().getUserAccountList().add(volunteerAccount00);
                org.getUserAccountDirectory().getUserAccountList().add(volunteerAccount01);
                org.getUserAccountDirectory().getUserAccountList().add(volunteerAccount02);

            }
        }
        salesAndDeliveryEnterprise.getOrganizationDirectory().createOrganization(Type.ONLINESTORE);
        for (Organization org : salesAndDeliveryEnterprise.getOrganizationDirectory().getOrganisationList()) {
            if (org instanceof OnlineStore) {
                org.getUserAccountDirectory().getUserAccountList().add(customer1);
            }
        }
        salesAndDeliveryEnterprise.getOrganizationDirectory().createOrganization(Type.DELIVERYPRGANIZATION);
        for (Organization org : salesAndDeliveryEnterprise.getOrganizationDirectory().getOrganisationList()) {
            if (org instanceof DeliveryOrganization) {
                org.getUserAccountDirectory().getUserAccountList().add(deliveryAgent00);
                org.getUserAccountDirectory().getUserAccountList().add(deliveryAgent01);
                org.getUserAccountDirectory().getUserAccountList().add(deliveryAgent02);
                org.getUserAccountDirectory().getUserAccountList().add(deliveryAgent03);
            }
        }

        int productId = business.getProductDirectory().getProductList().size() + 1;

        ImageIcon tomato = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Tomato.jpeg"));
        Product rameshTomato = new Product("Tomato", "Vegetable", 3, 50, "Fresh farm grown pesticides free tomatoes", tomato, "Ramesh", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(rameshTomato);
        rameshTomato.setDescription("Good Quality");
        Product janeTomato = new Product("Tomato", "Vegetable", 2.7, 100, "Fresh & juicy organic tomatoes", tomato, "Jane", Product.Status.REVIEWED, Double.valueOf("2.60"));
        business.getProductDirectory().addProduct(janeTomato);
        janeTomato.setDescription("Not very Fresh");
        Product michaelTomato = new Product("Tomato", "Vegetable", 2.8, 90, "Fresh juicy pest free organic tomatoes", tomato, "Michael", Product.Status.REVIEWED, Double.valueOf("2.80"));
        business.getProductDirectory().addProduct(michaelTomato);
        michaelTomato.setDescription("Quality upto the mark");
        Product emilyTomato = new Product("Tomato", "Vegetable", 2.9, 150, "High quality organic tomatoes", tomato, "Emily", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(emilyTomato);
        Product davidTomato = new Product("Tomato", "Vegetable", 3, 70, "Clustered juicy organic tomatoes", tomato, "David", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(davidTomato);
        Product jessicaTomato = new Product("Tomato", "Vegetable", 3.5, 45, "Local Farm grown organic tomatoes", tomato, "Jessica", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(jessicaTomato);

        ImageIcon potato = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Potato.jpeg"));

        Product rameshPotato = new Product("Potato", "Vegetable", 2, 50, "Fresh farm-grown potatoes with earthy flavor", potato, "Ramesh", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(rameshPotato);
        Product janePotato = new Product("Potato", "Vegetable", 1.5, 80, "Organic potatoes with a rich texture", potato, "Jane", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(janePotato);
        Product michaelPotato = new Product("Potato", "Vegetable", 1.8, 90, "Juicy and flavorful pesticide-free potatoes", potato, "Michael", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(michaelPotato);
        Product emilyPotato = new Product("Potato", "Vegetable", 2.2, 100, "Premium quality organic potatoes for gourmet dishes", potato, "Emily", Product.Status.REVIEWED, Double.valueOf("2.10"));
        business.getProductDirectory().addProduct(emilyPotato);
        emilyPotato.setDescription("Not very fresh qality");
        Product davidPotato = new Product("Potato", "Vegetable", 2.3, 30, "Freshly harvested cluster potatoes", potato, "David", Product.Status.REVIEWED, Double.valueOf("2.30"));
        business.getProductDirectory().addProduct(davidPotato);
        davidPotato.setDescription("Good quality");
        Product jessicaPotato = new Product("Potato", "Vegetable", 2.5, 45, "Locally sourced organic potatoes with unbeatable taste", potato, "Jessica", Product.Status.REVIEWED, Double.valueOf("2.00"));
        business.getProductDirectory().addProduct(jessicaPotato);
        jessicaPotato.setDescription("Not so good quality and not very fresh");

        ImageIcon onion = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Onion.jpeg"));

        Product rameshOnion = new Product("Onion", "Vegetable", 1.5, 40, "Fresh farm-grown onions with a pungent aroma", onion, "Ramesh", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(rameshOnion);
        Product janeOnion = new Product("Onion", "Vegetable", 1.2, 100, "Organic onions with crisp texture and intense flavor", onion, "Jane", Product.Status.REVIEWED, Double.valueOf("1.20"));
        business.getProductDirectory().addProduct(janeOnion);
        janeOnion.setDescription("Fresh qality");
        Product michaelOnion = new Product("Onion", "Vegetable", 1.3, 90, "Pesticide-free onions with a sweet and tangy taste", onion, "Michael", Product.Status.REVIEWED, Double.valueOf("1.30"));
        business.getProductDirectory().addProduct(michaelOnion);
        michaelOnion.setDescription("Good qality");
        Product emilyOnion = new Product("Onion", "Vegetable", 1.8, 150, "Premium quality organic onions for culinary delights", onion, "Emily", Product.Status.REVIEWED, Double.valueOf("1.50"));
        business.getProductDirectory().addProduct(emilyOnion);
        emilyOnion.setDescription("Not very fresh");
        Product davidOnion = new Product("Onion", "Vegetable", 2, 30, "Freshly harvested onions with a bold flavor", onion, "David", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(davidOnion);
        Product jessicaOnion = new Product("Onion", "Vegetable", 2.2, 45, "Locally sourced organic onions with exceptional taste", onion, "Jessica", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(jessicaOnion);

        ImageIcon cabbage = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Cabbage.jpg"));
        Product rameshCabbage = new Product("Cabbage", "Vegetable", 5.0, 50, "Freshly harvested cabbages with crisp and crunchy leaves", cabbage, "Ramesh", Product.Status.REVIEWED, Double.valueOf("4.50"));
        business.getProductDirectory().addProduct(rameshCabbage);
        rameshCabbage.setDescription("Not very fresh");
        Product janeCabbage = new Product("Cabbage", "Vegetable", 4.5, 100, "Organic cabbages known for their sweet and earthy flavor", cabbage, "Jane", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(janeCabbage);

        ImageIcon carrot = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Carrot.jpeg"));
        Product janeCarrot = new Product("Carrot", "Vegetable", 3, 30, "Freshly harvested carrots with a vibrant orange color and crunchy texture", carrot, "Jane", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(janeCarrot);
        janeCarrot.setDescription("Very good quality and fresh");
        Product michaelCarrot = new Product("Carrot", "Vegetable", 3.5, 50, "Organically grown carrots with a sweet and earthy flavor", carrot, "Michael", Product.Status.REVIEWED, Double.valueOf("3.40"));
        business.getProductDirectory().addProduct(michaelCarrot);
        michaelCarrot.setDescription("Not very good quality");

        ImageIcon greenchilli = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/GreenChilli.jpg"));
        Product emilyGreenChilli = new Product("Green Chilli", "Vegetable", 2.0, 200, "Fresh and spicy green chillies for adding a kick to your dishes", greenchilli, "Emily", Product.Status.REVIEWED, Double.valueOf("2.00"));
        business.getProductDirectory().addProduct(emilyGreenChilli);
        emilyGreenChilli.setDescription("Very good quality");
        Product sarahGreenChilli = new Product("Green Chilli", "Vegetable", 2.2, 180, "Organically grown green chillies with a vibrant color and intense flavor", greenchilli, "Sarah", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(sarahGreenChilli);

        ImageIcon spinach = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Spinach.jpg"));
        Product davidSpinach = new Product("Spinach", "Vegetable", 5, 40, "Freshly harvested spinach leaves, perfect for salads and smoothies", spinach, "David", Product.Status.TO_BE_REVIEWED, Double.valueOf("5.00"));
        business.getProductDirectory().addProduct(davidSpinach);
        Product sarahSpinach = new Product("Spinach", "Vegetable", 5.5, 35, "Nutrient-rich organic spinach, great for adding to soups and stir-fries", spinach, "Sarah", Product.Status.REVIEWED, Double.valueOf("5.50"));
        business.getProductDirectory().addProduct(sarahSpinach);
        sarahSpinach.setDescription("Very good quality");

        ImageIcon bellpepper = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Bellpepper.jpeg"));
        Product danielBellPepper = new Product("Bell Pepper", "Vegetable", 5, 40, "Freshly harvested bell peppers, perfect for salads and stir-fries", bellpepper, "Daniel", Product.Status.REVIEWED, Double.valueOf("5.00"));
        business.getProductDirectory().addProduct(danielBellPepper);
        danielBellPepper.setDescription("Very good quality");
        Product ashleyBellPepper = new Product("Bell Pepper", "Vegetable", 5.5, 75, "Nutrient-rich organic bell peppers, great for adding to soups and sauces", bellpepper, "Ashley", Product.Status.REVIEWED, Double.valueOf("4.00"));
        business.getProductDirectory().addProduct(ashleyBellPepper);
        sarahSpinach.setDescription("Very bad quality");
        Product janeBellPepper = new Product("Bell Pepper", "Vegetable", 4, 60, "Fresh and crunchy bell peppers, ideal for grilling and stuffing", bellpepper, "Jane", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(janeBellPepper);

        ImageIcon brocoli = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Brocoli.jpeg"));
        Product danielBroccoli = new Product("Broccoli", "Vegetable", 4, 100, "Freshly harvested broccoli florets, perfect for salads and stir-fries", brocoli, "Daniel", Product.Status.REVIEWED, Double.valueOf("4.00"));
        business.getProductDirectory().addProduct(danielBroccoli);
        danielBroccoli.setDescription("Very good quality");
        Product davidBroccoli = new Product("Broccoli", "Vegetable", 5, 25, "Nutrient-rich organic broccoli, great for adding to soups and side dishes", brocoli, "David", Product.Status.REVIEWED, Double.valueOf("4.00"));
        business.getProductDirectory().addProduct(davidBroccoli);
        davidBroccoli.setDescription("Not very fresh");
        Product janeBroccoli = new Product("Broccoli", "Vegetable", 3.5, 10, "Fresh and crunchy broccoli, ideal for steaming and roasting", brocoli, "Jane", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(janeBroccoli);

        ImageIcon apple = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Apple.jpeg"));
        Product michaelApple = new Product("Apple", "Fruit", 4, 100, "Crisp and juicy apples, perfect for snacking and baking", apple, "Michael", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(michaelApple);
        Product davidApple = new Product("Apple", "Fruit", 5, 25, "Organic apples with a sweet and tart flavor, ideal for salads and desserts", apple, "David", Product.Status.REVIEWED, Double.valueOf("5.00"));
        business.getProductDirectory().addProduct(davidApple);
        davidApple.setDescription("Very fresh and premium quality");
        Product jessicaApple = new Product("Apple", "Fruit", 3.5, 10, "Fresh and crunchy apples, great for making cider and sauces", apple, "Jessica", Product.Status.REVIEWED, Double.valueOf("3.50"));
        business.getProductDirectory().addProduct(jessicaApple);
        jessicaApple.setDescription("Good quality & fresh");

        ImageIcon banana = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Banana.jpg"));
        Product matthewBanana = new Product("Banana", "Fruit", 3, 80, "Organic bananas with a creamy texture and sweet taste, perfect for smoothies and desserts", banana, "Matthew", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(matthewBanana);
        matthewBanana.setDescription("Very fresh and good quality");
        Product ashleyBanana = new Product("Banana", "Fruit", 2.5, 50, "Fresh and nutritious organic bananas, ideal for a healthy snack or breakfast", banana, "Ashley", Product.Status.REVIEWED, Double.valueOf("2.50"));
        business.getProductDirectory().addProduct(ashleyBanana);
        ashleyBanana.setDescription("Good quality");
        Product jessicaBanana = new Product("Banana", "Fruit", 2, 30, "Certified organic bananas, sustainably grown and packed with essential nutrients", banana, "Jessica", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(jessicaBanana);

        ImageIcon pineapple = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Pineapple.jpeg"));
        Product matthewPineapple = new Product("Pineapple", "Fruit", 2.5, 70, "Juicy and organic pineapples, bursting with tropical flavor", pineapple, "Matthew", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(matthewPineapple);
        Product davidPineapple = new Product("Pineapple", "Fruit", 4, 40, "Sweet and succulent organic pineapples, perfect for cocktails and desserts", pineapple, "David", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(davidPineapple);
        davidPineapple.setDescription("Bad quality");
        Product rameshPineapple = new Product("Pineapple", "Fruit", 3, 20, "Organic pineapples, freshly harvested and full of tropical goodness", pineapple, "Ramesh", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(rameshPineapple);
        rameshPineapple.setDescription("Good quality");

        ImageIcon orange = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Orange.jpeg"));
        Product michaelOrange = new Product("Orange", "Fruit", 3.5, 80, "Organic oranges, bursting with tangy sweetness and rich in vitamin C", orange, "Michael", Product.Status.REVIEWED, Double.valueOf("3.50"));
        business.getProductDirectory().addProduct(michaelOrange);
        michaelOrange.setDescription("Good quality");
        Product sarahOrange = new Product("Orange", "Fruit", 4, 50, "Fresh and juicy organic oranges, perfect for refreshing drinks and snacks", orange, "Sarah", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(sarahOrange);
        Product emilyOrange = new Product("Orange", "Fruit", 3, 30, "Premium organic oranges, handpicked for superior quality and flavor", orange, "Emily", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(emilyOrange);
        emilyOrange.setDescription("Good quality");

        ImageIcon avocado = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Avacado.jpeg"));
        Product michaelAvocado = new Product("Avocado", "Fruit", 4, 100, "Fresh and creamy organic avocados, perfect for guacamole and salads", avocado, "Michael", Product.Status.REVIEWED, Double.valueOf("4.00"));
        business.getProductDirectory().addProduct(michaelAvocado);
        michaelAvocado.setDescription("Good quality");
        Product davidAvocado = new Product("Avocado", "Fruit", 5, 25, "Organic avocados with a rich and buttery texture, ideal for toast and sandwiches", avocado, "David", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(davidAvocado);
        Product jessicaAvocado = new Product("Avocado", "Fruit", 3.5, 10, "Fresh and flavorful organic avocados, great for adding to salads and smoothies", avocado, "Jessica", Product.Status.REVIEWED, Double.valueOf("3.50"));
        business.getProductDirectory().addProduct(jessicaAvocado);
        jessicaAvocado.setDescription("Good quality");
        Product rameshAvocado = new Product("Avocado", "Fruit", 4, 100, "Creamy and nutrient-rich organic avocados, perfect for healthy meals and snacks", avocado, "Ramesh", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(rameshAvocado);
        Product janeAvocado = new Product("Avocado", "Fruit", 5, 25, "Fresh organic avocados with a smooth and buttery texture, ideal for various culinary uses", avocado, "Jane", Product.Status.REVIEWED, Double.valueOf("4.00"));
        business.getProductDirectory().addProduct(janeAvocado);
        janeAvocado.setDescription("Bad quality");
        Product emilyAvocado = new Product("Avocado", "Fruit", 3.5, 10, "Organic avocados, rich in healthy fats and nutrients, perfect for a balanced diet", avocado, "Emily", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(emilyAvocado);
        Product sarahAvocado = new Product("Avocado", "Fruit", 4, 100, "Creamy and delicious organic avocados, great for making guacamole and avocado toast", avocado, "Sarah", Product.Status.REVIEWED, Double.valueOf("4.00"));
        business.getProductDirectory().addProduct(sarahAvocado);
        sarahAvocado.setDescription("Good quality");

        ImageIcon milk = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Milk.jpg"));
        Product jessicaMilk = new Product("Milk", "Dairy", 3.5, 10, "Organic fresh and flavorful milk, perfect for adding to cereals and smoothies", milk, "Jessica", Product.Status.REVIEWED, Double.valueOf("3.50"));
        business.getProductDirectory().addProduct(jessicaMilk);
        jessicaMilk.setDescription("Good quality");
        Product ashleyMilk = new Product("Milk", "Dairy", 4, 100, "Organic creamy and nutrient-rich milk, ideal for a healthy diet and snacks", milk, "Ashley", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(ashleyMilk);
        Product janeMilk = new Product("Milk", "Dairy", 5, 25, "Fresh organic milk with a smooth and creamy texture, great for various culinary uses", milk, "Jane", Product.Status.REVIEWED, Double.valueOf("3.00"));
        business.getProductDirectory().addProduct(janeMilk);
        janeMilk.setDescription("Doesn't look fresh");

        ImageIcon rawhoney = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/RawHoney.jpg"));
        Product mathewHoney = new Product("Honey", "Honey", 5, 50, "Organic raw honey with rich flavor and natural sweetness, perfect for sweetening beverages and desserts", rawhoney, "Mathew", Product.Status.REVIEWED, Double.valueOf("5.00"));
        business.getProductDirectory().addProduct(mathewHoney);
        mathewHoney.setDescription("Premium quality");
        Product sarahHoney = new Product("Honey", "Honey", 6, 150, "Organic creamy honey with nutrient-rich properties, ideal for a healthy diet and snacks", rawhoney, "Sarah", Product.Status.REVIEWED, Double.valueOf("4.50"));
        business.getProductDirectory().addProduct(sarahHoney);
        sarahHoney.setDescription("Bad quality");
        Product danielHoney = new Product("Honey", "Honey", 7, 80, "Fresh organic honey with a smooth texture and floral aroma, great for various culinary uses", rawhoney, "Daniel", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(danielHoney);
        Product ashleyHoney = new Product("Honey", "Honey", 7, 120, "Premium organic honey with a rich taste and golden color, perfect for drizzling over foods or adding to recipes", rawhoney, "Ashley", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(ashleyHoney);

        ImageIcon spices = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Spices.jpeg"));
        Product mathewSpices = new Product("Spices", "Herbs and Spices", 5, 50, "Premium organic spices with aromatic flavors, perfect for enhancing the taste of your dishes", spices, "Mathew", Product.Status.REVIEWED, Double.valueOf("4.50"));
        business.getProductDirectory().addProduct(mathewSpices);
        mathewSpices.setDescription("Not of premium quality");
        Product sarahSpices = new Product("Spices", "Herbs and Spices", 6, 150, "Fresh organic spices with rich and complex flavors, ideal for seasoning your favorite recipes", spices, "Sarah", Product.Status.REVIEWED, Double.valueOf("6.00"));
        business.getProductDirectory().addProduct(sarahSpices);
        sarahSpices.setDescription("Good quality");
        Product danielSpices = new Product("Spices", "Herbs and Spices", 7, 80, "Organic spice blend with a perfect balance of flavors, great for adding depth to your cooking", spices, "Daniel", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(danielSpices);

        ImageIcon browneggs = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/BrownEggs.jpg"));
        Product christopherBrownEggs = new Product("Brown Eggs", "Poultry", 5, 50, "Farm-fresh organic brown eggs, rich in nutrients and perfect for breakfast dishes", browneggs, "Christopher", Product.Status.REVIEWED, Double.valueOf("5.00"));
        business.getProductDirectory().addProduct(christopherBrownEggs);
        christopherBrownEggs.setDescription("Good quality");
        Product sarahBrownEggs = new Product("Brown Eggs", "Poultry", 6, 150, "Organic brown eggs with a rich flavor and golden yolks, ideal for baking and cooking", browneggs, "Sarah", Product.Status.REVIEWED, Double.valueOf("6.00"));
        business.getProductDirectory().addProduct(sarahBrownEggs);
        sarahBrownEggs.setDescription("High quality");
        Product danielBrownEggs = new Product("Brown Eggs", "Poultry", 7, 80, "Fresh organic brown eggs from free-range chickens, ensuring high quality and taste", browneggs, "Daniel", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(danielBrownEggs);

        ImageIcon eggsAndChicken = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/EggsAndChicken.jpeg"));
        Product christopherEggsAndChicken = new Product("Eggs and Chicken", "Poultry", 15, 50, "Organic free-range chicken eggs and tender chicken meat, providing high-quality protein and nutrients", eggsAndChicken, "Christopher", Product.Status.REVIEWED, Double.valueOf("15.00"));
        business.getProductDirectory().addProduct(christopherEggsAndChicken);
        christopherEggsAndChicken.setDescription("High quality");
        Product jenniferEggsAndChicken = new Product("Eggs and Chicken", "Poultry", 16, 150, "Organic eggs and chicken sourced from ethically raised chickens, ensuring freshness and flavor", eggsAndChicken, "Jennifer", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(jenniferEggsAndChicken);

        ImageIcon wheatgrain = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/WheatGrain.jpeg"));
        Product rameshWheatGrain = new Product("Wheat Grain", "Grains", 5, 40, "Fresh organic wheat grains with a rich aroma and nutty flavor, perfect for baking and cooking", wheatgrain, "Ramesh", Product.Status.REVIEWED, Double.valueOf("5.00"));
        business.getProductDirectory().addProduct(rameshWheatGrain);
        rameshWheatGrain.setDescription("Good quality");
        Product janeWheatGrain = new Product("Wheat Grain", "Grains", 6, 100, "Organic wheat grains harvested from sustainable farms, ensuring freshness and quality", wheatgrain, "Jane", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(janeWheatGrain);
        Product michaelWheatGrain = new Product("Wheat Grain", "Grains", 7, 90, "Pesticide-free wheat grains with a wholesome taste and nutritious profile, ideal for homemade bread and pastries", wheatgrain, "Michael", Product.Status.REVIEWED, Double.valueOf("7.00"));
        business.getProductDirectory().addProduct(michaelWheatGrain);
        michaelWheatGrain.setDescription("High quality");

        ImageIcon blackrice = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/Blackrice.jpeg"));
        Product emilyBlackRice = new Product("Black Rice", "Grains", 10, 150, "Premium quality organic black rice known for its nutty flavor and rich texture, perfect for various culinary creations", blackrice, "Emily", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(emilyBlackRice);
        Product davidBlackRice = new Product("Black Rice", "Grains", 12, 30, "Freshly harvested organic black rice with a bold and distinct flavor, ideal for rice bowls and sushi", blackrice, "David", Product.Status.REVIEWED, Double.valueOf("12.00"));
        business.getProductDirectory().addProduct(davidBlackRice);
        davidBlackRice.setDescription("High quality");
        Product jessicaBlackRice = new Product("Black Rice", "Grains", 15, 45, "Locally sourced organic black rice renowned for its exceptional taste and nutritional benefits, perfect for health-conscious individuals", blackrice, "Jessica", Product.Status.REVIEWED, Double.valueOf("14.00"));
        business.getProductDirectory().addProduct(jessicaBlackRice);
        jessicaBlackRice.setDescription("Not premium quality");

        ImageIcon ragimillet = new ImageIcon(ConfigureASystem.class.getResource("/ui/productImages/RagiMillet.jpeg"));
        Product michaelRagiMillet = new Product("Ragi Millet", "Grains", 10, 90, "Freshly harvested organic ragi millet with a nutty flavor and rich nutritional profile, perfect for gluten-free diets and traditional Indian dishes", ragimillet, "Michael", Product.Status.REVIEWED, Double.valueOf("10.00"));
        business.getProductDirectory().addProduct(michaelRagiMillet);
        michaelRagiMillet.setDescription("Good quality");
        Product emilyRagiMillet = new Product("Ragi Millet", "Grains", 12, 150, "Premium quality organic ragi millet known for its versatility and health benefits, ideal for making porridge, rotis, and desserts", ragimillet, "Emily", Product.Status.REVIEWED, Double.valueOf("10.00"));
        business.getProductDirectory().addProduct(emilyRagiMillet);
        emilyRagiMillet.setDescription("Not premium quality");
        Product davidRagiMillet = new Product("Ragi Millet", "Grains", 15, 30, "Locally sourced organic ragi millet with a distinct flavor and texture, great for incorporating into a balanced diet", ragimillet, "David", Product.Status.TO_BE_REVIEWED, Double.valueOf("0.00"));
        business.getProductDirectory().addProduct(davidRagiMillet);

        Cart cart1 = new Cart("s");

        Product cartProduct1 = new Product(rameshTomato.getProductId(), rameshTomato.getProductname(), rameshTomato.getOfferedPrice(), rameshTomato.getQuantity());

        cartProduct1.setFarmersId(rameshTomato.getFarmersId());
        cartProduct1.setQuantity(5);
        cartProduct1.setCategory(rameshTomato.getCategory());
        double totalPrice1 = cartProduct1.getQuantity() * rameshTomato.getOfferedPrice();

        cart1.addProduct(rameshTomato.getProductId(), cartProduct1);

        Product cartProduct2 = new Product(emilyPotato.getProductId(), emilyPotato.getProductname(), emilyPotato.getOfferedPrice(), emilyPotato.getQuantity());

        cartProduct2.setFarmersId(emilyPotato.getFarmersId());
        cartProduct2.setQuantity(2);
        cartProduct2.setCategory(emilyPotato.getCategory());
        double totalPrice2 = cartProduct2.getQuantity() * emilyPotato.getOfferedPrice();

        cart1.addProduct(emilyPotato.getProductId(), cartProduct2);
        double totalCartPrice = totalPrice1 + totalPrice2;
        System.out.println("Total Price: " + totalCartPrice);
        Order order1 = new Order("s", new Date(), totalCartPrice, DeliveryWorkRequest.Status.OrderPlaced.toString(), cart1);
        Order.ordersList.add(order1);

        DeliveryWorkRequest deliveryRequest = new DeliveryWorkRequest();
        deliveryRequest.setMessage("Please deliver the package to the customer.");
        deliveryRequest.setSender(customer1);
        deliveryRequest.setStatus(DeliveryWorkRequest.Status.OrderPlaced.toString());
        deliveryRequest.setDeliveryAddress(customer1.getPerson().getaddress());
        deliveryRequest.setCustomerName(customer1.getUsername());
        deliveryRequest.setOrder(order1);
        ArrayList<Network> networkList = business.getNetworkList();
        for (Network network : networkList) {
            ArrayList<Enterprise> enterpriseList = network.getEnterpriseDirectory().getEnterpriseList();
            for (Enterprise enterprise : enterpriseList) {
                if (enterprise instanceof SalesAndDeliveryEnterprise) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganisationList()) {
                        if (organization instanceof DeliveryOrganization) {
                            organization.getWorkQueue().getWorkRequestList().add(deliveryRequest);
                            customer1.getWorkQueue().getWorkRequestList().add(deliveryRequest);

                        }
                    }
                }
            }
        }

        Cart cart2 = new Cart("s");

        Product cartProduct11 = new Product(rameshPineapple.getProductId(), rameshPineapple.getProductname(), rameshPineapple.getOfferedPrice(), rameshPineapple.getQuantity());

        cartProduct11.setFarmersId(rameshPineapple.getFarmersId());
        cartProduct11.setQuantity(5);
        cartProduct11.setCategory(rameshPineapple.getCategory());
        double totalPrice11 = cartProduct11.getQuantity() * rameshPineapple.getOfferedPrice();

        cart2.addProduct(rameshPineapple.getProductId(), cartProduct11);

        Product cartProduct12 = new Product(emilyRagiMillet.getProductId(), emilyRagiMillet.getProductname(), emilyRagiMillet.getOfferedPrice(), emilyRagiMillet.getQuantity());

        cartProduct12.setFarmersId(emilyRagiMillet.getFarmersId());
        cartProduct12.setQuantity(4);
        cartProduct12.setCategory(emilyRagiMillet.getCategory());
        double totalPrice12 = cartProduct12.getQuantity() * emilyRagiMillet.getOfferedPrice();

        cart2.addProduct(emilyRagiMillet.getProductId(), cartProduct12);
        double totalCartPrice1 = totalPrice11 + totalPrice12;
        System.out.println("Total Price: " + totalCartPrice1);
        Order order2 = new Order("s", new Date(), totalCartPrice1, DeliveryWorkRequest.Status.OrderPlaced.toString(), cart2);
        Order.ordersList.add(order2);

        Cart cart3 = new Cart("s");

        Product cartProduct13 = new Product(christopherBrownEggs.getProductId(), christopherBrownEggs.getProductname(), christopherBrownEggs.getOfferedPrice(), christopherBrownEggs.getQuantity());

        cartProduct13.setFarmersId(christopherBrownEggs.getFarmersId());
        cartProduct13.setQuantity(12);
        cartProduct13.setCategory(christopherBrownEggs.getCategory());
        double totalPrice13 = cartProduct1.getQuantity() * christopherBrownEggs.getOfferedPrice();
        cart3.addProduct(christopherBrownEggs.getProductId(), cartProduct13);

        Product cartProduct14 = new Product(mathewHoney.getProductId(), mathewHoney.getProductname(), mathewHoney.getOfferedPrice(), mathewHoney.getQuantity());

        cartProduct14.setFarmersId(mathewHoney.getFarmersId());
        cartProduct14.setQuantity(2);
        cartProduct14.setCategory(mathewHoney.getCategory());
        double totalPrice14 = cartProduct14.getQuantity() * mathewHoney.getOfferedPrice();
        cart3.addProduct(mathewHoney.getProductId(), cartProduct14);

        Product cartProduct15 = new Product(sarahSpices.getProductId(), sarahSpices.getProductname(), sarahSpices.getOfferedPrice(), sarahSpices.getQuantity());

        cartProduct15.setFarmersId(sarahSpices.getFarmersId());
        cartProduct15.setQuantity(6);
        cartProduct15.setCategory(sarahSpices.getCategory());
        double totalPrice15 = cartProduct15.getQuantity() * sarahSpices.getOfferedPrice();
        cart3.addProduct(sarahSpices.getProductId(), cartProduct15);

        double totalCartPrice2 = totalPrice13 + totalPrice14 + totalPrice15;
        System.out.println("Total Price: " + totalCartPrice2);
        Order order3 = new Order("s", new Date(), totalCartPrice2, DeliveryWorkRequest.Status.OrderPlaced.toString(), cart3);
        Order.ordersList.add(order3);

        Cart cart4 = new Cart("s");

        Product cartProduct16 = new Product(janeMilk.getProductId(), janeMilk.getProductname(), janeMilk.getOfferedPrice(), janeMilk.getQuantity());

        cartProduct16.setFarmersId(janeMilk.getFarmersId());
        cartProduct16.setQuantity(5);
        cartProduct16.setCategory(janeMilk.getCategory());
        double totalPrice16 = cartProduct16.getQuantity() * janeMilk.getOfferedPrice();

        cart4.addProduct(janeMilk.getProductId(), cartProduct16);

        Product cartProduct17 = new Product(davidApple.getProductId(), davidApple.getProductname(), davidApple.getOfferedPrice(), davidApple.getQuantity());

        cartProduct17.setFarmersId(davidApple.getFarmersId());
        cartProduct17.setQuantity(12);
        cartProduct17.setCategory(davidApple.getCategory());
        double totalPrice17 = cartProduct17.getQuantity() * davidApple.getOfferedPrice();
        cart4.addProduct(davidApple.getProductId(), cartProduct17);

        Product cartProduct18 = new Product(matthewBanana.getProductId(), matthewBanana.getProductname(), matthewBanana.getOfferedPrice(), matthewBanana.getQuantity());

        cartProduct18.setFarmersId(matthewBanana.getFarmersId());
        cartProduct18.setQuantity(12);
        cartProduct18.setCategory(matthewBanana.getCategory());
        double totalPrice18 = cartProduct18.getQuantity() * matthewBanana.getOfferedPrice();
        cart4.addProduct(matthewBanana.getProductId(), cartProduct18);

        Product cartProduct19 = new Product(davidBlackRice.getProductId(), davidBlackRice.getProductname(), davidBlackRice.getOfferedPrice(), davidBlackRice.getQuantity());

        cartProduct19.setFarmersId(davidBlackRice.getFarmersId());
        cartProduct19.setQuantity(1);
        cartProduct19.setCategory(davidBlackRice.getCategory());
        double totalPrice19 = cartProduct19.getQuantity() * davidBlackRice.getOfferedPrice();
        cart4.addProduct(davidBlackRice.getProductId(), cartProduct19);

        double totalCartPrice3 = totalPrice16 + totalPrice17 + totalPrice18 + totalPrice19;

        Order order4 = new Order("s", new Date(), totalCartPrice3, DeliveryWorkRequest.Status.OrderPlaced.toString(), cart4);
        Order.ordersList.add(order4);

        Cart cart5 = new Cart("s");

        Product cartProduct20 = new Product(christopherEggsAndChicken.getProductId(), christopherEggsAndChicken.getProductname(), christopherEggsAndChicken.getOfferedPrice(), christopherEggsAndChicken.getQuantity());

        cartProduct20.setFarmersId(christopherEggsAndChicken.getFarmersId());
        cartProduct20.setQuantity(12);
        cartProduct20.setCategory(christopherEggsAndChicken.getCategory());
        double totalPrice20 = cartProduct20.getQuantity() * christopherEggsAndChicken.getOfferedPrice();

        cart5.addProduct(christopherEggsAndChicken.getProductId(), cartProduct20);

        Product cartProduct21 = new Product(michaelAvocado.getProductId(), michaelAvocado.getProductname(), michaelAvocado.getOfferedPrice(), michaelAvocado.getQuantity());

        cartProduct21.setFarmersId(michaelAvocado.getFarmersId());
        cartProduct21.setQuantity(2);
        cartProduct21.setCategory(michaelAvocado.getCategory());
        double totalPrice21 = cartProduct21.getQuantity() * michaelAvocado.getOfferedPrice();

        cart5.addProduct(michaelAvocado.getProductId(), cartProduct21);

        Product cartProduct22 = new Product(ashleyBanana.getProductId(), ashleyBanana.getProductname(), ashleyBanana.getOfferedPrice(), ashleyBanana.getQuantity());

        cartProduct22.setFarmersId(ashleyBanana.getFarmersId());
        cartProduct22.setQuantity(2);
        cartProduct22.setCategory(ashleyBanana.getCategory());
        double totalPrice22 = cartProduct22.getQuantity() * ashleyBanana.getOfferedPrice();

        cart5.addProduct(ashleyBanana.getProductId(), cartProduct22);

        Product cartProduct23 = new Product(rameshCabbage.getProductId(), rameshCabbage.getProductname(), rameshCabbage.getOfferedPrice(), rameshCabbage.getQuantity());

        cartProduct23.setFarmersId(rameshCabbage.getFarmersId());
        cartProduct23.setQuantity(2);
        cartProduct23.setCategory(rameshCabbage.getCategory());
        double totalPrice23 = cartProduct23.getQuantity() * rameshCabbage.getOfferedPrice();

        cart5.addProduct(rameshCabbage.getProductId(), cartProduct23);

        double totalCartPrice5 = totalPrice20 + totalPrice21 + totalPrice22 + totalPrice23;
        System.out.println("Total Price: " + totalCartPrice);
        Order order5 = new Order("s", new Date(), totalCartPrice5, DeliveryWorkRequest.Status.OrderPlaced.toString(), cart5);
        Order.ordersList.add(order5);

        Report reportSample = new Report();
        reportSample.setName("Annual Composting Techniques Report");
        reportSample.setFileDescription("Detailed analysis of composting techniques used throughout the year.");
        reportSample.setFilePath(Paths.get("/pdf/functionalSample.pdf"));
        ReportDirectory reportDirectory = business.getReportDirectory();
        reportDirectory.addReport(reportSample);

        Person person29 = new Person("Emma", "Patel", "emmapatel@gmail.com", "+1-(777)-666-5555", "852 Pine St, Riverside");
        Person person30 = new Person("Nathan", "Tran", "nathantran@gmail.com", "+1-(555)-444-3333", "741 Elm St, Lakeview");
        Person person31 = new Person("Olivia", "Gupta", "oliviagupta@gmail.com", "+1-(444)-333-2222", "369 Birch St, Lakeside");
        Person person32 = new Person("Andrew", "Chang", "andrewchang@gmail.com", "+1-(333)-222-1111", "123 Oak St, Hilltop");
        Person person33 = new Person("Sophia", "Singh", "sophiasingh@gmail.com", "+1-(222)-333-4444", "456 Maple St, Riverside");
        Person person34 = new Person("Jacob", "Kim", "jacobkim@gmail.com", "+1-(111)-222-3333", "753 Pine St, Lakeview");
        Person person35 = new Person("Isabella", "Wong", "isabellawong@gmail.com", "+1-(999)-888-7777", "159 Elm St, Lakeside");
        Person person36 = new Person("William", "Patel", "williampatel@gmail.com", "+1-(888)-777-6666", "357 Birch St, Hillcrest");
        Person person37 = new Person("Sophie", "Nguyen", "sophienguyen@gmail.com", "+1-(777)-666-5555", "852 Pine St, Riverside");
        Person person38 = new Person("Benjamin", "Tran", "benjamintran@gmail.com", "+1-(555)-444-3333", "741 Elm St, Lakeview");
        Person person39 = new Person("Victoria", "Gupta", "victoriagupta@gmail.com", "+1-(444)-333-2222", "369 Birch St, Lakeside");
        Person person40 = new Person("Ethan", "Chang", "ethanchang@gmail.com", "+1-(333)-222-1111", "123 Oak St, Hilltop");

        business.getPersonDirectory().addNewPerson(person29);
        business.getPersonDirectory().addNewPerson(person30);
        business.getPersonDirectory().addNewPerson(person31);
        business.getPersonDirectory().addNewPerson(person32);
        business.getPersonDirectory().addNewPerson(person33);
        business.getPersonDirectory().addNewPerson(person34);
        business.getPersonDirectory().addNewPerson(person34);
        business.getPersonDirectory().addNewPerson(person35);
        business.getPersonDirectory().addNewPerson(person36);
        business.getPersonDirectory().addNewPerson(person37);
        business.getPersonDirectory().addNewPerson(person38);
        business.getPersonDirectory().addNewPerson(person39);
        business.getPersonDirectory().addNewPerson(person40);
        return business;
    }

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
