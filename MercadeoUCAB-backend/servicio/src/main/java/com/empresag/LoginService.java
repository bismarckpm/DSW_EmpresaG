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
    public UsuarioDto currentUser(UsuarioDto usuarioDto) throws IndexDatabaseException {
        boolean authLDAP;

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoToken daoToken = new DaoToken();
        DirectorioActivo ldap = new DirectorioActivo();

        UsuarioEntity usuarioEntity = daoUsuario.findUserByEmail(usuarioDto.getEmail());
        String token = null;
        UsuarioDto authenticatedUser = new UsuarioDto();
        TokenEntity tokenEntity = null;

        /* If user doesn't exist */

        try {
            tokenEntity = daoToken.getUserToken(usuarioEntity.get_id());
        }
        catch (NullPointerException e){
            return null;
        }

        authLDAP = ldap.userAuthentication(usuarioDto);

        if (authLDAP){
            token = daoToken.getAlphaNumericString(25);
            if (tokenEntity != null){
                tokenEntity.setToken_login(token);
                daoToken.update(tokenEntity);
            }
            else {
                tokenEntity = new TokenEntity();
                tokenEntity.setFkUsuario(usuarioEntity);
                tokenEntity.setToken_login(token);
                daoToken.insert(tokenEntity);
            }
            RolDto rol = new RolDto();
            rol.set_id(usuarioEntity.getFk_Rol().get_id());
            authenticatedUser.setFkRol(rol);
            authenticatedUser.setTokenLogin(token);
            authenticatedUser.set_id(usuarioEntity.get_id());
            return authenticatedUser;
        }
        return null;
    }

    @POST
    @Path("/logout/{hash}")
    public void logout(@PathParam("hash") String hash){
        DaoUsuario usuarioDao = new DaoUsuario();
        DaoToken tokenDao = new DaoToken();

        TokenEntity currentToken = tokenDao.getTokenByHASH(hash,true);

        tokenDao.deleteTokenLogin(currentToken.getFkUsuario().get_id());
    }
}
