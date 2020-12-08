package com.empresag;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LoginService {
    @POST
    @Path("/{email}/link/{password}")
    public UsuarioEntity currentUser(@PathParam("email") String email,@PathParam("password") String password){
        DaoUsuario usuarioDao = new DaoUsuario();
        UsuarioEntity user;
        List<UsuarioEntity> users = usuarioDao.findUsuarioLogin(email,password);

        if (!users.isEmpty()){
            user = users.get(0);

            //return Response.ok().entity(user).build();
            return user;
        }
        else
            return null;
            //return Response.status(Response.Status.NOT_FOUND).build();
    }
}
