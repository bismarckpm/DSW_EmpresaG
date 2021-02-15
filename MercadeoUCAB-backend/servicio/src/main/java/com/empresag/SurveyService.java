package com.empresag;

import com.empresag.Extras.TmpAvailablePopulation;
import com.empresag.Extras.TmpAvailableSurveys;
import com.empresag.survey.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/survey")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SurveyService {

    @GET
    @Path("/available/{userId}")
    public RespuestaDto<List<TmpAvailableSurveys>> getAvailableSurveys(@PathParam("userId") long userId)
    {
        ComandoGetAvailableSurveys comando = new ComandoGetAvailableSurveys(userId);
        comando.execute();

        return comando.getResult();
    }

    @GET
    @Path("/available-population/{id}")
    public RespuestaDto<List<TmpAvailablePopulation>> getAvailablePopulation(@PathParam("id") long id)
    {
        ComandoGetAvailablePopulation comando = new ComandoGetAvailablePopulation(id);
        comando.execute();

        return comando.getResult();
    }

    @GET
    @Path("/available-population/{studyId}/{userId}")
    public RespuestaDto<Boolean> isPersonPartOfAvailablePopulation(@PathParam("studyId") long studyId,
                                                                   @PathParam("userId") long userId)
    {

        ComandoIsPersonPartOfAvailablePopulation comando = new ComandoIsPersonPartOfAvailablePopulation(studyId,userId);
        comando.execute();
        return comando.getResult();
    }

    @GET
    @Path("/available-population-interview/{studyId}/{personId}")
    public RespuestaDto<Boolean> isPersonPartOfAvailablePopulationInterview(@PathParam("studyId") long studyId,
                                                                            @PathParam("personId") long personId)
    {
//        DaoEncuesta daoEncuesta = new DaoEncuesta();
//        boolean isPartOfAvailablePopulation = daoEncuesta.isPersonPartOfAvailablePopulationInterview(studyId, personId);
//        if (isPartOfAvailablePopulation){
//            return Response.ok().build();
//        }
//        else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
        ComandoIsPersonPartOfAvailablePopulationInterview comando = new ComandoIsPersonPartOfAvailablePopulationInterview(studyId, personId);
        comando.execute();
        return comando.getResult();
    }

    @POST
    @Path("/take-interview/{studyId}/{personId}")
    public RespuestaDto<Boolean> takeSurveyInterview(@PathParam("studyId") long studyId, @PathParam("personId") long personId,
                               List<EncuestaDto> encuestaDto)
    {
        ComandoTakeSurveyInterview comando = new ComandoTakeSurveyInterview(studyId,personId, encuestaDto);
        comando.execute();
        return comando.getResult();

    }

    @POST
    @Path("/take/{studyId}/{userId}")
    public RespuestaDto<Boolean> takeSurvey(@PathParam("studyId") long studyId, @PathParam("userId") long userId,
                               List<EncuestaDto> encuestaDto)
    {

        ComandoTakeSurvey comando = new ComandoTakeSurvey(studyId, userId, encuestaDto);
        comando.execute();
        return comando.getResult();
    }

}
