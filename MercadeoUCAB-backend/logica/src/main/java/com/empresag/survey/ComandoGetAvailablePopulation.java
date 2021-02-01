package com.empresag.survey;

import com.empresag.*;

import java.util.List;

public class ComandoGetAvailablePopulation extends ComandoBase {
    private long id;
    private RespuestaDto<List<PersonaEntity>> respuesta;

    public ComandoGetAvailablePopulation(long id) {
        this.id = id;
        respuesta = new RespuestaDto<>();
    }

    @Override
    public void execute() {
        DaoEncuesta daoEncuesta = FabricaDao.crearDaoEncuesta();

        try {
            List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(id);

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(personas);
//            return Response.ok().entity(encuestas).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje( "No se encontro poblacion disponible." );
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
    public RespuestaDto<List<PersonaEntity>> getResult() {
        return respuesta;
    }
}
