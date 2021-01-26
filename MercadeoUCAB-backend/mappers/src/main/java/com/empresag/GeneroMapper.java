package com.empresag;

public class GeneroMapper {

    public static GeneroEntity mapDtoToEntity(GeneroDto dto){

        GeneroEntity entity = FabricaEntity.crearGeneroEntity(dto.get_id());

        entity.setNombre(dto.getNombre());

        return entity;
    }

    public static GeneroDto mapEntityToDto( GeneroEntity entity ) throws IndexDatabaseException {
        final GeneroDto dto = new GeneroDto();

        dto.set_id(entity.get_id());
        dto.setNombre( entity.getNombre());

        return dto;
    }
}
