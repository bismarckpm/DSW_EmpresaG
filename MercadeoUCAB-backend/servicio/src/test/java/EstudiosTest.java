import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EstudiosTest {
    String JPQL = null;
    Query q = null;
    List<FiltroEntity> estudios = null;

    @Test
    public void allExistingStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e WHERE e.fkEstudio IS NOT NULL";
        estudios = em.createQuery(JPQL).getResultList();

        for (FiltroEntity estudio: estudios) {
            System.out.println(estudio.getFkEstudio());
        }
    }

    @Test
    public void allActiveStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        JPQL = "SELECT e FROM FiltroEntity e, EstudioEntity ee WHERE e.fkEstudio " +
                "IS NOT NULL AND e.fkEstudio = ee AND ee.estado = 1";
        List<FiltroEntity> estudios = em.createQuery(JPQL).getResultList();

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
    public void getQuestionForStudyWithOptions() throws IndexDatabaseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);

        JPQL = "SELECT pe FROM " +
                "PreguntaEstudioEntity pe, PreguntaEntity p " +
                "WHERE " +
                "p = pe.fkPregunta AND p.status = 1 " +
                "AND pe.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        List<PreguntaEstudioEntity> preguntas = q.getResultList();
        List<PreguntaEstudioDto> resultSet = new ArrayList<>();

        for (PreguntaEstudioEntity pregunta: preguntas) {
            JPQL = "SELECT pr FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta.getFkPregunta());
            List<PosibleRespuestaEntity> opciones = q.getResultList();

            PreguntaEstudioDto np = new PreguntaEstudioDto();
            np.setFkPregunta(new PreguntaDto());
            np.getFkPregunta().setStatus(pregunta.getFkPregunta().getStatus());
            np.getFkPregunta().setPregunta(pregunta.getFkPregunta().getPregunta());
            np.getFkPregunta().set_id(pregunta.getFkPregunta().get_id());

            np.getFkPregunta().setFkTipoPregunta(new TipoPreguntaDto());
            np.getFkPregunta().getFkTipoPregunta().set_id(pregunta.getFkPregunta().getFkTipoPregunta().get_id());

            np.getFkPregunta().setListPosibleRespuestas(new ArrayList<PosibleRespuestaDto>());

            for (PosibleRespuestaEntity opcion: opciones) {
                PosibleRespuestaDto op = new PosibleRespuestaDto();
                op.set_id(opcion.get_id());
                op.setFkOpcion(new OpcionDto());
                op.getFkOpcion().set_id(opcion.getFkOpcion().get_id());
                op.getFkOpcion().setValor(opcion.getFkOpcion().getValor());
                op.getFkOpcion().setRangoInicial(opcion.getFkOpcion().getRangoInicial());
                op.getFkOpcion().setRangoFinal(opcion.getFkOpcion().getRangoFinal());
                np.getFkPregunta().getListPosibleRespuestas().add(op);
            }

            resultSet.add(np);
        }

        for (PreguntaEstudioDto pregunta: resultSet) {
            System.out.println(pregunta.getFkPregunta());
        }

    }

    @Test
    public void assignStudy(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();

        DaoEstudio daoEstudio = new DaoEstudio();
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        DaoFiltro daoFiltro = new DaoFiltro();
        FiltroEntity studyToClone = null;
        SolicitudEntity solicitud = null;
        EstudioEntity estudio = null;

        estudio = new EstudioEntity();
        estudio.setFechaRealizacion(new Date(System.currentTimeMillis()));
        estudio.setEstado(1);
        daoEstudio.insert(estudio);

        studyToClone = daoFiltro.getCurrentStudy(1);
        solicitud = daoSolicitud.find(1L, SolicitudEntity.class);
        studyToClone.setFkSolicitud(solicitud);
        studyToClone.setFkEstudio(estudio);
        daoFiltro.insert(studyToClone);

        StoredProcedureQuery query = em.createStoredProcedureQuery("CLONE_STUDY");
        query.registerStoredProcedureParameter("study_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cloned_study_id", Integer.class, ParameterMode.IN);
        query.setParameter("study_id", 1);
        query.setParameter("cloned_study_id", estudio.get_id());
        query.execute();

        //Assert.assertNotEquals(0, studyToClone.get_id());
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
