package com.empresag;

import java.util.List;
import java.util.Objects;

public class AnalisisMapper {

    public static AnalisisEntity mapDtoToEntity( AnalisisDto dto )
    {
        AnalisisEntity entity = FabricaEntity.crearAnalisisEntity(dto.get_id());

        entity.setConclusiones(dto.getConclusiones());

        return entity;
    }

    public static AnalisisDto mapEntityToDto( AnalisisEntity entity ) throws IndexDatabaseException {
        final AnalisisDto dto = FabricaDto.crearAnalisisDto();

        dto.setConclusiones(entity.getConclusiones());

        return dto;
    }
}