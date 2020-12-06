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

    @POST
    @Path("/questions/{studyId}/link/{questionId}")
    public Response linkCreatedQuestionToStudy(@PathParam("studyId") long studyId,
                                               @PathParam("questionId") long questionId){
        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoEstudio daoEstudio = new DaoEstudio();

        PreguntaCatSubcatEntity pcs = null;
        EstudioEntity estudio = null;

        try {
            pcs = daoPreguntaCategoriaSubcategoria.find(questionId,
                    PreguntaCatSubcatEntity.class);

            estudio = daoEstudio.find(studyId, EstudioEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PreguntaEstudioEntity pe = new PreguntaEstudioEntity();
        pe.setFkEstudio(estudio);
        pe.setFkPregunta(pcs.getFkPregunta());
        pe.setRequerido(1);
        daoPreguntaEstudio.insert(pe);

        return Response.ok().entity(pe).build();
    }

    @PUT
    @Path("/update/{id}")
    public Response updateStudyFilters(@PathParam("id") long id, FiltroDto filtroDto){
        DaoFiltro daoFiltro = new DaoFiltro();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
        DaoGenero daoGenero = new DaoGenero();
        DaoLugar daoLugar = new DaoLugar();

        FiltroEntity filtro = null;

        try {
            filtro = daoFiltro.find(id, FiltroEntity.class);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CategoriaEntity categoria = daoCategoria.find(filtroDto.getFkCategoria().get_id(), CategoriaEntity.class);
        SubcategoriaEntity subcategoria =
                daoSubcategoria.find(filtroDto.getFkSubcategoria().get_id(), SubcategoriaEntity.class);
        EstudioEntity estudio = daoEstudio.find(filtroDto.getFkEstudio().get_id(), EstudioEntity.class);

        EdoCivilEntity estadoCivil = null;
        GeneroEntity genero = null;
        NivelAcademicoEntity nivelAcademico = null;
        NivelSocioeconomicoEntity nivelSocioeconomico = null;
        LugarEntity lugar = null;

        /* Optional filters */
        if (filtroDto.getFkEdoCivil() != null){
            estadoCivil = daoEdoCivil.find(filtroDto.getFkEdoCivil().get_id(), EdoCivilEntity.class);
        }
        if (filtroDto.getFkGenero() != null){
            genero = daoGenero.find(filtroDto.getFkGenero().get_id(), GeneroEntity.class);
        }
        if (filtroDto.getFkNivelAcademico() != null){
            nivelAcademico = daoNivelAcademico.find
                    (filtroDto.getFkNivelAcademico().get_id(), NivelAcademicoEntity.class);
        }
        if (filtroDto.getFkNivelSocioeconomico() != null){
            nivelSocioeconomico = daoNivelSocioeconomico.find(filtroDto.getFkNivelSocioeconomico().get_id(),
                    NivelSocioeconomicoEntity.class);
        }
        if (filtroDto.getFkLugar() != null){
            lugar = daoLugar.find(filtroDto.getFkLugar().get_id(), LugarEntity.class);
        }
        if (filtroDto.getEdadMaxima() != null && filtroDto.getEdadMinima() != null){
            filtro.setEdadMinima(filtroDto.getEdadMinima());
            filtro.setEdadMaxima(filtroDto.getEdadMaxima());
        }
        if (filtroDto.getTipoFiltroLugar() != null){
            filtro.setTipoFiltroLugar(filtroDto.getTipoFiltroLugar());
        }

        filtro.setFkEstudio(estudio);
        filtro.setFkCategoria(categoria);
        filtro.setFkSubcategoria(subcategoria);
        filtro.setFkEdoCivil(estadoCivil);
        filtro.setFkGenero(genero);
        filtro.setFkNivelAcademico(nivelAcademico);
        filtro.setFkNivelSocioeconomico(nivelSocioeconomico);
        filtro.setFkLugar(lugar);
        daoFiltro.update(filtro);

        return Response.ok().entity(filtro).build();
    }
}