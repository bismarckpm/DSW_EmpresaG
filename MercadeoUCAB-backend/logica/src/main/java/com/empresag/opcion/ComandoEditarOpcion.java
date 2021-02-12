package com.empresag.opcion;

import com.empresag.*;

public class ComandoEditarOpcion extends ComandoBase {

    private long id;
    private OpcionDto opcionDto;
    private boolean editado = false;

    public ComandoEditarOpcion(long _id,OpcionDto _opcionDto) {
        this.id = _id;
        this.opcionDto = _opcionDto;
    }

    @Override
    public void execute() throws Exception {

        DaoOpcion daoOpcion =  FabricaDao.crearDaoOpcion();
        OpcionEntity opcionOld = daoOpcion.find(id, OpcionEntity.class);

        if (opcionOld != null){

            opcionOld.setValor(opcionDto.getValor());
            opcionOld.setRangoInicial(opcionDto.getRangoInicial());
            opcionOld.setRangoFinal(opcionDto.getRangoFinal());

            daoOpcion.update(opcionOld);

            editado = true;

        }
    }

    @Override
    public Boolean getResult() {
        return editado;
    }

}
