package com.empresag;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EncuestaMapper {

//    public static EncuestaEntity mapDtoToEntity( EncuestaDto dto )
//    {
//        EncuestaEntity entity = FabricaEntity.crearEncuestaEntity();
//
//        Timestamp fecha;
//        String respuestaTexto;
//        Integer respuestaRangoInicial;
//        Integer respuestaRangoFinal;
//        PosibleRespuestaDto fkPosibleRespuesta;
//        PreguntaDto fkPregunta;
//        EstudioDto fkEstudio;
//
//
//        entity.setFecha( dto.getFecha() );
//        entity.setRespuestaTexto( dto.getRespuestaTexto() );
//        entity.setRespuestaRangoInicial( dto.getRespuestaRangoInicial() );
//        entity.setRespuestaRangoFinal( dto.getRespuestaRangoFinal() );
//
//
//        entity.setFkPosibleRespuesta( dto.getFkPosibleRespuesta() );
//        entity.setFkPregunta( dto.getFkPregunta() );
//        entity.setFkEstudio( dto.getFkEstudio() );
//
//
////        if ( Objects.nonNull( dto.getFkRol() ) )
////        {
////            entity.setFk_Rol( RolMapper.mapDtoToEntity( dto.getFkRol() ) );
////        }
//
//        DaoRol daoRol = FabricaDao.crearDaoRol();
//        entity.setFk_Rol(daoRol.getEncuestadoRol().get(0));
//
//        if ( Objects.nonNull( dto.getFkPersona() ) )
//        {
//            entity.setFk_Persona( PersonaMapper.mapDtoToEntity( dto.getFkPersona() ) );
//        }
//
//        return entity;
//    }

    public static List<EncuestaEntity> mapListDtoToEntity( long studyId, long personId, List<EncuestaDto> listaDto, boolean isInterview ){
        DaoEstudio daoEstudio = FabricaDao.crearDaoEstudio();
        DaoPersona daoPersona = FabricaDao.crearDaoPersona();
        DaoPosibleRespuesta daoPosibleRespuesta = FabricaDao.crearDaoPosibleRespuesta();
        DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();
//
        List<EncuestaEntity> encuestaList = new ArrayList<>();
        EstudioEntity estudio = daoEstudio.find(studyId, EstudioEntity.class);
        PersonaEntity persona = null;
        if (isInterview)
            persona = daoPersona.find(personId, PersonaEntity.class);
        else
            persona = daoPersona.findPersonByUser(personId);

        for (EncuestaDto ejecucionEncuesta: listaDto) {
            EncuestaEntity encuesta = FabricaEntity.crearEncuestaEntity();
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

            encuestaList.add(encuesta);
        }


        return encuestaList;
    }

}
