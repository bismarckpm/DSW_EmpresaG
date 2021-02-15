package com.empresag.survey;

import com.empresag.*;
import com.empresag.Extras.TmpAvailableSurveys;

import java.util.List;

public class ComandoGetAvailableSurveys extends ComandoBase {

    private long userId;
//    private RespuestaDto<List<FiltroEntity>> respuesta;
    private RespuestaDto<List<TmpAvailableSurveys>> respuesta;

    public ComandoGetAvailableSurveys(long userId) {
        this.userId = userId;
        respuesta = new RespuestaDto<>();
    }

    @Override
    public void execute() {
        DaoEncuesta daoEncuesta = FabricaDao.crearDaoEncuesta();

        try {
//            List<FiltroEntity> encuestas = daoEncuesta.getAvailableSurveys(userId);
            List<TmpAvailableSurveys> encuestas = daoEncuesta.getAvailableSurveys(userId);

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(encuestas);
//            return Response.ok().entity(encuestas).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje( "No se encontraron encuestas disponibles." );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
//            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
//            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public RespuestaDto<List<TmpAvailableSurveys>> getResult() {
        return respuesta;
    }
}
