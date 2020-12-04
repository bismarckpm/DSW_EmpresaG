package com.empresag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoPosibleRespuesta extends Dao<PosibleRespuestaEntity> {
    static DaoHandler _handler = new DaoHandler();
    String JPQL = null;
    Query q = null;

    public DaoPosibleRespuesta(){
        super(_handler);
    }

    public List<PosibleRespuestaEntity> getAllOptions(long idPregunta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();

        JPQL = "SELECT pr FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :id";

        PreguntaCatSubcatEntity pcs = daoPreguntaCategoriaSubcategoria.find(idPregunta, PreguntaCatSubcatEntity.class);

        q = em.createQuery(JPQL);
        q.setParameter("id", pcs.getFkPregunta());
        return q.getResultList();
    }
}
