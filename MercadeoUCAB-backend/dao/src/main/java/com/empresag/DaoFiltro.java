package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DaoFiltro extends Dao<FiltroEntity> {
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;

    public DaoFiltro(){
        super(_handler);
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
