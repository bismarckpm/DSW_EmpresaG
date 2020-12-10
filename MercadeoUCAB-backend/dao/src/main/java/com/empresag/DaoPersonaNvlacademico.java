package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoPersonaNvlacademico extends Dao<PersonaNvlacademicoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaNvlacademico( ) {
        super(_handler);
    }

    String JPQL = null;
    Query q = null;

    public List<NivelAcademicoEntity> findNivAcademico(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT na FROM PersonaNvlacademicoEntity pna, NivelAcademicoEntity na " +
                "WHERE pna.fkPersona._id = :id AND na._id = pna.fkNivelAcademico._id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }

    public List<PersonaNvlacademicoEntity> findNivAcademicos(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT pna FROM PersonaNvlacademicoEntity pna " +
                "WHERE pna.fkPersona._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }
}
