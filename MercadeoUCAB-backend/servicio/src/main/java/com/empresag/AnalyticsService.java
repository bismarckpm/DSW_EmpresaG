package com.empresag;

import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        DaoAnalisis daoAnalisis = new DaoAnalisis();
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
        DaoAnalisis daoAnalisis = new DaoAnalisis();
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
        DaoAnalisis daoAnalisis = new DaoAnalisis();
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
        DaoAnalisis daoAnalisis = new DaoAnalisis();
        try {
            List<PreguntaEstudioDto> encuesta = daoAnalisis.getRangeAnswers(studyId);
            return Response.ok().entity(encuesta).build();
        }
        catch (NullPointerException | IndexDatabaseException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
