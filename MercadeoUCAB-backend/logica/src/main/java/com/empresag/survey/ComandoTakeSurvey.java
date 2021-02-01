package com.empresag.survey;

import com.empresag.*;

import java.util.List;

public class ComandoTakeSurvey extends ComandoBase {

    private List<EncuestaEntity> listaEncuesta;
    private RespuestaDto<Boolean> respuesta;

    public ComandoTakeSurvey(long studyId, long personId, List<EncuestaDto> listaEncuestaDto) {
        this.listaEncuesta = EncuestaMapper.mapListDtoToEntity(studyId, personId, listaEncuestaDto, false);
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

            for (EncuestaEntity encuesta : listaEncuesta) {
                daoEncuesta.insert(encuesta);
            }

            respuesta.setCodigo(0);
            respuesta.setEstado("OK");
            respuesta.setMensaje( "Encuesta almacenada exitosamente." );
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
