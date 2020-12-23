package com.empresag;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "estudio", schema = "empresag")
public class EstudioEntity extends BaseEntity{
    @Basic
    @Column(name = "fecha_realizacion")
    private Date fechaRealizacion;
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    @Basic
    @Column(name = "fecha_culminacion")
    private Date fechaCulminacion;

    public Date getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    @Basic
    @Column(name = "estado")
    private int estado;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne
    @JoinColumn(name = "fk_analisis")
    private AnalisisEntity fkAnalisis;

    public AnalisisEntity getFkAnalisis() {
        return fkAnalisis;
    }

    public void setFkAnalisis(AnalisisEntity fkAnalisis) {
        this.fkAnalisis = fkAnalisis;
    }

    @Override
    public String toString() {
        return "EstudioEntity{" +
                "fechaRealizacion=" + fechaRealizacion +
                ", fechaCulminacion=" + fechaCulminacion +
                ", estado=" + estado +
                '}';
    }
}
