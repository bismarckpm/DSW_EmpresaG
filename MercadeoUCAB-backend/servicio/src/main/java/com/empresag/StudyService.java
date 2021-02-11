package com.empresag;

import com.empresag.study.ComandoAssignStudy;
import com.empresag.study.ComandoLinkCreateQuestionToStudy;
import com.empresag.study.ComandoUpdateStudyFilter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

@Path("/studies")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class StudyService {
    @GET
    @Path("/existing")
    public RespuestaDto<List<FiltroEntity>> allExistingStudies(){
//        DaoFiltro daoFiltro = new DaoFiltro();
//        return daoFiltro.getAllStudies();

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        RespuestaDto<List<FiltroEntity>> res = new RespuestaDto<>();

        try {
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(daoFiltro.getAllStudies());
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @GET
    @Path("/active")
    public RespuestaDto<List<FiltroEntity>> allActiveStudies(){
//        DaoFiltro daoFiltro = new DaoFiltro();
//        return daoFiltro.getAllActiveStudies();

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        RespuestaDto<List<FiltroEntity>> res = new RespuestaDto<>();

        try {
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(daoFiltro.getAllActiveStudies());
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @GET
    @Path("/similar/{idCategoria}")
    public RespuestaDto<List<FiltroEntity>> getSimilarStudies(@PathParam("idCategoria") long id){
//        DaoFiltro daoFiltro = new DaoFiltro();
//        return daoFiltro.getSimilarStudies(id);

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        RespuestaDto<List<FiltroEntity>> res = new RespuestaDto<>();

        try {
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(daoFiltro.getSimilarStudies(id));
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @GET
    @Path("/filters/{id}")
    public RespuestaDto<FiltroEntity> getCurrentStudy(@PathParam("id") long id){
        //Response
//        DaoFiltro daoFiltro = new DaoFiltro();
//        try {
//            FiltroEntity estudio = daoFiltro.getCurrentStudy(id);
//            return Response.ok().entity(estudio).build();
//        }
//        catch (NullPointerException e){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }

        DaoFiltro daoFiltro = FabricaDao.crearDaoFiltro();
        RespuestaDto<FiltroEntity> res = new RespuestaDto<>();

        try{
            FiltroEntity estudio = daoFiltro.getCurrentStudy(id);
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(estudio);
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @GET
    @Path("/questions/{id}")
    public RespuestaDto<List<PreguntaCatSubcatEntity>> getStudyQuestions(@PathParam("id") long id){
        //Response
//        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
//        try {
//            List<PreguntaCatSubcatEntity> preguntas = daoPreguntaEstudio.getStudyQuestions(id);
//            return Response.ok().entity(preguntas).build();
//        }
//        catch (NullPointerException e){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }

        DaoPreguntaEstudio daoPreguntaEstudio = FabricaDao.crearDaoPreguntaEstudio();
        RespuestaDto<List<PreguntaCatSubcatEntity>> res = new RespuestaDto();

        try{
            List<PreguntaCatSubcatEntity> preguntas = daoPreguntaEstudio.getStudyQuestions(id);
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(preguntas);
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @GET
    @Path("/questions-with-options/{id}")
    public RespuestaDto<List<PreguntaEstudioDto>> getStudyQuestionsWithOptions(@PathParam("id") long id){
//        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
//        try {
//            List<PreguntaEstudioDto> preguntas = daoPreguntaEstudio.getStudyQuestionsWithOptions(id);
//            return Response.ok().entity(preguntas).build();
//        }
//        catch (NullPointerException | IndexDatabaseException e){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }

        DaoPreguntaEstudio daoPreguntaEstudio = FabricaDao.crearDaoPreguntaEstudio();
        RespuestaDto<List<PreguntaEstudioDto>> res = new RespuestaDto();

        try{
            List<PreguntaEstudioDto> preguntas = daoPreguntaEstudio.getStudyQuestionsWithOptions(id);
            res.setCodigo(0);
            res.setEstado("OK");
            res.setObjeto(preguntas);
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;

    }

    @POST
    @Path("/assign/{clonedId}/{requestId}")
    public RespuestaDto<FiltroEntity> assignStudy(@PathParam("clonedId") long id, @PathParam("requestId") long rid){

        RespuestaDto<FiltroEntity> res = new RespuestaDto<>();
        ComandoAssignStudy assignStudy = new ComandoAssignStudy(id, rid);

        try{
            assignStudy.execute();
            res.setCodigo(0);
            res.setEstado("OK");
            res.setMensaje("Estudio asignado correctamente");
            res.setObjeto(assignStudy.getResult());
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return  res;
    }

    @POST
    @Path("/questions/{studyId}/link/{questionId}")
    public RespuestaDto<PreguntaEstudioEntity> linkCreatedQuestionToStudy(@PathParam("studyId") long studyId,
                                               @PathParam("questionId") long questionId){

        RespuestaDto<PreguntaEstudioEntity> res = new RespuestaDto<>();
        ComandoLinkCreateQuestionToStudy linkCreateQuestionToStudy= new ComandoLinkCreateQuestionToStudy(studyId,questionId);

        try{
            linkCreateQuestionToStudy.execute();
            res.setCodigo(0);
            res.setEstado("OK");
            res.setMensaje("Pregunta enlazada correctamente");
            res.setObjeto(linkCreateQuestionToStudy.getResult());
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;
    }

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<FiltroEntity> updateStudyFilters(@PathParam("id") long id, FiltroDto filtroDto){

        RespuestaDto<FiltroEntity> res = new RespuestaDto<>();
        ComandoUpdateStudyFilter updateStudyFilter = new ComandoUpdateStudyFilter(id, filtroDto);

        try {
            updateStudyFilter.execute();
            res.setCodigo(0);
            res.setEstado("OK");
            res.setMensaje("Filtro actualizado correctamente");
            res.setObjeto(updateStudyFilter.getResult());
        }catch (Exception e){
            e.printStackTrace();
            res.setCodigo(-1);
            res.setEstado("ERROR");
            res.setMensaje(e.getMessage());
        }

        return res;

    }
}
