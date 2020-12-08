package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

        JPQL = "SELECT p FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna " +
                "WHERE f.fkEstudio = :estudio AND pna.fkPersona = p " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR f.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        return q.getResultList();
    }
}
