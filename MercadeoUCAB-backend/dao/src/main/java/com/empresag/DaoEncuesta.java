package com.empresag;

import javax.persistence.*;
import java.util.List;

public class DaoEncuesta extends Dao<EncuestaEntity> {

    String JPQL = null;
    Query q = null;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuesta( ) {
        super(_handler);
    }

    public List<PersonaEntity> getAvailablePopulation(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        JPQL = "SELECT DISTINCT p FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, " +
                "UsuarioEntity u, RolEntity r " +
                "WHERE f.fkEstudio = :estudio " +
                "AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) " +
                "AND u.fk_Persona = p AND u.fk_Rol = r AND r._id = 4 " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR f.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = :estudio AND e.fkPersona = p)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        return q.getResultList();
    }

    public List<FiltroEntity> getAvailableSurveys(long personId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoPersona daoPersona = new DaoPersona();
        PersonaEntity persona = daoPersona.find(personId, PersonaEntity.class);

        JPQL = "SELECT f FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, UsuarioEntity u, " +
                "RolEntity r, EstudioEntity ee " +
                "WHERE p = :persona AND f.fkEstudio IS NOT NULL AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) AND " +
                "u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR p.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = f.fkEstudio AND e.fkPersona = p) " +
                "AND ee = f.fkEstudio AND ee.estado = 1 " +
                "GROUP BY f.fkEstudio";

        q = em.createQuery(JPQL);
        q.setParameter("persona", persona);

        return q.getResultList();
    }

    public boolean isPersonPartOfAvailablePopulation(long studyId, long personId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPersona daoPersona = new DaoPersona();
        EstudioEntity estudio = daoEstudio.find(studyId, EstudioEntity.class);
        PersonaEntity persona = daoPersona.find(personId, PersonaEntity.class);

        JPQL = "SELECT DISTINCT p FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, " +
                "UsuarioEntity u, RolEntity r " +
                "WHERE f.fkEstudio = :estudio AND p = :persona " +
                "AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) " +
                "AND u.fk_Persona = p AND u.fk_Rol = r AND r._id = 4 " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR f.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = :estudio AND e.fkPersona = p)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        q.setParameter("persona", persona);

        try {
            q.getSingleResult();
            return true;
        }
        catch (NoResultException e){
            e.printStackTrace();
            return false;
        }
    }
}
