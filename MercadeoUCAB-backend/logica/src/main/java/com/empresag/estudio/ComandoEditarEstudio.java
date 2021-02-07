package com.empresag.estudio;

import com.empresag.*;

import java.util.List;

public class ComandoEditarEstudio extends ComandoBase {

    private long id;
    private EstudioDto estudioDto;
    private boolean editado = false;

    public ComandoEditarEstudio(long id, EstudioDto estudioDto) {
        this.id = id;
        this.estudioDto = estudioDto;
    }

    @Override
    public void execute() throws Exception {

        DaoEstudio estudioDao =  FabricaDao.crearDaoEstudio();
        EstudioEntity estudioOld = estudioDao.find(id, EstudioEntity.class);

        if (estudioOld != null){

            estudioOld.setEstado(estudioDto.getEstado());
            estudioOld.setFechaRealizacion(estudioDto.getFechaRealizacion());
            estudioOld.setFechaCulminacion(estudioDto.getFechaCulminacion());

            AnalisisEntity analisisEntity;
            DaoAnalisis daoAnalisis = new DaoAnalisis();
            analisisEntity = daoAnalisis.find(estudioDto.getFkAnalisis().get_id(), AnalisisEntity.class);
            estudioOld.setFkAnalisis(analisisEntity);

            estudioDao.update(estudioOld);

            editado = true;

        }
    }

    @Override
    public Boolean getResult() {
        return editado;
    }

}
