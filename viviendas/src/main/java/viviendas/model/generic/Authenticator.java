package viviendas.model.generic;

import javax.mail.PasswordAuthentication;

public class Authenticator extends javax.mail.Authenticator{
	 private PasswordAuthentication authentication;
     public Authenticator() {
    	 String username = "scvtalentohumano";
 		 String password = "Sistemas@2015";
         authentication = new PasswordAuthentication(username, password);
     }
     protected PasswordAuthentication getPasswordAuthentication() {
         return authentication;
     }
}
