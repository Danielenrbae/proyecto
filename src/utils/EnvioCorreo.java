package utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EnvioCorreo {
	String from = "danielenrbae@gmail.com";
	String clave = "15Enri12"; //Es una contraseña de aplicación
	String to = "";
	String asunto="";
	String mensaje="";
	MimeMessage Mimessage;
	
	// Variables para el servidor
   	String host = "smtp.gmail.com";
	String port = "465";//ssl, tls=>587";
	MailSSLSocketFactory sf;

   	// Tomar entorno del sistema
   	Properties properties = System.getProperties();
   	
   	public void correoVerificacion(String mail, String nombre, String codigo) {
   		to = mail;
   		asunto = "Correo de confirmación";
   		mensaje = "Estimado/a "+nombre+",\n"
   				+ "Ya solo falta un paso para completar su registro, verificar su dirección de correo.\n\n"
   				+ "Para ello debe introducir el código "+codigo+" en la página de verificación.\n\n"
   				+ "Un cordial saludo,\nEquipo de nexcript";

   		try {
   			sf = new MailSSLSocketFactory();
   	    	sf.setTrustAllHosts(true);
   	        // Añadir datos del servidor
   	        properties.put("mail.smtp.host", host);
   	        properties.put("mail.smtp.port", port);
   	        properties.put("mail.smtp.ssl.enable", "true");
   	        properties.put("mail.smtp.auth", "true");
   			properties.put("mail.smtp.ssl.socketFactory", sf);
   	        
   		
   	        // Instanciar una sesión de correo y añadir usuario y contraseña
   	        Session session = Session.getInstance(properties, 
   			new javax.mail.Authenticator() {
   	        	protected PasswordAuthentication getPasswordAuthentication() {
   	        		return new PasswordAuthentication(from, clave);
   				}
   	        });
   		    // Activar depuración
   		    session.setDebug(false);

   	 	    // Crear un objeto MimeMessage por defecto.
   		    Mimessage = new MimeMessage(session);

   	        // Asignar campo 'De:' al encabezado del correo
   	        Mimessage.setFrom(new InternetAddress(from));
   	        // Asignar campo 'Para:' al encabezado del correo
   	        Mimessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
   	        // Asignar campo 'Asunto:' al encabezado del correo
   	        Mimessage.setSubject(asunto);
   	        // Asignar el mensaje en sí
   	        Mimessage.setText(mensaje);
   	        // Enviar el mensaje
   	        Transport.send(Mimessage);
   		} catch (MessagingException mex) {
   			mex.printStackTrace();
   		}
   		catch (GeneralSecurityException gse) {
   			gse.printStackTrace();
   		}
   	}
   	
 
	

	public boolean enviar(String empresa, String asunto, String mensaje) {
		boolean resultado = true;
		
		to = empresa;


   		try {
   			sf = new MailSSLSocketFactory();
   	    	sf.setTrustAllHosts(true);
   	        // Añadir datos del servidor
   	        properties.put("mail.smtp.host", host);
   	        properties.put("mail.smtp.port", port);
   	        properties.put("mail.smtp.ssl.enable", "true");
   	        properties.put("mail.smtp.auth", "true");
   			properties.put("mail.smtp.ssl.socketFactory", sf);
   	        
   		
   	        // Instanciar una sesión de correo y añadir usuario y contraseña
   	        Session session = Session.getInstance(properties, 
   			new javax.mail.Authenticator() {
   	        	protected PasswordAuthentication getPasswordAuthentication() {
   	        		return new PasswordAuthentication(from, clave);
   				}
   	        });
   		    // Activar depuración
   		    session.setDebug(true);

   	 	    // Crear un objeto MimeMessage por defecto.
   		    Mimessage = new MimeMessage(session);

   	        // Asignar campo 'De:' al encabezado del correo
   	        Mimessage.setFrom(new InternetAddress(from));
   	        // Asignar campo 'Para:' al encabezado del correo
   	        Mimessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
   	        // Asignar campo 'Asunto:' al encabezado del correo
   	        Mimessage.setSubject(asunto);
   	        // Asignar el mensaje en sí
   	        Mimessage.setText(mensaje);
   	        // Enviar el mensaje
   	        Transport.send(Mimessage);
   		} catch (MessagingException mex) {
   			mex.printStackTrace();
   			resultado = false;
   		}
   		catch (GeneralSecurityException gse) {
   			gse.printStackTrace();
   			resultado = true;
   		}
		
		
//		String host;
//		String port;
//		MimeMessage Mimessage;
//		MailSSLSocketFactory sf;
//		Properties properties;
//		Session session;
//
//		host = "smtp.gmail.com";
//		port = "465";
//		properties = System.getProperties();
//
//		try {
//			sf = new MailSSLSocketFactory();
//
//			sf.setTrustAllHosts(true);
//
//			properties.put("mail.smtp.host", host);
//			properties.put("mail.smtp.port", port);
//			properties.put("mail.smtp.ssl.enable", "true");
//			properties.put("mail.smtp.auth", "true");
//			properties.put("mail.smtp.ssl.socketFactory", sf);
//
//			session = Session.getInstance(properties, new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(FROM, CLAVE);
//				}
//			});
//
//			session.setDebug(true);
//			
//			Mimessage = new MimeMessage(session);
//			
//			Mimessage.setFrom(new InternetAddress(FROM));
//			Mimessage.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
//			Mimessage.setSubject(asunto);
//			Mimessage.setText(mensaje);
//			Transport.send(Mimessage);
//			
//			
//			
//		} catch (GeneralSecurityException e) {
//			resultado= false;
//
//			e.printStackTrace();
//		} catch (AddressException e) {
//			resultado= false;
//
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			resultado= false;
//			e.printStackTrace();
//		}
		return resultado;
	}

}
