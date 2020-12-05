package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoPreguntaCategoriaSubcategoria extends Dao<PreguntaCatSubcatEntity> {
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;

    public DaoPreguntaCategoriaSubcategoria(){
        super(_handler);
    }

    public List<PreguntaCatSubcatEntity> getAllActiveQuestions(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs WHERE pcs.fkPregunta.status = 1";
        return em.createQuery(JPQL).getResultList();
    }

    public List<PreguntaCatSubcatEntity> getAllActiveQuestionsByCategory(long idCategoria){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs WHERE pcs.fkPregunta.status = 1" +
                " AND pcs.fkCategoria._id = :idCategoria";
        q = em.createQuery(JPQL);
        q.setParameter("idCategoria", idCategoria);
        return q.getResultList();
    }
}
