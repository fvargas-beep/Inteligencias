package utilities;

import java.io.ByteArrayOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class Email {
  private static final String email = "castillorojasmariapaula12@gmail.com";
  private static final String password = "atag rwym ppnf rdkj";
  
  public static void enviarEmail(String recipient, ByteArrayOutputStream doc){
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.starttls.enable", true);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
      }
    });
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(email));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      message.setSubject("Reporte");
      Multipart multipart = new MimeMultipart();
      BodyPart textPart = new MimeBodyPart();
      textPart.setText("Se adjunta el reporte solicitado:");
      multipart.addBodyPart(textPart);
      DataSource source = new ByteArrayDataSource(doc.toByteArray(), "application/pdf");
      BodyPart documentPart = new MimeBodyPart();
      documentPart.setDataHandler(new DataHandler(source));
      documentPart.setFileName("Reporte.pdf");
      multipart.addBodyPart(documentPart);
      message.setContent(multipart);
      Transport.send(message);
    } catch (MessagingException e) {
    }
  }

}
