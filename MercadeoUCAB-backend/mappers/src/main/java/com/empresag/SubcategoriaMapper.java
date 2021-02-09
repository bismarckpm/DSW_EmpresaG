package com.empresag;

import java.util.Objects;

public class SubcategoriaMapper {

    public static SubcategoriaEntity mapDtoToEntity( SubcategoriaDto dto )
    {
        SubcategoriaEntity entity = FabricaEntity.crearSubcategoriaEntity(dto.get_id());

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        return entity;
    }

    public static SubcategoriaDto mapEntityToDto( SubcategoriaEntity entity ) throws IndexDatabaseException {
        final SubcategoriaDto dto = FabricaDto.crearSubcategoriaDto();

        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }

}
