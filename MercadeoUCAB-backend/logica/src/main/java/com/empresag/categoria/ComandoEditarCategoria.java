package com.empresag.categoria;

import com.empresag.*;

public class ComandoEditarCategoria extends ComandoBase {

    private long id;
    private CategoriaDto categoriaDto;
    private boolean editado = false;

    public ComandoEditarCategoria(long _id,CategoriaDto _categoriaDto) {
        this.id = _id;
        this.categoriaDto = _categoriaDto;
    }

    @Override
    public void execute() throws Exception {

        DaoCategoria daoCategoria =  FabricaDao.crearDaoCategoria();
        CategoriaEntity categoriaOld = daoCategoria.find(id, CategoriaEntity.class);

        if (categoriaOld != null){

            categoriaOld.setDescripcion(categoriaDto.getDescripcion());
            categoriaOld.setNombre(categoriaDto.getNombre());

            daoCategoria.update(categoriaOld);

            editado = true;

        }
    }

    @Override
    public Boolean getResult() {
        return editado;
    }

}
