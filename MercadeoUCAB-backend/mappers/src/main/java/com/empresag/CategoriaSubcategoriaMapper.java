package com.empresag;

import java.util.Objects;

public class CategoriaSubcategoriaMapper {

    public static CategoriaSubcategoriaDto mapEntityToDto (CategoriaSubcategoriaEntity entity) throws IndexDatabaseException {

        CategoriaSubcategoriaDto dto = new CategoriaSubcategoriaDto();

        dto.set_id(entity.get_id());

        if (Objects.nonNull(entity.getFkCategoria()))
            dto.setFkCategoria(CategoriaMapper.mapEntityToDto(entity.getFkCategoria()));

        if (Objects.nonNull(entity.getFkSubcategoria()))
            dto.setFkSubcategoria(SubcategoriaMapper.mapEntityToDto(entity.getFkSubcategoria()));

        return dto;

    }

}
