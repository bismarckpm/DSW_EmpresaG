package com.empresag;

public class CategoriaMapper {

    public static CategoriaEntity mapDtoToEntity (CategoriaDto dto){
        CategoriaEntity entity = FabricaEntity.crearCategoriaEntity(dto.get_id());
        entity.setNombre(dto.getNombre());

        return entity;
    }

    public static CategoriaDto mapEntityToDto (CategoriaEntity entity) throws IndexDatabaseException {
        CategoriaDto dto = new CategoriaDto();

        dto.set_id(entity.get_id());
        dto.setNombre(entity.getNombre());

        return dto;

    }

}
