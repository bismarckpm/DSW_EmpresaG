import com.empresag.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EncuestaTest {
    String JPQL = null;
    Query q = null;

    @Test
    public void getAvailablePopulation(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(1L, EstudioEntity.class);

        JPQL = "SELECT DISTINCT p FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, " +
                "UsuarioEntity u, RolEntity r " +
                "WHERE f.fkEstudio = :estudio " +
                "AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) " +
                "AND u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR f.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = :estudio AND e.fkPersona = p)";

        q = em.createQuery(JPQL);
        q.setParameter("estudio", estudio);

        List<PersonaEntity> personas = q.getResultList();
        System.out.println("Resultado: " + personas.size());
        for (PersonaEntity persona: personas) {
            System.out.println(persona);
        }
    }

    @Test
    public void isUserPartOfAvailablePopulation(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoEncuesta daoEncuesta = new DaoEncuesta();
        DaoPersona daoPersona = new DaoPersona();
        PersonaEntity persona = daoPersona.find(64L, PersonaEntity.class);
        List<PersonaEntity> personas = daoEncuesta.getAvailablePopulation(1);
        boolean found = false;

        for (PersonaEntity p: personas) {
            if (p.get_id() == persona.get_id()){
                found = true;
            }
        }
        Assert.assertEquals(true, found);
    }

    @Test
    public void getAvailableStudies(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("empresag");
        EntityManager em = emf.createEntityManager();
        DaoPersona daoPersona = new DaoPersona();
        PersonaEntity persona = daoPersona.find(70L, PersonaEntity.class);

        JPQL = "SELECT f FROM PersonaEntity p, FiltroEntity f, PersonaNvlacademicoEntity pna, UsuarioEntity u, " +
                "RolEntity r, EstudioEntity ee " +
                "WHERE p = :persona AND f.fkEstudio IS NOT NULL AND (pna.fkPersona = p OR NOT EXISTS " +
                "(SELECT aux FROM PersonaNvlacademicoEntity aux WHERE aux.fkPersona = p)) AND " +
                "u.fk_Persona = p AND u.fk_Rol = r AND r.nombre = 'Encuestado' " +
                "AND (f.fkEdoCivil = p.fkEdoCivil OR f.fkEdoCivil IS NULL) " +
                "AND (f.fkGenero = p.fkGenero OR f.fkGenero IS NULL) " +
                "AND (f.fkLugar = p.fkLugar OR p.fkLugar IS NULL) " +
                "AND (f.fkNivelAcademico = pna.fkNivelAcademico OR f.fkNivelAcademico IS NULL) " +
                "AND NOT EXISTS (SELECT e FROM EncuestaEntity e WHERE e.fkEstudio = f.fkEstudio AND e.fkPersona = p) " +
                "AND ee = f.fkEstudio AND ee.estado = 1 " +
                "GROUP BY f.fkEstudio";

        q = em.createQuery(JPQL);
        q.setParameter("persona", persona);
        List<FiltroEntity> estudios = q.getResultList();

        for (FiltroEntity estudio: estudios) {
            System.out.println(estudio.getFkEstudio().get_id());
        }
    }

    @Test
    public void takeSurvey() throws IndexDatabaseException {
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPersona daoPersona = new DaoPersona();
        DaoPosibleRespuesta daoPosibleRespuesta = new DaoPosibleRespuesta();
        DaoPregunta daoPregunta = new DaoPregunta();
        DaoEncuesta daoEncuesta = new DaoEncuesta();

        EstudioEntity estudio = null;
        PersonaEntity persona = null;

        estudio = daoEstudio.find(1L, EstudioEntity.class);
        persona = daoPersona.find(4L, PersonaEntity.class);

        /* ONE */

        EncuestaDto one = new EncuestaDto();
        one.setRespuestaTexto("test");

        PreguntaDto preguntaOne = new PreguntaDto();
        preguntaOne.set_id(1);
        one.setFkPregunta(preguntaOne);

        /* TWO */

        EncuestaDto two = new EncuestaDto();

        PreguntaDto preguntaTwo = new PreguntaDto();
        preguntaTwo.set_id(21);
        two.setFkPregunta(preguntaTwo);

        PosibleRespuestaDto prTwo = new PosibleRespuestaDto();
        prTwo.set_id(45);

        /* THREE */

        EncuestaDto three = new EncuestaDto();

        PreguntaDto preguntaThree = new PreguntaDto();
        preguntaThree.set_id(23);
        three.setFkPregunta(preguntaThree);

        PosibleRespuestaDto prThree = new PosibleRespuestaDto();
        prThree.set_id(51);

        /* FOUR */

        EncuestaDto four = new EncuestaDto();

        PreguntaDto preguntaFour = new PreguntaDto();
        preguntaFour.set_id(23);
        four.setFkPregunta(preguntaFour);

        PosibleRespuestaDto prFour = new PosibleRespuestaDto();
        prFour.set_id(50);

        List<EncuestaDto> encuestaDto = new ArrayList<>();
        encuestaDto.add(one);
        encuestaDto.add(two);
        encuestaDto.add(three);
        encuestaDto.add(four);

        for (EncuestaDto ejecucionEncuesta: encuestaDto) {
            EncuestaEntity encuesta = new EncuestaEntity();
            encuesta.setFecha(new Timestamp(System.currentTimeMillis()));

            if (ejecucionEncuesta.getRespuestaTexto() != null){
                encuesta.setRespuestaTexto(ejecucionEncuesta.getRespuestaTexto());
            }
            if (ejecucionEncuesta.getRespuestaRangoInicial() != null &&
                    ejecucionEncuesta.getRespuestaRangoFinal() != null){
                encuesta.setRespuestaRangoInicial(ejecucionEncuesta.getRespuestaRangoInicial());
                encuesta.setRespuestaRangoFinal(ejecucionEncuesta.getRespuestaRangoFinal());
            }
            if (ejecucionEncuesta.getFkPosibleRespuesta() != null){
                PosibleRespuestaEntity pr = daoPosibleRespuesta.find
                        (ejecucionEncuesta.getFkPosibleRespuesta().get_id(), PosibleRespuestaEntity.class);

                encuesta.setFkPosibleRespuesta(pr);
            }

            PreguntaEntity pregunta = daoPregunta.find(ejecucionEncuesta.getFkPregunta().get_id(),
                    PreguntaEntity.class);

            encuesta.setFkPregunta(pregunta);
            encuesta.setFkPersona(persona);
            encuesta.setFkEstudio(estudio);
            daoEncuesta.insert(encuesta);
        }
    }
}
