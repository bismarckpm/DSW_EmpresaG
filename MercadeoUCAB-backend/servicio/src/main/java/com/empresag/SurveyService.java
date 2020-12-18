package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;

@Path("/survey")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SurveyService {
    @GET
    @Path("/available/{userId}")
    public Response getAvailableSurveys(@PathParam("userId") long userId){
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        try {
            List<FiltroEntity> encuestas = daoEncuesta.getAvailableSurveys(userId);
            return Response.ok().entity(encuestas).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @GET
    @Path("/available-population/{id}")
    public Response getAvailablePopulation(@PathParam("id") long id){
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        try {
            List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(id);
            return Response.ok().entity(personas).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/available-population/{studyId}/{userId}")
    public Response isPersonPartOfAvailablePopulation(@PathParam("studyId") long studyId, @PathParam("userId") long userId){
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        boolean isPartOfAvailablePopulation = daoEncuesta.isPersonPartOfAvailablePopulation(studyId, userId);
        if (isPartOfAvailablePopulation){
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/available-population-interview/{studyId}/{personId}")
    public Response isPersonPartOfAvailablePopulationInterview(@PathParam("studyId") long studyId, @PathParam("personId") long personId){
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        boolean isPartOfAvailablePopulation = daoEncuesta.isPersonPartOfAvailablePopulationInterview(studyId, personId);
        if (isPartOfAvailablePopulation){
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/take-interview/{studyId}/{personId}")
    public Response takeSurveyInterview(@PathParam("studyId") long studyId, @PathParam("personId") long personId,
                               List<EncuestaDto> encuestaDto){
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPersona daoPersona = new DaoPersona();
        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoEncuesta daoEncuesta = new DaoEncuesta();

        EstudioEntity estudio = null;
        PersonaEntity persona = null;

        try {
            estudio = daoEstudio.find(studyId, EstudioEntity.class);
            persona = daoPersona.find(personId, PersonaEntity.class);

            for (EncuestaDto ejecucionEncuesta: encuestaDto) {
                EncuestaEntity encuesta = new EncuestaEntity();
                encuesta.setFecha(new Timestamp(System.currentTimeMillis()));

                if (ejecucionEncuesta.getRespuestaTexto() != null){
                    encuesta.setRespuestaTexto(ejecucionEncuesta.getRespuestaTexto());
                }
                if (ejecucionEncuesta.getRespuestaRangoInicial() != null &&
                        ejecucionEncuesta.getRespuestaRangoFinal() != null){
                    encuesta.setRespuestaRangoInicial(ejecucionEncuesta.getRespuestaRangoInicial());
                    encuesta.setRespuestaRangoFinal(ejecucionEncuesta.getRespuestaRangoFinal());
                }
                if (ejecucionEncuesta.getFkPosibleRespuesta() != null){
                    PosibleRespuestaEntity pr = daoPosibleRespuesta.find
                            (ejecucionEncuesta.getFkPosibleRespuesta().get_id(), PosibleRespuestaEntity.class);

                    encuesta.setFkPosibleRespuesta(pr);
                }

                PreguntaEntity pregunta = daoPregunta.find(ejecucionEncuesta.getFkPregunta().get_id(),
                        PreguntaEntity.class);

                encuesta.setFkPregunta(pregunta);
                encuesta.setFkPersona(persona);
                encuesta.setFkEstudio(estudio);
                daoEncuesta.insert(encuesta);
            }
            return Response.ok().build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/take/{studyId}/{userId}")
    public Response takeSurvey(@PathParam("studyId") long studyId, @PathParam("userId") long userId,
                               List<EncuestaDto> encuestaDto){
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPersona daoPersona = new DaoPersona();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoEncuesta daoEncuesta = new DaoEncuesta();

        EstudioEntity estudio = null;
        PersonaEntity persona = null;

        try {
            estudio = daoEstudio.find(studyId, EstudioEntity.class);
            persona = daoPersona.findPersonByUser(userId);

            for (EncuestaDto ejecucionEncuesta: encuestaDto) {
                EncuestaEntity encuesta = new EncuestaEntity();
                encuesta.setFecha(new Timestamp(System.currentTimeMillis()));

                if (ejecucionEncuesta.getRespuestaTexto() != null){
                    encuesta.setRespuestaTexto(ejecucionEncuesta.getRespuestaTexto());
                }
                if (ejecucionEncuesta.getRespuestaRangoInicial() != null &&
                        ejecucionEncuesta.getRespuestaRangoFinal() != null){
                    encuesta.setRespuestaRangoInicial(ejecucionEncuesta.getRespuestaRangoInicial());
                    encuesta.setRespuestaRangoFinal(ejecucionEncuesta.getRespuestaRangoFinal());
                }
                if (ejecucionEncuesta.getFkPosibleRespuesta() != null){
                    PosibleRespuestaEntity pr = daoPosibleRespuesta.find
                            (ejecucionEncuesta.getFkPosibleRespuesta().get_id(), PosibleRespuestaEntity.class);

                    encuesta.setFkPosibleRespuesta(pr);
                }

                PreguntaEntity pregunta = daoPregunta.find(ejecucionEncuesta.getFkPregunta().get_id(),
                        PreguntaEntity.class);

                encuesta.setFkPregunta(pregunta);
                encuesta.setFkPersona(persona);
                encuesta.setFkEstudio(estudio);
                daoEncuesta.insert(encuesta);
            }
            return Response.ok().build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
