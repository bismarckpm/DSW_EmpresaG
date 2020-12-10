package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoPersonaOcupacion extends Dao<PersonaOcupacionEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaOcupacion( ) {
        super(_handler);
    }
    String JPQL = null;
    Query q = null;

    public List<OcupacionEntity> findOcupacion(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT o FROM PersonaOcupacionEntity po, OcupacionEntity o " +
                "WHERE po.fkPersona._id = :id AND o._id = po.fkOcupacion._id ";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }

    public List<PersonaOcupacionEntity> findOcupacionPersona(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT peo FROM PersonaOcupacionEntity peo " +
                "WHERE peo.fkPersona._id = :id  ";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }
}
