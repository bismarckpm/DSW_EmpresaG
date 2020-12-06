package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoOpcion extends Dao<OpcionEntity> {
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;

    public DaoOpcion(){
        super(_handler);
    }

    public List<OpcionEntity> getAllOptionsByQuestion(long idPregunta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT pr.fkOpcion FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta._id = :idPregunta";
        q = em.createQuery(JPQL);
        q.setParameter("idPregunta", idPregunta);
        return q.getResultList();
    }
}
