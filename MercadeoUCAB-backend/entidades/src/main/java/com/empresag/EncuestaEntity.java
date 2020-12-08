package com.empresag;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "encuesta", schema = "empresag")
public class EncuestaEntity extends BaseEntity{
    @Basic
    @Column(name = "fecha")
    private Timestamp fecha;

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "respuesta_texto")
    private String respuestaTexto;

    public String getRespuestaTexto() {
        return respuestaTexto;
    }

    public void setRespuestaTexto(String respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
    }

    @ManyToOne
    @JoinColumn(name = "fk_posible_respuesta")
    private PosibleRespuestaEntity fkPosibleRespuesta;

    public PosibleRespuestaEntity getFkPosibleRespuesta() {
        return fkPosibleRespuesta;
    }

    public void setFkPosibleRespuesta(PosibleRespuestaEntity fkPosibleRespuesta) {
        this.fkPosibleRespuesta = fkPosibleRespuesta;
    }

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    private PreguntaEntity fkPregunta;

    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }


}
