package com.empresag.opcion;

import com.empresag.*;

import java.util.List;

public class ComandoEliminarOpcion extends ComandoBase {

    private long id;

    private boolean eliminado = false;

    private List<OpcionEntity> opcionList;

    public ComandoEliminarOpcion(long id) {
        this.id = id;
    }

    public ComandoEliminarOpcion(List<OpcionEntity> list) { this.opcionList = list; };

    @Override
    public void execute() throws Exception {

        DaoOpcion daoOpcion = FabricaDao.crearDaoOpcion();
        OpcionEntity opcionEntity = daoOpcion.find(id, OpcionEntity.class);

        daoOpcion.delete(opcionEntity);
        eliminado = true;

    }

    public void executeList() {

        try {
            for (OpcionEntity opcion : opcionList) {
                ComandoEliminarOpcion eliminarOpcion = new ComandoEliminarOpcion(opcion.get_id());
                eliminarOpcion.execute();
            }
        }
        catch (Exception e){

        }

    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }

}
