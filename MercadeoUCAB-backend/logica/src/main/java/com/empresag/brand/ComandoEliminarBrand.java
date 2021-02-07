package com.empresag.brand;

import com.empresag.*;

public class ComandoEliminarBrand extends ComandoBase {

    private long id;

    private boolean eliminado = false;

    public ComandoEliminarBrand(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoriaMarca daoSubcategoriaMarca = FabricaDao.crearDaoSubcategoriaMarca();
        SubcategoriaMarcaEntity sm = daoSubcategoriaMarca.find(id, SubcategoriaMarcaEntity.class);

        daoSubcategoriaMarca.delete(sm);
        eliminado = true;
    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }

}
