package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoLugar extends Dao<LugarEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    String JPQL = null;
    Query q = null;

    public DaoLugar( ) {
        super(_handler);
    }

    public List<LugarEntity> findTop(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        if (id == 0) {
            JPQL = "SELECT l FROM LugarEntity l WHERE l.fkLugar is null";
            q = em.createQuery(JPQL);
        }
        else {
            JPQL = "SELECT l FROM LugarEntity l WHERE l.fkLugar._id = :id";
            q = em.createQuery(JPQL);
            q.setParameter("id", id);
        }

        return q.getResultList();
    }
}
