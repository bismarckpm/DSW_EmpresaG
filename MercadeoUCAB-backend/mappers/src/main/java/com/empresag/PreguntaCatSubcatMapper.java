package com.empresag;

import java.util.Objects;

public class PreguntaCatSubcatMapper {

    public static PreguntaCatSubcatEntity mapDtoToEntity( PreguntaCatSubcatDto dto )
    {
        PreguntaCatSubcatEntity entity = FabricaEntity.crearPreguntaCatSubcatEntity(dto.get_id());

        if ( Objects.nonNull( dto.getFkPregunta() ) )
        {
            entity.setFkPregunta( PreguntaMapper.mapDtoToEntity( dto.getFkPregunta() ) );
        }
        if ( Objects.nonNull( dto.getFkCategoria() ) )
        {
            entity.setFkCategoria( CategoriaMapper.mapDtoToEntity( dto.getFkCategoria() ) );
        }
        if ( Objects.nonNull( dto.getFkSubcategoria() ) )
        {
            entity.setFkSubcategoria( SubcategoriaMapper.mapDtoToEntity( dto.getFkSubcategoria() ) );
        }

        return entity;
    }

    public static PreguntaCatSubcatDto mapEntityToDto( PreguntaCatSubcatEntity entity ) throws IndexDatabaseException {
        final PreguntaCatSubcatDto dto = FabricaDto.crearPreguntaCatSubcatDto();

        dto.set_id(entity.get_id());
        dto.setFkPregunta(PreguntaMapper.mapEntityToDto(entity.getFkPregunta()));
        dto.setFkSubcategoria(SubcategoriaMapper.mapEntityToDto(entity.getFkSubcategoria()));
        dto.setFkCategoria(CategoriaMapper.mapEntityToDto(entity.getFkCategoria()));

        return dto;
    }

}
