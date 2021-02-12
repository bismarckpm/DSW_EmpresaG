package com.empresag;

import com.empresag.brand.ComandoEditarBrand;
import com.empresag.opcion.ComandoCrearOpcion;
import com.empresag.opcion.ComandoEditarOpcion;
import com.empresag.opcion.ComandoEliminarOpcion;
import com.empresag.pregunta.ComandoCrearPregunta;
import com.empresag.pregunta.ComandoEditarPregunta;
import com.empresag.respuesta.ComandoBuscarPosiblesRespuestas;
import com.empresag.respuesta.ComandoCrearPosibleRespuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/questions")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class QuestionService {

//    private void deleteAllOptions(long id){
//        String JPQL = null;
//        Query q = null;
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
//        EntityManager em = emf.createEntityManager();
//        DaoOpcion daoOpcion = new DaoOpcion();
//        DaoPregunta daoPregunta = new DaoPregunta();
//
//        PreguntaEntity pregunta = daoPregunta.find(id, PreguntaEntity.class);
//
//        List<OpcionEntity> opciones = null;
//        JPQL = "SELECT pr.fkOpcion FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :pregunta";
//        q = em.createQuery(JPQL);
//        q.setParameter("pregunta", pregunta);
//        opciones = q.getResultList();
//
//        for (OpcionEntity opcion: opciones) {
//            OpcionEntity op = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
//            daoOpcion.delete(op);
//        }
//    }

    private RespuestaDto<Boolean> deleteAllOptions(long id){
        String JPQL = null;
        Query q = null;

        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
            EntityManager em = emf.createEntityManager();
            DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();

            PreguntaEntity pregunta = daoPregunta.find(id, PreguntaEntity.class);

            List<OpcionEntity> opciones;
            JPQL = "SELECT pr.fkOpcion FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta);
            opciones = q.getResultList();

            ComandoEliminarOpcion eliminarOpcion = new ComandoEliminarOpcion(opciones);
            eliminarOpcion.executeList();

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setMensaje("Opciones eliminadas");
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

//    @GET
//    @Path("/all")
//    public List<PreguntaCatSubcatEntity> allActiveQuestions(){
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        return daoPreguntaCategoriaSubcategoria.getAllActiveQuestions();
//    }

    @GET
    @Path("/all")
    public RespuestaDto<List<PreguntaCatSubcatEntity>> allActiveQuestions(){
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();

        RespuestaDto<List<PreguntaCatSubcatEntity>> respuesta = new RespuestaDto<>();

        try{
        respuesta.setCodigo(0);
        respuesta.setEstado( "OK" );
        respuesta.setObjeto( daoPreguntaCategoriaSubcategoria.getAllActiveQuestions() );
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

//    @GET
//    @Path("/all-by-category/{id}")
//    public List<PreguntaCatSubcatEntity> allActiveQuestionsByCategory(@PathParam("id") long id){
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        return daoPreguntaCategoriaSubcategoria.getAllActiveQuestionsByCategory(id);
//    }

    @GET
    @Path("/all-by-category/{id}")
    public RespuestaDto<List<PreguntaCatSubcatEntity>> allActiveQuestionsByCategory(@PathParam("id") long id){

        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();

        RespuestaDto<List<PreguntaCatSubcatEntity>> respuesta = new RespuestaDto<>();

        try{
            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( daoPreguntaCategoriaSubcategoria.getAllActiveQuestionsByCategory(id) );
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

//    @GET
//    @Path("/find/{id}")
//    public Response getQuestion(@PathParam("id") long id){
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
//        PreguntaCatSubcatDto preguntaCatSubcatDto = new PreguntaCatSubcatDto();
//        DaoPregunta daoPregunta = new DaoPregunta();
//
//        try {
//            PreguntaCatSubcatEntity pcs = daoPreguntaCategoriaSubcategoria.find(id, PreguntaCatSubcatEntity.class);
//            preguntaCatSubcatDto.set_id(pcs.get_id());
//
//            CategoriaDto categoriaDto = new CategoriaDto();
//            preguntaCatSubcatDto.setFkCategoria(categoriaDto);
//            preguntaCatSubcatDto.getFkCategoria().set_id(pcs.getFkCategoria().get_id());
//
//
//            if (pcs.getFkSubcategoria() != null){
//                SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
//                preguntaCatSubcatDto.setFkSubcategoria(subcategoriaDto);
//                preguntaCatSubcatDto.getFkSubcategoria().set_id(pcs.getFkSubcategoria().get_id());
//            }
//
//            PreguntaDto preguntaDto = new PreguntaDto();
//            preguntaCatSubcatDto.setFkPregunta(preguntaDto);
//
//            PreguntaEntity pregunta = daoPregunta.find(pcs.getFkPregunta().get_id(),
//                    PreguntaEntity.class);
//
//            preguntaCatSubcatDto.getFkPregunta().set_id(pregunta.get_id());
//            preguntaCatSubcatDto.getFkPregunta().setPregunta(pregunta.getPregunta());
//            preguntaCatSubcatDto.getFkPregunta().setStatus(pregunta.getStatus());
//
//            TipoPreguntaDto tipoPreguntaDto = new TipoPreguntaDto();
//            preguntaCatSubcatDto.getFkPregunta().setFkTipoPregunta(tipoPreguntaDto);
//            preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().set_id
//                    (pregunta.getFkTipoPregunta().get_id());
//
//            List<OpcionDto> opcionDtos = new ArrayList<>();
//            preguntaCatSubcatDto.getFkPregunta().setListOpciones(opcionDtos);
//
//            if (pregunta.getFkTipoPregunta().get_id() == 1){
//                return Response.ok().entity(preguntaCatSubcatDto).build();
//            }
//            else {
//               List<PosibleRespuestaEntity> pr = null;
//               try {
//                   pr = daoPosibleRespuesta.getAllOptions(id);
//
//                   for (PosibleRespuestaEntity pre: pr) {
//                       OpcionDto opcionDto = new OpcionDto();
//                       if (pregunta.getFkTipoPregunta().get_id() == 5){
//                           opcionDto.setRangoInicial(pre.getFkOpcion().getRangoInicial());
//                           opcionDto.setRangoFinal(pre.getFkOpcion().getRangoFinal());
//                       }
//                       else {
//                           opcionDto.setValor(pre.getFkOpcion().getValor());
//                       }
//                       opcionDto.set_id(pre.getFkOpcion().get_id());
//                       preguntaCatSubcatDto.getFkPregunta().getListOpciones().add(opcionDto);
//                   }
//                   return Response.ok().entity(preguntaCatSubcatDto).build();
//               }
//               catch (NullPointerException e){
//                   e.printStackTrace();
//                   return Response.status(Response.Status.NOT_FOUND).build();
//               }
//            }
//        }
//        catch (NullPointerException | IndexDatabaseException e){
//            e.printStackTrace();
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @GET
    @Path("/find/{id}")
    public RespuestaDto<PreguntaCatSubcatDto> getQuestion(@PathParam("id") long id){
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();
        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();
        PreguntaCatSubcatDto preguntaCatSubcatDto = FabricaDto.crearPreguntaCatSubcatDto();

        RespuestaDto<PreguntaCatSubcatDto> respuesta = new RespuestaDto<>();

        try {
            PreguntaCatSubcatEntity pcs = daoPreguntaCategoriaSubcategoria.find(id, PreguntaCatSubcatEntity.class);
            preguntaCatSubcatDto = PreguntaCatSubcatMapper.mapEntityToDto(pcs);

            List<OpcionDto> opcionDtos = new ArrayList<>();
            preguntaCatSubcatDto.getFkPregunta().setListOpciones(opcionDtos);

            if (preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id() == 1){
                respuesta.setCodigo(0);
                respuesta.setEstado("OK");
                respuesta.setObjeto(preguntaCatSubcatDto);
                return respuesta;
            }
            else {
                List<PosibleRespuestaEntity> pr = null;
                try {
                    pr = daoPosibleRespuesta.getAllOptions(id);

                    ComandoBuscarPosiblesRespuestas buscarOpciones = new ComandoBuscarPosiblesRespuestas(preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id(),pr);
                    List<OpcionDto> list = buscarOpciones.executeList();

                    preguntaCatSubcatDto.getFkPregunta().setListOpciones(list);

                    respuesta.setCodigo(0);
                    respuesta.setEstado("OK");
                    respuesta.setObjeto(preguntaCatSubcatDto);

                    return respuesta;
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                    respuesta.setCodigo(-1);
                    respuesta.setEstado("ERROR");
                    respuesta.setMensaje("Data incorrecta.");

                    return respuesta;
                }
                catch (Exception e){
                    e.printStackTrace();
                    respuesta.setCodigo(-1);
                    respuesta.setEstado( "ERROR" );
                    respuesta.setMensaje( e.getMessage() );
                    respuesta.setMensajesoporte( e.getLocalizedMessage() );

                    return respuesta;
                }
            }
        }
        catch (NullPointerException | IndexDatabaseException e){
            e.printStackTrace();
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje("Data incorrecta.");

            return respuesta;
        }
    }

//    @POST
//    @Path("/add")
//    public PreguntaCatSubcatEntity addQuestion(PreguntaCatSubcatDto preguntaCatSubcatDto){
//        DaoPregunta daoPregunta = new DaoPregunta();
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        DaoOpcion daoOpcion = new DaoOpcion();
//        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
//        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
//        DaoCategoria daoCategoria = new DaoCategoria();
//        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
//
//        PreguntaEntity pregunta = new PreguntaEntity();
//
//        TipoPreguntaEntity tp = daoTipoPregunta.find(preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id(),
//                TipoPreguntaEntity.class);
//
//        pregunta.setFkTipoPregunta(tp);
//        pregunta.setStatus(1);
//        pregunta.setPregunta(preguntaCatSubcatDto.getFkPregunta().getPregunta());
//        daoPregunta.insert(pregunta);
//
//        PreguntaCatSubcatEntity pcs = new PreguntaCatSubcatEntity();
//
//        CategoriaEntity categoria = daoCategoria.find(preguntaCatSubcatDto.getFkCategoria().get_id(),
//                CategoriaEntity.class);
//
//        pcs.setFkCategoria(categoria);
//
//        if (preguntaCatSubcatDto.getFkSubcategoria() != null){
//            SubcategoriaEntity subcategoria = daoSubcategoria.find(preguntaCatSubcatDto.getFkSubcategoria().get_id(),
//                    SubcategoriaEntity.class);
//            pcs.setFkSubcategoria(subcategoria);
//        }
//        else {
//            pcs.setFkSubcategoria(null);
//        }
//
//        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
//        daoPreguntaCategoriaSubcategoria.insert(pcs);
//
//        /* Selection */
//        if (pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3){
//            List<OpcionDto> opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones();
//
//            for (OpcionDto opcion: opcionDto) {
//                OpcionEntity op = new OpcionEntity();
//                op.setValor(opcion.getValor());
//                daoOpcion.insert(op);
//
//                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                pr.setFkOpcion(op);
//                pr.setFkPregunta(pregunta);
//                daoPosibleRespuesta.insert(pr);
//            }
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 4){
//            List<OpcionEntity> opciones = new ArrayList<>();
//            opciones.add(new OpcionEntity("Verdadero"));
//            opciones.add(new OpcionEntity("Falso"));
//
//            for (OpcionEntity opcion: opciones) {
//                daoOpcion.insert(opcion);
//
//                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                pr.setFkOpcion(opcion);
//                pr.setFkPregunta(pregunta);
//                daoPosibleRespuesta.insert(pr);
//            }
//
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 5){
//            OpcionDto opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones().get(0);
//            OpcionEntity opcion = new OpcionEntity();
//            opcion.setRangoInicial(opcionDto.getRangoInicial());
//            opcion.setRangoFinal(opcionDto.getRangoFinal());
//            daoOpcion.insert(opcion);
//
//            PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//            pr.setFkOpcion(opcion);
//            pr.setFkPregunta(pregunta);
//            daoPosibleRespuesta.insert(pr);
//        }
//
//        return pcs;
//    }

    @POST
    @Path("/add")
    public RespuestaDto<PreguntaCatSubcatEntity> addQuestion(PreguntaCatSubcatDto preguntaCatSubcatDto){
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();

        RespuestaDto<PreguntaCatSubcatEntity> respuesta = new RespuestaDto<>();

        try {
            PreguntaDto preguntaDto = preguntaCatSubcatDto.getFkPregunta();

            preguntaDto.setStatus(1);

            ComandoCrearPregunta crearPregunta = new ComandoCrearPregunta(preguntaDto);
            crearPregunta.execute();


            PreguntaEntity pregunta = crearPregunta.getResult();

            PreguntaCatSubcatEntity pcs = PreguntaCatSubcatMapper.mapDtoToEntity(preguntaCatSubcatDto);
            pcs.setFkPregunta(new PreguntaEntity(crearPregunta.getResult().get_id()));

            daoPreguntaCategoriaSubcategoria.insert(pcs);

            /* Selection */
            if (pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3) {
                List<OpcionDto> opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones();

                for (OpcionDto opcion : opcionDto) {
                    ComandoCrearOpcion crearOpcion = new ComandoCrearOpcion(opcion);
                    crearOpcion.execute();

                    PosibleRespuestaDto pr = FabricaDto.crearPosibleRespuestaDto();
                    pr.setFkOpcion(OpcionMapper.mapEntityToDto(crearOpcion.getResult()));
                    pr.setFkPregunta(PreguntaMapper.mapEntityToDto(pregunta));
                    ComandoCrearPosibleRespuesta crearPR = new ComandoCrearPosibleRespuesta(pr);
                    crearPR.execute();
                }
            } else if (pregunta.getFkTipoPregunta().get_id() == 4) {
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero"));
                opciones.add(new OpcionEntity("Falso"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);

                    PosibleRespuestaEntity pr = FabricaEntity.crearPosibleRespuestaEntity();
                    pr.setFkOpcion(opcion);
                    pr.setFkPregunta(pregunta);
                    daoPosibleRespuesta.insert(pr);
                }

            } else if (pregunta.getFkTipoPregunta().get_id() == 5) {
                OpcionDto opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones().get(0);
                ComandoCrearOpcion crearOpcion = new ComandoCrearOpcion(opcionDto);
                crearOpcion.execute();

                PosibleRespuestaDto pr = FabricaDto.crearPosibleRespuestaDto();
                pr.setFkOpcion(OpcionMapper.mapEntityToDto(crearOpcion.getResult()));
                pr.setFkPregunta(PreguntaMapper.mapEntityToDto(pregunta));
                ComandoCrearPosibleRespuesta crearPR = new ComandoCrearPosibleRespuesta(pr);
                crearPR.execute();
            }

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(pcs);
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

//    @POST
//    @Path("/clone/{id}")
//    public PreguntaCatSubcatEntity cloneQuestion(PreguntaCatSubcatDto preguntaCatSubcatDto){
//        DaoPregunta daoPregunta = new DaoPregunta();
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        DaoOpcion daoOpcion = new DaoOpcion();
//        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
//        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
//        DaoCategoria daoCategoria = new DaoCategoria();
//        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
//
//        PreguntaEntity pregunta = new PreguntaEntity();
//
//        TipoPreguntaEntity tp = daoTipoPregunta.find(preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id(),
//                TipoPreguntaEntity.class);
//
//        pregunta.setFkTipoPregunta(tp);
//        pregunta.setStatus(2);
//        pregunta.setPregunta(preguntaCatSubcatDto.getFkPregunta().getPregunta());
//        daoPregunta.insert(pregunta);
//
//        PreguntaCatSubcatEntity pcs = new PreguntaCatSubcatEntity();
//
//        CategoriaEntity categoria = daoCategoria.find(preguntaCatSubcatDto.getFkCategoria().get_id(),
//                CategoriaEntity.class);
//
//        pcs.setFkCategoria(categoria);
//
//        if (preguntaCatSubcatDto.getFkSubcategoria() != null){
//            SubcategoriaEntity subcategoria = daoSubcategoria.find(preguntaCatSubcatDto.getFkSubcategoria().get_id(),
//                    SubcategoriaEntity.class);
//            pcs.setFkSubcategoria(subcategoria);
//        }
//        else {
//            pcs.setFkSubcategoria(null);
//        }
//
//        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
//        daoPreguntaCategoriaSubcategoria.insert(pcs);
//
//        /* Selection */
//        if (pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3){
//            List<OpcionEntity> opciones = daoOpcion.getAllOptionsByQuestion
//                    (preguntaCatSubcatDto.getFkPregunta().get_id());
//
//            for (OpcionEntity opcion: opciones) {
//                OpcionEntity op = new OpcionEntity();
//                op.setValor(opcion.getValor());
//                daoOpcion.insert(op);
//
//                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                pr.setFkOpcion(op);
//                pr.setFkPregunta(pregunta);
//                daoPosibleRespuesta.insert(pr);
//            }
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 4){
//            List<OpcionEntity> opciones = new ArrayList<>();
//            opciones.add(new OpcionEntity("Verdadero"));
//            opciones.add(new OpcionEntity("Falso"));
//
//            for (OpcionEntity opcion: opciones) {
//                daoOpcion.insert(opcion);
//
//                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                pr.setFkOpcion(opcion);
//                pr.setFkPregunta(pregunta);
//                daoPosibleRespuesta.insert(pr);
//            }
//
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 5){
//            OpcionEntity opcion = daoOpcion.getAllOptionsByQuestion
//                    (preguntaCatSubcatDto.getFkPregunta().get_id()).get(0);
//
//            OpcionEntity newOpcion = new OpcionEntity();
//            opcion.setRangoInicial(opcion.getRangoInicial());
//            opcion.setRangoFinal(opcion.getRangoFinal());
//            daoOpcion.insert(opcion);
//
//            PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//            pr.setFkOpcion(opcion);
//            pr.setFkPregunta(pregunta);
//            daoPosibleRespuesta.insert(pr);
//        }
//
//        return pcs;
//    }

    @POST
    @Path("/clone/{id}")
    public RespuestaDto<PreguntaCatSubcatEntity> cloneQuestion(PreguntaCatSubcatDto preguntaCatSubcatDto){
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();

        RespuestaDto<PreguntaCatSubcatEntity> respuesta = new RespuestaDto<>();

        PreguntaDto preguntaDto = preguntaCatSubcatDto.getFkPregunta();
        preguntaDto.setStatus(2);

        try {
            ComandoCrearPregunta crearPregunta = new ComandoCrearPregunta(preguntaDto);
            crearPregunta.execute();

            PreguntaEntity pregunta = crearPregunta.getResult();

            PreguntaCatSubcatEntity pcs = PreguntaCatSubcatMapper.mapDtoToEntity(preguntaCatSubcatDto);
            pcs.setFkPregunta(pregunta);

            daoPreguntaCategoriaSubcategoria.insert(pcs);

            /* Selection */
            if (pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3) {
                List<OpcionEntity> opciones = daoOpcion.getAllOptionsByQuestion
                        (preguntaCatSubcatDto.getFkPregunta().get_id());

                for (OpcionEntity opcion : opciones) {
                    OpcionEntity op = FabricaEntity.crearOpcionEntity();
                    op.setValor(opcion.getValor());
                    daoOpcion.insert(op);

                    PosibleRespuestaEntity pr = FabricaEntity.crearPosibleRespuestaEntity();
                    pr.setFkOpcion(op);
                    pr.setFkPregunta(pregunta);
                    daoPosibleRespuesta.insert(pr);
                }
            } else if (pregunta.getFkTipoPregunta().get_id() == 4) {
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero"));
                opciones.add(new OpcionEntity("Falso"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);

                    PosibleRespuestaEntity pr = FabricaEntity.crearPosibleRespuestaEntity();
                    pr.setFkOpcion(opcion);
                    pr.setFkPregunta(pregunta);
                    daoPosibleRespuesta.insert(pr);
                }

            } else if (pregunta.getFkTipoPregunta().get_id() == 5) {
                OpcionEntity opcion = daoOpcion.getAllOptionsByQuestion
                        (preguntaCatSubcatDto.getFkPregunta().get_id()).get(0);

                opcion.setRangoInicial(opcion.getRangoInicial());
                opcion.setRangoFinal(opcion.getRangoFinal());
                daoOpcion.insert(opcion);

                PosibleRespuestaEntity pr = FabricaEntity.crearPosibleRespuestaEntity();
                pr.setFkOpcion(opcion);
                pr.setFkPregunta(pregunta);
                daoPosibleRespuesta.insert(pr);
            }
            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setObjeto(pcs);
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

//    @PUT
//    @Path("/update/{id}")
//    public Response updateQuestion(@PathParam("id") long id, PreguntaCatSubcatDto preguntaCatSubcatDto){
//        DaoPregunta daoPregunta = new DaoPregunta();
//        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
//        DaoOpcion daoOpcion = new DaoOpcion();
//        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
//        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
//        DaoCategoria daoCategoria = new DaoCategoria();
//        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
//
//        PreguntaCatSubcatEntity pcs = null;
//        PreguntaEntity pregunta = null;
//        TipoPreguntaEntity tp = null;
//        CategoriaEntity categoria = null;
//        SubcategoriaEntity subcategoria = null;
//
//        Boolean modified = false;
//
//        try {
//            pcs = daoPreguntaCategoriaSubcategoria.find(id, PreguntaCatSubcatEntity.class);
//            pregunta = daoPregunta.find(preguntaCatSubcatDto.getFkPregunta().get_id(),
//                    PreguntaEntity.class);
//            tp = daoTipoPregunta.find(preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id(),
//                    TipoPreguntaEntity.class);
//            categoria = daoCategoria.find(preguntaCatSubcatDto.getFkCategoria().get_id(),
//                    CategoriaEntity.class);
//        }
//        catch (NullPointerException e){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        /* IF SELECTION QUESTION IS CHANGING TO ANOTHER TYPE DELETE ALL OPTIONS
//        *       Considerations:
//        *           1. Simple selection -> Multiple selection or viceversa = NO ACTION
//        *           2. Simple text -> Any selection = NO ACTION, no need to delete anything
//        *           3. Simple selection/Multiple selection -> Anything else or viceversa = DELETE ALL OPTIONS
//        *           4. Range -> Anything else or viceversa = DELETE ALL OPTIONS
//        *           5. Any selection -> Simple text = DELETE ALL OPTIONS
//        *           6. V/F -> Anything else or viceversa = DELETE ALL OPTIONS
//        * */
//        if (pregunta.getFkTipoPregunta().get_id() != preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id()){
//            modified = true;
//
//            if (pregunta.getFkTipoPregunta().get_id() == 2 &&
//                    preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id() != 3){
//                this.deleteAllOptions(pregunta.get_id());
//            }
//
//            else if (pregunta.getFkTipoPregunta().get_id() == 3 &&
//                    preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id() != 2){
//                this.deleteAllOptions(pregunta.get_id());
//            }
//
//            else if (pregunta.getFkTipoPregunta().get_id() == 4 || pregunta.getFkTipoPregunta().get_id() == 5){
//                this.deleteAllOptions(pregunta.get_id());
//            }
//        }
//
//        pregunta.setFkTipoPregunta(tp);
//        pregunta.setStatus(1);
//        pregunta.setPregunta(preguntaCatSubcatDto.getFkPregunta().getPregunta());
//        daoPregunta.update(pregunta);
//
//
//        pcs.setFkCategoria(categoria);
//
//        if (preguntaCatSubcatDto.getFkSubcategoria() != null){
//            try{
//                subcategoria = daoSubcategoria.find(preguntaCatSubcatDto.getFkSubcategoria().get_id(),
//                        SubcategoriaEntity.class);
//                pcs.setFkSubcategoria(subcategoria);
//            }
//            catch (NullPointerException e){
//                return Response.status(Response.Status.NOT_FOUND).build();
//            }
//
//        }
//        else {
//            pcs.setFkPregunta(null);
//        }
//
//        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
//        daoPreguntaCategoriaSubcategoria.update(pcs);
//
//        /* Selection */
//
//        if ((pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3 ||
//            pregunta.getFkTipoPregunta().get_id() == 4)
//                && preguntaCatSubcatDto.getFkPregunta().getListOpciones().size() > 0 && !modified){
//
//            List<OpcionDto> opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones();
//
//            for (OpcionDto opcion: opcionDto) {
//                if (opcion.get_id() != 0){
//                    OpcionEntity op = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
//                    op.setValor(opcion.getValor());
//                    daoOpcion.update(op);
//                }
//
//                else {
//                    OpcionEntity op = new OpcionEntity();
//                    op.setValor(opcion.getValor());
//                    daoOpcion.insert(op);
//
//                    PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                    pr.setFkOpcion(op);
//                    pr.setFkPregunta(pregunta);
//                    daoPosibleRespuesta.insert(pr);
//                }
//            }
//        }
//        else if ((pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3)
//                && preguntaCatSubcatDto.getFkPregunta().getListOpciones().size() > 0 && modified){
//
//            List<OpcionDto> opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones();
//
//            for (OpcionDto opcion: opcionDto) {
//                if (opcion.get_id() != 0){
//                    OpcionEntity op = new OpcionEntity();
//                    op.setValor(opcion.getValor());
//                    daoOpcion.insert(op);
//
//                    PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                    pr.setFkOpcion(op);
//                    pr.setFkPregunta(pregunta);
//                    daoPosibleRespuesta.insert(pr);
//                }
//            }
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 4 && modified){
//            List<OpcionEntity> opciones = new ArrayList<>();
//            opciones.add(new OpcionEntity("Verdadero"));
//            opciones.add(new OpcionEntity("Falso"));
//
//            for (OpcionEntity opcion: opciones) {
//                daoOpcion.insert(opcion);
//                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
//                pr.setFkOpcion(opcion);
//                pr.setFkPregunta(pregunta);
//                daoPosibleRespuesta.insert(pr);
//            }
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 5 && !modified){
//            OpcionDto opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones().get(0);
//            OpcionEntity opcion = daoOpcion.find(opcionDto.get_id(), OpcionEntity.class);
//            opcion.setRangoInicial(opcionDto.getRangoInicial());
//            opcion.setRangoFinal(opcionDto.getRangoFinal());
//            daoOpcion.update(opcion);
//        }
//        else if (pregunta.getFkTipoPregunta().get_id() == 5 && modified){
//            OpcionDto opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones().get(0);
//            OpcionEntity opcion = new OpcionEntity();
//            opcion.setRangoInicial(opcionDto.getRangoInicial());
//            opcion.setRangoFinal(opcionDto.getRangoFinal());
//            daoOpcion.insert(opcion);
//        }
//
//        return Response.ok().entity(pcs).build();
//    }

    @PUT
    @Path("/update/{id}")
    public RespuestaDto<PreguntaCatSubcatEntity> updateQuestion(@PathParam("id") long id, PreguntaCatSubcatDto preguntaCatSubcatDto){
        DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = FabricaDao.crearDaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();
        DaoTipoPregunta daoTipoPregunta = FabricaDao.crearDaoTipoPregunta();
        DaoCategoria daoCategoria = FabricaDao.crearDaoCategoria();
        DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();

        RespuestaDto<PreguntaCatSubcatEntity> respuesta = new RespuestaDto<>();

        PreguntaCatSubcatEntity pcs = null;
        PreguntaEntity pregunta = null;
        TipoPreguntaEntity tp = null;
        CategoriaEntity categoria = null;
        SubcategoriaEntity subcategoria = null;

        Boolean modified = false;

        try {
            try {
                pcs = daoPreguntaCategoriaSubcategoria.find(id, PreguntaCatSubcatEntity.class);
                pregunta = daoPregunta.find(preguntaCatSubcatDto.getFkPregunta().get_id(),
                        PreguntaEntity.class);
                tp = daoTipoPregunta.find(preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id(),
                        TipoPreguntaEntity.class);
                categoria = daoCategoria.find(preguntaCatSubcatDto.getFkCategoria().get_id(),
                        CategoriaEntity.class);
            } catch (NullPointerException e) {
                e.printStackTrace();
                respuesta.setCodigo(-1);
                respuesta.setEstado( "ERROR" );
                respuesta.setMensaje( e.getMessage() );
                respuesta.setMensajesoporte( e.getLocalizedMessage() );

                return  respuesta;
            }

            /* IF SELECTION QUESTION IS CHANGING TO ANOTHER TYPE DELETE ALL OPTIONS
             *       Considerations:
             *           1. Simple selection -> Multiple selection or viceversa = NO ACTION
             *           2. Simple text -> Any selection = NO ACTION, no need to delete anything
             *           3. Simple selection/Multiple selection -> Anything else or viceversa = DELETE ALL OPTIONS
             *           4. Range -> Anything else or viceversa = DELETE ALL OPTIONS
             *           5. Any selection -> Simple text = DELETE ALL OPTIONS
             *           6. V/F -> Anything else or viceversa = DELETE ALL OPTIONS
             * */
            if (pregunta.getFkTipoPregunta().get_id() != preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id()) {
                modified = true;

                if (pregunta.getFkTipoPregunta().get_id() == 2 &&
                        preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id() != 3) {
                    this.deleteAllOptions(pregunta.get_id());
                } else if (pregunta.getFkTipoPregunta().get_id() == 3 &&
                        preguntaCatSubcatDto.getFkPregunta().getFkTipoPregunta().get_id() != 2) {
                    this.deleteAllOptions(pregunta.get_id());
                } else if (pregunta.getFkTipoPregunta().get_id() == 4 || pregunta.getFkTipoPregunta().get_id() == 5) {
                    this.deleteAllOptions(pregunta.get_id());
                }
            }

            pregunta.setFkTipoPregunta(tp);
            pregunta.setStatus(1);
            pregunta.setPregunta(preguntaCatSubcatDto.getFkPregunta().getPregunta());
            daoPregunta.update(pregunta);


            pcs.setFkCategoria(categoria);

            if (preguntaCatSubcatDto.getFkSubcategoria() != null) {
                try {
                    subcategoria = daoSubcategoria.find(preguntaCatSubcatDto.getFkSubcategoria().get_id(),
                            SubcategoriaEntity.class);
                    pcs.setFkSubcategoria(subcategoria);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    respuesta.setCodigo(-1);
                    respuesta.setEstado( "ERROR" );
                    respuesta.setMensaje( e.getMessage() );
                    respuesta.setMensajesoporte( e.getLocalizedMessage() );

                    return respuesta;
                }

            } else {
                pcs.setFkPregunta(null);
            }

            pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
            daoPreguntaCategoriaSubcategoria.update(pcs);

            /* Selection */

            if ((pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3 ||
                    pregunta.getFkTipoPregunta().get_id() == 4)
                    && preguntaCatSubcatDto.getFkPregunta().getListOpciones().size() > 0 && !modified) {

                List<OpcionDto> opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones();

                for (OpcionDto opcion : opcionDto) {
                    if (opcion.get_id() != 0) {
                        ComandoEditarOpcion editarOpcion = new ComandoEditarOpcion(opcion.get_id(), opcion);
                        editarOpcion.execute();
                    } else {
                        ComandoCrearOpcion crearOpcion = new ComandoCrearOpcion(opcion);
                        crearOpcion.execute();

                        PosibleRespuestaDto pr = FabricaDto.crearPosibleRespuestaDto();
                        pr.setFkOpcion(OpcionMapper.mapEntityToDto(crearOpcion.getResult()));
                        pr.setFkPregunta(PreguntaMapper.mapEntityToDto(pregunta));
                        ComandoCrearPosibleRespuesta crearPR = new ComandoCrearPosibleRespuesta(pr);
                        crearPR.execute();
                    }
                }
            } else if ((pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3)
                    && preguntaCatSubcatDto.getFkPregunta().getListOpciones().size() > 0 && modified) {

                List<OpcionDto> opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones();

                for (OpcionDto opcion : opcionDto) {
                    if (opcion.get_id() != 0) {
                        ComandoCrearOpcion crearOpcion = new ComandoCrearOpcion(opcion);
                        crearOpcion.execute();

                        PosibleRespuestaDto pr = FabricaDto.crearPosibleRespuestaDto();
                        pr.setFkOpcion(OpcionMapper.mapEntityToDto(crearOpcion.getResult()));
                        pr.setFkPregunta(PreguntaMapper.mapEntityToDto(pregunta));
                        ComandoCrearPosibleRespuesta crearPR = new ComandoCrearPosibleRespuesta(pr);
                        crearPR.execute();
                    }
                }
            } else if (pregunta.getFkTipoPregunta().get_id() == 4 && modified) {
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero"));
                opciones.add(new OpcionEntity("Falso"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);
                    PosibleRespuestaEntity pr = FabricaEntity.crearPosibleRespuestaEntity();
                    pr.setFkOpcion(opcion);
                    pr.setFkPregunta(pregunta);
                    daoPosibleRespuesta.insert(pr);
                }
            } else if (pregunta.getFkTipoPregunta().get_id() == 5 && !modified) {
                OpcionDto opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones().get(0);
                ComandoEditarOpcion editarOpcion = new ComandoEditarOpcion(opcionDto.get_id(), opcionDto);
                editarOpcion.execute();
            } else if (pregunta.getFkTipoPregunta().get_id() == 5 && modified) {
                OpcionDto opcionDto = preguntaCatSubcatDto.getFkPregunta().getListOpciones().get(0);
                ComandoCrearOpcion crearOpcion = new ComandoCrearOpcion(opcionDto);
                crearOpcion.execute();
            }

            respuesta.setCodigo(0);
            respuesta.setEstado( "OK" );
            respuesta.setObjeto( pcs );
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

    @PUT
    @Path("/delete/{id}")
    public RespuestaDto<Boolean> deleteQuestionLogically(@PathParam("id") long id) {
        DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();
        PreguntaEntity pregunta = null;

        RespuestaDto<Boolean> respuesta = new RespuestaDto<>();

        try {
            pregunta = daoPregunta.find(id, PreguntaEntity.class);
            PreguntaDto preguntaDto = PreguntaMapper.mapEntityToDto(pregunta);
            preguntaDto.setStatus(0);

            ComandoEditarPregunta editarPregunta = new ComandoEditarPregunta(id, preguntaDto);
            editarPregunta.execute();

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje(e.getMessage());
            respuesta.setMensajesoporte(e.getLocalizedMessage());
        }

        return respuesta;
    }
}
