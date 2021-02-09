package com.empresag;

import java.util.Objects;

public class EstudioMapper {

    public static EstudioEntity mapDtoToEntity( EstudioDto dto )
    {
        EstudioEntity entity = FabricaEntity.crearEstudioEntity(dto.get_id());

        entity.setFechaRealizacion(dto.getFechaRealizacion());
        entity.setFechaCulminacion(dto.getFechaCulminacion());
        entity.setEstado(dto.getEstado());

        DaoAnalisis daoAnalisis = FabricaDao.crearDaoAnalisis();
        entity.setFkAnalisis(daoAnalisis.find(dto.getFkAnalisis().get_id(), AnalisisEntity.class));

        return entity;
    }

    public static EstudioDto mapEntityToDto( EstudioEntity entity ) throws IndexDatabaseException {
        final EstudioDto dto = FabricaDto.crearEstudioDto();

        dto.setFechaRealizacion(entity.getFechaRealizacion());
        dto.setFechaCulminacion(entity.getFechaCulminacion());
        dto.setEstado(entity.getEstado());

        if (Objects.nonNull(entity.getFkAnalisis())) {
            dto.setFkAnalisis(AnalisisMapper.mapEntityToDto(entity.getFkAnalisis()));
        }

        return dto;
    }

}
