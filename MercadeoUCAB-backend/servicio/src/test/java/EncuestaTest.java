import com.empresag.DaoEstudio;
import com.empresag.EstudioEntity;
import com.empresag.PersonaEntity;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EncuestaTest {
    String JPQL = null;
    Query q = null;

    @Test
    public void getAvailablePopulation(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);

        JPQL = "SELECT p FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna " +
                "WHERE f.fkEstudio = :estudio AND pna.fkPersona = p " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR f.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        List<PersonaEntity> personas = q.getResultList();
        for (PersonaEntity persona: personas) {
            System.out.println(persona);
        }
    }
}
