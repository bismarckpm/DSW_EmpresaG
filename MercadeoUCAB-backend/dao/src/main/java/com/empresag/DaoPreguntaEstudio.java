package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
                "AND p = pcs.fkPregunta AND p.status = 1 " +
                "AND pe.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        return q.getResultList();
    }
}
