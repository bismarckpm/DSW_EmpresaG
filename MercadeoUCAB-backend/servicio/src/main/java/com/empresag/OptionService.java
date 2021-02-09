package com.empresag;

import com.empresag.opcion.ComandoEliminarOpcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OptionService {
    @GET
    @Path("/all-by-question/{id}")
    public List<OpcionEntity> allQuestionOptions(@PathParam("id") long id){
        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
        return daoOpcion.getAllOptionsByQuestion(id);
    }

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<OpcionDto> deleteOption(@PathParam("id") long id){

        RespuestaDto<OpcionDto> respuesta = FabricaDto.crearRespuestaDto();

        try {
            ComandoEliminarOpcion eliminarOpcion = new ComandoEliminarOpcion(id);
            eliminarOpcion.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
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
