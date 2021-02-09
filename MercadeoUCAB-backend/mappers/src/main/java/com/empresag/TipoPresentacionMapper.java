package com.empresag;

public class TipoPresentacionMapper {

    public static TipoPresentacionEntity mapDtoToEntity(TipoPresentacionDto dto){

        TipoPresentacionEntity entity = FabricaEntity.crearTipoPresentacionEntity();

        entity.setFkPresentacion(PresentacionMapper.mapDtoToEntity(dto.getFkPresentacion()));
        entity.setFkTipo(TipoMapper.mapDtoToEntity(dto.getFkTipo()));

        return entity;

    }

}
