package com.empresag;

public class EdoCivilMapper {

    public static EdoCivilEntity mapDtoToEntity(EdoCivilDto dto){

        EdoCivilEntity entity = FabricaEntity.crearEdoCivilEntity(dto.get_id());

        entity.setNombre(dto.getNombre());

        return entity;
    }

    public static EdoCivilDto mapEntityToDto( EdoCivilEntity entity ) throws PruebaExcepcion, IndexDatabaseException {
        final EdoCivilDto dto = new EdoCivilDto();

        dto.set_id(entity.get_id());
        dto.setNombre( entity.getNombre());

        return dto;
    }

}