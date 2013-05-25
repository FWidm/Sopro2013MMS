package util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

public class Email {
	 private static String HOST = "smtp.gmail.com";
	    private static String USER = "sopro2013mms@gmail.com";
	    private static String PASSWORD = "sopramms";
	    private static String PORT = "465";
	    private static String FROM = "sopro2013mms@gmail.com";
	    private static String STARTTLS = "true";
	    private static String AUTH = "true";
	    private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    /**
	     * Sends email from sopro2013@gmail.com to the given recipient with given subject and text, accepts the string "true" and "false" as last argument for debugging purposes
	     * @param recipient
	     * @param subject
	     * @param text
	     * @param debug
	     */
	    public static synchronized void send(String recipient, String subject, String text, String debug) {
	        String recip=recipient;
	        String sub=subject;
	        String tex=text;
	        
	        Properties props = new Properties();
	 
	        props.put("mail.smtp.host", HOST);
	        props.put("mail.smtp.port", PORT);
	        props.put("mail.smtp.user", USER);
	 
	        props.put("mail.smtp.auth", AUTH);
	        props.put("mail.smtp.starttls.enable", STARTTLS);
	        props.put("mail.smtp.debug", debug);
	 
	        props.put("mail.smtp.socketFactory.port", PORT);
	        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
	        props.put("mail.smtp.socketFactory.fallback", "false");
	 
	        try {
	 
	            //Obtain the default mail session
	            Session session = Session.getDefaultInstance(props, null);
	            session.setDebug(true);
	 
	            //Construct the mail message
	            MimeMessage message = new MimeMessage(session);
	            message.setText(tex);
	            message.setSubject(sub);
	            message.setFrom(new InternetAddress(FROM));
	            message.addRecipient(RecipientType.TO, new InternetAddress(recip));
	            message.saveChanges();
	 
	            //Use Transport to deliver the message
	            Transport transport = session.getTransport("smtp");
	            transport.connect(HOST, USER, PASSWORD);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	    }

}
