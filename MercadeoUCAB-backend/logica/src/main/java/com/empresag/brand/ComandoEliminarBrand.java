package com.empresag.brand;

import com.empresag.*;

import javax.ws.rs.core.Response;

public class ComandoEliminarBrand extends ComandoBase {

    private long id = 0;

    private boolean eliminado = false;

    public ComandoEliminarBrand(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoriaMarca daoSubcategoriaMarca = new DaoSubcategoriaMarca();
        SubcategoriaMarcaEntity sm = daoSubcategoriaMarca.find(id, SubcategoriaMarcaEntity.class);

        daoSubcategoriaMarca.delete(sm);
        eliminado = true;
    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }

}
