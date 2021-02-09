package com.empresag;

import java.util.Objects;

public class TipoPreguntaMapper {

    public static TipoPreguntaEntity mapDtoToEntity( TipoPreguntaDto dto )
    {
        TipoPreguntaEntity entity = FabricaEntity.crearTipoPreguntaEntity(dto.get_id());

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        return entity;
    }

    public static TipoPreguntaDto mapEntityToDto( TipoPreguntaEntity entity ) throws IndexDatabaseException {
        final TipoPreguntaDto dto = FabricaDto.crearTipoPreguntaDto();

        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }

}
