package com.empresag.pregunta;

import com.empresag.*;

public class ComandoEditarPregunta extends ComandoBase {

    private long id;
    private PreguntaDto preguntaDto;
    private boolean editado = false;

    public ComandoEditarPregunta(long _id, PreguntaDto _preguntaDto) {
        this.id = _id;
        this.preguntaDto = _preguntaDto;
    }

    @Override
    public void execute() throws Exception {

        DaoPregunta daoPregunta =  FabricaDao.crearDaoPregunta();
        PreguntaEntity preguntaOld = daoPregunta.find(id, PreguntaEntity.class);

        if (preguntaOld != null){

            preguntaOld.setPregunta(preguntaDto.getPregunta());
            preguntaOld.setStatus(preguntaDto.getStatus());
            preguntaOld.setFkTipoPregunta(TipoPreguntaMapper.mapDtoToEntity(preguntaDto.getFkTipoPregunta()));

            daoPregunta.update(preguntaOld);

            editado = true;

        }
    }

    @Override
    public Boolean getResult() {
        return editado;
    }

}
