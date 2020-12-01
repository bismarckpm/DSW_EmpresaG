import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TipoTest {
    String JPQL = null;
    Query q = null;
    List<MarcaTipoEntity> mt = null;

    @Test
    public void getAllBrands(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT mt FROM MarcaTipoEntity mt";
        mt = em.createQuery(JPQL).getResultList();

        for (MarcaTipoEntity mte: mt) {
            System.out.println(mte.getFkMarca());
            System.out.println(mte.getFkTipo());
        }
    }

    @Test
    public void addType(){
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        DaoTipo daoTipo = new DaoTipo();

        MarcaEntity marca = new MarcaEntity(1);
        marca.setNombre("Supreme");

        TipoEntity tipo = new TipoEntity();
        tipo.setNombre("Abrigo");
        tipo.setDescripcion("Abrigos para todas las temporadas");
        daoTipo.insert(tipo);

        MarcaTipoEntity mt = new MarcaTipoEntity();
        mt.setFkMarca(marca);
        mt.setFkTipo(tipo);
        daoMarcaTipo.insert(mt);

        Assert.assertNotEquals(0, mt.get_id());
    }

    @Test
    public void updateType(){
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        DaoTipo daoTipo = new DaoTipo();
        MarcaTipoEntity mt = daoMarcaTipo.find(4L, MarcaTipoEntity.class);

        if (mt != null){
            mt.setFkMarca(new MarcaEntity(3, "Vans"));
            mt.setFkTipo(new TipoEntity(4));

            TipoEntity tipo = daoTipo.find(4L, TipoEntity.class);

            if (tipo != null){
                tipo.setNombre("Pantalones");
                tipo.setDescripcion(null);
                daoTipo.update(tipo);
                daoMarcaTipo.update(mt);
            }
        }
        Assert.assertNotEquals(0, mt.get_id());
    }

    @Test
    public void deleteBrand(){
        DaoMarcaTipo daoMarcaTipo = new DaoMarcaTipo();
        MarcaTipoEntity mt = daoMarcaTipo.find(4L, MarcaTipoEntity.class);
        daoMarcaTipo.delete(mt);
        Assert.assertNotEquals(0, mt.get_id());
    }
}
