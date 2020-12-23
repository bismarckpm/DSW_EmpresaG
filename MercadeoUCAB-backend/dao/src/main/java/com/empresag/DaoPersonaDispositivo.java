package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoPersonaDispositivo extends Dao<PersonaDispositivoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersonaDispositivo( ) {
        super(_handler);
    }



    String JPQL = null;
    Query q = null;

    public List<PersonaDispositivoEntity> findPersonaDispositivos(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT pd FROM PersonaDispositivoEntity pd WHERE pd.fkPersona._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);


        return q.getResultList();
    }
}
