package com.empresag;

import java.util.Objects;

public class LugarMapper {

    public static LugarEntity mapDtoToEntity(LugarDto dto){

//        LugarEntity entity = FabricaEntity.crearLugarEntity(dto.get_id());

//        entity.setNombre(dto.getNombre());

        DaoLugar daoLugar = FabricaDao.crearDaoLugar();
        return daoLugar.find(dto.get_id(), LugarEntity.class);
    }

    public static LugarDto mapEntityToDto( LugarEntity entity ) throws IndexDatabaseException {
        final LugarDto dto = new LugarDto();

        dto.set_id(entity.get_id());
        dto.setNombre( entity.getNombre());
        dto.setTipo( entity.getTipo() );

        if ( Objects.nonNull( entity.getFkLugar() ) ) {
            dto.setFkLugar(mapEntityToDto(entity.getFkLugar()));
        }

        return dto;
    }

}
