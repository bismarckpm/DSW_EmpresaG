package com.empresag;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo", schema = "empresag")
public class TipoEntity extends BaseEntity{
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

    public TipoEntity(long id, String nombre) {
        super(id);
        this.nombre = nombre;
    }

    public TipoEntity(long id) {
        super(id);
    }

    public TipoEntity() {
    }

    @Override
    public String toString() {
        return "TipoEntity{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
