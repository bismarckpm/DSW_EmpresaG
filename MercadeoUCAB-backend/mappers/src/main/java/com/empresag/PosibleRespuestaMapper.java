package com.empresag;

import java.util.Objects;

public class PosibleRespuestaMapper {

    public static PosibleRespuestaEntity mapDtoToEntity( PosibleRespuestaDto dto )
    {
        PosibleRespuestaEntity entity = FabricaEntity.crearPosibleRespuestaEntity(dto.get_id());

        if ( Objects.nonNull( dto.getFkOpcion() ) )
        {
            entity.setFkOpcion( OpcionMapper.mapDtoToEntity( dto.getFkOpcion() ) );
        }
        if ( Objects.nonNull( dto.getFkPregunta() ) )
        {
            entity.setFkPregunta( PreguntaMapper.mapDtoToEntity( dto.getFkPregunta() ) );
        }

        return entity;
    }

    public static PosibleRespuestaDto mapEntityToDto( PosibleRespuestaEntity entity ) throws IndexDatabaseException {
        final PosibleRespuestaDto dto = FabricaDto.crearPosibleRespuestaDto();

        dto.setFkOpcion(OpcionMapper.mapEntityToDto(entity.getFkOpcion()));
        dto.setFkPregunta(PreguntaMapper.mapEntityToDto(entity.getFkPregunta()));

        return dto;
    }

}
