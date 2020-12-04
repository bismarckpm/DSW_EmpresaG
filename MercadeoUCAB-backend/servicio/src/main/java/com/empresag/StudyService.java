package com.empresag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/studies")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class StudyService {
    @GET
    @Path("/existing")
    public List<FiltroEntity> allExistingStudies(){
        DaoFiltro daoFiltro = new DaoFiltro();
        return daoFiltro.findAll(FiltroEntity.class);
    }

    @GET
    @Path("/filters/{id}")
    public Response getCurrentStudy(@PathParam("id") long id){
        DaoFiltro daoFiltro = new DaoFiltro();
        try {
            FiltroEntity estudio = daoFiltro.getCurrentStudy(id);
            return Response.ok().entity(estudio).build();
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/questions/{id}")
    public Response getStudyQuestions(@PathParam("id") long id){
        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        try {
            List<PreguntaCatSubcatEntity> preguntas = daoPreguntaEstudio.getStudyQuestions(id);
            return Response.ok().entity(preguntas).build();
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
