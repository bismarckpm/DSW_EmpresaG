package com.empresag;

public class PreguntaEstudioDto extends BaseDto {
    private int requerido;
    private EstudioDto fkEstudio;
    private PreguntaDto fkPregunta;

    public int getRequerido() {
        return requerido;
    }

    public void setRequerido(int requerido) {
        this.requerido = requerido;
    }

    public EstudioDto getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioDto fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

    public PreguntaDto getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaDto fkPregunta) {
        this.fkPregunta = fkPregunta;
    }
}
