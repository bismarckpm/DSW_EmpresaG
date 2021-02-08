package com.empresag;

import com.empresag.token.ComandoCrearToken;
import com.empresag.token.ComandoEditarToken;
import org.eclipse.persistence.sessions.Login;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;

@Path("/login")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LoginService {

//    @POST
//    @Path("/authenticate")
//    public UsuarioDto currentUser(UsuarioDto usuarioDto) throws IndexDatabaseException {
//        boolean authLDAP;
//
//        DaoUsuario daoUsuario = new DaoUsuario();
//        DaoToken daoToken = new DaoToken();
//        DirectorioActivo ldap = new DirectorioActivo();
//
//        UsuarioEntity usuarioEntity = daoUsuario.findUserByEmail(usuarioDto.getEmail());
//        String token = null;
//        UsuarioDto authenticatedUser = new UsuarioDto();
//        TokenEntity tokenEntity = null;
//
//        /* If user doesn't exist */
//
//        try {
//            tokenEntity = daoToken.getUserToken(usuarioEntity.get_id());
//        }
//        catch (NullPointerException e){
//            return null;
//        }
//
//        authLDAP = ldap.userAuthentication(usuarioDto);
//
//        if (authLDAP){
//            token = daoToken.getAlphaNumericString(25);
//            if (tokenEntity != null){
//                tokenEntity.setToken_login(token);
//                daoToken.update(tokenEntity);
//            }
//            else {
//                tokenEntity = new TokenEntity();
//                tokenEntity.setFkUsuario(usuarioEntity);
//                tokenEntity.setToken_login(token);
//                daoToken.insert(tokenEntity);
//            }
//            RolDto rol = new RolDto();
//            rol.set_id(usuarioEntity.getFk_Rol().get_id());
//            authenticatedUser.setFkRol(rol);
//            authenticatedUser.setTokenLogin(token);
//            authenticatedUser.set_id(usuarioEntity.get_id());
//            return authenticatedUser;
//        }
//        return null;
//    }

    @POST
    @Path("/authenticate")
    public UsuarioDto currentUser(UsuarioDto usuarioDto) throws IndexDatabaseException, LoginException {
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

            if (tokenEntity != null) {
                try{
                    tokenEntity.setToken_login(token);
                    ComandoEditarToken editarToken = new ComandoEditarToken(tokenEntity.get_id(), TokenMapper.mapEntityToDto(tokenEntity));
                    editarToken.execute();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                try {
                    tokenEntity = new TokenEntity();
                    tokenEntity.setFkUsuario(usuarioEntity);
                    tokenEntity.setToken_login(token);
                    ComandoCrearToken crearToken = new ComandoCrearToken(TokenMapper.mapEntityToDto(tokenEntity));
                    crearToken.execute();
                }
                catch (Exception e){
                    e.printStackTrace();

                    throw new LoginException();
                }
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
    public void logout(@PathParam("hash") String hash) throws LogoutException {
        DaoUsuario usuarioDao = new DaoUsuario();
        DaoToken tokenDao = new DaoToken();

        try {
            TokenEntity currentToken = tokenDao.getTokenByHASH(hash,true);

            currentToken.setToken_login(null);
            ComandoEditarToken editarToken = new ComandoEditarToken(currentToken.get_id(), TokenMapper.mapEntityToDto(currentToken));
            editarToken.execute();
        }
        catch (Exception e){
            e.printStackTrace();

            throw new LogoutException();
        }
    }
}
