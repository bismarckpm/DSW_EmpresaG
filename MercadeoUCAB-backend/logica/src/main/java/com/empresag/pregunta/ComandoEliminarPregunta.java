package com.empresag.pregunta;

import com.empresag.*;

public class ComandoEliminarPregunta extends ComandoBase {

    private long id;

    private boolean eliminado = false;

    public ComandoEliminarPregunta(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {

        DaoPregunta daoPregunta = FabricaDao.crearDaoPregunta();
        PreguntaEntity preguntaEntity = daoPregunta.find(id, PreguntaEntity.class);

        daoPregunta.delete(preguntaEntity);
        eliminado = true;
    }

    @Override
    public Boolean getResult() {
        return eliminado;
    }
}
