package com.empresag;

public class TipoMapper {

    public static TipoEntity mapDtoToEntity(TipoDto dto){
        TipoEntity entity = FabricaDao.crearDaoTipo().find(dto.get_id(), TipoEntity.class);
        return entity;
    }

}
