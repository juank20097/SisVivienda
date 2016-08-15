package viviendas.model.generic;


import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {

	public static void generateAndSendEmail(String destinatario, String asunto, String mensaje) throws Exception
    {
		String origen = "software_solutions@yachay.gob.ec";
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.yachay.gob.ec");
        props.put("mail.from", origen);
        //props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.starttls.enable", "false"); 
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Authenticator authenticator = new Authenticator();
        props.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
        BodyPart texto = new MimeBodyPart();
        texto.setContent(mensaje, "text/html; charset=utf-8");
        
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
        
        //COPIA PARA ORIGEN
//        InternetAddress ccArr[] = new InternetAddress[1];
//        ccArr[0] = new InternetAddress("lcorrea@yachay.gob.ec");
        
        Session session = Session.getInstance(props, authenticator);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(origen));
//        msg.setRecipients(Message.RecipientType.CC, ccArr);
        msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse(destinatario));
        msg.setSubject(asunto);
        msg.setContent(multiParte);
        msg.setFrom();
     
        Transport transport;
        transport = session.getTransport("smtp");
        transport.connect();
        msg.saveChanges(); 
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        System.out.println("ENVIO DE MENSAJE CORRECTO PARA "+destinatario);
    }
}