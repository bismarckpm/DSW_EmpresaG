package com.empresag;

import com.empresag.analisis.ComandoCrearConclusion;
import com.empresag.estudio.ComandoEditarEstudio;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

@Path("/analytics")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class AnalyticsService {
    String JPQL = null;
    Query q = null;

    @GET
    @Path("/open-text/{studyId}")
    public Response getOpenTextResponses(@PathParam("studyId") long studyId){
        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
        try {
            List<PreguntaEstudioDto> encuesta = daoAnalisis.getOpenTextAnswers(studyId);
            return Response.ok().entity(encuesta).build();
        }
        catch (NullPointerException | IndexDatabaseException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/selection/{studyId}")
    public Response getSelectionResponses(@PathParam("studyId") long studyId){
        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
        try {
            List<PreguntaEstudioDto> encuesta = daoAnalisis.getSelectionAnswers(studyId);
            return Response.ok().entity(encuesta).build();
        }
        catch (NullPointerException | IndexDatabaseException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/true-false/{studyId}")
    public Response getTrueFalseResponses(@PathParam("studyId") long studyId){
        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
        try {
            List<PreguntaEstudioDto> encuesta = daoAnalisis.getTrueFalseAnswers(studyId);
            return Response.ok().entity(encuesta).build();
        }
        catch (NullPointerException | IndexDatabaseException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/range/{studyId}")
    public Response getRangeResponses(@PathParam("studyId") long studyId){
        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
        try {
            List<PreguntaEstudioDto> encuesta = daoAnalisis.getRangeAnswers(studyId);
            return Response.ok().entity(encuesta).build();
        }
        catch (NullPointerException | IndexDatabaseException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/find/{studyId}")
    public Response findConclusion(@PathParam("studyId") long id){
        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
        AnalisisEntity analisis = null;
        try {
            analisis = daoAnalisis.getAnalisis(id);
            return Response.ok().entity(analisis).build();
        }
        catch (NoResultException | NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    @POST
//    @Path("/add/{studyId}")
//    public Response addConclusion(@PathParam("studyId") long studyId, AnalisisDto analisisDto){
//        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
//        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
//
//        try {
//            EstudioEntity estudio = daoEstudio.find(studyId, EstudioEntity.class);
//
//            AnalisisEntity analisis = new AnalisisEntity();
//            analisis.setConclusiones(analisisDto.getConclusiones());
//            daoAnalisis.insert(analisis);
//
//            estudio.setEstado(2);
//            estudio.setFkAnalisis(analisis);
//            estudio.setFechaCulminacion(new Date(System.currentTimeMillis()));
//            daoEstudio.update(estudio);
//
//            return Response.ok().entity(analisis).build();
//        }
//        catch (NullPointerException e){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @POST
    @Path("/add/{studyId}")
    public RespuestaDto<AnalisisDto> addConclusion(@PathParam("studyId") long studyId, AnalisisDto analisisDto){
        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();

        RespuestaDto<AnalisisDto> respuesta = new RespuestaDto<>();
        try {
            EstudioEntity estudio = daoEstudio.find(studyId, EstudioEntity.class);

            ComandoCrearConclusion crearAnalisis = new ComandoCrearConclusion(analisisDto);
            crearAnalisis.execute();

            AnalisisEntity analisis = AnalisisMapper.mapDtoToEntity(analisisDto);

            estudio.setEstado(2);
            estudio.setFkAnalisis(analisis);
            estudio.setFechaCulminacion(new Date(System.currentTimeMillis()));
            EstudioDto estudioDto = EstudioMapper.mapEntityToDto(estudio);

            ComandoEditarEstudio editarEstudio = new ComandoEditarEstudio(studyId,estudioDto);
            editarEstudio.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( analisisDto );
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
