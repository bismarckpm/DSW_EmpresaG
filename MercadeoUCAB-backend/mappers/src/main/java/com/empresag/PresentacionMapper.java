package com.empresag;

public class PresentacionMapper {

    public static PresentacionEntity mapDtoToEntity(PresentacionDto dto){
        PresentacionEntity entity = FabricaEntity.crearPresentacionEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

}
