package com.empresag;

public class CategoriaMapper {

    public static CategoriaEntity mapDtoToEntity( CategoriaDto dto )
    {
        CategoriaEntity entity = FabricaEntity.crearCategoriaEntity(dto.get_id());

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        return entity;
    }

    public static CategoriaDto mapEntityToDto( CategoriaEntity entity ) throws IndexDatabaseException {
        final CategoriaDto dto = FabricaDto.crearCategoriaDto();

        dto.set_id(entity.get_id());
        dto.setNombre(entity.getNombre());
        dto.setNombre(entity.getNombre());

        return dto;

    }

}
