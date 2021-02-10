package com.empresag;

import com.empresag.opcion.ComandoEliminarOpcion;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OptionService {
//    @GET
//    @Path("/all-by-question/{id}")
//    public List<OpcionEntity> allQuestionOptions(@PathParam("id") long id){
//        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
//        return daoOpcion.getAllOptionsByQuestion(id);
//    }

    @GET
    @Path("/all-by-question/{id}")
    public RespuestaDto<List<OpcionEntity>> allQuestionOptions(@PathParam("id") long id){
        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();

        RespuestaDto<List<OpcionEntity>> respuesta = new RespuestaDto<>();

        try{
            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( daoOpcion.getAllOptionsByQuestion(id) );
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

    @DELETE
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteOption(@PathParam("id") long id){

        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            ComandoEliminarOpcion eliminarOpcion = new ComandoEliminarOpcion(id);
            eliminarOpcion.execute();

            if (eliminarOpcion.getResult()){
                respuesta.setCodigo(0);
                respuesta.setEstado( "OK" );
                respuesta.setMensaje("Opcion eliminada");
            }else{
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( "Opcion no eliminada" );
            }

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
