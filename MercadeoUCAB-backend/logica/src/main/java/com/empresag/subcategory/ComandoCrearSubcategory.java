package com.empresag.subcategory;

import com.empresag.*;

public class ComandoCrearSubcategory extends ComandoBase {

    private CategoriaSubcategoriaDto categoriaSubcategoriaDto;

    public ComandoCrearSubcategory(CategoriaSubcategoriaDto categoriaSubcategoriaDto) {
        this.categoriaSubcategoriaDto = categoriaSubcategoriaDto;
    }

    @Override
    public void execute() throws Exception {
        DaoSubcategoria daoSubcategoria = FabricaDao.crearDaoSubcategoria();
        SubcategoriaEntity subcategoria = SubcategoriaMapper
                .mapDtoToEntity(categoriaSubcategoriaDto.getFkSubcategoria());

        daoSubcategoria.insert(subcategoria);

        DaoCategoriaSubcategoria daoCategoriaSubcategoria = FabricaDao
                .crearDaoCategoriaSubcategoria();

        CategoriaEntity categoria = CategoriaMapper
                .mapDtoToEntity(categoriaSubcategoriaDto.getFkCategoria());

        CategoriaSubcategoriaEntity cs = FabricaEntity.crearCategoriaSubcategoriaEntity();
        cs.setFkCategoria(categoria);
        cs.setFkSubcategoria(subcategoria);

        categoriaSubcategoriaDto = CategoriaSubcategoriaMapper.mapEntityToDto(daoCategoriaSubcategoria.insert(cs));
    }

    @Override
    public CategoriaSubcategoriaDto getResult() {
        return categoriaSubcategoriaDto;
    }
}
