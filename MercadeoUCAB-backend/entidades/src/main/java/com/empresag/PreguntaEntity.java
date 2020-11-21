package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta", schema = "empresag", catalog = "")
public class PreguntaEntity extends BaseEntity{
    private int id;
    private String pregunta;
    private int status;
    private TipoEntity fkTipo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pregunta")
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    public TipoEntity getFkTipo() {
        return fkTipo;
    }

    public void setFkTipo(TipoEntity fkTipo) {
        this.fkTipo = fkTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreguntaEntity that = (PreguntaEntity) o;
        return id == that.id &&
                status == that.status &&
                fkTipo == that.fkTipo &&
                Objects.equals(pregunta, that.pregunta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pregunta, status, fkTipo);
    }
}
