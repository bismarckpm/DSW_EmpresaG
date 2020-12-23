package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DaoPreguntaEstudio extends Dao<PreguntaEstudioEntity> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;
    public DaoPreguntaEstudio(){
        super(_handler);
    }

    public List<PreguntaCatSubcatEntity> getStudyQuestions(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs, " +
                "PreguntaEstudioEntity pe, PreguntaEntity p " +
                "WHERE pcs.fkPregunta = pe.fkPregunta " +
                "AND p = pcs.fkPregunta AND (p.status = 1 OR p.status = 2) " +
                "AND pe.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        return q.getResultList();
    }

    public List<PreguntaEstudioDto> getStudyQuestionsWithOptions(long id) throws IndexDatabaseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        JPQL = "SELECT e FROM PreguntaEstudioEntity e, PreguntaEntity p, TipoPreguntaEntity tp " +
                "WHERE e.fkPregunta = p AND tp = p.fkTipoPregunta AND e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        List<PreguntaEstudioEntity> preguntas = q.getResultList();
        List<PreguntaEstudioDto> resultSet = new ArrayList<>();

        for (PreguntaEstudioEntity pregunta: preguntas) {
            JPQL = "SELECT pr FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta.getFkPregunta());
            List<PosibleRespuestaEntity> opciones = q.getResultList();

            PreguntaEstudioDto np = new PreguntaEstudioDto();
            np.setFkPregunta(new PreguntaDto());
            np.getFkPregunta().setStatus(pregunta.getFkPregunta().getStatus());
            np.getFkPregunta().setPregunta(pregunta.getFkPregunta().getPregunta());
            np.getFkPregunta().set_id(pregunta.getFkPregunta().get_id());

            np.getFkPregunta().setFkTipoPregunta(new TipoPreguntaDto());
            np.getFkPregunta().getFkTipoPregunta().set_id(pregunta.getFkPregunta().getFkTipoPregunta().get_id());

            np.getFkPregunta().setListPosibleRespuestas(new ArrayList<PosibleRespuestaDto>());

            for (PosibleRespuestaEntity opcion: opciones) {
                PosibleRespuestaDto op = new PosibleRespuestaDto();
                op.set_id(opcion.get_id());
                op.setFkOpcion(new OpcionDto());
                op.getFkOpcion().set_id(opcion.getFkOpcion().get_id());
                op.getFkOpcion().setValor(opcion.getFkOpcion().getValor());
                op.getFkOpcion().setRangoInicial(opcion.getFkOpcion().getRangoInicial());
                op.getFkOpcion().setRangoFinal(opcion.getFkOpcion().getRangoFinal());
                np.getFkPregunta().getListPosibleRespuestas().add(op);
            }

            resultSet.add(np);
        }
        return resultSet;
    }
}
