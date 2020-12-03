package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta", schema = "empresag")
public class PreguntaEntity extends BaseEntity{
    @Basic
    @Column(name = "pregunta")
    private String pregunta;

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Basic
    @Column(name = "status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    private TipoPreguntaEntity fkTipoPregunta;

    public TipoPreguntaEntity getFkTipoPregunta() {
        return fkTipoPregunta;
    }

    public void setFkTipoPregunta(TipoPreguntaEntity fkTipoPregunta) {
        this.fkTipoPregunta = fkTipoPregunta;
    }

    public PreguntaEntity(long id) {
        super(id);
    }

    public PreguntaEntity() {
    }

    @Override
    public String toString() {
        return "PreguntaEntity{" +
                "pregunta='" + pregunta + '\'' +
                ", status=" + status +
                '}';
    }
}
