package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoDispositivo extends Dao<DispositivoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoDispositivo( ) {
        super(_handler);
    }

    String JPQL = null;
    Query q = null;

    public List<DispositivoEntity> findDispositivos(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT d FROM DispositivoEntity d, PersonaDispositivoEntity pd " +
                "WHERE pd.fkPersona._id = :id AND d._id = pd.fkDispositivo._id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);


        return q.getResultList();
    }


}
