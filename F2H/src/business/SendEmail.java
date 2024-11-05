/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import business.customer.Cart;
import business.products.Product;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

/**
 *
 * @author bhavanidevulapalli
 */
public class SendEmail {

    public static void sendEmail(String sendTo, Cart cart, String totalAmount) {

        final String username = "freshhome670@gmail.com"; //sender's email
        final String password = "ocnm kizh kckj dber"; // create an app specific password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendTo)
            );
            message.setSubject("Testing Gmail ");
            message.setText(generateInvoiceEmailBody(cart, totalAmount));

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private static String generateInvoiceEmailBody(Cart cart, String totalAmount) {
        StringBuilder body = new StringBuilder();
        body.append("Subject: Your Order Invoice\n\n");
        body.append("Dear ").append(cart.getCustomerID()).append(",\n\n");
        body.append("Thank you for your recent order with us. Below is the invoice for your purchase:\n\n");
        body.append("Invoice Number: ").append(new Random().nextInt(90000) + 10000).append("\n");

        body.append("Product Details:\n");
        for (Map.Entry<Integer, Product> entry : cart.getProductMap().entrySet()) {
            Product product = entry.getValue();
            body.append(product.getName()).append(" - Quantity: ").append(product.getQuantity()).append(", Price: $").append(product.getPrice() * product.getQuantity()).append("\n");
        }
        body.append("\n");

        double totalPrice = Double.valueOf(totalAmount);
        body.append("Total Price: $").append(totalPrice).append("\n\n");

        body.append("Thank you for choosing our services. If you have any questions or concerns regarding your order, feel free to contact us by sending an email to 'freshhome670@gmail.com' .\n\n");
        body.append("Sincerely,\nFresh2Home :) ");

        return body.toString();
    }

}
