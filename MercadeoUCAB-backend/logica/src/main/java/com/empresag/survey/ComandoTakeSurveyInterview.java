package com.empresag.survey;

import com.empresag.*;

import java.util.List;

public class ComandoTakeSurveyInterview extends ComandoBase {

    private List<EncuestaEntity> listaEncuesta;
    private RespuestaDto<Boolean> respuesta;

    private long studyId, personId;

    public ComandoTakeSurveyInterview(long studyId, long personId, List<EncuestaDto> listaEncuestaDto) {
        this.studyId = studyId;
        this.personId = personId;
        this.listaEncuesta = EncuestaMapper.mapListDtoToEntity(studyId, personId, listaEncuestaDto, true);
        respuesta = new RespuestaDto<>();
    }

    @Override
    public void execute() {
//        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
//        DaoPersona daoPersona = FabricaDao.crearDaoPersona();
//        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();
//        DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();
//        DaoEncuesta daoEncuesta = FabricaDao.crearDaoEncuesta();
//
//        EstudioEntity estudio = null;
//        PersonaEntity persona = null;
//
//        try {
//            estudio = daoEstudio.find(studyId, EstudioEntity.class);
//            persona = daoPersona.find(personId, PersonaEntity.class);
//
//            for (EncuestaDto ejecucionEncuesta: listaEncuestaDto) {
//                EncuestaEntity encuesta = new EncuestaEntity();
//                encuesta.setFecha(new Timestamp(System.currentTimeMillis()));
//
//                if (ejecucionEncuesta.getRespuestaTexto() != null){
//                    encuesta.setRespuestaTexto(ejecucionEncuesta.getRespuestaTexto());
//                }
//                if (ejecucionEncuesta.getRespuestaRangoInicial() != null &&
//                        ejecucionEncuesta.getRespuestaRangoFinal() != null){
//                    encuesta.setRespuestaRangoInicial(ejecucionEncuesta.getRespuestaRangoInicial());
//                    encuesta.setRespuestaRangoFinal(ejecucionEncuesta.getRespuestaRangoFinal());
//                }
//                if (ejecucionEncuesta.getFkPosibleRespuesta() != null){
//                    PosibleRespuestaEntity pr = daoPosibleRespuesta.find
//                            (ejecucionEncuesta.getFkPosibleRespuesta().get_id(), PosibleRespuestaEntity.class);
//
//                    encuesta.setFkPosibleRespuesta(pr);
//                }
//
//                PreguntaEntity pregunta = daoPregunta.find(ejecucionEncuesta.getFkPregunta().get_id(),
//                        PreguntaEntity.class);
//
//                encuesta.setFkPregunta(pregunta);
//                encuesta.setFkPersona(persona);
//                encuesta.setFkEstudio(estudio);
//                daoEncuesta.insert(encuesta);
//            }
//            return Response.ok().build();
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }

        try {
            DaoEncuesta daoEncuesta = FabricaDao.crearDaoEncuesta();
            boolean update = false;

            try {
                List<EncuestaEntity> oldAnswers = daoEncuesta.getOldAnswers(studyId,personId);
                for (EncuestaEntity encuesta : oldAnswers) {
                    daoEncuesta.delete(encuesta);
                }
                update = true;
            }
            catch (NullPointerException e){
//                NO TIENE RESPUESTAS VIEJAS
            }
            catch (Exception e){
                e.printStackTrace();
            }

            for (EncuestaEntity encuesta : listaEncuesta) {
                daoEncuesta.insert(encuesta);
            }

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            if (update){
                respuesta.setMensaje("Encuesta actualizada exitosamente.");
            }
            else {
                respuesta.setMensaje("Encuesta almacenada exitosamente.");
            }
            respuesta.setObjeto(true);
        }
        catch (Exception e){
            e.printStackTrace();
            respuesta.setCodigo(-1);
            respuesta.setEstado("ERROR");
            respuesta.setMensaje( e.getMessage() );
            respuesta.setMensajesoporte( e.getLocalizedMessage() );
        }

    }

    @Override
    public RespuestaDto<Boolean> getResult() {
        return respuesta;
    }
}
