package com.empresag;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.Path;

@Path("/login")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LoginService {
    @POST
    @Path("/authenticate")
    public UsuarioEntity currentUser(UsuarioDto usuarioDto){
        boolean authLDAP;

        DirectorioActivo ldap = new DirectorioActivo();

        authLDAP = ldap.userAuthentication(usuarioDto);

        if (authLDAP){
            DaoUsuario usuarioDao = new DaoUsuario();
            UsuarioEntity user;
            List<UsuarioEntity> users = usuarioDao.findUsuarioLogin(usuarioDto.getEmail(), usuarioDto.getPassword());

            if (!users.isEmpty()){
                user = users.get(0);
                return user;
            }
            else
                return null;
        }
        else
            return null;
    }

    @POST
    @Path("/{email}")
    public void correoRecuperar(@PathParam("email") String email){
        String remitente = "mercadeoucab";

        Address destinatario = new Address() {
            @Override
            public String getType() {
                return null;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }
        };

        Properties props = System.getProperties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.user","mercadeoucab");
        props.put("mail.smtp.clave","claveMercadeo");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.startls.enable","true");
        props.put("mail.smtp.port","587");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try{
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, destinatario);
            message.setSubject("Recupercaion de clave");
            message.setText("hola hola hola");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com",remitente,"claveMercadeo");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (javax.mail.MessagingException ex){
            ex.printStackTrace();
        }
    }
}
