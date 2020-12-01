package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta_estudio", schema = "empresag", catalog = "")
public class PreguntaEstudioEntity extends BaseEntity{
    private int requerido;
    private EstudioEntity fkEstudio;
    private PreguntaEntity fkPregunta;

    @Basic
    @Column(name = "requerido")
    public int getRequerido() {
        return requerido;
    }

    public void setRequerido(int requerido) {
        this.requerido = requerido;
    }

    @ManyToOne
    @JoinColumn(name = "fk_estudio")
    public EstudioEntity getFkEstudio() {
        return fkEstudio;
    }

    public void setFkEstudio(EstudioEntity fkEstudio) {
        this.fkEstudio = fkEstudio;
    }

    @ManyToOne
    @JoinColumn(name = "fk_pregunta")
    public PreguntaEntity getFkPregunta() {
        return fkPregunta;
    }

    public void setFkPregunta(PreguntaEntity fkPregunta) {
        this.fkPregunta = fkPregunta;
    }

}
