import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class MarcaTest {
    String JPQL = null;
    Query q = null;
    List<SubcategoriaMarcaEntity> sm = null;

    @Test
    public void getAllBrands(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT sm FROM SubcategoriaMarcaEntity sm";
        sm = em.createQuery(JPQL).getResultList();

        for (SubcategoriaMarcaEntity sme: sm) {
            System.out.println(sme.getFkSubcategoria());
            System.out.println(sme.getFkMarca());
        }
    }

    @Test
    public void getBrandsBySubcategoryID(){
        long subcategory_id = 1;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT sm FROM SubcategoriaMarcaEntity sm WHERE sm.fkSubcategoria._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", subcategory_id);

        sm = q.getResultList();

        for (SubcategoriaMarcaEntity sme: sm) {
            System.out.println(sme.getFkSubcategoria());
            System.out.println(sme.getFkMarca());
        }
    }

    @Test
    public void addBrandStandAlone(){
        DaoMarca daoMarca = new DaoMarca();
        MarcaEntity marca = new MarcaEntity();
        marca.setNombre("Nike");
        marca.setDescripcion(null);
        daoMarca.insert(marca);
        Assert.assertNotEquals(0, marca.get_id());
    }

    @Test
    public void addBrand(){
        DaoSubcategoriaMarca daoSubcategoriaMarca = new DaoSubcategoriaMarca();
        DaoMarca daoMarca = new DaoMarca();

        SubcategoriaEntity subcategoria = new SubcategoriaEntity(5);
        subcategoria.setNombre("Hardware");

        MarcaEntity marca = new MarcaEntity();
        marca.setNombre("Intel");
        marca.setDescripcion("Marca de procesadores y tarjetas de video");
        daoMarca.insert(marca);

        SubcategoriaMarcaEntity sm = new SubcategoriaMarcaEntity();
        sm.setFkMarca(marca);
        sm.setFkSubcategoria(subcategoria);
        daoSubcategoriaMarca.insert(sm);

        Assert.assertNotEquals(0, sm.get_id());
    }

    @Test
    public void updateBrand(){
        DaoSubcategoriaMarca daoSubcategoriaMarca = new DaoSubcategoriaMarca();
        DaoMarca daoMarca = new DaoMarca();
        SubcategoriaMarcaEntity sm = daoSubcategoriaMarca.find(4L, SubcategoriaMarcaEntity.class);

        if (sm != null){
            sm.setFkSubcategoria(new SubcategoriaEntity(1, "Hardware"));
            sm.setFkMarca(new MarcaEntity(5));

            MarcaEntity marca = daoMarca.find(5L, MarcaEntity.class);

            if (marca != null){
                marca.setNombre("AMD");
                marca.setDescripcion("Marca de procesadores y tarjetas de video");
                daoMarca.update(marca);
                daoSubcategoriaMarca.update(sm);
            }
        }

        Assert.assertNotEquals(0, sm.get_id());
    }

    @Test
    public void deleteBrand(){
        DaoSubcategoriaMarca daoSubcategoriaMarca= new DaoSubcategoriaMarca();
        SubcategoriaMarcaEntity sm = daoSubcategoriaMarca.find(4L, SubcategoriaMarcaEntity.class);
        daoSubcategoriaMarca.delete(sm);

        Assert.assertNotEquals(0, sm.get_id());
    }

}
