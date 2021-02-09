package com.empresag;

public class OpcionMapper {

    public static OpcionEntity mapDtoToEntity( OpcionDto dto )
    {
        OpcionEntity entity = FabricaEntity.crearOpcionEntity(dto.get_id());

        entity.setRangoFinal(dto.getRangoFinal());
        entity.setRangoInicial(dto.getRangoInicial());
        entity.setValor(dto.getValor());

        return entity;
    }

    public static OpcionDto mapEntityToDto( OpcionEntity entity ) throws IndexDatabaseException {
        final OpcionDto dto = FabricaDto.crearOpcionDto();

        dto.setRangoFinal(entity.getRangoFinal());
        dto.setRangoInicial(entity.getRangoInicial());
        dto.setValor(entity.getValor());

        return dto;
    }

}
