package com.empresag;

public class RolMapper {

    public static RolEntity mapDtoToEntity(RolDto dto){

        RolEntity entity = FabricaEntity.crearRolEntity(dto.get_id());

        entity.setNombre(dto.getNombre());

        return entity;
    }

    public static RolDto mapEntityToDto( RolEntity entity ) throws PruebaExcepcion, IndexDatabaseException {
        final RolDto dto = new RolDto();

        dto.set_id(entity.get_id());
        dto.setNombre( entity.getNombre());

        return dto;
    }
}
