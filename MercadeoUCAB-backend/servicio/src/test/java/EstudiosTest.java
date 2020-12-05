import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
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

    @Test
    public void getCurrentStudy(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();

        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        FiltroEntity e = (FiltroEntity) q.getSingleResult();
        System.out.println(e);
    }

    @Test
    public void getQuestionsForStudy(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();

        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs, " +
                "PreguntaEstudioEntity pe, PreguntaEntity p " +
                "WHERE pcs.fkPregunta = pe.fkPregunta " +
                "AND p = pcs.fkPregunta AND p.status = 1 " +
                "AND pe.fkEstudio = :estudio";

        q = em.createQuery(JPQL, PreguntaEstudioEntity.class);
        q.setParameter("estudio", estudio);

        List<PreguntaCatSubcatEntity> pcs = q.getResultList();

        for (PreguntaCatSubcatEntity pcse: pcs) {
            System.out.println(pcse.getFkPregunta());
        }
    }

    @Test
    public void modifyFilters(){
        DaoFiltro daoFiltro = new DaoFiltro();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoEdoCivil daoEdoCivil = new DaoEdoCivil();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
        DaoGenero daoGenero = new DaoGenero();
        DaoLugar daoLugar = new DaoLugar();

        FiltroEntity filtro = daoFiltro.find(1L, FiltroEntity.class);
        CategoriaEntity categoria = daoCategoria.find(2L, CategoriaEntity.class);
        SubcategoriaEntity subcategoria = daoSubcategoria.find(2L, SubcategoriaEntity.class);
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);
        EdoCivilEntity estadoCivil = daoEdoCivil.find(1L, EdoCivilEntity.class);
        GeneroEntity genero = daoGenero.find(1L, GeneroEntity.class);
        NivelAcademicoEntity nivelAcademico = daoNivelAcademico.find(1L, NivelAcademicoEntity.class);
        NivelSocioeconomicoEntity nivelSocioeconomico =
                daoNivelSocioeconomico.find(1L, NivelSocioeconomicoEntity.class);
        LugarEntity lugar = daoLugar.find(2L, LugarEntity.class);

        filtro.setFkEstudio(estudio);
        filtro.setEdadMinima(66);
        filtro.setEdadMaxima(77);
        filtro.setFkCategoria(categoria);
        filtro.setFkSubcategoria(subcategoria);
        filtro.setFkEdoCivil(estadoCivil);
        filtro.setFkGenero(genero);
        filtro.setFkNivelAcademico(nivelAcademico);
        filtro.setFkNivelSocioeconomico(nivelSocioeconomico);
        filtro.setTipoFiltroLugar(2);
        filtro.setFkLugar(lugar);
        daoFiltro.update(filtro);

        Assert.assertNotEquals(0, filtro.get_id());
    }

    @Test
    public void linkCreatedQuestion(){
        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoEstudio daoEstudio = new DaoEstudio();

        PreguntaCatSubcatEntity pcs = null;
        EstudioEntity estudio = null;

        pcs = daoPreguntaCategoriaSubcategoria.find(35L, PreguntaCatSubcatEntity.class);
        estudio = daoEstudio.find(1L, EstudioEntity.class);

        PreguntaEstudioEntity pe = new PreguntaEstudioEntity();
        pe.setFkEstudio(estudio);
        pe.setFkPregunta(pcs.getFkPregunta());
        daoPreguntaEstudio.insert(pe);

        Assert.assertNotEquals(0, pe.get_id());
    }
}
