package com.empresag;

import java.util.Objects;

public class SubcategoriaMarcaMapper {

    public static SubcategoriaMarcaEntity mapDtoToEntity( SubcategoriaMarcaDto dto )
    {
        SubcategoriaMarcaEntity entity = FabricaEntity.crearSubcategoriaMarcaEntity(dto.get_id());

        DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();
        entity.setFkSubcategoria(daoSubcategoria.find(dto.getFkSubcategoria().get_id(), SubcategoriaEntity.class));

        DaoMarca daoMarca = FabricaDao.crearDaoMarca();
        entity.setFkMarca(daoMarca.find(dto.getFkMarca().get_id(), MarcaEntity.class));

        return entity;
    }

    public static SubcategoriaMarcaDto mapEntityToDto( SubcategoriaMarcaEntity entity ) throws IndexDatabaseException {
        final SubcategoriaMarcaDto dto = FabricaDto.crearSubcategoriaMarcaDto();

        if (Objects.nonNull(entity.getFkSubcategoria())) {
            dto.setFkSubcategoria(SubcategoriaMapper.mapEntityToDto(entity.getFkSubcategoria()));
        }

        if (Objects.nonNull(entity.getFkMarca())) {
            dto.setFkMarca(MarcaMapper.mapEntityToDto(entity.getFkMarca()));
        }

        return dto;
    }
}
