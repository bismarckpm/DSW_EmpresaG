package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoTelefono extends Dao<TelefonoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTelefono( ) {
        super(_handler);
    }

    String JPQL = null;
    Query q = null;

    public List<TelefonoEntity> findTelefono(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT t FROM TelefonoEntity t " +
                "WHERE t.fkPersona._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }
}
