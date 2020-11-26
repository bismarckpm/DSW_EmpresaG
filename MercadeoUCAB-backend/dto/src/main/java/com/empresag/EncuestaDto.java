package com.empresag;

import java.sql.Timestamp;

public class EncuestaDto extends BaseDto{
    private Timestamp fecha;
    private String respuestaTexto;
    private PosibleRespuestaDto fkPosibleRespuesta;
    private PreguntaDto fkPregunta;

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
}
