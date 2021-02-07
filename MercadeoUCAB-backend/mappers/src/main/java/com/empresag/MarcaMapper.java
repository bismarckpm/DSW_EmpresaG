package com.empresag;

public class MarcaMapper {

    public static MarcaEntity mapDtoToEntity( MarcaDto dto )
    {
        MarcaEntity entity = FabricaEntity.crearMarcaEntity(dto.get_id());

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        return entity;
    }

    public static MarcaDto mapEntityToDto( MarcaEntity entity ) throws IndexDatabaseException {
        final MarcaDto dto = new MarcaDto();

        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());

        return dto;
    }

}
