package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoFiltro extends Dao<FiltroEntity> {
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;

    public DaoFiltro(){
        super(_handler);
    }

    public List<FiltroEntity> getAllStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio IS NOT NULL";
        return em.createQuery(JPQL).getResultList();
    }

    public List<FiltroEntity> getAllActiveStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e, EstudioEntity ee WHERE e.fkEstudio " +
                "IS NOT NULL AND e.fkEstudio = ee AND ee.estado = 1";
        return em.createQuery(JPQL).getResultList();
    }

    public List<FiltroEntity> getSimilarStudies(long category_id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoCategoria daoCategoria = new DaoCategoria();
        CategoriaEntity categoria = daoCategoria.find(category_id, CategoriaEntity.class);

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio IS NOT NULL AND e.fkCategoria = :categoria";
        q = em.createQuery(JPQL);
        q.setParameter("categoria", categoria);
        return q.getResultList();
    }

    public List<FiltroEntity> getAllRequests(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio IS NULL";
        return em.createQuery(JPQL).getResultList();
    }

    public List<FiltroEntity> getUserRequests(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity usuario = daoUsuario.find(id, UsuarioEntity.class);

        JPQL = "SELECT f FROM FiltroEntity f, UsuarioEntity u, SolicitudEntity s " +
                "WHERE u = s.fkUsuario AND u = :usuario AND f.fkSolicitud = s";

        q = em.createQuery(JPQL);
        q.setParameter("usuario", usuario);
        return q.getResultList();
    }

    public FiltroEntity getUserRequest(long id, long requestId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        UsuarioEntity usuario = daoUsuario.find(id, UsuarioEntity.class);
        SolicitudEntity solicitud = daoSolicitud.find(requestId, SolicitudEntity.class);

        JPQL = "SELECT DISTINCT(f) FROM FiltroEntity f, UsuarioEntity u, SolicitudEntity s " +
                "WHERE u = s.fkUsuario AND u = :usuario AND f.fkSolicitud = s AND s = :solicitud";

        q = em.createQuery(JPQL);
        q.setParameter("usuario", usuario);
        q.setParameter("solicitud", solicitud);
        return (FiltroEntity) q.getSingleResult();
    }


    public FiltroEntity getCurrentStudy(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        return (FiltroEntity) q.getSingleResult();
    }

    public FiltroEntity getCurrentRequest(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoSolicitud daoSolicitud = new DaoSolicitud();

        SolicitudEntity solicitud = daoSolicitud.find(id, SolicitudEntity.class);

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkSolicitud = :solicitud AND e.fkEstudio IS NULL";
        q = em.createQuery(JPQL);
        q.setParameter("solicitud", solicitud);
        return (FiltroEntity) q.getSingleResult();
    }
}
