package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pregunta", schema = "empresag", catalog = "")
public class PreguntaEntity extends BaseEntity{
    private String pregunta;
    private int status;
    private TipoEntity fkTipo;

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

}
