package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta_estudio", schema = "empresag", catalog = "")
public class PreguntaEstudioEntity extends BaseEntity{
    private int id;
    private int requerido;
    private EstudioEntity fkEstudio;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreguntaEstudioEntity that = (PreguntaEstudioEntity) o;
        return id == that.id &&
                requerido == that.requerido &&
                fkEstudio == that.fkEstudio &&
                fkPregunta == that.fkPregunta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requerido, fkEstudio, fkPregunta);
    }
}
