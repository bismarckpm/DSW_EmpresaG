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

    @Basic
    @Column(name = "respuesta_rango_inicial")
    private Integer respuestaRangoInicial;

    public Integer getRespuestaRangoInicial() {
        return respuestaRangoInicial;
    }

    public void setRespuestaRangoInicial(Integer respuestaRangoInicial) {
        this.respuestaRangoInicial = respuestaRangoInicial;
    }

    @Basic
    @Column(name = "respuesta_rango_final")
    private Integer respuestaRangoFinal;

    public Integer getRespuestaRangoFinal() {
        return respuestaRangoFinal;
    }

    public void setRespuestaRangoFinal(Integer respuestaRangoFinal) {
        this.respuestaRangoFinal = respuestaRangoFinal;
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

    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaEntity fkPersona;

    public PersonaEntity getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(PersonaEntity fkPersona) {
        this.fkPersona = fkPersona;
    }

    @ManyToOne
    @JoinColumn(name = "fk_estudio")
    private EstudioEntity fkEstudio;

    public EstudioEntity getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioEntity fkEstudio) {
        this.fkEstudio = fkEstudio;
    }
}
