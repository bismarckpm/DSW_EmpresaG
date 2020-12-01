package com.empresag;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "estudio", schema = "empresag", catalog = "")
public class EstudioEntity extends BaseEntity{
    private Date fechaRealizacion;
    private Date fechaCulminacion;
    private int estado;
    private AnalisisEntity fkAnalisis;

    @Basic
    @Column(name = "fecha_realizacion")
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    @Basic
    @Column(name = "fecha_culminacion")
    public Date getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    @Basic
    @Column(name = "estado")
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @ManyToOne
    @JoinColumn(name = "fk_analisis")
    public AnalisisEntity getFkAnalisis() {
        return fkAnalisis;
    }

    public void setFkAnalisis(AnalisisEntity fkAnalisis) {
        this.fkAnalisis = fkAnalisis;
    }

}
