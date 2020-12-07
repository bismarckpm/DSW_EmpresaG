package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoUsuario extends Dao<UsuarioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    String JPQL = null;
    Query q = null;

    public DaoUsuario( ) {
        super(_handler);
    }

    public List<UsuarioEntity> findUsuarioLogin(String email, String password){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT u FROM UsuarioEntity u WHERE u.password = :password AND u.email = :email";
        q = em.createQuery(JPQL);
        q.setParameter("password", password);
        q.setParameter("email", email);

        return q.getResultList();
    }
}
