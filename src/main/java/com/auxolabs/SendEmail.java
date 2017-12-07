package com.auxolabs;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{
    public static void main(String[] args) {

        String version="2.10.x";
        String host="outlook.office365.com";
        final String user="xxxxxxxxxxx";//change accordingly
        final String password="--";//change accordingly

        String to="xxxxx";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Capture"+version);
            message.setText("Hi Suchitra, \n \t I have attached the results of Capture  "+version+"\n\t\t\t\t\t Thank You!");
            message.setContent("<h1>sending html mail check</h1>","text/html" );
            //
            BodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "./report.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);
            Multipart multipartObject = new MimeMultipart();
            multipartObject.addBodyPart(messageBodyPart2);
            message.setContent(multipartObject);
            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}