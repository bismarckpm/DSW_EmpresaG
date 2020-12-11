import com.empresag.*;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnalisisTest {
    String JPQL = null;
    Query q = null;

    @Test
    public void getOpenTextAnswers() throws IndexDatabaseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);
        TipoPreguntaEntity tipo = daoTipoPregunta.find(1L, TipoPreguntaEntity.class);

        JPQL = "SELECT e FROM PreguntaEstudioEntity e, PreguntaEntity p, TipoPreguntaEntity tp " +
                "WHERE e.fkPregunta = p AND tp = p.fkTipoPregunta AND tp = :tipo AND e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        q.setParameter("tipo", tipo);
        List<PreguntaEstudioEntity> preguntas = q.getResultList();
        List<PreguntaEstudioDto> resultSet = new ArrayList<>();

        for (PreguntaEstudioEntity pregunta: preguntas) {
            List<EncuestaEntity> respuestas = null;
            PreguntaEstudioDto pe = new PreguntaEstudioDto();
            pe.setFkPregunta(new PreguntaDto());
            pe.getFkPregunta().set_id(pregunta.getFkPregunta().get_id());
            pe.getFkPregunta().setPregunta(pregunta.getFkPregunta().getPregunta());
            pe.setListRespuestasTexto(new ArrayList<EncuestaDto>());

            JPQL = "SELECT e FROM EncuestaEntity e WHERE e.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta.getFkPregunta());
            respuestas = q.getResultList();

            for (EncuestaEntity respuesta: respuestas) {
                EncuestaDto encuestaDto = new EncuestaDto();
                encuestaDto.setRespuestaTexto(respuesta.getRespuestaTexto());
                pe.getListRespuestasTexto().add(encuestaDto);
            }

            resultSet.add(pe);
        }

        for (PreguntaEstudioDto pedto: resultSet) {
            System.out.println(pedto.getFkPregunta());
            for (EncuestaDto prdto: pedto.getListRespuestasTexto()) {
                System.out.println(prdto.getRespuestaTexto());
            }
        }

    }

    @Test
    public void getSelectionAnswers() throws IndexDatabaseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);
        TipoPreguntaEntity tipo = daoTipoPregunta.find(2L, TipoPreguntaEntity.class);
        TipoPreguntaEntity tipo2 = daoTipoPregunta.find(3L, TipoPreguntaEntity.class);

        JPQL = "SELECT e FROM PreguntaEstudioEntity e, PreguntaEntity p, TipoPreguntaEntity tp " +
                "WHERE e.fkPregunta = p AND tp = p.fkTipoPregunta AND (tp = :tipo OR tp = :tipo2) AND e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        q.setParameter("tipo", tipo);
        q.setParameter("tipo2", tipo2);
        List<PreguntaEstudioEntity> preguntas = q.getResultList();
        List<PreguntaEstudioDto> resultSet = new ArrayList<>();

        for (PreguntaEstudioEntity pregunta: preguntas) {
            PreguntaEstudioDto pe = new PreguntaEstudioDto();
            pe.setFkPregunta(new PreguntaDto());
            pe.getFkPregunta().set_id(pregunta.getFkPregunta().get_id());
            pe.getFkPregunta().setPregunta(pregunta.getFkPregunta().getPregunta());
            pe.getFkPregunta().setListOpciones(new ArrayList<OpcionDto>());

            JPQL = "SELECT pr FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta.getFkPregunta());
            List<PosibleRespuestaEntity> opciones = q.getResultList();

            for (PosibleRespuestaEntity prs: opciones) {
                OpcionDto opcionDto = new OpcionDto();
                opcionDto.set_id(prs.getFkOpcion().get_id());
                opcionDto.setValor(prs.getFkOpcion().getValor());

                JPQL = "SELECT COUNT(e) FROM EncuestaEntity e WHERE e.fkPosibleRespuesta = :pr";
                q = em.createQuery(JPQL);
                q.setParameter("pr", prs);
                opcionDto.setNumeroDePersonas((Long) q.getSingleResult());

                pe.getFkPregunta().getListOpciones().add(opcionDto);
            }
            resultSet.add(pe);
        }

        for (PreguntaEstudioDto pedto: resultSet) {
            System.out.println(pedto.getFkPregunta());
            for (OpcionDto op: pedto.getFkPregunta().getListOpciones()) {
                System.out.println("NUMERO DE PERSONAS QUE CONTESTARON LA OPCION: "+ op.getValor() + " "+ op.getNumeroDePersonas());
            }
        }
    }

    @Test
    public void getTrueFalseAnswers() throws IndexDatabaseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);
        TipoPreguntaEntity tipo = daoTipoPregunta.find(4L, TipoPreguntaEntity.class);

        JPQL = "SELECT e FROM PreguntaEstudioEntity e, PreguntaEntity p, TipoPreguntaEntity tp " +
                "WHERE e.fkPregunta = p AND tp = p.fkTipoPregunta AND tp = :tipo AND e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        q.setParameter("tipo", tipo);
        List<PreguntaEstudioEntity> preguntas = q.getResultList();
        List<PreguntaEstudioDto> resultSet = new ArrayList<>();

        for (PreguntaEstudioEntity pregunta: preguntas) {
            PreguntaEstudioDto pe = new PreguntaEstudioDto();
            pe.setFkPregunta(new PreguntaDto());
            pe.getFkPregunta().set_id(pregunta.getFkPregunta().get_id());
            pe.getFkPregunta().setPregunta(pregunta.getFkPregunta().getPregunta());
            pe.getFkPregunta().setListOpciones(new ArrayList<OpcionDto>());

            JPQL = "SELECT pr FROM PosibleRespuestaEntity pr WHERE pr.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta.getFkPregunta());
            List<PosibleRespuestaEntity> opciones = q.getResultList();

            for (PosibleRespuestaEntity prs: opciones) {
                OpcionDto opcionDto = new OpcionDto();
                opcionDto.set_id(prs.getFkOpcion().get_id());
                opcionDto.setValor(prs.getFkOpcion().getValor());

                JPQL = "SELECT COUNT(e) FROM EncuestaEntity e WHERE e.fkPosibleRespuesta = :pr";
                q = em.createQuery(JPQL);
                q.setParameter("pr", prs);
                opcionDto.setNumeroDePersonas((Long) q.getSingleResult());

                pe.getFkPregunta().getListOpciones().add(opcionDto);
            }
            resultSet.add(pe);
        }

        for (PreguntaEstudioDto pedto: resultSet) {
            System.out.println(pedto.getFkPregunta());
            for (OpcionDto op: pedto.getFkPregunta().getListOpciones()) {
                System.out.println("NUMERO DE PERSONAS QUE CONTESTARON LA OPCION: "+ op.getValor() + " "+ op.getNumeroDePersonas());
            }
        }
    }

    @Test
    public void getRangeAnswers() throws IndexDatabaseException {
        Object [] tuple = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);
        TipoPreguntaEntity tipo = daoTipoPregunta.find(5L, TipoPreguntaEntity.class);

        JPQL = "SELECT e FROM PreguntaEstudioEntity e, PreguntaEntity p, TipoPreguntaEntity tp " +
                "WHERE e.fkPregunta = p AND tp = p.fkTipoPregunta AND tp = :tipo AND e.fkEstudio = :estudio";
        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);
        q.setParameter("tipo", tipo);
        List<PreguntaEstudioEntity> preguntas = q.getResultList();
        List<PreguntaEstudioDto> resultSet = new ArrayList<>();

        for (PreguntaEstudioEntity pregunta: preguntas) {
            PreguntaEstudioDto pe = new PreguntaEstudioDto();
            pe.setFkPregunta(new PreguntaDto());
            pe.getFkPregunta().set_id(pregunta.getFkPregunta().get_id());
            pe.getFkPregunta().setPregunta(pregunta.getFkPregunta().getPregunta());
            pe.setListRespuestasTexto(new ArrayList<EncuestaDto>());

            JPQL = "SELECT AVG(e.respuestaRangoInicial) as rangoInicial, AVG(e.respuestaRangoFinal) as rangoFinal " +
                    "FROM EncuestaEntity e " +
                    "WHERE e.fkPregunta = :pregunta";
            q = em.createQuery(JPQL);
            q.setParameter("pregunta", pregunta.getFkPregunta());
            Iterator i = q.getResultList().iterator();

            while (i.hasNext()){
                tuple = (Object[]) i.next();
                pe.setPromedioRangoInicial((Double) tuple[0]);
                pe.setPromedioRangoFinal((Double) tuple[1]);
            }
            resultSet.add(pe);
        }

        for (PreguntaEstudioDto pedto: resultSet) {
            System.out.println(pedto.getFkPregunta());
            System.out.println(pedto.getPromedioRangoInicial());
            System.out.println(pedto.getPromedioRangoFinal());
        }
    }

    @Test
    public void postConclusion(){
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoAnalisis daoAnalisis = new DaoAnalisis();
        EstudioEntity estudio = daoEstudio.find(2L, EstudioEntity.class);
        estudio.setEstado(2);
        daoEstudio.update(estudio);
        AnalisisEntity analisis = new AnalisisEntity();
        analisis.setConclusiones("<p><strong><em><u>Hola</u></em></strong></p>");
        daoAnalisis.insert(analisis);
    }
}
