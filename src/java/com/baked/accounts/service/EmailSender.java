/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.accounts.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author khwezi
 */
public class EmailSender {
    final String senderEmailID="nc.resplug@gmail.com";
	final String senderPassword="NCholdings123";
	final String emailSMTPServer="smtp.gmail.com";
	final String emailServerPort ="465";
	String recieverEmail;
        String emailSubject;
        String emailBody;
	public static void main(String[]args){
             try {
            new EmailSender("celeswag4@gmail.com","Password Recovery","To reset your password please click on this link: ");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
                 System.out.println(false);
        }
            System.out.println("true");
        }
	public EmailSender(String recieverEmailID, String subject, String body) throws MessagingException {
		this.recieverEmail=recieverEmailID;
		this.emailSubject = subject;
		//Body
		this.emailBody= body;
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPServer);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		SecurityManager security = System.getSecurityManager();
		
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(body);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recieverEmailID));
			Transport.send(msg);
			
		
	}
	public class SMTPAuthenticator extends javax.mail.Authenticator{
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmailID, senderPassword);
		}
		
	}
}


