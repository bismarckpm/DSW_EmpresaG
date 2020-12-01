package com.empresag;

public class PosibleRespuestaDto extends BaseDto{
    private PreguntaDto fkPregunta;
    private OpcionDto fkOpcion;

    public PreguntaDto getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaDto fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    public OpcionDto getFkOpcion() {
        return fkOpcion;
    }

    public void setFkOpcion(OpcionDto fkOpcion) {
        this.fkOpcion = fkOpcion;
    }

}
