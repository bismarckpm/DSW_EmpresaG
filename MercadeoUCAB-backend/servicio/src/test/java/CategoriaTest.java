import com.empresag.CategoriaDto;
import com.empresag.CategoriaEntity;
import com.empresag.DaoCategoria;
import org.junit.Assert;
import org.junit.Test;
public class CategoriaTest {
    @Test
    public void addCategory(){
        DaoCategoria categoriaDao = new DaoCategoria();

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre("Música");
        categoria.setDescripcion(null);

        categoriaDao.insert(categoria);
        Assert.assertNotEquals( categoria.get_id(), 0 );
    }

    @Test
    public void updateCategory(){
        DaoCategoria categoriaDao = new DaoCategoria();
        CategoriaEntity categoriaOld = categoriaDao.find(1L, CategoriaEntity.class);

        categoriaOld.setNombre("Armas");
        categoriaOld.setDescripcion("Armas de fuego");
        categoriaDao.update(categoriaOld);

        Assert.assertNotEquals(categoriaOld.get_id(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void updateCategoryThatDoesNotExist(){
        DaoCategoria daoCategoria = new DaoCategoria();
        CategoriaEntity categoriaOld = daoCategoria.find(100L, CategoriaEntity.class);
    }

    @Test
    public void deleteCategory(){
        DaoCategoria categoriaDao = new DaoCategoria();
        CategoriaEntity categoriaOld = categoriaDao.find(1L, CategoriaEntity.class);
        categoriaDao.delete(categoriaOld);

        Assert.assertNotEquals(categoriaOld.get_id(), 0);
    }
}
