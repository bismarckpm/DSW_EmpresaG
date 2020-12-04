package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo_pregunta", schema = "empresag")
public class TipoPreguntaEntity extends BaseEntity{
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPreguntaEntity(long id) {
        super(id);
    }

    public TipoPreguntaEntity() {
    }

    @Override
    public String toString() {
        return "TipoPreguntaEntity{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
