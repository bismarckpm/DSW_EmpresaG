package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/questions")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class QuestionService {

    private void deleteAllOptions(long id){
        String JPQL = null;
        Query q = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoOpcion daoOpcion = new DaoOpcion();

        List<OpcionEntity> opciones = null;
        JPQL = "SELECT pr.fkOpcion FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);
        opciones = q.getResultList();

        for (OpcionEntity opcion: opciones) {
            OpcionEntity op = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
            daoOpcion.delete(op);
        }
    }

    @GET
    @Path("/all")
    public List<PreguntaCatSubcatEntity> allActiveQuestions(){
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        return daoPreguntaCategoriaSubcategoria.getAllActiveQuestions();
    }

    @POST
    @Path("/add")
    public PreguntaCatSubcatEntity addQuestion(PreguntaCatSubcatDto preguntaCatSubcatDto){
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();
        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

        PreguntaEntity pregunta = new PreguntaEntity();

        TipoPreguntaEntity tp = daoTipoPregunta.find(preguntaCatSubcatDto.getFkPregunta().getFkTipo().get_id(),
                TipoPreguntaEntity.class);

        pregunta.setFkTipoPregunta(tp);
        pregunta.setStatus(preguntaCatSubcatDto.getFkPregunta().getStatus());
        pregunta.setPregunta(preguntaCatSubcatDto.getFkPregunta().getPregunta());
        daoPregunta.insert(pregunta);

        PreguntaCatSubcatEntity pcs = new PreguntaCatSubcatEntity();

        CategoriaEntity categoria = daoCategoria.find(preguntaCatSubcatDto.getFkCategoria().get_id(),
                CategoriaEntity.class);

        pcs.setFkCategoria(categoria);

        if (preguntaCatSubcatDto.getFkSubcategoria() != null){
            SubcategoriaEntity subcategoria = daoSubcategoria.find(preguntaCatSubcatDto.getFkSubcategoria().get_id(),
                    SubcategoriaEntity.class);
            pcs.setFkSubcategoria(subcategoria);
        }
        else {
            pcs.setFkPregunta(null);
        }

        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
        daoPreguntaCategoriaSubcategoria.insert(pcs);

        /* Selection */
        if (pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3){
            List<OpcionDto> opcionDto = preguntaCatSubcatDto.getListOpciones();

            for (OpcionDto opcion: opcionDto) {
                OpcionEntity op = new OpcionEntity();
                op.setValor(opcion.getValor());
                daoOpcion.insert(op);

                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
                pr.setFkOpcion(op);
                pr.setFkPregunta(pregunta);
                daoPosibleRespuesta.insert(pr);
            }
        }
        else if (pregunta.getFkTipoPregunta().get_id() == 4){
            List<OpcionEntity> opciones = new ArrayList<>();
            opciones.add(new OpcionEntity("Verdadero"));
            opciones.add(new OpcionEntity("Falso"));

            for (OpcionEntity opcion: opciones) {
                daoOpcion.insert(opcion);

                PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
                pr.setFkOpcion(opcion);
                pr.setFkPregunta(pregunta);
                daoPosibleRespuesta.insert(pr);
            }

        }
        else if (pregunta.getFkTipoPregunta().get_id() == 5){
            OpcionDto opcionDto = preguntaCatSubcatDto.getListOpciones().get(0);
            OpcionEntity opcion = new OpcionEntity();
            opcion.setRangoInicial(opcionDto.getRangoInicial());
            opcion.setRangoFinal(opcionDto.getRangoFinal());
            daoOpcion.insert(opcion);

            PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
            pr.setFkOpcion(opcion);
            pr.setFkPregunta(pregunta);
            daoPosibleRespuesta.insert(pr);
        }

        return pcs;
    }

    @PUT
    @Path("/update/{id}")
    public Response updateQuestion(@PathParam("id") long id, PreguntaCatSubcatDto preguntaCatSubcatDto){
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();
        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

        PreguntaCatSubcatEntity pcs = null;
        PreguntaEntity pregunta = null;
        TipoPreguntaEntity tp = null;
        CategoriaEntity categoria = null;
        SubcategoriaEntity subcategoria = null;

        try {
            pcs = daoPreguntaCategoriaSubcategoria.find(id, PreguntaCatSubcatEntity.class);
            pregunta = daoPregunta.find(preguntaCatSubcatDto.getFkPregunta().get_id(),
                    PreguntaEntity.class);
            tp = daoTipoPregunta.find(preguntaCatSubcatDto.getFkPregunta().getFkTipo().get_id(),
                    TipoPreguntaEntity.class);
            categoria = daoCategoria.find(preguntaCatSubcatDto.getFkCategoria().get_id(),
                    CategoriaEntity.class);
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
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
        if (pregunta.getFkTipoPregunta().get_id() != preguntaCatSubcatDto.getFkPregunta().getFkTipo().get_id()){
            if (pregunta.getFkTipoPregunta().get_id() == 2 &&
                    preguntaCatSubcatDto.getFkPregunta().getFkTipo().get_id() != 3){
                this.deleteAllOptions(pregunta.get_id());
            }

            else if (pregunta.getFkTipoPregunta().get_id() == 3 &&
                    preguntaCatSubcatDto.getFkPregunta().getFkTipo().get_id() != 2){
                this.deleteAllOptions(pregunta.get_id());
            }

            else if (pregunta.getFkTipoPregunta().get_id() == 4 || pregunta.getFkTipoPregunta().get_id() == 5){
                this.deleteAllOptions(pregunta.get_id());
            }
        }

        pregunta.setFkTipoPregunta(tp);
        pregunta.setStatus(preguntaCatSubcatDto.getFkPregunta().getStatus());
        pregunta.setPregunta(preguntaCatSubcatDto.getFkPregunta().getPregunta());
        daoPregunta.update(pregunta);


        pcs.setFkCategoria(categoria);

        if (preguntaCatSubcatDto.getFkSubcategoria() != null){
            try{
                subcategoria = daoSubcategoria.find(preguntaCatSubcatDto.getFkSubcategoria().get_id(),
                        SubcategoriaEntity.class);
            }
            catch (NullPointerException e){
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            pcs.setFkSubcategoria(subcategoria);
        }
        else {
            pcs.setFkPregunta(null);
        }

        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
        daoPreguntaCategoriaSubcategoria.update(pcs);

        /* Selection */
        if (pregunta.getFkTipoPregunta().get_id() == 2 || pregunta.getFkTipoPregunta().get_id() == 3 ||
            pregunta.getFkTipoPregunta().get_id() == 4){
            List<OpcionDto> opcionDto = preguntaCatSubcatDto.getListOpciones();

            for (OpcionDto opcion: opcionDto) {
                OpcionEntity op = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
                op.setValor(opcion.getValor());
                daoOpcion.update(op);
            }
        }
        else if (pregunta.getFkTipoPregunta().get_id() == 5){
            OpcionDto opcionDto = preguntaCatSubcatDto.getListOpciones().get(0);
            OpcionEntity opcion = daoOpcion.find(opcionDto.get_id(), OpcionEntity.class);
            opcion.setRangoInicial(opcionDto.getRangoInicial());
            opcion.setRangoFinal(opcionDto.getRangoFinal());
            daoOpcion.update(opcion);
        }

        return Response.ok().entity(pcs).build();
    }

    @PUT
    @Path("/delete/{id}")
    public Response deleteQuestionLogically(@PathParam("id") long id){
        DaoPregunta daoPregunta = new DaoPregunta();
        PreguntaEntity pregunta = null;
        try {
            pregunta = daoPregunta.find(id, PreguntaEntity.class);
            pregunta.setStatus(0);
            daoPregunta.update(pregunta);
        }
        catch (NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return  Response.ok().entity(pregunta).build();
    }
}
