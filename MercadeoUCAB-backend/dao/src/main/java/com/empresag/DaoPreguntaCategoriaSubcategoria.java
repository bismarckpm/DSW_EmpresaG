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

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs, PreguntaEntity p WHERE " +
                "p = pcs.fkPregunta AND p.status = 1";
        return em.createQuery(JPQL).getResultList();
    }

    public List<PreguntaCatSubcatEntity> getAllActiveQuestionsByCategory(long idCategoria){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoCategoria daoCategoria = new DaoCategoria();
        CategoriaEntity categoria = daoCategoria.find(idCategoria, CategoriaEntity.class);

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs, PreguntaEntity p WHERE " +
                "p = pcs.fkPregunta AND p.status = 1 " +
                "AND pcs.fkCategoria = :idCategoria";
        q = em.createQuery(JPQL);
        q.setParameter("idCategoria", categoria);
        return q.getResultList();
    }
}
