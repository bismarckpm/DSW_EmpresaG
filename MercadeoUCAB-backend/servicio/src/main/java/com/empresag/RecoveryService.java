package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/recovery")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RecoveryService {
    @POST
    @Path("/{email}")
    public String correoRecuperar(@PathParam("email") String email){
        DaoToken tokenDao = new DaoToken();
        DaoUsuario usuarioDao = new DaoUsuario();
        long id_user;

        String hash = tokenDao.getHASH();

        List<UsuarioEntity> users = usuarioDao.emailExist(email);
        if (!users.isEmpty()){
            id_user = users.get(0).get_id();

            TokenEntity token = tokenDao.findToken(id_user);

            token.setToken_reset(hash);

            tokenDao.update(token);

            Email em = new Email(email,hash);

            return em.sendEmail();
        }
        else
            return "notfound";
    }

    @POST
    @Path("/change/{email}/pass/{newPassword}")
    public void changePassword(@PathParam("email") String email,@PathParam("newPassword") String password){
        DaoUsuario usuarioDao = new DaoUsuario();
        UserService userservice = new UserService();
        List<UsuarioEntity> users = usuarioDao.emailExist(email);
        UsuarioEntity user = users.get(0);
        user.setPassword(password);

        UsuarioDto dtoUsuario = userservice.getUser(user.get_id());
        dtoUsuario.setPassword(password);

        DirectorioActivo ldap = new DirectorioActivo();

        ldap.changePassword(dtoUsuario);

        usuarioDao.update(user);

        DaoToken tokenDao = new DaoToken();

        tokenDao.deleteTokenReset(user.get_id());
    }
}
