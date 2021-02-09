package com.empresag;

import com.empresag.recovery.ComandoChangePassword;
import com.empresag.recovery.ComandoRecovery;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/recovery")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RecoveryService {
    @POST
    @Path("/{email}")
    public RespuestaDto<String> correoRecuperar(@PathParam("email") String email){

        RespuestaDto<String> res = new RespuestaDto<>();

        ComandoRecovery recovery = new ComandoRecovery(email);
        try {

            recovery.execute();
            if (recovery.getResult().equals("notfound")){
                res.setCodigo(-1);
                res.setObjeto(recovery.getResult());
                res.setEstado("ERROR");
                res.setMensaje("Email no enviado");
            }else {
                res.setCodigo(0);
                res.setObjeto(recovery.getResult());
                res.setEstado("OK");
                res.setMensaje("Email enviado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;

    }

    @POST
    @Path("/{hash}/pass/{newPassword}")
    public RespuestaDto<String> changePassword(@PathParam("hash") String hash,@PathParam("newPassword") String password){

        RespuestaDto<String> res = new RespuestaDto<>();

        ComandoChangePassword changePassword = new ComandoChangePassword(hash, password);

        try {

            changePassword.execute();
            res.setCodigo(0);
            res.setObjeto(changePassword.getResult());
            res.setEstado("OK");
            res.setMensaje("Password updated");

        } catch (Exception e) {
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @POST
    @Path("/{hash}")
    public RespuestaDto<TokenEntity> existeHASH(@PathParam("hash") String hash){
        DaoToken tokenDao = FabricaDao.crearDaoToken();

        RespuestaDto<TokenEntity> res = new RespuestaDto<>();

        try {
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(tokenDao.getTokenByHASH(hash,false));
        }
        catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }
}
