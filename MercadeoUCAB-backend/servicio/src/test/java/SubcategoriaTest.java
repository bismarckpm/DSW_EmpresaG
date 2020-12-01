import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class SubcategoriaTest {
    String JPQL = null;
    Query q = null;
    List<CategoriaSubcategoriaEntity> cs = null;

    @Test
    public void getAllSubcategories(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT cs FROM CategoriaSubcategoriaEntity cs";
        cs = em.createQuery(JPQL).getResultList();

        for (CategoriaSubcategoriaEntity cse: cs) {
            System.out.println(cse.getFkCategoria());
            System.out.println(cse.getFkSubcategoria());
        }
    }

    @Test
    public void getSubcategoriesByCategoryID(){
        long category_id = 3;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT cs FROM CategoriaSubcategoriaEntity cs WHERE cs.fkCategoria._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", category_id);

        cs = q.getResultList();

        for (CategoriaSubcategoriaEntity cse: cs) {
            System.out.println(cse.getFkCategoria());
            System.out.println(cse.getFkSubcategoria());
        }
    }

    @Test
    public void addSubcategoryStandalone(){
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        SubcategoriaEntity subcategoria = new SubcategoriaEntity();
        subcategoria.setNombre("Música electrónica");
        subcategoria.setDescripcion("Productos para la producción de música a computadora, " +
                "no tiene que ver con el género pero podría ser con ese fin");

        daoSubcategoria.insert(subcategoria);

        Assert.assertNotEquals(0, subcategoria.get_id());
    }

    @Test
    public void addSubcategory(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        CategoriaEntity categoria = new CategoriaEntity(1);
        categoria.setNombre("Ropa");
        SubcategoriaEntity subcategoria = new SubcategoriaEntity();
        subcategoria.setNombre("Disfraces");
        daoSubcategoria.insert(subcategoria);

        CategoriaSubcategoriaEntity cs = new CategoriaSubcategoriaEntity();
        cs.setFkCategoria(categoria);
        cs.setFkSubcategoria(subcategoria);
        daoCategoriaSubcategoria.insert(cs);

        Assert.assertNotEquals(0, cs.get_id());
    }

    @Test
    public void updateSubcategoryStandalone(){
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        SubcategoriaEntity subcategoria = daoSubcategoria.find(8L, SubcategoriaEntity.class);
        subcategoria.setDescripcion("Disfraces para niños y adultos, incluye cosplay");

        daoSubcategoria.update(subcategoria);

        Assert.assertNotEquals(0, subcategoria.get_id());
    }

    @Test
    public void updateCategoryInSubcategory(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        CategoriaSubcategoriaEntity cs = new CategoriaSubcategoriaEntity(19);
        cs.setFkCategoria(new CategoriaEntity(2));
        cs.setFkSubcategoria(new SubcategoriaEntity(8));
        daoCategoriaSubcategoria.update(cs);

        Assert.assertNotEquals(0, cs.get_id());
    }

    @Test
    public void deleteSubcategory(){
        DaoCategoriaSubcategoria daoCategoriaSubcategoria = new DaoCategoriaSubcategoria();
        CategoriaSubcategoriaEntity cs = daoCategoriaSubcategoria.find(19L, CategoriaSubcategoriaEntity.class);
        daoCategoriaSubcategoria.delete(cs);

        Assert.assertNotEquals(0, cs.get_id());
    }
}
