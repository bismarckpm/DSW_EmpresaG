package com.empresag;

import javax.persistence.*;
import java.util.List;

public class DaoCategoriaSubcategoria extends Dao<CategoriaSubcategoriaEntity> {
    static DaoHandler _handler = new DaoHandler();

    String JPQL = null;
    Query q = null;

    public DaoCategoriaSubcategoria( ) {
        super(_handler);
    }

    public List<CategoriaSubcategoriaEntity> findByCategoryID(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT cs FROM CategoriaSubcategoriaEntity cs WHERE cs.fkCategoria._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);

        return q.getResultList();
    }
}
