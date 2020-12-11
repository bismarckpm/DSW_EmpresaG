package com.empresag;

import java.util.List;

public class PreguntaEstudioDto extends BaseDto {
    private int requerido;
    private EstudioDto fkEstudio;
    private PreguntaDto fkPregunta;
    private List<EncuestaDto> listRespuestasTexto;
    private Double promedioRangoInicial;
    private Double promedioRangoFinal;

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

    public List<EncuestaDto> getListRespuestasTexto() {
        return listRespuestasTexto;
    }

    public void setListRespuestasTexto(List<EncuestaDto> listRespuestasTexto) {
        this.listRespuestasTexto = listRespuestasTexto;
    }

    public Double getPromedioRangoInicial() {
        return promedioRangoInicial;
    }

    public void setPromedioRangoInicial(Double promedioRangoInicial) {
        this.promedioRangoInicial = promedioRangoInicial;
    }

    public Double getPromedioRangoFinal() {
        return promedioRangoFinal;
    }

    public void setPromedioRangoFinal(Double promedioRangoFinal) {
        this.promedioRangoFinal = promedioRangoFinal;
    }
}
