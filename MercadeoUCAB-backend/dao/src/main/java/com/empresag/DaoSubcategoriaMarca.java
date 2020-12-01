package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoSubcategoriaMarca extends Dao<SubcategoriaMarcaEntity> {
    static DaoHandler _handler = new DaoHandler();

    String JPQL = null;
    Query q = null;

    public DaoSubcategoriaMarca( ) {
        super(_handler);
    }

    public List<SubcategoriaMarcaEntity> findBySubcategoryID(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT sm FROM SubcategoriaMarcaEntity sm WHERE sm.fkSubcategoria._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }
}
