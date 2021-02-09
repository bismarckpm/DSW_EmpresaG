package com.empresag.opcion;

import com.empresag.*;

public class ComandoEliminarOpcion extends ComandoBase {

    private long id;

    private boolean eliminado = false;

    public ComandoEliminarOpcion(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
        OpcionEntity opcionEntity = daoOpcion.find(id, OpcionEntity.class);

        daoOpcion.delete(opcionEntity);
        eliminado = true;

    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }

}
