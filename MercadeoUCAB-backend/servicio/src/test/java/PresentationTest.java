import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PresentationTest {
    String JPQL = null;
    Query q = null;
    List<TipoPresentacionEntity> tp = null;

    @Test
    public void getAllPresentations(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT tp FROM TipoPresentacionEntity tp";
        tp = em.createQuery(JPQL).getResultList();

        for (TipoPresentacionEntity tpe: tp) {
            System.out.println(tpe.getFkTipo());
            System.out.println(tpe.getFkPresentacion());
        }
    }

    @Test
    public void addPresentation(){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        DaoPresentacion daoPresentacion = new DaoPresentacion();

        TipoEntity tipo = new TipoEntity(5);
        tipo.setNombre("Trackpants");

        PresentacionEntity presentacion = new PresentacionEntity();
        presentacion.setNombre("Blanco");
        presentacion.setDescripcion("Color de pantalón");
        daoPresentacion.insert(presentacion);

        TipoPresentacionEntity tp = new TipoPresentacionEntity();
        tp.setFkPresentacion(presentacion);
        tp.setFkTipo(tipo);
        daoTipoPresentacion.insert(tp);

        Assert.assertNotEquals(0, tp.get_id());
    }

    @Test
    public void updatePresentation(){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        TipoPresentacionEntity tp = daoTipoPresentacion.find(8L, TipoPresentacionEntity.class);

        if (tp != null){
            tp.setFkTipo(new TipoEntity(1, "Zapatos"));
            tp.setFkPresentacion(new PresentacionEntity(8));

            PresentacionEntity presentacion = daoPresentacion.find(8L, PresentacionEntity.class);

            if (presentacion != null){
                presentacion.setNombre("Classic");
                presentacion.setDescripcion(null);
                daoPresentacion.update(presentacion);
                daoTipoPresentacion.update(tp);
            }
        }
        Assert.assertNotEquals(0, tp.get_id());
    }

    @Test
    public void deletePresentation(){
        DaoTipoPresentacion daoTipoPresentacion = new DaoTipoPresentacion();
        TipoPresentacionEntity tp = daoTipoPresentacion.find(8L, TipoPresentacionEntity.class);
        daoTipoPresentacion.delete(tp);
        Assert.assertNotEquals(0, tp.get_id());
    }
}
