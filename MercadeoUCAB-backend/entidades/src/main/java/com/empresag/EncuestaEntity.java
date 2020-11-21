package com.empresag;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "encuesta", schema = "empresag", catalog = "")
public class EncuestaEntity extends BaseEntity{
    private int id;
    private Timestamp fecha;
    private String respuestaTexto;
    private PosibleRespuestaEntity fkPosibleRespuesta;
    private PreguntaEntity fkPregunta;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fecha")
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "respuesta_texto")
    public String getRespuestaTexto() {
        return respuestaTexto;
    }

    public void setRespuestaTexto(String respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
    }

    @ManyToOne
    @JoinColumn(name = "fk_posible_respuesta")
    public PosibleRespuestaEntity getFkPosibleRespuesta() {
        return fkPosibleRespuesta;
    }

    public void setFkPosibleRespuesta(PosibleRespuestaEntity fkPosibleRespuesta) {
        this.fkPosibleRespuesta = fkPosibleRespuesta;
    }

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncuestaEntity that = (EncuestaEntity) o;
        return id == that.id &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(respuestaTexto, that.respuestaTexto) &&
                Objects.equals(fkPosibleRespuesta, that.fkPosibleRespuesta) &&
                Objects.equals(fkPregunta, that.fkPregunta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, respuestaTexto, fkPosibleRespuesta, fkPregunta);
    }
}
