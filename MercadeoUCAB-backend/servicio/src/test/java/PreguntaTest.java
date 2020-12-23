import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PreguntaTest {
    String JPQL = null;
    Query q = null;
    List<PreguntaCatSubcatEntity> pcs = null;

    // Preguntas activas

    @Test
    public void allQuestions(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs WHERE pcs.fkPregunta.status = 1";
        pcs = em.createQuery(JPQL).getResultList();

        for (PreguntaCatSubcatEntity pcse: pcs) {
            System.out.println(pcse.getFkPregunta());
            System.out.println(pcse.getFkCategoria());
            System.out.println(pcse.getFkSubcategoria());
        }
    }

    @Test
    public void allActiveQuestionsThatDontExistInStudyByCategory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT pcs FROM PreguntaCatSubcatEntity pcs WHERE pcs.fkPregunta.status = 1" +
                " AND pcs.fkCategoria._id = :idCategoria";
        q = em.createQuery(JPQL);
        q.setParameter("idCategoria", 1);
        pcs = q.getResultList();

        for (PreguntaCatSubcatEntity pcse: pcs) {
            System.out.print(pcse.getFkPregunta().get_id()+" ");
            System.out.println(pcse.getFkPregunta());
            System.out.println(pcse.getFkCategoria());
            System.out.println(pcse.getFkSubcategoria());
        }
    }

    @Test
    public void addOpenTextQuestion(){
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();

        PreguntaEntity pregunta = new PreguntaEntity();
        pregunta.setFkTipoPregunta(new TipoPreguntaEntity(1));
        pregunta.setStatus(1);
        pregunta.setPregunta("¿Qué temática recomendaría para el próximo drop?");
        daoPregunta.insert(pregunta);

        PreguntaCatSubcatEntity pcs = new PreguntaCatSubcatEntity();
        pcs.setFkCategoria(new CategoriaEntity(1));
        pcs.setFkSubcategoria(new SubcategoriaEntity(1));
        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
        daoPreguntaCategoriaSubcategoria.insert(pcs);

        Assert.assertNotEquals(0, pcs.get_id());
    }

    @Test
    public void addSelectionQuestion(){
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();
        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();

        PreguntaEntity pregunta = new PreguntaEntity();
        pregunta.setFkTipoPregunta(new TipoPreguntaEntity(1));
        pregunta.setStatus(1);
        pregunta.setPregunta("¿En qué sección suele comprar?");
        daoPregunta.insert(pregunta);

        PreguntaCatSubcatEntity pcs = new PreguntaCatSubcatEntity();
        pcs.setFkCategoria(new CategoriaEntity(1));
        pcs.setFkSubcategoria(null);
        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
        daoPreguntaCategoriaSubcategoria.insert(pcs);

        List<OpcionEntity> opciones = new ArrayList<>();
        opciones.add(new OpcionEntity("Camisas"));
        opciones.add(new OpcionEntity("Trajes de baño"));
        opciones.add(new OpcionEntity("Casual"));
        opciones.add(new OpcionEntity("Elegante"));
        opciones.add(new OpcionEntity("Sudaderas"));
        opciones.add(new OpcionEntity("Vaqueros"));

        for (OpcionEntity opcion: opciones) {
            daoOpcion.insert(opcion);
            PosibleRespuestaEntity pr = new PosibleRespuestaEntity();
            pr.setFkOpcion(new OpcionEntity(opcion.get_id()));
            pr.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
            daoPosibleRespuesta.insert(pr);
        }

        Assert.assertNotEquals(0, pcs.get_id());
    }

    @Test
    public void updateSelectionQuestion(){
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();

        PreguntaEntity pregunta = daoPregunta.find(24L, PreguntaEntity.class);
        pregunta.setFkTipoPregunta(new TipoPreguntaEntity(1));
        pregunta.setStatus(1);
        pregunta.setPregunta("¿Cuál es su estereotipo de personaje favorito?");
        daoPregunta.update(pregunta);

        PreguntaCatSubcatEntity pcs = daoPreguntaCategoriaSubcategoria.find(24L, PreguntaCatSubcatEntity.class);
        pcs.setFkCategoria(new CategoriaEntity(4));
        pcs.setFkSubcategoria(new SubcategoriaEntity(4));
        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
        daoPreguntaCategoriaSubcategoria.update(pcs);

        List<OpcionEntity> opciones = new ArrayList<>();
        opciones.add(new OpcionEntity(49,  "Tsundere"));
        opciones.add(new OpcionEntity(50, "Yandere"));
        opciones.add(new OpcionEntity(51, "Yangire"));
        opciones.add(new OpcionEntity(52, "Dandere"));
        opciones.add(new OpcionEntity(53,"Kuudere"));
        opciones.add(new OpcionEntity(54,"Himedere"));

        for (OpcionEntity opcion: opciones) {
            daoOpcion.update(opcion);
        }

        Assert.assertNotEquals(0, pcs.get_id());
    }

    @Test
    public void updateFromSelectionToOpenTextQuestion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        DaoPregunta daoPregunta = new DaoPregunta();
        DaoPreguntaCategoriaSubcategoria daoPreguntaCategoriaSubcategoria = new DaoPreguntaCategoriaSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();

        PreguntaEntity pregunta = daoPregunta.find(25L, PreguntaEntity.class);
        pregunta.setFkTipoPregunta(new TipoPreguntaEntity(1));
        pregunta.setStatus(1);
        pregunta.setPregunta("¿Qué temática recomendaría para el próximo drop?");
        daoPregunta.update(pregunta);

        PreguntaCatSubcatEntity pcs = daoPreguntaCategoriaSubcategoria.find(24L, PreguntaCatSubcatEntity.class);
        pcs.setFkCategoria(new CategoriaEntity(1));
        pcs.setFkSubcategoria(new SubcategoriaEntity(1));
        pcs.setFkPregunta(new PreguntaEntity(pregunta.get_id()));
        daoPreguntaCategoriaSubcategoria.update(pcs);

        /* Delete the options */
        List<OpcionEntity> opciones = null;
        JPQL = "SELECT pr.fkOpcion FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta._id = :id";
        q = em.createQuery(JPQL);
        q.setParameter("id", 25);
        opciones = q.getResultList();

        for (OpcionEntity opcion: opciones) {
            OpcionEntity op = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
            daoOpcion.delete(op);
        }

        Assert.assertNotEquals(0, pcs.get_id());
    }

    @Test
    public void deleteQuestion(){
        DaoPregunta daoPregunta = new DaoPregunta();
        PreguntaEntity pregunta = daoPregunta.find(25L, PreguntaEntity.class);
        pregunta.setStatus(0);
        daoPregunta.update(pregunta);
        Assert.assertNotEquals(0, pregunta.get_id());
    }
}
