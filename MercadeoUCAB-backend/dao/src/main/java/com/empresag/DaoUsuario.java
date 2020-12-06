package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoUsuario extends Dao<UsuarioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoUsuario( ) {
        super(_handler);
    }

    String JPQL = null;
    Query q = null;

    public List<UsuarioEntity> emailExist(String correo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT u._id FROM UsuarioEntity u WHERE u.email = :correo ";
        q = em.createQuery(JPQL);
        q.setParameter("correo", correo);

        return q.getResultList();
    }
}
