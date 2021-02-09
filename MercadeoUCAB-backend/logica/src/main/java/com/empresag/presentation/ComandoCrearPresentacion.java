package com.empresag.presentation;

import com.empresag.*;

public class ComandoCrearPresentacion extends ComandoBase {

    private TipoPresentacionDto dto;
    private TipoPresentacionEntity entity;

    public ComandoCrearPresentacion(TipoPresentacionDto dto) {
        this.dto = dto;
    }

    @Override
    public void execute() throws Exception {
        DaoTipoPresentacion daoTipoPresentacion = FabricaDao.crearDaoTipoPresentacion();
        entity = TipoPresentacionMapper.mapDtoToEntity(dto);
        daoTipoPresentacion.insert(entity);
    }

    @Override
    public TipoPresentacionEntity getResult() {
        return entity;
    }
}
