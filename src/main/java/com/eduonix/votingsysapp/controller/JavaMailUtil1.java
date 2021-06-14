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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.eduonix.votingsysapp.controller.JavaMailUtil1;

public class JavaMailUtil1 {

	public static void sendMail(String recipient, String accadd,String nmoenic,String privateKey,String publicKey) throws MessagingException {
		
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
		
		Message message = PrepareMessage(session,EmailAccount,recipient,accadd,nmoenic,privateKey,publicKey);
		  Transport.send(message);
		  //System.out.println("message sent successfully");
		
	 
		}

		private static Message PrepareMessage(Session session, String emailAccount ,String recipient,String accadd, String nmoenic, String privateKey, String publicKey) {
			
			try {
				Message message =  new MimeMessage(session);
				message.setFrom(new InternetAddress( emailAccount));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				message.setSubject("DETAILS REGARDING YOUR AUTHORITY");
				String htmlcode = "<h1>VOTERS ADDRESS:</H1></br><h3>"+accadd +"</h3></br><H1>PUBLIC KEY:</H1></BR><h3>"+publicKey+
						"</h3></br><h1>PRIVATE KEY:</h1></br><h3>" +privateKey+"</h3></br><h1>MNEMONICS:</h1></br><h3>"+nmoenic+"</h3>";
			message.setContent(htmlcode,"text/html");
				return message;
			} catch (Exception e) {
				Logger.getLogger(JavaMailUtil1.class.getName()).log(Level.SEVERE,null,e);
				
			}
			
			return null;
		}

}
