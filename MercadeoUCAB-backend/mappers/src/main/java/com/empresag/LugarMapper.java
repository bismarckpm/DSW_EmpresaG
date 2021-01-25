package com.empresag;

public class LugarMapper {

    public static LugarEntity mapDtoToEntity(LugarDto dto){

        LugarEntity entity = FabricaEntity.crearLugarEntity(dto.get_id());

        entity.setNombre(dto.getNombre());

        return entity;
    }

    public static LugarDto mapEntityToDto( LugarEntity entity ) throws PruebaExcepcion, IndexDatabaseException {
        final LugarDto dto = new LugarDto();

        dto.set_id(entity.get_id());
        dto.setNombre( entity.getNombre());
        dto.setTipo( entity.getTipo() );
        dto.setFkLugar( mapEntityToDto( entity.getFkLugar() ) );

        return dto;
    }

}
