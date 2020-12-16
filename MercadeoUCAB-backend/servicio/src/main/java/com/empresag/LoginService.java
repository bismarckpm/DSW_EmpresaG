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
    @Path("/{email}/link/{password}")
    public UsuarioEntity currentUser(@PathParam("email") String email,@PathParam("password") String password){
        boolean authLDAP;

        UsuarioDto usuariodto = new UsuarioDto();
        usuariodto.setEmail(email);
        usuariodto.setPassword(password);

        DirectorioActivo ldap = new DirectorioActivo();

        authLDAP = ldap.userAuthentication(usuariodto);

        if (authLDAP){
            DaoUsuario usuarioDao = new DaoUsuario();
            UsuarioEntity user;
            List<UsuarioEntity> users = usuarioDao.findUsuarioLogin(email,password);

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
}
