package com.empresag;

import java.util.Objects;

public class PreguntaMapper {

    public static PreguntaEntity mapDtoToEntity( PreguntaDto dto )
    {
        PreguntaEntity entity = FabricaEntity.crearPreguntaEntity(dto.get_id());

        entity.setPregunta(dto.getPregunta());
        entity.setStatus(dto.getStatus());

        if ( Objects.nonNull( dto.getFkTipoPregunta() ) )
        {
            entity.setFkTipoPregunta( TipoPreguntaMapper.mapDtoToEntity( dto.getFkTipoPregunta() ) );
        }

        return entity;
    }

    public static PreguntaDto mapEntityToDto( PreguntaEntity entity ) throws IndexDatabaseException {
        final PreguntaDto dto = FabricaDto.crearPreguntaDto();

        dto.set_id(entity.get_id());
        dto.setPregunta(entity.getPregunta());
        dto.setStatus(entity.getStatus());

        dto.setFkTipoPregunta(TipoPreguntaMapper.mapEntityToDto(entity.getFkTipoPregunta()));

        return dto;
    }

}
