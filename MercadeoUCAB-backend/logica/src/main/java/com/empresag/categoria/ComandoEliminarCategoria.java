package com.empresag.categoria;

import com.empresag.*;

public class ComandoEliminarCategoria extends ComandoBase {

    private long id;

    private boolean eliminado = false;

    public ComandoEliminarCategoria(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoCategoria daoCategoria = FabricaDao.crearDaoCategoria();
        CategoriaEntity categoriaEntity = daoCategoria.find(id, CategoriaEntity.class);

        daoCategoria.delete(categoriaEntity);
        eliminado = true;
    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }

}
