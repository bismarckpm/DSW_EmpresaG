package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/answers")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class AnswerService {

    @GET
    @Path("/{studyId}/{personId}/{preguntaId}")
    public RespuestaDto<List<EncuestaEntity>> getActualAnswers(@PathParam("studyId") long studyId,
                                                           @PathParam("personId") long personId,
                                                           @PathParam("preguntaId") long preguntaId)
    {

        RespuestaDto<List<EncuestaEntity>> respuesta = new RespuestaDto<>();
        try {

            DaoEncuesta daoEncuesta = FabricaDao.crearDaoEncuesta();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto(daoEncuesta
                    .getActualQuestionAnswer(studyId, personId, preguntaId));

        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado( "ERROR" );
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }

        return respuesta;

    }

}
