package com.empresag;

import javax.persistence.*;

public class DaoEstudio extends Dao<EstudioEntity> {
    StoredProcedureQuery query = null;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio( ) {
        super(_handler);
    }

    public void cloneStudy(long study_id, long cloned_study_id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        StoredProcedureQuery query = em.createStoredProcedureQuery("CLONE_STUDY");
        query.registerStoredProcedureParameter("study_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cloned_study_id", Integer.class, ParameterMode.IN);
        query.setParameter("study_id", study_id);
        query.setParameter("cloned_study_id", cloned_study_id);
        query.execute();
    }
}
