import com.empresag.FiltroEntity;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EstudiosTest {
    String JPQL = null;
    Query q = null;
    List<FiltroEntity> estudios = null;

    @Test
    public void allExistingStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e";
        estudios = em.createQuery(JPQL).getResultList();

        for (FiltroEntity estudio: estudios) {
            System.out.println(estudio.getFkEstudio());
        }
    }
}
