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

    /*
    * Método para iniciar sesión en el sistema:
    * Recibe como parámetro el usuario y la contraseña proporcionada por el usuario
    * Verificará si el email proporcionado existe en el sistema
    * Verificará si existe un token de inicio de sesión en la base de datos y si ese es el caso lo modificará
    * en caso de que no exista dicho registro se creará en la base de datos
    * Ejecutará la autenticación con LDAP
     */

    @POST
    @Path("/authenticate")
    public RespuestaDto<UsuarioDto> currentUser(UsuarioDto usuarioDto) throws IndexDatabaseException, LoginException {
        boolean authLDAP;
        RespuestaDto<UsuarioDto> respuesta = new RespuestaDto<>();

        try {
            DaoUsuario daoUsuario = FabricaDao.crearDaoUsuario();
            DaoToken daoToken = FabricaDao.crearDaoToken();
            DirectorioActivo ldap = new DirectorioActivo();

            UsuarioEntity usuarioEntity = daoUsuario.findUserByEmail(usuarioDto.getEmail());
            String token = null;
            UsuarioDto authenticatedUser = FabricaDto.crearUsuarioDto();
            TokenEntity tokenEntity = null;

            /* If user doesn't exist */

            try {
                tokenEntity = daoToken.getUserToken(usuarioEntity.get_id());
            } catch (NullPointerException e) {
                respuesta.setCodigo(-1);
                respuesta.setEstado("ERROR");
                respuesta.setMensaje("Data incorrecta.");

                return respuesta;
            }

            authLDAP = ldap.userAuthentication(usuarioDto);

            if (authLDAP) {
                token = daoToken.getAlphaNumericString(25);

                if (tokenEntity != null) {
                    try {
                        tokenEntity.setToken_login(token);
                        ComandoEditarToken editarToken = new ComandoEditarToken(tokenEntity.get_id(), TokenMapper.mapEntityToDto(tokenEntity));
                        editarToken.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        tokenEntity = FabricaEntity.crearTokenEntity();
                        tokenEntity.setFkUsuario(usuarioEntity);
                        tokenEntity.setToken_login(token);
                        ComandoCrearToken crearToken = new ComandoCrearToken(TokenMapper.mapEntityToDto(tokenEntity));
                        crearToken.execute();
                    } catch (Exception e) {
                        e.printStackTrace();

                        respuesta.setCodigo(-1);
                        respuesta.setEstado("ERROR");
                        respuesta.setMensaje("Usuario o clave incorrectos.");
                        return respuesta;
                    }
                }
                RolDto rol = FabricaDto.crearRolDto();
                rol.set_id(usuarioEntity.getFk_Rol().get_id());
                authenticatedUser.setFkRol(rol);
                authenticatedUser.setTokenLogin(token);
                authenticatedUser.set_id(usuarioEntity.get_id());

                respuesta.setCodigo(0);
                respuesta.setEstado("OK");
                respuesta.setObjeto(authenticatedUser);
            }
            else{
                respuesta.setCodigo(-1);
                respuesta.setEstado("ERROR");
                respuesta.setMensaje("Usuario o clave incorrectos.");
            }

            return respuesta;
        }
        catch(Exception e){
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("Usuario o clave incorrectos.");
            return respuesta;
        }

    }

    /*
    * Método para cerrar sesión:
    * Limpia el token de inicio de sesión de la base de datos
    * */

    @POST
    @Path("/logout/{hash}")
    public void logout(@PathParam("hash") String hash) throws LogoutException {
        DaoToken tokenDao = FabricaDao.crearDaoToken();

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
