package com.empresag;

import javax.persistence.*;
import java.util.List;

public class DaoUsuario extends Dao<UsuarioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    String JPQL = null;
    Query q = null;

    public DaoUsuario( ) {
        super(_handler);
    }

    public UsuarioEntity findUserByEmail(String email){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT u FROM UsuarioEntity u WHERE u.email = :correo ";
        q = em.createQuery(JPQL);
        q.setParameter("correo", email);

        try {
            return (UsuarioEntity) q.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
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

    public List<UsuarioEntity> emailExist(String correo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT u FROM UsuarioEntity u WHERE u.email = :correo ";
        q = em.createQuery(JPQL);
        q.setParameter("correo", correo);

        return q.getResultList();
    }
}
