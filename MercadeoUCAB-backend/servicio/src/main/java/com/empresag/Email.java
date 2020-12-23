package com.empresag;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Email {
    private final Properties properties = new Properties();

    private String _correo;
    private String _hash;

    private Session session;

    public Email(){}

    public Email(String correo, String hash){
        this._correo = correo;
        this._hash = hash;
    }

    private void init() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender","mercadeoucab.empresag@gmail.com");
        properties.put("mail.smtp.user", "mercadeoucab.empresag@gmail.com");
        properties.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);
    }

    public String sendEmail(){

        init();
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(_correo));
            message.setSubject("Recuperacion de clave.");
            message.setText("Por favor, dirigirse al siguiente link y seguir los pasos para completar el proceso de recuperacion de contraseña:\n\nhttp://localhost:4200/change/" + _hash);
            Transport t = session.getTransport("smtp");
            t.connect((String)properties.get("mail.smtp.user"), "Caracas1.");
            t.sendMessage(message, message.getAllRecipients());
            t.close();

            return "success";
        }catch (MessagingException me){
            Logger.getLogger(Email.class.getName()).log(Level.WARNING,"No se pudo enviar el email",me);
            return "error";
        }
        catch(Exception e){
            e.printStackTrace();
            return "error";

        }

    }
}
