package com.empresag;

import com.empresag.Extras.TmpAvailablePopulation;
import com.empresag.Extras.TmpAvailableSurveys;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEncuesta extends Dao<EncuestaEntity> {

    String JPQL = null;
    Query q = null;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuesta( ) {
        super(_handler);
    }

//    public List<PersonaEntity> getAvailablePopulation(long id){
    public List<TmpAvailablePopulation> getAvailablePopulation(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        JPQL = "SELECT DISTINCT p, " +
                "(SELECT COUNT(DISTINCT e.fkPregunta) FROM EncuestaEntity e WHERE e.fkEstudio = :estudio AND e.fkPersona = p), " +
                "(SELECT COUNT(pe)  FROM PreguntaEstudioEntity pe WHERE pe.fkEstudio = :estudio)  " +
                "FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, " +
                "UsuarioEntity u, RolEntity r " +
                "WHERE f.fkEstudio = :estudio " +
                "AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) " +
                "AND u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) ";
//                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = :estudio AND e.fkPersona = p)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        List<Object[]> tmp = q.getResultList();

        List<TmpAvailablePopulation> response = new ArrayList<>();

        for (Object[] o :
                tmp) {
            TmpAvailablePopulation tmpAvailablePopulation = new TmpAvailablePopulation((PersonaEntity)o[0], (Long) o[1], (Long) o[2] );
            System.out.println("RESPUESTICA: " +tmpAvailablePopulation.toString());
            response.add(tmpAvailablePopulation);
        }

//        return q.getResultList();
        return response;
    }

//    public List<FiltroEntity> getAvailableSurveys(long userId){
    public List<TmpAvailableSurveys> getAvailableSurveys(long userId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoPersona daoPersona = new DaoPersona();
        PersonaEntity persona = daoPersona.findPersonByUser(userId);

        JPQL = "SELECT f, " +
                "(SELECT COUNT(DISTINCT e.fkPregunta) FROM EncuestaEntity e WHERE e.fkEstudio = f.fkEstudio AND e.fkPersona = :persona), " +
                "(SELECT COUNT(pe)  FROM PreguntaEstudioEntity pe WHERE pe.fkEstudio = f.fkEstudio)  " +
                "FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, UsuarioEntity u, " +
                "RolEntity r, EstudioEntity ee " +
                "WHERE p = :persona AND f.fkEstudio IS NOT NULL AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) AND " +
                "u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
//                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = f.fkEstudio AND e.fkPersona = p) " +
                "AND ee = f.fkEstudio AND ee.estado = 1 " +
                "GROUP BY f.fkEstudio";

        q = em.createQuery(JPQL);
        q.setParameter("persona", persona);

        List<Object[]> tmp = q.getResultList();

        List<TmpAvailableSurveys> response = new ArrayList<>();

        for (Object[] o :
                tmp) {
            TmpAvailableSurveys tmpAvailableSurveys = new TmpAvailableSurveys((FiltroEntity)o[0], (Long) o[1], (Long) o[2] );
            System.out.println("RESPUESTICA: " + tmpAvailableSurveys.toString());
            response.add(tmpAvailableSurveys);
        }

//        return q.getResultList();
        return response;
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
//        List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(studyId);
        List<TmpAvailablePopulation> personas = daoEncuesta.getAvailablePopulation(studyId);
        boolean found = false;

//        for (PersonaEntity p: personas) {
        for (TmpAvailablePopulation p: personas) {
            if (p.getPersona().get_id() == persona.get_id()){
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
//        List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(studyId);
        List<TmpAvailablePopulation> personas = daoEncuesta.getAvailablePopulation(studyId);
        boolean found = false;

        for (TmpAvailablePopulation p: personas) {
            if (p.getPersona().get_id() == personId){
                found = true;
                break;
            }
        }

        return found;
    }

    public List<EncuestaEntity> getOldAnswers (long studyId, long personId){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM EncuestaEntity e WHERE e.fkEstudio._id = :estudio AND e.fkPersona._id = :persona ";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", studyId);
        q.setParameter("persona", personId);

        return q.getResultList();
    }

    public List<EncuestaEntity> getActualQuestionAnswer (long studyId, long personId, long preguntaId){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM EncuestaEntity e WHERE e.fkEstudio._id = :estudio " +
                "AND e.fkPersona._id = :persona AND e.fkPregunta._id = :pregunta ";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", studyId);
        q.setParameter("persona", personId);
        q.setParameter("pregunta", preguntaId);

        return q.getResultList();
    }
}
