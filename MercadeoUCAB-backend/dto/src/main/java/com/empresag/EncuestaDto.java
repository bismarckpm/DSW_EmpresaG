package com.empresag;

import java.sql.Timestamp;

public class EncuestaDto extends BaseDto{
    private Timestamp fecha;
    private String respuestaTexto;
    private Integer respuestaRangoInicial;
    private Integer respuestaRangoFinal;
    private PosibleRespuestaDto fkPosibleRespuesta;
    private PreguntaDto fkPregunta;
    private EstudioDto fkEstudio;

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getRespuestaTexto() {
        return respuestaTexto;
    }

    public void setRespuestaTexto(String respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
    }

    public Integer getRespuestaRangoInicial() {
        return respuestaRangoInicial;
    }

    public void setRespuestaRangoInicial(Integer respuestaRangoInicial) {
        this.respuestaRangoInicial = respuestaRangoInicial;
    }

    public Integer getRespuestaRangoFinal() {
        return respuestaRangoFinal;
    }

    public void setRespuestaRangoFinal(Integer respuestaRangoFinal) {
        this.respuestaRangoFinal = respuestaRangoFinal;
    }

    public PosibleRespuestaDto getFkPosibleRespuesta() {
        return fkPosibleRespuesta;
    }

    public void setFkPosibleRespuesta(PosibleRespuestaDto fkPosibleRespuesta) {
        this.fkPosibleRespuesta = fkPosibleRespuesta;
    }

    public PreguntaDto getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaDto fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    public EstudioDto getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioDto fkEstudio) {
        this.fkEstudio = fkEstudio;
    }
}
