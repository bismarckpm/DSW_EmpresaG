package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoRol extends Dao<RolEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRol( ) {
        super(_handler);
    }

    String JPQL = null;
    Query q = null;

    public List<RolEntity> getEncuestadoRol(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT r FROM RolEntity r WHERE r.nombre = 'ENCUESTADO' ";
        q = em.createQuery(JPQL);

        return q.getResultList();
    }
}
