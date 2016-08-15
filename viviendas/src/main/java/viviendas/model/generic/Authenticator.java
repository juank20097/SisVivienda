package viviendas.model.generic;

import javax.mail.PasswordAuthentication;

public class Authenticator extends javax.mail.Authenticator{
	 private PasswordAuthentication authentication;
     public Authenticator() {
    	 String username = "software_solutions";
 		 String password = "Ecuador2016";
         authentication = new PasswordAuthentication(username, password);
     }
     protected PasswordAuthentication getPasswordAuthentication() {
         return authentication;
     }
}
