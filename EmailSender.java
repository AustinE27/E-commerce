package com.example.ecommercestoreprojecttemp;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Utility class for sending emails via SMTP using Jakarta Mail.
 * Handles session setup, authentication, and message delivery.
 *
 * @author Samuel Garcia
 * @version 1.0
 * 4/28/25
 */
public class EmailSender {

    /**
     * Sends an email using the specified SMTP credentials and message parameters.
     *
     * @param fromEmail sender’s Gmail address
     * @param password  sender’s Gmail app password or OAuth token
     * @param toEmail   recipient’s email address
     * @param subject   subject line for the email
     * @param body      plain-text body of the email
     */
    public static void sendEmail(String fromEmail, String password, String toEmail, String subject, String body)
            throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}