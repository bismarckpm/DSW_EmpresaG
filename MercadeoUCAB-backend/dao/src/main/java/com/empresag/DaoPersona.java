package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoPersona extends Dao<PersonaEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPersona( ) {
        super(_handler);
    }

    String JPQL = null;
    Query q = null;

    public List<PersonaEntity> findSons(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        JPQL = "SELECT p FROM PersonaEntity p WHERE p.fkPersona._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", id);


        return q.getResultList();
    }

    public PersonaEntity findPersonByUser(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity currentUser = daoUsuario.find(id, UsuarioEntity.class);
        JPQL = "SELECT p.fk_Persona FROM UsuarioEntity p WHERE p = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", currentUser);

        return (PersonaEntity) q.getSingleResult();
    }
}
