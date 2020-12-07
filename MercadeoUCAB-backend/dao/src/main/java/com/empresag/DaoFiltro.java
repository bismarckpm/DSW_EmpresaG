package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoFiltro extends Dao<FiltroEntity> {
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;

    public DaoFiltro(){
        super(_handler);
    }

    public List<FiltroEntity> getAllStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio IS NOT NULL";
        return em.createQuery(JPQL).getResultList();
    }

    public List<FiltroEntity> getAllRequests(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio IS NULL";
        return em.createQuery(JPQL).getResultList();
    }

    public FiltroEntity getCurrentStudy(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        return (FiltroEntity) q.getSingleResult();
    }
}
