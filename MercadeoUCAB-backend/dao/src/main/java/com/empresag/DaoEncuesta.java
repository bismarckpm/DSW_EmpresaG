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
                "AND u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = :estudio AND e.fkPersona = p)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        return q.getResultList();
    }

    public List<FiltroEntity> getAvailableSurveys(long userId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoPersona daoPersona = new DaoPersona();
        PersonaEntity persona = daoPersona.findPersonByUser(userId);

        JPQL = "SELECT f FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, UsuarioEntity u, " +
                "RolEntity r, EstudioEntity ee " +
                "WHERE p = :persona AND f.fkEstudio IS NOT NULL AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) AND " +
                "u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = f.fkEstudio AND e.fkPersona = p) " +
                "AND ee = f.fkEstudio AND ee.estado = 1 " +
                "GROUP BY f.fkEstudio";

        q = em.createQuery(JPQL);
        q.setParameter("persona", persona);

        return q.getResultList();
    }

    public boolean isPersonPartOfAvailablePopulation(long studyId, long userId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        DaoPersona daoPersona = new DaoPersona();
        PersonaEntity persona = daoPersona.findPersonByUser(userId);
        System.out.println("-----USER ID");
        System.out.println(userId);
        System.out.println("-----PERSONA");
        System.out.println(persona);
        List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(studyId);
        boolean found = false;

        for (PersonaEntity p: personas) {
            if (p.get_id() == persona.get_id()){
                found = true;
                break;
            }
        }

        return found;
    }

    public boolean isPersonPartOfAvailablePopulationInterview(long studyId, long personId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(studyId);
        boolean found = false;

        for (PersonaEntity p: personas) {
            if (p.get_id() == personId){
                found = true;
                break;
            }
        }

        return found;
    }
}
