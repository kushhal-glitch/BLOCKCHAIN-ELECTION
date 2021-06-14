package com.eduonix.votingsysapp.controller;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.eduonix.votingsysapp.controller.PKC_send;

public class PKC_send {

	public static void sendMail(String recipient) throws MessagingException {
		
		Properties properties = new Properties();
		//String acc1 = Accounts.accountGive();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String EmailAccount = "blockvoteencrypt@gmail.com";
		String password ="blockpass123";
		
		
		Session session = Session.getInstance(properties, new Authenticator(){
	protected PasswordAuthentication getPasswordAuthentication()	
	{
		return new PasswordAuthentication(EmailAccount,password);
		
	}
			
		});
		
		Message message = PrepareMessage(session,EmailAccount,recipient);
		  Transport.send(message);
		  //System.out.println("message sent successfully");
		
	 
		}

		private static Message PrepareMessage(Session session, String emailAccount ,String recipient) {
			
			try {
				Message message =  new MimeMessage(session);
				message.setFrom(new InternetAddress( emailAccount));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				message.setSubject("SUCCESSFULL VOTE");
				String htmlcode = "<H2THANKS FOR VOTING IN ELECTION 2021,YOUR VOTE HAS BEEN SECURED IN OUR BLOCKVOTE</H2></BR>"+"<H2>WE WILL PUBLISH THE ELECTION RESULTS SOON<H2>" ;
			message.setContent(htmlcode,"text/html");
				return message;
			} catch (Exception e) {
				Logger.getLogger(PKC_send.class.getName()).log(Level.SEVERE,null,e);
				
			}
			
			return null;
		}

}
